/**
 * @author Pavlo Glez
 */

package org.pyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.ResultSetMetaData;

public class SemestreInd {

	private final String YUAREL = "jdbc:mysql://192.168.1.90:3306/tecbd";
	private final String PSSW = "app";
	private final String USR = "proyectohawk";
	private final String PSTM = "SELECT Nombre,Prom FROM materia NATURAL JOIN cursando WHERE cursando.nocontrol = '";
	private final String MTSP = "' && cursando.edo = '0' && Sem = '";

	public String[] Kardex(final String nocontrol, final Integer pos,
			final String sems) {

		String[][] datos;
		String[] elbueno;
		// ArrayList<HashMap<String, String>> arre = new ArrayList<>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(YUAREL, PSSW, USR);
			PreparedStatement statement = con.prepareStatement(PSTM + nocontrol
					+ MTSP + sems + "' ");
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
				for (int y = 1; y < numberOfColumns; y++) {

					datos[v][c] = result.getString(y);
					c++;
				}
				v++;

			}
			elbueno = datos[pos];

			return elbueno;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}