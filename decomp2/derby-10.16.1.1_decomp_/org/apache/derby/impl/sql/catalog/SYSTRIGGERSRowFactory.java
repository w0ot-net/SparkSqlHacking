package org.apache.derby.impl.sql.catalog;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.derby.catalog.ReferencedColumns;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.uuid.UUIDFactory;
import org.apache.derby.iapi.sql.dictionary.CatalogRowFactory;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SystemColumn;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.ExecutionFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.DataValueFactory;
import org.apache.derby.iapi.types.SQLBoolean;
import org.apache.derby.iapi.types.SQLChar;
import org.apache.derby.iapi.types.SQLTimestamp;
import org.apache.derby.iapi.types.SQLVarchar;
import org.apache.derby.iapi.types.UserType;
import org.apache.derby.shared.common.error.StandardException;

public class SYSTRIGGERSRowFactory extends CatalogRowFactory {
   static final String TABLENAME_STRING = "SYSTRIGGERS";
   public static final int SYSTRIGGERS_TRIGGERID = 1;
   public static final int SYSTRIGGERS_TRIGGERNAME = 2;
   public static final int SYSTRIGGERS_SCHEMAID = 3;
   public static final int SYSTRIGGERS_CREATIONTIMESTAMP = 4;
   public static final int SYSTRIGGERS_EVENT = 5;
   public static final int SYSTRIGGERS_FIRINGTIME = 6;
   public static final int SYSTRIGGERS_TYPE = 7;
   public static final int SYSTRIGGERS_STATE = 8;
   public static final int SYSTRIGGERS_TABLEID = 9;
   public static final int SYSTRIGGERS_WHENSTMTID = 10;
   public static final int SYSTRIGGERS_ACTIONSTMTID = 11;
   public static final int SYSTRIGGERS_REFERENCEDCOLUMNS = 12;
   public static final int SYSTRIGGERS_TRIGGERDEFINITION = 13;
   public static final int SYSTRIGGERS_REFERENCINGOLD = 14;
   public static final int SYSTRIGGERS_REFERENCINGNEW = 15;
   public static final int SYSTRIGGERS_OLDREFERENCINGNAME = 16;
   public static final int SYSTRIGGERS_NEWREFERENCINGNAME = 17;
   public static final int SYSTRIGGERS_WHENCLAUSETEXT = 18;
   public static final int SYSTRIGGERS_COLUMN_COUNT = 18;
   public static final int SYSTRIGGERS_INDEX1_ID = 0;
   public static final int SYSTRIGGERS_INDEX2_ID = 1;
   public static final int SYSTRIGGERS_INDEX3_ID = 2;
   private static final int[][] indexColumnPositions = new int[][]{{1}, {2, 3}, {9, 4}};
   private static final boolean[] uniqueness = new boolean[]{true, true, false};
   private static final String[] uuids = new String[]{"c013800d-00d7-c025-4809-000a0a411200", "c013800d-00d7-c025-480a-000a0a411200", "c013800d-00d7-c025-480b-000a0a411200", "c013800d-00d7-c025-480c-000a0a411200", "c013800d-00d7-c025-480d-000a0a411200"};
   private static final TimeZone UTC = TimeZone.getTimeZone("UTC");
   private final DataDictionary dataDictionary;

   SYSTRIGGERSRowFactory(DataDictionary var1, UUIDFactory var2, ExecutionFactory var3, DataValueFactory var4) throws StandardException {
      super(var2, var3, var4);
      this.dataDictionary = var1;
      this.initInfo(18, "SYSTRIGGERS", indexColumnPositions, uniqueness, uuids);
   }

   public ExecRow makeRow(TupleDescriptor var1, TupleDescriptor var2) throws StandardException {
      return this.makeRow(var1, this.getHeapColumnCount());
   }

   public ExecRow makeEmptyRowForCurrentVersion() throws StandardException {
      return this.makeRow((TupleDescriptor)null, 18);
   }

