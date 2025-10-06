package model;

public class Rua {
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