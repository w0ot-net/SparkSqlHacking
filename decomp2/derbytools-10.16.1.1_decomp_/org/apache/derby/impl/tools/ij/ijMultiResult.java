package org.apache.derby.impl.tools.ij;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

class ijMultiResult extends ijResultImpl {
   private Statement statement;
   private ResultSet rs;
   boolean closeWhenDone;

   ijMultiResult(Statement var1, ResultSet var2, boolean var3) {
      this.statement = var1;
      this.rs = var2;
      this.closeWhenDone = var3;
   }

   public boolean isMulti() {
      return true;
   }

   public Statement getStatement() {
      return this.statement;
   }

   public ResultSet getResultSet() {
      return this.rs;
   }

   public void closeStatement() throws SQLException {
      if (this.closeWhenDone) {
         this.statement.close();
      }

   }

   public SQLWarning getSQLWarnings() {
      return null;
   }

   public void clearSQLWarnings() {
   }
}
