
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
import modelos.FormaPagto;
import modelos.Produto;
import util.ControlaConexao;

public class FormaPagtoDAO {  
    
    private Connection conexao;
     public void inserir(FormaPagto formaPagto) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO formapagto (FORDESCRICAO, FORQTDEPARCELAS)"
                 + " VALUES (?, ?)";

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, formaPagto);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
            
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }     
     
     
    public List<FormaPagto> pesquisar(FormaPagto forPagto) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        //Este comando mostra os resultados por nome parcial, mas também um 
        //registro apenas com um ID específico.
        
        String sql = " SELECT FORID, FORDESCRICAO, FORQTDEPARCELAS FROM formapagto "
                + " WHERE FORDESCRICAO LIKE ?";                   
        
        //Deixar o nome vazio, se for nulo, assim, mostra todos os registros.
        forPagto.setDescricao(forPagto.getDescricao()==null?"":forPagto.getDescricao());

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            instrucao.setString(1,"%"+forPagto.getDescricao()+"%");
            
            resultados = instrucao.executeQuery();
            
            List<FormaPagto> formaPagtos=new ArrayList<>();
            FormaPagto formaPagtoSaida;
            
            while(resultados.next()){
                formaPagtoSaida = new FormaPagto();
                
                formaPagtoSaida.setId(resultados.getInt("FORID"));
                formaPagtoSaida.setDescricao(resultados.getString("FORDESCRICAO"));
                formaPagtoSaida.setQtdeParcelas(resultados.getInt("FORQTDEPARCELAS"));
                formaPagtos.add(formaPagtoSaida);
            }
            
            return formaPagtos;

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
            
        } finally {
            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }    
     
    public void excluir(FormaPagto formaPagto) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM formapagto WHERE FORID = " + formaPagto.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    }
    public void alterar(FormaPagto formaPagto) throws BDException{
        
         PreparedStatement instrucao = null;        
         
        try {
            conexao = ControlaConexao.getConexao();
            
            String sql = "UPDATE formapagto SET FORDESCRICAO = ?, FORQTDEPARCELAS = ?"
                    + " WHERE FORID = ?";
            
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, formaPagto.getDescricao());
            instrucao.setInt(2, formaPagto.getQtdeParcelas());
            instrucao.setInt(3, (int) formaPagto.getId());
                        
            instrucao.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
    
    private void preencherParametros(PreparedStatement instrucao, FormaPagto formPagto) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (formPagto.getDescricao() != null) {
            instrucao.setString(1, formPagto.getDescricao());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        if (formPagto.getQtdeParcelas() != 0) {
            instrucao.setInt(2, formPagto.getQtdeParcelas());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
    }

}
