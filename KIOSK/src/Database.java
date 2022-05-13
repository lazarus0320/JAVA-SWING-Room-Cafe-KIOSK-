import java.sql.*;

public class Database {
	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://192.168.1.130:3307/db_study";
	String user = "min0321";
	String pass = "369369";
	
	Database() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			stmt = con.createStatement();
			System.out.println("MySQL server connection success!");
		} catch(Exception e) {
			System.out.println("MySQL server connection failed..." + e.toString());
		}
	}
	
	boolean loginCheck(String _i, String _p) {
		boolean flag = false;
		
		String id = _i;
		String pw = _p;
		
		try {
			String checkingStr = "SELECT userPass FROM usertable WHERE userId='" + id + "'";
			ResultSet result = stmt.executeQuery(checkingStr);
			
			while (result.next()) {
				if(pw.equals(result.getString("userPass"))) {
					flag = true;
					System.out.println("로그인 성공");
				}
				
				else {
					flag = false;
					System.out.println("로그인 실패");
				}
			}
		
		} catch(Exception e) {
			flag = false;
			System.out.println("로그인 실패 > " + e.toString());
		}
		return flag;
	}
}
