package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PermDescriptor;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSPERMSRowFactory extends PermissionsCatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSPERMS";
   private static final int SYSPERMS_COLUMN_COUNT = 7;
   private static final int SYSPERMS_PERMISSIONID = 1;
   private static final int SYSPERMS_OBJECTTYPE = 2;
   private static final int SYSPERMS_OBJECTID = 3;
   private static final int SYSPERMS_PERMISSION = 4;
   private static final int SYSPERMS_GRANTOR = 5;
   private static final int SYSPERMS_GRANTEE = 6;
   private static final int SYSPERMS_IS_GRANTABLE = 7;
   private static final int[][] indexColumnPositions = new int[][]{{1}, {3}, {6, 3, 5}};
   public static final int PERMS_UUID_IDX_NUM = 0;
   public static final int PERMS_OBJECTID_IDX_NUM = 1;
   public static final int GRANTEE_OBJECTID_GRANTOR_INDEX_NUM = 2;
   private static final boolean[] uniqueness = new boolean[]{true, false, true};
   private static final String[] uuids = new String[]{"9810800c-0121-c5e1-a2f5-00000043e718", "6ea6ffac-0121-c5e3-f286-00000043e718", "5cc556fc-0121-c5e6-4e43-00000043e718", "7a92cf84-0122-51e6-2c5e-00000047b548", "9810800c-0125-8de5-3aa0-0000001999e8"};

   SYSPERMSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(7, "SYSPERMS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecIndexRow buildIndexKeyRow(int var1, PermissionsDescriptor var2) throws StandardException {
      ExecIndexRow var3 = null;
      switch (var1) {
         case 0:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var5 = ((PermDescriptor)var2).getUUID().toString();
            var3.setColumn(1, new SQLChar(var5));
            break;
         case 2:
            var3 = this.getExecutionFactory().getIndexableRow(2);
            var3.setColumn(1, this.getAuthorizationID(var2.getGrantee()));
            String var4 = ((PermDescriptor)var2).getPermObjectId().toString();
            var3.setColumn(2, new SQLChar(var4));
      }

      return var3;
   }

   public int getPrimaryKeyIndexNumber() {
      return 2;
   }

   public int orPermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      return 0;
   }

   public int removePermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      return -1;
   }

   void setUUIDOfThePassedDescriptor(ExecRow var1, PermissionsDescriptor var2) throws StandardException {
      DataValueDescriptor var3 = var1.getColumn(1);
      var2.setUUID(this.getUUIDFactory().recreateUUID(var3.getString()));
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      String var5 = "SEQUENCE";
      String var6 = null;
      String var7 = "USAGE";
      String var8 = null;
      String var9 = null;
      boolean var10 = false;
      if (var1 != null) {
         PermDescriptor var11 = (PermDescriptor)var1;
         UUID var12 = var11.getUUID();
         if (var12 == null) {
            var12 = this.getUUIDFactory().createUUID();
            var11.setUUID(var12);
         }

         var4 = var12.toString();
         var5 = var11.getObjectType();
         UUID var13 = var11.getPermObjectId();
         var6 = var13.toString();
         var7 = var11.getPermission();
         var8 = var11.getGrantor();
         var9 = var11.getGrantee();
         var10 = var11.isGrantable();
      }

      ExecRow var3 = this.getExecutionFactory().getValueRow(7);
      var3.setColumn(1, new SQLChar(var4));
      var3.setColumn(2, new SQLVarchar(var5));
      var3.setColumn(3, new SQLChar(var6));
      var3.setColumn(4, new SQLChar(var7));
      var3.setColumn(5, new SQLVarchar(var8));
      var3.setColumn(6, new SQLVarchar(var9));
      var3.setColumn(7, new SQLChar(var10 ? "Y" : "N"));
      return var3;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var13 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var6 = var4.getString();
      var4 = var1.getColumn(2);
      String var7 = var4.getString();
      var4 = var1.getColumn(3);
      String var8 = var4.getString();
      var4 = var1.getColumn(4);
      String var9 = var4.getString();
      var4 = var1.getColumn(5);
      String var10 = var4.getString();
      var4 = var1.getColumn(6);
      String var11 = var4.getString();
      var4 = var1.getColumn(7);
      String var12 = var4.getString();
      PermDescriptor var5 = var13.newPermDescriptor(this.getUUIDFactory().recreateUUID(var6), var7, this.getUUIDFactory().recreateUUID(var8), var9, var10, var11, var12.equals("Y"));
      return var5;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("UUID", false), SystemColumnImpl.getColumn("OBJECTTYPE", 12, false, 36), SystemColumnImpl.getUUIDColumn("OBJECTID", false), SystemColumnImpl.getColumn("PERMISSION", 1, false, 36), SystemColumnImpl.getIdentifierColumn("GRANTOR", false), SystemColumnImpl.getIdentifierColumn("GRANTEE", false), SystemColumnImpl.getIndicatorColumn("ISGRANTABLE")};
   }
}
