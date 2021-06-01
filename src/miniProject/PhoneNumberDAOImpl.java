package miniProject;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneNumberDAOImpl implements PhoneNumberDAO{
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"C##bituser", "bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("로드 실패");
		}
		
		return conn;
	}

	@Override
	public List<PhoneNumberVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PhoneNumberVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id, name, hp, tel FROM phonebook ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long char_id = rs.getLong(1);
				String char_name = rs.getString(2);
				String char_hp = rs.getString(3);
				String char_tel = rs.getString(4);
				
				PhoneNumberVO vo = new PhoneNumberVO(char_id, char_name, char_hp, char_tel);
				list.add(vo);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public List<PhoneNumberVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PhoneNumberVO> list = new ArrayList<>();
		
		String sql = "SELECT id, name, hp, tel FROM phonebook WHERE name LIKE ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+keyword+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long char_id = rs.getLong("id");
				String char_name = rs.getString("name");
				String char_hp = rs.getString("hp");
				String char_tel = rs.getString("tel");
				
				PhoneNumberVO vo = new PhoneNumberVO(char_id, char_name, char_hp, char_tel);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return list;
	}

	@Override
	public boolean insert(PhoneNumberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "INSERT INTO phonebook VALUES(phonebook_id.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getChar_name());
			pstmt.setString(2, vo.getChar_hp());
			pstmt.setString(3, vo.getChar_tel());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(Long char_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "DELETE FROM phonebook WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, char_id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		return 1 == deletedCount;
	}

	@Override
	public PhoneNumberVO get(Long char_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PhoneNumberVO vo = null;
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phonebook WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, char_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Long id = rs.getLong(1);
				String char_name = rs.getString(2);
				String char_hp = rs.getString(3);
				String char_tel = rs.getString(4);
				
				vo = new PhoneNumberVO(id, char_name, char_hp, char_tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return vo;
	}

	
	
}
