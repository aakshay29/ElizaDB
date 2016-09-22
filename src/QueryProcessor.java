import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryProcessor {
	Random rnd = new Random();
	String query = null;

	public String getAnswer(String query, String name) {
		String answer = null;
		int randomInt = 1 + rnd.nextInt(2);
		String delim = " ";
		String[] parts = query.split(delim);

		if (randomInt == 1) {
			query = "SELECT * FROM (SELECT * FROM ElizaCommands ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum=1 AND categoryID = 2";
			answer = getCommand(query);
		} else if (randomInt == 2) {
			String temp = "";
			for (int i = 0; i < parts.length; i++) {
				String replacedString = getReplacement(parts[i]);
				if (replacedString != null) {
					parts[i] = replacedString;
				}
				temp += parts[i] + " ";
			}
			query = "SELECT * FROM (SELECT * FROM ElizaCommands ORDER BY DBMS_RANDOM.RANDOM) WHERE rownum=1 AND categoryID = 3";
			answer = getCommand(query) + temp;
		}
		for (int j = 0; j < parts.length; j++) {
			if (parts[j].equalsIgnoreCase("sad")) {
				answer = getKeyword("sad");
			}
			if (parts[j].equalsIgnoreCase("gloomy")) {
				answer = getKeyword("gloomy");
			}
			if (parts[j].equalsIgnoreCase("bad")) {
				answer = getKeyword("bad");
			}
			if (parts[j].equalsIgnoreCase("happy")) {
				answer = getKeyword("happy");
			}
			if (parts[j].equalsIgnoreCase("life")) {
				answer = getKeyword("life");
			}
			if (parts[j].equalsIgnoreCase("run")) {
				answer = getKeyword("run");
			}
			if (parts[j].equalsIgnoreCase("yeah") || parts[j].equalsIgnoreCase("yes")
					|| parts[j].equalsIgnoreCase("yup")) {
				answer = getKeyword("yeah");
			}
		}
		return answer;
	}

	public static String getCommand(String sql) {
		String returnString = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnString = rs.getString("data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}

	public static String getReplacement(String value) {
		String returnString = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ElizaReplacements where data1='" + value + "'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnString = rs.getString("data2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}
	
	public static String getKeyword(String value) {
		String returnString = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ElizaKeywords where keyword='" + value + "'";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				returnString = rs.getString("data");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return returnString;
	}
}
