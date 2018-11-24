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
	
	public Connection getConexion(String dominio, String db, String usr, String clave, String driver) {
		
		Connection conn = null;
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
/*			if (getRowCount(rs)==0) {
				System.out.println("NO HAY DATOS");
				stm.close();
				rs.close();
				return null;
			}*/
			while (rs.next()) {
				if (rs.getRow() == 0) break;
				HashMap<String, Object> datosUnaLinea = new HashMap<String,Object>();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					datosUnaLinea.put(metaData.getColumnName(i), rs.getObject(i));
					//datosUnaLinea.put(metaData.getColumnName(i), rs.getString(i)); //Hace lo mismo pero con otro método
					//System.out.println(rs.getString(i));					
				}
				registros.add(datosUnaLinea);
			}
/*
			System.out.println("getColumnLabel   " + metaData.getColumnLabel(2));
			System.out.println("getColumnName    " + metaData.getColumnName(2));
			System.out.println("getColumnTypeName   " + metaData.getColumnTypeName(2));
			System.out.println("getColumnType   " + metaData.getColumnType(2));
			System.out.println("getColumnDisplaySize   " + metaData.getColumnDisplaySize(2));
			System.out.println("getColumnCount   " + metaData.getColumnCount());	
			System.out.println("getPrecision   " + metaData.getPrecision(2));	
			System.out.println("getScale   " + metaData.getScale(2));	
			System.out.println("getSchemaName   " + metaData.getSchemaName(2));	
			System.out.println("getTableName   " + metaData.getTableName(2));	
			System.out.println("isSearchable   " + metaData.isSearchable(2));	
			System.out.println("getColumnClassName    " + metaData.getColumnClassName(2));
			System.out.println("getCatalogName    " + metaData.getCatalogName(2)); //nombre de la BBDD

			*/
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return registros;
	}
	
	
	public void mostrarResulsetArrayListHashMap(ArrayList<HashMap<String,Object>> datos, String tabla) {
		if (datos == null) return;
		System.out.println();
		System.out.println("\033[4;37;44m*************** TABLA : " + tabla + "**************************************\033[0m ");
		Set<String> unRegistro = datos.get(0).keySet();
		String[] nombreColumnasTabla = new String[unRegistro.size()];
		int indice = 0;
		for ( String unCampo : unRegistro) {			
			System.out.printf("  \033[0;40;33m %-25s", unCampo + "\033[0m");
			nombreColumnasTabla[indice++] = unCampo;
		}
		System.out.println();
		for (int i = 0; i < datos.size(); i++) {	
			for (int j = 0; j < datos.get(i).size(); j++) {
				System.out.printf("%-25s", datos.get(i).get(nombreColumnasTabla[j]));
			}		
			System.out.println("");
		}
	}

		

}
