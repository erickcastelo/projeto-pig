
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
import modelos.Cliente;
import modelos.Estado;
import modelos.EstadoCivil;
import util.ControlaConexao;

public class ClienteDAO {  
    
    private Connection conexao;
     public void inserir(Cliente cliente) throws BDException {
        
        Connection conexao=null;
        PreparedStatement instrucao=null;

         String sql = "INSERT INTO cliente (CLINOME, CLIENDERECO, CLITELEFONE, CLICIDID, CLIETCID, CLISEXO)"
                 + " VALUES (?,?,?,?,?,?)";

        try {
            
            conexao = ControlaConexao.getConexao();

            instrucao = conexao.prepareStatement(sql);

            preencherParametros(instrucao, cliente);

            instrucao.execute();

        } catch (SQLException ex) {
             ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO,ex);
           
            
        } finally {
            
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }


    }
     
     
     
    public List<Cliente> cliente() throws BDException{
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            String sql = "SELECT CLIID, CLINOME, CLIENDERECO, CLITELEFONE, CLICIDID, CLIETCID"+
                    "FROM cliente";
            instrucao.execute(sql);
            
            ResultSet resultado = instrucao.getResultSet(); 
            
            CidadeDAO dao = new CidadeDAO();
            
            while(resultado.next()){
                Cliente cliente = new Cliente();
                
                cliente.setId(resultado.getInt("CLIID"));
                cliente.setNomeCliente(resultado.getString("CLINOME"));
                cliente.setEndereco(resultado.getString("CLIENDERECO"));
                cliente.setTelefone(resultado.getString("CLITELEFONE"));                             
               
                int clicidid = resultado.getInt("CLICIDID");
                cliente.setCidade(dao.selecionar(clicidid));                
                clientes.add(cliente);
            }
                      
            System.out.print("executou");
            
            return clientes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException("Ocorreu um problema ao tentar pesquisar os dados!"+ ex);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public Cliente selecionar(Cliente cliente) throws BDException{
         
        
        
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "SELECT CLIID, CLINOME, CIDNOME, CLICIDID, ETCDESCRICAO, CLIETCID" +
                        " FROM cliente" +
                        " INNER JOIN CIDADE ON CIDID = CLICIDID" +
                        " INNER JOIN ESTADOCIVIL ON ETCID = CLIETCID"; 
            instrucao.executeQuery(sql);
            
            ResultSet resultado = instrucao.getResultSet();            
            if(resultado.next()){
                cliente.setId(resultado.getInt("CLIID"));
                cliente.setNomeCliente(resultado.getString("CLINOME"));
                cliente.setEndereco(resultado.getString("CLIENDERECO"));
                cliente.setTelefone(resultado.getString("CLITELEFONE"));
                cliente.setCidade(new Cidade(resultado.getString("CIDNOME")));
                cliente.setEstadoCivil(new EstadoCivil(resultado.getInt("CLIETCID"), null));
            }           
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }
        return null;
    } 
    
    public List<Cliente> pesquisar(Cliente cliente) throws BDException{
        Connection conexao=null;
        PreparedStatement instrucao=null;
        ResultSet resultados=null;
        
       String sql = "SELECT CLIID, CLINOME, CLIENDERECO, CLITELEFONE, CIDNOME, CLICIDID, CIDID, ETCDESCRICAO, CLIETCID, CLISEXO" +
                        " FROM cliente" +
                        " INNER JOIN cidade ON CIDID = CLICIDID" +
                        " INNER JOIN estadocivil ON ETCID = CLIETCID" +
                        " WHERE CLINOME LIKE ?";
             
        cliente.setNomeCliente(cliente.getNomeCliente()==null?"":cliente.getNomeCliente());
        
        try {
            conexao = ControlaConexao.getConexao();
            instrucao = conexao.prepareStatement(sql);
            
            //JOptionPane.showMessageDialog(null, estado.getNome());
            
            instrucao.setString(1, "%"+cliente.getNomeCliente()+"%");
            resultados = instrucao.executeQuery();
            
            List<Cliente> clientes = new ArrayList<>();           
            
            //CidadeDAO dao = new CidadeDAO();
            
            while (resultados.next()) { 
               // int clicidid = resultados.getInt("CLICIDID");
                Cliente clienteSaida = new Cliente();
                
                clienteSaida.setId(resultados.getInt("CLIID"));
                clienteSaida.setNomeCliente(resultados.getString("CLINOME"));
                clienteSaida.setEndereco(resultados.getString("CLIENDERECO"));
                clienteSaida.setTelefone(resultados.getString("CLITELEFONE"));
                clienteSaida.setCidade(new Cidade(resultados.getLong("CIDID"), resultados.getString("CIDNOME"), null));
                clienteSaida.setEstadoCivil(new EstadoCivil(resultados.getInt("CLIETCID"), resultados.getString("ETCDESCRICAO")));
                clienteSaida.setSexo(resultados.getString("CLISEXO").charAt(0));
                clientes.add(clienteSaida);
            }
            return clientes;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{            
            ControlaConexao.fecharResultSet(resultados);
            ControlaConexao.fecharInstrucao(instrucao);
            ControlaConexao.fecharConexao(conexao);
        }
    }
    
    public void alterar(Cliente cliente) throws BDException{
        
         PreparedStatement instrucao = null;        
         
        try {
            conexao = ControlaConexao.getConexao();
            
            String sql = "UPDATE cliente SET CLINOME = ?,"
                    + " CLIENDERECO = ?, CLITELEFONE = ?, CLICIDID = ?, CLIETCID = ?, CLISEXO = ?"
                    + " WHERE CLIID = ?";
            
            instrucao = conexao.prepareStatement(sql);
            instrucao.setString(1, cliente.getNomeCliente());
            instrucao.setString(2, cliente.getEndereco());
            instrucao.setString(3, cliente.getTelefone());
            instrucao.setInt(4, (int) cliente.getCidade().getId());
            instrucao.setInt(5, (int) cliente.getEstadoCivil().getId());
            instrucao.setString(6, String.valueOf(cliente.getSexo()));
            instrucao.setInt(7, (int) cliente.getId());
            instrucao.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }       
    }
     
    public void excluir(Cliente cliente) throws BDException{
        try {
            conexao = ControlaConexao.getConexao();
            Statement instrucao = conexao.createStatement();
            
            String sql = "DELETE FROM cliente WHERE CLIID = " + cliente.getId();
            instrucao.execute(sql);
           
                 
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new BDException(BDMensagensPadrao.INSTRUCAO_ERRO);
        }finally{
            ControlaConexao.fecharConexao(conexao);
        }
    }   
   
    
    private void preencherParametros(PreparedStatement instrucao, Cliente cliente) throws SQLException {
        
        //Esse método trata as informações podem estar nulas.

        if (cliente.getNomeCliente()!= null) {
            instrucao.setString(1, cliente.getNomeCliente());
        } else {
            instrucao.setNull(1, java.sql.Types.VARCHAR);
        }
        
        if (cliente.getEndereco()!= null) {
            instrucao.setString(2, cliente.getEndereco());
        } else {
            instrucao.setNull(2, java.sql.Types.VARCHAR);
        }
        
        if (cliente.getTelefone() != null) {
            instrucao.setString(3, cliente.getTelefone());
        } else {
            instrucao.setNull(3, java.sql.Types.VARCHAR);
        }
        
        if (cliente.getCidade().getId() != 0) {
            instrucao.setInt(4, (int) cliente.getCidade().getId());
        } else {
            instrucao.setNull(4, java.sql.Types.VARCHAR);
        }
        
        if (cliente.getEstadoCivil().getId() != 0) {
            instrucao.setInt(5, cliente.getEstadoCivil().getId());
        } else {
            instrucao.setNull(5, java.sql.Types.VARCHAR);
        }
        if (cliente.getSexo() != 0) {
            instrucao.setString(6, String.valueOf(cliente.getSexo()));
        } else {
            instrucao.setNull(6, java.sql.Types.VARCHAR);
        }
    }

}
