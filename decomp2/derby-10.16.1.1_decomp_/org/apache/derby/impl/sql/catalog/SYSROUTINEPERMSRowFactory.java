package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.RoutinePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSROUTINEPERMSRowFactory extends PermissionsCatalogRowFactory {
   static final String TABLENAME_STRING = "SYSROUTINEPERMS";
   private static final int ROUTINEPERMSID_COL_NUM = 1;
   private static final int GRANTEE_COL_NUM = 2;
   private static final int GRANTOR_COL_NUM = 3;
   private static final int ALIASID_COL_NUM = 4;
   private static final int GRANTOPTION_COL_NUM = 5;
   private static final int COLUMN_COUNT = 5;
   static final int GRANTEE_ALIAS_GRANTOR_INDEX_NUM = 0;
   public static final int ROUTINEPERMSID_INDEX_NUM = 1;
   public static final int ALIASID_INDEX_NUM = 2;
   private static final int[][] indexColumnPositions = new int[][]{{2, 4, 3}, {1}, {4}};
   public static final int GRANTEE_COL_NUM_IN_GRANTEE_ALIAS_GRANTOR_INDEX = 1;
   private static final boolean[] indexUniqueness = new boolean[]{true, true, false};
   private static final String[] uuids = new String[]{"2057c01b-0103-0e39-b8e7-00000010f010", "185e801c-0103-0e39-b8e7-00000010f010", "c065801d-0103-0e39-b8e7-00000010f010", "40f70088-010c-4c2f-c8de-0000000f43a0", "08264012-010c-bc85-060d-000000109ab8"};

   SYSROUTINEPERMSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(5, "SYSROUTINEPERMS", indexColumnPositions, indexUniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      Object var5 = null;
      Object var6 = null;
      String var7 = null;
      DataValueDescriptor var9;
      DataValueDescriptor var10;
      if (var1 == null) {
         var9 = this.getNullAuthorizationID();
         var10 = this.getNullAuthorizationID();
      } else {
         RoutinePermsDescriptor var8 = (RoutinePermsDescriptor)var1;
         UUID var3 = var8.getUUID();
         if (var3 == null) {
            var3 = this.getUUIDFactory().createUUID();
            var8.setUUID(var3);
         }

         var4 = var3.toString();
         var9 = this.getAuthorizationID(var8.getGrantee());
         var10 = this.getAuthorizationID(var8.getGrantor());
         if (var8.getRoutineUUID() != null) {
            var7 = var8.getRoutineUUID().toString();
         }
      }

      ExecRow var11 = this.getExecutionFactory().getValueRow(5);
      var11.setColumn(1, new SQLChar(var4));
      var11.setColumn(2, var9);
      var11.setColumn(3, var10);
      var11.setColumn(4, new SQLChar(var7));
      var11.setColumn(5, new SQLChar("N"));
      return var11;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      String var4 = var1.getColumn(1).getString();
      UUID var5 = this.getUUIDFactory().recreateUUID(var4);
      String var6 = var1.getColumn(4).getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var6);
      RoutinePermsDescriptor var8 = new RoutinePermsDescriptor(var3, this.getAuthorizationID(var1, 2), this.getAuthorizationID(var1, 3), var7);
      var8.setUUID(var5);
      return var8;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("ROUTINEPERMSID", false), SystemColumnImpl.getIdentifierColumn("GRANTEE", false), SystemColumnImpl.getIdentifierColumn("GRANTOR", false), SystemColumnImpl.getUUIDColumn("ALIASID", false), SystemColumnImpl.getIndicatorColumn("GRANTOPTION")};
   }

   public ExecIndexRow buildIndexKeyRow(int var1, PermissionsDescriptor var2) throws StandardException {
      ExecIndexRow var3 = null;
      switch (var1) {
         case 0:
            var3 = this.getExecutionFactory().getIndexableRow(2);
            var3.setColumn(1, this.getAuthorizationID(var2.getGrantee()));
            String var6 = ((RoutinePermsDescriptor)var2).getRoutineUUID().toString();
            var3.setColumn(2, new SQLChar(var6));
            break;
         case 1:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var5 = var2.getObjectID().toString();
            var3.setColumn(1, new SQLChar(var5));
            break;
         case 2:
            var3 = this.getExecutionFactory().getIndexableRow(1);
            String var4 = ((RoutinePermsDescriptor)var2).getRoutineUUID().toString();
            var3.setColumn(1, new SQLChar(var4));
      }

      return var3;
   }

   public int getPrimaryKeyIndexNumber() {
      return 0;
   }

   public int orPermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      return 0;
   }

   public int removePermissions(ExecRow var1, PermissionsDescriptor var2, boolean[] var3) throws StandardException {
      return -1;
   }

   public void setUUIDOfThePassedDescriptor(ExecRow var1, PermissionsDescriptor var2) throws StandardException {
      DataValueDescriptor var3 = var1.getColumn(1);
      var2.setUUID(this.getUUIDFactory().recreateUUID(var3.getString()));
   }
}