   private ExecRow makeRow(TupleDescriptor var1, int var2) throws StandardException {
      String var3 = null;
      UUID var4 = null;
      UUID var5 = null;
      UUID var6 = null;
      UUID var7 = null;
      UUID var8 = null;
      Timestamp var9 = null;
      String var10 = null;
      String var11 = null;
      String var12 = null;
      String var13 = null;
      String var14 = null;
      String var15 = null;
      String var16 = null;
      ReferencedColumnsDescriptorImpl var17 = null;
      boolean var18 = false;
      boolean var19 = false;
      String var20 = null;
      if (var1 != null) {
         TriggerDescriptor var21 = (TriggerDescriptor)var1;
         var3 = var21.getName();
         var4 = var21.getUUID();
         var5 = var21.getSchemaDescriptor().getUUID();
         var9 = var21.getCreationTimestamp();
         var10 = var21.listensForEvent(1) ? "U" : (var21.listensForEvent(2) ? "D" : "I");
         var11 = var21.isBeforeTrigger() ? "B" : "A";
         var12 = var21.isRowTrigger() ? "R" : "S";
         var13 = var21.isEnabled() ? "E" : "D";
         var6 = var21.getTableDescriptor().getUUID();
         int[] var22 = var21.getReferencedCols();
         int[] var23 = var21.getReferencedColsInTriggerAction();
         var17 = var22 == null && var23 == null ? null : new ReferencedColumnsDescriptorImpl(var22, var23);
         var7 = var21.getActionId();
         var8 = var21.getWhenClauseId();
         var14 = var21.getTriggerDefinition();
         var18 = var21.getReferencingOld();
         var19 = var21.getReferencingNew();
         var15 = var21.getOldReferencingName();
         var16 = var21.getNewReferencingName();
         var20 = var21.getWhenClauseText();
      }

      ExecRow var24 = this.getExecutionFactory().getValueRow(var2);
      var24.setColumn(1, new SQLChar(var4 == null ? null : var4.toString()));
      var24.setColumn(2, new SQLVarchar(var3));
      var24.setColumn(3, new SQLChar(var5 == null ? null : var5.toString()));
      SQLTimestamp var25 = var9 == null ? new SQLTimestamp((Timestamp)null) : new SQLTimestamp(var9, this.getCalendarForCreationTimestamp());
      var24.setColumn(4, var25);
      var24.setColumn(5, new SQLChar(var10));
      var24.setColumn(6, new SQLChar(var11));
      var24.setColumn(7, new SQLChar(var12));
      var24.setColumn(8, new SQLChar(var13));
      var24.setColumn(9, new SQLChar(var6 == null ? null : var6.toString()));
      var24.setColumn(10, new SQLChar(var8 == null ? null : var8.toString()));
      var24.setColumn(11, new SQLChar(var7 == null ? null : var7.toString()));
      var24.setColumn(12, new UserType(var17));
      var24.setColumn(13, this.dvf.getLongvarcharDataValue(var14));
      var24.setColumn(14, new SQLBoolean(var18));
      var24.setColumn(15, new SQLBoolean(var19));
      var24.setColumn(16, new SQLVarchar(var15));
      var24.setColumn(17, new SQLVarchar(var16));
      if (var24.nColumns() >= 18) {
         var24.setColumn(18, this.dvf.getLongvarcharDataValue(var20));
      }

      return var24;
   }

   private Calendar getCalendarForCreationTimestamp() throws StandardException {
      return this.dataDictionary.checkVersion(230, (String)null) ? Calendar.getInstance(UTC) : Calendar.getInstance();
   }

