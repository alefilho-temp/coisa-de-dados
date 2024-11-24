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


/*

package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// https://stackoverflow.com/questions/23658239/how-to-connect-java-to-microsoft-sql-server
// https://stackoverflow.com/questions/12297475/how-to-find-sql-server-running-port

public class DBConnection {
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // nome do driver
    private static final String HOST_NAME = "localhost"; // IP ou hostname do servidor
    private static final String DB_PORT = "1433"; // Porta do banco de dados
    private static final String DB_NAME = "Marketplace"; // Nome do banco de dados
    private static final String DB_USER = "sa2"; // Nome do usuário do SQL Server
    private static final String DB_PASS = "senha123"; // Senha do usuário

    private static Connection connection;

    static {
        
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (
            connection == null || 
            connection.isClosed() || 
            !connection.isValid(1000)
        ) {
            Class.forName(DRIVER);
            String connectionUrl = String.format(
                "jdbc:sqlserver://%s:%s;DatabaseName=%s;user=%s;password=%s;trustServerCertificate=false;encrypt=false",
                HOST_NAME, DB_PORT, DB_NAME, DB_USER, DB_PASS
            );
            connection = DriverManager.getConnection(connectionUrl);
        }

        return connection;
    }
}

*/
