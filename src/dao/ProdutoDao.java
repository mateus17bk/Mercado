package dao;

//Importes 
import java.sql.Connection;
import modelo.Produtos;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDao {

//Variaveis 
private Connection com;
private PreparedStatement stmt;
private Statement st;
private ResultSet rs;
private ArrayList <Produtos> lista = new ArrayList<Produtos> ();

    //Contrutor
    public ProdutoDao(){
        com = new ConnectFactory().getConecao();
        
    }
    //Metodo para inserir os produtos para o banco de dados
    public void inserir(Produtos produtos){
        String sql = "INSERT INTO produtos {descri_produto, preco_produto} VALUES{?,?}";
        
        try{
            stmt = com.prepareCall(sql);
            stmt.setString(1, produtos.getDescri_produto());
            stmt.setDouble(2, produtos.getPreco_produto());
            stmt.execute();
            stmt.close();
        }catch(Exception erro){
            throw new RuntimeException("Erro 2:" +erro);
        }
    }
    
    //Metodo para alterar os produtos para o banco de dados
    public void alterar(Produtos produtos){
        String sql="UPDATE produtos SET descri_produto = ? , preco_produtos = ? WHERE cod_produto = ?";
        
        try{
            stmt = com.prepareStatement(sql);
            stmt.setString(1, produtos.getDescri_produto());
            stmt.setDouble(2, produtos.getPreco_produto());
            stmt.setInt(3, produtos.getCod_produto());
            stmt.execute();
            stmt.close();;
        }catch(Exception erro){
            throw new RuntimeException("Erro 3:"+erro);
            
        }
    }
    
    //Metodo para excluir os produtos para o banco de dados
    public void excluir(int valor){
        String sql= "DELETE FROM produtos WHERE cod_produto = "+ valor;
        
        try{
            st = com.createStatement();
            st.execute(sql);
            st.close();
        }catch(Exception erro){
            throw new RuntimeException("Erro 4:"+erro);
            
        }
    }
    
    //Metodo array para listar os produtos
    public ArrayList<Produtos> ListarTodos(){
    
          String sql="SELEC * FROM produtosst";  
    
          try{
              st = com.createStatement();
              rs = st.executeQuery(sql);
              
              while(rs.next()){
                  Produtos produtos = new Produtos();
                  produtos.setCod_produto(rs.getInt("cod_produto"));
                  produtos.setDescri_produto(rs.getString("descri_produto"));
                  produtos.setPreco_produto(rs.getDouble("preco_produto"));
                  lista.add(produtos);
                  
              }
          }catch(Exception erro){
              throw new RuntimeException("Erro 5: "+ erro);
          }
    
          return lista;
    }
   
    //Metodo array para listar todos os tipos de produtos
    public ArrayList<Produtos> ListarTodosDescricao(String valor){
    
          String sql="SELEC * FROM produtosst WHERE descri_produto LIKE '%'+valor+'%' ";  
    
          try{
              st = com.createStatement();
              rs = st.executeQuery(sql);
              
              while(rs.next()){
                  Produtos produtos = new Produtos();
                  produtos.setCod_produto(rs.getInt("cod_produto"));
                  produtos.setDescri_produto(rs.getString("descri_produto"));
                  produtos.setPreco_produto(rs.getDouble("preco_produto"));
                  lista.add(produtos);
                  
              }
          }catch(Exception erro){
              throw new RuntimeException("Erro 5: "+ erro);
          }
    return lista;
     
    }
}