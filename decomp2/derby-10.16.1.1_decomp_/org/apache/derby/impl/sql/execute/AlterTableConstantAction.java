package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.IndexDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.catalog.types.StatisticsImpl;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.services.io.StreamStorable;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.PreparedStatement;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.compile.CompilerContext;
import org.apache.derby.iapi.sql.compile.Parser;
import org.apache.derby.iapi.sql.compile.Visitable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.CheckConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.DependencyDescriptor;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.IndexLister;
import org.apache.derby.iapi.sql.dictionary.IndexRowGenerator;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.sql.dictionary.StatisticsDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.execute.ExecIndexRow;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.GroupFetchScanController;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.RowLocationRetRowSource;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.iapi.types.TypeId;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.impl.sql.compile.StatementNode;
import org.apache.derby.shared.common.error.StandardException;

class AlterTableConstantAction extends DDLSingleTableConstantAction implements RowLocationRetRowSource {
   private static final int RANGE_TOP = 0;
   private static final int RANGE_BOTTOM = 1;
   private SchemaDescriptor sd;
   private String tableName;
   private UUID schemaId;
   private int tableType;
   private ColumnInfo[] columnInfo;
   private ConstraintConstantAction[] constraintActions;
   private char lockGranularity;
   private long tableConglomerateId;
   private boolean compressTable;
   private int behavior;
   private boolean sequential;
   private boolean truncateTable;
   private boolean purge;
   private boolean defragment;
   private boolean truncateEndOfTable;
   private boolean updateStatistics;
   private boolean updateStatisticsAll;
   private boolean dropStatistics;
   private boolean dropStatisticsAll;
   private String indexNameForStatistics;
   private boolean doneScan;
   private boolean[] needToDropSort;
   private boolean[] validRow;
   private int bulkFetchSize = 16;
   private int currentCompressRow;
   private int numIndexes;
   private int rowCount;
   private long estimatedRowCount;
   private long[] indexConglomerateNumbers;
   private long[] sortIds;
   private FormatableBitSet indexedCols;
   private ConglomerateController compressHeapCC;
   private ExecIndexRow[] indexRows;
   private ExecRow[] baseRow;
   private ExecRow currentRow;
   private GroupFetchScanController compressHeapGSC;
   private IndexRowGenerator[] compressIRGs;
   private DataValueDescriptor[][] baseRowArray;
   private RowLocation[] compressRL;
   private SortController[] sorters;
   private int droppedColumnPosition;
   private ColumnOrdering[][] ordering;
   private int[][] collation;
   private TableDescriptor td;
   private LanguageConnectionContext lcc;
   private DataDictionary dd;
   private DependencyManager dm;
   private TransactionController tc;
   private Activation activation;

   AlterTableConstantAction(SchemaDescriptor var1, String var2, UUID var3, long var4, int var6, ColumnInfo[] var7, ConstraintConstantAction[] var8, char var9, boolean var10, int var11, boolean var12, boolean var13, boolean var14, boolean var15, boolean var16, boolean var17, boolean var18, boolean var19, boolean var20, String var21) {
      super(var3);
      this.sd = var1;
      this.tableName = var2;
      this.tableConglomerateId = var4;
      this.tableType = var6;
      this.columnInfo = var7;
      this.constraintActions = var8;
      this.lockGranularity = var9;
      this.compressTable = var10;
      this.behavior = var11;
      this.sequential = var12;
      this.truncateTable = var13;
      this.purge = var14;
      this.defragment = var15;
      this.truncateEndOfTable = var16;
      this.updateStatistics = var17;
      this.updateStatisticsAll = var18;
      this.dropStatistics = var19;
      this.dropStatisticsAll = var20;
      this.indexNameForStatistics = var21;
   }

   public String toString() {
      return this.truncateTable ? "TRUNCATE TABLE " + this.tableName : "ALTER TABLE " + this.tableName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      try {
         this.executeConstantActionBody(var1);
      } finally {
         this.clearState();
      }

   }

   private void executeConstantActionBody(Activation var1) throws StandardException {
      this.activation = var1;
      this.lcc = var1.getLanguageConnectionContext();
      this.dd = this.lcc.getDataDictionary();
      this.dm = this.dd.getDependencyManager();
      this.tc = this.lcc.getTransactionExecute();
      int var2 = 0;
      boolean var3 = false;
      if (this.compressTable || this.truncateTable) {
         DeferredConstraintsMemory.compressOrTruncate(this.lcc, this.tableId, this.tableName);
      }

      if (!this.compressTable || !this.purge && !this.defragment && !this.truncateEndOfTable) {
         if (this.updateStatistics) {
            this.updateStatistics();
         } else if (this.dropStatistics) {
            this.dropStatistics();
         } else {
            this.dd.startWriting(this.lcc);
            if (this.tableConglomerateId == 0L) {
               this.td = this.dd.getTableDescriptor(this.tableId);
               if (this.td == null) {
                  throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
               }

               this.tableConglomerateId = this.td.getHeapConglomerateId();
            }

            this.lockTableForDDL(this.tc, this.tableConglomerateId, true);
            this.td = this.dd.getTableDescriptor(this.tableId);
            if (this.td == null) {
               throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
            } else {
               if (this.truncateTable) {
                  this.dm.invalidateFor(this.td, 42, this.lcc);
               } else {
                  this.dm.invalidateFor(this.td, 12, this.lcc);
               }

               var1.setDDLTableDescriptor(this.td);
               if (this.sd == null) {
                  this.sd = getAndCheckSchemaDescriptor(this.dd, this.schemaId, "ALTER TABLE");
               }

               if (this.truncateTable) {
                  this.dm.invalidateFor(this.td, 42, this.lcc);
               } else {
                  this.dm.invalidateFor(this.td, 12, this.lcc);
               }

               if (this.columnInfo != null) {
                  boolean var4 = false;

                  for(int var5 = 0; var5 < this.columnInfo.length; ++var5) {
                     if (this.columnInfo[var5].action == 0 && !this.columnInfo[var5].dataType.isNullable() && this.columnInfo[var5].defaultInfo == null && this.columnInfo[var5].autoincInc == 0L) {
                        var4 = true;
                     }
                  }

                  if (var4) {
                     var2 = this.getSemiRowCount(this.tc);
                     if (var2 > 0) {
                        throw StandardException.newException("X0Y57.S", new Object[]{this.td.getQualifiedName()});
                     }

                     var3 = true;
                  }

                  for(int var11 = 0; var11 < this.columnInfo.length; ++var11) {
                     if (this.columnInfo[var11].action == 0) {
                        this.addNewColumnToTable(var11);
                     } else if (this.columnInfo[var11].action != 5 && this.columnInfo[var11].action != 6 && this.columnInfo[var11].action != 10 && this.columnInfo[var11].action != 7) {
                        if (this.columnInfo[var11].action == 2) {
                           this.modifyColumnType(var11);
                        } else if (this.columnInfo[var11].action == 3) {
                           this.modifyColumnConstraint(this.columnInfo[var11].name, true);
                        } else if (this.columnInfo[var11].action == 4) {
                           if (!var3) {
                              var3 = true;
                              var2 = this.getSemiRowCount(this.tc);
                           }

                           String[] var6 = new String[]{this.columnInfo[var11].name};
                           boolean[] var7 = new boolean[1];
                           if (this.validateNotNullConstraint(var6, var7, var2, this.lcc, "X0Y80.S")) {
                              this.modifyColumnConstraint(this.columnInfo[var11].name, false);
                           }
                        } else if (this.columnInfo[var11].action == 1) {
                           this.dropColumnFromTable(this.columnInfo[var11].name);
                        } else if (this.columnInfo[var11].action == 8 || this.columnInfo[var11].action == 9) {
                           this.modifyIdentityState(var11);
                        }
                     } else {
                        this.modifyColumnDefault(var11);
                     }
                  }
               }

               this.adjustUDTDependencies(this.lcc, this.dd, this.td, this.columnInfo, false);
               if (this.constraintActions != null) {
                  for(int var10 = 0; var10 < this.constraintActions.length; ++var10) {
                     ConstraintConstantAction var12 = this.constraintActions[var10];
                     boolean var13 = false;
                     if (var12 instanceof CreateConstraintConstantAction) {
                        CreateConstraintConstantAction var14 = (CreateConstraintConstantAction)var12;
                        int var8 = var14.getConstraintType();
                        var13 = var8 == 4 && var14.isInitiallyDeferred();
                        switch (var8) {
                           case 2:
                              ConstraintDescriptorList var9 = this.dd.getConstraintDescriptors(this.td);
                              if (var9.getPrimaryKey() != null) {
                                 throw StandardException.newException("X0Y58.S", new Object[]{this.td.getQualifiedName()});
                              }

                              if (!var3) {
                                 var3 = true;
                                 var2 = this.getSemiRowCount(this.tc);
                              }
                              break;
                           case 4:
                              if (!var3) {
                                 var3 = true;
                                 var2 = this.getSemiRowCount(this.tc);
                              }

                              if (var13) {
                                 this.constraintActions[var10].executeConstantAction(var1);
                              }

                              if (var2 > 0) {
                                 ConstraintConstantAction.validateConstraint(var12.getConstraintName(), ((CreateConstraintConstantAction)var12).getConstraintText(), var12.getConstraintId(), this.td, this.lcc, true, var13);
                              }
                        }
                     }

                     if (!var13) {
                        this.constraintActions[var10].executeConstantAction(var1);
                     }
                  }
               }

               if (this.lockGranularity != 0) {
                  this.td.setLockGranularity(this.lockGranularity);
                  this.dd.updateLockGranularity(this.td, this.sd, this.lockGranularity, this.tc);
               }

               if (this.compressTable) {
                  this.compressTable();
               }

               if (this.truncateTable) {
                  this.truncateTable();
               }

            }
         }
      } else {
         this.td = this.dd.getTableDescriptor(this.tableId);
         if (this.td == null) {
            throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
         } else {
            if (this.purge) {
               this.purgeRows(this.tc);
            }

            if (this.defragment) {
               this.defragmentRows(this.tc);
            }

            if (this.truncateEndOfTable) {
               this.truncateEnd(this.tc);
            }

         }
      }
   }

