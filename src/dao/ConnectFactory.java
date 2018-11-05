package dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectFactory {
    
//Metodo para concetar    
public Connection getConecao(){
   
    //Execução do banco de dados
    try{
        return DriverManager.getConnection("jbdc:mysql://localhost/mercado","root","123456");
    }catch(Exception erro){
        throw new RuntimeException("Erro 1:"+erro);
        }
    
    }

}