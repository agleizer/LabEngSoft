package app.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaracteristicasDTOTest {

    @Test
    void deveCriarDTOComValoresCorretos() {
        CaracteristicasDTO dto = new CaracteristicasDTO(
                10.0,
                20.0,
                30.0,
                40.0
        );

        assertEquals(10.0, dto.piso_tatil());
        assertEquals(20.0, dto.rebaixamento_guia());
        assertEquals(30.0, dto.iluminacao_noturna());
        assertEquals(40.0, dto.sem_obstaculos());
    }
}
