package app.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "relatorio")
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataGeracaoRelatorio;
    private String area;
    private String autor;
    private String mediaNotas;

    @ManyToMany
    @JoinTable(
        name = "relatorio_avaliacao",
        joinColumns = @JoinColumn(name = "relatorio_id"),
        inverseJoinColumns = @JoinColumn(name = "avaliacao_id")
    )
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    // No-arg constructor (required by JPA)
    public Relatorio() {}

    // Convenience constructor for testing or easy creation
    public Relatorio(LocalDate dataGeracaoRelatorio, String area, String autor, String mediaNotas) {
        this.dataGeracaoRelatorio = dataGeracaoRelatorio;
        this.area = area;
        this.autor = autor;
        this.mediaNotas = mediaNotas;
        this.avaliacoes = new ArrayList<>();
    }

    // getters and setters
    public LocalDate getDataGeracaoRelatorio() { return dataGeracaoRelatorio; }
    public void setDataGeracaoRelatorio(LocalDate dataGeracaoRelatorio) { this.dataGeracaoRelatorio = dataGeracaoRelatorio; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getMediaNotas() { return mediaNotas; }
    public void setMediaNotas(String mediaNotas) { this.mediaNotas = mediaNotas; }

    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void adicionarAvaliacao(Avaliacao avaliacao) { this.avaliacoes.add(avaliacao); }
}
