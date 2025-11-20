package app.services;

import app.model.Avaliacao;
import app.repositories.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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

    // ============================================================
    // TESTES ORIGINAIS
    // ============================================================

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
        ).thenReturn(1L); // já existe calcadaId = 1

        when(jdbc.update(anyString(), any(Object[].class))).thenReturn(1);

        service.salvar(dados);

        verify(repository, times(1)).save(any(Avaliacao.class));
        verify(jdbc, atLeastOnce()).update(anyString(), any(Object[].class));
    }

    // ============================================================
    // NOVOS TESTES COMPLETOS (COBERTURA TOTAL DO MÉTODO salvar)
    // ============================================================

    @Test
    void deveCriarRuaECalcadaQuandoNaoExistem() {

        Map<String, Object> dados = Map.of(
                "rua", "Nova Rua",
                "notaGeral", 4
        );

        // ORDEM EXATA DOS RETURNS DO jdbc.query:
        // 1) busca calçada → null
        // 2) busca ruaId após criar rua → 10
        // 3) busca calcadaId após criar calçada → 50
        when(jdbc.query(
                anyString(),
                any(org.springframework.jdbc.core.PreparedStatementSetter.class),
                any(org.springframework.jdbc.core.ResultSetExtractor.class))
        ).thenReturn(null)
                .thenReturn(10L)
                .thenReturn(50L);

        when(jdbc.update(anyString(), any(Object[].class))).thenReturn(1);

        service.salvar(dados);

        verify(repository).save(any(Avaliacao.class));
        verify(jdbc, atLeastOnce()).update(anyString(), any(Object[].class));
    }

    @Test
    void deveLancarErroQuandoNaoConsegueObterRuaId() {
        Map<String, Object> dados = Map.of("rua", "Rua Nova");

        // 1) calçada não existe
        // 2) falhou ao obter ruaId mesmo após criar
        when(jdbc.query(
                anyString(),
                any(org.springframework.jdbc.core.PreparedStatementSetter.class),
                any(org.springframework.jdbc.core.ResultSetExtractor.class))
        ).thenReturn(null)
                .thenReturn(null);

        when(jdbc.update(anyString(), any(Object[].class))).thenReturn(1); // tentou criar rua

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.salvar(dados)
        );

        assertTrue(ex.getMessage().contains("Falha ao criar/obter Rua"));
    }

    @Test
    void deveLancarErroQuandoNaoConsegueObterCalcadaId() {
        Map<String, Object> dados = Map.of("rua", "Rua XPTO");

        // Ordem correta dos returns:
        // 1) busca calçada → null
        // 2) busca ruaId após criar rua → ok (8)
        // 3) busca calcadaId após criar calçada → null (erro!)
        when(jdbc.query(
                anyString(),
                any(org.springframework.jdbc.core.PreparedStatementSetter.class),
                any(org.springframework.jdbc.core.ResultSetExtractor.class))
        ).thenReturn(null)
                .thenReturn(8L)
                .thenReturn(null);

        when(jdbc.update(anyString(), any(Object[].class))).thenReturn(1);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.salvar(dados)
        );

        assertTrue(ex.getMessage().contains("Falha ao criar/obter Calçada"));
    }
}
