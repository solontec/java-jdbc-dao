package app;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        UserDAO dao = new UserDAO();

        int opcao;

        do{
            System.out.println("MENU");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 Listar clientes");
            System.out.println("0 Sair ");
            System.out.print("opção");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Email");
                    String email = scanner.nextLine();

                    System.out.println("Senha");
                    String senha = scanner.nextLine();

                    User user = new User(email, senha);
                    dao.cadastrar(user);
                    System.out.println("Cadastrou");
                    break;

                case 2:
                    System.out.println("Clientes cadastrados");

                    List<User> users = dao.listar();

                    if(users.isEmpty()){
                        System.out.println("Não existe users por aqui");
                    } else{

                        for (int i = 0; i < users.size() ; i++) {
                            User u = users.get(i);
                            System.out.println("Email:" + u.getEmail() + "Senha" + u.getSenha());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saindo");
                    break;

                default:
                    System.out.println("opção invalida");

            }
        }while (opcao != 0);

        scanner.close();
    }
}