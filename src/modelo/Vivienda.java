package modelo;

import java.time.LocalDate;

public class Vivienda {

	private Integer id;
	private String nombre;
	private Float precio;
	private Character tipo;
	private Boolean nuevo;
	private LocalDate fabricacion;
	
	


	public Vivienda(Integer id, String nombre, Float precio, Character tipo, Boolean nuevo, LocalDate fabricacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
		this.nuevo = nuevo;
		this.fabricacion = fabricacion;
	}


	public int getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public float getPrecio() {
		return precio;
	}


	public char getTipo() {
		return tipo;
	}


	public boolean isNuevo() {
		return nuevo;
	}


	public LocalDate getFabricacion() {
		return fabricacion;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public void setTipo(char tipo) {
		this.tipo = tipo;
	}


	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}


	public void setFabricacion(LocalDate fabricacion) {
		this.fabricacion = fabricacion;
	}


	@Override
	public String toString() {
		return "Vivienda [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + ", nuevo="
				+ nuevo + ", fabricacion=" + fabricacion + "]";
	}



	
	
}
