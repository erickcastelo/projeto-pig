
package dao;


import excecao.BDException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.ItemVenda;
import modelos.Produto;
import modelos.Venda;
import util.ControlaConexao;

public class VendaDAO {  
    
    private Connection conexao;
     public ItemVenda inserir(Venda venda, ItemVenda itv) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO venda(VENCLIID, VENFORID, VENDATA)"
                 + " VALUES (?, ?, ?)";

        try {
            long idVenda = 0, idProduto = 0, idCliente = 0;
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            Venda vendaDoida = preencherParametros(instrucao, venda); // a venda que eu for inserir

            instrucao.execute();
            
            String sql2 = "SELECT VENID, VENCLIID, VENFORID"
                    + " FROM venda"
                    + " ORDER BY VENID DESC LIMIT 1";
            
            instrucao.execute(sql2); 
            ResultSet resultado = instrucao.getResultSet();
            
            if(resultado.next()){
                idVenda = resultado.getLong("VENID");
                idCliente = resultado.getLong("VENCLIID");
                //JOptionPane.showMessageDialog(null, idVenda);
            }
            
            
            
            ItemVenda itvCompleto = new ItemVenda();
            itvCompleto.setProduto(itv.getProduto());
            venda.setId(idVenda);
            itvCompleto.setQtde(itv.getQtde());
            itvCompleto.setVenda(venda);
          // JOptionPane.showMessageDialog(null, itv.getProduto().getId() + "");
            
            
            return itv;
        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);           
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }    
     
     
    public List<Venda> pesquisar(Venda venda) throws BDException, ParseException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registro apenas com um ID específico.
        
        String sql = " SELECT VENID, CLINOME, VENDATA"
                + " FROM venda "
                + "INNER JOIN cliente ON CLIID = VENCLIID"
                + " GROUP BY VENID"
                + " HAVING CLINOME LIKE ?";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
        venda.setCliente((Cliente) (venda.getCliente()==null?"":venda.getCliente()));

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            instrucao.setString(1,"%"+venda.getCliente().getNomeCliente()+"%");
            
            resultados = instrucao.executeQuery();            
            
            List<Venda> vendas=new ArrayList<>();
            Venda vendaSaida;
            
            while(resultados.next()){
                vendaSaida = new Venda();
                
                vendaSaida.setId(resultados.getInt("VENID"));
                vendaSaida.setCliente(new Cliente(resultados.getString("CLINOME")));
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(resultados.getDate("VENDATA"));
                
                DateFormat f = DateFormat.getDateInstance(DateFormat.DATE_FIELD);                
                vendaSaida.setData(cal);
                vendas.add(vendaSaida);
            }
            
            return vendas;

        } catch (SQLException ex) {
           ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }    
     
    public void excluir(Venda venda) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM venda WHERE VENID = " + venda.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    }
    public void alterar(Produto produto) throws BDException{
        
         PreparedStatement instrucao = null;        
         
        try {
            conexao = ControlaConexao.getConexao();
            
            String sql = "UPDATE produto SET PRODESCRICAO = ?"
                    + " WHERE PROID = ?";
            
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, produto.getDescricao());
            instrucao.setInt(2, (int) produto.getId());
                        
            instrucao.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
    
    private Venda preencherParametros(PreparedStatement instrucao, Venda venda) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.
                
        if (venda.getCliente().getId() != 0 ){           
            instrucao.setLong(1, venda.getCliente().getId());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if (venda.getFormaPagto().getId() != 0){           
            instrucao.setLong(2, venda.getFormaPagto().getId());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
        
        Calendar c = venda.getData();
        java.util.Date data = c.getTime();
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        
         if (venda.getData() != null){           
            instrucao.setDate(3, sqlDate);
        } else {
            instrucao.setNull(3, java.sql.Types.VARCHAR);
        }
        return venda;
    }

}
