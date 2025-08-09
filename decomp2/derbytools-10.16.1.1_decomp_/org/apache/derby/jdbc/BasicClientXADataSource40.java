package org.apache.derby.jdbc;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.ShardingKeyBuilder;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import org.apache.derby.client.ClientXADataSourceInterface;

public class BasicClientXADataSource40 extends BasicClientDataSource40 implements ClientXADataSourceInterface, XADataSource {
   public static final String className__ = "org.apache.derby.jdbc.BasicClientXADataSource40";
   private static final long serialVersionUID = 7057075094707674881L;

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
