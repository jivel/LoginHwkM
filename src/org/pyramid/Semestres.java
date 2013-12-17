/**
 * @author Pavlo Glez
 */
package org.pyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Semestres {

	public String[] Kardex(String nocontrol) {

		String[] datos;
		final String YUAREL = "jdbc:mysql://SOCKET/tecbd";
		final String PSSW = "app";
		final String USR = "proyectohawk";
		final String PSTM = "SELECT DISTINCT Sem FROM cursando WHERE nocontrol = '";
		final String MTSP = "' && Edo = '0'";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(YUAREL, PSSW, USR);
			PreparedStatement statement = con.prepareStatement(PSTM + nocontrol
					+ MTSP);
			ResultSet result = statement.executeQuery();

			int size = 0;
			result.last();
			size = result.getRow();
			result.beforeFirst();
			datos = new String[size];

			int v = 0;

			while (result.next()) {

				datos[v] = result.getString(1);

				v++;
			}

			return datos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
