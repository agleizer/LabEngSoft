package app.services;

import app.model.Avaliacao;
import app.repositories.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoServiceTest {

    @Mock
    AvaliacaoRepository repository;

    @Mock
    JdbcTemplate jdbc;

    @InjectMocks
    AvaliacaoService service;

    @Test
    void deveListarTodasAvaliacoes() {
        Avaliacao a = new Avaliacao();
        when(repository.findAll()).thenReturn(List.of(a));

        var resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveLancarExcecaoQuandoRuaForNula() {
        Map<String, Object> dados = Map.of();

        var ex = assertThrows(IllegalArgumentException.class, () -> service.salvar(dados));

        assertEquals("Nome da rua é obrigatório.", ex.getMessage());
        verifyNoInteractions(repository);
    }

    @Test
    void deveSalvarAvaliacaoQuandoRuaExistente() {
        Map<String, Object> dados = Map.of("rua", "Paulista", "notaGeral", 5);

        when(jdbc.query(
                anyString(),
                any(org.springframework.jdbc.core.PreparedStatementSetter.class),
                any(org.springframework.jdbc.core.ResultSetExtractor.class))
        ).thenReturn(1L);

        when(jdbc.update(anyString(), any(Object[].class))).thenReturn(1);

        service.salvar(dados);

        verify(repository, times(1)).save(any(Avaliacao.class));
        verify(jdbc, atLeastOnce()).update(anyString(), any(Object[].class));
    }
}
