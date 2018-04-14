package principal;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import auxiliar.Practicas;
import modelo.Datos;
import modelo.Equipo;
import modelo.Estudiante;
import modelo.Vehiculo;
import modelo.VehiculoOtro;
import dao.AccesosTienda;

public class Principal {
	private static final String String = null;

	// metodo por el que debe empezar la ejecuci�n ..
	public static void main(String[] args) {
		/*
		 * Persona persona; persona = new Persona();
		 * 
		 * persona.setNombre("Manolo"); System.out.println("nif : " + persona.getNif());
		 * System.out.println("nombre : " + persona.getNombre());
		 * System.out.println("altura : " + persona.getAltura());
		 * System.out.println("persona1 creada");
		 */
		/*
		 * Persona padre = new Persona(); padre.setNombre("JorgePadre");
		 * padre.setSexo('M'); Persona madre = new Persona();
		 * madre.setNombre("MariaMadre"); madre.setNif("44567981H"); madre.setSexo('F');
		 */

		/*
		 * Persona persona2 = new Persona("43897124R", "PEPE", 'F', LocalDate.of(1999,
		 * 9, 21), 170, padre, madre); System.out.println("persona2 creada");
		 * System.out.println("Nombre madre : " + persona2.getMadre().getNombre());
		 */
		// crear un Estudiante

		Estudiante estAnonimo = new Estudiante(123);

		Estudiante estudiante = new Estudiante(111, "44556677G", "Rafael", 'M', LocalDate.now(), 187, estAnonimo, null);
		Estudiante estudiante2 = new Estudiante(111, "44556674T", "Javier", 'M', LocalDate.now(), 187, estAnonimo,
				null);
		Estudiante estudiante3 = new Estudiante(111, "44556672X", "Marcos", 'M', LocalDate.now(), 187, estAnonimo,
				null);

		Estudiante[] listaEstudiantes = { estAnonimo, estudiante, estudiante2, estudiante3 };

		Practicas practicas = new Practicas();
		//practicas.ordenaEstudiantes(listaEstudiantes);

		// new Practicas().muestraNumerosDe1A1000();
		// practicas.muestraNumerosDe1A1000();
		// practicas.muestraNumeros3(20, 25);
		// practicas.generaAleatorios2(3,10,11);
		// practicas.estadisticaCadena("Las&%=\" PalmAs\\ de Gran CanarIa");
		// int [] numeros=practicas.generaAleatorios3(100, 1, 6);
		// int[] frecuencia = practicas.frecuenciaAparicion(100, 1, 8);
		// practicas.listaDiasSemana();
		// Estudiante[] lista = new Datos().getEstudiantes();
		// practicas.listaEstudiantes(new Datos().getEstudiantes());
		int[][] visitantesYear = { { 2, 4, 5, 1, 3, 2, 1, 1, 1, 3, 5, 1 },
				{ 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 13 }, { 23, 3, 56, 12, 32, 23, 17, 12, 11, 34, 45, 12 },
				{ 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 13 }, { 23, 4, 56, 12, 32, 23, 17, 12, 11, 34, 45, 10 },
				{ 23, 3, 56, 12, 32, 23, 17, 12, 11, 34, 45, 45 }, { 23, 1, 56, 12, 32, 23, 17, 12, 11, 34, 45, 37 }

		};
		ArrayList<ArrayList<Integer>> listaMatriz = practicas.convierteMatrizArrayLista(visitantesYear);
		
		
		
		
		
		
		
		
		String[] islas = { "GC", "LTE", "FTV", "TFE", "LPA", "GOM", "HIE" };
		String[] meses = { "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC" };

		System.out.println("********************************************************");
		 int isla=2; int mes =7; //System.out.println("Visitantes en "+ islas[isla]
		//+" : " + practicas.visitantesIslaYear(isla, visitantesYear));
		 System.out.println("Visitantes  en Canarias en el mes " + meses[mes]+ " : " +
		 practicas.visitantesMesYear(mes, visitantesYear));
		 System.out.println("********************************************************");

		/*
		 * String[] misDatos = { "123","abc", "345", "1x2", "990" }; //int[] res =
		 * practicas.convierteCadenasANumeros(misDatos); float saldo= 1000.0f; float[]
		 * movimientos= {10.0f, -5.0f,20.5f};
		 * 
		 * float saldoFinal = practicas.calculaSaldo(saldo, movimientos);
		 */
		int[] datos = practicas.generaAleatorios3(500, 1, 500);
		// practicas.ordenaEnteros(datos);
		// Arrays.sort(datos);
		String[] cadenas = { "perro", "gato", "alce" };
		practicas.ordenaCadenas(cadenas);
		int[] array2 = { 3, 6, 9, 9, 9, 15, 29 };
		int[] array1 = { 1, 2, 9, 9, 25, 39, 56, 67, 99 };
		int[] arrayMezclado = practicas.mezclaArrays(array1, array2);
		// liga
		String[][] goles = new Datos().getResultados();

		int[] clasificacion = practicas.obtenerClasificacion2(goles);
		String[] equipos = new Datos().getEquipos();
		practicas.ordenaClasificacion(clasificacion, equipos);
		for (int i = 0; i < equipos.length; i++) {
			//System.out.println(equipos[i] + " \t" + clasificacion[i]);

		}
		int[][] puntosJornadas = new Datos().getPuntosJornada();
		Equipo[] clasi = practicas.obtenerClasificacion3(puntosJornadas);
		String nif = "345239";
	//	System.out.println(practicas.validarNif(nif)?"OK":"KO");
		int x=67;
		//System.out.println("El numero "+ x + (practicas.esPrimo(x)?" ES ":" NO ES ")+ " PRIMO" );
		int[][] matriz = {
				{3,4,8},
				{6},
				{5,9}
		};
		Integer[][] matriz2 = {
				{3,4,null,8,null,12,37,null},
				{6,7,12,null,34,21,null,11},
				{5,null,9}
		};
	//	practicas.recorrerMatrizIrregularPorColumnas(matriz);
		//practicas.recorrerMatrizIrregularPorColumnas2(matriz2);
		//int[] primos = practicas.numerosPrimos(100);
		//int [] fibonacci = practicas.numerosFibonacci(20);
		//ArrayList<Estudiante> lista = practicas.introListas();
	//	practicas.listaEstudiantes(practicas.introListas());
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("12");
		lista.add("19");
		lista.add("-12");
		lista.add("1x2");
		//ArrayList<Integer> numeros = practicas.convierteCadenasANumeros(lista);
		 HashMap<String, Estudiante> resultado = practicas.introMapas();
		 
		 practicas.leerFicheroTexto();
		 String variable = "44536771F&&CARLOS HERNANDEZ PEREZ&&24091997&&M".split("&&")[1];
		 System.out.println(variable);

		 
		 //ArrayList<String> resultado5 = practicas.leerFicheroArrayList("ficheros/personas.txt");
		 //HashMap<String,String> resultado6 = practicas.leerFicheroHashMap("ficheros/personas.txt");
		 //practicas.escribirStringEnfichero("probando", "ficheros/GuardarPersonas.txt");
		 //HashMap<String,ArrayList<Float>> ventas = practicas.resumenVentasParcialesVendedor("ficheros/ventasDeptoAlmacen.txt");
		 HashMap<String,ArrayList<Float>> ventas = practicas.resumenVentasParcialesVendedor1("ficheros/ventasDeptoAlmacen.txt");
		 //practicas.leerFicheroTexto();
		 HashMap<String, Float> ventasPorVendedor = practicas.resumenVentasTotalesPorVendedor1(ventas);
		 HashMap<String, Float> resumenVentasVendedor = practicas.resumenVentasTotalesPorVendedor2(ventas);
		 System.out.println("**********>>>" + ventas);
		 System.out.println("**********>>>" + ventasPorVendedor);
		 System.out.println("**********>>>" + resumenVentasVendedor);
		 ArrayList<ArrayList<Integer>> enviarDatos = practicas.convierteMatrizArrayLista(matriz);
		 practicas.recorrerMatrizIrregularPorColumnas(enviarDatos);
		 System.out.println("------------------------");
		 practicas.recorrerMatrizIrregularPorColumnas(matriz);
		 
		 ArrayList<ArrayList<Integer>> prueba = new ArrayList<ArrayList<Integer>>();

		 //practicas.escribirObjetoEnFichero("ficheros/estudiantes.obj");
		 practicas.leerObjetoEnfichero("ficheros/estudiantes.obj");
		//*************************************************************************************
			ArrayList<Object> listaObjetos = new ArrayList<Object>();
			Estudiante est = new Estudiante(10, "111G", "Paco1", 'M', null, 181, null, null);
			Estudiante est1 = new Estudiante(20, "222G", "Paco2", 'M', null, 180, null, null);
			Estudiante est2 = new Estudiante(30, "333G", "Paco3", 'M', null, 180, null, null);
			listaObjetos.add(est);
			listaObjetos.add(est1);
			listaObjetos.add(est2);
			practicas.grabarArrayListEnFichero("ficheros/arraylist.obj", listaObjetos);
			
		//***********************************************************************************
			ArrayList<Object> listaObjetos2 = practicas.leerArrayListEnFichero("ficheros/arraylist.obj");	
			System.out.println(listaObjetos2);
		//***********************************************************************************

			 ArrayList<Object> resultado2 = practicas.leerFicheroTextoAtributosObjetos("ficheros/objetos.txt");

		//***********************************************************************************
			 Object[][] matrizPrueba=
				 {
				 { 1,true,"Pepe",12.56f, 'M',"20010312"},
				 { 2,false,"Maria",120.16f, 'F',"19970422"}
				 };

			 practicas.matrizObjetosAFichertoTexto(matrizPrueba,"ficheros/Datos.txt");
			 

				//***********************************************************************************		 
				Object[][] datos1 = {
		                {"3452FCN","1987-11-09",'A',5600,"43498107G"},
		                {"1455GHN","2016-10-19",'A',15900,"4448109K"},
				};
				for (int i = 0; i < datos1.length; i++) {

					String matricula = (String) datos1[i][0];
					LocalDate fecha = LocalDate.parse((CharSequence) datos1[i][1]);
					char tipo = (char) datos1[i][2];
					int precio = (int) datos1[i][3];
					String dni = (String) datos1[i][4];
					System.out.println(matricula + " " + fecha + " " + tipo + " " + precio + " " + dni);

				}
				ArrayList<HashMap<Integer, Integer>> resultado5 = practicas.leerFicherosIslasPorMes("ficheros/Turistas2016.txt");		
				
				//practicas.visitantesTotalesPorIsla(resultado5);
				HashMap<Integer, ArrayList<Float>> resultado6 = practicas.leerFicheroTextoVisitantes();
				//practicas.mostrarVisitantesTotalesPorIsla(resultado6);
				//practicas.mostrarVisitantesTotalesPorMes(resultado6);
				//practicas.mostrarVisitantesIslaMes(resultado6);
				//practicas.mostrarVisitantesIslaMes2(resultado6);
				//practicas.leerFicheroTextoOrdenado("ficheros/EstudiantesPorGrupo.txt");
				//practicas.escribirObjetoEnFichero("ficheros/estudiantes.txt","ficheros/estudiantes.obj");
				//practicas.leerObjetoEnfichero("ficheros/estudiantes.obj");
				//practicas.copiarObjetosEstudianteEnFicheroTxt("ficheros/estudiantes.obj", "ficheros/estudiantes2.txt");
				//practicas.leerFicheroTextoProvinciasComAutoYListarDatos();
				//practicas.leerFicheroTextoProvinciasComAutoYListarDatos2();
				//ArrayList<VehiculoOtro> listadoVehiculos = practicas.leerFicheroVehiculos("ficheros/vehiculos.txt");
				//practicas.grabarVehiculosEnFichero(listadoVehiculos);
				//practicas.leerObjetosVehiculosOtroYMostrarlos("ficheros/vehiculosOtros.obj");
				
				//************************** JDBC y MYSQL ************************

				AccesosTienda myAcceso = new AccesosTienda();
				
				//SQLite Local
/*			    String url = "jdbc:sqlite:c:/Users/JavierHS/git/CopiaMauricio/ficheros/TiendaInformatica.db";
			    String dbName = "";
			    String driver = "org.sqlite.JDBC";
			    String userName = "";
			    String password = "";
			    String tabla = "vacia";*/
				
				//MySQL en ordenador de Mauricio
/*			    String url = "jdbc:mysql://192.168.201.97:3306/";
			    String dbName = "tienda";
			    String driver = "com.mysql.jdbc.Driver";
			    String userName = "root";
			    String password = "123";
			    String tabla = "books";*/

				//MySQL en LocalHost
/*			    String url = "jdbc:mysql://localhost:3306/";
			    String dbName = "tiendainformatica";
			    String driver = "com.mysql.jdbc.Driver";
			    String userName = "root";
			    String password = "Gratis007";
			    String tabla = "Articulos";*/
			    
				//MySQL en LocalHost
			    String url = "jdbc:mysql://localhost:3306/";
			    String dbName = "information_schema";
			    String driver = "com.mysql.jdbc.Driver";
			    String userName = "root";
			    String password = "Gratis007";
			    String tabla = "INNODB_SYS_FOREIGN_COLS";
			    
			    //MySQL en Hostalia
/*			    String url = "jdbc:mysql://mysql377.srv-hostalia.com:3306/";
			    String dbName = "db5517321_educativa";
			    String driver = "com.mysql.jdbc.Driver";
			    String userName = "u5517321_01";
			    String password = "BU4959popi";*/
			    Connection conn = myAcceso.getConexion(url, dbName, userName, password, driver);
			    ArrayList<HashMap<String, Object>> todosLosDatos = myAcceso.getAllRecords(conn, tabla);
			    myAcceso.mostrarResulsetArrayListHashMap(todosLosDatos, tabla);

			    

			    
		 System.out.println("\nfin");
		
	}
	
//	************* ORDENAR HASHMAP POR clave (clasificacion es el hashmap) ****************
//	TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
//	LinkedHashMap<String, Integer> lm = new LinkedHashMap<String, Integer>();
//	Set<String> key = clasificacion.keySet();
//	for (String string : key) {
//		tm.put(string, clasificacion.get(string));	
//	}
	
	
//	************* ORDENAR HASHMAP POR VALOR ****************
//	List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(clasificacion.entrySet());
//	Comparator<Entry<String, Integer>> algo = new Comparator<Map.Entry<String, Integer>>() {
//		  public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
//		    return a.getValue().compareTo(b.getValue());
//		  }};
//	Collections.sort(entries, algo);
//	Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
//	for (Map.Entry<String, Integer> entry : entries) {
//	  lm.put(entry.getKey(), entry.getValue());
//	}

}
