import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcUrl = "jdbc:mysql://localhost:33306/inventario";
            Connection com = DriverManager.getConnection(jdbcUrl,"root", "zx76wbz7FG89k");

            if (com != null) {
                System.out.println("Conectado correctamente");

                usuario(com);
                estancia(com);
                
            } else {
                System.out.println("Error de conexión");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void usuario(Connection com) throws SQLException{
        System.out.println("------------SELECT-----------");
                String sql = "SELECT * FROM usuario";

                PreparedStatement ps = com.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                System.out.println("\tid \t username \t password \t password \t email");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String tipo = rs.getString("tipo");
                    String email = rs.getString("email");

                    System.out.println("\t" + id + "\t" + username + "\t" + password + "\t" + password + "\t" + tipo + "\t" + email);
                }

                System.out.println("-------------INSERT----------");
                sql = "INSERT INTO usuario (?,?,?,?,?)";//Así mejor -> INSERT INTO usuario (id, username, password, tipo, email) VALUES (?,?,?,?,?)
                ps = com.prepareStatement(sql);
                ps.setInt(1, -1);
                ps.setString(2, "Paco");
                ps.setString(3, "contraseñaPaco");
                ps.setString(4, "ADMIN");
                ps.setString(5, "pacopepe@gmail.com");

                if (ps.executeUpdate() > 0){
                    System.out.println("INSERT REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL INSERTAR");
                }

                System.out.println("--------------DELETE------------");
                sql = "DELETE FROM usuario WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Paco");

                if (ps.executeUpdate() > 0){
                    System.out.println("DELETE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL BORRAR");
                }

                System.out.println("--------------UPDATE------------");
                sql = "UPDATE FROM usuario SET password = ? WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "nuevacontra122");
                ps.setString(2, "pablo12");

                if (ps.executeUpdate() > 0){
                    System.out.println("UPDATE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL ACTUALIZAR");
                }

                com.close();
    }

    public static void estancia(Connection com) throws SQLException{
        System.out.println("------------SELECT-----------");
                String sql = "SELECT * FROM estancia";

                PreparedStatement ps = com.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                System.out.println("\tid \t nombre \t desripcion");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");

                    System.out.println("\t" + id + "\t" + nombre + "\t" + descripcion);
                }

                System.out.println("-------------INSERT----------");
                sql = "INSERT INTO estancia (id, nombre, descripcion) VALUES (?,?,?)";
                ps = com.prepareStatement(sql);
                ps.setInt(1, 1);
                ps.setString(2, "Hotel");
                ps.setString(3, "Descripción simple");

                if (ps.executeUpdate() > 0){
                    System.out.println("INSERT REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL INSERTAR");
                }

                System.out.println("--------------DELETE------------");
                sql = "DELETE FROM usuario WHERE username = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Hotel");

                if (ps.executeUpdate() > 0){
                    System.out.println("DELETE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL BORRAR");
                }

                System.out.println("--------------UPDATE------------");
                sql = "UPDATE FROM usuario SET descripcion = ? WHERE nombre = ?";
                ps = com.prepareStatement(sql);
                ps.setString(1, "Cambio de descripcion");
                ps.setString(2, "Hotel");

                if (ps.executeUpdate() > 0){
                    System.out.println("UPDATE REALIZADO CORRECTAMENTE");
                } else {
                    System.out.println("ERROR AL ACTUALIZAR");
                }

                com.close();
    }
}
