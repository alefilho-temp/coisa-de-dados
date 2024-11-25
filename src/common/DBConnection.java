/*
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
*/


package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados SQL Server.
 * 
 * Esta classe fornece um método estático para obter uma conexão com o banco de dados
 * utilizando o driver JDBC do SQL Server.
 * 
 * @author Seu Nome
 */
public class DBConnection {
    
    // Driver JDBC para SQL Server
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    // IP ou hostname do servidor
    private static final String HOST_NAME = "localhost"; 
    // Porta do banco de dados
    private static final String DB_PORT = "1433"; 
    // Nome do banco de dados
    private static final String DB_NAME = "Marketplace"; 
    // Nome do usuário do SQL Server
    private static final String DB_USER = "sa2"; 
    // Senha do usuário
    private static final String DB_PASS = "senha123"; 

    // Conexão com o banco de dados
    private static Connection connection;

    /**
     * Obtém uma conexão com o banco de dados.
     * 
     * Este método verifica se a conexão atual é válida. Se não for, ele cria uma nova
     * conexão utilizando as informações de configuração definidas na classe.
     * 
     * @return A conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao estabelecer a conexão.
     * @throws ClassNotFoundException Se o driver JDBC não for encontrado.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // Verifica se a conexão é nula, está fechada ou não é válida
        if (connection == null || connection.isClosed() || !connection.isValid(1000)) {
            // Registra o driver JDBC
            Class.forName(DRIVER);
            
            // Monta a string de conexão
            String connectionUrl = String.format(
                "jdbc:sqlserver://%s:%s;DatabaseName=%s;user=%s;password=%s;trustServerCertificate=false;encrypt=false",
                HOST_NAME, DB_PORT, DB_NAME, DB_USER, DB_PASS
            );
            
            // Estabelece a conexão
            connection = DriverManager.getConnection(connectionUrl);
        }

        return connection;
    }
}
