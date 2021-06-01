package logicaPersistencia.accesoBD;

import com.mysql.jdbc.CommunicationsException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pool {
	
	public Pool() {
    }
	
	 public ConnectionPool getPool(String servidor, String db, String usuario, String contrasena) throws CommunicationsException, SQLException {

	        return getConnectionPool(servidor, db, usuario, contrasena);
	    }

	    private ConnectionPool getConnectionPool(String servidor, String db, String usuario, String contrasena) throws CommunicationsException, SQLException {
	        ConnectionPool connectionpool = new ConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://" + servidor + "/" + db, usuario, contrasena, 10, 40, true);
	        return connectionpool;
	    }
	
	    
	    public void Ejemplo() throws SQLException  {
	        
	        ConnectionPool Pool = getPool("caucana.com","nombre_base_datos","usuari_base_datos","contrasena");
	        Connection conexion = Pool.getConnection();
	        Statement sql       = conexion.createStatement();
	        ResultSet rs        = sql.executeQuery("show tables");
	        while (rs.next()) {
	            System.out.println(rs.getString(1));
	        }
	        Pool.free(conexion);
	        System.out.println(Pool.totalConnections());
	        
	    }

}
