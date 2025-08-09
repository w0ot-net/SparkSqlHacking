package org.apache.derby.impl.sql.execute;

import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.StatisticsImpl;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.loader.ClassFactory;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.impl.services.daemon.IndexStatisticsDaemonImpl;
import org.apache.derby.shared.common.error.StandardException;

class CreateIndexConstantAction extends IndexConstantAction {
   private final boolean forCreateTable;
   private boolean unique;
   private boolean uniqueWithDuplicateNulls;
   private boolean uniqueDeferrable;
   private final boolean hasDeferrableChecking;
   private final boolean initiallyDeferred;
   private final int constraintType;
   private String indexType;
   private String[] columnNames;
   private boolean[] isAscending;
   private boolean isConstraint;
   private UUID conglomerateUUID;
   private Properties properties;
   private ExecRow indexTemplateRow;
   private long conglomId;
   private long droppedConglomNum;

   CreateIndexConstantAction(boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, int var6, String var7, String var8, String var9, String var10, UUID var11, String[] var12, boolean[] var13, boolean var14, UUID var15, Properties var16) {
      super(var11, var9, var10, var8);
      this.forCreateTable = var1;
      this.unique = var2 && !var4;
      this.uniqueWithDuplicateNulls = var3;
      this.hasDeferrableChecking = var4;
      this.initiallyDeferred = var5;
      this.constraintType = var6;
      this.uniqueDeferrable = var2 && var4;
      this.indexType = var7;
      this.columnNames = var12;
      this.isAscending = var13;
      this.isConstraint = var14;
      this.conglomerateUUID = var15;
      this.properties = var16;
      this.conglomId = -1L;
      this.droppedConglomNum = -1L;
   }

   CreateIndexConstantAction(ConglomerateDescriptor var1, TableDescriptor var2, Properties var3) {
      super(var2.getUUID(), var1.getConglomerateName(), var2.getName(), var2.getSchemaName());
      this.forCreateTable = false;
      this.droppedConglomNum = var1.getConglomerateNumber();
      IndexRowGenerator var4 = var1.getIndexDescriptor();
      this.unique = var4.isUnique();
      this.uniqueWithDuplicateNulls = var4.isUniqueWithDuplicateNulls();
      this.hasDeferrableChecking = false;
      this.uniqueDeferrable = false;
      this.initiallyDeferred = false;
      this.constraintType = -1;
      this.indexType = var4.indexType();
      this.columnNames = var1.getColumnNames();
      this.isAscending = var4.isAscending();
      this.isConstraint = var1.isConstraint();
      this.conglomerateUUID = var1.getUUID();
      this.properties = var3;
      this.conglomId = -1L;
      if (this.columnNames == null) {
         int[] var5 = var4.baseColumnPositions();
         this.columnNames = new String[var5.length];
         ColumnDescriptorList var6 = var2.getColumnDescriptorList();

         for(int var7 = 0; var7 < var5.length; ++var7) {
            this.columnNames[var7] = var6.elementAt(var5[var7] - 1).getColumnName();
         }
      }

   }

