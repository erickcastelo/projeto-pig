
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
import modelos.Cidade;
import modelos.Estado;
import util.ControlaConexao;

public class EstadoDAO {  
    
    private Connection conexao;
     public void inserir(Estado estado) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO estado(ESTNOME) VALUES (?)";

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, estado);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
            
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }

    }
     
     public List<Estado> estado() throws BDException{
        List<Estado> estados = new ArrayList<>();
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            String sql = "SELECT ESTID, ESTNOME "+
                    "FROM estado";
            instrucao.execute(sql);
            
            ResultSet resultado = instrucao.getResultSet();           
            
            while(resultado.next()){
                Estado estado = new Estado();
                
                estado.setId(resultado.getInt("ESTID"));
                estado.setNome(resultado.getString("ESTNOME"));    
                 
                estados.add(estado);
            }
                      
            System.out.print("executou");
            
            return estados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException("Ocorreu um problema ao tentar pesquisar os dados!"+ ex);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public Estado selecionar(int estid) throws BDException{
           
        Estado estado = null;
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();            
            String sql = "SELECT ESTID, ESTNOME FROM estado WHERE ESTID = " + estid;
            instrucao.executeQuery(sql);
            
            ResultSet resultado = instrucao.getResultSet();            
            if(resultado.next()){
                estado = new Estado();
                
                estado.setId(resultado.getInt("ESTID"));
                estado.setNome(resultado.getString("ESTNOME"));
            }
            return estado;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }
    } 
    
    public List<Estado> pesquisar(Estado estado) throws BDException{
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
        String sql = "SELECT ESTID, ESTNOME FROM estado WHERE ESTNOME LIKE ?";
        
        estado.setNome(estado.getNome()==null?"":estado.getNome());
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, estado.getNome());
            
            instrucao.setString(1, "%"+estado.getNome()+"%");
            resultados = instrucao.executeQuery();
            
            List<Estado> estados = new ArrayList<>();
            Estado estadoSaida;
            
            while (resultados.next()) {                
                estadoSaida = new Estado(resultados.getInt("ESTID"), resultados.getString("ESTNOME"));
                estados.add(estadoSaida);
            }
            return estados;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public void alterar(Estado estado) throws BDException{
        
         PreparedStatement instrucao = null;        
         
        try {
            conexao = ControlaConexao.getConexao();
            
            String sql = "UPDATE estado SET ESTNOME = ? WHERE ESTID = ?";
            
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, estado.getNome());
            instrucao.setInt(2, (int) estado.getId());
            
            instrucao.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public void excluir(Estado estado) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM estado WHERE ESTID = " + estado.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    } 
    
    private void preencherParametros(PreparedStatement instrucao, Estado estado) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (estado.getNome() != null) {
            instrucao.setString(1, estado.getNome());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
    }

}
