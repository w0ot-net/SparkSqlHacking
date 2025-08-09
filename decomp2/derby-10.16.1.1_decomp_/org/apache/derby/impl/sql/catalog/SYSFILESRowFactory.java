package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.FileInfoDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

class SYSFILESRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSFILES";
   private static final int SYSFILES_COLUMN_COUNT = 4;
   private static final int ID_COL_NUM = 1;
   private static final String ID_COL_NAME = "FILEID";
   private static final int SCHEMA_ID_COL_NUM = 2;
   private static final String SCHEMA_ID_COL_NAME = "SCHEMAID";
   private static final int NAME_COL_NUM = 3;
   private static final String NAME_COL_NAME = "FILENAME";
   private static final int GENERATION_ID_COL_NUM = 4;
   private static final String GENERATION_ID_COL_NAME = "GENERATIONID";
   static final int SYSFILES_INDEX1_ID = 0;
   static final int SYSFILES_INDEX2_ID = 1;
   private static final int[][] indexColumnPositions = new int[][]{{3, 2}, {1}};
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"80000000-00d3-e222-873f-000a0a0b1900", "80000000-00d3-e222-9920-000a0a0b1900", "80000000-00d3-e222-a373-000a0a0b1900", "80000000-00d3-e222-be7b-000a0a0b1900"};

   SYSFILESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(4, "SYSFILES", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var3 = null;
      String var4 = null;
      String var5 = null;
      long var6 = 0L;
      if (var1 != null) {
         FileInfoDescriptor var9 = (FileInfoDescriptor)var1;
         var3 = var9.getUUID().toString();
         var4 = var9.getSchemaDescriptor().getUUID().toString();
         var5 = var9.getName();
         var6 = var9.getGenerationId();
      }

      ExecRow var8 = this.getExecutionFactory().getValueRow(4);
      var8.setColumn(1, new SQLChar(var3));
      var8.setColumn(2, new SQLChar(var4));
      var8.setColumn(3, new SQLVarchar(var5));
      var8.setColumn(4, new SQLLongint(var6));
      return var8;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var4 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var12 = var1.getColumn(1);
      String var5 = var12.getString();
      UUID var6 = this.getUUIDFactory().recreateUUID(var5);
      var12 = var1.getColumn(2);
      String var7 = var12.getString();
      UUID var8 = this.getUUIDFactory().recreateUUID(var7);
      SchemaDescriptor var13 = var3.getSchemaDescriptor(var8, (TransactionController)null);
      var12 = var1.getColumn(3);
      String var9 = var12.getString();
      var12 = var1.getColumn(4);
      long var10 = var12.getLong();
      FileInfoDescriptor var14 = var4.newFileInfoDescriptor(var6, var13, var9, var10);
      return var14;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("FILEID", false), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getIdentifierColumn("FILENAME", false), SystemColumnImpl.getColumn("GENERATIONID", -5, false)};
   }
}
