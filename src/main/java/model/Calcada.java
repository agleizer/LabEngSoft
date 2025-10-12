package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "calcada")
public class Calcada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private float avaliacaoMedia;
    private double latitudeIni;
    private double longitudeIni;
    private double latitudeFim;
    private double longitudeFim;

    @ManyToOne
    @JoinColumn(name = "rua_id")
    private Rua rua;                          // assoc com Rua

    @OneToMany(mappedBy = "calcada", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes;       // assoc com Avaliações

    // Construtor vazio
    public Calcada() {
        this.avaliacoes = new ArrayList<>();
    }

    // Construtor basico
    public Calcada(String nome, float avaliacaoMedia,
                   double latitudeIni, double longitudeIni,
                   double latitudeFim, double longitudeFim,
                   Rua rua) {
        this.nome = nome;
        this.avaliacaoMedia = avaliacaoMedia;
        this.latitudeIni = latitudeIni;
        this.longitudeIni = longitudeIni;
        this.latitudeFim = latitudeFim;
        this.longitudeFim = longitudeFim;
        this.rua = rua;
        this.avaliacoes = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public float getAvaliacaoMedia() { return avaliacaoMedia; }
    public void setAvaliacaoMedia(float avaliacaoMedia) { this.avaliacaoMedia = avaliacaoMedia; }

    public double getLatitudeIni() { return latitudeIni; }
    public void setLatitudeIni(double latitudeIni) { this.latitudeIni = latitudeIni; }

    public double getLongitudeIni() { return longitudeIni; }
    public void setLongitudeIni(double longitudeIni) { this.longitudeIni = longitudeIni; }

    public double getLatitudeFim() { return latitudeFim; }
    public void setLatitudeFim(double latitudeFim) { this.latitudeFim = latitudeFim; }

    public double getLongitudeFim() { return longitudeFim; }
    public void setLongitudeFim(double longitudeFim) { this.longitudeFim = longitudeFim; }

    public Rua getRua() { return rua; }
    public void setRua(Rua rua) { this.rua = rua; }

    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void adicionarAvaliacao(Avaliacao avaliacao) { this.avaliacoes.add(avaliacao); }
}