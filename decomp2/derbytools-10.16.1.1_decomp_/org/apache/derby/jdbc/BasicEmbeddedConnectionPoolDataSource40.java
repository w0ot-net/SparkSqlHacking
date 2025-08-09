package org.apache.derby.jdbc;

import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import org.apache.derby.iapi.jdbc.EmbeddedConnectionPoolDataSourceInterface;
import org.apache.derby.impl.jdbc.EmbedPooledConnection;

public class BasicEmbeddedConnectionPoolDataSource40 extends BasicEmbeddedDataSource40 implements ConnectionPoolDataSource, EmbeddedConnectionPoolDataSourceInterface {
   private static final long serialVersionUID = 7852784308039674161L;

   public final PooledConnection getPooledConnection() throws SQLException {
      return this.createPooledConnection(this.getUser(), this.getPassword(), false);
   }

   public final PooledConnection getPooledConnection(String var1, String var2) throws SQLException {
      return this.createPooledConnection(var1, var2, true);
   }

   private PooledConnection createPooledConnection(String var1, String var2, boolean var3) throws SQLException {
      this.findDriver();
      return new EmbedPooledConnection(this, var1, var2, var3);
   }
}
