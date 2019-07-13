package nl.hu.v1wac.wacapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserPostgresDaoImpl extends PostgresBaseDao implements UserDao {
	public String findRoleForUser(String name, String pass) {
		String result = null;
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement("select * from useraccount "
					+ "where username = '" + name + "' and password = '" + pass + "'");
			ResultSet dbResultSet = pstmt.executeQuery();
			
			while (dbResultSet.next()) {
				result = dbResultSet.getString("role");
			}
		} catch (SQLException sqle) { sqle.printStackTrace(); }
		
		return result;
	}
}