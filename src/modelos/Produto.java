
package modelos;

public class Produto {
    
    private long id;
    private String descricao;

    @Override
    public String toString() {
        return descricao;
    }

    public Produto() {
    }
    
   public Produto(long id){
       this.id = id;
   }
    
    public Produto(String descricao) {
        this.descricao = descricao;
    }
    
    public Produto(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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
   
}
