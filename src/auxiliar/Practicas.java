package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Vehiculo;
import modelo.VehiculoOtro;



public class Practicas {
	
	private int getRowCount(ResultSet resultSet) {
	    if (resultSet == null) {
	        return 0;
	    }
	    try {
	        resultSet.last();
	        return resultSet.getRow();
	    } catch (SQLException exp) {
	        exp.printStackTrace();
	    } finally {
	        try {
	            resultSet.beforeFirst();
	        } catch (SQLException exp) {
	            exp.printStackTrace();
	        }
	    }
	    return 0;
	}
	

/**/public HashMap<String, ArrayList<Float>> resumenVentasParcialesVendedor(String ficheroVentas) {
		HashMap<String, ArrayList<Float>> resultado = new HashMap<String, ArrayList<Float>>();

		try {

			// Abrir el fichero
			FileReader fr = new FileReader(ficheroVentas);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			int acumulado = 0;
			int contador = 0;
			while ((linea = br.readLine()) != null) {
				String[] campos = linea.split("%");
				if (resultado.get(campos[1]) == null)
					resultado.put(campos[1], new ArrayList<Float>());
				resultado.get(campos[1]).add(Float.parseFloat((campos[2])));
			}

			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return resultado;
	}
	
	
	
	public void copiarObjetosEstudianteEnFicheroTxt(String rutaObj, String rutaTxt) {
		ArrayList<Estudiante> estudiantes = leerObjetoEnficheroConDevolucion(rutaObj);
		for (Estudiante estudiante : estudiantes) {
			String cadena = estudiante.getCodGrupo() + "#" +
							estudiante.getNif() + "#" +
							estudiante.getNombre() + "#" + 
							estudiante.getSexo() + "#" +
							estudiante.getFecha() + "#" + 
							estudiante.getAltura();
			escribirStringEnfichero(cadena, rutaTxt);
	
		}	
	}
	public void escribirStringEnfichero(String cadena, String rutaFichero) {
		try {
			FileWriter fw = new FileWriter(rutaFichero,true);
			BufferedWriter br = new BufferedWriter(fw);		
			br.write(cadena+"\n");		
			br.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Estudiante crearEstudiante(String[] estudianteTxt) {
		
		int codGrupo =  Integer.parseInt(estudianteTxt[0]);
		String nif = estudianteTxt[1];
		String nombre = estudianteTxt[2];
		char sexo = estudianteTxt[3].charAt(0);
		String nacimiento =  estudianteTxt[4];			
		//LocalDate fechaNacimiento = LocalDate.parse(nacimiento, DateTimeFormatter.ofPattern("yyyyMMdd"));
		int year = Integer.parseInt(nacimiento.substring(0, 4));
		int month = Integer.parseInt(nacimiento.substring(4, 6));
		int dayOfMonth = Integer.parseInt(nacimiento.substring(6, 8));
		LocalDate fechaNacimiento = LocalDate.of(year, month, dayOfMonth);
		int altura = Integer.parseInt(estudianteTxt[5]);
		Estudiante unEstudiante = new Estudiante(codGrupo, nif, nombre, sexo, fechaNacimiento, altura, null, null);
		return unEstudiante;
	}
	
	public void escribirObjetoEnFichero(String rutaFicheroTxt, String rutaFicheroObject) {
		Estudiante unEstudiante;
		try {
			FileReader fr = new FileReader(rutaFicheroTxt);
			BufferedReader br = new BufferedReader(fr);
			FileOutputStream fo = new FileOutputStream(rutaFicheroObject);
			ObjectOutputStream  oo = new ObjectOutputStream(fo);
			String strLine;
			String[] campos;
			//int codGrupo, String nif, String nombre, char sexo, LocalDate fecha, int altura, Persona padre, Persona madre
			while ((strLine = br.readLine()) != null) {
				campos = strLine.split("#");
				unEstudiante = crearEstudiante(campos);
				oo.writeObject(unEstudiante);
				
			}
			//Guardar los objetos
			br.close();
			fr.close();
			oo.close();
			fo.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
		}

		System.out.println("Fin del método");

	}
/**/public ArrayList<Estudiante> leerObjetoEnficheroConDevolucion(String rutaFichero) {
	ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		try {
			FileInputStream fis = new FileInputStream(rutaFichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				
				Estudiante obj = (Estudiante) ois.readObject();
				estudiantes.add(obj);
				System.out.println("---->" + obj.toString());
			}
			fis.close();
			ois.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		}
		return estudiantes;
	}
	
	public void leerObjetoEnfichero(String rutaFichero) {
		
		try {
			FileInputStream fis = new FileInputStream(rutaFichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				
				Estudiante obj = (Estudiante) ois.readObject();
				System.out.println(obj.toString());
			}
			fis.close();
			ois.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		}
	}

	
/**/public HashMap<String,ArrayList<Float>> resumenVentasParcialesVendedor1(String rutaFichero){
		HashMap<String,ArrayList<Float>> resultado = new HashMap<String,ArrayList<Float>>();
	
		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while ((linea = br.readLine()) != null) {
				String fechaV = linea.split("%")[0];
				String idV = linea.split("%")[1];
				float importeV = Float.parseFloat(linea.split("%")[2]);
				if (!resultado.containsKey(idV)) { //(resultado.get(idV)== null)
					resultado.put(idV, new ArrayList<Float>());
					//ArrayList<Float> lista = new ArrayList<Float>();
					//lista.add(importeV);
					//resultado.put(idV, lista );					
				}// else {
					//ArrayList<Float> lista = resultado.get(idV);
					//lista.add(importeV);
					//resultado.put(idV, lista );
					//resultado.get(idV).add(importeV);
				resultado.get(linea.split("%")[1]).add(Float.parseFloat(linea.split("%")[2]));
				//}
				
			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

/**/public HashMap<String, Float> resumenVentasTotalesPorVendedor1(HashMap<String, ArrayList<Float>> listaVendedoresVentas){
		HashMap<String, Float> resultado = new HashMap<String, Float>();
		for (String clave : listaVendedoresVentas.keySet()) {
			for (Float valor : listaVendedoresVentas.get(clave)) {
				if (resultado.get(clave) == null) {
					resultado.put(clave, valor);
				} else {
					resultado.put(clave, resultado.get(clave) + valor);
				}			
			}		
		}
	
		return resultado;
	}
	
/**/public HashMap<String, Float> resumenVentasTotalesPorVendedor2(HashMap<String, ArrayList<Float>> ventas) {
		HashMap<String, Float> resultado = new HashMap<String, Float>();
		// recorrer hm de entrada creando el de salida
		Set<String> claves = ventas.keySet();
		for (String clave : claves) {
			ArrayList<Float> listaVentas = ventas.get(clave);
			float acumuladoVendedor = 0;
			for (Float importe : listaVentas)
				acumuladoVendedor += importe;
			resultado.put(clave, acumuladoVendedor);
		}
		return resultado;
	}
	

	
	//leer Fichero ArrayList de string
/**/public HashMap<String,String> leerFicheroHashMap(String rutaFichero){
		HashMap<String,String> resultado = new HashMap<String,String>();
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader(rutaFichero);
			// Get the object of DataInputStream

			BufferedReader br = new BufferedReader(fr);
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the consoleï¿½
								
				resultado.put(strLine.split("&&")[0], strLine);

				System.out.println(strLine);
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		return resultado;
		
	}
	
/**/public ArrayList<String> leerFicheroArrayList(String rutaFichero){
		ArrayList<String> resultado = new ArrayList<String>();
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader(rutaFichero);
			// Get the object of DataInputStream

			BufferedReader br = new BufferedReader(fr);
			String strLine;
			// Read File , Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the consoleï¿½
							
				resultado.add(strLine);

				System.out.println(strLine);
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		return resultado;
		
	}
	/*
	public String[] leerFicheroComunidadesDevolverArray(){
		String[] resultado = new String[];
		try {
			// Open the file that is the first
			// command line parameter
			FileReader fr = new FileReader(rutaFichero);
			// Get the object of DataInputStream

			BufferedReader br = new BufferedReader(fr);
			String strLine;
			// Read File , Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the consoleï¿½
							
				resultado.add(strLine);

				System.out.println(strLine);
			}
			// Close the input stream
			fr.close();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
		
		return resultado;
		
	}
	*/
	public void leerFicheroTexto() {
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/personas.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// System.out.println(LocalDate.now());
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {

				String[] campos = linea.split("&&");
				System.out.println(linea);
				System.out.println(calculaEdad(campos[2]));

			}
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	

/**/public HashMap<Integer, ArrayList<Float>> leerFicheroTextoVisitantes() {

		HashMap<Integer, ArrayList<Float>> resultado = new HashMap<Integer, ArrayList<Float>>();
		ArrayList<Float> visitantesPorMes = null;
		for (int i = 0; i < 7; i++) {
			visitantesPorMes = new ArrayList<Float>();
			for (int j = 0; j < 12; j++) {
				visitantesPorMes.add(0.0f);
			}
			resultado.put(i, visitantesPorMes);
		}
			FileReader fr;
			BufferedReader br;
			try {
				fr = new FileReader("ficheros/DatosIslaMesValor.txt");
				br = new BufferedReader(fr);
				String strLine;

				while ((strLine = br.readLine()) != null) {
					System.out.println(strLine);
					String[] campos = strLine.split("@");
					Integer isla = Integer.parseInt(campos[0])-1;
					Integer mes = Integer.parseInt(campos[1])-1;
					Float milesVisitantes = Float.parseFloat(campos[2]);
					resultado.get(isla).set(mes, milesVisitantes);			
				}

				fr.close();
				br.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		System.out.println(resultado);
		return resultado;
	}
	
/**/public void mostrarVisitantesTotalesPorIsla(HashMap<Integer, ArrayList<Float>> datosVisitantes){		
		String[] islas = { "GC", "LTE", "FTV", "TFE", "LPA", "GOM", "HIE" };
		String[] meses = { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" };
		for (int i = 0; i < 7; i++) {
			float acumulador = 0.0f;
			System.out.println("ISLA de " + islas[i]);
			System.out.println("----------------");		
			for (int j = 0; j < 12; j++) {
				System.out.println(meses[j] +": " + datosVisitantes.get(i).get(j));
				acumulador += datosVisitantes.get(i).get(j);
			}
			System.out.print("TOTAL VISITANTES EN " + islas[i]); System.out.printf(": %.2f", acumulador);
			System.out.println();
			System.out.println("----------------");
		}			
	}
		
/**/public void mostrarVisitantesTotalesPorMes(HashMap<Integer, ArrayList<Float>> datosVisitantes){		
		String[] islas = { "GC", "LTE", "FTV", "TFE", "LPA", "GOM", "HIE" };
		String[] meses = { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" };
		for (int i = 0; i < 12; i++) {
			float acumulador = 0.0f;
			System.out.println("Mes de " + meses[i]);
			System.out.println("----------------");		
			for (int j = 0; j < 7; j++) {
				System.out.println(islas[j] +": " + datosVisitantes.get(j).get(i));
				acumulador += datosVisitantes.get(j).get(i);
			}
			System.out.print("TOTAL VISITANTES EN " + meses[i]); System.out.printf(": %.2f", acumulador);
			System.out.println();
			System.out.println("----------------");
		}			
	}
/**/public void mostrarVisitantesIslaMes(HashMap<Integer, ArrayList<Float>> listaVisitantes) {
		String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPT",
				"OCTUBRE", "NOV", "DIC" };
		String[] islas = { "GRAN CANARIA", "LANZAROTE", "FUERTEVENTURA", "TENERIFE", "LA PALMA", "LA GOMERA",
				"EL HIERRO" };
		int isla = 0;
		float acumulador2 = 0;
		System.out.print("\t");
		for (String mes : meses) {
			System.out.print("\t" + mes);
		}
		System.out.print("\t" + "TOTAL");
		System.out.println();
		for (Entry<Integer, ArrayList<Float>> visitasIslaYear : listaVisitantes.entrySet()) {
			System.out.print(islas[isla++]);
			float acumulador = 0.0f;
			for (Integer mes = 0; mes < visitasIslaYear.getValue().size(); mes++) {	
				System.out.print("\t" + visitasIslaYear.getValue().get(mes).floatValue() + "");
				acumulador += listaVisitantes.get(isla-1).get(mes);
			}
			acumulador2 += acumulador;
			System.out.printf("\t%.2f", acumulador);
			System.out.println();
		}
		System.out.println("..............................................................Total de Visitantes en todo el Archipielago:\t" + acumulador2);
	}
	
/**/public void mostrarVisitantesIslaMes2(HashMap<Integer, ArrayList<Float>> listaVisitantes) {
		String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPT",
				"OCTUBRE", "NOV", "DIC" };
		String[] islas = { "GRAN CANARIA", "LANZAROTE", "FUERTEVENTURA", "TENERIFE", "LA PALMA", "LA GOMERA",
				"EL HIERRO" };
		float acumulador2 = 0;
		float[] acumuladorVisitantesMes = new float[12];
		System.out.print("\t");
		for (String mes : meses) {
			System.out.print("\t" + mes);
		}
		System.out.print("\t" + "TOTAL");
		System.out.println();
		for (int i = 0; i < islas.length; i++) {
			System.out.print(islas[i]);
			float acumulador = 0.0f;
			for (int j = 0; j < meses.length; j++) {
				System.out.printf("\t%.2f" , listaVisitantes.get(i).get(j));
				acumulador += listaVisitantes.get(i).get(j);
				acumuladorVisitantesMes[j] += listaVisitantes.get(i).get(j);
			}
			acumulador2 += acumulador;
			System.out.printf("\t%.2f", acumulador);
			System.out.println("");

		}
		System.out.print("TOTAL: \t");

		for (int j = 0; j < meses.length; j++) {
			System.out.printf("\t%.2f" , acumuladorVisitantesMes[j]);
		}
		System.out.print("\t" + acumulador2);
		
	}
	
	public  void leerFicheroTextoOrdenado(String rutafichero) {
		
		int contador_grupo = 0;
		int contador_total = 0;
		try {
			FileReader fr = new FileReader(rutafichero);
			BufferedReader br = new BufferedReader(fr);
			String strLine;
			String codigoLeido, codigoAnterior = null;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {	
				String[] campos = strLine.split("&&");
				codigoLeido = campos[0];
				if (!codigoLeido.equals(codigoAnterior)) {	
					System.out.println("Hay " + contador_grupo + " alumnos en el grupo " + codigoAnterior);
					contador_total += contador_grupo;
					contador_grupo = 0;
					codigoAnterior = codigoLeido;				
				}		
			contador_grupo++;				
			}
			System.out.println("Hay " + contador_grupo + " alumnos en el grupo " + codigoAnterior);
			contador_total += contador_grupo;
			System.out.println("Hay " + contador_total + " alumnos en total");
			
			fr.close();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void leerFicheroTexto1() {
		int contador = 0;
		try {

			FileReader fr = new FileReader("ficheros/personas.txt");

			BufferedReader br = new BufferedReader(fr);
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the consoleï¿½
				String[] campos = strLine.split("&&");
				//System.out.println(calculaEdad(campos[2]));
				//acumulado += calculaEdad2(campos[2]);
				contador++;
				System.out.println(strLine);
			}
			//System.out.println("La media de edad es: " + acumulado/contador);
			// Close the input stream
			fr.close();
			br.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static void leerFicheroTexto2() {
		try {
			// Open the file that is the first
			// command line parameter
			File file = new File("ficheros/personas.txt");
			FileInputStream fis = new FileInputStream(file);
			// Get the object of DataInputStream
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			String str = new String(data, "UTF-8");

			System.out.println(str);

		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}

	public static int calculaEdad(String nacimiento) {

		int year = Integer.parseInt(nacimiento.substring(4, 8));
		int month = Integer.parseInt(nacimiento.substring(2, 4));
		int dayOfMonth = Integer.parseInt(nacimiento.substring(0, 2));
		System.out.println(year + "-" + month + "-" + dayOfMonth);
		LocalDate birth = LocalDate.of(year, month, dayOfMonth);

		LocalDate actual = null;

		// Calcula los aÃ±os que tiene, primero resta el aÃ±o actual al de nacimiento,
		// despuÃ©s compara si el mes actual es inferior o no, si es inferior resta 1,
		// si es superior mira los dias.
		int resultado = actual.now().getYear() - birth.getYear()
				- (actual.now().getMonthValue() < birth.getMonthValue() ? 1
						: (actual.now().getDayOfMonth() < birth.getDayOfMonth() ? 1 : 0));

		System.out.println(resultado);
		return resultado;
	}

	public static int calculaEdad2(String nacimiento) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("ddMMyyyy");
		LocalDate fechaNac = LocalDate.parse(nacimiento, fmt);
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fechaNac, ahora);
		System.out.printf("Tu edad es: %s aï¿½os, %s meses y %s dï¿½as", periodo.getYears(), periodo.getMonths(),
				periodo.getDays());
		return periodo.getYears();
	}

	// SEGUNDA EVALUACION

	public ArrayList<Estudiante> introListas() {
		ArrayList<Estudiante> listaE;
		listaE = new ArrayList<Estudiante>();
		Estudiante est1 = new Estudiante(123);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		listaE.add(est1);
		int tam = listaE.size();
		Estudiante est2 = new Estudiante(321);
		listaE.add(0, est2);
		listaE.remove(listaE.size() - 1);
		// listaE.set(0, est1);
		for (Estudiante estudiante : listaE) {
			// System.out.println(estudiante.getCodGrupo());
		}
		for (int i = 0; i < listaE.size(); i++) {
			// System.out.println(listaE.get(i).getCodGrupo());
		}

		// System.out.println("fin introListas");
		return listaE;

	}

	// 11 enero 2018
	// Leer una matriz de int y devolverla como ArrayList

	public ArrayList<ArrayList<Integer>> convierteMatrizArrayLista(int[][] matriz) {

		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		for (int[] filaMatriz : matriz) {
			// crear alist
			ArrayList<Integer> filaLista = new ArrayList<Integer>();
			for (int numero : filaMatriz)
				filaLista.add(numero);
			resultado.add(filaLista);
		}
		return resultado;
	}

	// Mapas, clase HashMap

	public HashMap<String, Estudiante> introMapas() {
		// la clave representa el nif del Estudiante
		HashMap<String, Estudiante> resultado = new HashMap<String, Estudiante>();
		Estudiante est = new Estudiante(123, "435G", "Paco", 'M', null, 180, null, null);
		resultado.put(est.getNif(), est);
		Estudiante estudiante = resultado.get("435G");
		Estudiante est2 = new Estudiante(321, "435G", "Carlos", 'M', null, 180, null, null);

		resultado.put("435G", est2);
		resultado.put("123T", new Estudiante(123, "123T", "Pepe", 'M', null, 180, null, null));
		Set<String> claves = resultado.keySet();
		for (String clave : claves) {
			// System.out.println(resultado.get(clave).getNombre());
		}

		return resultado;
	}

	// private static String[] diasSemana = { "lunes", "martes", "miercoles",
	// "jueves", "viernes", "sï¿½bado", "domingo" };

	public boolean esPrimo(int numero) {

		for (int i = 2; i < numero; i++) {
			if (numero % i == 0)
				return false;
		}

		return true;
	}

	public int[] numerosPrimos(int cuantos) {
		int[] primos = new int[cuantos];
		int i = 0;
		int j = 1;
		while (i < cuantos) {
			if (esPrimo(j))
				primos[i++] = j;
			j++;
		}
		return primos;
	}

	public int[] numerosFibonacci(int cuantos) {
		int[] numeros = new int[cuantos];
		int x = 0;
		int y = 1;
		int z;
		numeros[0] = x;
		numeros[1] = y;
		for (int i = 2; i < cuantos; i++) {
			z = x + y;
			numeros[i] = z;
			x = y;
			y = z;
		}
		return numeros;
	}

	// LIGA: Obtener clasificaciï¿½n a partir de resultados
	public int[] obtenerClasificacion(String[][] goles) {
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int i = 0; i < goles.length; i++)
			for (int j = 0; j < goles[i].length; j++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}

	public int[] obtenerClasificacion2(String[][] goles) {
		// la diferencia con el anterior es que recorre la
		// matriz por columnas
		int[] puntos = new int[5];
		int golesLocal;
		int golesVisitante;
		String[] resultado = null;
		// recorrer la matriz de goles
		for (int j = 0; j < goles[0].length; j++)
			for (int i = 0; i < goles.length; i++)
				if (goles[i][j].indexOf('-') != -1) {
					resultado = goles[i][j].split("-");
					golesLocal = Integer.parseInt(resultado[0]);
					golesVisitante = Integer.parseInt(resultado[1]);
					if (golesLocal > golesVisitante)
						// suma 3 al local
						puntos[i] += 3;
					else if (golesLocal < golesVisitante)
						// suma 3 al visitante
						puntos[j] += 3;
					else { // empate
						puntos[i] += 1;
						puntos[j] += 1;
					}
				}
		return puntos;
	}

	public Equipo[] obtenerClasificacion3(int[][] puntosJornadas) {
		Equipo[] clasificacion = new Equipo[5];
		String[] equipos = new Datos().getEquipos();
		for (int j = 0; j < puntosJornadas[0].length; j++) {
			Equipo e = new Equipo();
			e.setNombre(equipos[j]);
			e.setPuntos(0);
			for (int i = 0; i < clasificacion.length; i++)
				e.setPuntos(e.getPuntos() + puntosJornadas[i][j]);
			clasificacion[j] = e;
		}

		return clasificacion;
	}

	public boolean validarNif(String nif) {
		char[] letrasValidas = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q',
				'V', 'H', 'L', 'C', 'K', 'E' };

		if (nif.length() != 9)
			return false;
		String numeros = nif.substring(0, 8);
		// System.out.println(numeros);
		int numerosOK;
		try {
			numerosOK = Integer.parseInt(numeros);
		} catch (NumberFormatException e) {
			return false;
		}
		int resto = numerosOK % 23;
		if (letrasValidas[resto] != nif.charAt(8))
			return false;
		return true;
	}

	// ORDENACION
	public void ordenaEnteros(int[] numeros) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] > numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
				}
	}
	
	
	public int [] ordenaMatrizEnteros(int[][] numeros) {
		
		
		int totalCeldas = 0;
		
		for (int i = 0; i < numeros.length; i++) {
			totalCeldas += numeros[i].length;		
		}
		int [] resultado = new int[totalCeldas];
		int fila = 0;
		int col = 0;
		int index = 0;
		for (int i = 0; i < numeros.length; i++) {
			for (int j = 0; j < numeros[i].length; j++) {
				if (j == numeros[i].length-1) {
					fila = i+1;
					col = 0;
				} else {
					fila = i;
					col = j +1;
				}

				System.out.println("i - j : " + i + " - " + j);
				System.out.println((fila < numeros.length) + " - " + (col <= numeros[numeros.length-1].length));
				System.out.println((numeros.length) + " - " + (numeros[numeros.length-1].length));
				while ((fila < numeros.length) && (col <= numeros[numeros.length-1].length)) {
					System.out.println((fila < numeros.length) + " - " + (col <= numeros[numeros.length-1].length));
					System.out.println("ANTES: " + fila + " - " + col);
					System.out.println("LONG FILA: " + (numeros[fila].length-1));
					
					if (numeros[i][j] > numeros[fila][col]) {
						int aux = numeros[i][j];
						numeros[i][j] = numeros[fila][col];
						numeros[fila][col] = aux;						
					}	
					System.out.println("-----------------------");
					if (col >= numeros[fila].length -1) {
						col = 0;
						fila++;
					} else {
						col++;
					}
					System.out.println("DESPUES: " + fila + " - " + col);
					
						
				}
				resultado[index++] = numeros[i][j];
			}
			
		}	
		System.out.println();
		return resultado;
	}
	
	
	public void ordenarEnterosArrayList(ArrayList<Integer> numeros) {
		Collections.sort(numeros);
	}
	
	
	public ArrayList<Object> leerFicheroTextoAtributosObjetos(String rutaFichero) {
		ArrayList<Object>  resultado = new ArrayList<Object>();
		String linea;
		
		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);

			while((linea = br.readLine()) != null) {
				String[] atributos = linea.split("#");
				String clase = atributos[0];
				Integer id = Integer.parseInt(atributos[1]);
				String nombre = atributos[2];
				Float precio = Float.parseFloat(atributos[3]);
				Character tipo = atributos[4].charAt(0);
				Boolean nuevo = Boolean.valueOf(atributos[5]);
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate fabricacion = LocalDate.parse(atributos[6],formato);
				Object obj = construyeUnObjeto(clase, id, nombre, precio, tipo, nuevo, fabricacion);
				System.out.println("****" + obj.getClass());
				
				resultado.add((obj.getClass().cast(obj)));
	
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public ArrayList<HashMap<Integer,Integer>> leerFicherosIslasPorMes(String rutaFichero){
		ArrayList<HashMap<Integer,Integer>> resultado = new ArrayList<HashMap<Integer,Integer>>();
						
		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			ArrayList<String[]> todoTrozos = new ArrayList<String[]>();
			while(linea !=null) {
				linea = br.readLine();
				if(linea !=null) {
					String[] isla = linea.split("@");
					todoTrozos.add(isla);
				}
			}
						
			for (int j = 0; j < todoTrozos.get(0).length; j++) {//*****recorre islas
				HashMap<Integer,Integer>mes = new HashMap<Integer,Integer>();
				resultado.add(mes);
				for (int i = 0; i < todoTrozos.size(); i++) {//*****recorre meses
					
					Integer convertido = Integer.parseInt(todoTrozos.get(i)[j]);
					mes.put(i,convertido );
					resultado.set(j,mes );
				}				
			}
	
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return resultado;
				
	}
	
	public void visitantesTotalesPorIsla(ArrayList<HashMap<Integer,Integer>> datosVisitantes){
		
		String[] islas = { "GC", "LTE", "FTV", "TFE", "LPA", "GOM", "HIE" };
		int indice = 0;//****hemos creado una variable indice para poder recorrer el array de islas, porque
						//****tenemos un foreach que no tiene variable con un indice.
		
		for (HashMap<Integer, Integer> elemento : datosVisitantes) {
			Integer visitantesAnio = 0;//****tenemos que inicializarlo antes de acumular para que no sea null
			for (int i = 0; i < elemento.size(); i++) {
				 visitantesAnio += elemento.get(i);
				 
			}
			System.out.println(islas[indice++] + " - " + visitantesAnio);
		}
		
	
	}

	//REFLEXION
	public Object construyeUnObjeto(String nombreClase, Integer id, String nombre, Float precio, Character tipo, Boolean nuevo, LocalDate fabricacion) {
		Object resultado = new Object();
		try {
			
			Class<?> clazz = Class.forName("modelo." + nombreClase);
			Class[] parameters = new Class[] {Integer.class, String.class, Float.class, Character.class, Boolean.class, LocalDate.class };
			
			Constructor<?> constructor = clazz.getConstructor(parameters);

			Object o = constructor.newInstance(id, nombre, precio, tipo, nuevo, fabricacion);
			System.out.println(clazz.getDeclaredMethod("getNombre").invoke(o));
			System.out.println("--------" + o);
			resultado = o;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}
	
	public void ordenaClasificacion(int[] numeros, String[] equipos) {
		for (int i = 0; i < numeros.length - 1; i++)
			for (int j = i + 1; j < numeros.length; j++)
				if (numeros[i] < numeros[j]) {
					int aux = numeros[i];
					numeros[i] = numeros[j];
					numeros[j] = aux;
					String aux2 = equipos[i];
					equipos[i] = equipos[j];
					equipos[j] = aux2;
				}
	}
	// mezcla dos arrays ordenados

	public int[] mezclaArrays(int[] l1, int[] l2) {
		int i = 0, j = 0, k = 0;
		int[] resultado = new int[l1.length + l2.length];

		while (l1[i] != Integer.MAX_VALUE || l2[j] != Integer.MAX_VALUE) {
			if (l1[i] < l2[j])
				resultado[k] = l1[i++];
			else
				resultado[k] = l2[j++];
			k++;

			if (i == l1.length)
				l1[--i] = Integer.MAX_VALUE;

			if (j == l2.length)
				l2[--j] = Integer.MAX_VALUE;
		}
		return resultado;
	}

	public void ordenaCadenas(String[] cadenas) {
		for (int i = 0; i < cadenas.length - 1; i++)
			for (int j = i + 1; j < cadenas.length; j++)
				if (cadenas[i].compareTo(cadenas[j]) > 0) {
					String aux = cadenas[i];
					cadenas[i] = cadenas[j];
					cadenas[j] = aux;
				}

	}
	
	public static void grabarObjetosEnFichero(String fichero) {
		Estudiante est = new Estudiante(10, "111G", "Paco1", 'M', null, 181, null, null);
		Estudiante est1 = new Estudiante(20, "222G", "Paco2", 'M', null, 180, null, null);
		Estudiante est2 = new Estudiante(30, "333G", "Paco3", 'M', null, 180, null, null);
		ArrayList<Estudiante> lista = new ArrayList<Estudiante>();

		// aï¿½adimos los 3 estudiantes a la lista

		lista.add(est);
		lista.add(est1);
		lista.add(est2);

		// abrir el fichero de objetos...
		try {
			FileOutputStream fIs = new FileOutputStream(fichero);
			ObjectOutputStream fObj = new ObjectOutputStream(fIs);

			// guardar los objetos estudiantes en el fichero...
			fObj.writeObject(lista);
			// fObj.writeObject(est);
			// fObj.writeObject(est1);
			// fObj.writeObject(est2);
			fObj.close();
			fIs.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error IO");

		}
		System.out.println("Fichero creado");
	}

	//**************************************grabarArrayListEnFichero***********************************************
	
		public void grabarArrayListEnFichero(String rutaFichero, ArrayList<Object> listaObjetos) {
			try {
				FileOutputStream foS = new FileOutputStream(rutaFichero);
				ObjectOutputStream ooS = new ObjectOutputStream(foS);
				ooS.writeObject(listaObjetos);
				ooS.close();
				foS.close();
				
				
			} catch (FileNotFoundException e) {
				
				System.out.println("FileNotFoundException");
			} catch (IOException e) {
				System.out.println("IOException");
			}
		}
		
		//*************************************************************************************
		
		@SuppressWarnings("unchecked")
		public ArrayList<Object> leerArrayListEnFichero(String rutaFichero){
			
			Object test =null;
			try {
				FileInputStream fis = new FileInputStream(rutaFichero);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				
					 test = ois.readObject();
					
					System.out.println(test);
				
				ois.close();
				fis.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException");
			} catch (IOException e) {
				System.out.println("IOException");
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException");
			}
			return (ArrayList<Object>)test;
		}
	public void ordenaEstudiantes(Estudiante[] estudiantes) {
		// ejemplo de uso de la interfaz Comparable
		// debe implementarse el mï¿½todo compareTo

		for (int i = 0; i < estudiantes.length - 1; i++)
			for (int j = i + 1; j < estudiantes.length; j++)
				if (estudiantes[i].compareTo(estudiantes[j]) > 0) {
					Estudiante aux = estudiantes[i];
					estudiantes[i] = estudiantes[j];
					estudiantes[j] = aux;
				}
	}
	public float calculaSaldo(float saldoInicial, float[] movimientos) {
		float saldoFinal = saldoInicial;
		for (int i = 0; i < movimientos.length; i++)
			saldoFinal += movimientos[i];
		return saldoFinal;
	}
	public float calculaSaldo(float saldoInicial, ArrayList<Float> movimientos) {
		float saldoFinal = saldoInicial;
		for (Float elemento : movimientos) {
			saldoFinal += elemento;
		}
		return saldoFinal;
	}

	public int[] convierteCadenasANumeros(String[] cadenas) {
		int[] resultado = new int[cadenas.length];
		for (int i = 0; i < resultado.length; i++) {

			try {

				resultado[i] = Integer.parseInt(cadenas[i]);
			} catch (NumberFormatException e) {

				resultado[i] = -1;
			}
		}
		return resultado;
	}

	public ArrayList<Integer> convierteCadenasANumeros(ArrayList<String> cadenas) {
		// int[] resultado = new int[cadenas.length];
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		// for (int i = 0; i < resultado.length; i++) {
		for (String cadena : cadenas) {
			try {

				// resultado[i] = Integer.parseInt(cadenas[i]);
				resultado.add(Integer.parseInt(cadena));
			} catch (NumberFormatException e) {

				// resultado[i] = -1;
				resultado.add(-1);
			}
		}
		return resultado;
	}
	
	public ArrayList<ArrayList<Integer>> convierteMatrizArrayEnMatrizArrayList(int[][] matriz){
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < matriz.length; i++) {
			resultado.add(new ArrayList<Integer>());
			for (int j = 0; j < matriz[i].length ; j++) {
				resultado.get(i).add(matriz[i][j]);
			}
		}
		return resultado;
	}


	public void muestraNumeros() {

		int x = 0;
		while (x <= 1000) {
			System.out.println("x: " + x);
			x++;
		}
	}

	public void muestraNumeros(int min, int max) {

		if (min <= max) {
			int x = min;
			while (x <= max) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros2(int min, int max) {

		if (min <= max) {
			int x = min;
			do {
				System.out.println("x: " + x);
				x++;
			} while (x <= max);
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}

	public void muestraNumeros3(int min, int max) {
		int x = min;
		if (min <= max) {
			// for (int x = min; x < max; x++) {
			// for (;;) {
			while (true) {
				System.out.println("x: " + x);
				x++;
			}
		} else
			System.out.println("Error, min debe ser menor que maximo");
	}
	
	public ArrayList<Float> generaAleatoriosArrayListFloat(int cuantos, float inferior, float superior) // max 30, min 10
	{
		ArrayList<Float> resultado = new ArrayList<Float>();
		
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++) {
			resultado.add(inferior + rnd.nextFloat() * superior);
			
		}
		return resultado;
	}
	public ArrayList<Integer> generaAleatoriosArrayListInteger(int cuantos, int inferior, int superior) // max 30, min 10
	{
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++) {
			resultado.add(inferior + rnd.nextInt(superior - inferior + 1));
			
		}
		return resultado;
	}
	

	public void generaAleatorios(int cuantos, int inferior, int superior) // max 30, min 10
	{

		for (int i = 0; i < cuantos; i++)
			System.out.println(inferior + (int) (Math.random() * (superior - inferior + 1)));

	}

	public void generaAleatorios2(int cuantos, int inferior, int superior) // max 30, min 10
	{

		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			System.out.println(inferior + rnd.nextInt(superior - inferior + 1));
	}

	public int[] generaAleatorios3(int cuantos, int inferior, int superior) // max 30, min 10
	{
		int[] resultado = new int[cuantos];
		Random rnd = new Random();
		for (int i = 0; i < cuantos; i++)
			// System.out.println(inferior + rnd.nextInt(superior - inferior + 1));
			resultado[i] = inferior + rnd.nextInt(superior - inferior + 1);
		return resultado;
	}

	public int[] frecuenciaAparicion(int cuantos, int inferior, int superior) {
		int[] resultado = new int[superior - inferior + 1];
		int[] lanzamientos = this.generaAleatorios3(cuantos, inferior, superior);
		for (int i = 0; i < lanzamientos.length; i++) {
			resultado[lanzamientos[i] - 1]++;
		}
		return resultado;

	}

	public void estadisticaCadena(String cadena) {
		int contadorVocales = 0;
		int contadorMayusculas = 0;
		int contadorEspeciales = 0;
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == 'a' || cadena.charAt(i) == 'e' || cadena.charAt(i) == 'i' || cadena.charAt(i) == 'o'
					|| cadena.charAt(i) == 'u' || cadena.charAt(i) == 'A' || cadena.charAt(i) == 'E'
					|| cadena.charAt(i) == 'I' || cadena.charAt(i) == 'O' || cadena.charAt(i) == 'U')
				contadorVocales++;
			if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z')
				contadorMayusculas++;
			int caracterAscii = cadena.charAt(i);
			if ((caracterAscii >= 0 && caracterAscii <= 47) || (caracterAscii >= 58 && caracterAscii <= 64) ||

					(caracterAscii >= 91) && (caracterAscii <= 96))

				contadorEspeciales++;
		}
		// System.out.println("Hay " + contadorVocales + " vocales en " + cadena);
		System.out.println("Hay " + contadorEspeciales + " caracteres especiales en " + cadena);

	}

	public void listaDiasSemana() {
		Datos datos = new Datos();
		// String[] diasSemana = { "lunes", "martes", "miercoles", "jueves", "viernes",
		// "sï¿½bado", "domingo" };
		// for (int i = 0; i < datos.getDiasSemana().length; i++)
		for (String dia : datos.getDiasSemana())
			// System.out.println(datos.getDiasSemana()[i]);
			System.out.println(dia);
	}

	public void listaEstudiantes(Estudiante[] lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public void listaEstudiantes(ArrayList<Estudiante> lista) {
		for (Estudiante estudiante : lista) {
			// if (estudiante != null)
			try {
				System.out.println(estudiante.getNombre());
			} catch (NullPointerException e) {

			}
		}
	}

	public int visitantesIslaYear(int isla, int[][] v) {
		int acu = 0;
		for (int j = 0; j < v[0].length; j++)
			acu += v[isla][j];
		return acu;
	}

	public int visitantesMesYear(int mes, int[][] v) {
		int acu = 0;
		for (int i = 0; i < v.length; i++)
			acu += v[i][mes];
		return acu;
	}

	public void recorrerMatrizIrregularPorColumnas(int[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}
		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}
	public void recorrerMatrizIrregularPorColumnas(ArrayList<ArrayList<Integer>> matriz) {
		ArrayList<ArrayList<Integer>> resultado = new ArrayList<ArrayList<Integer>>();
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.size(); i++) {
			if (matriz.get(i).size() > JMAX)
				JMAX = matriz.get(i).size();
		}
		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.size(); i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz.get(i).get(j));
				} catch (IndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}
	
	public void salvarMatrizVehiculosTabla(Object[][] datos1,String nombreTabla) {
		Object[][] datos = {
                {"3452FCN","1987-11-09",'A',5600,"43498107G"},
                {"1455GHN","2016-10-19",'A',15900,"4448109K"},
		};
		try {
			String mySqlDriver = "org.qjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://localhost/test";
			Class.forName(mySqlDriver);
			Connection conn = DriverManager.getConnection(myUrl, "user", "password");
			for (int i = 0; i < datos.length; i++) {

				String matricula = (String) datos1[i][0];
				//LocalDate fecha = LocalDate.parse((CharSequence) datos1[i][1]);
				Date fecha = Date.valueOf((String) datos1[i][1]);
				char tipo = (char) datos1[i][2];
				int precio = (int) datos1[i][3];
				String dni = (String) datos1[i][4];
				System.out.println(matricula + " " + fecha + " " + tipo + " " + precio + " " + dni);
					
				String query = " insert into" + nombreTabla + "()" + " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = conn.prepareStatement(query);
				preparedStmt.setString(1, matricula);
				preparedStmt.setDate(2, fecha);
				preparedStmt.setString(3, String.valueOf(tipo));
				preparedStmt.setInt(4, precio);
				preparedStmt.setString(5, dni);
				
				preparedStmt.execute();
			}

			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void matrizObjetosAFichertoTexto(Object[][] matriz, String rutaFichero) {
		try {
			FileWriter fw = new FileWriter(rutaFichero);
			BufferedWriter br = new BufferedWriter(fw);
			for (int i = 0; i < matriz.length; i++) {
				String cadena = "";
				for (int j = 0; j < matriz[0].length; j++) {
					System.out.println(matriz[i][j].toString());
					cadena += matriz[i][j].toString()+"$";
					
				}
				br.write(cadena+"\n");
			}
					
			br.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void recorrerMatrizIrregularPorColumnas2(Integer[][] matriz) {
		int JMAX = 0;
		// obtenemos el numero maximo de columnas
		for (int i = 0; i < matriz.length; i++) {
			if (matriz[i].length > JMAX)
				JMAX = matriz[i].length;
		}

		for (int j = 0; j < JMAX; j++) {
			for (int i = 0; i < matriz.length; i++) {
				try {
					System.out.println("[" + i + "][" + j + "]: " + matriz[i][j].byteValue());
				} catch (ArrayIndexOutOfBoundsException e) {
					continue;
				} catch (NullPointerException e) {
					continue;
				}

			}
		}
	}
	
	public String[] leerComunidadesAutonomasTxt() {
		String[] comunidades = new String[19];
		int index = 0;
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/comunidades.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				comunidades[index] = linea.split("%")[1];
				index++;
			}
			fr.close();//cierra el fichero
			br.close();//cierra el buffer
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}return comunidades;
	}
	public String[] leerComunidadesAutonomasTxt2() {
		String[] comunidades = new String[19];
		int index = 0;
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/comunidades.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			linea = br.readLine();
			//Este while sería con la condición tradicional sin usar una vaiarable dentro
			//a la que asignamos el valor de la linea.
			while (linea != null) {
				comunidades[index] = linea.split("%")[1];
				index++;
				linea = br.readLine();
			}
			fr.close();//cierra el fichero
			br.close();//cierra el buffer
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}return comunidades;
	}
	
	public String[] leerProvinciasTxt() {
		
		String[] provincias = new String[52];
		int index=0;
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/provincias.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {

				//String[] campos = linea.split("%");
				//provincias[index]=campos[1]+"%"+campos[2]+"%"+campos[3];
				provincias[index]=linea;
				index++;
				System.out.println(provincias);
			}			
			fr.close();
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	return provincias;
	}
	
	public String[] leerProvinciasTxt2() {
		String[] provincias = new String[52];
		int index = 0;
		try {
			// Abrir el fichero
			FileReader fr = new FileReader("ficheros/provincias.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			
			// Leer el fichero linea a linea
			while ((linea = br.readLine()) != null) {
				/*String provincia = strLine.split("%")[0];
				String comunidadAutonomaDeLaProvincia = strLine.split("%")[1];
				String habitantesDeLaProvincia = strLine.split("%")[2];
				resultado[index] = provincia + "%" + comunidadAutonomaDeLaProvincia + "%" + habitantesDeLaProvincia;*/
				provincias[index] = linea;
				index++;
			}
			fr.close();//cierra el fichero
			br.close();//cierra el buffer
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return provincias;
	}
	
	public void mostrarDatosComAutonomasProvinciasHabitantes(HashMap<Integer,ArrayList<Integer>> habitantes, HashMap<Integer,ArrayList<String>> nombresProvincias, String[] nombreComunidadesAutonomas ) {
		int subTotal = 0;
		int total = 0;
		System.out.println("***************************************************");
		System.out.println("******Comunidades Autónomas y sus Provincias*******");
		System.out.println("***************************************************");
		for (int indexComunidad = 0; indexComunidad < habitantes.size(); indexComunidad++) {
			System.out.println("----  " + nombreComunidadesAutonomas[indexComunidad] + "  ----");
			for (int indexProvincia = 0; indexProvincia < habitantes.get(indexComunidad).size(); indexProvincia++) {			
				System.out.print(nombresProvincias.get(indexComunidad).get(indexProvincia) + ":   \t");
				int numHabitantes = habitantes.get(indexComunidad).get(indexProvincia);
				subTotal += numHabitantes;
				System.out.println(numHabitantes);
					
			}
			System.out.println("TOTAL de habitantes de " + nombreComunidadesAutonomas[indexComunidad] + " = " + subTotal);
			total += subTotal;
			subTotal = 0;
			System.out.println();
		}
		System.out.println("TOTAL de habitantes de España = " + total);
	}
	
	public void mostrarDatosComAutonomasProvinciasHabitantes2(HashMap<Integer,ArrayList<Integer>> habitantes, HashMap<Integer,ArrayList<String>> nombresProvincias, String[] nombreComunidadesAutonomas ) {
		int subTotal = 0;
		int total = 0;
		
		System.out.println("***************************************************");
		System.out.println("******Comunidades Autónomas y sus Provincias*******");
		System.out.println("***************************************************");
		
		for ( Integer indexComunidad : habitantes.keySet()) {
			int indexProvincia =0;
			System.out.println("----  " + nombreComunidadesAutonomas[indexComunidad] + "  ----");
			ArrayList<Integer> provincias = habitantes.get(indexComunidad);
			for (Integer cantidadHabitantes : provincias) {				
				System.out.print(nombresProvincias.get(indexComunidad).get(indexProvincia) + ":   \t");
				subTotal += cantidadHabitantes;
				System.out.println(cantidadHabitantes);
				indexProvincia++;
			}
			System.out.println("TOTAL de habitantes de " + nombreComunidadesAutonomas[indexComunidad] + " = " + subTotal);
			total += subTotal;
			subTotal = 0;
			System.out.println();
		}
		
		System.out.println("TOTAL de habitantes de España = " + total);
	}
	
	//Leemos dos ficheros de textos llamados provincias.txt y comunidades.txt
	//debemos sacar los datos de los habitantes empadronados de cada provincia pero
	//agrupadas cada una por su comunidad autonoma, con el total de habitantes por
	//Comunidad autonoma
	public void leerFicheroTextoProvinciasComAutoYListarDatos() {
		String[] comunidadesAutonomas = leerComunidadesAutonomasTxt();
		String[] datosTodasLasProvincias = leerProvinciasTxt();
		HashMap<Integer,ArrayList<Integer>> comunidadesProvinciasHabitantes = new HashMap<Integer,ArrayList<Integer>>();
		for (int i = 0; i < comunidadesAutonomas.length; i++) {
				ArrayList<Integer> listadoProvincias = new ArrayList<Integer>();
				comunidadesProvinciasHabitantes.put(i, listadoProvincias);
		}

		//El siguiente HashMap llamado nombresProvincias almacena en su clave el indice de la comunidad
		//y en el valor un ArrayList de String donde cada String es el nombre de a provincia
		HashMap<Integer,ArrayList<String>> nombresProvincias = new HashMap<Integer,ArrayList<String>>();

			//Recorremos el Array de String que nos devolvió el método leerProvinciasTxt()
			
			for (String datosUnaProvincia : datosTodasLasProvincias) {
				String[] datosUnaProvinciaSeparados = datosUnaProvincia.split("%");				
				int indiceComunidadAutonoma = Integer.parseInt(datosUnaProvinciaSeparados[2])-1;			
				int habitantesUnaProvincia = Integer.parseInt(datosUnaProvinciaSeparados[3]);				
				//añadimos un condicional que solo cree un ArrayList la primera vez
				//cuando estamos en una comunidad autonoma y no nos cree ninguno mas
				//cuando volvamos a tener que añadir datos a la misma comunidad autonoma.
				if (nombresProvincias.get(indiceComunidadAutonoma) == null) {
					ArrayList<String> provincias = new ArrayList<String>();
					nombresProvincias.put(indiceComunidadAutonoma,provincias);
				}
				//añadimos al HashMap de provincias, el nombre que le corresponde en la comunidad atonoma
				//a la que pertence.
				nombresProvincias.get(indiceComunidadAutonoma).add(datosUnaProvinciaSeparados[1]);				
				//añadimos el número de habitantes a la provincia en la comunidad autonoma que corresponde
				comunidadesProvinciasHabitantes.get(indiceComunidadAutonoma).add(habitantesUnaProvincia);
			}
			
		//mostrarDatosComAutonomasProvinciasHabitantes(comunidadesProvinciasHabitantes,nombresProvincias, comunidadesAutonomas);
		mostrarDatosComAutonomasProvinciasHabitantes2(comunidadesProvinciasHabitantes,nombresProvincias, comunidadesAutonomas);
		System.out.println("BreakPoint");
		
	}
	
	
	public void mostrarDatosComAutonomasProvinciasHabitantes3( HashMap<Integer,ArrayList<String>> datosProvincias, String[] nombreComunidadesAutonomas ) {
		int subTotal = 0;
		int total = 0;
		
		System.out.println("***************************************************");
		System.out.println("******Comunidades Autónomas y sus Provincias*******");
		System.out.println("***************************************************");
		
		for ( Integer indexComunidad : datosProvincias.keySet()) {
			@SuppressWarnings("unused")
			int indexProvincia = 0;
			System.out.println("\u001B[47m\u001B[32m----------   \t" + nombreComunidadesAutonomas[indexComunidad] + "   \t----------\u001b[0m");
			ArrayList<String> provincias = datosProvincias.get(indexComunidad);
			for (String datosProvinciaHabitantes : provincias) {
				String[] datos = datosProvinciaHabitantes.split("%");
				String nombreProvincia = datos[0];
				int cantidadHabitantes = Integer.parseInt(datos[1]);
				System.out.print(nombreProvincia + ":   \t");
				subTotal += cantidadHabitantes;
				System.out.println(cantidadHabitantes);
				indexProvincia++;
			}
			
			System.out.println("\u001B[36mTOTAL de habitantes de " + nombreComunidadesAutonomas[indexComunidad] + " = \u001B[41m\u001B[37m" + subTotal + "\u001b[0m");
			total += subTotal;
			subTotal = 0;
			System.out.println();
		}
		
		System.out.println("\u001B[33mTOTAL de habitantes de España = \u001b[0m" + total);

	}
	
	public void leerFicheroTextoProvinciasComAutoYListarDatos2() {
		String[] comunidadesAutonomas = leerComunidadesAutonomasTxt();
		String[] datosTodasLasProvincias = leerProvinciasTxt();
		/*HashMap<Integer,ArrayList<Integer>> comunidadesProvinciasHabitantes = new HashMap<Integer,ArrayList<Integer>>();
		for (int i = 0; i < comunidadesAutonomas.length; i++) {
				ArrayList<Integer> listadoProvincias = new ArrayList<Integer>();
				comunidadesProvinciasHabitantes.put(i, listadoProvincias);
		}*/

		//El siguiente HashMap llamado nombresProvincias almacena en su clave el indice de la comunidad
		//y en el valor un ArrayList de String donde cada String es el nombre de a provincia
		HashMap<Integer,ArrayList<String>> datosProvincias = new HashMap<Integer,ArrayList<String>>();

			//Recorremos el Array de String que nos devolvió el método leerProvinciasTxt()
			
			for (String datosUnaProvincia : datosTodasLasProvincias) {
				String[] datosUnaProvinciaSeparados = datosUnaProvincia.split("%");
				String nombreProvincia = datosUnaProvinciaSeparados[1];
				int indiceComunidadAutonoma = Integer.parseInt(datosUnaProvinciaSeparados[2])-1;			
				int habitantesUnaProvincia = Integer.parseInt(datosUnaProvinciaSeparados[3]);				
				//añadimos un condicional que solo cree un ArrayList la primera vez
				//cuando estamos en una comunidad autonoma y no nos cree ninguno mas
				//cuando volvamos a tener que añadir datos a la misma comunidad autonoma.
				if (datosProvincias.get(indiceComunidadAutonoma) == null) {
					ArrayList<String> provincias = new ArrayList<String>();
					datosProvincias.put(indiceComunidadAutonoma,provincias);
				}
				//añadimos al HashMap de provincias, el nombre que le corresponde en la comunidad atonoma
				//a la que pertence.
				datosProvincias.get(indiceComunidadAutonoma).add(nombreProvincia + "%" + habitantesUnaProvincia);				
				//añadimos el número de habitantes a la provincia en la comunidad autonoma que corresponde
				//comunidadesProvinciasHabitantes.get(indiceComunidadAutonoma).add(habitantesUnaProvincia);
			}
			
		mostrarDatosComAutonomasProvinciasHabitantes3(datosProvincias, comunidadesAutonomas);
		System.out.println("BreakPoint");
		
	}
	
	public ArrayList<VehiculoOtro> leerFicheroVehiculos(String rutaFichero) {
		ArrayList<VehiculoOtro> resultado = new ArrayList<VehiculoOtro>();
		String linea;
		
		try {
			FileReader fr = new FileReader(rutaFichero);
			BufferedReader br = new BufferedReader(fr);

			while((linea = br.readLine()) != null) {
				String[] atributos = linea.split("#");
				Integer id = Integer.parseInt(atributos[0]);
				String matricula = atributos[1];
				Integer marcaModelo = Integer.parseInt(atributos[2]);
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyyMMdd");
				LocalDate fechaMatricula = LocalDate.parse(atributos[3],formato);				
				resultado.add(new VehiculoOtro(id,matricula,marcaModelo,fechaMatricula));	
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return resultado;
	}
	
	public static void grabarVehiculosEnFichero(ArrayList<VehiculoOtro> listadoVehiculos) {
		try {
			FileOutputStream fIs = new FileOutputStream("ficheros/vehiculosOtros.obj");
			ObjectOutputStream fObj = new ObjectOutputStream(fIs);
			for (VehiculoOtro vehiculoOtro : listadoVehiculos) {
				fObj.writeObject(vehiculoOtro);
			}
			fObj.close();
			fIs.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error IO");
		}
		System.out.println("Fichero creado");
	}

	public void leerObjetosVehiculosOtroYMostrarlos(String rutaFichero) {
		//ArrayList<VehiculoOtro> listaVehiculoOtro = new ArrayList<VehiculoOtro>();
		try {
			FileInputStream fis = new FileInputStream(rutaFichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			while (fis.available() > 0) {
				
				VehiculoOtro miVehiculo = (VehiculoOtro) ois.readObject();
				//listaVehiculoOtro.add(miVehiculo);
				System.out.println("---->" + miVehiculo.toString());
			}
			fis.close();
			ois.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		}
		
	}
	
	public int[][] ejercicio1(int nfilas, int nColumnas, int inferior, int superior){
		int[][] devuelve = new int[nfilas][nColumnas];
		
		for (int i = 0; i < devuelve.length; i++) {
			for (int j = 0; j < devuelve[0].length; j++) {
				Random numAleatorio = new Random();
				int dato = inferior + numAleatorio.nextInt(superior - inferior + 1);
				devuelve[i][j] = dato;
			}
		}
	
		return devuelve;		
	}
	
	
	public void ejercicio2() {
		HashMap<String,ArrayList<Float>> cuentasBancarias = new HashMap<String,ArrayList<Float>>();
		String numeroCuenta;
		Float saldoInicial;
		Float saldoParcial;
		try {
			FileReader fr = new FileReader("ficheros/cuentas.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			
			while((linea=br.readLine()) != null) {
				String[] datos = linea.split("&");
				numeroCuenta = datos[0];
				saldoInicial = Float.parseFloat(datos[1]);
				ArrayList<Float> cuenta = new ArrayList<Float>();
				cuenta.add(saldoInicial);
				cuentasBancarias.put(numeroCuenta, cuenta);				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileReader fr = new FileReader("ficheros/movimientos.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			
			while((linea=br.readLine()) != null) {
				String[] datos = linea.split("&");
				numeroCuenta = datos[1];
				saldoParcial = Float.parseFloat(datos[2]);
				//añado el saldo parcial al hashmap donde la clave coincide con el numCuenta
				cuentasBancarias.get(numeroCuenta).add(saldoParcial);
							
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(cuentasBancarias);
		
	}
	
}
