public class Avaliacao {
    private float notaGeral;
    private float notaIdoso;
    private float notaCego;
    private float notaCadeirante;
    private float notaCarrinho;
    private int dataAval;
    private String comentario;
    private boolean presencaPisoTatil;
    private boolean rebaixamentoGuia;
    private boolean semObstaculos;
    private boolean iluminacaoNoturna;

    // Construtor vazio
    public Avaliacao() {}

    // Construtor basico
    public Avaliacao(float notaGeral, float notaIdoso, float notaCego, float notaCadeirante, float notaCarrinho, int dataAval, String comentario, boolean presencaPisoTatil, boolean rebaixamentoGuia, boolean semObstaculos, boolean iluminacaoNoturna) {
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
    // getters e setters
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

    public int getDataAval() { return dataAval; }
    public void setDataAval(int dataAval) { this.dataAval = dataAval; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public boolean isPresencaPisoTatil() { return presencaPisoTatil; }
    public void setPresencaPisoTatil(boolean presencaPisoTatil) { this.presencaPisoTatil = presencaPisoTatil; }

    public boolean isRebaixamentoGuia() { return rebaixamentoGuia; }
    public void setRebaixamentoGuia(boolean rebaixamentoGuia) { this.rebaixamentoGuia = rebaixamentoGuia; }

    public boolean isSemObstaculos() { return semObstaculos; }
    public void setSemObstaculos(boolean semObstaculos) { this.semObstaculos = semObstaculos; }

    public boolean isIluminacaoNoturna() { return iluminacaoNoturna; }
    public void setIluminacaoNoturna(boolean iluminacaoNoturna) { this.iluminacaoNoturna = iluminacaoNoturna; }

    public boolean isAcessibilidadePlena() { return presencaPisoTatil && rebaixamentoGuia && semObstaculos && iluminacaoNoturna; }

}