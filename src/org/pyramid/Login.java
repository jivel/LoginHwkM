/**
 * @author Pavlo Glez
 */

package org.pyramid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {

	private final String YUAREL = "jdbc:mysql://192.168.1.90:3306/tecbd";
	private final String PSSW = "app";
	private final String USR = "proyectohawk";
	private final String PSTM = "SELECT nocontrol, Nombre, nip FROM alumno WHERE nocontrol = '";
	private final String MTSP = "' && nip = '";

	public String authentication(final String nocontrol, final String nip) {
		String retrievedUserName = "";
		String retrievedPassword = "";

		String nom = "";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(YUAREL, PSSW, USR);
			PreparedStatement statement = con.prepareStatement(PSTM + nocontrol
					+ MTSP + nip + "'");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				retrievedUserName = result.getString("nocontrol");
				retrievedPassword = result.getString("nip");
				nom = result.getString("Nombre");
			}
			if (retrievedUserName.equals("") && retrievedPassword.equals(""))

			{
				nom = null;
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nom;

	}

	public String state() {
		return "1";
	}

}