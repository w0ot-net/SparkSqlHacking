package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.util.IdUtil;

public abstract class XPLAINTableDescriptor {
   private String tableInsertStmt;

   public abstract String getCatalogName();

   protected abstract SystemColumn[] buildColumnList();

   public String[] getTableDDL(String var1) {
      String var2 = IdUtil.normalToDelimited(var1);
      String var3 = IdUtil.normalToDelimited(this.getCatalogName());
      SystemColumn[] var4 = this.buildColumnList();
      StringBuffer var5 = new StringBuffer();
      StringBuffer var6 = new StringBuffer();
      StringBuffer var7 = new StringBuffer();

      for(int var8 = 0; var8 < var4.length; ++var8) {
         if (var8 == 0) {
            var5.append("(");
            var6.append("(");
            var7.append("(");
         } else {
            var5.append(",");
            var6.append(",");
            var7.append(",");
         }

         var5.append(var4[var8].getName());
         var6.append(var4[var8].getName());
         var7.append("?");
         var5.append(" ");
         var5.append(var4[var8].getType().getCatalogType().getSQLstring());
      }

      var5.append(")");
      var6.append(")");
      var7.append(")");
      String var9 = "create table " + var2 + "." + var3 + var5.toString();
      this.tableInsertStmt = "insert into " + var2 + "." + var3 + var6.toString() + " values " + var7.toString();
      return new String[]{var9};
   }

   public String getTableInsert() {
      return this.tableInsertStmt;
   }
}
