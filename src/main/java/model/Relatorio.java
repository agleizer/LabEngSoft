package model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList; // CORREÇÃO: Importa a classe ArrayList

public class Relatorio {
    private Date dataGeracaoRelatorio;
    private String area;
    private String autor;
    private String mediaNotas;

    private List<Avaliacao> avaliacoes;   // assoc com Avaliações

    // Construtor vazio
    public Relatorio() {
        this.avaliacoes = new ArrayList<>(); // Inicialização para evitar NullPointerException
    }

    // Construtor basico
    public Relatorio(Date dataGeracaoRelatorio, String area, String autor, String mediaNotas) {
        this.dataGeracaoRelatorio = dataGeracaoRelatorio;
        this.area = area;
        this.autor = autor;
        this.mediaNotas = mediaNotas;
        this.avaliacoes = new ArrayList<>(); // Boa prática
    }

    // getters e setters
    public Date getDataGeracaoRelatorio() { return dataGeracaoRelatorio; }
    public void setDataGeracaoRelatorio(Date dataGeracaoRelatorio) { this.dataGeracaoRelatorio = dataGeracaoRelatorio; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getMediaNotas() { return mediaNotas; }
    public void setMediaNotas(String mediaNotas) { this.mediaNotas = mediaNotas; }

    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void adicionarAvaliacao(Avaliacao avaliacao) { this.avaliacoes.add(avaliacao); }
}