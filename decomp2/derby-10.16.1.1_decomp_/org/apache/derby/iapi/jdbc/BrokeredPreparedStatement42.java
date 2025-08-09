package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;

public class BrokeredPreparedStatement42 extends BrokeredPreparedStatement {
   public BrokeredPreparedStatement42(BrokeredStatementControl var1, String var2, Object var3) throws SQLException {
      super(var1, var2, var3);
   }

   public void setObject(int var1, Object var2, SQLType var3) throws SQLException {
      this.getPreparedStatement().setObject(var1, var2, var3);
   }

   public void setObject(int var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.getPreparedStatement().setObject(var1, var2, var3, var4);
   }
}
