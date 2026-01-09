package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void cadastrar(User user){

        if(emailExists(user.getEmail())){
            throw new RuntimeException("Email já existe");
        }
        String sql = "INSERT INTO usuarios( email, senha) VALUES(?,?)";

        try(
                Connection conn = Conectar.getConnection();
          ){


            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getSenha());


            stmt.execute();
            System.out.println("salvou cliente no banco");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> listar(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, senha FROM usuarios";

        try(
                Connection conn = Conectar.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()
                ){

            while(resultado.next()){
                users.add(
                        new User(
                                resultado.getInt("id"),
                                resultado.getString("email"),
                                resultado.getString("senha")
                        )
                );
            }
        }
        catch(SQLException e){
            System.out.println("erro" + e.getMessage());
        }

        return users;
    }


    public boolean emailExists(String email){
        String sql = "SELECT email FROM usuarios WHERE email = ?";
        try(
                Connection conn = Conectar.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
                stmt.setString(1, email);
                ResultSet result = stmt.executeQuery();
                return result.next();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    public void atualizar(User user){
        String sql = "UPDATE usuarios SET senha = ? WHERE id = ?";

        try(
                Connection conn = Conectar.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, user.getSenha());
            stmt.setInt(2, user.getId());

            int result = stmt.executeUpdate();
            System.out.println("Atualizou dados do cliente");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deletar(User user){
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try(
                Connection conn = Conectar.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setInt(1, user.getId());
            int result = stmt.executeUpdate();
            System.out.println("deletou o user" + user.getId());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


}
