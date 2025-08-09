package org.apache.derby.iapi.store.access;

public interface TransactionInfo {
   String getGlobalTransactionIdString();

   String getTransactionIdString();

   String getUsernameString();

   String getTransactionTypeString();

   String getTransactionStatusString();

   String getFirstLogInstantString();

   String getStatementTextString();
}
