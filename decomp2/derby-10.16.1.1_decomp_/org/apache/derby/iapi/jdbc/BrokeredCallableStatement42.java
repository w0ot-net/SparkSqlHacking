package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;

public class BrokeredCallableStatement42 extends BrokeredCallableStatement {
   public BrokeredCallableStatement42(BrokeredStatementControl var1, String var2) throws SQLException {
      super(var1, var2);
   }

   public void registerOutParameter(int var1, SQLType var2) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2);
   }

   public void registerOutParameter(int var1, SQLType var2, int var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public void registerOutParameter(int var1, SQLType var2, String var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public void registerOutParameter(String var1, SQLType var2) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2);
   }

   public void registerOutParameter(String var1, SQLType var2, int var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public void registerOutParameter(String var1, SQLType var2, String var3) throws SQLException {
      this.getCallableStatement().registerOutParameter(var1, var2, var3);
   }

   public void setObject(int var1, Object var2, SQLType var3) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3);
   }

   public void setObject(int var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3, var4);
   }

   public void setObject(String var1, Object var2, SQLType var3) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3);
   }

   public void setObject(String var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.getCallableStatement().setObject(var1, var2, var3, var4);
   }
}
