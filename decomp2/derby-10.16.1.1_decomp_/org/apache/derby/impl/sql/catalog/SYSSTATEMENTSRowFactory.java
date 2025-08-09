package org.apache.derby.impl.sql.catalog;

import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLTimestamp;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSSTATEMENTSRowFactory extends CatalogRowFactory {
   static final String TABLENAME_STRING = "SYSSTATEMENTS";
   public static final int SYSSTATEMENTS_STMTID = 1;
   public static final int SYSSTATEMENTS_STMTNAME = 2;
   public static final int SYSSTATEMENTS_SCHEMAID = 3;
   public static final int SYSSTATEMENTS_TYPE = 4;
   public static final int SYSSTATEMENTS_VALID = 5;
   public static final int SYSSTATEMENTS_TEXT = 6;
   public static final int SYSSTATEMENTS_LASTCOMPILED = 7;
   public static final int SYSSTATEMENTS_COMPILATION_SCHEMAID = 8;
   public static final int SYSSTATEMENTS_USINGTEXT = 9;
   public static final int SYSSTATEMENTS_CONSTANTSTATE = 10;
   public static final int SYSSTATEMENTS_INITIALLY_COMPILABLE = 11;
   public static final int SYSSTATEMENTS_COLUMN_COUNT = 11;
   public static final int SYSSTATEMENTS_HIDDEN_COLUMN_COUNT = 2;
   protected static final int SYSSTATEMENTS_INDEX1_ID = 0;
   protected static final int SYSSTATEMENTS_INDEX2_ID = 1;
   private static final int[][] indexColumnPositions = new int[][]{{1}, {2, 3}};
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"80000000-00d1-15f7-ab70-000a0a0b1500", "80000000-00d1-15fc-60b9-000a0a0b1500", "80000000-00d1-15fc-eda1-000a0a0b1500", "80000000-00d1-15fe-bdf8-000a0a0b1500"};

   SYSSTATEMENTSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(11, "SYSSTATEMENTS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeSYSSTATEMENTSrow(boolean var1, SPSDescriptor var2) throws StandardException {
      String var6 = null;
      Object var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      ExecPreparedStatement var13 = null;
      String var14 = null;
      boolean var15 = true;
      Timestamp var16 = null;
      boolean var17 = true;
      if (var2 != null) {
         var6 = var2.getName();
         UUID var18 = var2.getUUID();
         var9 = var2.getSchemaDescriptor().getUUID().toString();
         var8 = var18.toString();
         var11 = var2.getText();
         var15 = var2.isValid();
         var16 = var2.getCompileTime();
         var14 = var2.getTypeAsString();
         var17 = var2.initiallyCompilable();
         var13 = var2.getPreparedStatement(var1);
         var10 = var2.getCompSchemaId() != null ? var2.getCompSchemaId().toString() : null;
         var12 = var2.getUsingText();
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(11);
      var4.setColumn(1, new SQLChar(var8));
      var4.setColumn(2, new SQLVarchar(var6));
      var4.setColumn(3, new SQLChar(var9));
      var4.setColumn(4, new SQLChar(var14));
      var4.setColumn(5, new SQLBoolean(var15));
      var4.setColumn(6, this.dvf.getLongvarcharDataValue(var11));
      var4.setColumn(7, new SQLTimestamp(var16));
      var4.setColumn(8, new SQLChar(var10));
      var4.setColumn(9, this.dvf.getLongvarcharDataValue(var12));
      var4.setColumn(10, new UserType(var13));
      var4.setColumn(11, new SQLBoolean(var17));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      UUID var10 = null;
      Object var17 = null;
      ExecPreparedStatement var18 = null;
      DataDescriptorGenerator var20 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var11 = var4.getString();
      UUID var9 = this.getUUIDFactory().recreateUUID(var11);
      var4 = var1.getColumn(2);
      String var6 = var4.getString();
      var4 = var1.getColumn(3);
      String var13 = var4.getString();
      UUID var12 = this.getUUIDFactory().recreateUUID(var13);
      var4 = var1.getColumn(4);
      char var15 = var4.getString().charAt(0);
      boolean var16;
      if (var3.isReadOnlyUpgrade()) {
         var16 = false;
      } else {
         var4 = var1.getColumn(5);
         var16 = var4.getBoolean();
      }

      var4 = var1.getColumn(6);
      String var7 = var4.getString();
      var4 = var1.getColumn(7);
      Timestamp var32 = var4.getTimestamp(new GregorianCalendar());
      var4 = var1.getColumn(8);
      var11 = var4.getString();
      if (var11 != null) {
         var10 = this.getUUIDFactory().recreateUUID(var11);
      }

      var4 = var1.getColumn(9);
      String var8 = var4.getString();
      if (var16) {
         var4 = var1.getColumn(10);
         var18 = (ExecPreparedStatement)var4.getObject();
      }

      var4 = var1.getColumn(11);
      boolean var19;
      if (var4.isNull()) {
         var19 = true;
      } else {
         var19 = var4.getBoolean();
      }

      SPSDescriptor var5 = new SPSDescriptor(var3, var6, var9, var12, var10, var15, var16, var7, var8, var32, var18, var19);
      return var5;
   }

   public ExecRow makeEmptyRow() throws StandardException {
      return this.makeSYSSTATEMENTSrow(false, (SPSDescriptor)null);
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("STMTID", false), SystemColumnImpl.getIdentifierColumn("STMTNAME", false), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getIndicatorColumn("TYPE"), SystemColumnImpl.getColumn("VALID", 16, false), SystemColumnImpl.getColumn("TEXT", -1, false, 32700), SystemColumnImpl.getColumn("LASTCOMPILED", 93, true), SystemColumnImpl.getUUIDColumn("COMPILATIONSCHEMAID", true), SystemColumnImpl.getColumn("USINGTEXT", -1, true, 32700)};
   }

   public Properties getCreateHeapProperties() {
      Properties var1 = new Properties();
      var1.put("derby.storage.pageSize", "2048");
      var1.put("derby.storage.pageReservedSpace", "0");
      var1.put("derby.storage.minimumRecordSize", "1");
      return var1;
   }
}
