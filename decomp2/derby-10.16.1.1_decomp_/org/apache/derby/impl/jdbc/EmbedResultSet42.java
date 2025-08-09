package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.sql.SQLType;
import org.apache.derby.iapi.sql.ResultSet;

public class EmbedResultSet42 extends EmbedResultSet {
   public EmbedResultSet42(EmbedConnection var1, ResultSet var2, boolean var3, EmbedStatement var4, boolean var5) throws SQLException {
      super(var1, var2, var3, var4, var5);
   }

   public void updateObject(int var1, Object var2, SQLType var3) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(var1, var2, Util42.getTypeAsInt(var3));
   }

   public void updateObject(int var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(var1, var2, Util42.getTypeAsInt(var3));
      this.adjustScale(var1, var4);
   }

   public void updateObject(String var1, Object var2, SQLType var3) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(var1, var2, Util42.getTypeAsInt(var3));
   }

   public void updateObject(String var1, Object var2, SQLType var3, int var4) throws SQLException {
      this.checkIfClosed("updateObject");
      this.updateObject(this.findColumnName(var1), var2, var3, var4);
   }
}
