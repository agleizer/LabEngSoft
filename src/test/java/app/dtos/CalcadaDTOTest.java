package app.dtos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalcadaDTOTest {

    @Test
    void deveCriarCalcadaDTOCorretamente() {
        CaracteristicasDTO car = new CaracteristicasDTO(1.0, 2.0, 3.0, 4.0);
        AvaliacaoDTO aval = new AvaliacaoDTO("bom", 5.0f);

        CalcadaDTO dto = new CalcadaDTO(
                10L,
                "Rua X",
                "Centro",
                "Trecho 1",
                1.1,
                2.2,
                3.3,
                4.4,
                5.5,
                car,
                List.of(aval)
        );

        assertEquals(10L, dto.id());
        assertEquals("Rua X", dto.nome_rua());
        assertEquals("Centro", dto.bairro());
        assertEquals("Trecho 1", dto.nome_calcada());
        assertEquals(1.1, dto.latitude_ini());
        assertEquals(2.2, dto.longitude_ini());
        assertEquals(3.3, dto.latitude_fim());
        assertEquals(4.4, dto.longitude_fim());
        assertEquals(5.5, dto.avaliacao_media());
        assertEquals(car, dto.caracteristicas());
        assertEquals(1, dto.avaliacoes().size());
        assertEquals("bom", dto.avaliacoes().get(0).comentario());
    }

    @Test
    void devePermitirCamposNulos() {
        CalcadaDTO dto = new CalcadaDTO(
                1L,
                null,
                null,
                null,
                0.0,
                0.0,
                0.0,
                0.0,
                0.0,
                null,
                null
        );

        assertNull(dto.nome_rua());
        assertNull(dto.bairro());
        assertNull(dto.nome_calcada());
        assertNull(dto.caracteristicas());
        assertNull(dto.avaliacoes());
    }
}
