package org.apache.derby.impl.load;

import java.sql.SQLException;
import java.util.HashMap;
import org.apache.derby.vti.VTIMetaDataTemplate;

class ImportResultSetMetaData extends VTIMetaDataTemplate {
   private final int numberOfColumns;
   private final String[] columnNames;
   private final int[] columnWidths;
   private final int[] tableColumnTypes;
   private final String[] columnTypeNames;
   private final HashMap udtClasses;

   public ImportResultSetMetaData(int var1, String[] var2, int[] var3, int[] var4, String[] var5, HashMap var6) {
      this.numberOfColumns = var1;
      this.columnNames = var2;
      this.columnWidths = var3;
      this.tableColumnTypes = var4;
      this.columnTypeNames = var5;
      this.udtClasses = var6;
   }

   public int getColumnCount() {
      return this.numberOfColumns;
   }

   public String getColumnName(int var1) {
      return this.columnNames[var1 - 1];
   }

   public int getColumnType(int var1) {
      short var2;
      switch (this.tableColumnTypes[var1 - 1]) {
         case -4 -> var2 = -4;
         case -3 -> var2 = -3;
         case -2 -> var2 = -2;
         case 2000 -> var2 = 2000;
         case 2004 -> var2 = 2004;
         case 2005 -> var2 = 2005;
         default -> var2 = 12;
      }

      return var2;
   }

   public int isNullable(int var1) {
      return 2;
   }

   public int getColumnDisplaySize(int var1) {
      return this.columnWidths == null ? 32672 : this.columnWidths[var1 - 1];
   }

   public String getColumnTypeName(int var1) throws SQLException {
      return this.columnTypeNames[var1 - 1];
   }

   Class getUDTClass(int var1) throws SQLException {
      this.getColumnName(var1);
      return (Class)this.udtClasses.get(this.getColumnName(var1));
   }
}
