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

public class HuespedDAO {

    private Connection con;

    public HuespedDAO(Connection con) {
        this.con = con;
    }
    
    public void guardar(Huesped huesped) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO huespedes "
                        + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFecha_nacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getReserva_id());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                    	huesped.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el producto: %s", huesped));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void guardar(Huesped huesped, Reserva reserva) {
    	
        try {
        	con.setAutoCommit(false);
        	ReservaDAO reservaDAO = new ReservaDAO(con);
        	huesped.setReserva_id(reservaDAO.guardar(reserva));
        	PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO huespedes "
                        + "(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, huesped.getNombre());
                statement.setString(2, huesped.getApellido());
                statement.setDate(3, huesped.getFecha_nacimiento());
                statement.setString(4, huesped.getNacionalidad());
                statement.setString(5, huesped.getTelefono());
                statement.setInt(6, huesped.getReserva_id());
    
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                    	huesped.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertado el producto: %s", huesped));
                    }
                    if (huesped.getReserva_id() != null)
                    	con.commit();
                    else
                    	con.rollback();
                }
            }
        } catch (SQLException e) {
        	
            throw new RuntimeException(e);
        }
    }
    
    public List<Huesped> listar() {
        List<Huesped> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM huespedes");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Huesped(
                                resultSet.getInt("id"),
                                resultSet.getString("nombre"),
                                resultSet.getString("apellido"),
                                resultSet.getDate("fecha_nacimiento"),
                                resultSet.getString("nacionalidad"),
                                resultSet.getString("telefono"),
                                resultSet.getInt("reserva_id")
                                ));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE ID = ?");

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

    public int modificar(String nombre, String apellido, String fecha_nacimiento, String nacionalidad, String telefono, Integer reserva_id, Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE huespedes SET "
                    + " nombre = ?, "
                    + " apellido = ?,"
                    + " fecha_nacimiento = ?,"
                    + " nacionalidad = ?,"
                    + " telefono = ?,"
                    + " reserva_id = ?"
                    + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, apellido);
                statement.setDate(3, Date.valueOf(fecha_nacimiento));
                statement.setString(4, nacionalidad);
                statement.setString(5, telefono);
                statement.setInt(6, reserva_id);
                statement.setInt(7, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public List<Producto> listar(Categoria categoria) {
        List<Producto> resultado = new ArrayList<>();

        try {
            String sql = "SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD "
            + " FROM PRODUCTO WHERE CATEGORIA_ID = ?";
            System.out.println(sql);
            
            final PreparedStatement statement = con.prepareStatement(
                    sql);
    
            try (statement) {
                statement.setInt(1, categoria.getId());
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    */

}
