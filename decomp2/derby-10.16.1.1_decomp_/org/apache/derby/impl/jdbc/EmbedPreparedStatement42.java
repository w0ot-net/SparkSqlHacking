package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;

public class EmbedPreparedStatement42 extends EmbedPreparedStatement {
   public EmbedPreparedStatement42(EmbedConnection var1, String var2, boolean var3, int var4, int var5, int var6, int var7, int[] var8, String[] var9) throws SQLException {
      super(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public void setObject(int var1, Object var2, SQLType var3) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3));
   }

   public void setObject(int var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.checkStatus();
      this.setObject(var1, var2, Util42.getTypeAsInt(var3), var4);
   }
}
