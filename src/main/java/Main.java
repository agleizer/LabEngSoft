package app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import model.Rua;
import repositories.RuaDAO;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(RuaDAO ruaDAO) {
        return (args) -> {
            Rua rua = new Rua("Rua das Flores", "Centro");
            ruaDAO.create(rua);
            System.out.println("Rua criada!");

            List<Rua> ruas = ruaDAO.readAll();
            ruas.forEach(r -> System.out.println("Rua: " + r.getNome() + ", Bairro: " + r.getBairro()));
        };
    }
}
