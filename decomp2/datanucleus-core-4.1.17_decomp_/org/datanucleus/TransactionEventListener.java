package org.datanucleus;

public interface TransactionEventListener {
   void transactionStarted();

   void transactionEnded();

   void transactionPreFlush();

   void transactionFlushed();

   void transactionPreCommit();

   void transactionCommitted();

   void transactionPreRollBack();

   void transactionRolledBack();

   void transactionSetSavepoint(String var1);

   void transactionReleaseSavepoint(String var1);

   void transactionRollbackToSavepoint(String var1);
}
