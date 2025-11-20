package app.controllers;

import app.dtos.CalcadaDTO;
import app.dtos.CaracteristicasDTO;
import app.services.CalcadasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalcadasControllerTest {

    private CalcadasService service;
    private CalcadasController controller;

    @BeforeEach
    void setup() {
        service = mock(CalcadasService.class);
        controller = new CalcadasController(service);
    }

    @Test
    void deveDelegarParaServiceERetornarLista() {

        var car = new CaracteristicasDTO(
                10.0, 20.0, 30.0, 40.0
        );

        var dto = new CalcadaDTO(
                1L,
                "Rua X",
                "Centro",
                "Calcada 1",
                1.1, 2.2,
                3.3, 4.4,
                5.5,
                car,
                List.of()
        );

        when(service.buscarPorRua("Rua X")).thenReturn(List.of(dto));

        var resposta = controller.porRua("Rua X");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(service).buscarPorRua(captor.capture());
        assertEquals("Rua X", captor.getValue());

        assertNotNull(resposta.getBody());
        assertEquals(1, resposta.getBody().size());
        assertEquals("Rua X", resposta.getBody().get(0).nome_rua());
    }

    @Test
    void deveRetornarListaVaziaQuandoServiceRetornaVazia() {
        when(service.buscarPorRua("Nada")).thenReturn(List.of());

        var resposta = controller.porRua("Nada");

        assertNotNull(resposta.getBody());
        assertTrue(resposta.getBody().isEmpty());
    }
}
