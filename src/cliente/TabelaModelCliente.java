
package cliente;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cliente;

public class TabelaModelCliente extends AbstractTableModel{
    
    private String colunas[] = {"Nome", "Endereço", "Cidade", "Sexo"};
    private List<Cliente> clientes;

    public TabelaModelCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    @Override
    public int getRowCount() {
       return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Cliente cliente = clientes.get(rowIndex);
       switch(columnIndex){
            case 0:
               return cliente.getNomeCliente();
            case 1:
                return cliente.getEndereco();
            case 2:
                return cliente.getCidade();
            case 3:
                return cliente.getSexo();
               
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
