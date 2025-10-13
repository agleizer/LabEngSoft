package app.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // relação muitos-para-um: várias avaliações pertencem a uma calçada
    @ManyToOne
    @JoinColumn(name = "calcada_id")
    @JsonBackReference
    private Calcada calcada;

    private float notaGeral;
    private float notaIdoso;
    private float notaCego;
    private float notaCadeirante;
    private float notaCarrinho;

    private LocalDate dataAval;
    private String comentario;

    private boolean presencaPisoTatil;
    private boolean rebaixamentoGuia;
    private boolean semObstaculos;
    private boolean iluminacaoNoturna;

    // -------------------------------
    // Construtores
    // -------------------------------
    public Avaliacao() {}

    public Avaliacao(
        float notaGeral,
        float notaIdoso,
        float notaCego,
        float notaCadeirante,
        float notaCarrinho,
        LocalDate dataAval,
        String comentario,
        boolean presencaPisoTatil,
        boolean rebaixamentoGuia,
        boolean semObstaculos,
        boolean iluminacaoNoturna
    ) {
        this.notaGeral = notaGeral;
        this.notaIdoso = notaIdoso;
        this.notaCego = notaCego;
        this.notaCadeirante = notaCadeirante;
        this.notaCarrinho = notaCarrinho;
        this.dataAval = dataAval;
        this.comentario = comentario;
        this.presencaPisoTatil = presencaPisoTatil;
        this.rebaixamentoGuia = rebaixamentoGuia;
        this.semObstaculos = semObstaculos;
        this.iluminacaoNoturna = iluminacaoNoturna;
    }

    // -------------------------------
    // Getters e Setters
    // -------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calcada getCalcada() {
        return calcada;
    }

    public void setCalcada(Calcada calcada) {
        this.calcada = calcada;
    }

    public float getNotaGeral() {
        return notaGeral;
    }

    public void setNotaGeral(float notaGeral) {
        this.notaGeral = notaGeral;
    }

    public float getNotaIdoso() {
        return notaIdoso;
    }

    public void setNotaIdoso(float notaIdoso) {
        this.notaIdoso = notaIdoso;
    }

    public float getNotaCego() {
        return notaCego;
    }

    public void setNotaCego(float notaCego) {
        this.notaCego = notaCego;
    }

    public float getNotaCadeirante() {
        return notaCadeirante;
    }

    public void setNotaCadeirante(float notaCadeirante) {
        this.notaCadeirante = notaCadeirante;
    }

    public float getNotaCarrinho() {
        return notaCarrinho;
    }

    public void setNotaCarrinho(float notaCarrinho) {
        this.notaCarrinho = notaCarrinho;
    }

    public LocalDate getDataAval() {
        return dataAval;
    }

    public void setDataAval(LocalDate dataAval) {
        this.dataAval = dataAval;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isPresencaPisoTatil() {
        return presencaPisoTatil;
    }

    public void setPresencaPisoTatil(boolean presencaPisoTatil) {
        this.presencaPisoTatil = presencaPisoTatil;
    }

    public boolean isRebaixamentoGuia() {
        return rebaixamentoGuia;
    }

    public void setRebaixamentoGuia(boolean rebaixamentoGuia) {
        this.rebaixamentoGuia = rebaixamentoGuia;
    }

    public boolean isSemObstaculos() {
        return semObstaculos;
    }

    public void setSemObstaculos(boolean semObstaculos) {
        this.semObstaculos = semObstaculos;
    }

    public boolean isIluminacaoNoturna() {
        return iluminacaoNoturna;
    }

    public void setIluminacaoNoturna(boolean iluminacaoNoturna) {
        this.iluminacaoNoturna = iluminacaoNoturna;
    }

    // -------------------------------
    // Método auxiliar
    // -------------------------------
    public boolean isAcessibilidadePlena() {
        return presencaPisoTatil && rebaixamentoGuia && semObstaculos && iluminacaoNoturna;
    }
}
