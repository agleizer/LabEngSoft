import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class RelatorioTest {

    private Avaliacao criarAvaliacao(float nota) {
        return new Avaliacao(nota, 0, 0, 0, 0, 0, "Comentário", false, false, false, false);
    }

    @Test
    void testConstrutorBasico() {
        Date data = new Date();
        Relatorio relatorio = new Relatorio(data, "Zona Central", "Gilberto", "4.2");
        
        assertEquals(data, relatorio.getDataGeracaoRelatorio());
        assertEquals("Zona Central", relatorio.getArea());
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