   private void clearState() {
      this.td = null;
      this.lcc = null;
      this.dd = null;
      this.dm = null;
      this.tc = null;
      this.activation = null;
   }

   private void dropStatistics() throws StandardException {
      this.td = this.dd.getTableDescriptor(this.tableId);
      this.dd.startWriting(this.lcc);
      this.dm.invalidateFor(this.td, 40, this.lcc);
      if (this.dropStatisticsAll) {
         this.dd.dropStatisticsDescriptors(this.td.getUUID(), (UUID)null, this.tc);
      } else {
         ConglomerateDescriptor var1 = this.dd.getConglomerateDescriptor(this.indexNameForStatistics, this.sd, false);
         this.dd.dropStatisticsDescriptors(this.td.getUUID(), var1.getUUID(), this.tc);
      }

   }

   private void updateStatistics() throws StandardException {
      this.td = this.dd.getTableDescriptor(this.tableId);
      ConglomerateDescriptor[] var1;
      if (this.updateStatisticsAll) {
         var1 = null;
      } else {
         var1 = new ConglomerateDescriptor[]{this.dd.getConglomerateDescriptor(this.indexNameForStatistics, this.sd, false)};
      }

      this.dd.getIndexStatsRefresher(false).runExplicitly(this.lcc, this.td, var1, "ALTER TABLE");
   }

   private void truncateEnd(TransactionController var1) throws StandardException {
      switch (this.td.getTableType()) {
         default:
            ConglomerateDescriptor[] var2 = this.td.getConglomerateDescriptors();

            for(int var3 = 0; var3 < var2.length; ++var3) {
               ConglomerateDescriptor var4 = var2[var3];
               var1.compressConglomerate(var4.getConglomerateNumber());
            }
         case 2:
         case 5:
      }
   }

   private void defragmentRows(TransactionController var1) throws StandardException {
      GroupFetchScanController var2 = null;
      int var3 = 0;
      int[][] var4 = null;
      ScanController[] var5 = null;
      ConglomerateController[] var6 = null;
      DataValueDescriptor[][] var7 = null;
      TransactionController var8 = null;
      boolean var20 = false;

      label323: {
         try {
            var20 = true;
            var8 = var1.startNestedUserTransaction(false, true);
            ExecRow var9;
            Iterator var10;
            switch (this.td.getTableType()) {
               case 2:
               case 5:
                  var20 = false;
                  break label323;
               default:
                  var9 = this.lcc.getLanguageConnectionFactory().getExecutionFactory().getValueRow(this.td.getNumberOfColumns());
                  var10 = this.td.getColumnDescriptorList().iterator();
            }

            while(var10.hasNext()) {
               ColumnDescriptor var11 = (ColumnDescriptor)var10.next();
               var9.setColumn(var11.getPosition(), var11.getType().getNull());
            }

            DataValueDescriptor[][] var27 = new DataValueDescriptor[100][];
            var27[0] = var9.getRowArray();
            RowLocation[] var28 = new RowLocation[100];
            RowLocation[] var12 = new RowLocation[100];
            ConglomerateDescriptor[] var13 = this.td.getConglomerateDescriptors();
            var3 = var13.length - 1;
            if (var3 > 0) {
               var4 = new int[var3][];
               var5 = new ScanController[var3];
               var6 = new ConglomerateController[var3];
               var7 = new DataValueDescriptor[var3][];
               setup_indexes(var8, this.td, var4, var5, var6, var7);
            }

            var2 = var8.defragmentConglomerate(this.td.getHeapConglomerateId(), false, true, 4, 7, 5);

            int var14;
            while((var14 = var2.fetchNextGroup(var27, var28, var12)) != 0) {
               if (var3 > 0) {
                  for(int var15 = 0; var15 < var14; ++var15) {
                     for(int var16 = 0; var16 < var3; ++var16) {
                        fixIndex(var27[var15], var7[var16], var28[var15], var12[var15], var6[var16], var5[var16], var4[var16]);
                     }
                  }
               }
            }

            var8.commit();
            var20 = false;
         } finally {
            if (var20) {
               if (var2 != null) {
                  var2.close();
                  Object var22 = null;
               }

               if (var3 > 0) {
                  for(int var18 = 0; var18 < var3; ++var18) {
                     if (var5 != null && var5[var18] != null) {
                        var5[var18].close();
                        var5[var18] = null;
                     }

                     if (var6 != null && var6[var18] != null) {
                        var6[var18].close();
                        var6[var18] = null;
                     }
                  }
               }

               if (var8 != null) {
                  var8.destroy();
               }

            }
         }

         if (var2 != null) {
            var2.close();
            Object var23 = null;
         }

         if (var3 > 0) {
            for(int var25 = 0; var25 < var3; ++var25) {
               if (var5 != null && var5[var25] != null) {
                  var5[var25].close();
                  var5[var25] = null;
               }

               if (var6 != null && var6[var25] != null) {
                  var6[var25].close();
                  var6[var25] = null;
               }
            }
         }

         if (var8 != null) {
            var8.destroy();
         }

         return;
      }

      if (var2 != null) {
         var2.close();
         Object var24 = null;
      }

      if (var3 > 0) {
         for(int var26 = 0; var26 < var3; ++var26) {
            if (var5 != null && var5[var26] != null) {
               var5[var26].close();
               var5[var26] = null;
            }

            if (var6 != null && var6[var26] != null) {
               var6[var26].close();
               var6[var26] = null;
            }
         }
      }

      if (var8 != null) {
         var8.destroy();
      }

   }

