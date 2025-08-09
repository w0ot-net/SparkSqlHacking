package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSFOREIGNKEYSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSFOREIGNKEYS";
   protected static final int SYSFOREIGNKEYS_COLUMN_COUNT = 5;
   protected static final int SYSFOREIGNKEYS_CONSTRAINTID = 1;
   protected static final int SYSFOREIGNKEYS_CONGLOMERATEID = 2;
   protected static final int SYSFOREIGNKEYS_KEYCONSTRAINTID = 3;
   protected static final int SYSFOREIGNKEYS_DELETERULE = 4;
   protected static final int SYSFOREIGNKEYS_UPDATERULE = 5;
   protected static final int SYSFOREIGNKEYS_CONSTRAINTID_WIDTH = 36;
   protected static final int SYSFOREIGNKEYS_INDEX1_ID = 0;
   protected static final int SYSFOREIGNKEYS_INDEX2_ID = 1;
   private static final int[][] indexColumnPositions = new int[][]{{1}, {3}};
   private static final boolean[] uniqueness = new boolean[]{true, false};
   private static final String[] uuids = new String[]{"8000005b-00d0-fd77-3ed8-000a0a0b1900", "80000060-00d0-fd77-3ed8-000a0a0b1900", "8000005d-00d0-fd77-3ed8-000a0a0b1900", "8000005f-00d0-fd77-3ed8-000a0a0b1900"};

   SYSFOREIGNKEYSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(5, "SYSFOREIGNKEYS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var5 = null;
      String var6 = null;
      String var7 = null;
      String var8 = "N";
      String var9 = "N";
      if (var1 != null) {
         ForeignKeyConstraintDescriptor var10 = (ForeignKeyConstraintDescriptor)var1;
         var5 = var10.getUUID().toString();
         ReferencedKeyConstraintDescriptor var11 = var10.getReferencedConstraint();
         var6 = var11.getUUID().toString();
         var7 = var10.getIndexUUIDString();
         var8 = this.getRefActionAsString(var10.getRaDeleteRule());
         var9 = this.getRefActionAsString(var10.getRaUpdateRule());
      }

      ExecIndexRow var4 = this.getExecutionFactory().getIndexableRow(5);
      var4.setColumn(1, new SQLChar(var5));
      var4.setColumn(2, new SQLChar(var7));
      var4.setColumn(3, new SQLChar(var6));
      var4.setColumn(4, new SQLChar(var8));
      var4.setColumn(5, new SQLChar(var9));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var5 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var9 = var4.getString();
      UUID var6 = this.getUUIDFactory().recreateUUID(var9);
      var4 = var1.getColumn(2);
      String var10 = var4.getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var10);
      var4 = var1.getColumn(3);
      var9 = var4.getString();
      UUID var8 = this.getUUIDFactory().recreateUUID(var9);
      var4 = var1.getColumn(4);
      String var11 = var4.getString();
      int var12 = this.getRefActionAsInt(var11);
      var4 = var1.getColumn(5);
      var11 = var4.getString();
      int var13 = this.getRefActionAsInt(var11);
      return new SubKeyConstraintDescriptor(var6, var7, var8, var12, var13);
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("CONSTRAINTID", false), SystemColumnImpl.getUUIDColumn("CONGLOMERATEID", false), SystemColumnImpl.getUUIDColumn("KEYCONSTRAINTID", false), SystemColumnImpl.getIndicatorColumn("DELETERULE"), SystemColumnImpl.getIndicatorColumn("UPDATERULE")};
   }

   int getRefActionAsInt(String var1) {
      byte var2;
      switch (var1.charAt(0)) {
         case 'C' -> var2 = 0;
         case 'D' -> var2 = 4;
         case 'R' -> var2 = 2;
         case 'S' -> var2 = 1;
         case 'U' -> var2 = 3;
         default -> var2 = 2;
      }

      return var2;
   }

   String getRefActionAsString(int var1) {
      String var2;
      switch (var1) {
         case 0:
            var2 = "C";
            break;
         case 1:
            var2 = "S";
            break;
         case 2:
            var2 = "R";
            break;
         case 3:
            var2 = "U";
            break;
         case 4:
            var2 = "D";
            var1 = 4;
            break;
         default:
            var2 = "N";
      }

      return var2;
   }
}
