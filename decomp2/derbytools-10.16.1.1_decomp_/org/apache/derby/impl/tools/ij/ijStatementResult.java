package org.apache.derby.impl.tools.ij;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

class ijStatementResult extends ijResultImpl {
   Statement statement;
   boolean closeWhenDone;

   ijStatementResult(Statement var1, boolean var2) {
      this.statement = var1;
      this.closeWhenDone = var2;
   }

   public boolean isStatement() {
      return true;
   }

   public boolean isResultSet() throws SQLException {
      return this.statement.getUpdateCount() == -1;
   }

   public boolean isUpdateCount() throws SQLException {
      return this.statement.getUpdateCount() >= 0;
   }

   public Statement getStatement() {
      return this.statement;
   }

   public int getUpdateCount() throws SQLException {
      return this.statement.getUpdateCount();
   }

   public ResultSet getResultSet() throws SQLException {
      return this.statement.getResultSet();
   }

   public void closeStatement() throws SQLException {
      if (this.closeWhenDone) {
         this.statement.close();
      }

   }

   public SQLWarning getSQLWarnings() throws SQLException {
      return this.statement.getWarnings();
   }

   public void clearSQLWarnings() throws SQLException {
      this.statement.clearWarnings();
   }
}
