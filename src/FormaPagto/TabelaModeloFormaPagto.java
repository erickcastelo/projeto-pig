
package FormaPagto;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cliente;
import modelos.FormaPagto;

public class TabelaModeloFormaPagto extends AbstractTableModel{
    
    private String colunas[] = {"Descrição", "Parcelas"};
    private List<FormaPagto> formaPagtos;

    public TabelaModeloFormaPagto(List<FormaPagto> formaPagtos) {
        this.formaPagtos = formaPagtos;
    }
    
    @Override
    public int getRowCount() {
       return formaPagtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         FormaPagto formaPagto = formaPagtos.get(rowIndex);
       switch(columnIndex){
            case 0:
               return formaPagto.getDescricao();
            case 1:
                return formaPagto.getQtdeParcelas() + "x";
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
