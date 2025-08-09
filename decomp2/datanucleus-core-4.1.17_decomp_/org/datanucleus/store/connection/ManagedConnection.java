package org.datanucleus.store.connection;

import javax.transaction.xa.XAResource;

public interface ManagedConnection {
   Object getConnection();

   void release();

   void transactionFlushed();

   void transactionPreClose();

   void close();

   void setCommitOnRelease(boolean var1);

   void setCloseOnRelease(boolean var1);

   boolean commitOnRelease();

   boolean closeOnRelease();

   XAResource getXAResource();

   boolean isLocked();

   void lock();

   void unlock();

   void addListener(ManagedConnectionResourceListener var1);

   void removeListener(ManagedConnectionResourceListener var1);

   boolean closeAfterTransactionEnd();

   void setSavepoint(String var1);

   void releaseSavepoint(String var1);

   void rollbackToSavepoint(String var1);
}
