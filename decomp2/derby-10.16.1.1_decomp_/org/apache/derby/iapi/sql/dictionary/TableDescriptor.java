package org.apache.derby.iapi.sql.dictionary;

import java.util.List;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public class TableDescriptor extends UniqueSQLObjectDescriptor implements Provider, Dependent {
   public static final int BASE_TABLE_TYPE = 0;
   public static final int SYSTEM_TABLE_TYPE = 1;
   public static final int VIEW_TYPE = 2;
   public static final int GLOBAL_TEMPORARY_TABLE_TYPE = 3;
   public static final int SYNONYM_TYPE = 4;
   public static final int VTI_TYPE = 5;
   public static final char ROW_LOCK_GRANULARITY = 'R';
   public static final char TABLE_LOCK_GRANULARITY = 'T';
   public static final char DEFAULT_LOCK_GRANULARITY = 'R';
   public static final int ISTATS_CREATE_THRESHOLD = PropertyUtil.getSystemInt("derby.storage.indexStats.debug.createThreshold", 100);
   public static final int ISTATS_ABSDIFF_THRESHOLD = PropertyUtil.getSystemInt("derby.storage.indexStats.debug.absdiffThreshold", 1000);
   public static final double ISTATS_LNDIFF_THRESHOLD;
   private char lockGranularity;
   private boolean onCommitDeleteRows;
   private boolean onRollbackDeleteRows;
   private boolean indexStatsUpToDate;
   private String indexStatsUpdateReason;
   SchemaDescriptor schema;
   String tableName;
   UUID oid;
   int tableType;
   private volatile long heapConglomNumber;
   ColumnDescriptorList columnDescriptorList;
   ConglomerateDescriptorList conglomerateDescriptorList;
   ConstraintDescriptorList constraintDescriptorList;
   private TriggerDescriptorList triggerDescriptorList;
   ViewDescriptor viewDescriptor;
   private List statisticsDescriptorList;

   private FormatableBitSet referencedColumnMapGet() {
      LanguageConnectionContext var1 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      return var1.getReferencedColumnMap(this);
   }

   private void referencedColumnMapPut(FormatableBitSet var1) {
      LanguageConnectionContext var2 = (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
      if (var2 != null) {
         var2.setReferencedColumnMap(this, var1);
      }

   }

   public TableDescriptor(DataDictionary var1, String var2, SchemaDescriptor var3, int var4, boolean var5, boolean var6) {
      this(var1, var2, var3, var4, '\u0000');
      this.onCommitDeleteRows = var5;
      this.onRollbackDeleteRows = var6;
   }

   public TableDescriptor(DataDictionary var1, String var2, SchemaDescriptor var3, int var4, char var5) {
      super(var1);
      this.indexStatsUpToDate = true;
      this.heapConglomNumber = -1L;
      this.schema = var3;
      this.tableName = var2;
      this.tableType = var4;
      this.lockGranularity = var5;
      this.conglomerateDescriptorList = new ConglomerateDescriptorList();
      this.columnDescriptorList = new ColumnDescriptorList();
      this.constraintDescriptorList = new ConstraintDescriptorList();
      this.triggerDescriptorList = new TriggerDescriptorList();
   }

   public String getSchemaName() {
      return this.schema.getSchemaName();
   }

   public SchemaDescriptor getSchemaDescriptor() {
      return this.schema;
   }

   public String getName() {
      return this.tableName;
   }

   public void setTableName(String var1) {
      this.tableName = var1;
   }

   public String getQualifiedName() {
      return IdUtil.mkQualifiedName(this.getSchemaName(), this.getName());
   }

   public UUID getUUID() {
      return this.oid;
   }

   public int getTableType() {
      return this.tableType;
   }

   public long getHeapConglomerateId() throws StandardException {
      ConglomerateDescriptor var1 = null;
      if (this.heapConglomNumber != -1L) {
         return this.heapConglomNumber;
      } else {
         ConglomerateDescriptor[] var2 = this.getConglomerateDescriptors();

         for(int var3 = 0; var3 < var2.length; ++var3) {
            var1 = var2[var3];
            if (!var1.isIndex()) {
               break;
            }
         }

         this.heapConglomNumber = var1.getConglomerateNumber();
         return this.heapConglomNumber;
      }
   }

   public int getNumberOfColumns() {
      return this.getColumnDescriptorList().size();
   }

   public FormatableBitSet getReferencedColumnMap() {
      return this.referencedColumnMapGet();
   }

   public void setReferencedColumnMap(FormatableBitSet var1) {
      this.referencedColumnMapPut(var1);
   }

   public FormatableBitSet makeColumnMap(ColumnDescriptorList var1) {
      FormatableBitSet var2 = new FormatableBitSet(this.columnDescriptorList.size() + 1);
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ColumnDescriptor var5 = var1.elementAt(var4);
         var2.set(var5.getPosition());
      }

      return var2;
   }

   public int getMaxColumnID() throws StandardException {
      int var1 = 1;

      for(ColumnDescriptor var3 : this.columnDescriptorList) {
         var1 = Math.max(var1, var3.getPosition());
      }

      return var1;
   }

   public void setUUID(UUID var1) {
      this.oid = var1;
   }

   public char getLockGranularity() {
      return this.lockGranularity;
   }

   public void setLockGranularity(char var1) {
      this.lockGranularity = var1;
   }

   public boolean isOnRollbackDeleteRows() {
      return this.onRollbackDeleteRows;
   }

   public boolean isOnCommitDeleteRows() {
      return this.onCommitDeleteRows;
   }

   public void resetHeapConglomNumber() {
      this.heapConglomNumber = -1L;
   }

   public ExecRow getEmptyExecRow() throws StandardException {
      int var1 = this.getNumberOfColumns();
      ExecRow var2 = this.getDataDictionary().getExecutionFactory().getValueRow(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         ColumnDescriptor var4 = this.columnDescriptorList.elementAt(var3);
         DataValueDescriptor var5 = var4.getType().getNull();
         var2.setColumn(var3 + 1, var5);
      }

      return var2;
   }

   public int[] getColumnCollationIds() throws StandardException {
      int[] var1 = new int[this.getNumberOfColumns()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         ColumnDescriptor var3 = this.columnDescriptorList.elementAt(var2);
         var1[var2] = var3.getType().getCollationType();
      }

      return var1;
   }

   public ConglomerateDescriptorList getConglomerateDescriptorList() {
      return this.conglomerateDescriptorList;
   }

   public ViewDescriptor getViewDescriptor() {
      return this.viewDescriptor;
   }

   public void setViewDescriptor(ViewDescriptor var1) {
      this.viewDescriptor = var1;
   }

   public boolean isPersistent() {
      return this.tableType == 3 ? false : super.isPersistent();
   }

   public boolean isSynonymDescriptor() {
      return this.tableType == 4;
   }

   public int getTotalNumberOfIndexes() throws StandardException {
      return this.getQualifiedNumberOfIndexes(0, false);
   }

   public int getQualifiedNumberOfIndexes(int var1, boolean var2) {
      int var3 = 0;

      for(ConglomerateDescriptor var5 : this.conglomerateDescriptorList) {
         if (var5.isIndex()) {
            IndexRowGenerator var6 = var5.getIndexDescriptor();
            if (var6.numberOfOrderedColumns() >= var1 || var2 && !var6.isUnique()) {
               ++var3;
            }
         }
      }

      return var3;
   }

   public void getAllRelevantTriggers(int var1, int[] var2, TriggerDescriptorList var3) throws StandardException {
      DataDictionary var4 = this.getDataDictionary();

      for(TriggerDescriptor var6 : var4.getTriggerDescriptors(this)) {
         if (var6.needsToFire(var1, var2)) {
            var3.add(var6);
         }
      }

   }

   public void getAllRelevantConstraints(int var1, int[] var2, boolean[] var3, ConstraintDescriptorList var4) throws StandardException {
      DataDictionary var5 = this.getDataDictionary();
      ConstraintDescriptorList var6 = var5.getConstraintDescriptors(this);
      int var7 = var6.size();

      for(int var8 = 0; var8 < var7; ++var8) {
         ConstraintDescriptor var9 = var6.elementAt(var8);
         if (!var3[0] && var9 instanceof ReferencedKeyConstraintDescriptor && var1 != 3 && var1 != 2) {
            var3[0] = ((ReferencedKeyConstraintDescriptor)var9).hasSelfReferencingFK(var6, 1);
         }

         if (var9.needsToFire(var1, var2)) {
            if (var9 instanceof ReferencedKeyConstraintDescriptor && (var1 == 3 || var1 == 2)) {
               var3[0] = true;
            }

            var4.add(var9);
         }
      }

   }

   public DependableFinder getDependableFinder() {
      return this.referencedColumnMapGet() == null ? this.getDependableFinder(137) : this.getColumnDependableFinder(393, this.referencedColumnMapGet().getByteArray());
   }

   public String getObjectName() {
      if (this.referencedColumnMapGet() == null) {
         return this.tableName;
      } else {
         StringBuilder var1 = new StringBuilder();
         var1.append(this.tableName);
         boolean var2 = true;

         for(ColumnDescriptor var4 : this.columnDescriptorList) {
            if (this.referencedColumnMapGet().isSet(var4.getPosition())) {
               if (var2) {
                  var1.append("(").append(var4.getColumnName());
                  var2 = false;
               } else {
                  var1.append(", ").append(var4.getColumnName());
               }
            }
         }

         if (!var2) {
            var1.append(")");
         }

         return var1.toString();
      }
   }

   public UUID getObjectID() {
      return this.oid;
   }

   public String getClassType() {
      return "Table";
   }

   public String toString() {
      return "";
   }

   public ColumnDescriptorList getColumnDescriptorList() {
      return this.columnDescriptorList;
   }

   public ColumnDescriptorList getGeneratedColumns() {
      ColumnDescriptorList var1 = this.getColumnDescriptorList();
      ColumnDescriptorList var2 = new ColumnDescriptorList();
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ColumnDescriptor var5 = var1.elementAt(var4);
         if (var5.hasGenerationClause()) {
            var2.add(this.oid, var5);
         }
      }

      return var2;
   }

   public int[] getColumnIDs(String[] var1) {
      int var2 = var1.length;
      int[] var3 = new int[var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         var3[var4] = this.getColumnDescriptor(var1[var4]).getPosition();
      }

      return var3;
   }

   public ConstraintDescriptorList getConstraintDescriptorList() throws StandardException {
      return this.constraintDescriptorList;
   }

   public void setConstraintDescriptorList(ConstraintDescriptorList var1) {
      this.constraintDescriptorList = var1;
   }

   public void emptyConstraintDescriptorList() throws StandardException {
      this.constraintDescriptorList = new ConstraintDescriptorList();
   }

   public ReferencedKeyConstraintDescriptor getPrimaryKey() throws StandardException {
      ConstraintDescriptorList var1 = this.getDataDictionary().getConstraintDescriptors(this);
      return var1.getPrimaryKey();
   }

   public TriggerDescriptorList getTriggerDescriptorList() throws StandardException {
      return this.triggerDescriptorList;
   }

   public void setTriggerDescriptorList(TriggerDescriptorList var1) {
      this.triggerDescriptorList = var1;
   }

   public void emptyTriggerDescriptorList() throws StandardException {
      this.triggerDescriptorList = new TriggerDescriptorList();
   }

   public boolean tableNameEquals(String var1, String var2) {
      String var3 = this.getSchemaName();
      if (var3 != null && var2 != null) {
         return var3.equals(var2) && this.tableName.equals(var1);
      } else {
         return this.tableName.equals(var1);
      }
   }

   public void removeConglomerateDescriptor(ConglomerateDescriptor var1) throws StandardException {
      this.conglomerateDescriptorList.dropConglomerateDescriptor(this.getUUID(), var1);
   }

   public void removeConstraintDescriptor(ConstraintDescriptor var1) throws StandardException {
      this.constraintDescriptorList.remove(var1);
   }

   public ColumnDescriptor getColumnDescriptor(String var1) {
      return this.columnDescriptorList.getColumnDescriptor(this.oid, var1);
   }

   public ColumnDescriptor getColumnDescriptor(int var1) {
      return this.columnDescriptorList.getColumnDescriptor(this.oid, var1);
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors() {
      int var1 = this.conglomerateDescriptorList.size();
      ConglomerateDescriptor[] var2 = new ConglomerateDescriptor[var1];
      this.conglomerateDescriptorList.toArray(var2);
      return var2;
   }

   public ConglomerateDescriptor getConglomerateDescriptor(long var1) throws StandardException {
      return this.conglomerateDescriptorList.getConglomerateDescriptor(var1);
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(long var1) throws StandardException {
      return this.conglomerateDescriptorList.getConglomerateDescriptors(var1);
   }

   public ConglomerateDescriptor getConglomerateDescriptor(UUID var1) throws StandardException {
      return this.conglomerateDescriptorList.getConglomerateDescriptor(var1);
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(UUID var1) throws StandardException {
      return this.conglomerateDescriptorList.getConglomerateDescriptors(var1);
   }

   public IndexLister getIndexLister() throws StandardException {
      return new IndexLister(this);
   }

   public boolean tableHasAutoincrement() {
      for(ColumnDescriptor var2 : this.columnDescriptorList) {
         if (var2.isAutoincrement()) {
            return true;
         }
      }

      return false;
   }

   public String[] getColumnNamesArray() {
      int var1 = this.getNumberOfColumns();
      String[] var2 = new String[var1];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = this.getColumnDescriptor(var3 + 1).getColumnName();
      }

      return var2;
   }

   public long[] getAutoincIncrementArray() {
      if (!this.tableHasAutoincrement()) {
         return null;
      } else {
         int var1 = this.getNumberOfColumns();
         long[] var2 = new long[var1];

         for(int var3 = 0; var3 < var1; ++var3) {
            ColumnDescriptor var4 = this.getColumnDescriptor(var3 + 1);
            if (var4.isAutoincrement()) {
               var2[var3] = var4.getAutoincInc();
            }
         }

         return var2;
      }
   }

   public synchronized List getStatistics() throws StandardException {
      if (this.statisticsDescriptorList != null) {
         return this.statisticsDescriptorList;
      } else {
         DataDictionary var1 = this.getDataDictionary();
         return this.statisticsDescriptorList = var1.getStatisticsDescriptors(this);
      }
   }

   public void markForIndexStatsUpdate(long var1) throws StandardException {
      List var3 = this.getStatistics();
      if (var3.isEmpty() && var1 >= (long)ISTATS_CREATE_THRESHOLD) {
         this.indexStatsUpToDate = false;
         this.indexStatsUpdateReason = "no stats, row-estimate=" + var1;
      } else {
         for(StatisticsDescriptor var5 : var3) {
            long var6 = var5.getStatistic().getRowEstimate();
            long var8 = Math.abs(var1 - var6);
            if (var8 >= (long)ISTATS_ABSDIFF_THRESHOLD) {
               double var10 = Math.abs(Math.log((double)var6) - Math.log((double)var1));
               if (Double.compare(var10, ISTATS_LNDIFF_THRESHOLD) == 1) {
                  this.indexStatsUpToDate = false;
                  this.indexStatsUpdateReason = "t-est=" + var1 + ", i-est=" + var6 + " => cmp=" + var10;
                  break;
               }
            }
         }

      }
   }

   public boolean getAndClearIndexStatsIsUpToDate() {
      boolean var1 = this.indexStatsUpToDate;
      this.indexStatsUpToDate = true;
      return var1;
   }

   public String getIndexStatsUpdateReason() {
      return this.indexStatsUpdateReason;
   }

   public boolean statisticsExist(ConglomerateDescriptor var1) throws StandardException {
      List var2 = this.getStatistics();
      if (var1 == null) {
         return var2.size() > 0;
      } else {
         UUID var3 = var1.getUUID();

         for(StatisticsDescriptor var5 : var2) {
            if (var3.equals(var5.getReferenceID())) {
               return true;
            }
         }

         return false;
      }
   }

   public double selectivityForConglomerate(ConglomerateDescriptor var1, int var2) throws StandardException {
      UUID var3 = var1.getUUID();

      for(StatisticsDescriptor var5 : this.getStatistics()) {
         if (var3.equals(var5.getReferenceID()) && var5.getColumnCount() == var2) {
            return var5.getStatistic().selectivity((Object[])null);
         }
      }

      return Math.pow(0.1, (double)var2);
   }

   public String getDescriptorName() {
      return this.tableName;
   }

   public String getDescriptorType() {
      return this.tableType == 4 ? "Synonym" : "Table/View";
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      DependencyManager var4 = this.getDataDictionary().getDependencyManager();
      switch (var2) {
         default -> throw StandardException.newException("X0Y29.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), this.getQualifiedName()});
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
   }

   public static String makeSequenceName(UUID var0) {
      return var0.toANSIidentifier();
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   static {
      double var0 = (double)1.0F;

      try {
         String var2 = PropertyUtil.getSystemProperty("derby.storage.indexStats.debug.lndiffThreshold");
         if (var2 != null) {
            var0 = Double.parseDouble(var2);
         }
      } catch (NumberFormatException var3) {
      }

      ISTATS_LNDIFF_THRESHOLD = var0;
   }
}
