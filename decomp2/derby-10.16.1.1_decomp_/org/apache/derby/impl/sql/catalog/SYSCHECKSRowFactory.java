package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.CheckConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SubCheckConstraintDescriptor;
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

class SYSCHECKSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSCHECKS";
   private static final int SYSCHECKS_COLUMN_COUNT = 3;
   private static final int SYSCHECKS_CONSTRAINTID = 1;
   private static final int SYSCHECKS_CHECKDEFINITION = 2;
   private static final int SYSCHECKS_REFERENCEDCOLUMNS = 3;
   static final int SYSCHECKS_INDEX1_ID = 0;
   private static final boolean[] uniqueness = null;
   private static final int[][] indexColumnPositions = new int[][]{{1}};
   private static final String[] uuids = new String[]{"80000056-00d0-fd77-3ed8-000a0a0b1900", "80000059-00d0-fd77-3ed8-000a0a0b1900", "80000058-00d0-fd77-3ed8-000a0a0b1900"};

   SYSCHECKSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(3, "SYSCHECKS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      ReferencedColumns var5 = null;
      String var6 = null;
      String var7 = null;
      if (var1 != null) {
         CheckConstraintDescriptor var8 = (CheckConstraintDescriptor)var1;
         var7 = var8.getUUID().toString();
         var6 = var8.getConstraintText();
         var5 = var8.getReferencedColumnsDescriptor();
      }

      ExecIndexRow var4 = this.getExecutionFactory().getIndexableRow(3);
      var4.setColumn(1, new SQLChar(var7));
      var4.setColumn(2, this.dvf.getLongvarcharDataValue(var6));
      var4.setColumn(3, new UserType(var5));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      TupleDescriptor var4 = null;
      DataDescriptorGenerator var6 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var5 = var1.getColumn(1);
      String var9 = var5.getString();
      UUID var10 = this.getUUIDFactory().recreateUUID(var9);
      var5 = var1.getColumn(2);
      String var8 = var5.getString();
      var5 = var1.getColumn(3);
      ReferencedColumns var7 = (ReferencedColumns)var5.getObject();
      var4 = new SubCheckConstraintDescriptor(var10, var8, var7);
      return var4;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("CONSTRAINTID", false), SystemColumnImpl.getColumn("CHECKDEFINITION", -1, false), SystemColumnImpl.getJavaColumn("REFERENCEDCOLUMNS", "org.apache.derby.catalog.ReferencedColumns", false)};
   }
}
