
package venda;

import produto.*;
import cliente.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cliente;
import modelos.ItemVenda;
import modelos.Produto;
import modelos.Venda;

public class TabelaModeloItemVenda extends AbstractTableModel{
    
    private String colunas[] = {"Produto", "Quantidades"};
    private List<ItemVenda> itemVendas;

    public TabelaModeloItemVenda(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }
    
    @Override
    public int getRowCount() {
       return itemVendas.size();
       
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         ItemVenda itemVenda = itemVendas.get(rowIndex);
       switch(columnIndex){
            case 0:
               return itemVenda.getProduto().getDescricao();
            case 1:
                return itemVenda.getQtde(); 
            
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
