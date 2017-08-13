
package dao;

import excecao.BDException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.ItemVenda;
import modelos.Produto;
import modelos.Venda;
import util.ControlaConexao;

public class ItemVendaDAO {
    
    private Connection conexao;
     public void inserir(ItemVenda itv) throws BDException {
      //  JOptionPane.showMessageDialog(null, itv.getProduto().getId() + " " + itv.getVenda().getId());
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO itemvenda(ITVPROID, ITVQTDE, ITVVENID)"
                 + " VALUES (?, ?, ?)";
         
        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, itv);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
        } finally {            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }
     
     public List<ItemVenda> pesquisar(ItemVenda itv) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registro apenas com um ID específico.
        
        String sql = " SELECT ITVID, PRODESCRICAO, ITVQTDE"
                + " FROM produto "
                + "INNER JOIN itemvenda ON PROID = ITVPROID";
                //+ " WHERE CLINOME LIKE ?";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
       // venda.setCliente((Cliente) (venda.getCliente()==null?"":venda.getCliente()));

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            //instrucao.setString(1,"%"+venda.getCliente().getNomeCliente()+"%");
            
            resultados = instrucao.executeQuery();            
            
            List<ItemVenda> itvendas=new ArrayList<>();
            ItemVenda itvSaida;
            
            while(resultados.next()){
                itvSaida = new ItemVenda();
                itvSaida.setId(resultados.getLong("ITVID"));
                itvSaida.setProduto(new Produto(resultados.getString("PRODESCRICAO")));
                itvSaida.setQtde(resultados.getInt("ITVQTDE"));
                                
                itvendas.add(itvSaida);
            }
            
            return itvendas;

        } catch (SQLException ex) {
           ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
     
    public List<ItemVenda> pegaUltimo(ItemVenda itv) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registr+o apenas com um ID específico.
        
        String sql = " SELECT PRODESCRICAO, ITVQTDE"
                + " FROM produto "
                + "INNER JOIN itemvenda ON PROID = ITVPROID"
                + " ORDER BY ITVID DESC LIMIT 1";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
       // venda.setCliente((Cliente) (venda.getCliente()==null?"":venda.getCliente()));

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            //instrucao.setString(1,"%"+venda.getCliente().getNomeCliente()+"%");
            
            resultados = instrucao.executeQuery();            
            
            List<ItemVenda> itvendas=new ArrayList<>();
            ItemVenda itvSaida;
            
            while(resultados.next()){
                itvSaida = new ItemVenda();
                itvSaida.setProduto(new Produto(resultados.getString("PRODESCRICAO")));
                itvSaida.setQtde(resultados.getInt("ITVQTDE"));
                                
                itvendas.add(itvSaida);
            }
            
            return itvendas;

        } catch (SQLException ex) {
           ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    
    
     
    public void excluir(ItemVenda itemVenda) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM itemvenda WHERE ITVID = " + itemVenda.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    } 
     
     private void preencherParametros(PreparedStatement instrucao, ItemVenda itv) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (itv.getProduto().getId() != 0) {
            instrucao.setLong(1, itv.getProduto().getId());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if (itv.getQtde() != 0) {
            instrucao.setInt(2, itv.getQtde());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
        
        if (itv.getVenda().getId() != 0) {
            instrucao.setLong(3, itv.getVenda().getId());
        } else {
            instrucao.setNull(3, java.sql.Types.VARCHAR);
        }
    }
}
