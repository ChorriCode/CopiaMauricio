package modelo;

public class Venta {
	
	private String fechaV;
	private String idV;
	private float importe;
	
	public Venta(String fechaV, String idV, float importe) {
		super();
		this.fechaV = fechaV;
		this.idV = idV;
		this.importe = importe;
	}
	
	
	public String getFechaV() {
		return fechaV;
	}

	public void setFechaV(String fechaV) {
		this.fechaV = fechaV;
	}

	public String getIdV() {
		return idV;
	}

	public void setIdV(String idV) {
		this.idV = idV;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}


	
	

}
