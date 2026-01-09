package model;
import dao.UserDAO;

import java.util.Scanner;


public class User {
    Scanner sc = new Scanner(System.in);
    UserDAO dao = new UserDAO();

    private String email;
    private String senha;

    public User(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void cadastrarCliente(){
        System.out.println("Email: ");
        String email = sc.nextLine();

        System.out.println("Senha: ");
        String senha = sc.nextLine();

        User user = new User(email, senha);

        dao.cadastrar(user);
        System.out.println("Cadastrou");

    }

}
