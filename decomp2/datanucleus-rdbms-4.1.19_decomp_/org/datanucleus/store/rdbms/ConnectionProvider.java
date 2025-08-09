package org.datanucleus.store.rdbms;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public interface ConnectionProvider {
   void setFailOnError(boolean var1);

   Connection getConnection(DataSource[] var1) throws SQLException;
}
