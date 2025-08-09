package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSROLESRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSROLES";
   private static final int SYSROLES_COLUMN_COUNT = 6;
   private static final int SYSROLES_ROLE_UUID = 1;
   private static final int SYSROLES_ROLEID = 2;
   private static final int SYSROLES_GRANTEE = 3;
   private static final int SYSROLES_GRANTOR = 4;
   private static final int SYSROLES_WITHADMINOPTION = 5;
   static final int SYSROLES_ISDEF = 6;
   private static final int[][] indexColumnPositions = new int[][]{{2, 3, 4}, {2, 6}, {1}};
   static final int SYSROLES_ROLEID_COLPOS_IN_INDEX_ID_EE_OR = 1;
   static final int SYSROLES_GRANTEE_COLPOS_IN_INDEX_ID_EE_OR = 2;
   static final int SYSROLES_INDEX_ID_EE_OR_IDX = 0;
   static final int SYSROLES_INDEX_ID_DEF_IDX = 1;
   static final int SYSROLES_INDEX_UUID_IDX = 2;
   private static final boolean[] uniqueness = new boolean[]{true, false, true};
   private static final String[] uuids = new String[]{"e03f4017-0115-382c-08df-ffffe275b270", "c851401a-0115-382c-08df-ffffe275b270", "c065801d-0115-382c-08df-ffffe275b270", "787c0020-0115-382c-08df-ffffe275b270", "629f8094-0116-d8f9-5f97-ffffe275b270"};

   SYSROLESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(6, "SYSROLES", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      String var5 = null;
      String var6 = null;
      String var7 = null;
      boolean var8 = false;
      boolean var9 = false;
      if (var1 != null) {
         RoleGrantDescriptor var10 = (RoleGrantDescriptor)var1;
         var5 = var10.getRoleName();
         var6 = var10.getGrantee();
         var7 = var10.getGrantor();
         var8 = var10.isWithAdminOption();
         var9 = var10.isDef();
         UUID var11 = var10.getUUID();
         var4 = var11.toString();
      }

      ExecRow var3 = this.getExecutionFactory().getValueRow(6);
      var3.setColumn(1, new SQLChar(var4));
      var3.setColumn(2, new SQLVarchar(var5));
      var3.setColumn(3, new SQLVarchar(var6));
      var3.setColumn(4, new SQLVarchar(var7));
      var3.setColumn(5, new SQLChar(var8 ? "Y" : "N"));
      var3.setColumn(6, new SQLChar(var9 ? "Y" : "N"));
      return var3;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var12 = var3.getDataDescriptorGenerator();
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
      RoleGrantDescriptor var5 = var12.newRoleGrantDescriptor(this.getUUIDFactory().recreateUUID(var6), var7, var8, var9, var10.equals("Y"), var11.equals("Y"));
      return var5;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("UUID", false), SystemColumnImpl.getIdentifierColumn("ROLEID", false), SystemColumnImpl.getIdentifierColumn("GRANTEE", false), SystemColumnImpl.getIdentifierColumn("GRANTOR", false), SystemColumnImpl.getIndicatorColumn("WITHADMINOPTION"), SystemColumnImpl.getIndicatorColumn("ISDEF")};
   }
}