   public String toString() {
      return "CREATE INDEX " + this.indexName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      IndexRowGenerator var6 = null;
      int var14 = -1;
      LanguageConnectionContext var15 = var1.getLanguageConnectionContext();
      DataDictionary var16 = var15.getDataDictionary();
      DependencyManager var17 = var16.getDependencyManager();
      TransactionController var18 = var15.getTransactionExecute();
      var16.startWriting(var15);
      SchemaDescriptor var19 = var16.getSchemaDescriptor(this.schemaName, var18, true);
      TableDescriptor var2 = var1.getDDLTableDescriptor();
      if (var2 == null) {
         if (this.tableId != null) {
            var2 = var16.getTableDescriptor(this.tableId);
         } else {
            var2 = var16.getTableDescriptor(this.tableName, var19, var18);
         }
      }

      if (var2 == null) {
         throw StandardException.newException("X0Y38.S", new Object[]{this.indexName, this.tableName});
      } else if (var2.getTableType() == 1) {
         throw StandardException.newException("X0Y28.S", new Object[]{this.indexName, this.tableName});
      } else {
         this.lockTableForDDL(var18, var2.getHeapConglomerateId(), false);
         if (!this.forCreateTable) {
            var17.invalidateFor(var2, 3, var15);
         }

         int[] var5 = new int[this.columnNames.length];

         for(int var20 = 0; var20 < this.columnNames.length; ++var20) {
            ColumnDescriptor var4 = var2.getColumnDescriptor(this.columnNames[var20]);
            if (var4 == null) {
               throw StandardException.newException("42X14", new Object[]{this.columnNames[var20], this.tableName});
            }

            TypeId var21 = var4.getType().getTypeId();
            ClassFactory var22 = var15.getLanguageConnectionFactory().getClassFactory();
            boolean var23 = var21.orderable(var22);
            if (var23 && var21.userType()) {
               String var24 = var21.getCorrespondingJavaTypeName();

               try {
                  if (var22.isApplicationClass(var22.loadApplicationClass(var24))) {
                     var23 = false;
                  }
               } catch (ClassNotFoundException var43) {
                  var23 = false;
               }
            }

            if (!var23) {
               throw StandardException.newException("X0X67.S", new Object[]{var21.getSQLTypeName()});
            }

            var5[var20] = var4.getPosition();
            if (var14 < var5[var20]) {
               var14 = var5[var20];
            }
         }

         ConglomerateDescriptor[] var45 = var2.getConglomerateDescriptors();
         boolean var46 = false;

         for(int var47 = 0; var47 < var45.length; ++var47) {
            ConglomerateDescriptor var49 = var45[var47];
            if (var49.isIndex() && this.droppedConglomNum != var49.getConglomerateNumber()) {
               IndexRowGenerator var51 = var49.getIndexDescriptor();
               int[] var25 = var51.baseColumnPositions();
               boolean[] var26 = var51.isAscending();
               int var27 = 0;
               boolean var28 = (var51.isUnique() || !this.unique) && var25.length == var5.length && !this.hasDeferrableChecking;
               if (var28 && !var51.isUnique()) {
                  var28 = var51.isUniqueWithDuplicateNulls() || !this.uniqueWithDuplicateNulls;
               }

               if (var28 && this.indexType.equals(var51.indexType())) {
                  while(var27 < var25.length && var25[var27] == var5[var27] && var26[var27] == this.isAscending[var27]) {
                     ++var27;
                  }
               }

               if (var27 == var5.length) {
                  if (!this.isConstraint) {
                     var1.addWarning(StandardException.newWarning("01504", new Object[]{this.indexName, var49.getConglomerateName()}));
                     return;
                  }

                  this.conglomId = var49.getConglomerateNumber();
                  var6 = new IndexRowGenerator(this.indexType, this.unique, this.uniqueWithDuplicateNulls, false, false, var5, this.isAscending, var5.length);
                  this.conglomerateUUID = var16.getUUIDFactory().createUUID();
                  var46 = true;
                  break;
               }
            }
         }

         boolean var48 = this.droppedConglomNum > -1L;
         DataDescriptorGenerator var50 = var16.getDataDescriptorGenerator();
         if (var46 && !var48) {
            ConglomerateDescriptor var52 = var50.newConglomerateDescriptor(this.conglomId, this.indexName, true, var6, this.isConstraint, this.conglomerateUUID, var2.getUUID(), var19.getUUID());
            var16.addDescriptor(var52, var19, 0, false, var18);
            ConglomerateDescriptorList var54 = var2.getConglomerateDescriptorList();
            var54.add(var52);
         }

         Properties var53;
         if (this.properties != null) {
            var53 = this.properties;
         } else {
            var53 = new Properties();
         }

         var53.put("baseConglomerateId", Long.toString(var2.getHeapConglomerateId()));
         if (this.uniqueWithDuplicateNulls && !this.hasDeferrableChecking) {
            if (var16.checkVersion(160, (String)null)) {
               var53.put("uniqueWithDuplicateNulls", Boolean.toString(true));
            } else if (this.uniqueWithDuplicateNulls) {
               this.unique = true;
            }
         }

         var53.put("nUniqueColumns", Integer.toString(this.unique ? var5.length : var5.length + 1));
         var53.put("rowLocationColumn", Integer.toString(var5.length));
         var53.put("nKeyFields", Integer.toString(var5.length + 1));
         if (!var46) {
            if (var16.checkVersion(160, (String)null)) {
               var6 = new IndexRowGenerator(this.indexType, this.unique, this.uniqueWithDuplicateNulls, this.uniqueDeferrable, this.hasDeferrableChecking && this.constraintType != 6, var5, this.isAscending, var5.length);
            } else {
               var6 = new IndexRowGenerator(this.indexType, this.unique, false, false, false, var5, this.isAscending, var5.length);
            }
         }

         RowLocationRetRowSource var11 = null;
         long var12 = 0L;
         boolean var55 = false;
         int var56 = this.forCreateTable ? 1 : 16;
         int var57 = var2.getNumberOfColumns();
         int var58 = 0;
         FormatableBitSet var29 = new FormatableBitSet(var57 + 1);

         for(int var30 = 0; var30 < var5.length; ++var30) {
            var29.set(var5[var30]);
         }

         FormatableBitSet var59 = RowUtil.shift(var29, 1);
         GroupFetchScanController var10 = var18.openGroupFetchScan(var2.getHeapConglomerateId(), false, 0, 7, 5, var59, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
         ExecRow[] var7 = new ExecRow[var56];
         ExecIndexRow[] var8 = new ExecIndexRow[var56];
         ExecRow[] var9 = new ExecRow[var56];

         try {
            for(int var31 = 0; var31 < var56; ++var31) {
               var7[var31] = var1.getExecutionFactory().getValueRow(var14);
               var8[var31] = var6.getIndexRowTemplate();
               var9[var31] = var1.getExecutionFactory().getValueRow(var5.length);
            }

            this.indexTemplateRow = var8[0];
            ColumnDescriptorList var60 = var2.getColumnDescriptorList();
            int var32 = var60.size();
            int var33 = 0;

            for(int var34 = 0; var33 < var32; ++var33) {
               if (var59.get(var33)) {
                  ++var34;
                  ColumnDescriptor var35 = var60.elementAt(var33);
                  DataTypeDescriptor var36 = var35.getType();

                  for(int var37 = 0; var37 < var56; ++var37) {
                     var7[var37].setColumn(var33 + 1, var36.getNull());
                     var9[var37].setColumn(var34, var7[var37].getColumn(var33 + 1));
                  }

                  var58 += var36.getTypeId().getApproximateLengthInBytes(var36);
               }
            }

            RowLocation[] var64 = new RowLocation[var56];

            for(int var67 = 0; var67 < var56; ++var67) {
               var64[var67] = var10.newRowLocationTemplate();
               var6.getIndexRow(var9[var67], var64[var67], var8[var67], var29);
            }

            if (var46) {
               return;
            }

            Properties var71 = null;
            int var68;
            Object var69;
            if (!this.unique && !this.uniqueWithDuplicateNulls && !this.uniqueDeferrable) {
               var68 = var5.length + 1;
               var69 = new BasicSortObserver(true, false, this.indexTemplateRow, true);
            } else {
               String var73 = this.indexName;
               if (this.conglomerateUUID != null) {
                  ConglomerateDescriptor var38 = var16.getConglomerateDescriptor(this.conglomerateUUID);
                  if (this.isConstraint && var38 != null && var38.getUUID() != null && var2 != null) {
                     ConstraintDescriptor var39 = var16.getConstraintDescriptor(var2, var38.getUUID());
                     var73 = var39.getConstraintName();
                  }
               }

               if (!this.unique && !this.uniqueDeferrable) {
                  var68 = var5.length + 1;
                  var71 = new Properties();
                  var71.put("implType", "sort almost unique external");
                  var69 = new UniqueWithDuplicateNullsIndexSortObserver(var15, this.constraintID, true, this.hasDeferrableChecking && this.constraintType != 6, this.initiallyDeferred, var73, this.indexTemplateRow, true, var2.getName());
               } else {
                  var68 = this.unique ? var5.length : var5.length + 1;
                  var69 = new UniqueIndexSortObserver(var15, this.constraintID, true, this.uniqueDeferrable, this.initiallyDeferred, var73, this.indexTemplateRow, true, var2.getName());
               }
            }

            ColumnOrdering[] var74 = new ColumnOrdering[var68];

            for(int var76 = 0; var76 < var68; ++var76) {
               var74[var76] = new IndexColumnOrder(var76, !this.unique && var76 >= var68 - 1 ? true : this.isAscending[var76]);
            }

            var12 = var18.createSort(var71, this.indexTemplateRow.getRowArrayClone(), var74, (SortObserver)var69, false, var10.getEstimatedRowCount(), var58);
            var55 = true;
            var11 = this.loadSorter(var7, var8, var18, var10, var12, var64);
            this.conglomId = var18.createAndLoadConglomerate(this.indexType, this.indexTemplateRow.getRowArray(), var74, var6.getColumnCollationIds(var2.getColumnDescriptorList()), var53, 0, var11, (long[])null);
         } finally {
            if (var10 != null) {
               var10.close();
            }

            if (var11 != null) {
               var11.closeRowSource();
            }

            if (var55) {
               var18.dropSort(var12);
            }

         }

         ConglomerateController var61 = var18.openConglomerate(this.conglomId, false, 0, 7, 5);
         if (!var61.isKeyed()) {
            var61.close();
            throw StandardException.newException("X0X85.S", new Object[]{this.indexName, this.indexType});
         } else {
            var61.close();
            if (!var48) {
               ConglomerateDescriptor var62 = var50.newConglomerateDescriptor(this.conglomId, this.indexName, true, var6, this.isConstraint, this.conglomerateUUID, var2.getUUID(), var19.getUUID());
               var16.addDescriptor(var62, var19, 0, false, var18);
               ConglomerateDescriptorList var65 = var2.getConglomerateDescriptorList();
               var65.add(var62);
               this.conglomerateUUID = var62.getUUID();
            }

            CardinalityCounter var63 = (CardinalityCounter)var11;
            long var66 = var63.getRowCount();
            if (this.addStatistics(var16, var6, var66)) {
               long[] var70 = var63.getCardinality();

               for(int var72 = 0; var72 < var70.length; ++var72) {
                  StatisticsDescriptor var75 = new StatisticsDescriptor(var16, var16.getUUIDFactory().createUUID(), this.conglomerateUUID, var2.getUUID(), "I", new StatisticsImpl(var66, var70[var72]), var72 + 1);
                  var16.addDescriptor(var75, (TupleDescriptor)null, 14, true, var18);
               }
            }

         }
      }
   }

   private boolean addStatistics(DataDictionary var1, IndexRowGenerator var2, long var3) throws StandardException {
      boolean var5 = var3 > 0L;
      if (var1.checkVersion(210, (String)null) && ((IndexStatisticsDaemonImpl)var1.getIndexStatsRefresher(false)).skipDisposableStats && var5 && var2.isUnique() && var2.numberOfOrderedColumns() == 1) {
         var5 = false;
      }

      return var5;
   }

   ExecRow getIndexTemplateRow() {
      return this.indexTemplateRow;
   }

   long getCreatedConglomNumber() {
      return this.conglomId;
   }

   long getReplacedConglomNumber() {
      return this.droppedConglomNum;
   }

   UUID getCreatedUUID() {
      return this.conglomerateUUID;
   }

   private RowLocationRetRowSource loadSorter(ExecRow[] var1, ExecIndexRow[] var2, TransactionController var3, GroupFetchScanController var4, long var5, RowLocation[] var7) throws StandardException {
      long var9 = 0L;
      SortController var8 = var3.openSort(var5);

      try {
         int var11 = var1.length;
         DataValueDescriptor[][] var12 = new DataValueDescriptor[var11][];

         for(int var13 = 0; var13 < var11; ++var13) {
            var12[var13] = var1[var13].getRowArray();
         }

         int var18;
         while((var18 = var4.fetchNextGroup(var12, var7)) > 0) {
            for(int var14 = 0; var14 < var18; ++var14) {
               var8.insert(var2[var14].getRowArray());
               ++var9;
            }
         }

         var4.setEstimatedRowCount(var9);
      } finally {
         var8.completedInserts();
      }

      return new CardinalityCounter(var3.openSortRowSource(var5));
   }
}
