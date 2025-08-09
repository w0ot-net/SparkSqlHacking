package org.datanucleus.management;

import org.datanucleus.util.MathUtils;

public abstract class AbstractStatistics {
   AbstractStatistics parent = null;
   String registeredName;
   int numReads = 0;
   int numWrites = 0;
   int numReadsLastTxn = 0;
   int numWritesLastTxn = 0;
   int numReadsStartTxn = 0;
   int numWritesStartTxn = 0;
   int insertCount = 0;
   int deleteCount = 0;
   int updateCount = 0;
   int fetchCount = 0;
   int txnTotalCount;
   int txnCommittedTotalCount;
   int txnRolledBackTotalCount;
   int txnActiveTotalCount;
   int txnExecutionTotalTime = 0;
   int txnExecutionTimeHigh = -1;
   int txnExecutionTimeLow = -1;
   MathUtils.SMA txnExecutionTimeAverage = new MathUtils.SMA(50);
   int queryActiveTotalCount;
   int queryErrorTotalCount;
   int queryExecutionTotalCount;
   int queryExecutionTotalTime = 0;
   int queryExecutionTimeHigh = -1;
   int queryExecutionTimeLow = -1;
   MathUtils.SMA queryExecutionTimeAverage = new MathUtils.SMA(50);

   public AbstractStatistics(String name) {
      this.registeredName = name;
   }

   public String getRegisteredName() {
      return this.registeredName;
   }

   public int getQueryActiveTotalCount() {
      return this.queryActiveTotalCount;
   }

   public int getQueryErrorTotalCount() {
      return this.queryErrorTotalCount;
   }

   public int getQueryExecutionTotalCount() {
      return this.queryExecutionTotalCount;
   }

   public int getQueryExecutionTimeLow() {
      return this.queryExecutionTimeLow;
   }

   public int getQueryExecutionTimeHigh() {
      return this.queryExecutionTimeHigh;
   }

   public int getQueryExecutionTotalTime() {
      return this.queryExecutionTotalTime;
   }

   public int getQueryExecutionTimeAverage() {
      return (int)this.queryExecutionTimeAverage.currentAverage();
   }

   public void queryBegin() {
      ++this.queryActiveTotalCount;
      if (this.parent != null) {
         this.parent.queryBegin();
      }

   }

   public void queryExecutedWithError() {
      ++this.queryErrorTotalCount;
      --this.queryActiveTotalCount;
      if (this.parent != null) {
         this.parent.queryExecutedWithError();
      }

   }

   public void queryExecuted(long executionTime) {
      ++this.queryExecutionTotalCount;
      --this.queryActiveTotalCount;
      this.queryExecutionTimeAverage.compute((double)executionTime);
      this.queryExecutionTimeLow = (int)Math.min(this.queryExecutionTimeLow == -1 ? executionTime : (long)this.queryExecutionTimeLow, executionTime);
      this.queryExecutionTimeHigh = (int)Math.max((long)this.queryExecutionTimeHigh, executionTime);
      this.queryExecutionTotalTime = (int)((long)this.queryExecutionTotalTime + executionTime);
      if (this.parent != null) {
         this.parent.queryExecuted(executionTime);
      }

   }

   public int getNumberOfDatastoreWrites() {
      return this.numWrites;
   }

   public int getNumberOfDatastoreReads() {
      return this.numReads;
   }

   public int getNumberOfDatastoreWritesInLatestTxn() {
      return this.numWritesLastTxn;
   }

   public int getNumberOfDatastoreReadsInLatestTxn() {
      return this.numReadsLastTxn;
   }

   public void incrementNumReads() {
      ++this.numReads;
      if (this.parent != null) {
         this.parent.incrementNumReads();
      }

   }

   public void incrementNumWrites() {
      ++this.numWrites;
      if (this.parent != null) {
         this.parent.incrementNumWrites();
      }

   }

   public int getNumberOfObjectFetches() {
      return this.fetchCount;
   }

   public int getNumberOfObjectInserts() {
      return this.insertCount;
   }

   public int getNumberOfObjectUpdates() {
      return this.updateCount;
   }

   public int getNumberOfObjectDeletes() {
      return this.deleteCount;
   }

   public void incrementInsertCount() {
      ++this.insertCount;
      if (this.parent != null) {
         this.parent.incrementInsertCount();
      }

   }

   public void incrementDeleteCount() {
      ++this.deleteCount;
      if (this.parent != null) {
         this.parent.incrementDeleteCount();
      }

   }

   public void incrementFetchCount() {
      ++this.fetchCount;
      if (this.parent != null) {
         this.parent.incrementFetchCount();
      }

   }

   public void incrementUpdateCount() {
      ++this.updateCount;
      if (this.parent != null) {
         this.parent.incrementUpdateCount();
      }

   }

   public int getTransactionExecutionTimeAverage() {
      return (int)this.txnExecutionTimeAverage.currentAverage();
   }

   public int getTransactionExecutionTimeLow() {
      return this.txnExecutionTimeLow;
   }

   public int getTransactionExecutionTimeHigh() {
      return this.txnExecutionTimeHigh;
   }

   public int getTransactionExecutionTotalTime() {
      return this.txnExecutionTotalTime;
   }

   public int getTransactionTotalCount() {
      return this.txnTotalCount;
   }

   public int getTransactionActiveTotalCount() {
      return this.txnActiveTotalCount;
   }

   public int getTransactionCommittedTotalCount() {
      return this.txnCommittedTotalCount;
   }

   public int getTransactionRolledBackTotalCount() {
      return this.txnRolledBackTotalCount;
   }

   public void transactionCommitted(long executionTime) {
      ++this.txnCommittedTotalCount;
      --this.txnActiveTotalCount;
      this.txnExecutionTimeAverage.compute((double)executionTime);
      this.txnExecutionTimeLow = (int)Math.min(this.txnExecutionTimeLow == -1 ? executionTime : (long)this.txnExecutionTimeLow, executionTime);
      this.txnExecutionTimeHigh = (int)Math.max((long)this.txnExecutionTimeHigh, executionTime);
      this.txnExecutionTotalTime = (int)((long)this.txnExecutionTotalTime + executionTime);
      this.numReadsLastTxn = this.numReads - this.numReadsStartTxn;
      this.numWritesLastTxn = this.numWrites - this.numWritesStartTxn;
      if (this.parent != null) {
         this.parent.transactionCommitted(executionTime);
      }

   }

   public void transactionRolledBack(long executionTime) {
      ++this.txnRolledBackTotalCount;
      --this.txnActiveTotalCount;
      this.txnExecutionTimeAverage.compute((double)executionTime);
      this.txnExecutionTimeLow = (int)Math.min(this.txnExecutionTimeLow == -1 ? executionTime : (long)this.txnExecutionTimeLow, executionTime);
      this.txnExecutionTimeHigh = (int)Math.max((long)this.txnExecutionTimeHigh, executionTime);
      this.txnExecutionTotalTime = (int)((long)this.txnExecutionTotalTime + executionTime);
      this.numReadsLastTxn = this.numReads - this.numReadsStartTxn;
      this.numWritesLastTxn = this.numWrites - this.numWritesStartTxn;
      if (this.parent != null) {
         this.parent.transactionRolledBack(executionTime);
      }

   }

   public void transactionStarted() {
      ++this.txnTotalCount;
      ++this.txnActiveTotalCount;
      this.numReadsStartTxn = this.numReads;
      this.numWritesStartTxn = this.numWrites;
      if (this.parent != null) {
         this.parent.transactionStarted();
      }

   }
}
