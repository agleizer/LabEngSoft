package services;

import dtos.*;
import repositories.CalcadasRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CalcadasService {
    private final CalcadasRepository repo;

    public CalcadasService(CalcadasRepository repo) {
        this.repo = repo;
    }

    public List<CalcadaDTO> buscarPorRua(String ruaLike) {
        List<Map<String, Object>> rows = repo.buscarPorRua(ruaLike);
        List<CalcadaDTO> saida = new ArrayList<>();

        for (Map<String, Object> r : rows) {
            long id = ((Number) r.get("calcada_id")).longValue();
            String nomeRua = (String) r.get("nome_rua");
            String bairro = (String) r.get("bairro");
            String nomeCalcada = (String) r.get("nome_calcada");
            double latIni = num(r.get("latitude_ini"));
            double lonIni = num(r.get("longitude_ini"));
            double latFim = num(r.get("latitude_fim"));
            double lonFim = num(r.get("longitude_fim"));
            double media = num(r.get("media_geral"));

            var car = new CaracteristicasDTO(
                    num(r.get("perc_piso_tatil")),
                    num(r.get("perc_rebaixamento_guia")),
                    num(r.get("perc_iluminacao")),
                    num(r.get("perc_sem_obstaculos"))
            );

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> avals = (List<Map<String, Object>>) r.getOrDefault("avaliacoes", List.of());
            List<AvaliacaoDTO> avalsDTO = avals.stream()
                    .map(a -> new AvaliacaoDTO(
                            (String) a.getOrDefault("comentario", ""),
                            ((Number) a.getOrDefault("nota_geral", 0)).floatValue()
                    ))
                    .toList();

            saida.add(new CalcadaDTO(id, nomeRua, bairro, nomeCalcada, latIni, lonIni, latFim, lonFim,
                    media, car, avalsDTO));
        }
        return saida;
    }

    private double num(Object o) {
        if (o == null) return 0;
        if (o instanceof Number n) return n.doubleValue();
        return Double.parseDouble(o.toString());
    }
}
