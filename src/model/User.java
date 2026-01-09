package model;
import dao.UserDAO;

import java.util.Scanner;


public class User {
    Scanner sc = new Scanner(System.in);
    UserDAO dao = new UserDAO();

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String email;
    private String senha;

    public User(int id, String email, String senha) {
        this.id = id;
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




}
