
package modelos;

public class FormaPagto {
    
    private long id;
    private String descricao;
    private int qtdeParcelas;

    @Override
    public String toString() {
        return descricao + " " + qtdeParcelas + "x vezes";
    }

    public FormaPagto() {
    }

    public FormaPagto(long id, String descricao, int qtdeParcelas) {
        this.id = id;
        this.descricao = descricao;
        this.qtdeParcelas = qtdeParcelas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdeParcelas() {
        return qtdeParcelas;
    }

    public void setQtdeParcelas(int qtdeParcelas) {
        this.qtdeParcelas = qtdeParcelas;
    }
}