   public TupleDescriptor buildDescriptor(ExecRow var1, TupleDescriptor var2, DataDictionary var3) throws StandardException {
      UUID var14 = null;
      UUID var15 = null;
      byte var17 = 0;
      DataDescriptorGenerator var25 = var3.getDataDescriptorGenerator();
      DataValueDescriptor var4 = var1.getColumn(1);
      String var7 = var4.getString();
      UUID var11 = this.getUUIDFactory().recreateUUID(var7);
      var4 = var1.getColumn(2);
      String var5 = var4.getString();
      var4 = var1.getColumn(3);
      var7 = var4.getString();
      UUID var12 = this.getUUIDFactory().recreateUUID(var7);
      var4 = var1.getColumn(4);
      Timestamp var16 = var4.getTimestamp(this.getCalendarForCreationTimestamp());
      var4 = var1.getColumn(5);
      char var6 = var4.getString().charAt(0);
      switch (var6) {
         case 'D' -> var17 = 2;
         case 'I' -> var17 = 4;
         case 'U' -> var17 = 1;
      }

      boolean var18 = this.getCharBoolean(var1.getColumn(6), 'B', 'A');
      boolean var19 = this.getCharBoolean(var1.getColumn(7), 'R', 'S');
      boolean var20 = this.getCharBoolean(var1.getColumn(8), 'E', 'D');
      var4 = var1.getColumn(9);
      var7 = var4.getString();
      UUID var13 = this.getUUIDFactory().recreateUUID(var7);
      var4 = var1.getColumn(10);
      var7 = var4.getString();
      if (var7 != null) {
         var15 = this.getUUIDFactory().recreateUUID(var7);
      }

      var4 = var1.getColumn(11);
      var7 = var4.getString();
      if (var7 != null) {
         var14 = this.getUUIDFactory().recreateUUID(var7);
      }

      var4 = var1.getColumn(12);
      ReferencedColumns var23 = (ReferencedColumns)var4.getObject();
      var4 = var1.getColumn(13);
      String var8 = var4.getString();
      var4 = var1.getColumn(14);
      boolean var21 = var4.getBoolean();
      var4 = var1.getColumn(15);
      boolean var22 = var4.getBoolean();
      var4 = var1.getColumn(16);
      String var9 = var4.getString();
      var4 = var1.getColumn(17);
      String var10 = var4.getString();
      String var26 = null;
      if (var1.nColumns() >= 18) {
         var4 = var1.getColumn(18);
         var26 = var4.getString();
      }

      TriggerDescriptor var24 = var25.newTriggerDescriptor(var3.getSchemaDescriptor(var12, (TransactionController)null), var11, var5, var17, var18, var19, var20, var3.getTableDescriptor(var13), var15, var14, var16, var23 == null ? (int[])null : var23.getReferencedColumnPositions(), var23 == null ? (int[])null : var23.getTriggerActionReferencedColumnPositions(), var8, var21, var22, var9, var10, var26);
      return var24;
   }

   public SystemColumn[] buildColumnList() throws StandardException {
      return new SystemColumn[]{SystemColumnImpl.getUUIDColumn("TRIGGERID", false), SystemColumnImpl.getIdentifierColumn("TRIGGERNAME", false), SystemColumnImpl.getUUIDColumn("SCHEMAID", false), SystemColumnImpl.getColumn("CREATIONTIMESTAMP", 93, false), SystemColumnImpl.getIndicatorColumn("EVENT"), SystemColumnImpl.getIndicatorColumn("FIRINGTIME"), SystemColumnImpl.getIndicatorColumn("TYPE"), SystemColumnImpl.getIndicatorColumn("STATE"), SystemColumnImpl.getUUIDColumn("TABLEID", false), SystemColumnImpl.getUUIDColumn("WHENSTMTID", true), SystemColumnImpl.getUUIDColumn("ACTIONSTMTID", true), SystemColumnImpl.getJavaColumn("REFERENCEDCOLUMNS", "org.apache.derby.catalog.ReferencedColumns", true), SystemColumnImpl.getColumn("TRIGGERDEFINITION", -1, true, Integer.MAX_VALUE), SystemColumnImpl.getColumn("REFERENCINGOLD", 16, true), SystemColumnImpl.getColumn("REFERENCINGNEW", 16, true), SystemColumnImpl.getIdentifierColumn("OLDREFERENCINGNAME", true), SystemColumnImpl.getIdentifierColumn("NEWREFERENCINGNAME", true), SystemColumnImpl.getColumn("WHENCLAUSETEXT", -1, true, Integer.MAX_VALUE)};
   }

   private boolean getCharBoolean(DataValueDescriptor var1, char var2, char var3) throws StandardException {
      char var4 = var1.getString().charAt(0);
      if (var4 == var2) {
         return true;
      } else {
         return var4 != var3;
      }
   }

   public int getHeapColumnCount() throws StandardException {
      boolean var1 = this.dataDictionary.checkVersion(230, (String)null);
      int var2 = super.getHeapColumnCount();
      return var1 ? var2 : var2 - 1;
   }
}
