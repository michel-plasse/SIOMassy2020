package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Test;

public class DateTimeTest {

  /**
   * Pour passer un LocalDateTime en SQL DateTime :
   * Timestamp.valueOf(leLocalDateTime)
   */
	@Test
	public void testJava2Sql() throws SQLException {
    System.out.println("java -> sql");
    // Prenons un instant : 23/03/2020 15:10
    LocalDateTime instant = LocalDateTime.of(2020, Month.MARCH, 23, 15, 10);
    // Vérifions
    String sql = "SELECT '2020-03-23 15:10:00' = ? AS identique"; 
		Connection db = Database.getConnection();
    PreparedStatement ps = db.prepareStatement(sql);
    ps.setTimestamp(1, Timestamp.valueOf(instant));
    ResultSet rs = ps.executeQuery();
    rs.next();
    assertEquals(true, rs.getBoolean("identique"));
  }

  /**
   * Pour récupérer un SQL DateTime en LocalDateTime :
   * rs.getTimestamp(nomColonneDateTime).toLocalDateTime()
   */
	@Test
	public void testSql2Java() throws SQLException {
    System.out.println("sql -> java");
    // Prenons un instant : 23/03/2020 15:10
    LocalDateTime instant = LocalDateTime.of(2020, Month.MARCH, 23, 15, 10);
    // Vérifions
    String sql = "SELECT '2020-03-23 15:10:00' AS maDate"; 
		Connection db = Database.getConnection();
    PreparedStatement ps = db.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
    rs.next();
    assertEquals(instant, rs.getTimestamp("maDate").toLocalDateTime());
  }
}
