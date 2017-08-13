package modelos;

public class EstadoCivil {
    private int id;
    private String descricao;

    public EstadoCivil() {
        
    }

    @Override
    public String toString() {
        return descricao;
    }

    public EstadoCivil(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
