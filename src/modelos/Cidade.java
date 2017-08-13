
package modelos;

public class Cidade {
    
    private long id;
    private String nomeCidade;
    private Estado estado;

    @Override
    public String toString() {
        return nomeCidade;
    }

    public Cidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
    

    public Cidade() {
    }

    public Cidade(long id, String nomeCidade, Estado estado) {
        this.id = id;
        this.nomeCidade = nomeCidade;
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }   
}
