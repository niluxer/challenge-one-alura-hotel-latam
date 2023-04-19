package modelo;

import java.sql.Date;

public class Huesped {

    private Integer id;

    private String nombre;

    private String apellido;

    private Date fecha_nacimiento;
    
    private String nacionalidad;
    
    private String telefono;
    
    private Integer reserva_id;
    
    private Reserva reserva;

	public Huesped(Integer id, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono,
			Integer reserva_id) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reserva_id = reserva_id;
	}

	public Huesped() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getReserva_id() {
		return reserva_id;
	}

	public void setReserva_id(Integer reserva_id) {
		this.reserva_id = reserva_id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

    


    /*@Override
    public String toString() {
        return String.format(
                "{ id: %d, nombre: %s, descripcion: %s, cantidad: %d }",
                this.id, this.nombre, this.descripcion, this.cantidad);
    }*/

}
