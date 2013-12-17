/**
 * @author Pavlo Glez
 */

package org.pyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.ResultSetMetaData;

public class boleta {

	private final String YUAREL = "jdbc:mysql://SOCKET/tecbd";
	private final String PSSW = "app";
	private final String USR = "proyectohawk";
	private final String PSTM = "SELECT Nombre,Un1,Un2,Un3,Un4,Un5,Un6,Un7,Un8,Un9 FROM materia NATURAL JOIN cursando WHERE cursando.nocontrol = '";
	private final String MTSP = "' && cursando.edo = '1'";

	public String[] consulta(final String nocontrol, final Integer pos) {

		String[][] datos;
		String[] elbueno;
		// ArrayList<HashMap<String, String>> arre = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(YUAREL, PSSW, USR);
			final PreparedStatement statement = con.prepareStatement(PSTM
					+ nocontrol + MTSP);
			ResultSet result = statement.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			numberOfColumns += 1; // CHECAR ESTO PUEDE QUE ESTE SACANDO EL NULL
									// DEL FINAL
			int size = 0;
			result.last();
			size = result.getRow();
			// result.first();
			result.beforeFirst();
			datos = new String[size][numberOfColumns];
			elbueno = new String[numberOfColumns];
			// datos[0][0] = result.getString(1);
			int v = 0;
			while (result.next()) {
				int c = 0;
				for (int y = 1; y < 11; y++) {

					if (result.getString(y) != null)
						datos[v][c] = result.getString(y);
					c++;
				}
				v++;

			}
			elbueno = datos[pos];
			// guardando en arraylist
			/*
			 * HashMap<String, String> data = new HashMap<String, String>();
			 * for(int y=1;y<numberOfColumns;y++){
			 * 
			 * 
			 * data.put(rsmd.getColumnName(y), result.getString(y));
			 * 
			 * }
			 * 
			 * arre.add(data);
			 */

			return elbueno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
