package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class AccesosTienda {
	
	private String url;
	private String usr;
	private String clave;
	private String driver;

	
	public Connection getConexion(String dominio, String db, String usr, String clave, String driver) {
		
		Connection conn = null;
		Statement stmt = null;
		try {
			//Registro el driver JDBC
			Class.forName(driver);
			
			//Abrimos una coneccion a la Base de Datos
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(dominio+db,usr,clave);
			
	      
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
	
		return conn;	
	}
	
	public ArrayList<HashMap<String,Object>> getAllRecords(Connection conn, String tabla) { //cada indice del ArrayList contiene una fila de la BBDD con todas sus columnas
		ArrayList<HashMap<String,Object>> registros = new ArrayList<HashMap<String,Object>>();
		
		try {
			
			String sql = "SELECT * FROM " + tabla;
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			
			while (rs.next()) {
				HashMap<String, Object> datosUnaLinea = new HashMap<String,Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					datosUnaLinea.put(metaData.getColumnName(i), rs.getObject(i));
					System.out.println(rs.getObject(i));
					
				}
				registros.add(datosUnaLinea);
			}
			System.out.println(metaData.getColumnLabel(2));
			System.out.println(metaData.getColumnTypeName(2));
			System.out.println(metaData.getColumnDisplaySize(2));
			System.out.println(metaData.getColumnName(2));
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registros;
	}
	
	
	public void mostrarResulsetArrayListHashMap(ArrayList<HashMap<String,Object>> datos) {
		
		float acumulador2 = 0;
		float[] acumuladorVisitantesMes = new float[12];
		System.out.print("\t");

		for (HashMap<String,Object> registro : datos) {
			Set<String> nombreColumnas = registro.keySet();
			System.out.print("\t" + nombreColumnas);
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

}
