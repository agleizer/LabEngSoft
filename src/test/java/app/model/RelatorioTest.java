package app.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RelatorioTest {

    private Avaliacao criarAvaliacao(float nota) {
        LocalDate data = LocalDate.of(2024, 9, 25); // fixed test date
        return new Avaliacao(nota, 0, 0, 0, 0, data,
                             "Comentário", false, false, false, false);
    }

    @Test
    void testConstrutorBasico() {
        LocalDate data = LocalDate.of(2024, 10, 11);
        Relatorio relatorio = new Relatorio(data, "Zona Central", "Gilberto", "4.2");
        
        assertEquals(data, relatorio.getDataGeracaoRelatorio());
        assertEquals("Zona Central", relatorio.getArea());
        assertEquals("Gilberto", relatorio.getAutor());
        assertEquals("4.2", relatorio.getMediaNotas());
    }

    @Test
    void testAdicionarAvaliacoes() {
        Relatorio relatorio = new Relatorio();
        Avaliacao aval1 = criarAvaliacao(5.0f);
        
        relatorio.adicionarAvaliacao(aval1);
        
        assertNotNull(relatorio.getAvaliacoes());
        assertEquals(1, relatorio.getAvaliacoes().size());
    }
}
