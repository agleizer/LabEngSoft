package app.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import app.model.Avaliacao;
import app.model.Calcada;
import app.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
    private final AvaliacaoRepository repository;
    private final JdbcTemplate jdbc;

    public AvaliacaoService(AvaliacaoRepository repository, JdbcTemplate jdbc) {
        this.repository = repository;
        this.jdbc = jdbc;
    }

    // --- Métodos já existentes ---
    public List<Avaliacao> listarPorCalcada(Long calcadaId) {
        return repository.findByCalcadaId(calcadaId);
    }

    public List<Avaliacao> listarTodas() {
        return repository.findAll();
    }

    // --- Novo método para registrar avaliação ---
    public void salvar(Map<String, Object> dados) {
    String nomeRua = (String) dados.get("rua");
    if (nomeRua == null || nomeRua.isBlank()) {
        throw new IllegalArgumentException("Nome da rua é obrigatório.");
    }

    // tenta achar calçada existente
    String sqlBusca = """
        SELECT c.id FROM calcada c
        JOIN rua r ON r.id = c.rua_id
        WHERE r.nome ILIKE ?
        ORDER BY c.id LIMIT 1
    """;
    Long calcadaId = jdbc.query(sqlBusca, ps -> ps.setString(1, nomeRua),
            rs -> rs.next() ? rs.getLong(1) : null);

    // se não existir, cria rua e calçada genérica
    if (calcadaId == null) {
        // cria rua (se não existir)
        jdbc.update("INSERT INTO rua (nome, bairro) VALUES (?, ?) ON CONFLICT DO NOTHING",
                nomeRua, "Desconhecido");

        // pega id da rua
        Long ruaId = jdbc.query("""
            SELECT id FROM rua WHERE nome ILIKE ? LIMIT 1
        """, ps -> ps.setString(1, nomeRua),
                rs -> rs.next() ? rs.getLong(1) : null);

        if (ruaId == null) {
            throw new IllegalStateException("Falha ao criar/obter Rua para: " + nomeRua);
        }

        // tenta ler coordenadas do payload (opcionais)
        double latIni = ((Number) dados.getOrDefault("latitudeIni", 0)).doubleValue();
        double lonIni = ((Number) dados.getOrDefault("longitudeIni", 0)).doubleValue();
        double latFim = ((Number) dados.getOrDefault("latitudeFim", 0)).doubleValue();
        double lonFim = ((Number) dados.getOrDefault("longitudeFim", 0)).doubleValue();

        // cria calçada com colunas NOT NULL preenchidas
        jdbc.update("""
            INSERT INTO calcada (
                avaliacao_media,
                latitude_ini, longitude_ini,
                latitude_fim, longitude_fim,
                nome, rua_id
            )
            VALUES (0, ?, ?, ?, ?, 'Trecho principal', ?)
        """, latIni, lonIni, latFim, lonFim, ruaId);

        // pega id da nova calçada
        calcadaId = jdbc.query("""
            SELECT id FROM calcada
            WHERE rua_id = ?
            ORDER BY id DESC
            LIMIT 1
        """, ps -> ps.setLong(1, ruaId),
                rs -> rs.next() ? rs.getLong(1) : null);

        if (calcadaId == null) {
            throw new IllegalStateException("Falha ao criar/obter Calçada para a rua: " + nomeRua);
        }
    }


    // cria a nova avaliação normalmente
    Avaliacao a = new Avaliacao();
    Calcada c = new Calcada();
    c.setId(calcadaId);
    a.setCalcada(c);

    a.setNotaGeral(((Number) dados.getOrDefault("notaGeral", 0)).floatValue());
    a.setNotaIdoso(((Number) dados.getOrDefault("notaIdoso", 0)).floatValue());
    a.setNotaCego(((Number) dados.getOrDefault("notaCego", 0)).floatValue());
    a.setNotaCadeirante(((Number) dados.getOrDefault("notaCadeirante", 0)).floatValue());
    a.setNotaCarrinho(((Number) dados.getOrDefault("notaCarrinho", 0)).floatValue());
    a.setPresencaPisoTatil((Boolean) dados.getOrDefault("presencaPisoTatil", false));
    a.setRebaixamentoGuia((Boolean) dados.getOrDefault("rebaixamentoGuia", false));
    a.setSemObstaculos((Boolean) dados.getOrDefault("semObstaculos", false));
    a.setIluminacaoNoturna((Boolean) dados.getOrDefault("iluminacaoNoturna", false));
    a.setComentario((String) dados.getOrDefault("comentario", ""));
    a.setDataAval(LocalDate.now());

    repository.save(a);

    // recalcula média da calçada
    jdbc.update("""
        UPDATE calcada
        SET avaliacao_media = (
            SELECT COALESCE(AVG(nota_geral), 0)
            FROM avaliacao WHERE calcada_id = ?
        )
        WHERE id = ?
    """, calcadaId, calcadaId);
    }

}
