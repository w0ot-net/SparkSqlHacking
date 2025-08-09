package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLLongint;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSSEQUENCESRowFactory extends CatalogRowFactory {
   public static final String TABLENAME_STRING = "SYSSEQUENCES";
   public static final int SYSSEQUENCES_COLUMN_COUNT = 10;
   public static final int SYSSEQUENCES_SEQUENCEID = 1;
   public static final int SYSSEQUENCES_SEQUENCENAME = 2;
   public static final int SYSSEQUENCES_SCHEMAID = 3;
   public static final int SYSSEQUENCES_SEQUENCEDATATYPE = 4;
   public static final int SYSSEQUENCES_CURRENT_VALUE = 5;
   public static final int SYSSEQUENCES_START_VALUE = 6;
   public static final int SYSSEQUENCES_MINIMUM_VALUE = 7;
   public static final int SYSSEQUENCES_MAXIMUM_VALUE = 8;
   public static final int SYSSEQUENCES_INCREMENT = 9;
   public static final int SYSSEQUENCES_CYCLE_OPTION = 10;
   private static final int[][] indexColumnPositions = new int[][]{{1}, {3, 2}};
   static final int SYSSEQUENCES_INDEX1_ID = 0;
   static final int SYSSEQUENCES_INDEX2_ID = 1;
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"9810800c-0121-c5e2-e794-00000043e718", "6ea6ffac-0121-c5e6-29e6-00000043e718", "7a92cf84-0121-c5fa-caf1-00000043e718", "6b138684-0121-c5e9-9114-00000043e718"};

   SYSSEQUENCESRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(10, "SYSSEQUENCES", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var4 = null;
      String var5 = null;
      String var6 = null;
      TypeDescriptor var7 = null;
      Long var8 = null;
      long var9 = 0L;
      long var11 = 0L;
      long var13 = 0L;
      long var15 = 0L;
      boolean var17 = false;
      if (var1 != null) {
         SequenceDescriptor var18 = (SequenceDescriptor)var1;
         UUID var19 = var18.getUUID();
         var4 = var19.toString();
         var5 = var18.getSequenceName();
         UUID var20 = var18.getSchemaId();
         var6 = var20.toString();
         var7 = var18.getDataType().getCatalogType();
         var8 = var18.getCurrentValue();
         var9 = var18.getStartValue();
         var11 = var18.getMinimumValue();
         var13 = var18.getMaximumValue();
         var15 = var18.getIncrement();
         var17 = var18.canCycle();
      }

      ExecRow var3 = this.getExecutionFactory().getValueRow(10);
      var3.setColumn(1, new SQLChar(var4));
      var3.setColumn(2, new SQLVarchar(var5));
      var3.setColumn(3, new SQLChar(var6));
      var3.setColumn(4, new UserType(var7));
      SQLLongint var21;
      if (var8 == null) {
         var21 = new SQLLongint();
      } else {
         var21 = new SQLLongint(var8);
      }

      var3.setColumn(5, var21);
      var3.setColumn(6, new SQLLongint(var9));
      var3.setColumn(7, new SQLLongint(var11));
      var3.setColumn(8, new SQLLongint(var13));
      var3.setColumn(9, new SQLLongint(var15));
      var3.setColumn(10, new SQLChar(var17 ? "Y" : "N"));
      return var3;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var19 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var20 = var4.getString();
      UUID var6 = this.getUUIDFactory().recreateUUID(var20);
      var4 = var1.getColumn(2);
      String var7 = var4.getString();
      var4 = var1.getColumn(3);
      String var21 = var4.getString();
      UUID var8 = this.getUUIDFactory().recreateUUID(var21);
      TypeDescriptor var22 = (TypeDescriptor)var1.getColumn(4).getObject();
      DataTypeDescriptor var23 = DataTypeDescriptor.getType(var22);
      var4 = var1.getColumn(5);
      Long var9;
      if (var4.isNull()) {
         var9 = null;
      } else {
         var9 = var4.getLong();
      }

      var4 = var1.getColumn(6);
      long var10 = var4.getLong();
      var4 = var1.getColumn(7);
      long var12 = var4.getLong();
      var4 = var1.getColumn(8);
      long var14 = var4.getLong();
      var4 = var1.getColumn(9);
      long var16 = var4.getLong();
      var4 = var1.getColumn(10);
      String var18 = var4.getString();
      SequenceDescriptor var5 = var19.newSequenceDescriptor(var3.getSchemaDescriptor(var8, (TransactionController)null), var6, var7, var23, var9, var10, var12, var14, var16, var18.equals("Y"));
      return var5;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("SEQUENCEID", false), SystemColumnImpl.getIdentifierColumn("SEQUENCENAME", false), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getJavaColumn("SEQUENCEDATATYPE", "org.apache.derby.catalog.TypeDescriptor", false), SystemColumnImpl.getColumn("CURRENTVALUE", -5, true), SystemColumnImpl.getColumn("STARTVALUE", -5, false), SystemColumnImpl.getColumn("MINIMUMVALUE", -5, false), SystemColumnImpl.getColumn("MAXIMUMVALUE", -5, false), SystemColumnImpl.getColumn("INCREMENT", -5, false), SystemColumnImpl.getIndicatorColumn("CYCLEOPTION")};
   }
}
