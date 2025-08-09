package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;

public class EmbedCallableStatement42 extends EmbedCallableStatement {
   public EmbedCallableStatement42(EmbedConnection var1, String var2, int var3, int var4, int var5) throws SQLException {
      super(var1, var2, var3, var4, var5);
   }

   public void registerOutParameter(int var1, SQLType var2) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2));
   }

   public void registerOutParameter(int var1, SQLType var2, int var3) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2), var3);
   }

   public void registerOutParameter(int var1, SQLType var2, String var3) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2), var3);
   }

   public void registerOutParameter(String var1, SQLType var2) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2));
   }

   public void registerOutParameter(String var1, SQLType var2, int var3) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2), var3);
   }

   public void registerOutParameter(String var1, SQLType var2, String var3) throws SQLException {
      this.checkStatus();
      this.registerOutParameter(var1, Util42.getTypeAsInt(var2), var3);
   }

   public void setObject(int var1, Object var2, SQLType var3) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3));
   }

   public void setObject(int var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3), var4);
   }

   public void setObject(String var1, Object var2, SQLType var3) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3));
   }

   public void setObject(String var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3), var4);
   }
}
