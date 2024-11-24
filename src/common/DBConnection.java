package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection c;

    public DBConnection() {
        super();
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost"; // IP ou hostname do servidor
        String dbName = "Marketplace"; // Nome do banco de dados
        String user = "sa"; // Nome do usuário do SQL Server
        String senha = "bancodadosfatec"; // Senha do usuário

        // Carregando o driver
        Class.forName("net.sourceforge.jtds.jdbc.Driver");

        // String de conexão corrigida
        String connectionUrl = String.format(
            "jdbc:jtds:sqlserver://%s:1433/%s;user=%s;password=%s",
            hostName, dbName, user, senha
        );

        // Estabelecendo a conexão
        c = DriverManager.getConnection(connectionUrl);

        return c;
    }
}
