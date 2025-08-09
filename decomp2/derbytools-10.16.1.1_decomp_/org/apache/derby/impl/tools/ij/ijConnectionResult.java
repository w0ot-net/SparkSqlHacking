package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;

class ijConnectionResult extends ijResultImpl {
   Connection conn;

   ijConnectionResult(Connection var1) {
      this.conn = var1;
   }

   public boolean isConnection() {
      return true;
   }

   public Connection getConnection() {
      return this.conn;
   }

   public SQLWarning getSQLWarnings() throws SQLException {
      return this.conn.getWarnings();
   }

   public void clearSQLWarnings() throws SQLException {
      this.conn.clearWarnings();
   }
}
