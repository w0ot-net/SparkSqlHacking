package org.apache.derby.jdbc;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.ShardingKeyBuilder;
import javax.sql.XAConnection;
import org.apache.derby.client.ClientXADataSourceInterface;

public class ClientXADataSource extends ClientDataSource implements ClientXADataSourceInterface {
   public static final String className__ = "org.apache.derby.jdbc.ClientXADataSource";
   private static final long serialVersionUID = 7057075094707674880L;

   public XAConnection getXAConnection() throws SQLException {
      return this.getXAConnectionMinion();
   }

   public XAConnection getXAConnection(String var1, String var2) throws SQLException {
      return this.getXAConnectionMinion(var1, var2);
   }

   public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }
}
