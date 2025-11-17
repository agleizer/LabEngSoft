package app.controllers;

import app.services.AvaliacaoService;
import app.repositories.AvaliacaoRepository;
import app.model.Avaliacao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

class AvaliacaoControllerTest {

    private AvaliacaoRepository repo;
    private AvaliacaoService service;
    private AvaliacaoController controller;

    @BeforeEach
    void setup() {
        repo = mock(AvaliacaoRepository.class);
        service = mock(AvaliacaoService.class);
        controller = new AvaliacaoController(repo, service);
    }

    // ---------------------------------------------------------------------
    // LISTAR TODAS AS AVALIAÇÕES
    // ---------------------------------------------------------------------
    @Test
    void deveListarTodasAvaliacoes() {
        Avaliacao a1 = mock(Avaliacao.class);
        Avaliacao a2 = mock(Avaliacao.class);

        when(repo.findAll()).thenReturn(List.of(a1, a2));

        List<Avaliacao> result = controller.listar(null);

        assertEquals(2, result.size());
        verify(repo).findAll();
    }

    // ---------------------------------------------------------------------
    // LISTAR POR CALÇADA
    // ---------------------------------------------------------------------
    @Test
    void deveListarPorCalcadaId() {
        Avaliacao a1 = mock(Avaliacao.class);

        when(repo.findByCalcadaId(10L)).thenReturn(List.of(a1));

        List<Avaliacao> result = controller.listar(10L);

        assertEquals(1, result.size());
        verify(repo).findByCalcadaId(10L);
    }

    // ---------------------------------------------------------------------
    // CRIAR AVALIAÇÃO COM SUCESSO
    // ---------------------------------------------------------------------
    @Test
    void deveCriarAvaliacaoComSucesso() {
        Map<String, Object> dados = Map.of("rua", "Rua A");

        doNothing().when(service).salvar(dados);

        ResponseEntity<?> resp = controller.criar(dados);

        assertEquals(200, resp.getStatusCodeValue());

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) resp.getBody();

        assertEquals("ok", body.get("status"));
        verify(service).salvar(dados);
    }

    // ---------------------------------------------------------------------
    // ERRO AO CRIAR AVALIAÇÃO
    // ---------------------------------------------------------------------
    @Test
    void deveRetornarErroQuandoServiceFalha() {
        Map<String, Object> dados = Map.of("rua", "Rua B");

        doThrow(new RuntimeException("falhou"))
                .when(service).salvar(dados);

        ResponseEntity<?> resp = controller.criar(dados);

        assertEquals(400, resp.getStatusCodeValue());

        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) resp.getBody();

        assertEquals("falhou", body.get("erro"));
        verify(service).salvar(dados);
    }

    // ---------------------------------------------------------------------
    // RESUMO SEM AVALIAÇÕES
    // ---------------------------------------------------------------------
    @Test
    void deveRetornarResumoVazioQuandoNaoHaAvaliacoes() {
        when(repo.findByCalcadaId(1L)).thenReturn(List.of());

        Map<String, Object> resp = controller.resumo(1L);

        assertEquals(0.0, resp.get("mediaGeral"));
        assertEquals(0, resp.get("qtdAvaliacoes"));
        assertTrue(((List<?>) resp.get("comentarios")).isEmpty());

        verify(repo).findByCalcadaId(1L);
    }

    // ---------------------------------------------------------------------
    // RESUMO COM AVALIAÇÕES
    // ---------------------------------------------------------------------
    @Test
    void deveRetornarResumoCorreto() {

        Avaliacao a1 = mock(Avaliacao.class);
        Avaliacao a2 = mock(Avaliacao.class);

        when(a1.getNotaGeral()).thenReturn(5f);
        when(a2.getNotaGeral()).thenReturn(3f);

        when(a1.isPresencaPisoTatil()).thenReturn(true);
        when(a2.isPresencaPisoTatil()).thenReturn(false);

        when(a1.getComentario()).thenReturn("Bom");
        when(a2.getComentario()).thenReturn("");

        when(a1.getNotaIdoso()).thenReturn(4f);
        when(a2.getNotaIdoso()).thenReturn(5f);
        when(a1.getNotaCadeirante()).thenReturn(3f);
        when(a2.getNotaCadeirante()).thenReturn(5f);
        when(a1.getNotaCarrinho()).thenReturn(4f);
        when(a2.getNotaCarrinho()).thenReturn(2f);

        when(repo.findByCalcadaId(9L)).thenReturn(List.of(a1, a2));

        Map<String, Object> r = controller.resumo(9L);

        assertEquals(4.0, r.get("mediaGeral"));
        assertEquals(2, r.get("qtdAvaliacoes"));

        // CORREÇÃO: controller devolve número inteiro (50), não 50.0
        assertEquals(50.0, ((Number) r.get("pisoTatil")).doubleValue());


        verify(repo).findByCalcadaId(9L);
    }
}
