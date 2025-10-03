import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CalcadaTest {

    private Rua criarRua() {
        return new Rua("Rua Teste", "Bairro Teste");
    }

    private Avaliacao criarAvaliacao(float nota) {
        return new Avaliacao(nota, 0, 0, 0, 0, 0, "Comentário", false, false, false, false);
    }

    @Test
    void testConstrutorBasicoEAssociacaoRua() {
        Rua rua = criarRua();
        Calcada calcada = new Calcada("Calçada Principal", 4.0f, 
                                      -23.5, -46.6, -23.6, -46.7, rua);
        
        assertEquals("Calçada Principal", calcada.getNome());
        assertEquals(4.0f, calcada.getAvaliacaoMedia(), 0.001);
        assertEquals(rua, calcada.getRua());
        assertTrue(calcada.getAvaliacoes().isEmpty());
    }

    @Test
    void testAdicionarAvaliacoes() {
        Rua rua = criarRua();
        Calcada calcada = new Calcada("Calçada A", 0.0f, 0, 0, 0, 0, rua);
        
        Avaliacao aval1 = criarAvaliacao(5.0f);
        Avaliacao aval2 = criarAvaliacao(3.0f);
        
        calcada.adicionarAvaliacao(aval1);
        calcada.adicionarAvaliacao(aval2);
        
        List<Avaliacao> avaliacoes = calcada.getAvaliacoes();
        
        assertEquals(2, avaliacoes.size());
    }
}