package app.dtos;

import java.util.List;

public record CalcadaDTO(
        long id,
        String nome_rua,
        String bairro,
        String nome_calcada,
        double latitude_ini,
        double longitude_ini,
        double latitude_fim,
        double longitude_fim,
        double avaliacao_media,
        CaracteristicasDTO caracteristicas,
        List<AvaliacaoDTO> avaliacoes
) {}
