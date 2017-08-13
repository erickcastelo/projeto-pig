
package produto;

import cliente.*;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cliente;
import modelos.Produto;

public class TabelaModeloProduto extends AbstractTableModel{
    
    private String colunas[] = {"ID", "Descrição"};
    private List<Produto> produtos;

    public TabelaModeloProduto(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    @Override
    public int getRowCount() {
       return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Produto produto = produtos.get(rowIndex);
       switch(columnIndex){
            case 0:
               return produto.getId();
            case 1:
                return produto.getDescricao();            
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
