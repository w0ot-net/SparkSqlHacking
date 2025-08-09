package org.apache.derby.impl.load;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import org.apache.derby.iapi.security.Securable;
import org.apache.derby.iapi.security.SecurityUtil;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;

public class Import extends ImportAbstract {
   private static int _importCounter;
   private static Hashtable _importers = new Hashtable();
   private String inputFileName;
   private static short skip;

   public Import(String var1, String var2, String var3, String var4, int var5, String var6, boolean var7, int var8, String var9, String var10) throws SQLException {
      try {
         this.inputFileName = var1;
         this.noOfColumnsExpected = var5;
         this.tableColumnTypesStr = var6;
         this.columnTypeNamesString = var9;
         this.udtClassNamesString = var10;
         this.controlFileReader = new ControlInfo();
         this.controlFileReader.setControlProperties(var3, var2, var4);
         this.lobsInExtFile = var7;
         _importers.put(var8, this);
         this.doImport();
      } catch (Exception var12) {
         throw this.importError(var12);
      }
   }

   private void doImport() throws Exception {
      if (this.inputFileName == null) {
         throw LoadError.dataFileNull();
      } else {
         this.doAllTheWork();
      }
   }

   public static void importTable(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6, short var7, boolean var8, short... var9) throws SQLException {
      try {
         if (var8) {
            SecurityUtil.authorize(Securable.IMPORT_TABLE_LOBS_FROM_EXTFILE);
         } else {
            SecurityUtil.authorize(Securable.IMPORT_TABLE);
         }
      } catch (StandardException var11) {
         throw PublicAPI.wrapStandardException(var11);
      }

      if (var9.length > 0) {
         skip = var9[0];
         if (skip < 0) {
            throw PublicAPI.wrapStandardException(StandardException.newException("42XAV", new Object[0]));
         }
      } else {
         skip = 0;
      }

      performImport(var0, var1, (String)null, (String)null, var2, var3, var4, var5, var6, var7, var8);
   }

   public static void importData(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, short var9, boolean var10, short... var11) throws SQLException {
      try {
         if (var10) {
            SecurityUtil.authorize(Securable.IMPORT_DATA_LOBS_FROM_EXTFILE);
         } else {
            SecurityUtil.authorize(Securable.IMPORT_DATA);
         }
      } catch (StandardException var13) {
         throw PublicAPI.wrapStandardException(var13);
      }

      if (var11.length > 0) {
         skip = var11[0];
         if (skip < 0) {
            throw PublicAPI.wrapStandardException(StandardException.newException("42XAV", new Object[0]));
         }
      } else {
         skip = 0;
      }

      performImport(var0, var1, var3, var4, var2, var5, var6, var7, var8, var9, var10);
   }

   private static void performImport(Connection var0, String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, short var9, boolean var10) throws SQLException {
      Integer var11 = bumpImportCounter();

      try {
         if (var0 == null) {
            throw LoadError.connectionNull();
         }

         if (var4 == null) {
            throw LoadError.entityNameMissing();
         }

         ColumnInfo var12 = new ColumnInfo(var0, var1, var4, var2, var3, "COLUMN", readHeaders(var5, var7, var6, var8));
         Object var13 = null;
         Object var14 = null;

         try {
            var33 = var12.getColumnTypeNames();
            var34 = var12.getUDTClassNames();
         } catch (Throwable var31) {
            throw formatImportError((Import)_importers.get(var11), var5, var31);
         }

         StringBuffer var15 = new StringBuffer("new ");
         var15.append("org.apache.derby.impl.load.Import");
         var15.append("(");
         var15.append(quoteStringArgument(var5));
         var15.append(",");
         var15.append(quoteStringArgument(var6));
         var15.append(",");
         var15.append(quoteStringArgument(var7));
         var15.append(",");
         var15.append(quoteStringArgument(var8));
         var15.append(", ");
         var15.append(var12.getExpectedNumberOfColumnsInFile());
         var15.append(", ");
         var15.append(quoteStringArgument(var12.getExpectedVtiColumnTypesAsString()));
         var15.append(", ");
         var15.append(var10);
         var15.append(", ");
         var15.append(var11);
         var15.append(", ");
         var15.append(quoteStringArgument(var33));
         var15.append(", ");
         var15.append(quoteStringArgument(var34));
         var15.append(" )");
         String var16 = var15.toString();
         String var17 = IdUtil.mkQualifiedName(var1, var4);
         String var18;
         if (var9 > 0) {
            var18 = "replace";
         } else {
            var18 = "bulkInsert";
         }

         String var19 = var12.getColumnNamesWithCasts();
         String var20 = var12.getInsertColumnNames();
         if (var20 != null) {
            var20 = "(" + var20 + ") ";
         } else {
            var20 = "";
         }

         String var21 = "INSERT INTO " + var17 + var20 + " --DERBY-PROPERTIES insertMode=" + var18 + "\n SELECT " + var19 + " from " + var16 + " AS importvti";
         PreparedStatement var22 = var0.prepareStatement(var21);
         Statement var23 = var0.createStatement();
         String var24 = "LOCK TABLE " + var17 + " IN EXCLUSIVE MODE";
         var23.executeUpdate(var24);

         try {
            var22.executeUpdate();
         } catch (Throwable var30) {
            throw formatImportError((Import)_importers.get(var11), var5, var30);
         }

         var23.close();
         var22.close();
      } finally {
         _importers.remove(var11);
      }

   }

   private static String[] readHeaders(String var0, String var1, String var2, String var3) throws SQLException {
      try {
         if (skip <= 0) {
            return null;
         } else {
            ControlInfo var4 = new ControlInfo();
            var4.setControlProperties(var1, var2, var3);
            ImportReadData var5 = new ImportReadData(var0, var4, (short)0);
            String[] var6 = new String[var5.numberOfColumns];
            String[] var7 = new String[var5.numberOfColumns];

            for(int var8 = 0; var8 < skip; ++var8) {
               var5.readNextRow(var7);

               for(int var9 = 0; var9 < var5.numberOfColumns; ++var9) {
                  if (var8 == 0) {
                     var6[var9] = var7[var9];
                  } else if (var7[var9] != null) {
                     var6[var9] = var6[var9] + " " + var7[var9];
                  }
               }
            }

            return var6;
         }
      } catch (Exception var10) {
         throw LoadError.unexpectedError(var10);
      }
   }

   ImportReadData getImportReadData() throws Exception {
      return new ImportReadData(this.inputFileName, this.controlFileReader, skip);
   }

   private static synchronized int bumpImportCounter() {
      return ++_importCounter;
   }

   private static SQLException formatImportError(Import var0, String var1, Throwable var2) {
      int var3 = -1;
      if (var0 != null) {
         var3 = var0.getCurrentLineNumber();
      }

      StandardException var4 = StandardException.newException("XIE0R.S", new Object[]{var3, var1, var2.getMessage()});
      var4.initCause(var2);
      return PublicAPI.wrapStandardException(var4);
   }

   private static String quoteStringArgument(String var0) {
      return var0 == null ? "NULL" : StringUtil.quoteStringLiteral(var0);
   }
}
