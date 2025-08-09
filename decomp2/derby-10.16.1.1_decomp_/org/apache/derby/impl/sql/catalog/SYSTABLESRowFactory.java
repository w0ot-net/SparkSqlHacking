package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

class SYSTABLESRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSTABLES";
   protected static final int SYSTABLES_COLUMN_COUNT = 5;
   protected static final int SYSTABLES_TABLEID = 1;
   protected static final int SYSTABLES_TABLENAME = 2;
   protected static final int SYSTABLES_TABLETYPE = 3;
   protected static final int SYSTABLES_SCHEMAID = 4;
   protected static final int SYSTABLES_LOCKGRANULARITY = 5;
   protected static final int SYSTABLES_INDEX1_ID = 0;
   protected static final int SYSTABLES_INDEX1_TABLENAME = 1;
   protected static final int SYSTABLES_INDEX1_SCHEMAID = 2;
   protected static final int SYSTABLES_INDEX2_ID = 1;
   protected static final int SYSTABLES_INDEX2_TABLEID = 1;
   private static final String[] uuids = new String[]{"80000018-00d0-fd77-3ed8-000a0a0b1900", "80000028-00d0-fd77-3ed8-000a0a0b1900", "8000001a-00d0-fd77-3ed8-000a0a0b1900", "8000001c-00d0-fd77-3ed8-000a0a0b1900"};
   private static final int[][] indexColumnPositions = new int[][]{{2, 4}, {1}};

   SYSTABLESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(5, "SYSTABLES", indexColumnPositions, (boolean[])null, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      if (var1 != null) {
         TableDescriptor var11 = (TableDescriptor)var1;
         SchemaDescriptor var12 = (SchemaDescriptor)var2;
         UUID var3 = var11.getUUID();
         if (var3 == null) {
            var3 = this.getUUIDFactory().createUUID();
            var11.setUUID(var3);
         }

         var8 = var3.toString();
         var9 = var12.getUUID().toString();
         var10 = var11.getName();
         int var5 = var11.getTableType();
         switch (var5) {
            case 0:
               var4 = "T";
               break;
            case 1:
               var4 = "S";
               break;
            case 2:
               var4 = "V";
            case 3:
            default:
               break;
            case 4:
               var4 = "A";
         }

         char[] var13 = new char[]{var11.getLockGranularity()};
         var7 = new String(var13);
      }

      ExecRow var6 = this.getExecutionFactory().getValueRow(5);
      var6.setColumn(1, new SQLChar(var8));
      var6.setColumn(2, new SQLVarchar(var10));
      var6.setColumn(3, new SQLChar(var4));
      var6.setColumn(4, new SQLChar(var9));
      var6.setColumn(5, new SQLChar(var7));
      return var6;
   }

   ExecIndexRow buildEmptyIndexRow(int var1, RowLocation var2) throws StandardException {
      int var3 = this.getIndexColumnCount(var1);
      ExecIndexRow var4 = this.getExecutionFactory().getIndexableRow(var3 + 1);
      var4.setColumn(var3 + 1, var2);
      switch (var1) {
         case 0:
            var4.setColumn(1, new SQLVarchar());
            var4.setColumn(2, new SQLChar());
            break;
         case 1:
            var4.setColumn(1, new SQLChar());
      }

      return var4;
   }

   TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3, int var4) throws StandardException {
      return this.buildDescriptorBody(var1, var2, var3, var4);
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      return this.buildDescriptorBody(var1, var2, var3, 4);
   }

   public TupleDescriptor buildDescriptorBody(ExecRow var1, TupleDescriptor var2, DataDictionary var3, int var4) throws StandardException {
      DataDescriptorGenerator var5 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var12 = var1.getColumn(1);
      String var6 = var12.getString();
      UUID var13 = this.getUUIDFactory().recreateUUID(var6);
      var12 = var1.getColumn(2);
      String var10 = var12.getString();
      var12 = var1.getColumn(3);
      String var11 = var12.getString();
      byte var8;
      switch (var11.charAt(0)) {
         case 'A' -> var8 = 4;
         case 'S' -> var8 = 1;
         case 'T' -> var8 = 0;
         case 'V' -> var8 = 2;
         default -> var8 = -1;
      }

      var12 = var1.getColumn(4);
      String var7 = var12.getString();
      UUID var14 = this.getUUIDFactory().recreateUUID(var7);
      SchemaDescriptor var15 = var3.getSchemaDescriptor(var14, var4, (TransactionController)null);
      var12 = var1.getColumn(5);
      String var9 = var12.getString();
      TableDescriptor var16 = var5.newTableDescriptor(var10, var15, var8, var9.charAt(0));
      var16.setUUID(var13);
      return var16;
   }

   protected String getTableName(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(2);
      return var2.getString();
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getIdentifierColumn("TABLENAME", false), SystemColumnImpl.getIndicatorColumn("TABLETYPE"), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getIndicatorColumn("LOCKGRANULARITY")};
   }
}
