
package Estado;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Estado;

public class TabelaModeloEstado extends AbstractTableModel{
    
    private String colunas[] = {"Nome"};
    private List<Estado> estados;

    public TabelaModeloEstado(List<Estado> estados) {
        this.estados = estados;
    }
    
    @Override
    public int getRowCount() {
        return estados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Estado estado = estados.get(rowIndex);
       switch(columnIndex){
           case 0:
               return estado.getNome();
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return colunas[column];
        }
        return null;
    }
    
    
}
