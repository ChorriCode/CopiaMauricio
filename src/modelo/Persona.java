package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nif;
	private String nombre;
	private char sexo; // 'M' 'F'
	private LocalDate fecha;
	// private Integer altura;
	private int altura;
	private Persona padre;
	private Persona madre;

	// constructor sin parámetros
	public Persona() { 
		
		this.nif = "44882229Y";
		this.nombre="Anonimo";
		this.sexo = 'F';
		this.fecha = LocalDate.now();
		this.altura = 180;
		this.madre = null;
		this.padre = null;

	}

	public  Persona(String nif,String nombre, char sexo, LocalDate fecha, int altura, Persona padre, Persona madre) {

		this.nif = nif;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fecha = fecha;
		this.altura = altura;
		this.padre = padre;
		this.madre = madre;

	}
@Override	

	public String toString() {
	
		return "nif : " + this.nif  + ", nombre : " + this.nombre  + ", sexo : " + this.sexo 
				+ ",fecha : " + this.fecha  + ", altura : " + this.altura ;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static void soyEstatico() {

	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Persona getMadre() {
		return madre;
	}

	public void setMadre(Persona madre) {
		this.madre = madre;
	}

	

}
