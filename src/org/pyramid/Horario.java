/**
 * @author Pavlo Glez
 */
package org.pyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.ResultSetMetaData;

public class Horario {

	private final String YUAREL = "jdbc:mysql://URL:3306/tecbd";
	private final String PSSW = "app";
	private final String USR = "proyectohawk";
	private final String PSTM = "SELECT Nombre,";
	private final String MTSP = ",Aula FROM materia NATURAL JOIN cursando WHERE cursando.nocontrol = '";
	private final String TSPM = "' && cursando.edo = '1' ORDER BY Lunes";

	public String[] horas(final String nocontrol, final Integer pos,
			final String dia) {
		String[] elbueno = new String[3];
		String[][] datos;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(YUAREL, PSSW, USR);
			PreparedStatement statement = con.prepareStatement(PSTM + dia
					+ MTSP + nocontrol + TSPM);
			ResultSet result = statement.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			int size = 0;
			result.last();
			size = result.getRow();
			numberOfColumns += 1;
			// result.first();
			result.beforeFirst();
			datos = new String[size][numberOfColumns];

			int v = 0;

			while (result.next()) {
				int c = 0;
				for (int y = 1; y < numberOfColumns; y++) {

					if (result.getString(y) != null)
						datos[v][c] = result.getString(y);

					c++;
				}
				v++;
				/*lo se falta cerrar la conexión de la B.D. este no es el codigo final :)
				no te iba a dar  todo hecho ;)*/

			}
			elbueno = datos[pos];

			return elbueno;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
