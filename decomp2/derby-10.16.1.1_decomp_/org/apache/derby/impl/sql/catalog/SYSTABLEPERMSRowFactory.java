package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TablePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSTABLEPERMSRowFactory extends PermissionsCatalogRowFactory {
   static final String TABLENAME_STRING = "SYSTABLEPERMS";
   private static final int TABLEPERMSID_COL_NUM = 1;
   private static final int GRANTEE_COL_NUM = 2;
   private static final int GRANTOR_COL_NUM = 3;
   private static final int TABLEID_COL_NUM = 4;
   private static final int SELECTPRIV_COL_NUM = 5;
   private static final int DELETEPRIV_COL_NUM = 6;
   private static final int INSERTPRIV_COL_NUM = 7;
   private static final int UPDATEPRIV_COL_NUM = 8;
   private static final int REFERENCESPRIV_COL_NUM = 9;
   private static final int TRIGGERPRIV_COL_NUM = 10;
   private static final int COLUMN_COUNT = 10;
   public static final int GRANTEE_TABLE_GRANTOR_INDEX_NUM = 0;
   public static final int TABLEPERMSID_INDEX_NUM = 1;
   public static final int TABLEID_INDEX_NUM = 2;
   private static final int[][] indexColumnPositions = new int[][]{{2, 4, 3}, {1}, {4}};
   public static final int GRANTEE_COL_NUM_IN_GRANTEE_TABLE_GRANTOR_INDEX = 1;
   private static final boolean[] indexUniqueness = new boolean[]{true, true, false};
   private static final String[] uuids = new String[]{"b8450018-0103-0e39-b8e7-00000010f010", "004b0019-0103-0e39-b8e7-00000010f010", "c851401a-0103-0e39-b8e7-00000010f010", "80220011-010c-426e-c599-0000000f1120", "f81e0010-010c-bc85-060d-000000109ab8"};

   SYSTABLEPERMSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(10, "SYSTABLEPERMS", indexColumnPositions, indexUniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      Object var4 = null;
      Object var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = null;
      String var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      String var13 = null;
      DataValueDescriptor var15;
      DataValueDescriptor var16;
      if (var1 == null) {
         var15 = this.getNullAuthorizationID();
         var16 = this.getNullAuthorizationID();
      } else {
         TablePermsDescriptor var14 = (TablePermsDescriptor)var1;
         UUID var3 = var14.getUUID();
         if (var3 == null) {
            var3 = this.getUUIDFactory().createUUID();
            var14.setUUID(var3);
         }

         var6 = var3.toString();
         var15 = this.getAuthorizationID(var14.getGrantee());
         var16 = this.getAuthorizationID(var14.getGrantor());
         var7 = var14.getTableUUID().toString();
         var8 = var14.getSelectPriv();
         var9 = var14.getDeletePriv();
         var10 = var14.getInsertPriv();
         var11 = var14.getUpdatePriv();
         var12 = var14.getReferencesPriv();
         var13 = var14.getTriggerPriv();
      }

      ExecRow var17 = this.getExecutionFactory().getValueRow(10);
      var17.setColumn(1, new SQLChar(var6));
      var17.setColumn(2, var15);
      var17.setColumn(3, var16);
      var17.setColumn(4, new SQLChar(var7));
      var17.setColumn(5, new SQLChar(var8));
      var17.setColumn(6, new SQLChar(var9));
      var17.setColumn(7, new SQLChar(var10));
      var17.setColumn(8, new SQLChar(var11));
      var17.setColumn(9, new SQLChar(var12));
      var17.setColumn(10, new SQLChar(var13));
      return var17;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      String var4 = var1.getColumn(1).getString();
      UUID var5 = this.getUUIDFactory().recreateUUID(var4);
      String var6 = var1.getColumn(4).getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var6);
      String var8 = var1.getColumn(5).getString();
      String var9 = var1.getColumn(6).getString();
      String var10 = var1.getColumn(7).getString();
      String var11 = var1.getColumn(8).getString();
      String var12 = var1.getColumn(9).getString();
      String var13 = var1.getColumn(10).getString();
      TablePermsDescriptor var14 = new TablePermsDescriptor(var3, this.getAuthorizationID(var1, 2), this.getAuthorizationID(var1, 3), var7, var8, var9, var10, var11, var12, var13);
      var14.setUUID(var5);
      return var14;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TABLEPERMSID", false), SystemColumnImpl.getIdentifierColumn("GRANTEE", false), SystemColumnImpl.getIdentifierColumn("GRANTOR", false), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getIndicatorColumn("SELECTPRIV"), SystemColumnImpl.getIndicatorColumn("DELETEPRIV"), SystemColumnImpl.getIndicatorColumn("INSERTPRIV"), SystemColumnImpl.getIndicatorColumn("UPDATEPRIV"), SystemColumnImpl.getIndicatorColumn("REFERENCESPRIV"), SystemColumnImpl.getIndicatorColumn("TRIGGERPRIV")};
   }

   public ExecIndexRow buildIndexKeyRow(int var1, PermissionsDescriptor var2) throws StandardException {
      ExecIndexRow var3 = null;
      switch (var1) {
         case 0:
            var3 = this.getExecutionFactory().getIndexableRow(2);
            var3.setColumn(1, this.getAuthorizationID(var2.getGrantee()));
            String var6 = ((TablePermsDescriptor)var2).getTableUUID().toString();
            var3.setColumn(2, new SQLChar(var6));
            break;
         case 1:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var5 = var2.getObjectID().toString();
            var3.setColumn(1, new SQLChar(var5));
            break;
         case 2:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var4 = ((TablePermsDescriptor)var2).getTableUUID().toString();
            var3.setColumn(1, new SQLChar(var4));
      }

      return var3;
   }

   public int getPrimaryKeyIndexNumber() {
      return 0;
   }

   public int orPermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      TablePermsDescriptor var4 = (TablePermsDescriptor)var2;
      int var5 = 0;
      var5 += this.orOnePermission(var1, var3, 5, var4.getSelectPriv());
      var5 += this.orOnePermission(var1, var3, 6, var4.getDeletePriv());
      var5 += this.orOnePermission(var1, var3, 7, var4.getInsertPriv());
      var5 += this.orOnePermission(var1, var3, 8, var4.getUpdatePriv());
      var5 += this.orOnePermission(var1, var3, 9, var4.getReferencesPriv());
      var5 += this.orOnePermission(var1, var3, 10, var4.getTriggerPriv());
      return var5;
   }

   private int orOnePermission(ExecRow var1, boolean[] var2, int var3, String var4) throws StandardException {
      if (var4.charAt(0) == 'N') {
         return 0;
      } else {
         DataValueDescriptor var5 = var1.getColumn(var3);
         char var6 = var5.getString().charAt(0);
         if (var6 != 'Y' && var6 != var4.charAt(0)) {
            var5.setValue(var4);
            var2[var3 - 1] = true;
            return 1;
         } else {
            return 0;
         }
      }
   }

   public int removePermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      TablePermsDescriptor var4 = (TablePermsDescriptor)var2;
      int var5 = 0;
      boolean var6 = this.removeOnePermission(var1, var3, 5, var4.getSelectPriv()) | this.removeOnePermission(var1, var3, 6, var4.getDeletePriv()) | this.removeOnePermission(var1, var3, 7, var4.getInsertPriv()) | this.removeOnePermission(var1, var3, 8, var4.getUpdatePriv()) | this.removeOnePermission(var1, var3, 9, var4.getReferencesPriv()) | this.removeOnePermission(var1, var3, 10, var4.getTriggerPriv());
      if (!var6) {
         return -1;
      } else {
         for(int var7 = 0; var7 < var3.length; ++var7) {
            if (var3[var7]) {
               ++var5;
            }
         }

         return var5;
      }
   }

   private boolean removeOnePermission(ExecRow var1, boolean[] var2, int var3, String var4) throws StandardException {
      DataValueDescriptor var5 = var1.getColumn(var3);
      char var6 = var5.getString().charAt(0);
      if (var4.charAt(0) == 'N') {
         return var6 != 'N';
      } else {
         if (var6 != 'N') {
            var5.setValue("N");
            var2[var3 - 1] = true;
         }

         return false;
      }
   }

   public void setUUIDOfThePassedDescriptor(ExecRow var1, PermissionsDescriptor var2) throws StandardException {
      DataValueDescriptor var3 = var1.getColumn(1);
      var2.setUUID(this.getUUIDFactory().recreateUUID(var3.getString()));
   }
}
