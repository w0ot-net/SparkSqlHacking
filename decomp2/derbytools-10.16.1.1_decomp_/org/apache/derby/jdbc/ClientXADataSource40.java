package org.apache.derby.jdbc;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.ShardingKeyBuilder;
import javax.sql.XADataSource;

/** @deprecated */
public class ClientXADataSource40 extends ClientXADataSource implements XADataSource {
   private static final long serialVersionUID = -3463444509507830926L;

   public ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }
}
