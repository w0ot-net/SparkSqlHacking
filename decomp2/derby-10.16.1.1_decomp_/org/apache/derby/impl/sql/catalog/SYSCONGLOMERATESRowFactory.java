package org.apache.derby.impl.sql.catalog;

import java.util.Properties;
import org.apache.derby.catalog.IndexDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSCONGLOMERATESRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSCONGLOMERATES";
   protected static final int SYSCONGLOMERATES_COLUMN_COUNT = 8;
   protected static final int SYSCONGLOMERATES_SCHEMAID = 1;
   protected static final int SYSCONGLOMERATES_TABLEID = 2;
   protected static final int SYSCONGLOMERATES_CONGLOMERATENUMBER = 3;
   protected static final int SYSCONGLOMERATES_CONGLOMERATENAME = 4;
   protected static final int SYSCONGLOMERATES_ISINDEX = 5;
   protected static final int SYSCONGLOMERATES_DESCRIPTOR = 6;
   protected static final int SYSCONGLOMERATES_ISCONSTRAINT = 7;
   protected static final int SYSCONGLOMERATES_CONGLOMERATEID = 8;
   protected static final int SYSCONGLOMERATES_INDEX1_ID = 0;
   protected static final int SYSCONGLOMERATES_INDEX2_ID = 1;
   protected static final int SYSCONGLOMERATES_INDEX3_ID = 2;
   private static final boolean[] uniqueness = new boolean[]{false, true, false};
   private static final int[][] indexColumnPositions = new int[][]{{8}, {4, 1}, {2}};
   private static final String[] uuids = new String[]{"80000010-00d0-fd77-3ed8-000a0a0b1900", "80000027-00d0-fd77-3ed8-000a0a0b1900", "80000012-00d0-fd77-3ed8-000a0a0b1900", "80000014-00d0-fd77-3ed8-000a0a0b1900", "80000016-00d0-fd77-3ed8-000a0a0b1900"};

   SYSCONGLOMERATESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(8, "SYSCONGLOMERATES", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var5 = null;
      Long var6 = null;
      String var7 = null;
      Boolean var8 = null;
      IndexRowGenerator var9 = null;
      Boolean var10 = null;
      String var11 = null;
      String var12 = null;
      ConglomerateDescriptor var13 = (ConglomerateDescriptor)var1;
      if (var1 != null) {
         if (var2 != null) {
            SchemaDescriptor var14 = (SchemaDescriptor)var2;
            var12 = var14.getUUID().toString();
         } else {
            var12 = var13.getSchemaID().toString();
         }

         var5 = var13.getTableID().toString();
         var6 = var13.getConglomerateNumber();
         var7 = var13.getConglomerateName();
         var11 = var13.getUUID().toString();
         var8 = var13.isIndex();
         var9 = var13.getIndexDescriptor();
         var10 = var13.isConstraint();
      }

      ExecRow var3 = this.getExecutionFactory().getValueRow(8);
      var3.setColumn(1, new SQLChar(var12));
      var3.setColumn(2, new SQLChar(var5));
      var3.setColumn(3, new SQLLongint(var6));
      var3.setColumn(4, var7 == null ? new SQLVarchar(var5) : new SQLVarchar(var7));
      var3.setColumn(5, new SQLBoolean(var8));
      var3.setColumn(6, new UserType(var9 == null ? (IndexDescriptor)null : var9.getIndexDescriptor()));
      var3.setColumn(7, new SQLBoolean(var10));
      var3.setColumn(8, new SQLChar(var11));
      return var3;
   }

   public ExecRow makeEmptyRow() throws StandardException {
      return this.makeRow((TupleDescriptor)null, (TupleDescriptor)null);
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
      DataDescriptorGenerator var4 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var11 = var1.getColumn(1);
      String var15 = var11.getString();
      UUID var16 = this.getUUIDFactory().recreateUUID(var15);
      var11 = var1.getColumn(2);
      String var17 = var11.getString();
      UUID var18 = this.getUUIDFactory().recreateUUID(var17);
      var11 = var1.getColumn(3);
      long var5 = var11.getLong();
      var11 = var1.getColumn(4);
      String var7 = var11.getString();
      var11 = var1.getColumn(5);
      boolean var9 = var11.getBoolean();
      var11 = var1.getColumn(6);
      IndexRowGenerator var10 = new IndexRowGenerator((IndexDescriptor)var11.getObject());
      var11 = var1.getColumn(7);
      boolean var8 = var11.getBoolean();
      var11 = var1.getColumn(8);
      String var13 = var11.getString();
      UUID var14 = this.getUUIDFactory().recreateUUID(var13);
      ConglomerateDescriptor var12 = var4.newConglomerateDescriptor(var5, var7, var9, var10, var8, var14, var18, var16);
      return var12;
   }

   protected UUID getConglomerateUUID(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(8);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected UUID getTableUUID(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(2);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected UUID getSchemaUUID(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(1);
      String var3 = var2.getString();
      return this.getUUIDFactory().recreateUUID(var3);
   }

   protected String getConglomerateName(ExecRow var1) throws StandardException {
      DataValueDescriptor var2 = var1.getColumn(4);
      return var2.getString();
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getColumn("CONGLOMERATENUMBER", -5, false), SystemColumnImpl.getIdentifierColumn("CONGLOMERATENAME", true), SystemColumnImpl.getColumn("ISINDEX", 16, false), SystemColumnImpl.getJavaColumn("DESCRIPTOR", "org.apache.derby.catalog.IndexDescriptor", true), SystemColumnImpl.getColumn("ISCONSTRAINT", 16, true), SystemColumnImpl.getUUIDColumn("CONGLOMERATEID", false)};
   }
}
