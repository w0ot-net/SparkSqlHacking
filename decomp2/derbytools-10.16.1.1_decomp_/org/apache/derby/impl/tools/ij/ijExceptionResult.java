package org.apache.derby.impl.tools.ij;

import java.sql.SQLException;
import java.sql.SQLWarning;

class ijExceptionResult extends ijResultImpl {
   SQLException except;

   ijExceptionResult(SQLException var1) {
      this.except = var1;
   }

   public boolean isException() {
      return true;
   }

   public SQLException getException() {
      return this.except;
   }

   public SQLWarning getSQLWarnings() {
      return null;
   }

   public void clearSQLWarnings() {
   }
}
