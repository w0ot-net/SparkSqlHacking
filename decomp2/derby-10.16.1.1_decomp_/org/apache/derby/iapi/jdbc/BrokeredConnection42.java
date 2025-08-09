package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;

public class BrokeredConnection42 extends BrokeredConnection {
   public BrokeredConnection42(BrokeredConnectionControl var1) throws SQLException {
      super(var1);
   }

   public final BrokeredPreparedStatement newBrokeredStatement(BrokeredStatementControl var1, String var2, Object var3) throws SQLException {
      try {
         return new BrokeredPreparedStatement42(var1, var2, var3);
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw var5;
      }
   }

   public BrokeredCallableStatement newBrokeredStatement(BrokeredStatementControl var1, String var2) throws SQLException {
      try {
         return new BrokeredCallableStatement42(var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }
}
