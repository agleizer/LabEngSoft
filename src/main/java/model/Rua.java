package model;

import jakarta.persistence.*;

@Entity
@Table(name = "rua")
public class Rua {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String bairro;

    // Construtor vazio
    public Rua() {}

    // Construtor basico
    public Rua(String nome, String bairro) {
        this.nome = nome;
        this.bairro = bairro;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }
}