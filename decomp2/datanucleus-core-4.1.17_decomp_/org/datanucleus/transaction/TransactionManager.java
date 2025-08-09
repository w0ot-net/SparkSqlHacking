package org.datanucleus.transaction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.datanucleus.ExecutionContext;

public class TransactionManager {
   private boolean containerManagedConnections = false;
   private Map txnForExecutionContext = new ConcurrentHashMap();

   public void setContainerManagedConnections(boolean flag) {
      this.containerManagedConnections = flag;
   }

   public void begin(ExecutionContext ec) {
      if (this.txnForExecutionContext.get(ec) != null) {
         throw new NucleusTransactionException("Invalid state. Transaction has already started");
      } else {
         this.txnForExecutionContext.put(ec, new Transaction());
      }
   }

   public void commit(ExecutionContext ec) {
      Transaction tx = (Transaction)this.txnForExecutionContext.get(ec);
      if (tx == null) {
         throw new NucleusTransactionException("Invalid state. Transaction does not exist");
      } else {
         try {
            if (!this.containerManagedConnections) {
               tx.commit();
            }
         } finally {
            this.txnForExecutionContext.remove(ec);
         }

      }
   }

   public void rollback(ExecutionContext ec) {
      Transaction tx = (Transaction)this.txnForExecutionContext.get(ec);
      if (tx == null) {
         throw new NucleusTransactionException("Invalid state. Transaction does not exist");
      } else {
         try {
            if (!this.containerManagedConnections) {
               tx.rollback();
            }
         } finally {
            this.txnForExecutionContext.remove(ec);
         }

      }
   }

   public Transaction getTransaction(ExecutionContext ec) {
      return ec == null ? null : (Transaction)this.txnForExecutionContext.get(ec);
   }

   public void setRollbackOnly(ExecutionContext ec) {
      Transaction tx = (Transaction)this.txnForExecutionContext.get(ec);
      if (tx == null) {
         throw new NucleusTransactionException("Invalid state. Transaction does not exist");
      } else {
         tx.setRollbackOnly();
      }
   }

   public void setTransactionTimeout(ExecutionContext ec, int millis) {
      throw new UnsupportedOperationException();
   }

   public void resume(ExecutionContext ec, Transaction tx) {
      throw new UnsupportedOperationException();
   }

   public Transaction suspend(ExecutionContext ec) {
      throw new UnsupportedOperationException();
   }
}
