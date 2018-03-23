package modelo;
import java.io.Serializable;
import java.time.LocalDate;

public class VehiculoOtro implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String matricula;
	private int marcaModelo;
	private LocalDate fechaMatricula;
   
   
public VehiculoOtro(int id, String matricula, int marcaModelo, LocalDate fechaMatricula) {
	super();
	this.id = id;
	this.matricula = matricula;
	this.marcaModelo = marcaModelo;
	this.fechaMatricula = fechaMatricula;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getMatricula() {
	return matricula;
}


public void setMatricula(String matricula) {
	this.matricula = matricula;
}


public int getMarcaModelo() {
	return marcaModelo;
}


public void setMarcaModelo(int marcaModelo) {
	this.marcaModelo = marcaModelo;
}


public LocalDate getFechaMatricula() {
	return fechaMatricula;
}


public void setFechaMatricula(LocalDate fechaMatricula) {
	this.fechaMatricula = fechaMatricula;
}


@Override
public String toString() {
	return "VehiculoOtro [id=" + id + ", matricula=" + matricula + ", marcaModelo=" + marcaModelo + ", fechaMatricula="
			+ fechaMatricula + "]";
}
   
   
 

}
