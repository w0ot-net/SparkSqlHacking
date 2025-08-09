package org.apache.derby.impl.sql.catalog;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.GregorianCalendar;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.UserDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLTimestamp;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.shared.common.error.StandardException;

public class SYSUSERSRowFactory extends CatalogRowFactory {
   public static final String TABLE_NAME = "SYSUSERS";
   public static final String SYSUSERS_UUID = "9810800c-0134-14a5-40c1-000004f61f90";
   public static final String PASSWORD_COL_NAME = "PASSWORD";
   private static final int SYSUSERS_COLUMN_COUNT = 4;
   public static final int USERNAME_COL_NUM = 1;
   public static final int HASHINGSCHEME_COL_NUM = 2;
   public static final int PASSWORD_COL_NUM = 3;
   public static final int LASTMODIFIED_COL_NUM = 4;
   static final int SYSUSERS_INDEX1_ID = 0;
   private static final int[][] indexColumnPositions = new int[][]{{1}};
   private static final boolean[] uniqueness = null;
   private static final String[] uuids = new String[]{"9810800c-0134-14a5-40c1-000004f61f90", "9810800c-0134-14a5-a609-000004f61f90", "9810800c-0134-14a5-f1cd-000004f61f90"};

   SYSUSERSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(4, "SYSUSERS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var3 = null;
      String var4 = null;
      char[] var5 = null;
      Timestamp var6 = null;

      ExecRow var7;
      try {
         if (var1 != null) {
            UserDescriptor var8 = (UserDescriptor)var1;
            var3 = var8.getUserName();
            var4 = var8.getHashingScheme();
            var5 = var8.getAndZeroPassword();
            var6 = var8.getLastModified();
         }

         var7 = this.getExecutionFactory().getValueRow(4);
         var7.setColumn(1, new SQLVarchar(var3));
         var7.setColumn(2, new SQLVarchar(var4));
         var7.setColumn(3, new SQLVarchar(var5));
         var7.setColumn(4, new SQLTimestamp(var6));
      } finally {
         if (var5 != null) {
            Arrays.fill(var5, '\u0000');
         }

      }

      return var7;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      DataDescriptorGenerator var4 = var3.getDataDescriptorGenerator();
      char[] var7 = null;
      SQLVarchar var10 = null;

      UserDescriptor var11;
      try {
         DataValueDescriptor var9 = var1.getColumn(1);
         String var5 = var9.getString();
         var9 = var1.getColumn(2);
         String var6 = var9.getString();
         var10 = (SQLVarchar)var1.getColumn(3);
         var7 = var10.getRawDataAndZeroIt();
         var9 = var1.getColumn(4);
         Timestamp var8 = var9.getTimestamp(new GregorianCalendar());
         var11 = var4.newUserDescriptor(var5, var6, var7, var8);
      } finally {
         if (var7 != null) {
            Arrays.fill(var7, '\u0000');
         }

         if (var10 != null) {
            var10.zeroRawData();
         }

      }

      return var11;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getIdentifierColumn("USERNAME", false), SystemColumnImpl.getColumn("HASHINGSCHEME", 12, false, 32672), SystemColumnImpl.getColumn("PASSWORD", 12, false, 32672), SystemColumnImpl.getColumn("LASTMODIFIED", 93, false)};
   }
}
