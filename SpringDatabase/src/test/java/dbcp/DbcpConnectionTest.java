package dbcp;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DbcpConnectionTest {
	
	HikariConnectionPool dbcp = new HikariConnectionPool();
	
	@Test
	public void getDataSourceTest() {
		HikariDataSource ds = dbcp.getHikariDataSource();
		
		assertNotNull(ds);
		log.info(ds);
		
		log.info("Hikari max pool size:" + ds.getMaximumPoolSize());
		log.info("Idle timeout:" + ds.getIdleTimeout());
		log.info("Hikari Minimunm idle size:" + ds.getMinimumIdle());
		
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employees");
			ResultSet rs = pstmt.executeQuery();
		){
			while (rs.next()) {
				log.info("{} {} / {}", 
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("job_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
