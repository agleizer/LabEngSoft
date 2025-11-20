package app.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoDTOTest {

    @Test
    void deveCriarAvaliacaoDTO() {
        AvaliacaoDTO dto = new AvaliacaoDTO("ótimo", 4.5f);

        assertEquals("ótimo", dto.comentario());
        assertEquals(4.5f, dto.nota_geral());
    }

    @Test
    void deveAceitarComentarioNulo() {
        AvaliacaoDTO dto = new AvaliacaoDTO(null, 3.0f);

        assertNull(dto.comentario());
        assertEquals(3.0f, dto.nota_geral());
    }
}
