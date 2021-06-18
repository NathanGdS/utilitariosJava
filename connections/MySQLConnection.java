package connections;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

public class MySQLConnection {

    public MySQLConnection() {}

    public static java.sql.Connection getConexaoMySQL() {
        Connection connection = null;          //atributo do tipo Connection
        try {

            // Configurando a nossa conexão com um banco de dados//
            String serverName = "localhost";    //caminho do servidor do BD

            String mydatabase ="faculdade";        //nome do seu banco de dados

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

            String username = "developer";        //nome de um usuário de seu BD

            String password = "1234";      //sua senha de acesso

            connection = DriverManager.getConnection(url, username, password);

            return connection;

            }  catch (SQLException e) {

                System.out.println("Nao foi possivel conectar ao Banco de Dados. " + e.getMessage());

                return null;
            }
    }

    public static boolean CloseConnection() {
        try {
            MySQLConnection.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static java.sql.Connection RebootConection() {
        CloseConnection();
        return MySQLConnection.getConexaoMySQL();
    }
}