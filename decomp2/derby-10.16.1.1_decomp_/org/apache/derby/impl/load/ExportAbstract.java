package org.apache.derby.impl.load;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.iapi.util.StringUtil;

abstract class ExportAbstract {
   protected ControlInfo controlFileReader;
   protected ExportResultSetForObject exportResultSetForObject;
   protected ExportWriteDataAbstract exportWriteData;
   protected Connection con;
   protected String entityName;
   protected String schemaName;
   protected String selectStatement;
   protected boolean lobsInExtFile = false;

   protected ResultSet resultSetForEntity() throws Exception {
      this.exportResultSetForObject = new ExportResultSetForObject(this.con, this.schemaName, this.entityName, this.selectStatement);
      ResultSet var1 = this.exportResultSetForObject.getResultSet();
      return var1;
   }

   private String[] getOneRowAtATime(ResultSet var1, boolean[] var2, boolean[] var3) throws Exception {
      if (!var1.next()) {
         var1.close();
         this.exportResultSetForObject.close();
         return null;
      } else {
         int var4 = this.exportResultSetForObject.getColumnCount();
         ResultSetMetaData var5 = var1.getMetaData();
         String[] var6 = new String[var4];

         for(int var7 = 0; var7 < var4; ++var7) {
            if (this.lobsInExtFile && (var3[var7] || var2[var7])) {
               String var10;
               if (var2[var7]) {
                  InputStream var11 = var1.getBinaryStream(var7 + 1);
                  var10 = this.exportWriteData.writeBinaryColumnToExternalFile(var11);
               } else {
                  Reader var12 = var1.getCharacterStream(var7 + 1);
                  var10 = this.exportWriteData.writeCharColumnToExternalFile(var12);
               }

               var6[var7] = var10;
            } else {
               int var9 = var7 + 1;
               String var8;
               if (var5.getColumnType(var9) == 2000) {
                  var8 = stringifyObject(var1.getObject(var9));
               } else {
                  var8 = var1.getString(var9);
               }

               var6[var7] = var8;
            }
         }

         return var6;
      }
   }

   public static String stringifyObject(Object var0) throws Exception {
      DynamicByteArrayOutputStream var1 = new DynamicByteArrayOutputStream();
      ObjectOutputStream var2 = new ObjectOutputStream(var1);
      var2.writeObject(var0);
      byte[] var3 = var1.getByteArray();
      int var4 = var1.getUsed();
      return StringUtil.toHexString(var3, 0, var4);
   }

   protected ControlInfo getControlFileReader() {
      return this.controlFileReader;
   }

   protected abstract ExportWriteDataAbstract getExportWriteData() throws Exception;

   protected void doAllTheWork() throws Exception {
      ResultSet var1 = null;

      try {
         var1 = this.resultSetForEntity();
         if (var1 != null) {
            ResultSetMetaData var2 = var1.getMetaData();
            int var3 = var2.getColumnCount();
            boolean[] var4 = new boolean[var3];
            boolean[] var5 = new boolean[var3];
            boolean[] var6 = new boolean[var3];

            for(int var7 = 0; var7 < var3; ++var7) {
               int var8 = var2.getColumnType(var7 + 1);
               if (var8 != -5 && var8 != 3 && var8 != 8 && var8 != 6 && var8 != 4 && var8 != 2 && var8 != 7 && var8 != 5 && var8 != -6) {
                  var4[var7] = false;
               } else {
                  var4[var7] = true;
               }

               if (var8 == 2005) {
                  var5[var7] = true;
               } else {
                  var5[var7] = false;
               }

               if (var8 == 2004) {
                  var6[var7] = true;
               } else {
                  var6[var7] = false;
               }
            }

            this.exportWriteData = this.getExportWriteData();
            this.exportWriteData.writeColumnDefinitionOptionally(this.exportResultSetForObject.getColumnDefinition(), this.exportResultSetForObject.getColumnTypes());
            this.exportWriteData.setColumnLengths(this.controlFileReader.getColumnWidths());

            for(String[] var12 = this.getOneRowAtATime(var1, var6, var5); var12 != null; var12 = this.getOneRowAtATime(var1, var6, var5)) {
               this.exportWriteData.writeData(var12, var4);
            }
         }
      } finally {
         if (this.exportWriteData != null) {
            this.exportWriteData.noMoreRows();
         }

         if (var1 != null) {
            var1.close();
         }

      }

   }
}
