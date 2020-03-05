package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void test() throws SQLException {
		Connection db = Database.getConnection();
		assertNotNull(db);
	}

}
