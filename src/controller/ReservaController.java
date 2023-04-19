package controller;

import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import dao.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class ReservaController {

    private ReservaDAO reservaDao;
    
    public ReservaController() {
        var factory = new ConnectionFactory();
        this.reservaDao = new ReservaDAO(factory.recuperaConexion());
    }

    public int modificar(String fecha_entrada, String fecha_salida, double valor, String forma_pago, Integer id) {
        return reservaDao.modificar(fecha_entrada, fecha_salida, valor, forma_pago, id);
    }

    public int eliminar(Integer id) {
        return reservaDao.eliminar(id);
    }

    public List<Reserva> listar() {
        return reservaDao.listar();
    }

    public void guardar(Reserva reserva) {
    	reservaDao.guardar(reserva);
    }

    /*public List<Huesped> listar(Categoria categoria) {
        return productoDao.listar(categoria);
    }*/

}
