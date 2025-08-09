package org.apache.derby.impl.sql.catalog;

import java.util.Properties;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.DefaultInfoImpl;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.UniqueTupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSCOLUMNSRowFactory extends CatalogRowFactory {
   static final String TABLENAME_STRING = "SYSCOLUMNS";
   protected static final int SYSCOLUMNS_COLUMN_COUNT = 10;
   protected static final int SYSCOLUMNS_TABLEID = 1;
   protected static final int SYSCOLUMNS_REFERENCEID = 1;
   protected static final int SYSCOLUMNS_COLUMNNAME = 2;
   protected static final int SYSCOLUMNS_COLUMNNUMBER = 3;
   protected static final int SYSCOLUMNS_COLUMNDATATYPE = 4;
   protected static final int SYSCOLUMNS_COLUMNDEFAULT = 5;
   protected static final int SYSCOLUMNS_COLUMNDEFAULTID = 6;
   protected static final int SYSCOLUMNS_AUTOINCREMENTVALUE = 7;
   protected static final int SYSCOLUMNS_AUTOINCREMENTSTART = 8;
   protected static final int SYSCOLUMNS_AUTOINCREMENTINC = 9;
   protected static final int SYSCOLUMNS_AUTOINCREMENTINCCYCLE = 10;
   protected static final int SYSCOLUMNS_INDEX1_ID = 0;
   protected static final int SYSCOLUMNS_INDEX2_ID = 1;
   private static final boolean[] uniqueness = new boolean[]{true, false};
   private static final String[] uuids = new String[]{"8000001e-00d0-fd77-3ed8-000a0a0b1900", "80000029-00d0-fd77-3ed8-000a0a0b1900", "80000020-00d0-fd77-3ed8-000a0a0b1900", "6839c016-00d9-2829-dfcd-000a0a411400"};
   private static final int[][] indexColumnPositions = new int[][]{{1, 2}, {6}};
   private final DataDictionary dataDictionary;

   SYSCOLUMNSRowFactory(DataDictionary var1, UUIDFactory var2, ExecutionFactory var3, DataValueFactory var4) {
      this(var1, var2, var3, var4, "SYSCOLUMNS");
   }

   SYSCOLUMNSRowFactory(DataDictionary var1, UUIDFactory var2, ExecutionFactory var3, DataValueFactory var4, String var5) {
      super(var2, var3, var4);
      this.dataDictionary = var1;
      this.initInfo(10, var5, indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      return this.makeRow(var1, this.getHeapColumnCount());
   }

   public ExecRow makeEmptyRowForCurrentVersion() throws StandardException {
      return this.makeRow((TupleDescriptor)null, 10);
   }

   private ExecRow makeRow(TupleDescriptor var1, int var2) throws StandardException {
      String var4 = null;
      String var5 = null;
      String var6 = null;
      Integer var7 = null;
      TypeDescriptor var8 = null;
      Object var9 = null;
      long var10 = 0L;
      long var12 = 0L;
      long var14 = 0L;
      boolean var16 = false;
      long var17 = -1L;
      if (var1 != null) {
         ColumnDescriptor var19 = (ColumnDescriptor)var1;
         var8 = var19.getType().getCatalogType();
         var6 = var19.getReferencingUUID().toString();
         var4 = var19.getColumnName();
         var7 = var19.getPosition();
         var10 = var19.getAutoincStart();
         var12 = var19.getAutoincInc();
         var14 = var19.getAutoincValue();
         var17 = var19.getAutoinc_create_or_modify_Start_Increment();
         var16 = var19.getAutoincCycle();
         if (var19.getDefaultInfo() != null) {
            var9 = var19.getDefaultInfo();
         } else {
            var9 = var19.getDefaultValue();
         }

         if (var19.getDefaultUUID() != null) {
            var5 = var19.getDefaultUUID().toString();
         }
      }

      ExecRow var3 = this.getExecutionFactory().getValueRow(var2);
      var3.setColumn(1, new SQLChar(var6));
      var3.setColumn(2, new SQLVarchar(var4));
      var3.setColumn(3, new SQLInteger(var7));
      var3.setColumn(4, new UserType(var8));
      var3.setColumn(5, new UserType(var9));
      var3.setColumn(6, new SQLChar(var5));
      if (var17 != 0L && var17 != 2L && var17 != 3L) {
         if (var17 == 1L) {
            ColumnDescriptor var20 = (ColumnDescriptor)var1;
            var3.setColumn(7, new SQLLongint(var10));
            var3.setColumn(8, new SQLLongint(var10));
            var3.setColumn(9, new SQLLongint(var20.getTableDescriptor().getColumnDescriptor(var4).getAutoincInc()));
            if (var3.nColumns() >= 10) {
               var3.setColumn(10, new SQLBoolean(var16));
            }
         } else if (var17 == 4L) {
            ColumnDescriptor var21 = (ColumnDescriptor)var1;
            var3.setColumn(7, new SQLLongint(var21.getTableDescriptor().getColumnDescriptor(var4).getAutoincValue()));
            var3.setColumn(8, new SQLLongint(var21.getTableDescriptor().getColumnDescriptor(var4).getAutoincStart()));
            var3.setColumn(9, new SQLLongint(var21.getTableDescriptor().getColumnDescriptor(var4).getAutoincInc()));
            if (var3.nColumns() >= 10) {
               var3.setColumn(10, new SQLBoolean(var16));
            }
         } else {
            var3.setColumn(7, new SQLLongint());
            var3.setColumn(8, new SQLLongint());
            var3.setColumn(9, new SQLLongint());
            if (var3.nColumns() >= 10) {
               var3.setColumn(10, new SQLBoolean(var16));
            }
         }
      } else {
         var3.setColumn(7, new SQLLongint(var14));
         var3.setColumn(8, new SQLLongint(var10));
         var3.setColumn(9, new SQLLongint(var12));
         if (var3.nColumns() >= 10) {
            var3.setColumn(10, new SQLBoolean(var16));
         }
      }

      return var3;
   }

   public Properties getCreateHeapProperties() {
      Properties var1 = new Properties();
      var1.put("derby.storage.pageSize", "4096");
      var1.put("derby.storage.pageReservedSpace", "0");
      var1.put("derby.storage.minimumRecordSize", "1");
      return var1;
   }

   public Properties getCreateIndexProperties(int var1) {
      Properties var2 = new Properties();
      var2.put("derby.storage.pageSize", "4096");
      return var2;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DefaultInfoImpl var7 = null;
      DataValueDescriptor var9 = null;
      UUID var10 = null;
      Object var11 = null;
      UUIDFactory var12 = this.getUUIDFactory();
      boolean var19 = false;
      DataDescriptorGenerator var20 = var3.getDataDescriptorGenerator();
      UUID var25;
      if (var2 != null) {
         var25 = ((UniqueTupleDescriptor)var2).getUUID();
      } else {
         var25 = var12.recreateUUID(var1.getColumn(1).getString());
      }

      Object var21 = var1.getColumn(5).getObject();
      if (var21 instanceof DataValueDescriptor var9) {
         ;
      } else if (var21 instanceof DefaultInfoImpl var7) {
         var9 = var7.getDefaultValue();
      }

      String var6 = var1.getColumn(6).getString();
      if (var6 != null) {
         var10 = var12.recreateUUID(var6);
      }

      String var5 = var1.getColumn(2).getString();
      int var4 = var1.getColumn(3).getInt();
      TypeDescriptor var22 = (TypeDescriptor)var1.getColumn(4).getObject();
      DataTypeDescriptor var23 = DataTypeDescriptor.getType(var22);
      long var17 = var1.getColumn(7).getLong();
      long var13 = var1.getColumn(8).getLong();
      long var15 = var1.getColumn(9).getLong();
      if (var1.nColumns() >= 10) {
         DataValueDescriptor var24 = var1.getColumn(10);
         var19 = var24.getBoolean();
      }

      DataValueDescriptor var28 = var1.getColumn(8);
      var13 = var28.getLong();
      var28 = var1.getColumn(9);
      var15 = var28.getLong();
      ColumnDescriptor var8 = new ColumnDescriptor(var5, var4, var23, var9, var7, var25, var10, var13, var15, var17, var19);
      return var8;
   }

   public int getPrimaryKeyIndexNumber() {
      return 0;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("REFERENCEID", false), SystemColumnImpl.getIdentifierColumn("COLUMNNAME", false), SystemColumnImpl.getColumn("COLUMNNUMBER", 4, false), SystemColumnImpl.getJavaColumn("COLUMNDATATYPE", "org.apache.derby.catalog.TypeDescriptor", false), SystemColumnImpl.getJavaColumn("COLUMNDEFAULT", "java.io.Serializable", true), SystemColumnImpl.getUUIDColumn("COLUMNDEFAULTID", true), SystemColumnImpl.getColumn("AUTOINCREMENTVALUE", -5, true), SystemColumnImpl.getColumn("AUTOINCREMENTSTART", -5, true), SystemColumnImpl.getColumn("AUTOINCREMENTINC", -5, true), SystemColumnImpl.getColumn("AUTOINCREMENTCYCLE", 16, true)};
   }

   public int getHeapColumnCount() throws StandardException {
      boolean var1 = this.dataDictionary.checkVersion(260, (String)null);
      int var2 = super.getHeapColumnCount();
      return var1 ? var2 : var2 - 1;
   }
}
