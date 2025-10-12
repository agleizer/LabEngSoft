package model;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            RuaDAO ruaDAO = new RuaDAO();
            Rua rua = new Rua("Rua das Flores", "Centro");
            ruaDAO.create(rua);
            System.out.println("Rua criada!");

            List<Rua> ruas = ruaDAO.readAll();
            ruas.forEach(r -> System.out.println("Rua: " + r.getNome() + ", Bairro: " + r.getBairro()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
