package controller;

import java.util.List;

import dao.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;
import modelo.Reserva;

public class HuespedController {

    private HuespedDAO huespedDao;
    
    public HuespedController() {
        var factory = new ConnectionFactory();
        this.huespedDao = new HuespedDAO(factory.recuperaConexion());
    }

    public int modificar(String nombre, String apellido, String fecha_nacimiento, String nacionalidad, String telefono, Integer reserva_id, Integer id) {
        return huespedDao.modificar(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, reserva_id, id);
    }

    public int eliminar(Integer id) {
        return huespedDao.eliminar(id);
    }

    public List<Huesped> listar() {
        return huespedDao.listar();
    }

    public void guardar(Huesped huesped, Integer reservaId) {
    	huesped.setReserva_id(reservaId);
        huespedDao.guardar(huesped);
    }

    public void guardar(Huesped huesped, Reserva reserva) {
        huespedDao.guardar(huesped, reserva);
    }

    /*public List<Huesped> listar(Categoria categoria) {
        return productoDao.listar(categoria);
    }*/

}
