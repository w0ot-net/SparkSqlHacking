package org.datanucleus;

import java.util.Map;
import javax.transaction.Synchronization;

public interface Transaction {
   String TRANSACTION_ISOLATION_OPTION = "transaction.isolation";

   void close();

   void begin();

   void commit();

   void rollback();

   boolean isActive();

   boolean getIsActive();

   void preFlush();

   void flush();

   void end();

   boolean getRollbackOnly();

   void setRollbackOnly();

   void setNontransactionalRead(boolean var1);

   boolean getNontransactionalRead();

   void setNontransactionalWrite(boolean var1);

   boolean getNontransactionalWrite();

   void setNontransactionalWriteAutoCommit(boolean var1);

   boolean getNontransactionalWriteAutoCommit();

   void setRetainValues(boolean var1);

   boolean getRetainValues();

   void setRestoreValues(boolean var1);

   boolean getRestoreValues();

   void setOptimistic(boolean var1);

   boolean getOptimistic();

   void setSerializeRead(Boolean var1);

   Boolean getSerializeRead();

   void setSynchronization(Synchronization var1);

   Synchronization getSynchronization();

   boolean isCommitting();

   void setSavepoint(String var1);

   void releaseSavepoint(String var1);

   void rollbackToSavepoint(String var1);

   void addTransactionEventListener(TransactionEventListener var1);

   void removeTransactionEventListener(TransactionEventListener var1);

   void bindTransactionEventListener(TransactionEventListener var1);

   Map getOptions();

   void setOption(String var1, int var2);

   void setOption(String var1, boolean var2);

   void setOption(String var1, String var2);

   void setOption(String var1, Object var2);
}
