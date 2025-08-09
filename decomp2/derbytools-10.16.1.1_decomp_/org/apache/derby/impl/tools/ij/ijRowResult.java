package org.apache.derby.impl.tools.ij;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class ijRowResult extends ijResultImpl {
   ResultSet rowResult;
   boolean hadRow;

   public ijRowResult(ResultSet var1, boolean var2) {
      this.rowResult = var1;
      this.hadRow = var2;
   }

   public boolean isNextRowOfResultSet() {
      return true;
   }

   public ResultSet getNextRowOfResultSet() {
      return this.hadRow ? this.rowResult : null;
   }

   public SQLWarning getSQLWarnings() throws SQLException {
      return this.rowResult.getWarnings();
   }

   public void clearSQLWarnings() throws SQLException {
      this.rowResult.clearWarnings();
   }
}
