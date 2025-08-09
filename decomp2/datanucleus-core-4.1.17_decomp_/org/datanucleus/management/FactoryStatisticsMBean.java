package org.datanucleus.management;

public interface FactoryStatisticsMBean {
   String getRegisteredName();

   int getQueryActiveTotalCount();

   int getQueryErrorTotalCount();

   int getQueryExecutionTotalCount();

   int getQueryExecutionTimeLow();

   int getQueryExecutionTimeHigh();

   int getQueryExecutionTotalTime();

   int getQueryExecutionTimeAverage();

   int getNumberOfDatastoreWrites();

   int getNumberOfDatastoreReads();

   int getNumberOfDatastoreWritesInLatestTxn();

   int getNumberOfDatastoreReadsInLatestTxn();

   int getNumberOfObjectFetches();

   int getNumberOfObjectInserts();

   int getNumberOfObjectUpdates();

   int getNumberOfObjectDeletes();

   int getTransactionExecutionTimeAverage();

   int getTransactionExecutionTimeLow();

   int getTransactionExecutionTimeHigh();

   int getTransactionExecutionTotalTime();

   int getTransactionTotalCount();

   int getTransactionActiveTotalCount();

   int getTransactionCommittedTotalCount();

   int getTransactionRolledBackTotalCount();

   int getConnectionActiveCurrent();

   int getConnectionActiveHigh();

   int getConnectionActiveTotal();
}
