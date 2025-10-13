package app.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class CalcadasRepository {
    private final JdbcTemplate jdbc;

    public CalcadasRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> buscarPorRua(String ruaLike) {
        String sqlAgregado = """
            SELECT
              c.id AS calcada_id,
              c.nome AS nome_calcada,
              r.nome AS nome_rua,
              r.bairro,
              c.latitude_ini, c.longitude_ini,
              c.latitude_fim, c.longitude_fim,
              COALESCE(AVG(a.nota_geral), 0) AS media_geral,
              AVG(CASE WHEN a.presenca_piso_tatil THEN 1 ELSE 0 END) AS perc_piso_tatil,
              AVG(CASE WHEN a.rebaixamento_guia THEN 1 ELSE 0 END) AS perc_rebaixamento_guia,
              AVG(CASE WHEN a.iluminacao_noturna THEN 1 ELSE 0 END) AS perc_iluminacao,
              AVG(CASE WHEN a.sem_obstaculos THEN 1 ELSE 0 END) AS perc_sem_obstaculos
            FROM calcada c
            JOIN rua r ON r.id = c.rua_id
            LEFT JOIN avaliacao a ON a.calcada_id = c.id
            WHERE r.nome ILIKE ?
            GROUP BY c.id, c.nome, r.nome, r.bairro,
                     c.latitude_ini, c.longitude_ini, c.latitude_fim, c.longitude_fim
            ORDER BY r.nome, c.id
        """;

        List<Map<String, Object>> agregados = jdbc.queryForList(sqlAgregado, "%" + ruaLike + "%");
        if (agregados.isEmpty()) return agregados;

        // Busca avaliações de todas as calçadas retornadas
        String ids = agregados.stream()
                .map(m -> String.valueOf(m.get("calcada_id")))
                .reduce((a, b) -> a + "," + b)
                .orElse("-1");

        String sqlAval = """
            SELECT calcada_id, comentario, nota_geral
            FROM avaliacao
            WHERE calcada_id IN (%s)
            ORDER BY calcada_id, id
        """.formatted(ids);

        List<Map<String, Object>> avals = jdbc.queryForList(sqlAval);

        Map<Long, List<Map<String, Object>>> porCalcada = new HashMap<>();
        for (Map<String, Object> row : avals) {
            Long cid = ((Number) row.get("calcada_id")).longValue();
            porCalcada.computeIfAbsent(cid, k -> new ArrayList<>()).add(row);
        }

        for (Map<String, Object> agg : agregados) {
            Long cid = ((Number) agg.get("calcada_id")).longValue();
            agg.put("avaliacoes", porCalcada.getOrDefault(cid, List.of()));
        }

        return agregados;
    }
}
