package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados SQL Server.
 */
public class DBConnection {

    // Configurações do banco de dados
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // Driver oficial do SQL Server
    private static final String HOST_NAME = "localhost"; // Endereço do servidor
    private static final String DB_PORT = "1433"; // Porta padrão do SQL Server
    private static final String DB_NAME = "Marketplace"; // Nome do banco de dados
    private static final String DB_USER = "sa2"; // Nome de usuário
    private static final String DB_PASS = "senha123"; // Senha do usuário

    // Variável para armazenar a conexão
    private static Connection connection;

    /**
     * Obtém a conexão com o banco de dados.
     * 
     * @return Um objeto Connection válido.
     * @throws SQLException Se ocorrer um erro ao se conectar ao banco.
     * @throws ClassNotFoundException Se o driver JDBC não for encontrado.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            try {
                // Registra o driver JDBC
                Class.forName(DRIVER);

                // Monta a URL de conexão
                String connectionUrl = String.format(
                    "jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s;trustServerCertificate=true;encrypt=false",
                    HOST_NAME, DB_PORT, DB_NAME, DB_USER, DB_PASS
                );

                // Estabelece a conexão
                connection = DriverManager.getConnection(connectionUrl);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (ClassNotFoundException e) {
                System.err.println("Erro: Driver JDBC não encontrado!");
                throw e;
            } catch (SQLException e) {
                System.err.println("Erro ao se conectar ao banco de dados: " + e.getMessage());
                throw e;
            }
        }

        return connection;
    }

    /**
     * Fecha a conexão com o banco de dados.
     * 
     * @throws SQLException Se ocorrer um erro ao fechar a conexão.
     */
    public static void closeConnection() throws SQLException {
     
    }
}