
package cidade;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cidade;

public class TabelaModeloCidade extends AbstractTableModel{
    
    private String colunas[] = {"Nome", "Estado"};
    private List<Cidade> cidades;

    public TabelaModeloCidade(List<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    @Override
    public int getRowCount() {
        return cidades.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cidade cidade = cidades.get(rowIndex);
       switch(columnIndex){
            case 0:
               return cidade.getNomeCidade();
            case 1:
                return cidade.getEstado();
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {    
        
        return colunas[column];       
    }
    
    
}
