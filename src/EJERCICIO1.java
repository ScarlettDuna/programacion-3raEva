import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EJERCICIO1 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Establecer la conexión con la BD
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/examen9abril", "root", "Anchan24");
            Statement sentencia = conexion.createStatement();

            System.out.println("¿Qué deseas hacer? Insertar artículos (1), insertar pedidos (2), visualizar pedidos (3) o mostrar artículos (4)");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();

            if (opcion == 1) {
                // Introducir articulo
                System.out.println("Introduce el artículo.");
                String sqlArt= sc.next();

                // INSERT INTO articulos VALUES (25,'LATA ALCACHOFAS', 27.5, '2028-12-10', 130);
                int filas = sentencia.executeUpdate(sqlArt);
                System.out.printf("Filas afectadas: %d %n", filas);

            } else if (opcion == 2) {
                // Introducir pedido
                System.out.println("Debe insertar el id del comprador, el id del articulo, y la cantidad.");
                int idcomprador = sc.nextInt();
                int idarticulo = sc.nextInt();
                int cantidad = sc.nextInt();

                // Comprobar que existe el idarticulo
                String sqlComp = String.format("Select * FROM articulos WHERE cod = %s", idarticulo);
                ResultSet resultDep = sentencia.executeQuery(sqlComp);
                if (!resultDep.next()) {
                    System.out.println("Error: El artículo no existe.");
                    resultDep.close();
                    return;
                }

                // Comprobación de que hay suficiente Stock
                // Select stock from articulos where cod = xxx;
                String sqlStock = String.format("Select stock from articulos where cod = %s", idarticulo);
                ResultSet resultStock = sentencia.executeQuery(sqlStock);
                int stock = 0;
                if (resultStock.next()) {
                    stock = resultStock.getInt(1);
                }

                // INSERT INTO pedidos VALUES (fecha, idcomprador, idarticulo, cantidad);
                String sqlPed = String.format("INSERT INTO pedidos VALUES (default, '%s', %d, %d, %d);", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), idcomprador, idarticulo, cantidad);
                System.out.println(sqlPed);
                int filas = sentencia.executeUpdate(sqlPed);
                System.out.printf("Filas afectadas: %d %n", filas);

                // Modificar stock(articulo)
                int newStock = stock - cantidad;

                // UPDATE articulos SET stock = xxx WHERE cos = xxx;
                String sqlUpdate = String.format("UPDATE articulos SET stock = %s WHERE cod = %s", newStock, idarticulo);
                int filas2 = sentencia.executeUpdate(sqlUpdate);
                System.out.printf("Filas afectadas: %d %n", filas2);

                sentencia.close();
                conexion.close();

            } else if (opcion == 3) {
                // Visualizar pedidos
                String sql = "SELECT * FROM pedidos";
                Boolean valor = sentencia.execute(sql);

                if (valor) {
                    ResultSet result = sentencia.getResultSet();
                    while (result.next()) {
                        System.out.printf("Código pedido: %d, fecha pedidos: %s, id comprador: %d, id artículo: %d %n",
                                result.getInt(1), result.getString(2), result.getInt(3), result.getInt(4));
                    }
                    result.close();
                } else {
                    int f = sentencia.getUpdateCount();
                    System.out.printf("Filas afectadas: %d %n", f);
                }
                sentencia.close();
                conexion.close();
            } else if (opcion == 4) {
                // Mostrar artículos
                String sql = "SELECT * FROM articulos";
                Boolean valor = sentencia.execute(sql);

                if (valor) {
                    ResultSet result = sentencia.getResultSet();
                    while (result.next()) {
                        System.out.printf("Código: %d, nombre: %s, precio: %f, fecha de caducidad: %s, stock: %d %n",
                                result.getInt(1), result.getString(2), result.getFloat(3), result.getString(4), result.getInt(5));
                    }
                    result.close();
                } else {
                    int f = sentencia.getUpdateCount();
                    System.out.printf("Filas afectadas: %d %n", f);
                }
                sentencia.close();
                conexion.close();
            } else {
                System.out.println("Opción no válida.");
            }

        } catch (NumberFormatException | ClassNotFoundException e) {
            System.out.println("Error con los datos");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de SQL.");
            e.printStackTrace();
        }

    }
}
