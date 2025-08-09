package org.apache.derby.impl.tools.ij;

import java.sql.SQLWarning;

class ijWarningResult extends ijResultImpl {
   SQLWarning warn;

   ijWarningResult(SQLWarning var1) {
      this.warn = var1;
   }

   public SQLWarning getSQLWarnings() {
      return this.warn;
   }

   public void clearSQLWarnings() {
      this.warn = null;
   }
}
