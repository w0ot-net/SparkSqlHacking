package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.ColPermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

class SYSCOLPERMSRowFactory extends PermissionsCatalogRowFactory {
   static final String TABLENAME_STRING = "SYSCOLPERMS";
   private static final int COLPERMSID_COL_NUM = 1;
   private static final int GRANTEE_COL_NUM = 2;
   private static final int GRANTOR_COL_NUM = 3;
   private static final int TABLEID_COL_NUM = 4;
   private static final int TYPE_COL_NUM = 5;
   protected static final int COLUMNS_COL_NUM = 6;
   private static final int COLUMN_COUNT = 6;
   static final int GRANTEE_TABLE_TYPE_GRANTOR_INDEX_NUM = 0;
   static final int COLPERMSID_INDEX_NUM = 1;
   static final int TABLEID_INDEX_NUM = 2;
   protected static final int TOTAL_NUM_OF_INDEXES = 3;
   private static final int[][] indexColumnPositions = new int[][]{{2, 4, 5, 3}, {1}, {4}};
   public static final int GRANTEE_COL_NUM_IN_GRANTEE_TABLE_TYPE_GRANTOR_INDEX = 1;
   private static final boolean[] indexUniqueness = new boolean[]{true, true, false};
   private static final String[] uuids = new String[]{"286cc01e-0103-0e39-b8e7-00000010f010", "6074401f-0103-0e39-b8e7-00000010f010", "787c0020-0103-0e39-b8e7-00000010f010", "c9a3808d-010c-42a2-ae15-0000000f67f8", "80220011-010c-bc85-060d-000000109ab8"};

   SYSCOLPERMSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(6, "SYSCOLPERMS", indexColumnPositions, indexUniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      Object var5 = null;
      Object var6 = null;
      String var7 = null;
      String var8 = null;
      FormatableBitSet var9 = null;
      DataValueDescriptor var11;
      DataValueDescriptor var12;
      if (var1 == null) {
         var11 = this.getNullAuthorizationID();
         var12 = this.getNullAuthorizationID();
      } else {
         ColPermsDescriptor var10 = (ColPermsDescriptor)var1;
         UUID var3 = var10.getUUID();
         if (var3 == null) {
            var3 = this.getUUIDFactory().createUUID();
            var10.setUUID(var3);
         }

         var4 = var3.toString();
         var11 = this.getAuthorizationID(var10.getGrantee());
         var12 = this.getAuthorizationID(var10.getGrantor());
         var7 = var10.getTableUUID().toString();
         var8 = var10.getType();
         var9 = var10.getColumns();
      }

      ExecRow var13 = this.getExecutionFactory().getValueRow(6);
      var13.setColumn(1, new SQLChar(var4));
      var13.setColumn(2, var11);
      var13.setColumn(3, var12);
      var13.setColumn(4, new SQLChar(var7));
      var13.setColumn(5, new SQLChar(var8));
      var13.setColumn(6, new UserType(var9));
      return var13;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      String var4 = var1.getColumn(1).getString();
      UUID var5 = this.getUUIDFactory().recreateUUID(var4);
      String var6 = var1.getColumn(4).getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var6);
      String var8 = var1.getColumn(5).getString();
      FormatableBitSet var9 = (FormatableBitSet)var1.getColumn(6).getObject();
      ColPermsDescriptor var10 = new ColPermsDescriptor(var3, this.getAuthorizationID(var1, 2), this.getAuthorizationID(var1, 3), var7, var8, var9);
      var10.setUUID(var5);
      return var10;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("COLPERMSID", false), SystemColumnImpl.getIdentifierColumn("GRANTEE", false), SystemColumnImpl.getIdentifierColumn("GRANTOR", false), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getIndicatorColumn("TYPE"), SystemColumnImpl.getJavaColumn("COLUMNS", "org.apache.derby.iapi.services.io.FormatableBitSet", false)};
   }

   public ExecIndexRow buildIndexKeyRow(int var1, PermissionsDescriptor var2) throws StandardException {
      ExecIndexRow var3 = null;
      switch (var1) {
         case 0:
            var3 = this.getExecutionFactory().getIndexableRow(3);
            var3.setColumn(1, this.getAuthorizationID(var2.getGrantee()));
            ColPermsDescriptor var7 = (ColPermsDescriptor)var2;
            String var8 = var7.getTableUUID().toString();
            var3.setColumn(2, new SQLChar(var8));
            var3.setColumn(3, new SQLChar(var7.getType()));
            break;
         case 1:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var6 = var2.getObjectID().toString();
            var3.setColumn(1, new SQLChar(var6));
            break;
         case 2:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            ColPermsDescriptor var4 = (ColPermsDescriptor)var2;
            String var5 = var4.getTableUUID().toString();
            var3.setColumn(1, new SQLChar(var5));
      }

      return var3;
   }

   public int getPrimaryKeyIndexNumber() {
      return 0;
   }

   public int orPermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      ColPermsDescriptor var4 = (ColPermsDescriptor)var2;
      FormatableBitSet var5 = (FormatableBitSet)var1.getColumn(6).getObject();
      FormatableBitSet var6 = var4.getColumns();
      boolean var7 = false;

      for(int var8 = var6.anySetBit(); var8 >= 0; var8 = var6.anySetBit(var8)) {
         if (!var5.get(var8)) {
            var5.set(var8);
            var7 = true;
         }
      }

      if (var7) {
         var3[5] = true;
         return 1;
      } else {
         return 0;
      }
   }

   public int removePermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      ColPermsDescriptor var4 = (ColPermsDescriptor)var2;
      FormatableBitSet var5 = var4.getColumns();
      if (var5 == null) {
         return -1;
      } else {
         FormatableBitSet var6 = (FormatableBitSet)var1.getColumn(6).getObject();
         boolean var7 = false;

         for(int var8 = var5.anySetBit(); var8 >= 0; var8 = var5.anySetBit(var8)) {
            if (var6.get(var8)) {
               var6.clear(var8);
               var7 = true;
            }
         }

         if (var7) {
            var3[5] = true;
            if (var6.anySetBit() < 0) {
               return -1;
            } else {
               return 1;
            }
         } else {
            return 0;
         }
      }
   }

   public void setUUIDOfThePassedDescriptor(ExecRow var1, PermissionsDescriptor var2) throws StandardException {
      DataValueDescriptor var3 = var1.getColumn(1);
      var2.setUUID(this.getUUIDFactory().recreateUUID(var3.getString()));
   }
}
