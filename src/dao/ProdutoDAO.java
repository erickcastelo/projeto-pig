
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
import modelos.Produto;
import util.ControlaConexao;

public class ProdutoDAO {  
    
    private Connection conexao;
     public void inserir(Produto produto) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO produto(PRODESCRICAO) VALUES (?)";

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, produto);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
            
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }
     
     public List<Produto> produto() throws BDException{
        List<Produto> produtos = new ArrayList<>();
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            String sql = "SELECT PROID, PRODESCRICAO "+
                    "FROM produto";
            instrucao.execute(sql);
            
            ResultSet resultado = instrucao.getResultSet();           
            
            while(resultado.next()){
                Produto produto = new Produto();
                
                produto.setId(resultado.getInt("PROID"));
                produto.setDescricao(resultado.getString("PRODESCRICAO"));    
                 
                produtos.add(produto);
            }
                      
            System.out.print("executou");
            
            return produtos;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException("Ocorreu um problema ao tentar pesquisar os dados!"+ ex);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public List<Produto> pesquisar(Produto produto) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registro apenas com um ID específico.
        
        String sql = " SELECT PROID, PRODESCRICAO FROM produto "
                + " WHERE PRODESCRICAO LIKE ?";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
        produto.setDescricao(produto.getDescricao()==null?"":produto.getDescricao());

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            instrucao.setString(1,"%"+produto.getDescricao()+"%");
            
            resultados = instrucao.executeQuery();
            
            
            List<Produto> produtos=new ArrayList<>();
            Produto produtoSaida;
            
            while(resultados.next()){
                produtoSaida = new Produto();
                
                produtoSaida.setId(resultados.getInt("PROID"));
                produtoSaida.setDescricao(resultados.getString("PRODESCRICAO"));
                produtos.add(produtoSaida);
            }
            
            return produtos;

        } catch (SQLException ex) {
           
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }    
     
    public void excluir(Produto produto) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM produto WHERE PROID = " + produto.getId();
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
    
    private void preencherParametros(PreparedStatement instrucao, Produto produto) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (produto.getDescricao() != null) {
            instrucao.setString(1, produto.getDescricao());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
    }

}
