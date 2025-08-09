package org.datanucleus.api.jdo;

import java.util.Map;
import javax.jdo.JDOOptimisticVerificationException;
import javax.jdo.JDOUnsupportedOptionException;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.transaction.Synchronization;
import org.datanucleus.TransactionEventListener;
import org.datanucleus.api.jdo.exceptions.TransactionActiveException;
import org.datanucleus.api.jdo.exceptions.TransactionCommitingException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.transaction.TransactionUtils;

public class JDOTransaction implements Transaction {
   org.datanucleus.Transaction tx;
   JDOPersistenceManager pm;

   public JDOTransaction(JDOPersistenceManager pm, org.datanucleus.Transaction tx) {
      this.tx = tx;
      this.pm = pm;
   }

   public JDOPersistenceManager getPersistenceManager() {
      return this.pm;
   }

   public boolean isActive() {
      return this.tx.isActive();
   }

   public void begin() {
      if (this.pm.isClosed()) {
      }

      this.internalBegin();
   }

   protected void internalBegin() {
      this.tx.begin();
   }

   public void commit() {
      try {
         this.tx.commit();
      } catch (NucleusException var7) {
         NucleusException ne = var7;
         if (var7.getNestedExceptions() != null) {
            if (!(var7.getNestedExceptions()[0] instanceof NucleusOptimisticException)) {
               NucleusException ex;
               if (var7.getNestedExceptions()[0] instanceof NucleusException) {
                  ex = (NucleusException)var7.getNestedExceptions()[0];
               } else {
                  ex = new NucleusException(var7.getNestedExceptions()[0].getMessage(), var7.getNestedExceptions()[0]);
               }

               throw NucleusJDOHelper.getJDOExceptionForNucleusException(ex);
            } else if (var7.getNestedExceptions().length <= 1) {
               NucleusException ex;
               if (var7.getNestedExceptions()[0] instanceof NucleusException) {
                  ex = (NucleusException)var7.getNestedExceptions()[0];
               } else {
                  ex = new NucleusException(var7.getNestedExceptions()[0].getMessage(), var7.getNestedExceptions()[0]);
               }

               Throwable[] nested = ex.getNestedExceptions();
               JDOOptimisticVerificationException[] jdoNested = new JDOOptimisticVerificationException[nested.length];

               for(int i = 0; i < nested.length; ++i) {
                  NucleusException nestedEx;
                  if (nested[i] instanceof NucleusException) {
                     nestedEx = (NucleusException)nested[i];
                  } else {
                     nestedEx = new NucleusException(nested[i].getMessage(), nested[i]);
                  }

                  jdoNested[i] = (JDOOptimisticVerificationException)NucleusJDOHelper.getJDOExceptionForNucleusException(nestedEx);
               }

               throw new JDOOptimisticVerificationException(var7.getMessage(), jdoNested);
            } else {
               int numNested = var7.getNestedExceptions().length;
               JDOOptimisticVerificationException[] jdoNested = new JDOOptimisticVerificationException[numNested];

               for(int i = 0; i < numNested; ++i) {
                  NucleusException nested = (NucleusOptimisticException)ne.getNestedExceptions()[i];
                  jdoNested[i] = (JDOOptimisticVerificationException)NucleusJDOHelper.getJDOExceptionForNucleusException(nested);
               }

               throw new JDOOptimisticVerificationException(ne.getMessage(), jdoNested);
            }
         } else {
            throw NucleusJDOHelper.getJDOExceptionForNucleusException(var7);
         }
      }
   }

   public void rollback() {
      try {
         this.tx.rollback();
      } catch (NucleusException jpe) {
         throw NucleusJDOHelper.getJDOExceptionForNucleusException(jpe);
      }
   }

   public boolean getNontransactionalRead() {
      return this.tx.getNontransactionalRead();
   }

   public boolean getNontransactionalWrite() {
      return this.tx.getNontransactionalWrite();
   }

   public boolean getNontransactionalWriteAutoCommit() {
      return this.tx.getNontransactionalWriteAutoCommit();
   }

   public boolean getOptimistic() {
      return this.tx.getOptimistic();
   }

   public boolean getRestoreValues() {
      return this.tx.getRestoreValues();
   }

   public boolean getRetainValues() {
      return this.tx.getRetainValues();
   }

   public boolean getRollbackOnly() {
      return this.tx.getRollbackOnly();
   }

