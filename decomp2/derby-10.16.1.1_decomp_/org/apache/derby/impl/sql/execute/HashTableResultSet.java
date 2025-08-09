package org.apache.derby.impl.sql.execute;

import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.types.ReferencedColumnsDescriptorImpl;
import org.apache.derby.iapi.services.io.FormatableArrayHolder;
import org.apache.derby.iapi.services.io.FormatableIntHolder;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.iapi.store.access.BackingStoreHashtable;
import org.apache.derby.iapi.store.access.KeyHasher;
import org.apache.derby.iapi.store.access.Qualifier;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;

class HashTableResultSet extends NoPutResultSetImpl implements CursorResultSet {
   public long restrictionTime;
   public long projectionTime;
   public int hashtableSize;
   public Properties scanProperties;
   public NoPutResultSet source;
   public GeneratedMethod singleTableRestriction;
   public Qualifier[][] nextQualifiers;
   private GeneratedMethod projection;
   private int[] projectMapping;
   private boolean runTimeStatsOn;
   private ExecRow mappedResultRow;
   public boolean reuseResult;
   public int[] keyColumns;
   private boolean removeDuplicates;
   private long maxInMemoryRowCount;
   private int initialCapacity;
   private float loadFactor;
   private boolean skipNullKeyColumns;
   private boolean firstNext = true;
   private int numFetchedOnNext;
   private int entryVectorSize;
   private List entryVector;
   private boolean hashTableBuilt;
   private boolean firstIntoHashtable = true;
   private ExecRow nextCandidate;
   private ExecRow projRow;
   private BackingStoreHashtable ht;

   HashTableResultSet(NoPutResultSet var1, Activation var2, GeneratedMethod var3, Qualifier[][] var4, GeneratedMethod var5, int var6, int var7, boolean var8, int var9, boolean var10, long var11, int var13, float var14, boolean var15, double var16, double var18) throws StandardException {
      super(var2, var6, var16, var18);
      this.source = var1;
      this.singleTableRestriction = var3;
      this.nextQualifiers = var4;
      this.projection = var5;
      this.projectMapping = ((ReferencedColumnsDescriptorImpl)var2.getPreparedStatement().getSavedObject(var7)).getReferencedColumnPositions();
      FormatableArrayHolder var20 = (FormatableArrayHolder)var2.getPreparedStatement().getSavedObject(var9);
      FormatableIntHolder[] var21 = (FormatableIntHolder[])var20.getArray(FormatableIntHolder[].class);
      this.keyColumns = new int[var21.length];

      for(int var22 = 0; var22 < var21.length; ++var22) {
         this.keyColumns[var22] = var21[var22].getInt();
      }

      this.reuseResult = var8;
      this.removeDuplicates = var10;
      this.maxInMemoryRowCount = var11;
      this.initialCapacity = var13;
      this.loadFactor = var14;
      this.skipNullKeyColumns = var15;
      if (this.projection == null) {
         this.mappedResultRow = this.activation.getExecutionFactory().getValueRow(this.projectMapping.length);
      }

      this.runTimeStatsOn = this.getLanguageConnectionContext().getRunTimeStatisticsMode();
      this.recordConstructorTime();
   }

   public void openCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      TransactionController var1 = this.activation.getTransactionController();
      if (!this.hashTableBuilt) {
         this.source.openCore();
         this.ht = new BackingStoreHashtable(var1, this, this.keyColumns, this.removeDuplicates, (long)((int)this.optimizerEstimatedRowCount), this.maxInMemoryRowCount, this.initialCapacity, this.loadFactor, this.skipNullKeyColumns, false);
         if (this.runTimeStatsOn) {
            this.hashtableSize = this.ht.size();
            if (this.scanProperties == null) {
               this.scanProperties = new Properties();
            }

            try {
               if (this.ht != null) {
                  this.ht.getAllRuntimeStats(this.scanProperties);
               }
            } catch (StandardException var3) {
            }
         }

         this.isOpen = true;
         this.hashTableBuilt = true;
      }

