package instalador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bleno Vale
 */
public class ExecutaBanco {

    private static final String drive = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(drive).newInstance();
            System.out.println(">> Drive rodou!!");
        } catch (Exception error) {
            System.out.println(">> Erro:" + error + "\n");
            error.printStackTrace();
        }

    }

    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public ExecutaBanco(String URL, String USER, String PASSAWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSAWORD;
    }

    public Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean checaConexao() {
        try {
            getConexao();
            System.out.println(">> ja existe conexão");
            return true;
        } catch (Exception e) {
            System.out.println(">> não existe conexão");
            return false;
        }
    }

    public void criaBancoDeDados() {
        String linha = new String();
        StringBuffer stringBuffer = new StringBuffer();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("Banco.sql"), "UTF-8"));

            while ((linha = bufferedReader.readLine()) != null) {
                stringBuffer.append(linha);
            }
            bufferedReader.close();

            String[] comando = stringBuffer.toString().split(";");

            Connection connection = getConexao();
            Statement statement = connection.createStatement();

            for (int i = 0; i < comando.length; i++) {
                if (!comando[i].trim().equals("")) {
                    statement.executeUpdate(comando[i]);
                    System.out.println(">>" + comando[i]);
                }
            }
        } catch (IOException | SQLException error) {
         
            System.out.println("error:" + error + "\n");
            error.printStackTrace();
        }
    }
}
