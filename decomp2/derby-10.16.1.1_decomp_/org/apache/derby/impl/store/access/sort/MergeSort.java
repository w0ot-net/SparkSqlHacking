package org.apache.derby.impl.store.access.sort;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.store.access.ColumnOrdering;
import org.apache.derby.iapi.store.access.RowUtil;
import org.apache.derby.iapi.store.access.SortController;
import org.apache.derby.iapi.store.access.SortObserver;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.ScanControllerRowSource;
import org.apache.derby.iapi.store.access.conglomerate.ScanManager;
import org.apache.derby.iapi.store.access.conglomerate.Sort;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.iapi.store.raw.Transaction;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class MergeSort implements Sort {
   private static final int STATE_CLOSED = 0;
   private static final int STATE_INITIALIZED = 1;
   private static final int STATE_INSERTING = 2;
   private static final int STATE_DONE_INSERTING = 3;
   private static final int STATE_SCANNING = 4;
   private static final int STATE_DONE_SCANNING = 5;
   private int state = 0;
   protected DataValueDescriptor[] template;
   protected ColumnOrdering[] columnOrdering;
   protected int[] columnOrderingMap;
   protected boolean[] columnOrderingAscendingMap;
   protected boolean[] columnOrderingNullsLowMap;
   SortObserver sortObserver;
   protected boolean alreadyInOrder;
   private MergeInserter inserter = null;
   private Scan scan = null;
   private Vector mergeRuns = null;
   private SortBuffer sortBuffer = null;
   int sortBufferMax;
   int sortBufferMin;
   static Properties properties = null;

   public SortController open(TransactionManager var1) throws StandardException {
      this.state = 2;
      this.inserter = new MergeInserter();
      if (!this.inserter.initialize(this, var1)) {
         throw StandardException.newException("XSAS6.S", new Object[0]);
      } else {
         return this.inserter;
      }
   }

   public ScanManager openSortScan(TransactionManager var1, boolean var2) throws StandardException {
      if (this.mergeRuns != null && this.mergeRuns.size() != 0) {
         long var3 = this.createMergeRun(var1, this.sortBuffer);
         this.mergeRuns.addElement(var3);
         if (this.mergeRuns.size() > 512 || this.mergeRuns.size() > this.sortBuffer.capacity()) {
            this.multiStageMerge(var1);
         }

         MergeScan var5 = new MergeScan(this, var1, this.sortBuffer, this.mergeRuns, this.sortObserver, var2);
         if (!var5.init(var1)) {
            throw StandardException.newException("XSAS6.S", new Object[0]);
         }

         this.scan = var5;
         this.sortBuffer = null;
         this.mergeRuns = null;
      } else {
         this.scan = new SortBufferScan(this, var1, this.sortBuffer, var2);
         this.sortBuffer = null;
      }

      this.state = 4;
      return this.scan;
   }

   public ScanControllerRowSource openSortRowSource(TransactionManager var1) throws StandardException {
      Object var2 = null;
      if (this.mergeRuns != null && this.mergeRuns.size() != 0) {
         long var3 = this.createMergeRun(var1, this.sortBuffer);
         this.mergeRuns.addElement(var3);
         if (this.mergeRuns.size() > 512 || this.mergeRuns.size() > this.sortBuffer.capacity()) {
            this.multiStageMerge(var1);
         }

         MergeScanRowSource var5 = new MergeScanRowSource(this, var1, this.sortBuffer, this.mergeRuns, this.sortObserver, false);
         if (!var5.init(var1)) {
            throw StandardException.newException("XSAS6.S", new Object[0]);
         }

         this.scan = var5;
         var2 = var5;
         this.sortBuffer = null;
         this.mergeRuns = null;
      } else {
         this.scan = new SortBufferRowSource(this.sortBuffer, var1, this.sortObserver, false, this.sortBufferMax);
         var2 = (ScanControllerRowSource)this.scan;
         this.sortBuffer = null;
      }

      this.state = 4;
      return (ScanControllerRowSource)var2;
   }

   public void drop(TransactionController var1) throws StandardException {
      if (this.inserter != null) {
         this.inserter.completedInserts();
      }

      this.inserter = null;
      if (this.scan != null) {
         this.scan.close();
         this.scan = null;
      }

      if (this.sortBuffer != null) {
         this.sortBuffer.close();
         this.sortBuffer = null;
      }

      this.template = null;
      this.columnOrdering = null;
      this.sortObserver = null;
      this.dropMergeRuns((TransactionManager)var1);
      this.state = 0;
   }

   private boolean checkColumnOrdering(DataValueDescriptor[] var1, ColumnOrdering[] var2) {
      int var3 = var1.length;
      boolean[] var4 = new boolean[var3];

      for(int var5 = 0; var5 < var2.length; ++var5) {
         int var6 = var2[var5].getColumnId();
         if (var6 < 0 || var6 >= var3) {
            return false;
         }

         if (var4[var6]) {
            return false;
         }

         var4[var6] = true;
         DataValueDescriptor var7 = RowUtil.getColumn(var1, (FormatableBitSet)null, var6);
         if (var7 == null) {
            return false;
         }
      }

      return true;
   }

   void checkColumnTypes(DataValueDescriptor[] var1) throws StandardException {
      int var2 = var1.length;
      if (this.template.length != var2) {
         throw StandardException.newException("XSAS3.S", new Object[0]);
      }
   }

   protected int compare(DataValueDescriptor[] var1, DataValueDescriptor[] var2) throws StandardException {
      int var3 = this.columnOrdering.length;

      for(int var5 = 0; var5 < var3; ++var5) {
         if (var5 == var3 - 1 && this.sortObserver.deferrable()) {
            if (!this.sortObserver.deferred()) {
               break;
            }

            this.sortObserver.rememberDuplicate(var1);
         }

         int var6 = this.columnOrderingMap[var5];
         boolean var7 = this.columnOrderingNullsLowMap[var5];
         int var4;
         if ((var4 = var1[var6].compare(var2[var6], var7)) != 0) {
            if (this.columnOrderingAscendingMap[var5]) {
               return var4;
            }

            return -var4;
         }
      }

      return 0;
   }

   public void initialize(DataValueDescriptor[] var1, ColumnOrdering[] var2, SortObserver var3, boolean var4, long var5, int var7) throws StandardException {
      this.template = var1;
      this.columnOrdering = var2;
      this.sortObserver = var3;
      this.alreadyInOrder = var4;
      this.columnOrderingMap = new int[var2.length];
      this.columnOrderingAscendingMap = new boolean[var2.length];
      this.columnOrderingNullsLowMap = new boolean[var2.length];

      for(int var8 = 0; var8 < var2.length; ++var8) {
         this.columnOrderingMap[var8] = var2[var8].getColumnId();
         this.columnOrderingAscendingMap[var8] = var2[var8].getIsAscending();
         this.columnOrderingNullsLowMap[var8] = var2[var8].getIsNullsOrderedLow();
      }

      this.inserter = null;
      this.scan = null;
      this.mergeRuns = null;
      this.sortBuffer = null;
      this.sortBufferMax = var7;
      if (var5 > (long)var7) {
         this.sortBufferMin = var7;
      } else {
         this.sortBufferMin = (int)var5;
      }

      this.state = 1;
   }

   void doneInserting(MergeInserter var1, SortBuffer var2, Vector var3) {
      this.sortBuffer = var2;
      this.mergeRuns = var3;
      this.inserter = null;
      this.state = 3;
   }

   void doneScanning(Scan var1, SortBuffer var2) {
      this.sortBuffer = var2;
      this.scan = null;
      this.state = 5;
   }

   void doneScanning(Scan var1, SortBuffer var2, Vector var3) {
      this.mergeRuns = var3;
      this.doneScanning(var1, var2);
   }

   void dropMergeRuns(TransactionManager var1) {
      if (this.mergeRuns != null) {
         Enumeration var2 = this.mergeRuns.elements();

         try {
            Transaction var3 = var1.getRawStoreXact();
            long var4 = -1L;

            while(var2.hasMoreElements()) {
               long var6 = (Long)var2.nextElement();
               var3.dropStreamContainer(var4, var6);
            }
         } catch (StandardException var8) {
         }

         this.mergeRuns = null;
      }

   }

   private void multiStageMerge(TransactionManager var1) throws StandardException {
      int var3 = this.sortBuffer.capacity();
      if (var3 > 512) {
         var3 = 512;
      }

      while(this.mergeRuns.size() > var3) {
         Vector var4 = new Vector(var3);
         Vector var5 = new Vector(this.mergeRuns.size() - var3);
         Enumeration var2 = this.mergeRuns.elements();

         while(var2.hasMoreElements()) {
            Long var6 = (Long)var2.nextElement();
            if (var4.size() < var3) {
               var4.addElement(var6);
            } else {
               var5.addElement(var6);
            }
         }

         this.mergeRuns = var5;
         MergeScanRowSource var13 = new MergeScanRowSource(this, var1, this.sortBuffer, var4, this.sortObserver, false);
         if (!var13.init(var1)) {
            throw StandardException.newException("XSAS6.S", new Object[0]);
         }

         Transaction var7 = var1.getRawStoreXact();
         byte var8 = -1;
         long var9 = var7.addAndLoadStreamContainer((long)var8, properties, var13);
         this.mergeRuns.addElement(var9);
         var2 = var4.elements();

         while(var2.hasMoreElements()) {
            Long var11 = (Long)var2.nextElement();
            var7.dropStreamContainer((long)var8, var11);
         }
      }

   }

   long createMergeRun(TransactionManager var1, SortBuffer var2) throws StandardException {
      SortBufferRowSource var3 = new SortBufferRowSource(var2, (TransactionManager)null, this.sortObserver, true, this.sortBufferMax);
      Transaction var4 = var1.getRawStoreXact();
      byte var5 = -1;
      long var6 = var4.addAndLoadStreamContainer((long)var5, properties, var3);
      var3 = null;
      return var6;
   }

   static {
      properties = new Properties();
      properties.put("derby.storage.streamFileBufferSize", "16384");
   }
}