      this.resetProbeVariables();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   public void reopenCore() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      this.resetProbeVariables();
      ++this.numOpens;
      this.openTime += this.getElapsedMillis(this.beginTime);
   }

   private void resetProbeVariables() throws StandardException {
      this.firstNext = true;
      this.numFetchedOnNext = 0;
      this.entryVector = null;
      this.entryVectorSize = 0;
      if (this.nextQualifiers != null) {
         this.clearOrderableCache(this.nextQualifiers);
      }

   }

   public ExecRow getNextRowCore() throws StandardException {
      if (this.isXplainOnlyMode()) {
         return null;
      } else {
         ExecRow var1 = null;
         DataValueDescriptor[] var2 = null;
         this.beginTime = this.getCurrentTimeMillis();
         if (this.isOpen) {
            do {
               if (!this.firstNext) {
                  if (this.numFetchedOnNext < this.entryVectorSize) {
                     var2 = (DataValueDescriptor[])this.entryVector.get(this.numFetchedOnNext);
                  }
               } else {
                  this.firstNext = false;
                  Object var3;
                  if (this.keyColumns.length == 1) {
                     var3 = this.ht.get(this.nextQualifiers[0][0].getOrderable());
                  } else {
                     KeyHasher var4 = new KeyHasher(this.keyColumns.length);

                     for(int var5 = 0; var5 < this.keyColumns.length; ++var5) {
                        var4.setObject(var5, this.nextQualifiers[0][var5].getOrderable());
                     }

                     var3 = this.ht.get(var4);
                  }

                  if (var3 instanceof List) {
                     this.entryVector = (List)var3;
                     this.entryVectorSize = this.entryVector.size();
                     var2 = (DataValueDescriptor[])this.entryVector.get(0);
                  } else {
                     this.entryVector = null;
                     this.entryVectorSize = 0;
                     var2 = (DataValueDescriptor[])var3;
                  }
               }

               if (var2 == null) {
                  var1 = null;
               } else {
                  boolean var6 = true;

                  for(int var8 = 0; var8 < this.nextQualifiers[0].length; ++var8) {
                     Qualifier var10 = this.nextQualifiers[0][var8];
                     var6 = var2[var10.getColumnId()].compare(var10.getOperator(), var10.getOrderable(), var10.getOrderedNulls(), var10.getUnknownRV());
                     if (var10.negateCompareResult()) {
                        var6 = !var6;
                     }

                     if (!var6) {
                        break;
                     }
                  }

                  if (var6) {
                     for(int var9 = 0; var9 < var2.length; ++var9) {
                        this.nextCandidate.setColumn(var9 + 1, var2[var9]);
                     }

                     var1 = this.doProjection(this.nextCandidate);
                  } else {
                     var1 = null;
                  }

                  ++this.numFetchedOnNext;
               }
            } while(var1 == null && this.numFetchedOnNext < this.entryVectorSize);
         }

         this.setCurrentRow(var1);
         this.nextTime += this.getElapsedMillis(this.beginTime);
         if (this.runTimeStatsOn) {
            if (!this.isTopResultSet) {
               StatementContext var7 = this.activation.getLanguageConnectionContext().getStatementContext();
               this.subqueryTrackingArray = var7.getSubqueryTrackingArray();
            }

            this.nextTime += this.getElapsedMillis(this.beginTime);
         }

         return var1;
      }
   }

   public long getTimeSpent(int var1) {
      long var2 = this.constructorTime + this.openTime + this.nextTime + this.closeTime;
      return var1 == 0 ? var2 - this.source.getTimeSpent(1) : var2;
   }

   public void close() throws StandardException {
      this.beginTime = this.getCurrentTimeMillis();
      if (this.isOpen) {
         this.clearCurrentRow();
         this.source.close();
         super.close();
         if (this.hashTableBuilt) {
            this.ht.close();
            this.ht = null;
            this.hashTableBuilt = false;
         }
      }

      this.closeTime += this.getElapsedMillis(this.beginTime);
   }

   public RowLocation getRowLocation() throws StandardException {
      return ((CursorResultSet)this.source).getRowLocation();
   }

   public ExecRow getCurrentRow() throws StandardException {
      Object var1 = null;
      ExecRow var2 = null;
      boolean var3 = false;
      if (this.currentRow == null) {
         return null;
      } else {
         ExecRow var5 = ((CursorResultSet)this.source).getCurrentRow();
         if (var5 != null) {
            this.setCurrentRow(var5);
            DataValueDescriptor var4 = (DataValueDescriptor)(this.singleTableRestriction == null ? null : this.singleTableRestriction.invoke(this.activation));
            var3 = var4 == null || !var4.isNull() && var4.getBoolean();
         }

         if (var5 != null && var3) {
            var2 = this.doProjection(var5);
         }

         this.currentRow = var2;
         if (var2 == null) {
            this.clearCurrentRow();
         }

         return this.currentRow;
      }
   }

   private ExecRow doProjection(ExecRow var1) throws StandardException {
      if (this.reuseResult && this.projRow != null) {
         return this.projRow;
      } else {
         ExecRow var2;
         if (this.projection != null) {
            var2 = (ExecRow)this.projection.invoke(this.activation);
         } else {
            var2 = this.mappedResultRow;
         }

         for(int var3 = 0; var3 < this.projectMapping.length; ++var3) {
            if (this.projectMapping[var3] != -1) {
               var2.setColumn(var3 + 1, var1.getColumn(this.projectMapping[var3]));
            }
         }

         this.setCurrentRow(var2);
         if (this.reuseResult) {
            this.projRow = var2;
         }

         return var2;
      }
   }

   public DataValueDescriptor[] getNextRowFromRowSource() throws StandardException {
      for(ExecRow var1 = this.source.getNextRowCore(); var1 != null; var1 = this.source.getNextRowCore()) {
         boolean var2 = false;
         ++this.rowsSeen;
         DataValueDescriptor var3 = (DataValueDescriptor)(this.singleTableRestriction == null ? null : this.singleTableRestriction.invoke(this.activation));
         var2 = var3 == null || !var3.isNull() && var3.getBoolean();
         if (var2) {
            if (this.targetResultSet != null) {
               this.clonedExecRow = this.targetResultSet.preprocessSourceRow(var1);
            }

            if (this.firstIntoHashtable) {
               this.nextCandidate = this.activation.getExecutionFactory().getValueRow(var1.nColumns());
               this.firstIntoHashtable = false;
            }

            return var1.getRowArray();
         }
      }

      return null;
   }

   public boolean isForUpdate() {
      return this.source == null ? false : this.source.isForUpdate();
   }
}
