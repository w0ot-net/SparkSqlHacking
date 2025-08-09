package org.apache.derby.impl.sql.catalog;

import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.shared.common.error.StandardException;

class SYSDUMMY1RowFactory extends CatalogRowFactory {
   protected static final int SYSDUMMY1_COLUMN_COUNT = 1;
   private static final String[] uuids = new String[]{"c013800d-00f8-5b70-bea3-00000019ed88", "c013800d-00f8-5b70-fee8-000000198c88"};

   SYSDUMMY1RowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(1, "SYSDUMMY1", (int[][])null, (boolean[])null, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      ExecRow var3 = this.getExecutionFactory().getValueRow(1);
      var3.setColumn(1, new SQLChar("Y"));
      return var3;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      return null;
   }

   public SystemColumn[] buildColumnList() {
      return new SystemColumn[]{SystemColumnImpl.getColumn("IBMREQD", 1, true, 1)};
   }
}
