package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class AsyncStatement extends Thread {
   Connection conn;
   String stmt;
   ijResult result;

   AsyncStatement(Connection var1, String var2) {
      this.conn = var1;
      this.stmt = var2;
   }

   public void run() {
      Statement var1 = null;

      try {
         var1 = this.conn.createStatement();
         var1.execute(this.stmt);
         this.result = new ijStatementResult(var1, true);
      } catch (SQLException var5) {
         this.result = new ijExceptionResult(var5);
         if (var1 != null) {
            try {
               var1.close();
            } catch (SQLException var4) {
            }
         }
      }

      Object var7 = null;
   }

   ijResult getResult() {
      return this.result;
   }
}
