package util;

import dao.BDMensagensPadrao;
import excecao.BDException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlaConexao {
    
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/projetopig","root","root");
    }

    public static void fecharResultSet(ResultSet resultados) throws BDException{
        if(resultados!=null){
            try{
                resultados.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_RESULTSET_ERRO, ex);
            }
        }
    }
    
    
    public static void fecharInstrucao(PreparedStatement instrucao) throws BDException{
        if(instrucao!=null){
            try{
                instrucao.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_INSTRUCAO_ERRO, ex);
            }
        }
    }
    
    public static void fecharConexao(Connection conexao) throws BDException {
        if(conexao!=null){
            try{
                conexao.close();
            }catch(SQLException ex){
                throw new BDException(BDMensagensPadrao.FECHAR_CONEXAO_ERRO, ex);
            }
        }
    }
    
}
