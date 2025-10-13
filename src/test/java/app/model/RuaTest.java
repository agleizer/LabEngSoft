package app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuaTest {

    @Test
    void testConstrutorVazioESetters() {
        Rua rua = new Rua();
        
        rua.setNome("Rua das Flores");
        rua.setBairro("Jardim Amarelo");
        
        assertEquals("Rua das Flores", rua.getNome());
        assertEquals("Jardim Amarelo", rua.getBairro());
    }

    @Test
    void testConstrutorBasico() {
        Rua rua = new Rua("Avenida Principal", "Centro");
        
        assertEquals("Avenida Principal", rua.getNome());
        assertEquals("Centro", rua.getBairro());
    }
}