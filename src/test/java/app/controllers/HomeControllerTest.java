package app.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void deveRetornarForwardParaIndex() {
        HomeController controller = new HomeController();

        String resp = controller.home();

        assertEquals("forward:/index.html", resp);
    }
}