   private static void setup_indexes(TransactionController var0, TableDescriptor var1, int[][] var2, ScanController[] var3, ConglomerateController[] var4, DataValueDescriptor[][] var5) throws StandardException {
      ConglomerateDescriptor[] var6 = var1.getConglomerateDescriptors();
      int var7 = 0;

      for(int var8 = 0; var8 < var6.length; ++var8) {
         ConglomerateDescriptor var9 = var6[var8];
         if (var9.isIndex()) {
            var3[var7] = var0.openScan(var9.getConglomerateNumber(), true, 4, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
            var4[var7] = var0.openConglomerate(var9.getConglomerateNumber(), true, 4, 7, 5);
            int[] var10 = var9.getIndexDescriptor().baseColumnPositions();
            int[] var11 = new int[var10.length];

            for(int var12 = 0; var12 < var10.length; ++var12) {
               var11[var12] = var10[var12] - 1;
            }

            var2[var7] = var11;
            var5[var7] = new DataValueDescriptor[var10.length + 1];
            ++var7;
         }
      }

   }

   private static void fixIndex(DataValueDescriptor[] var0, DataValueDescriptor[] var1, RowLocation var2, RowLocation var3, ConglomerateController var4, ScanController var5, int[] var6) throws StandardException {
      for(int var7 = 0; var7 < var6.length; ++var7) {
         var1[var7] = var0[var6[var7]];
      }

      var1[var1.length - 1] = var2;
      var5.reopenScan(var1, 1, (Qualifier[][])null, var1, -1);
      if (var5.next()) {
         var5.delete();
      }

      var1[var1.length - 1] = var3;
      var4.insert(var1);
   }

   private void purgeRows(TransactionController var1) throws StandardException {
      switch (this.td.getTableType()) {
         default:
            ConglomerateDescriptor[] var2 = this.td.getConglomerateDescriptors();

            for(int var3 = 0; var3 < var2.length; ++var3) {
               ConglomerateDescriptor var4 = var2[var3];
               var1.purgeConglomerate(var4.getConglomerateNumber());
            }
         case 2:
         case 5:
      }
   }

   private void addNewColumnToTable(int var1) throws StandardException {
      ColumnDescriptor var2 = this.td.getColumnDescriptor(this.columnInfo[var1].name);
      int var4 = this.td.getMaxColumnID() + var1;
      if (var2 != null) {
         throw StandardException.newException("X0Y32.S", new Object[]{var2.getDescriptorType(), this.columnInfo[var1].name, this.td.getDescriptorType(), this.td.getQualifiedName()});
      } else {
         DataValueDescriptor var3;
         if (this.columnInfo[var1].defaultValue != null) {
            var3 = this.columnInfo[var1].defaultValue;
         } else {
            var3 = this.columnInfo[var1].dataType.getNull();
         }

         this.tc.addColumnToConglomerate(this.td.getHeapConglomerateId(), var4, var3, this.columnInfo[var1].dataType.getCollationType());
         UUID var5 = this.columnInfo[var1].newDefaultUUID;
         if (this.columnInfo[var1].defaultInfo != null && var5 == null) {
            var5 = this.dd.getUUIDFactory().createUUID();
         }

         var2 = new ColumnDescriptor(this.columnInfo[var1].name, var4 + 1, this.columnInfo[var1].dataType, this.columnInfo[var1].defaultValue, this.columnInfo[var1].defaultInfo, this.td, var5, this.columnInfo[var1].autoincStart, this.columnInfo[var1].autoincInc, this.columnInfo[var1].autoinc_create_or_modify_Start_Increment, this.columnInfo[var1].autoincCycle);
         this.dd.addDescriptor(var2, this.td, 2, false, this.tc);
         this.td.getColumnDescriptorList().add(var2);
         if (var2.isAutoincrement()) {
            CreateSequenceConstantAction var6 = CreateTableConstantAction.makeCSCA(this.columnInfo[var1], TableDescriptor.makeSequenceName(this.td.getUUID()));
            var6.executeConstantAction(this.activation);
         }

         if (var2.isAutoincrement() || var2.hasNonNullDefault()) {
            this.updateNewColumnToDefault(var2);
         }

         this.addColumnDependencies(this.lcc, this.dd, this.td, this.columnInfo[var1]);
         this.dd.updateSYSCOLPERMSforAddColumnToUserTable(this.td.getUUID(), this.tc);
      }
   }

   private void dropColumnFromTable(String var1) throws StandardException {
      boolean var2 = this.behavior == 0;
      ColumnDescriptorList var3 = this.td.getGeneratedColumns();
      int var4 = var3.size();
      ArrayList var5 = new ArrayList();

      for(int var6 = 0; var6 < var4; ++var6) {
         ColumnDescriptor var7 = var3.elementAt(var6);
         String[] var8 = var7.getDefaultInfo().getReferencedColumnNames();
         int var9 = var8.length;

         for(int var10 = 0; var10 < var9; ++var10) {
            if (var1.equals(var8[var10])) {
               String var11 = var7.getColumnName();
               if (!var2) {
                  throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), var1, "GENERATED COLUMN", var11});
               }

               var5.add(var11);
            }
         }
      }

      DataDescriptorGenerator var30 = this.dd.getDataDescriptorGenerator();
      int var31 = var5.size();
      int var32 = this.td.getColumnDescriptorList().size() - var31;
      if (var32 == 1) {
         throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), "THE *LAST* COLUMN " + var1, "TABLE", this.td.getQualifiedName()});
      } else {
         for(int var33 = 0; var33 < var31; ++var33) {
            String var35 = (String)var5.get(var33);
            this.activation.addWarning(StandardException.newWarning("01009", new Object[]{var35, this.td.getName()}));
            this.dropColumnFromTable(var35);
         }

         this.td = this.dd.getTableDescriptor(this.tableId);
         ColumnDescriptor var34 = this.td.getColumnDescriptor(var1);
         if (var34 == null) {
            throw StandardException.newException("42X14", new Object[]{var1, this.td.getQualifiedName()});
         } else {
            int var36 = this.td.getColumnDescriptorList().size();
            this.droppedColumnPosition = var34.getPosition();
            FormatableBitSet var37 = new FormatableBitSet(var36 + 1);
            var37.set(this.droppedColumnPosition);
            this.td.setReferencedColumnMap(var37);
            this.dm.invalidateFor(this.td, var2 ? 37 : 46, this.lcc);
            if (var34.getDefaultInfo() != null) {
               this.dm.clearDependencies(this.lcc, var34.getDefaultDescriptor(this.dd));
            }

            if (var34.isAutoincrement() && this.dd.checkVersion(230, (String)null)) {
               DropTableConstantAction.dropIdentitySequence(this.dd, this.td, this.activation);
            }

            for(TriggerDescriptor var13 : this.dd.getTriggerDescriptors(this.td)) {
               boolean var14 = false;
               int[] var15 = var13.getReferencedCols();
               if (var15 != null) {
                  int var16 = var15.length;
                  boolean var18 = false;

                  int var17;
                  for(var17 = 0; var17 < var16; ++var17) {
                     if (var15[var17] > this.droppedColumnPosition) {
                        var18 = true;
                     } else if (var15[var17] == this.droppedColumnPosition) {
                        if (!var2) {
                           throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), var1, "TRIGGER", var13.getName()});
                        }

                        var13.drop(this.lcc);
                        var14 = true;
                        this.activation.addWarning(StandardException.newWarning("01502", new Object[]{var13.getName(), this.td.getName()}));
                        break;
                     }
                  }

                  if (var17 == var16 && var18) {
                     this.dd.dropTriggerDescriptor(var13, this.tc);

                     for(int var45 = 0; var45 < var16; ++var45) {
                        if (var15[var45] > this.droppedColumnPosition) {
                           int var10002 = var15[var45]--;
                        }
                     }

                     var13.setReferencedCols(var15);
                     this.dd.addDescriptor(var13, this.sd, 13, false, this.tc);
                  }
               }

               if (!var14) {
                  int[] var43 = var13.getReferencedColsInTriggerAction();
                  if (var43 != null) {
                     int var46 = var43.length;
                     boolean var19 = false;

                     int var50;
                     for(var50 = 0; var50 < var46; ++var50) {
                        if (var43[var50] > this.droppedColumnPosition) {
                           var19 = true;
                        } else if (var43[var50] == this.droppedColumnPosition) {
                           if (!var2) {
                              throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), var1, "TRIGGER", var13.getName()});
                           }

                           var13.drop(this.lcc);
                           var14 = true;
                           this.activation.addWarning(StandardException.newWarning("01502", new Object[]{var13.getName(), this.td.getName()}));
                           break;
                        }
                     }

                     if (var50 == var46 && var19) {
                        this.dd.dropTriggerDescriptor(var13, this.tc);

                        for(int var51 = 0; var51 < var46; ++var51) {
                           if (var43[var51] > this.droppedColumnPosition) {
                              int var67 = var43[var51]--;
                           }
                        }

                        var13.setReferencedColsInTriggerAction(var43);
                        this.dd.addDescriptor(var13, this.sd, 13, false, this.tc);
                     }
                  }
               }
            }

            ConstraintDescriptorList var38 = this.dd.getConstraintDescriptors(this.td);
            int var39 = var38.size();
            ArrayList var41 = new ArrayList();
            int var42 = 0;
            ConstraintDescriptor[] var44 = new ConstraintDescriptor[var39];

            for(int var47 = var39 - 1; var47 >= 0; --var47) {
               ConstraintDescriptor var52 = var38.elementAt(var47);
               int[] var55 = var52.getReferencedColumns();
               int var20 = var55.length;
               boolean var22 = false;

               int var21;
               for(var21 = 0; var21 < var20; ++var21) {
                  if (var55[var21] > this.droppedColumnPosition) {
                     var22 = true;
                  }

                  if (var55[var21] == this.droppedColumnPosition) {
                     break;
                  }
               }

               if (var21 != var20) {
                  if (!var2) {
                     throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), var1, "CONSTRAINT", var52.getConstraintName()});
                  }

                  if (var52 instanceof ReferencedKeyConstraintDescriptor) {
                     var44[var42++] = var52;
                  } else {
                     this.dm.invalidateFor(var52, 19, this.lcc);
                     this.dropConstraint(var52, this.td, var41, this.activation, this.lcc, true);
                     this.activation.addWarning(StandardException.newWarning("01500", new Object[]{var52.getConstraintName(), this.td.getName()}));
                  }
               } else if (var52 instanceof CheckConstraintDescriptor && var22) {
                  this.dd.dropConstraintDescriptor(var52, this.tc);

                  for(int var62 = 0; var62 < var20; ++var62) {
                     if (var55[var62] > this.droppedColumnPosition) {
                        int var68 = var55[var62]--;
                     }
                  }

                  ((CheckConstraintDescriptor)var52).setReferencedColumnsDescriptor(new ReferencedColumnsDescriptorImpl(var55));
                  this.dd.addConstraintDescriptor(var52, this.tc);
               }
            }

            for(int var48 = var42 - 1; var48 >= 0; --var48) {
               ConstraintDescriptor var53 = var44[var48];
               this.dropConstraint(var53, this.td, var41, this.activation, this.lcc, false);
               this.activation.addWarning(StandardException.newWarning("01500", new Object[]{var53.getConstraintName(), this.td.getName()}));
               if (var2) {
                  for(ConstraintDescriptor var63 : this.dd.getForeignKeys(var53.getUUID())) {
                     this.dm.invalidateFor(var63, 19, this.lcc);
                     this.dropConstraint(var63, this.td, var41, this.activation, this.lcc, true);
                     this.activation.addWarning(StandardException.newWarning("01500", new Object[]{var63.getConstraintName(), var63.getTableDescriptor().getName()}));
                  }
               }

               this.dm.invalidateFor(var53, 19, this.lcc);
               this.dm.clearDependencies(this.lcc, var53);
            }

            this.createNewBackingCongloms(var41, (long[])null);
            this.td = this.dd.getTableDescriptor(this.tableId);
            this.compressTable();
            ColumnDescriptorList var49 = this.td.getColumnDescriptorList();
            this.dd.dropColumnDescriptor(this.td.getUUID(), var1, this.tc);
            ColumnDescriptor[] var54 = new ColumnDescriptor[var36 - var34.getPosition()];
            int var57 = var34.getPosition();

            for(int var60 = 0; var57 < var36; ++var60) {
               ColumnDescriptor var64 = var49.elementAt(var57);
               this.dd.dropColumnDescriptor(this.td.getUUID(), var64.getColumnName(), this.tc);
               var64.setPosition(var57);
               if (var64.isAutoincrement()) {
                  var64.setAutoinc_create_or_modify_Start_Increment(0);
               }

               var54[var60] = var64;
               ++var57;
            }

            this.dd.addDescriptorArray(var54, this.td, 2, false, this.tc);

            for(DependencyDescriptor var65 : this.dd.getProvidersDescriptorList(this.td.getObjectID().toString())) {
               DependableFinder var66 = var65.getDependentFinder();
               if (var66.getSQLObjectType().equals("StoredPreparedStatement")) {
                  for(DependencyDescriptor var25 : this.dd.getProvidersDescriptorList(var65.getUUID().toString())) {
                     DependableFinder var26 = var25.getDependentFinder();
                     if (var26.getSQLObjectType().equals("Trigger")) {
                        TriggerDescriptor var27 = this.dd.getTriggerDescriptor(var25.getUUID());
                        UUID var28 = var27.getWhenClauseId();
                        boolean var29 = false;
                        if (var28 != null) {
                           var29 = this.columnDroppedAndTriggerDependencies(var27, var28, true, var2, var1);
                        }

                        if (!var29) {
                           this.columnDroppedAndTriggerDependencies(var27, var27.getActionId(), false, var2, var1);
                        }
                     }
                  }
               }
            }

            this.dd.updateSYSCOLPERMSforDropColumn(this.td.getUUID(), this.tc, var34);
            var49.remove(this.td.getColumnDescriptor(var1));
         }
      }
   }

   private boolean columnDroppedAndTriggerDependencies(TriggerDescriptor var1, UUID var2, boolean var3, boolean var4, String var5) throws StandardException {
      this.dd.dropTriggerDescriptor(var1, this.tc);
      SchemaDescriptor var6 = this.dd.getSchemaDescriptor(this.dd.getSPSDescriptor(var2).getCompSchemaId(), (TransactionController)null);
      CompilerContext var7 = this.lcc.pushCompilerContext(var6);
      Parser var8 = var7.getParser();
      String var9 = var3 ? var1.getWhenClauseText() : var1.getTriggerDefinition();
      Visitable var10 = var3 ? var8.parseSearchCondition(var9) : var8.parseStatement(var9);
      this.lcc.popCompilerContext(var7);
      var7 = null;

      label136: {
         boolean var12;
         try {
            try {
               SPSDescriptor var11 = var3 ? var1.getWhenClauseSPS(this.lcc) : var1.getActionSPS(this.lcc);
               int[] var22 = new int[this.td.getNumberOfColumns()];
               Arrays.fill(var22, -1);
               String var13 = this.dd.getTriggerActionString(var10, var1.getOldReferencingName(), var1.getNewReferencingName(), var9, var1.getReferencedCols(), var22, 0, var1.getTableDescriptor(), var1.getTriggerEventMask(), true, (List)null, (int[])null);
               if (var3) {
                  var13 = "VALUES " + var13;
               }

               var11.setText(var13);
               var7 = this.lcc.pushCompilerContext(var6);
               var7.setReliability(0);
               var8 = var7.getParser();
               StatementNode var14 = (StatementNode)var8.parseStatement(var13);
               var7.setCurrentDependent(var11.getPreparedStatement());
               var14.bindStatement();
               break label136;
            } catch (StandardException var18) {
               if (!var18.getMessageId().equals("42X04") && !var18.getMessageId().equals("42X14") && !var18.getMessageId().equals("42802") && !var18.getMessageId().equals("42X05")) {
                  throw var18;
               }
            }

            if (!var4) {
               throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), var5, "TRIGGER", var1.getName()});
            }

            var1.drop(this.lcc);
            this.activation.addWarning(StandardException.newWarning("01502", new Object[]{var1.getName(), this.td.getName()}));
            var12 = true;
         } finally {
            if (var7 != null) {
               this.lcc.popCompilerContext(var7);
            }

         }

         return var12;
      }

      this.dd.addDescriptor(var1, this.sd, 13, false, this.tc);
      return false;
   }

   private void modifyColumnType(int var1) throws StandardException {
      ColumnDescriptor var2 = this.td.getColumnDescriptor(this.columnInfo[var1].name);
      ColumnDescriptor var3 = new ColumnDescriptor(this.columnInfo[var1].name, var2.getPosition(), this.columnInfo[var1].dataType, var2.getDefaultValue(), var2.getDefaultInfo(), this.td, var2.getDefaultUUID(), this.columnInfo[var1].autoincStart, this.columnInfo[var1].autoincInc, this.columnInfo[var1].autoincCycle);
      this.dd.dropColumnDescriptor(this.td.getUUID(), this.columnInfo[var1].name, this.tc);
      this.dd.addDescriptor(var3, this.td, 2, false, this.tc);
   }

   private void modifyColumnConstraint(String var1, boolean var2) throws StandardException {
      ColumnDescriptor var3 = this.td.getColumnDescriptor(var1);
      DataTypeDescriptor var4 = var3.getType().getNullabilityType(var2);
      ConstraintDescriptorList var5 = this.dd.getConstraintDescriptors(this.td);
      int var6 = var3.getPosition();

      for(int var7 = 0; var7 < var5.size(); ++var7) {
         ConstraintDescriptor var8 = var5.elementAt(var7);
         if (var8.getConstraintType() == 3) {
            ColumnDescriptorList var9 = var8.getColumnDescriptors();

            for(int var10 = 0; var10 < var9.size() && var9.elementAt(var10).getPosition() == var6; ++var10) {
               ConglomerateDescriptor var11 = this.td.getConglomerateDescriptor(var8.getConglomerateId());
               if (!var11.getIndexDescriptor().isUnique() && !var11.getIndexDescriptor().hasDeferrableChecking()) {
                  break;
               }

               this.recreateUniqueConstraintBackingIndexAsUniqueWhenNotNull(var11, this.td, this.activation, this.lcc);
            }
         }
      }

      ColumnDescriptor var12 = new ColumnDescriptor(var1, var3.getPosition(), var4, var3.getDefaultValue(), var3.getDefaultInfo(), this.td, var3.getDefaultUUID(), var3.getAutoincStart(), var3.getAutoincInc(), var3.getAutoincCycle());
      this.dd.dropColumnDescriptor(this.td.getUUID(), var1, this.tc);
      this.dd.addDescriptor(var12, this.td, 2, false, this.tc);
   }

   private void modifyColumnDefault(int var1) throws StandardException {
      ColumnDescriptor var2 = this.td.getColumnDescriptor(this.columnInfo[var1].name);
      int var3 = var2.getPosition();
      if (var2.hasNonNullDefault()) {
         DefaultDescriptor var4 = new DefaultDescriptor(this.dd, this.columnInfo[var1].oldDefaultUUID, this.td.getUUID(), var3);
         this.dm.invalidateFor(var4, 31, this.lcc);
         this.dm.clearDependencies(this.lcc, var4);
      }

      UUID var12 = this.columnInfo[var1].newDefaultUUID;
      if (this.columnInfo[var1].defaultInfo != null && var12 == null) {
         var12 = this.dd.getUUIDFactory().createUUID();
      }

      var2 = new ColumnDescriptor(this.columnInfo[var1].name, var3, this.columnInfo[var1].dataType, this.columnInfo[var1].defaultValue, this.columnInfo[var1].defaultInfo, this.td, var12, this.columnInfo[var1].autoincStart, this.columnInfo[var1].autoincInc, this.columnInfo[var1].autoinc_create_or_modify_Start_Increment, this.columnInfo[var1].autoincCycle);
      this.dd.dropColumnDescriptor(this.td.getUUID(), this.columnInfo[var1].name, this.tc);
      this.dd.addDescriptor(var2, this.td, 2, false, this.tc);
      if (this.columnInfo[var1].action == 6) {
         long var5 = this.getColumnMax(this.td, this.columnInfo[var1].name, this.columnInfo[var1].autoincInc);
         this.dd.setAutoincrementValue(this.tc, this.td.getUUID(), this.columnInfo[var1].name, var5, true);
      } else if (this.columnInfo[var1].action == 5) {
         this.dd.setAutoincrementValue(this.tc, this.td.getUUID(), this.columnInfo[var1].name, this.columnInfo[var1].autoincStart, false);
      }

      if ((this.columnInfo[var1].action == 6 || this.columnInfo[var1].action == 5 || this.columnInfo[var1].action == 10) && this.dd.checkVersion(230, (String)null)) {
         Long var13 = null;
         if (this.columnInfo[var1].action == 6 || this.columnInfo[var1].action == 10) {
            var13 = this.dd.peekAtIdentity(this.td.getSchemaName(), this.td.getName());
         }

         if (this.columnInfo[var1].action == 10) {
            if (this.columnInfo[var1].autoincCycle) {
               if (var13 == null) {
                  int var6 = this.columnInfo[var1].autoincInc > 0L ? 1 : 0;
                  var13 = this.getRangeBound(this.columnInfo[var1].dataType, var6);
               }
            } else {
               int var14 = this.columnInfo[var1].autoincInc > 0L ? 1 : 0;
               Long var7 = this.getRangeBound(this.columnInfo[var1].dataType, var14);
               if (var13 != null && var13.equals(var7)) {
                  var13 = null;
               }
            }
         }

         DropTableConstantAction.dropIdentitySequence(this.dd, this.td, this.activation);
         String var15 = TableDescriptor.makeSequenceName(this.td.getUUID());
         CreateSequenceConstantAction var16 = CreateTableConstantAction.makeCSCA(this.columnInfo[var1], var15);
         var16.executeConstantAction(this.activation);
         if (this.columnInfo[var1].action == 6 || this.columnInfo[var1].action == 10) {
            SequenceDescriptor var8 = this.dd.getSequenceDescriptor(this.dd.getSystemSchemaDescriptor(), var15);
            RowLocation[] var9 = new RowLocation[1];
            SequenceDescriptor[] var10 = new SequenceDescriptor[1];
            this.dd.computeSequenceRowLocation(this.tc, var8.getUUID().toString(), var9, var10);
            this.dd.updateCurrentSequenceValue(this.tc, var9[0], true, (Long)null, var13);
         }
      }

   }

   private long getRangeBound(DataTypeDescriptor var1, int var2) throws StandardException {
      TypeId var3 = var1.getTypeId();
      boolean var4 = var2 == 1;
      if (var3 == TypeId.SMALLINT_ID) {
         return var4 ? -32768L : 32767L;
      } else if (var3 == TypeId.INTEGER_ID) {
         return var4 ? -2147483648L : 2147483647L;
      } else if (var3 != TypeId.BIGINT_ID) {
         throw StandardException.newException("0A000.S", new Object[0]);
      } else {
         return var4 ? Long.MIN_VALUE : Long.MAX_VALUE;
      }
   }

   private void modifyIdentityState(int var1) throws StandardException {
      ColumnDescriptor var2 = this.td.getColumnDescriptor(this.columnInfo[var1].name);
      int var3 = var2.getPosition();
      boolean var4 = var2.isAutoincAlways();
      boolean var5 = this.columnInfo[var1].action == 8;
      if (var4 != var5) {
         UUID var6 = var5 ? null : this.dd.getUUIDFactory().createUUID();
         ColumnDescriptor var7 = new ColumnDescriptor(this.columnInfo[var1].name, var3, var2.getType(), this.columnInfo[var1].defaultValue, this.columnInfo[var1].defaultInfo, this.td, var6, var2.getAutoincStart(), var2.getAutoincInc(), 3L, var2.getAutoincCycle());
         this.dd.dropColumnDescriptor(this.td.getUUID(), this.columnInfo[var1].name, this.tc);
         this.dd.addDescriptor(var7, this.td, 2, false, this.tc);
      }
   }

   private void compressTable() throws StandardException {
      Properties var3 = new Properties();
      ExecRow var5 = this.td.getEmptyExecRow();
      int[] var6 = this.td.getColumnCollationIds();
      this.compressHeapCC = this.tc.openConglomerate(this.td.getHeapConglomerateId(), false, 4, 7, 5);
      RowLocation var4 = this.compressHeapCC.newRowLocationTemplate();
      this.compressHeapCC.getInternalTablePropertySet(var3);
      this.compressHeapCC.close();
      this.compressHeapCC = null;
      this.baseRow = new ExecRow[this.bulkFetchSize];
      this.baseRowArray = new DataValueDescriptor[this.bulkFetchSize][];
      this.validRow = new boolean[this.bulkFetchSize];
      this.getAffectedIndexes();
      this.compressRL = new RowLocation[this.bulkFetchSize];
      this.indexRows = new ExecIndexRow[this.numIndexes];
      if (!this.compressTable) {
         ExecRow var7 = this.activation.getExecutionFactory().getValueRow(var5.nColumns() - 1);
         int[] var8 = new int[var6.length - 1];

         for(int var9 = 0; var9 < var7.nColumns(); ++var9) {
            var7.setColumn(var9 + 1, var9 < this.droppedColumnPosition - 1 ? var5.getColumn(var9 + 1) : var5.getColumn(var9 + 1 + 1));
            var8[var9] = var6[var9 < this.droppedColumnPosition - 1 ? var9 : var9 + 1];
         }

         var5 = var7;
         var6 = var8;
      }

      this.setUpAllSorts(var5, var4);
      this.openBulkFetchScan(this.td.getHeapConglomerateId());
      this.estimatedRowCount = this.compressHeapGSC.getEstimatedRowCount();

      for(int var11 = 0; var11 < this.bulkFetchSize; ++var11) {
         this.baseRow[var11] = this.td.getEmptyExecRow();
         this.baseRowArray[var11] = this.baseRow[var11].getRowArray();
         this.compressRL[var11] = this.compressHeapGSC.newRowLocationTemplate();
      }

      long var1 = this.tc.createAndLoadConglomerate("heap", var5.getRowArray(), (ColumnOrdering[])null, var6, var3, 0, this, (long[])null);
      this.closeBulkFetchScan();
      ScanController var12 = this.tc.openScan(var1, false, 4, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
      var12.setEstimatedRowCount((long)this.rowCount);
      var12.close();
      Object var13 = null;
      this.dd.startWriting(this.lcc);
      if (this.compressIRGs.length > 0) {
         this.updateAllIndexes(var1, this.dd);
      }

      long var14 = this.td.getHeapConglomerateId();
      ConglomerateDescriptor var10 = this.td.getConglomerateDescriptor(var14);
      this.dd.updateConglomerateDescriptor(var10, var1, this.tc);
      this.dm.invalidateFor(this.td, 33, this.lcc);
      this.tc.dropConglomerate(var14);
      this.cleanUp();
   }

   private void truncateTable() throws StandardException {
      Properties var4 = new Properties();

      for(ConstraintDescriptor var7 : this.dd.getConstraintDescriptors(this.td)) {
         if (var7 instanceof ReferencedKeyConstraintDescriptor var8) {
            Iterator var9 = var8.getNonSelfReferencingFK(1).iterator();
            if (var9.hasNext()) {
               ConstraintDescriptor var10 = (ConstraintDescriptor)var9.next();
               ForeignKeyConstraintDescriptor var11 = (ForeignKeyConstraintDescriptor)var10;
               throw StandardException.newException("XCL48.S", new Object[]{this.td.getName()});
            }
         }
      }

      for(TriggerDescriptor var16 : this.dd.getTriggerDescriptors(this.td)) {
         if (var16.listensForEvent(2) && var16.isEnabled()) {
            throw StandardException.newException("XCL49.S", new Object[]{this.td.getName(), var16.getName()});
         }
      }

      ExecRow var1 = this.td.getEmptyExecRow();
      this.compressHeapCC = this.tc.openConglomerate(this.td.getHeapConglomerateId(), false, 4, 7, 5);
      RowLocation var5 = this.compressHeapCC.newRowLocationTemplate();
      this.compressHeapCC.getInternalTablePropertySet(var4);
      this.compressHeapCC.close();
      this.compressHeapCC = null;
      long var2 = this.tc.createConglomerate("heap", var1.getRowArray(), (ColumnOrdering[])null, this.td.getColumnCollationIds(), var4, 0);
      this.getAffectedIndexes();
      if (this.numIndexes > 0) {
         this.indexRows = new ExecIndexRow[this.numIndexes];
         this.ordering = new ColumnOrdering[this.numIndexes][];
         this.collation = new int[this.numIndexes][];

         for(int var13 = 0; var13 < this.numIndexes; ++var13) {
            IndexRowGenerator var17 = this.compressIRGs[var13];
            this.indexRows[var13] = var17.getIndexRowTemplate();
            var17.getIndexRow(var1, var5, this.indexRows[var13], (FormatableBitSet)null);
            int[] var19 = var17.baseColumnPositions();
            boolean[] var21 = var17.isAscending();
            int var22 = var19.length + 1;
            this.ordering[var13] = new ColumnOrdering[var22];
            this.collation[var13] = var17.getColumnCollationIds(this.td.getColumnDescriptorList());

            for(int var23 = 0; var23 < var22 - 1; ++var23) {
               this.ordering[var13][var23] = new IndexColumnOrder(var23, var21[var23]);
            }

            this.ordering[var13][var22 - 1] = new IndexColumnOrder(var22 - 1);
         }
      }

      this.dd.startWriting(this.lcc);
      if (this.numIndexes > 0) {
         long[] var14 = new long[this.numIndexes];

         for(int var18 = 0; var18 < this.numIndexes; ++var18) {
            this.updateIndex(var2, this.dd, var18, var14);
         }
      }

      long var15 = this.td.getHeapConglomerateId();
      ConglomerateDescriptor var20 = this.td.getConglomerateDescriptor(var15);
      this.dd.updateConglomerateDescriptor(var20, var2, this.tc);
      this.dm.invalidateFor(this.td, 42, this.lcc);
      this.tc.dropConglomerate(var15);
      this.cleanUp();
   }

   private void updateAllIndexes(long var1, DataDictionary var3) throws StandardException {
      long[] var4 = new long[this.numIndexes];
      if (this.sequential) {
         if (this.numIndexes >= 1) {
            this.updateIndex(var1, var3, 0, var4);
         }

         for(int var5 = 1; var5 < this.numIndexes; ++var5) {
            this.openBulkFetchScan(var1);

            while(this.getNextRowFromRowSource() != null) {
               this.objectifyStreamingColumns();
               this.insertIntoSorter(var5, this.compressRL[this.currentCompressRow - 1]);
            }

            this.updateIndex(var1, var3, var5, var4);
            this.closeBulkFetchScan();
         }
      } else {
         for(int var6 = 0; var6 < this.numIndexes; ++var6) {
            this.updateIndex(var1, var3, var6, var4);
         }
      }

   }

   private void updateIndex(long var1, DataDictionary var3, int var4, long[] var5) throws StandardException {
      Properties var6 = new Properties();
      ConglomerateDescriptor var7 = this.td.getConglomerateDescriptor(this.indexConglomerateNumbers[var4]);
      ConglomerateController var8 = this.tc.openConglomerate(this.indexConglomerateNumbers[var4], false, 4, 7, 5);
      var8.getInternalTablePropertySet(var6);
      int var9 = this.indexRows[var4].nColumns();
      var6.put("baseConglomerateId", Long.toString(var1));
      if (var7.getIndexDescriptor().isUnique()) {
         var6.put("nUniqueColumns", Integer.toString(var9 - 1));
      } else {
         var6.put("nUniqueColumns", Integer.toString(var9));
      }

      if (var7.getIndexDescriptor().isUniqueWithDuplicateNulls() && !var7.getIndexDescriptor().hasDeferrableChecking()) {
         var6.put("uniqueWithDuplicateNulls", Boolean.toString(true));
      }

      var6.put("rowLocationColumn", Integer.toString(var9 - 1));
      var6.put("nKeyFields", Integer.toString(var9));
      var8.close();
      boolean var11 = false;
      if (!this.truncateTable) {
         this.sorters[var4].completedInserts();
         this.sorters[var4] = null;
         CardinalityCounter var10;
         if (this.td.statisticsExist(var7)) {
            var10 = new CardinalityCounter(this.tc.openSortRowSource(this.sortIds[var4]));
            var11 = true;
         } else {
            var10 = new CardinalityCounter(this.tc.openSortRowSource(this.sortIds[var4]));
         }

         var5[var4] = this.tc.createAndLoadConglomerate("BTREE", this.indexRows[var4].getRowArray(), this.ordering[var4], this.collation[var4], var6, 0, var10, (long[])null);
         if (var11) {
            var3.dropStatisticsDescriptors(this.td.getUUID(), var7.getUUID(), this.tc);
         }

         long var12;
         if ((var12 = ((CardinalityCounter)var10).getRowCount()) > 0L) {
            long[] var14 = ((CardinalityCounter)var10).getCardinality();

            for(int var15 = 0; var15 < var14.length; ++var15) {
               StatisticsDescriptor var16 = new StatisticsDescriptor(var3, var3.getUUIDFactory().createUUID(), var7.getUUID(), this.td.getUUID(), "I", new StatisticsImpl(var12, var14[var15]), var15 + 1);
               var3.addDescriptor(var16, (TupleDescriptor)null, 14, true, this.tc);
            }
         }
      } else {
         var5[var4] = this.tc.createConglomerate("BTREE", this.indexRows[var4].getRowArray(), this.ordering[var4], this.collation[var4], var6, 0);
         if (this.td.statisticsExist(var7)) {
            var3.dropStatisticsDescriptors(this.td.getUUID(), var7.getUUID(), this.tc);
         }
      }

      var3.updateConglomerateDescriptor(this.td.getConglomerateDescriptors(this.indexConglomerateNumbers[var4]), var5[var4], this.tc);
      this.tc.dropConglomerate(this.indexConglomerateNumbers[var4]);
   }

   private void getAffectedIndexes() throws StandardException {
      IndexLister var1 = this.td.getIndexLister();
      this.compressIRGs = var1.getIndexRowGenerators();
      this.numIndexes = this.compressIRGs.length;
      this.indexConglomerateNumbers = var1.getIndexConglomerateNumbers();
      if (!this.compressTable && !this.truncateTable) {
         ArrayList var17 = new ArrayList();

         for(int var3 = 0; var3 < this.compressIRGs.length; ++var3) {
            int[] var4 = this.compressIRGs[var3].baseColumnPositions();

            int var5;
            for(var5 = 0; var5 < var4.length && var4[var5] != this.droppedColumnPosition; ++var5) {
            }

            if (var5 != var4.length) {
               if (var4.length != 1 && (this.behavior != 0 || !this.compressIRGs[var3].isUnique())) {
                  if (this.compressIRGs[var3].isUnique()) {
                     ConglomerateDescriptor var25 = this.td.getConglomerateDescriptor(this.indexConglomerateNumbers[var3]);
                     throw StandardException.newException("X0Y25.S", new Object[]{this.dm.getActionString(37), this.columnInfo[0].name, "UNIQUE INDEX", var25.getConglomerateName()});
                  }
               } else {
                  --this.numIndexes;
                  ConglomerateDescriptor var6 = this.td.getConglomerateDescriptor(this.indexConglomerateNumbers[var3]);
                  this.dropConglomerate(var6, this.td, true, var17, this.activation, this.activation.getLanguageConnectionContext());
                  this.compressIRGs[var3] = null;
               }
            }
         }

         this.createNewBackingCongloms(var17, this.indexConglomerateNumbers);
         IndexRowGenerator[] var19 = new IndexRowGenerator[this.numIndexes];
         long[] var21 = new long[this.numIndexes];
         this.collation = new int[this.numIndexes][];
         int var23 = 0;

         for(int var26 = 0; var23 < this.numIndexes; ++var26) {
            while(this.compressIRGs[var26] == null) {
               ++var26;
            }

            this.collation[var23] = this.compressIRGs[var26].getColumnCollationIds(this.td.getColumnDescriptorList());
            int[] var7 = this.compressIRGs[var26].baseColumnPositions();
            var19[var23] = this.compressIRGs[var26];
            var21[var23] = this.indexConglomerateNumbers[var26];
            boolean[] var8 = this.compressIRGs[var26].isAscending();
            boolean var9 = false;
            boolean var10 = false;
            int var11 = var7.length;

            for(int var12 = 0; var12 < var11; ++var12) {
               if (var7[var12] > this.droppedColumnPosition) {
                  int var10002 = var7[var12]--;
                  var10 = true;
               } else if (var7[var12] == this.droppedColumnPosition) {
                  var7[var12] = 0;
                  var9 = true;
               }
            }

            if (var10) {
               this.compressIRGs[var26].setBaseColumnPositions(var7);
            }

            if (var9) {
               --var11;
               int[] var28 = new int[var11];
               boolean[] var13 = new boolean[var11];
               int[] var14 = new int[this.collation[var23].length - 1];
               int var15 = 0;

               for(int var16 = 0; var15 < var11; ++var15) {
                  if (var16 == 0 && var7[var15 + var16] == 0) {
                     ++var16;
                  }

                  var28[var15] = var7[var15 + var16];
                  var13[var15] = var8[var15 + var16];
                  var14[var15] = this.collation[var23][var15 + var16];
               }

               IndexDescriptor var29 = this.compressIRGs[var26].getIndexDescriptor();
               var29.setBaseColumnPositions(var28);
               var29.setIsAscending(var13);
               var29.setNumberOfOrderedColumns(var29.numberOfOrderedColumns() - 1);
               this.collation[var23] = var14;
            }

            ++var23;
         }

         this.compressIRGs = var19;
         this.indexConglomerateNumbers = var21;
      } else {
         this.collation = new int[this.numIndexes][];

         for(int var2 = 0; var2 < this.numIndexes; ++var2) {
            this.collation[var2] = this.compressIRGs[var2].getColumnCollationIds(this.td.getColumnDescriptorList());
         }
      }

      Object[] var18 = this.compressIndexArrays(this.indexConglomerateNumbers, this.compressIRGs);
      if (var18 != null) {
         this.indexConglomerateNumbers = (long[])var18[1];
         this.compressIRGs = (IndexRowGenerator[])var18[2];
         this.numIndexes = this.indexConglomerateNumbers.length;
      }

      this.indexedCols = new FormatableBitSet(!this.compressTable && !this.truncateTable ? this.td.getNumberOfColumns() : this.td.getNumberOfColumns() + 1);

      for(int var20 = 0; var20 < this.numIndexes; ++var20) {
         int[] var22 = this.compressIRGs[var20].getIndexDescriptor().baseColumnPositions();

         for(int var24 = 0; var24 < var22.length; ++var24) {
            this.indexedCols.set(var22[var24]);
         }
      }

   }

   private void createNewBackingCongloms(ArrayList var1, long[] var2) throws StandardException {
      int var3 = var1.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         CreateIndexConstantAction var5 = (CreateIndexConstantAction)var1.get(var4);
         if (this.dd.getConglomerateDescriptor(var5.getCreatedUUID()) != null) {
            this.executeConglomReplacement(var5, this.activation);
            long var6 = var5.getReplacedConglomNumber();
            long var8 = var5.getCreatedConglomNumber();
            ConglomerateDescriptor[] var10 = this.td.getConglomerateDescriptors(var6);

            for(int var11 = 0; var11 < var10.length; ++var11) {
               var10[var11].setConglomerateNumber(var8);
            }

            if (var2 != null) {
               for(int var12 = 0; var12 < var2.length; ++var12) {
                  if (var2[var12] == var6) {
                     var2[var12] = var8;
                  }
               }
            }
         }
      }

   }

   private void setUpAllSorts(ExecRow var1, RowLocation var2) throws StandardException {
      this.ordering = new ColumnOrdering[this.numIndexes][];
      this.needToDropSort = new boolean[this.numIndexes];
      this.sortIds = new long[this.numIndexes];

      for(int var3 = 0; var3 < this.numIndexes; ++var3) {
         this.indexRows[var3] = this.compressIRGs[var3].getIndexRowTemplate();
         this.compressIRGs[var3].getIndexRow(var1, var2, this.indexRows[var3], (FormatableBitSet)null);
         int[] var4 = this.compressIRGs[var3].baseColumnPositions();
         boolean[] var5 = this.compressIRGs[var3].isAscending();
         int var6 = var4.length + 1;
         boolean var7 = this.numIndexes == 1;
         BasicSortObserver var8 = new BasicSortObserver(false, false, this.indexRows[var3], var7);
         this.ordering[var3] = new ColumnOrdering[var6];

         for(int var9 = 0; var9 < var6 - 1; ++var9) {
            this.ordering[var3][var9] = new IndexColumnOrder(var9, var5[var9]);
         }

         this.ordering[var3][var6 - 1] = new IndexColumnOrder(var6 - 1);
         this.sortIds[var3] = this.tc.createSort((Properties)null, this.indexRows[var3].getRowArrayClone(), this.ordering[var3], var8, false, this.estimatedRowCount, -1);
      }

      this.sorters = new SortController[this.numIndexes];

      for(int var10 = 0; var10 < this.numIndexes; ++var10) {
         this.sorters[var10] = this.tc.openSort(this.sortIds[var10]);
         this.needToDropSort[var10] = true;
      }

   }

   public FormatableBitSet getValidColumns() {
      return null;
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      this.currentRow = null;
      if (!this.doneScan && (this.currentCompressRow == this.bulkFetchSize || !this.validRow[this.currentCompressRow])) {
         int var1 = this.compressHeapGSC.fetchNextGroup(this.baseRowArray, this.compressRL);
         this.doneScan = var1 != this.bulkFetchSize;
         this.currentCompressRow = 0;
         this.rowCount += var1;

         for(int var2 = 0; var2 < var1; ++var2) {
            this.validRow[var2] = true;
         }

         for(int var4 = var1; var4 < this.bulkFetchSize; ++var4) {
            this.validRow[var4] = false;
         }
      }

      if (this.validRow[this.currentCompressRow]) {
         if (this.compressTable) {
            this.currentRow = this.baseRow[this.currentCompressRow];
         } else {
            if (this.currentRow == null) {
               this.currentRow = this.activation.getExecutionFactory().getValueRow(this.baseRowArray[this.currentCompressRow].length - 1);
            }

            for(int var3 = 0; var3 < this.currentRow.nColumns(); ++var3) {
               this.currentRow.setColumn(var3 + 1, var3 < this.droppedColumnPosition - 1 ? this.baseRow[this.currentCompressRow].getColumn(var3 + 1) : this.baseRow[this.currentCompressRow].getColumn(var3 + 1 + 1));
            }
         }

         ++this.currentCompressRow;
      }

      if (this.currentRow != null) {
         if (this.compressIRGs.length > 0) {
            this.currentRow = this.currentRow.getClone(this.indexedCols);
         }

         return this.currentRow.getRowArray();
      } else {
         return null;
      }
   }

   public boolean needsToClone() {
      return true;
   }

   public void closeRowSource() {
   }

   public boolean needsRowLocation() {
      return this.numIndexes > 0;
   }

   public boolean needsRowLocationForDeferredCheckConstraints() {
      return false;
   }

   public void rowLocation(RowLocation var1) throws StandardException {
      if (this.compressIRGs.length > 0) {
         this.objectifyStreamingColumns();
         int var2 = this.compressIRGs.length;
         if (var2 > 1 && this.sequential) {
            var2 = 1;
         }

         for(int var3 = 0; var3 < var2; ++var3) {
            this.insertIntoSorter(var3, var1);
         }
      }

   }

   private void objectifyStreamingColumns() throws StandardException {
      for(int var1 = 0; var1 < this.currentRow.getRowArray().length; ++var1) {
         if (this.indexedCols.get(var1 + 1) && this.currentRow.getRowArray()[var1] instanceof StreamStorable) {
            this.currentRow.getRowArray()[var1].getObject();
         }
      }

   }

   private void insertIntoSorter(int var1, RowLocation var2) throws StandardException {
      this.indexRows[var1].getNewObjectArray();
      this.compressIRGs[var1].getIndexRow(this.currentRow, (RowLocation)var2.cloneValue(false), this.indexRows[var1], (FormatableBitSet)null);
      this.sorters[var1].insert(this.indexRows[var1].getRowArray());
   }

   private void cleanUp() throws StandardException {
      if (this.compressHeapCC != null) {
         this.compressHeapCC.close();
         this.compressHeapCC = null;
      }

      if (this.compressHeapGSC != null) {
         this.closeBulkFetchScan();
      }

      if (this.sorters != null) {
         for(int var1 = 0; var1 < this.compressIRGs.length; ++var1) {
            if (this.sorters[var1] != null) {
               this.sorters[var1].completedInserts();
            }

            this.sorters[var1] = null;
         }
      }

      if (this.needToDropSort != null) {
         for(int var2 = 0; var2 < this.needToDropSort.length; ++var2) {
            if (this.needToDropSort[var2]) {
               this.tc.dropSort(this.sortIds[var2]);
               this.needToDropSort[var2] = false;
            }
         }
      }

   }

   private int getSemiRowCount(TransactionController var1) throws StandardException {
      int var2 = 0;
      ScanController var3 = var1.openScan(this.td.getHeapConglomerateId(), false, 0, 7, 5, org.apache.derby.iapi.store.access.RowUtil.EMPTY_ROW_BITSET, (DataValueDescriptor[])null, 1, (Qualifier[][])null, (DataValueDescriptor[])null, -1);

      while(var3.next()) {
         ++var2;
         if (var2 == 2) {
            break;
         }
      }

      var3.close();
      return var2;
   }

   private void updateNewColumnToDefault(ColumnDescriptor var1) throws StandardException {
      String var2 = var1.getColumnName();
      String var10000 = IdUtil.mkQualifiedName(this.td.getSchemaName(), this.td.getName());
      String var3 = "UPDATE " + var10000 + " SET " + IdUtil.normalToDelimited(var2) + "=DEFAULT";
      executeUpdate(this.lcc, var3);
   }

   private static void executeUpdate(LanguageConnectionContext var0, String var1) throws StandardException {
      PreparedStatement var2 = var0.prepareInternalStatement(var1);
      ResultSet var3 = var2.executeSubStatement(var0, true, 0L);
      var3.close();
   }

   private long getColumnMax(TableDescriptor var1, String var2, long var3) throws StandardException {
      String var5 = var3 > 0L ? "MAX" : "MIN";
      String var6 = "SELECT  " + var5 + "(" + IdUtil.normalToDelimited(var2) + ") FROM " + IdUtil.mkQualifiedName(var1.getSchemaName(), var1.getName());
      PreparedStatement var7 = this.lcc.prepareInternalStatement(var6);
      ResultSet var8 = var7.executeSubStatement(this.lcc, false, 0L);
      DataValueDescriptor[] var9 = var8.getNextRow().getRowArray();
      var8.close();
      var8.finish();
      return var9[0].getLong();
   }

   private void openBulkFetchScan(long var1) throws StandardException {
      this.doneScan = false;
      this.compressHeapGSC = this.tc.openGroupFetchScan(var1, false, 0, 7, 5, (FormatableBitSet)null, (DataValueDescriptor[])null, 0, (Qualifier[][])null, (DataValueDescriptor[])null, 0);
   }

   private void closeBulkFetchScan() throws StandardException {
      this.compressHeapGSC.close();
      this.compressHeapGSC = null;
   }

   private boolean validateNotNullConstraint(String[] var1, boolean[] var2, int var3, LanguageConnectionContext var4, String var5) throws StandardException {
      boolean var6 = false;
      StringBuilder var7 = new StringBuilder();

      for(int var8 = 0; var8 < var1.length; ++var8) {
         ColumnDescriptor var9 = this.td.getColumnDescriptor(var1[var8]);
         if (var9 == null) {
            throw StandardException.newException("42X14", new Object[]{var1[var8], this.td.getName()});
         }

         if (var9.getType().isNullable()) {
            if (var3 > 0) {
               if (var6) {
                  var7.append(" AND ");
               }

               var7.append(IdUtil.normalToDelimited(var1[var8]));
               var7.append(" IS NOT NULL ");
            }

            var6 = true;
            var2[var8] = true;
         }
      }

      if (var6 && var3 > 0 && !ConstraintConstantAction.validateConstraint((String)null, var7.toString(), (UUID)null, this.td, var4, false, false)) {
         if (var5.equals("X0Y63.S")) {
            throw StandardException.newException("X0Y63.S", new Object[]{this.td.getQualifiedName()});
         } else if (var5.equals("X0Y63.S.1")) {
            throw StandardException.newException("X0Y63.S.1", new Object[]{this.td.getQualifiedName()});
         } else {
            throw StandardException.newException("X0Y80.S", new Object[]{this.td.getQualifiedName(), var1[0]});
         }
      } else {
         return var6;
      }
   }

   private Object[] compressIndexArrays(long[] var1, IndexRowGenerator[] var2) {
      long[] var3 = new long[var1.length];
      int var4 = 0;
      int var5 = var1.length - 1;

      for(int var6 = 0; var6 < var1.length; ++var6) {
         int var7;
         for(var7 = 0; var7 < var4; ++var7) {
            if (var1[var6] == var3[var7]) {
               var3[var5--] = (long)var6;
               break;
            }
         }

         if (var7 == var4) {
            var3[var4++] = var1[var6];
         }
      }

      if (var4 >= var1.length) {
         return null;
      } else {
         long[] var12 = new long[var4];
         IndexRowGenerator[] var13 = new IndexRowGenerator[var4];
         int[] var8 = new int[var1.length - var4];
         var5 = 0;
         int var9 = 0;

         for(int var10 = var1.length - 1; var9 < var1.length; ++var9) {
            if (var9 < var4) {
               var12[var9] = var3[var9];
            } else {
               var8[var1.length - var9 - 1] = (int)var3[var9];
            }

            if (var10 >= var4 && var9 == (int)var3[var10]) {
               --var10;
            } else {
               var13[var5] = var2[var9];
               ++var5;
            }
         }

         Object[] var14 = new Object[]{var8, var12, var13};
         return var14;
      }
   }

   public void offendingRowLocation(RowLocation var1, long var2) throws StandardException {
   }
}
