package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.KeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SubKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSKEYSRowFactory extends CatalogRowFactory {
   private static final String TABLENAME_STRING = "SYSKEYS";
   protected static final int SYSKEYS_COLUMN_COUNT = 2;
   protected static final int SYSKEYS_CONSTRAINTID = 1;
   protected static final int SYSKEYS_CONGLOMERATEID = 2;
   protected static final int SYSKEYS_INDEX1_ID = 0;
   private static final boolean[] uniqueness = null;
   private static final int[][] indexColumnPositions = new int[][]{{1}};
   private static final String[] uuids = new String[]{"80000039-00d0-fd77-3ed8-000a0a0b1900", "8000003c-00d0-fd77-3ed8-000a0a0b1900", "8000003b-00d0-fd77-3ed8-000a0a0b1900"};

   SYSKEYSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(2, "SYSKEYS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var6 = null;
      String var7 = null;
      if (var1 != null) {
         KeyConstraintDescriptor var8 = (KeyConstraintDescriptor)var1;
         UUID var5 = var8.getUUID();
         var6 = var5.toString();
         var7 = var8.getIndexUUIDString();
      }

      ExecRow var4 = this.getExecutionFactory().getValueRow(2);
      var4.setColumn(1, new SQLChar(var6));
      var4.setColumn(2, new SQLChar(var7));
      return var4;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      TupleDescriptor var4 = null;
      DataDescriptorGenerator var6 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var5 = var1.getColumn(1);
      String var9 = var5.getString();
      UUID var7 = this.getUUIDFactory().recreateUUID(var9);
      var5 = var1.getColumn(2);
      String var10 = var5.getString();
      UUID var8 = this.getUUIDFactory().recreateUUID(var10);
      var4 = new SubKeyConstraintDescriptor(var7, var8);
      return var4;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("CONSTRAINTID", false), SystemColumnImpl.getUUIDColumn("CONGLOMERATEID", false)};
   }
}
