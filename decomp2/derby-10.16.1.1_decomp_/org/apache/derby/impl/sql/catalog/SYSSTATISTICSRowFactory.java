package org.apache.derby.impl.sql.catalog;

import java.sql.Timestamp;
import org.apache.derby.catalog.Statistics;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLInteger;
import org.apache.derby.iapi.types.SQLTimestamp;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSSTATISTICSRowFactory extends CatalogRowFactory {
   static final String TABLENAME_STRING = "SYSSTATISTICS";
   protected static final int SYSSTATISTICS_ID = 1;
   protected static final int SYSSTATISTICS_REFERENCEID = 2;
   protected static final int SYSSTATISTICS_TABLEID = 3;
   protected static final int SYSSTATISTICS_TIMESTAMP = 4;
   protected static final int SYSSTATISTICS_TYPE = 5;
   protected static final int SYSSTATISTICS_VALID = 6;
   protected static final int SYSSTATISTICS_COLCOUNT = 7;
   protected static final int SYSSTATISTICS_STAT = 8;
   protected static final int SYSSTATISTICS_COLUMN_COUNT = 8;
   protected static final int SYSSTATISTICS_INDEX1_ID = 0;
   private static final boolean[] uniqueness = new boolean[]{false};
   private static final int[][] indexColumnPositions = new int[][]{{3, 2}};
   private static final String[] uuids = new String[]{"f81e0010-00e3-6612-5a96-009e3a3b5e00", "08264012-00e3-6612-5a96-009e3a3b5e00", "c013800d-00e3-ffbe-37c6-009e3a3b5e00"};

   SYSSTATISTICSRowFactory(UUIDFactory var1, ExecutionFactory var2, DataValueFactory var3) {
      super(var1, var2, var3);
      this.initInfo(8, "SYSSTATISTICS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      String var3 = null;
      String var4 = null;
      String var5 = null;
      Object var6 = null;
      Object var7 = null;
      String var8 = null;
      Timestamp var9 = null;
      int var10 = 0;
      Statistics var11 = null;
      boolean var12 = false;
      ExecRow var13 = this.getExecutionFactory().getValueRow(8);
      if (var1 != null) {
         StatisticsDescriptor var14 = (StatisticsDescriptor)var1;
         var3 = var14.getUUID().toString();
         var5 = var14.getTableUUID().toString();
         var4 = var14.getReferenceID().toString();
         var9 = var14.getUpdateTimestamp();
         var8 = var14.getStatType();
         var12 = var14.isValid();
         var11 = var14.getStatistic();
         var10 = var14.getColumnCount();
      }

      var13.setColumn(1, new SQLChar(var3));
      var13.setColumn(2, new SQLChar(var4));
      var13.setColumn(3, new SQLChar(var5));
      var13.setColumn(4, new SQLTimestamp(var9));
      var13.setColumn(5, new SQLChar(var8));
      var13.setColumn(6, new SQLBoolean(var12));
      var13.setColumn(7, new SQLInteger(var10));
      var13.setColumn(8, new UserType(var11));
      return var13;
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      UUIDFactory var6 = this.getUUIDFactory();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var5 = var4.getString();
      UUID var7 = var6.recreateUUID(var5);
      var4 = var1.getColumn(2);
      var5 = var4.getString();
      UUID var8 = var6.recreateUUID(var5);
      var4 = var1.getColumn(3);
      var5 = var4.getString();
      UUID var9 = var6.recreateUUID(var5);
      var4 = var1.getColumn(4);
      Timestamp var11 = (Timestamp)var4.getObject();
      var4 = var1.getColumn(5);
      String var12 = var4.getString();
      var4 = var1.getColumn(6);
      boolean var13 = var4.getBoolean();
      var4 = var1.getColumn(7);
      int var14 = var4.getInt();
      var4 = var1.getColumn(8);
      Statistics var15 = (Statistics)var4.getObject();
      return new StatisticsDescriptor(var3, var7, var8, var9, var12, var15, var14);
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("STATID", false), SystemColumnImpl.getUUIDColumn("REFERENCEID", false), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getColumn("CREATIONTIMESTAMP", 93, false), SystemColumnImpl.getIndicatorColumn("TYPE"), SystemColumnImpl.getColumn("VALID", 16, false), SystemColumnImpl.getColumn("COLCOUNT", 4, false), SystemColumnImpl.getJavaColumn("STATISTICS", "org.apache.derby.catalog.Statistics", false)};
   }
}
