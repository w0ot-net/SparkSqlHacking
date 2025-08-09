package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.ShardingKeyBuilder;
import javax.sql.XADataSource;

public interface EmbeddedXADataSourceInterface extends EmbeddedDataSourceInterface, XADataSource {
   ResourceAdapter getResourceAdapter();

   default ShardingKeyBuilder createShardingKeyBuilder() throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }
}
