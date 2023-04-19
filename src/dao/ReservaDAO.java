package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Huesped;
import modelo.Reserva;

public class ReservaDAO {

    private Connection con;

    public ReservaDAO(Connection con) {
        this.con = con;
    }

    public List<Reserva> listar() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            String sql = "SELECT * FROM reservas";
            
            System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("fecha_entrada"),
                                resultSet.getDate("fecha_salida"),
                                resultSet.getDouble("valor"),
                                resultSet.getString("forma_pago")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
    
    public int guardar(Reserva reserva) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO reservas "
                        + "(fecha_entrada, fecha_salida, valor, forma_pago)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setDate(1, reserva.getFecha_entrada());
                statement.setDate(2, reserva.getFecha_salida());
                statement.setDouble(3, reserva.getValor());
                statement.setString(4, reserva.getForma_pago());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                    	reserva.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el producto: %s", reserva));
                    }
                    return reserva.getId();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String fecha_entrada, String fecha_salida, double valor, String forma_pago, Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE reservas SET "
                    + " fecha_entrada = ?, "
                    + " fecha_salida = ?,"
                    + " valor = ?,"
                    + " forma_pago = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setDate(1, Date.valueOf(fecha_entrada));
            	//statement.setDate(1, fecha_entrada);
                statement.setDate(2, Date.valueOf(fecha_salida));
            	//statement.setDate(2, fecha_salida);
                statement.setDouble(3, valor);
                statement.setString(4, forma_pago);
                statement.setInt(5, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*public List<Reserva> listarConProductos() {
        List<Reserva> resultado = new ArrayList<>();

        try {
            String sql = "SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD "
                    + " FROM CATEGORIA C INNER JOIN PRODUCTO P "
                    + " ON C.ID = P.CATEGORIA_ID";
            
            System.out.println(sql);
            
            final PreparedStatement statement = con
                    .prepareStatement(sql);

            try (statement) {
                final ResultSet resultSet = statement.executeQuery();

                try (resultSet) {
                    while (resultSet.next()) {
                        int categoriaId = resultSet.getInt("C.ID");
                        String categoriaNombre = resultSet.getString("C.NOMBRE");
                        
                        Categoria categoria = resultado
                            .stream()
                            .filter(cat -> cat.getId().equals(categoriaId))
                            .findAny().orElseGet(() -> {
                                Categoria cat = new Categoria(
                                        categoriaId, categoriaNombre);
                                resultado.add(cat);
                                
                                return cat;
                            });
                        
                        Producto producto = new Producto(
                                resultSet.getInt("P.ID"),
                                resultSet.getString("P.NOMBRE"),
                                resultSet.getInt("P.CANTIDAD"));
                        
                        categoria.agregar(producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }*/

}
