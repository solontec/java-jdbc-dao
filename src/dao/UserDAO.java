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
        String sql = "INSERT INTO usuarios(email, senha) VALUES(?,?)";

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
        String sql = "SELECT email, senha FROM usuarios";

        try(
                Connection conn = Conectar.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()
                ){

            while(resultado.next()){
                users.add(
                        new User(
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


}
