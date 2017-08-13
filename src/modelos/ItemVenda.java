
package modelos;

public class ItemVenda {
 
    private long id;
    private Produto produto;
    private int qtde;
    private Venda venda;

    public ItemVenda() {
    }
        
    
    public ItemVenda(long id, Produto produto, int qtde, Venda venda) {
        this.id = id;
        this.produto = produto;
        this.qtde = qtde;
        this.venda = venda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public ItemVenda get(int rowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
