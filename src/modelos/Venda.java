
package modelos;

import java.util.List;
import modelos.ItemVenda;
import java.util.Calendar;

public class Venda {
    
    private long id;
    private Calendar data;
    private Cliente cliente;
    private FormaPagto formaPagto;
    private List<ItemVenda> itens;

    @Override
    public String toString() {
        return data.toString();
    }
    
    public Venda() {
    } 
    
    public Venda(long id) {
        this.id = id;
    } 
      
    public Venda(long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public Venda(long id, Calendar data, Cliente cliente, FormaPagto formaPagto, List<ItemVenda> itens) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.formaPagto = formaPagto;
        this.itens = itens;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public FormaPagto getFormaPagto() {
        return formaPagto;
    }

    public void setFormaPagto(FormaPagto formaPagto) {
        this.formaPagto = formaPagto;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }
}
