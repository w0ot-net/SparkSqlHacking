package org.apache.derby.impl.load;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import org.apache.derby.iapi.util.StringUtil;
import org.apache.derby.shared.common.error.PublicAPI;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.vti.VTITemplate;

abstract class ImportAbstract extends VTITemplate {
   ControlInfo controlFileReader;
   ImportReadData importReadData;
   String[] columnNames;
   int numberOfColumns;
   int[] columnWidths;
   int lineNumber = 0;
   String[] nextRow;
   ImportResultSetMetaData importResultSetMetaData;
   int noOfColumnsExpected;
   protected boolean lobsInExtFile = false;
   String tableColumnTypesStr;
   int[] tableColumnTypes;
   String columnTypeNamesString;
   String[] columnTypeNames;
   String udtClassNamesString;
   HashMap udtClasses;
   private boolean wasNull;
   static final String COLUMNNAMEPREFIX = "COLUMN";

   abstract ImportReadData getImportReadData() throws Exception;

   void doAllTheWork() throws Exception {
      this.importReadData = this.getImportReadData();
      this.numberOfColumns = this.importReadData.getNumberOfColumns();
      if (this.numberOfColumns == 0) {
         this.numberOfColumns = this.noOfColumnsExpected;
      }

      this.columnWidths = this.controlFileReader.getColumnWidths();
      this.columnNames = new String[this.numberOfColumns];
      this.loadColumnNames();
      this.nextRow = new String[this.numberOfColumns];
      this.tableColumnTypes = ColumnInfo.getExpectedVtiColumnTypes(this.tableColumnTypesStr, this.numberOfColumns);
      this.columnTypeNames = ColumnInfo.getExpectedColumnTypeNames(this.columnTypeNamesString, this.numberOfColumns);
      this.udtClasses = ColumnInfo.getExpectedUDTClasses(this.udtClassNamesString);
      this.importResultSetMetaData = new ImportResultSetMetaData(this.numberOfColumns, this.columnNames, this.columnWidths, this.tableColumnTypes, this.columnTypeNames, this.udtClasses);
   }

   void loadColumnNames() {
      for(int var1 = 1; var1 <= this.numberOfColumns; ++var1) {
         this.columnNames[var1 - 1] = "COLUMN" + var1;
      }

   }

   public ResultSetMetaData getMetaData() {
      return this.importResultSetMetaData;
   }

   public int getRow() throws SQLException {
      return this.importReadData.getCurrentRowNumber();
   }

   public int getCurrentLineNumber() {
      return this.lineNumber;
   }

   public boolean next() throws SQLException {
      try {
         ++this.lineNumber;
         return this.importReadData.readNextRow(this.nextRow);
      } catch (Exception var2) {
         throw this.importError(var2);
      }
   }

   public void close() throws SQLException {
      try {
         if (this.importReadData != null) {
            this.importReadData.closeStream();
         }

      } catch (Exception var2) {
         throw LoadError.unexpectedError(var2);
      }
   }

   public boolean wasNull() {
      return this.wasNull;
   }

   public String getString(int var1) throws SQLException {
      if (var1 <= this.numberOfColumns) {
         String var2 = this.nextRow[var1 - 1];
         if (this.isColumnInExtFile(var1)) {
            var2 = this.importReadData.getClobColumnFromExtFileAsString(var2, var1);
         }

         this.wasNull = var2 == null;
         return var2;
      } else {
         throw LoadError.invalidColumnNumber(this.numberOfColumns);
      }
   }

   public Clob getClob(int var1) throws SQLException {
      Object var2 = null;
      if (this.lobsInExtFile) {
         var2 = this.importReadData.getClobColumnFromExtFile(this.nextRow[var1 - 1], var1);
      } else {
         String var3 = this.nextRow[var1 - 1];
         if (var3 != null) {
            var2 = new ImportClob(var3);
         }
      }

      this.wasNull = var2 == null;
      return (Clob)var2;
   }

   public Blob getBlob(int var1) throws SQLException {
      Object var2 = null;
      if (this.lobsInExtFile) {
         var2 = this.importReadData.getBlobColumnFromExtFile(this.nextRow[var1 - 1], var1);
      } else {
         String var3 = this.nextRow[var1 - 1];
         Object var4 = null;
         if (var3 != null) {
            byte[] var5 = StringUtil.fromHexString(var3, 0, var3.length());
            if (var5 == null) {
               throw PublicAPI.wrapStandardException(StandardException.newException("XIE0N.S", new Object[]{var3}));
            }

            var2 = new ImportBlob(var5);
         }
      }

      this.wasNull = var2 == null;
      return (Blob)var2;
   }

   public Object getObject(int var1) throws SQLException {
      byte[] var2 = this.getBytes(var1);

      try {
         Class var3 = this.importResultSetMetaData.getUDTClass(var1);
         Object var4 = readObject(var2);
         if (var4 != null && !var3.isInstance(var4)) {
            String var10002 = var4.getClass().getName();
            throw new ClassCastException(var10002 + " -> " + var3.getName());
         } else {
            return var4;
         }
      } catch (Exception var5) {
         throw this.importError(var5);
      }
   }

   public static Object readObject(byte[] var0) throws Exception {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      ObjectInputStream var2 = new ObjectInputStream(var1);
      return var2.readObject();
   }

   public static Object destringifyObject(String var0) throws Exception {
      byte[] var1 = StringUtil.fromHexString(var0, 0, var0.length());
      return readObject(var1);
   }

   public byte[] getBytes(int var1) throws SQLException {
      String var2 = this.nextRow[var1 - 1];
      this.wasNull = var2 == null;
      byte[] var3 = null;
      if (var2 != null) {
         var3 = StringUtil.fromHexString(var2, 0, var2.length());
         if (var3 == null) {
            throw PublicAPI.wrapStandardException(StandardException.newException("XIE0N.S", new Object[]{var2}));
         }
      }

      return var3;
   }

   private boolean isColumnInExtFile(int var1) {
      return this.lobsInExtFile && (this.tableColumnTypes[var1 - 1] == 2004 || this.tableColumnTypes[var1 - 1] == 2005);
   }

   public SQLException importError(Exception var1) {
      Exception var2 = null;
      if (this.importReadData != null) {
         try {
            this.importReadData.closeStream();
         } catch (Exception var4) {
            var2 = var4;
         }
      }

      SQLException var3 = LoadError.unexpectedError(var1);
      if (var2 != null) {
         var3.setNextException(LoadError.unexpectedError(var2));
      }

      return var3;
   }
}