   public Synchronization getSynchronization() {
      return this.tx.getSynchronization();
   }

   public void setNontransactionalRead(boolean flag) {
      this.assertNotCommitting();
      this.tx.setNontransactionalRead(flag);
   }

   public void setNontransactionalWrite(boolean flag) {
      this.assertNotCommitting();
      this.tx.setNontransactionalWrite(flag);
   }

   public void setNontransactionalWriteAutoCommit(boolean flag) {
      this.assertNotCommitting();
      this.tx.setNontransactionalWriteAutoCommit(flag);
   }

   public void setOptimistic(boolean opt) {
      this.assertNotInUse();
      this.assertNotCommitting();
      this.tx.setOptimistic(opt);
   }

   public void setRestoreValues(boolean restore) {
      this.assertNotInUse();
      this.assertNotCommitting();
      this.tx.setRestoreValues(restore);
   }

   public void setRetainValues(boolean retain) {
      this.assertNotCommitting();
      this.tx.setRetainValues(retain);
   }

   public void setRollbackOnly() {
      if (this.tx.isActive()) {
         this.tx.setRollbackOnly();
      }

   }

   public void setSynchronization(Synchronization synch) {
      this.tx.setSynchronization(synch);
   }

   public void setIsolationLevel(String level) {
      this.assertNotCommitting();
      if (this.tx.isActive() && !this.tx.getOptimistic()) {
         throw new JDOUnsupportedOptionException("Cannot change the transaction isolation level while a datastore transaction is active");
      } else {
         PersistenceManagerFactory pmf = this.pm.getPersistenceManagerFactory();
         if (!pmf.supportedOptions().contains("javax.jdo.option.TransactionIsolationLevel." + level)) {
            throw new JDOUnsupportedOptionException("Isolation level \"" + level + "\" not supported by this datastore");
         } else {
            int isolationLevel = TransactionUtils.getTransactionIsolationLevelForName(level);
            this.tx.setOption("transaction.isolation", isolationLevel);
         }
      }
   }

   public String getIsolationLevel() {
      Map<String, Object> txOptions = this.tx.getOptions();
      Object value = txOptions != null ? txOptions.get("transaction.isolation") : null;
      return value != null ? TransactionUtils.getNameForTransactionIsolationLevel((Integer)value) : null;
   }

   public void setSavepoint(String name) {
      if (name == null) {
         throw new IllegalStateException("No savepoint name provided so cannot set savepoint");
      } else if (this.tx.isActive()) {
         this.tx.setSavepoint(name);
      } else {
         throw new IllegalStateException("No active transaction so cannot set savepoint");
      }
   }

   public void releaseSavepoint(String name) {
      if (name == null) {
         throw new IllegalStateException("No savepoint name provided so cannot release savepoint");
      } else if (this.tx.isActive()) {
         this.tx.releaseSavepoint(name);
      } else {
         throw new IllegalStateException("No active transaction so cannot release a savepoint");
      }
   }

   public void rollbackToSavepoint(String name) {
      if (name == null) {
         throw new IllegalStateException("No savepoint name provided so cannot rollback to savepoint");
      } else if (this.tx.isActive()) {
         this.tx.rollbackToSavepoint(name);
      } else {
         throw new IllegalStateException("No active transaction so cannot rollback to savepoint");
      }
   }

   protected void assertNotCommitting() {
      if (this.tx.isCommitting()) {
         throw new TransactionCommitingException(this);
      }
   }

   protected void assertNotInUse() {
      if (this.tx.isActive()) {
         throw new TransactionActiveException(this);
      }
   }

   public Boolean getSerializeRead() {
      return this.tx.getSerializeRead();
   }

   public void setSerializeRead(Boolean serialize) {
      this.assertNotCommitting();
      this.tx.setSerializeRead(serialize);
   }

   public void setOption(String option, int value) {
      this.tx.setOption(option, value);
   }

   public void setOption(String option, boolean value) {
      this.tx.setOption(option, value);
   }

   public void setOption(String option, String value) {
      this.tx.setOption(option, value);
   }

   public void registerEventListener(TransactionEventListener listener) {
      this.tx.bindTransactionEventListener(listener);
   }

   public void deregisterEventListener(TransactionEventListener listener) {
      this.tx.removeTransactionEventListener(listener);
   }
}
