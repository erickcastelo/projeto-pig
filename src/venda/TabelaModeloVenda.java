
package venda;

import produto.*;
import cliente.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelos.Cliente;
import modelos.Produto;
import modelos.Venda;

public class TabelaModeloVenda extends AbstractTableModel{
    
    private String colunas[] = {"Número", "Data", "Cliente"};
    private List<Venda> vendas;

    public TabelaModeloVenda(List<Venda> vendas) {
        this.vendas = vendas;
    }
    
    @Override
    public int getRowCount() {
       return vendas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Venda venda = vendas.get(rowIndex);
         
         Calendar c = venda.getData();
         Date data = c.getTime();
         DateFormat f = DateFormat.getDateInstance(DateFormat.DATE_FIELD);
       switch(columnIndex){
            case 0:
               return venda.getId();
            case 1:
                return f.format(data); 
            case 2:
                return venda.getCliente();
       }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    
}
