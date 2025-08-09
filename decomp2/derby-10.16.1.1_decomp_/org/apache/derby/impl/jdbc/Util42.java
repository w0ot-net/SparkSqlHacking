package org.apache.derby.impl.jdbc;

import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.SQLType;

public class Util42 {
   public static int getTypeAsInt(SQLType var0) throws SQLException {
      if (var0 instanceof JDBCType) {
         int var1 = ((JDBCType)var0).getVendorTypeNumber();
         Util.checkForSupportedDataType(var1);
         return var1;
      } else {
         throw Util.generateCsSQLException("0A000.S.7", var0);
      }
   }
}
