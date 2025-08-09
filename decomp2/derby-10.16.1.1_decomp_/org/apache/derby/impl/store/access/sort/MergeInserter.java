package org.apache.derby.impl.store.access.sort;

import java.util.Vector;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortInfo;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

final class MergeInserter implements SortController {
   private MergeSort sort;
   private TransactionManager tran;
   private Vector mergeRuns;
   private SortBuffer sortBuffer;
   private long beginMemoryUsage;
   private boolean avoidMergeRun;
   private int runSize;
   private int totalRunSize;
   String stat_sortType;
   int stat_numRowsInput;
   int stat_numRowsOutput;
   int stat_numMergeRuns;
   Vector stat_mergeRunsSize;

   public void insert(DataValueDescriptor[] var1) throws StandardException {
      this.sort.checkColumnTypes(var1);
      int var2 = this.sortBuffer.insert(var1);
      ++this.stat_numRowsInput;
      if (var2 != 1) {
         ++this.stat_numRowsOutput;
      }

      if (var2 == 2) {
         if (this.avoidMergeRun) {
            Runtime var3 = Runtime.getRuntime();
            long var4 = var3.freeMemory();
            long var6 = var3.totalMemory();
            long var8 = var6 - var4;
            long var10 = var8 - this.beginMemoryUsage;
            if (var10 < 0L) {
               this.beginMemoryUsage = var8;
            }

            if (var10 < 0L || 2L * var10 < (var10 + var4) / 2L || 2L * var10 < 1048576L && var6 < 5242880L) {
               this.sortBuffer.grow(100);
               if (this.sortBuffer.insert(var1) != 2) {
                  return;
               }
            }

            this.avoidMergeRun = false;
         }

         this.stat_sortType = "external";
         long var12 = this.sort.createMergeRun(this.tran, this.sortBuffer);
         if (this.mergeRuns == null) {
            this.mergeRuns = new Vector();
         }

         this.mergeRuns.addElement(var12);
         ++this.stat_numMergeRuns;
         this.runSize = this.stat_numRowsInput - this.totalRunSize - 1;
         this.totalRunSize += this.runSize;
         this.stat_mergeRunsSize.addElement(this.runSize);
         this.sortBuffer.insert(var1);
      }

   }

   public void completedInserts() {
      if (this.sort != null) {
         this.sort.doneInserting(this, this.sortBuffer, this.mergeRuns);
      }

      if (this.stat_sortType == "external") {
         ++this.stat_numMergeRuns;
         this.stat_mergeRunsSize.addElement(this.stat_numRowsInput - this.totalRunSize);
      }

      this.tran.closeMe((SortController)this);
      this.sort = null;
      this.tran = null;
      this.mergeRuns = null;
      this.sortBuffer = null;
   }

   public SortInfo getSortInfo() throws StandardException {
      return new MergeSortInfo(this);
   }

   boolean initialize(MergeSort var1, TransactionManager var2) {
      Runtime var3 = Runtime.getRuntime();
      this.beginMemoryUsage = var3.totalMemory() - var3.freeMemory();
      this.avoidMergeRun = true;
      this.stat_sortType = "internal";
      this.stat_numMergeRuns = 0;
      this.stat_numRowsInput = 0;
      this.stat_numRowsOutput = 0;
      this.stat_mergeRunsSize = new Vector();
      this.runSize = 0;
      this.totalRunSize = 0;
      this.sort = var1;
      this.tran = var2;
      this.sortBuffer = new SortBuffer(var1);
      return this.sortBuffer.init();
   }
}
