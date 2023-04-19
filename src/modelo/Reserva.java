package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Reserva {

    private Integer id;
    private Date fecha_entrada;
    private Date fecha_salida;
    private double valor;
    private String forma_pago;

    //private List<Producto> productos = new ArrayList<>();
    
    public Reserva()
    {
    	
    }
    
    public Reserva(Integer id, Date fecha_entrada, Date fecha_salida, double valor, String forma_pago) {
		this.id = id;
    	this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
    
    

	/*@Override
    public String toString() {
        return this.nombre;
    }*/

	/*public void agregar(Producto producto) {
        this.productos.add(producto);
    }

    public List<Producto> getProductos() {
        return this.productos;
    }*/

}
