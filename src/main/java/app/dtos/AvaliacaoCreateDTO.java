package app.dtos;

public class AvaliacaoCreateDTO {
    // identificação da rua OU (em versões futuras) um calcadaId
    private String rua;

    // notas 0..5
    private float notaGeral;
    private float notaIdoso;
    private float notaCego;
    private float notaCadeirante;
    private float notaCarrinho;

    // flags de acessibilidade
    private boolean presencaPisoTatil;
    private boolean rebaixamentoGuia;
    private boolean semObstaculos;
    private boolean iluminacaoNoturna;

    private String comentario;

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public float getNotaGeral() { return notaGeral; }
    public void setNotaGeral(float notaGeral) { this.notaGeral = notaGeral; }

    public float getNotaIdoso() { return notaIdoso; }
    public void setNotaIdoso(float notaIdoso) { this.notaIdoso = notaIdoso; }

    public float getNotaCego() { return notaCego; }
    public void setNotaCego(float notaCego) { this.notaCego = notaCego; }

    public float getNotaCadeirante() { return notaCadeirante; }
    public void setNotaCadeirante(float notaCadeirante) { this.notaCadeirante = notaCadeirante; }

    public float getNotaCarrinho() { return notaCarrinho; }
    public void setNotaCarrinho(float notaCarrinho) { this.notaCarrinho = notaCarrinho; }

    public boolean isPresencaPisoTatil() { return presencaPisoTatil; }
    public void setPresencaPisoTatil(boolean v) { this.presencaPisoTatil = v; }

    public boolean isRebaixamentoGuia() { return rebaixamentoGuia; }
    public void setRebaixamentoGuia(boolean v) { this.rebaixamentoGuia = v; }

    public boolean isSemObstaculos() { return semObstaculos; }
    public void setSemObstaculos(boolean v) { this.semObstaculos = v; }

    public boolean isIluminacaoNoturna() { return iluminacaoNoturna; }
    public void setIluminacaoNoturna(boolean v) { this.iluminacaoNoturna = v; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
}
