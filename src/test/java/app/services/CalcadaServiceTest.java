package app.services;

import app.dtos.AvaliacaoDTO;
import app.dtos.CalcadaDTO;
import app.dtos.CaracteristicasDTO;
import app.repositories.CalcadasRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalcadasServiceTest {

    @Mock
    CalcadasRepository repo;

    @InjectMocks
    CalcadasService service;

    @Test
    void deveRetornarCalcadasDTOQuandoBuscarPorRua() {
        Map<String, Object> row = new HashMap<>();
        row.put("calcada_id", 10L);
        row.put("nome_rua", "Paulista");
        row.put("bairro", "Bela Vista");
        row.put("nome_calcada", "Trecho 1");
        row.put("latitude_ini", 1.1);
        row.put("longitude_ini", 2.2);
        row.put("latitude_fim", 3.3);
        row.put("longitude_fim", 4.4);
        row.put("media_geral", 4.7);

        row.put("perc_piso_tatil", 0.9);
        row.put("perc_rebaixamento_guia", 0.8);
        row.put("perc_iluminacao", 0.7);
        row.put("perc_sem_obstaculos", 0.6);

        Map<String, Object> aval = new HashMap<>();
        aval.put("comentario", "boa");
        aval.put("nota_geral", 5.0);

        row.put("avaliacoes", List.of(aval));

        when(repo.buscarPorRua("paul")).thenReturn(List.of(row));

        List<CalcadaDTO> saida = service.buscarPorRua("paul");

        assertEquals(1, saida.size());
        CalcadaDTO c = saida.get(0);

        assertEquals(10L, c.id());
        assertEquals("Paulista", c.nome_rua());
        assertEquals("Bela Vista", c.bairro());
        assertEquals("Trecho 1", c.nome_calcada());

        assertEquals(1.1, c.latitude_ini());
        assertEquals(2.2, c.longitude_ini());
        assertEquals(3.3, c.latitude_fim());
        assertEquals(4.4, c.longitude_fim());
        assertEquals(4.7, c.avaliacao_media());

        CaracteristicasDTO car = c.caracteristicas();
        assertEquals(0.9, car.piso_tatil());
        assertEquals(0.8, car.rebaixamento_guia());
        assertEquals(0.7, car.iluminacao_noturna());
        assertEquals(0.6, car.sem_obstaculos());

        List<AvaliacaoDTO> avals = c.avaliacoes();
        assertEquals(1, avals.size());
        assertEquals("boa", avals.get(0).comentario());
        assertEquals(5.0f, avals.get(0).nota_geral());
    }

    @Test
    void deveInterpretarNumComNullComoZero() {
        Map<String, Object> row = new HashMap<>();
        row.put("calcada_id", 1L);
        row.put("nome_rua", "X");
        row.put("bairro", "Y");
        row.put("nome_calcada", "Z");

        row.put("latitude_ini", null);
        row.put("longitude_ini", null);
        row.put("latitude_fim", null);
        row.put("longitude_fim", null);
        row.put("media_geral", null);
        row.put("perc_piso_tatil", null);
        row.put("perc_rebaixamento_guia", null);
        row.put("perc_iluminacao", null);
        row.put("perc_sem_obstaculos", null);

        row.put("avaliacoes", List.of());

        when(repo.buscarPorRua("x")).thenReturn(List.of(row));

        CalcadaDTO dto = service.buscarPorRua("x").get(0);

        assertEquals(0.0, dto.latitude_ini());
        assertEquals(0.0, dto.longitude_ini());
        assertEquals(0.0, dto.latitude_fim());
        assertEquals(0.0, dto.longitude_fim());
        assertEquals(0.0, dto.avaliacao_media());

        CaracteristicasDTO car = dto.caracteristicas();
        assertEquals(0.0, car.piso_tatil());
        assertEquals(0.0, car.rebaixamento_guia());
        assertEquals(0.0, car.iluminacao_noturna());
        assertEquals(0.0, car.sem_obstaculos());
    }

    @Test
    void deveInterpretarNumComStringNumerica() {
        Map<String, Object> row = new HashMap<>();
        row.put("calcada_id", 2L);
        row.put("nome_rua", "X");
        row.put("bairro", "Y");
        row.put("nome_calcada", "Z");

        row.put("latitude_ini", "10.5");
        row.put("longitude_ini", "20.5");
        row.put("latitude_fim", "30.5");
        row.put("longitude_fim", "40.5");
        row.put("media_geral", "8.25");

        row.put("perc_piso_tatil", "1.0");
        row.put("perc_rebaixamento_guia", "2.0");
        row.put("perc_iluminacao", "3.0");
        row.put("perc_sem_obstaculos", "4.0");

        row.put("avaliacoes", List.of());

        when(repo.buscarPorRua("abc")).thenReturn(List.of(row));

        CalcadaDTO dto = service.buscarPorRua("abc").get(0);

        assertEquals(10.5, dto.latitude_ini());
        assertEquals(20.5, dto.longitude_ini());
        assertEquals(30.5, dto.latitude_fim());
        assertEquals(40.5, dto.longitude_fim());
        assertEquals(8.25, dto.avaliacao_media());
    }
}
