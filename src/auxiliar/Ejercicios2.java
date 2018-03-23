package auxiliar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import modelo.Persona;

public class Ejercicios2 {
	private HashMap<String, Persona> mPersonas = new HashMap<String, Persona>();

	private ArrayList<Persona> personas; // lista del Menu

	private ArrayList<String> cadenas;
	private ArrayList<ArrayList<Persona>> matrizPersonas;

	private Scanner teclado = new Scanner(System.in);
	private boolean error = true;

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public HashMap<String, Persona> getmPersonas() {
		return mPersonas;
	}
	/* ficheros */

	public void leerFicheroTexto(String rutaFichero) {
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutaFichero));
			String linea;

			while ((linea = br.readLine()) != null) {

				System.out.println(linea);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (EOFException e) {
			System.out.println("Fin de fichero...");

		} catch (IOException e) {
			System.out.println("Ooops, excepcion!");
		} catch (Exception e) {
			System.out.println("Cualquier excepcion");

		}

	}

	public void creaPartidosTodos(String rutaPartidos, String[] equipos) {
		try {
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(rutaPartidos));
			for (int i = 0; i < equipos.length - 1; i++) {
				for (int j = i + 1; j < equipos.length; j++) {
					// generar linea fichero partidos.txt
					int golesLocal = (int) (Math.random() * 4);
					int golesVisitante = (int) (Math.random() * 4);
					String linea = equipos[i] + "#" + Integer.toString(golesLocal) + "#" + equipos[j] + "#"
							+ Integer.toString(golesVisitante) + "\n";
					bw.write(linea);
				}
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("erro io");
		}
	}

	public HashMap<String, ArrayList<Integer>> creaClasificacion2(String rutaPartidos) {
		HashMap<String, ArrayList<Integer>> resultado = new HashMap<String, ArrayList<Integer>>();
		String[] equipos = { "RMA", "BCN", "UDL", "ALA", "RSO", "BET", "EIB", "ATM", "ATB", "SEV", "VIL", "ESP", "CEL",
				"VAL", "DEP", "MLG", "LEG", "GRA", "SPO", "OSA" };
		for (int i = 0; i < equipos.length; i++) {
			resultado.put(equipos[i], new ArrayList<Integer>());
		}
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutaPartidos));
			String linea;

			while ((linea = br.readLine()) != null) {
				int golesLocal = Integer.parseInt(linea.split("#")[1].trim());
				String eLocal = linea.split("#")[0];
				int golesVisitante = Integer.parseInt(linea.split("#")[3].trim());
				String eVisitante = linea.split("#")[2];
				int puntos;
				if (golesLocal > golesVisitante) {
					resultado.get(eLocal).add(new Integer(3));
				} else if (golesLocal < golesVisitante) {
					resultado.get(eVisitante).add(new Integer(3));
				} else {
					resultado.get(eLocal).add(new Integer(1));
					resultado.get(eVisitante).add(new Integer(1));
				}

			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (EOFException e) {
			System.out.println("Fin de fichero...");

		} catch (IOException e) {
			System.out.println("Ooops, excepcion!");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return resultado;
	}

	public HashMap<String, Integer> creaClasificacion(String rutaPartidos) {
		HashMap<String, Integer> resultado = new HashMap<String, Integer>();
		String[] equipos = { "RMA", "BCNA", "UDLP", "ALA", "RSO", "BET", "EIB", "ATM", "ATB", "SEV", "VIL", "ESP",
				"CEL", "VAL", "DEP", "MLG", "LEG", "GRA", "SPO", "OSA" };
		for (int i = 0; i < equipos.length; i++) {
			resultado.put(equipos[i], new Integer(0));
		}
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutaPartidos));
			String linea;

			while ((linea = br.readLine()) != null) {
				int golesLocal = Integer.parseInt(linea.split("#")[1].trim());
				String eLocal = linea.split("#")[0];
				int golesVisitante = Integer.parseInt(linea.split("#")[3].trim());
				String eVisitante = linea.split("#")[2];
				int puntos;
				if (golesLocal > golesVisitante) {
					puntos = resultado.get(eLocal).intValue() + 3;
					resultado.put(eLocal, new Integer(puntos));
				} else if (golesLocal < golesVisitante) {
					puntos = resultado.get(eVisitante).intValue() + 3;
					resultado.put(eVisitante, new Integer(puntos));
				} else {
					puntos = resultado.get(eLocal).intValue() + 1;
					resultado.put(eLocal, new Integer(puntos));
					puntos = resultado.get(eVisitante).intValue() + 1;
					resultado.put(eVisitante, new Integer(puntos));
				}

			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		} catch (EOFException e) {
			System.out.println("Fin de fichero...");

		} catch (IOException e) {
			System.out.println("Ooops, excepcion!");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return resultado;
	}

	public void escribirLanzamientosDadoFichero(int numLanzadas) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("ficheros/lanzadas.txt"));
			for (int i = 0; i < numLanzadas; i++) {
				int valor = (int) (1 + Math.random() * 6);
				bw.write(Integer.toString(valor) + "\n");
			}
			bw.close();
		} catch (IOException e) {
		}
	}

	public void mediaEnterosFichero(String rutaFichero) {
		int contaErrores = 0;
		try {
			BufferedReader br;
			br = new BufferedReader(new FileReader(rutaFichero));
			String linea;
			int contador = 0;
			int acumulador = 0;

			while ((linea = br.readLine()) != null) {
				try {
					int valor = Integer.parseInt(linea);
					contador++;
					acumulador += valor;
				} catch (NumberFormatException e) {
					// System.out.println("NumberFormat");
					contaErrores++;
				}
			}
			System.out.println("Errores :" + contaErrores);
			System.out.println("Acumulado :" + acumulador + "  , media :" + (acumulador / contador));
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");

		}

		catch (EOFException e) {
			System.out.println("Fin de fichero...");

		} catch (IOException e) {
			System.out.println("Ooops, excepcion!");
		} catch (Exception e) {
			System.out.println("Cualquier excepcion");

		}

	}

	

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	public HashMap<String, Persona> crearMapaPersonas(ArrayList<Persona> miLista) {
		HashMap<String, Persona> mPersonas2 = new HashMap<String, Persona>();
		for (Persona persona : miLista) {
			mPersonas2.put(persona.getNif(), persona);
		}

		return mPersonas2;
	}

	public String validaNif(String nif) {
		// Criterios de validación del nif. Debe
		// tener 9 caracteres
		// los 8 primeros deben ser números y el último carácter una letra
		// la letra debe obtenerse a partir de un algoritmo en función de los
		// dígitos

		if (!nif.matches("[0-9]{8}[a-zA-Z]{1}")) {
			System.out.println("nif erroneo");
			return "";
		} else
		// comprobamos que la letra se obtiene a partir de los números
		if (!validaLetraNif(nif)) {
			System.out.println("nif erroneo, letra mal");
			return "";
		}
		return nif;
	}

	private boolean validaLetraNif(String nif) {
		// extraer y convertir el numero del nif (substr)
		int numeroNif = Integer.parseInt(nif.substring(0, 8));
		// extraer la letra
		char letraNif = nif.substring(8).toUpperCase().charAt(0);

		String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKE";
		if (NIF_STRING_ASOCIATION.charAt(numeroNif % 23) == letraNif)
			return true;
		else
			return false;

	}

	public char validaSexo(String sexo) {
		if (sexo.length() != 1 || !sexo.matches("[mMfF]"))
			return 0;
		else
			return sexo.charAt(0);
	}

	public int validaFecha(String fecha) { // ddMMyyyy

		// AAAA, posterior a 1900, anterior a la actual
		// MM , entre 01 y 12
		// DD , depende del mes .. ;-)
		int year, mes, dia;
		int[] diasMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if (fecha.length() != 8)
			return -1;
		try {
			year = Integer.parseInt(fecha.substring(4, 8));
			mes = Integer.parseInt(fecha.substring(2, 4));
			dia = Integer.parseInt(fecha.substring(0, 2));
		} catch (NumberFormatException e) {
			return -1;
		}
		if (mes < 1 || mes > 12)
			return -1;
		if (dia < 1 || dia > diasMes[mes - 1])
			return -1;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date fechaTecleada = null;
		try {
			fechaTecleada = sdf.parse(fecha);
		} catch (ParseException e) {
		}

		Date fActual = new Date(); // fecha de hoy
		if (year < 1900 || fechaTecleada.after(fActual)) {
			return -1;
		}
		return Integer.parseInt(fecha);
	}

	public void borrarPersonaIdEnMapa(HashMap<String, Persona> mapa) { // se //
																		// valida
																		// // el
																		// ID
		String tecleado = null;
		do { // lectura por teclado del id de la persona a borrar del mapa

			try {
				error = false;
				System.out.println("Introduzca ID de la persona (1-" + mapa.size() + ") o q|Q para volver");
				tecleado = teclado.nextLine();
				if (tecleado.compareToIgnoreCase("q") == 0)
					return;
				if (Integer.parseInt(tecleado) < 1 || Integer.parseInt(tecleado) > mapa.size())
					error = true;
			} catch (NumberFormatException e) {
				error = true;
			}
		} while (error);

		Set<String> claves = mapa.keySet();
		String[] ks = new String[claves.size()];
		claves.toArray(ks);
		String nifABorrar = ks[Integer.parseInt(tecleado) - 1];

		// pedir confirmacion de borrado
		System.out.println(
				"¿Está realmente SEGURO  de eliminar del mapa al nif " + nifABorrar + "? (ENTER para confirmar)");

		String borrarSiNo = teclado.nextLine();
		System.out.println("tecleado : " + borrarSiNo);
		if (borrarSiNo.equals("")) { // Si
			System.out.println("Eliminando del mapa al nif " + nifABorrar);

			mapa.remove(nifABorrar);
		} else
			System.out.println("Eliminación cancelada por el usuario");
	}

	public static ArrayList<HashMap<String, String>> creaDatos(String[] claves, String[] valores) {
		ArrayList<HashMap<String, String>> resultado = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < valores.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(claves[i], valores[i]);
			resultado.add(hm);
		}
		return resultado;
	}

	public static ArrayList<HashMap<Integer, Integer>> creaDatos2(String[] islas, int[][] visitantesIslaMes) {
		ArrayList<HashMap<Integer, Integer>> resultado = new ArrayList<HashMap<Integer, Integer>>();
		for (int i = 0; i < islas.length; i++) {
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			for (int j = 0; j < visitantesIslaMes[i].length; j++) {
				hm.put(j, visitantesIslaMes[i][j]);
			}
			resultado.add(hm);
		}
		return resultado;
	}

	public int[] getVisitantesIslaYear(ArrayList<HashMap<Integer, Integer>> listaVisitantes) {
		int[] visitantesAcumuladoIsla = new int[listaVisitantes.size()];
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < listaVisitantes.size(); i++) {
			visitantesAcumuladoIsla[i] = 0;
			hm = listaVisitantes.get(i);
			Set<Integer> claves = hm.keySet();
			for (Integer clave : claves) {
				visitantesAcumuladoIsla[i] += hm.get(clave).intValue();
			}
		}
		return visitantesAcumuladoIsla;
	}
	
	public int[] getVisistantesIslaMes(ArrayList<HashMap<Integer, Integer>> listaVisitantes) {
		int numIslas = listaVisitantes.get(0).keySet().size();
		int[] visitantesAcumuladoMes = new int[numIslas]; 
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int j = 0; j < numIslas; j++) {
			visitantesAcumuladoMes[j] = 0;
			for (int i = 0; i < listaVisitantes.size(); i++) {
				hm = listaVisitantes.get(i);
				Integer valor = hm.get(j);
				visitantesAcumuladoMes[j] += valor;
			}
		}
		return visitantesAcumuladoMes;
	}

	public ArrayList<HashMap<Integer, Integer>> leerFicheroDatosVisitantesMes(String ruta) {
		ArrayList<HashMap<Integer, Integer>> resultado = new ArrayList<HashMap<Integer, Integer>>();
		HashMap<Integer, Integer>[] parcial = new HashMap[7];
		for (int i = 0; i < 7; i++) {
			resultado.add(new HashMap<Integer,Integer>());
			parcial[i] = new HashMap<Integer, Integer>();
		}
		
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			String linea;
			int cuentaMeses = 0;
			while ((linea = br.readLine()) != null) {
				
				String[] islas = linea.split("@");
				for (int i = 0; i < islas.length; i++) {
					parcial[i].put(cuentaMeses, Integer.parseInt(islas[i]));
					
					resultado.set(i, parcial[i]);
					//System.out.println("cuentaMeses= " + cuentaMeses + "   i= " + i + "  valor= " + islas[i]);
				}
				
				
				cuentaMeses++;
			}

			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error de entra / salida");
		}		
		return resultado;
	}
	
	public void mostrarVisitantesIslaMes(ArrayList<HashMap<Integer, Integer>> listaVisitantes) {
		String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPT",
				"OCTUBRE", "NOV", "DIC" };
		String[] islas = { "GRAN CANARIA", "LANZAROTE", "FUERTEVENTURA", "TENERIFE", "LA PALMA", "GOMERA    ",
				"EL HIERRO" };
		int isla = 0;
		System.out.print("\t");
		for (String mes : meses) {
			System.out.print("\t" + mes);
		}
		System.out.println();
		for (HashMap<Integer, Integer> visitasIslaYear : listaVisitantes) {
			System.out.print(islas[isla++]);
			for (Integer mes = 0; mes < visitasIslaYear.size(); mes++) {	
				System.out.print("\t" + visitasIslaYear.get(mes).intValue() + "   ");
			}
			System.out.println();
		}
	}

	public void borrarPersona() { // se valida el ID
		String tecleado = null;
		do { // lectura por teclado del id de la persona a borrar de la
				// lista
			try {
				error = false;
				System.out.println("Introduzca ID de la persona (1-" + personas.size() + ") o q|Q para volver");
				tecleado = teclado.nextLine();
				if (tecleado.compareToIgnoreCase("q") == 0)
					return;
				if (Integer.parseInt(tecleado) < 1 || Integer.parseInt(tecleado) > personas.size())
					error = true;
			} catch (NumberFormatException e) {
				error = true;
			}
		} while (error);
		// pedir confirmacion de borrado
		System.out.println("¿Está realmente SEGURO  de eliminar de la lista a "
				+ personas.get(Integer.parseInt(tecleado) - 1).getNombre() + "? (ENTER para confirmar)");

		String borrarSiNo = teclado.nextLine();
		System.out.println("tecleado : " + borrarSiNo);
		if (borrarSiNo.equals("")) { // Si
			System.out.println(
					"Borrando a " + personas.get(Integer.parseInt(tecleado) - 1).getNombre() + "  de la lista");

			personas.remove(Integer.parseInt(tecleado) - 1);
		} else
			System.out.println("Eliminación cancelada por el usuario");
	}

	public void borrarPersonaNif() {
		String tecleado = null;
		do { // lectura por teclado del nif de la persona a borrar de la
				// lista
			error = false;
			System.out.println("Introduzca NIF de la persona (99999999X) o q|Q para volver");
			tecleado = teclado.nextLine();
			if (tecleado.compareToIgnoreCase("q") == 0)
				return;
			if (validaNif(tecleado).equals(""))
				error = true;
		} while (error);
		// existe el nif en la lista ?
		// System.out.println("Nif : "+ tecleado);
		if (existEnLista(tecleado)) {
			System.out.println("¿Está realmente SEGURO  de eliminar de la lista a la persona con NIF " + tecleado
					+ "? (ENTER para confirmar)");
			String borrarSiNo = teclado.nextLine();
			System.out.println("tecleado : " + borrarSiNo);
			if (borrarSiNo.equals("")) { // Si
				System.out.println("Borrando a " + personas.get(getIdFromNif(tecleado)).getNombre() + "  de la lista");
				personas.remove(getIdFromNif(tecleado));
			} else
				System.out.println("Eliminación cancelada por el usuario");
		} else
			System.out.println("Nif No existe en la lista");
	}


	
	private boolean existEnLista(String nif) {
		for (Persona persona : personas) {
			if (persona.getNif().equals(nif))
				return true;
		}
		return false;
	}

	public int getIdFromNif(String nif) {
		for (int i = 0; i < personas.size(); i++) {
			if (personas.get(i).getNif().compareToIgnoreCase(nif) == 0)
				return i;
		}
		return 0;

	}


	public void recorrerMapa(HashMap<String, Persona> mapa) {
		System.out.println("\tID\tNIF \t\t\tNOMBRE\t\tFECHA\t\t\tSEXO");
		int contador = 1;
		Set<String> claves = mapa.keySet();

		for (String clave : claves)
			if (mapa.get(clave) != null)
				System.out.println(
						"\t" + (contador++) + "\t" + mapa.get(clave).getNif() + "\t\t" + mapa.get(clave).getNombre()
								+ "\t\t " + mapa.get(clave).getFecha() + "\t\t" + mapa.get(clave).getSexo());
	}

	public void buscarEnMapa(String[] nifs) {

		for (String nif : nifs)
			if (mPersonas.containsKey(nif))
				System.out.println("existe nif " + nif);
			else
				System.out.println("NO existe nif " + nif);
	}

	public void insertarEnCadenas() {
		cadenas = new ArrayList<String>();
		cadenas.add("Paco");
		cadenas.add("Juan");
		cadenas.add("Maria");
		cadenas.add("Carlos");

	}

	

	
	public void recorrerListaPersonas() {
		for (int i = 0; i < personas.size(); i++) // bucle básico
			System.out.println(personas.get(i).getNombre());
	}

	public void recorrerListaPersonas2() {
		System.out.println("\tID\tNIF \t\t\tNOMBRE\t\tFECHA\t\t\tSEXO");
		int contador = 1;
		for (Persona persona : personas)
			if (persona != null)
				System.out.println("\t" + (contador++) + "\t" + persona.getNif() + "\t\t" + persona.getNombre()
						+ "\t\t " + persona.getFecha() + "\t\t" + persona.getSexo());
	}

	public void recorrerListaPersonas3() { // con un iterador
		Iterator<Persona> iteradorLista = personas.iterator();
		while (iteradorLista.hasNext()) {
			Persona persona = (Persona) iteradorLista.next();
			// Procesar persona
			System.out.println(persona.getNombre());
		}
	}

	public void recorrerMatrizPersonas() {
		for (int i = 0; i < matrizPersonas.size(); i++) // bucle básico
			for (int j = 0; j < matrizPersonas.get(i).size(); j++) {
				try {
					System.out.print(matrizPersonas.get(i).get(j).getNombre() + ", ");
				} catch (NullPointerException e) {
					System.out.println("null");
				}
			}
	}

	

	public ArrayList<String> getCadenas() {
		return cadenas;
	}

	public void setCadenas(ArrayList<String> cadenas) {
		this.cadenas = cadenas;
	}

	public void modificarPersona() {
		do { // lectura por teclado y validación de los datos

			error = false;
			System.out.println(
					"Introduzca datos de la persona (nif#[nombre]#[sexo(m|f)]#[fecha(ddmmaaaa)], o q|Q para volver");
			String tecleado = teclado.nextLine();
			if (tecleado.compareToIgnoreCase("q") == 0)
				return;
			String[] campos = tecleado.split("#");
			if (campos.length < 2) {
				System.out.println(" NUMERO DE PARAMETROS DEBE SER AL MENOS 2\n");
				error = true;
				continue;
			}

			if (existEnLista(campos[0])) {
				modificarPersonaLista(campos[0]);
				System.out.println("Modificado el objeto Nif :" + campos[0]);
			} else {
				System.out.println("Nif inexistente, " + campos[0]);
				error = true;
			}
		} while (error);

	}

	private void modificarPersonaLista(String tecleado) {

		System.out.println("modificando la persona de nif " + tecleado);
	}

}
