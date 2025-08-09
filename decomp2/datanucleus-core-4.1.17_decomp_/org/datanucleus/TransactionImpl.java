package org.datanucleus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Synchronization;
import org.datanucleus.exceptions.NucleusDataStoreException;
import org.datanucleus.exceptions.NucleusException;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.exceptions.TransactionActiveOnBeginException;
import org.datanucleus.exceptions.TransactionNotActiveException;
import org.datanucleus.properties.PropertyStore;
import org.datanucleus.transaction.HeuristicMixedException;
import org.datanucleus.transaction.HeuristicRollbackException;
import org.datanucleus.transaction.NucleusTransactionException;
import org.datanucleus.transaction.RollbackException;
import org.datanucleus.transaction.TransactionManager;
import org.datanucleus.transaction.TransactionUtils;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;
import org.datanucleus.util.StringUtils;

public class TransactionImpl implements Transaction {
   ExecutionContext ec;
   TransactionManager txnMgr;
   boolean active = false;
   boolean committing;
   Synchronization sync;
   protected boolean rollbackOnly = false;
   protected Boolean serializeRead = null;
   private Set listenersPerTransaction = new HashSet();
   private TransactionEventListener ecListener;
   private List userListeners = new ArrayList();
   private Map options = null;
   long beginTime;
   boolean closed = false;
   private PropertyStore properties;

   public TransactionImpl(ExecutionContext ec, PropertyStore properties) {
      this.ec = ec;
      this.ecListener = (TransactionEventListener)ec;
      this.txnMgr = ec.getNucleusContext().getTransactionManager();
      this.properties = properties;
      Configuration config = ec.getNucleusContext().getConfiguration();
      int isolationLevel = TransactionUtils.getTransactionIsolationLevelForName(config.getStringProperty("datanucleus.transactionIsolation"));
      this.setOption("transaction.isolation", isolationLevel);
      if (properties != null) {
         Boolean serialiseReadProp = properties.getFrequentProperties().getSerialiseRead();
         this.serializeRead = serialiseReadProp == null ? this.serializeRead : serialiseReadProp;
      } else {
         Boolean serialiseReadProp = config.getBooleanObjectProperty("datanucleus.SerializeRead");
         if (ec.getProperty("datanucleus.SerializeRead") != null) {
            serialiseReadProp = ec.getBooleanProperty("datanucleus.SerializeRead");
         }

         if (serialiseReadProp != null) {
            this.serializeRead = serialiseReadProp;
         }
      }

   }

   public void close() {
      this.closed = true;
   }

   public void begin() {
      if (this.ec.getMultithreaded()) {
         synchronized(this) {
            this.txnMgr.begin(this.ec);
         }
      } else {
         this.txnMgr.begin(this.ec);
      }

      this.internalBegin();
   }

   protected void internalBegin() {
      if (this.active) {
         throw new TransactionActiveOnBeginException(this.ec);
      } else {
         this.active = true;
         this.beginTime = System.currentTimeMillis();
         if (this.ec.getStatistics() != null) {
            this.ec.getStatistics().transactionStarted();
         }

         if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
            NucleusLogger.TRANSACTION.debug(Localiser.msg("015000", this.ec, "" + this.ec.getBooleanProperty("datanucleus.Optimistic")));
         }

         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionStarted();
         }

      }
   }

   public void preFlush() {
      try {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionPreFlush();
         }

      } catch (Throwable ex) {
         if (ex instanceof NucleusException) {
            throw (NucleusException)ex;
         } else {
            throw new NucleusTransactionException(Localiser.msg("015005"), ex);
         }
      }
   }

   public void flush() {
      try {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionFlushed();
         }

      } catch (Throwable ex) {
         if (ex instanceof NucleusException) {
            throw (NucleusException)ex;
         } else {
            throw new NucleusTransactionException(Localiser.msg("015005"), ex);
         }
      }
   }

   public void end() {
      boolean var13 = false;

      try {
         var13 = true;
         this.flush();
         var13 = false;
      } finally {
         if (var13) {
            TransactionEventListener[] ls = this.getListenersForEvent();

            for(TransactionEventListener tel : ls) {
               tel.transactionEnded();
            }

         }
      }

      TransactionEventListener[] ls = this.getListenersForEvent();

      for(TransactionEventListener tel : ls) {
         tel.transactionEnded();
      }

   }

   public void commit() {
      if (!this.isActive()) {
         throw new TransactionNotActiveException();
      } else if (this.rollbackOnly) {
         if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
            NucleusLogger.TRANSACTION.debug(Localiser.msg("015020"));
         }

         throw (new NucleusDataStoreException(Localiser.msg("015020"))).setFatal();
      } else {
         long startTime = System.currentTimeMillis();
         boolean success = false;
         boolean canComplete = true;
         List<Throwable> errors = new ArrayList();

         try {
            this.flush();
            this.internalPreCommit();
            this.internalCommit();
            success = true;
         } catch (RollbackException var22) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var22));
            }

            errors.add(var22);
         } catch (HeuristicRollbackException var23) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var23));
            }

            errors.add(var23);
         } catch (HeuristicMixedException var24) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var24));
            }

            errors.add(var24);
         } catch (NucleusUserException var25) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var25));
            }

            canComplete = false;
            throw var25;
         } catch (NucleusException var26) {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var26));
            }

            errors.add(var26);
         } finally {
            if (canComplete) {
               try {
                  if (!success) {
                     this.rollback();
                  } else {
                     this.internalPostCommit();
                  }
               } catch (Throwable e) {
                  errors.add(e);
               }
            }

         }

         if (errors.size() > 0) {
            throw new NucleusTransactionException(Localiser.msg("015007"), (Throwable[])errors.toArray(new Throwable[errors.size()]));
         } else {
            if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
               NucleusLogger.TRANSACTION.debug(Localiser.msg("015022", System.currentTimeMillis() - startTime));
            }

         }
      }
   }

   protected void internalPreCommit() {
      this.committing = true;
      if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
         NucleusLogger.TRANSACTION.debug(Localiser.msg("015001", this.ec));
      }

      if (this.sync != null) {
         this.sync.beforeCompletion();
      }

      TransactionEventListener[] ls = this.getListenersForEvent();

      for(TransactionEventListener tel : ls) {
         tel.transactionPreCommit();
      }

   }

   protected void internalCommit() {
      if (this.ec.getMultithreaded()) {
         synchronized(this) {
            this.txnMgr.commit(this.ec);
         }
      } else {
         this.txnMgr.commit(this.ec);
      }

   }

   public void rollback() {
      if (!this.isActive()) {
         throw new TransactionNotActiveException();
      } else {
         long startTime = System.currentTimeMillis();

         try {
            boolean canComplete = true;
            this.committing = true;

            try {
               this.flush();
            } finally {
               try {
                  this.internalPreRollback();
               } catch (NucleusUserException var296) {
                  if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
                     NucleusLogger.TRANSACTION.debug(StringUtils.getStringFromStackTrace(var296));
                  }

                  canComplete = false;
                  throw var296;
               } finally {
                  if (canComplete) {
                     try {
                        this.internalRollback();
                     } finally {
                        try {
                           this.active = false;
                           if (this.ec.getStatistics() != null) {
                              this.ec.getStatistics().transactionRolledBack(System.currentTimeMillis() - this.beginTime);
                           }
                        } finally {
                           this.listenersPerTransaction.clear();
                           this.rollbackOnly = false;
                           if (this.sync != null) {
                              this.sync.afterCompletion(4);
                           }

                        }

                     }
                  }

               }
            }
         } catch (NucleusUserException e) {
            throw e;
         } catch (NucleusException e) {
            throw new NucleusDataStoreException(Localiser.msg("015009"), e);
         } finally {
            this.committing = false;
         }

         if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
            NucleusLogger.TRANSACTION.debug(Localiser.msg("015023", System.currentTimeMillis() - startTime));
         }

      }
   }

   protected void internalPreRollback() {
      if (NucleusLogger.TRANSACTION.isDebugEnabled()) {
         NucleusLogger.TRANSACTION.debug(Localiser.msg("015002", this.ec));
      }

      TransactionEventListener[] ls = this.getListenersForEvent();

      for(TransactionEventListener tel : ls) {
         tel.transactionPreRollBack();
      }

   }

   protected void internalRollback() {
      org.datanucleus.transaction.Transaction tx = this.txnMgr.getTransaction(this.ec);
      if (tx != null) {
         if (this.ec.getMultithreaded()) {
            synchronized(this) {
               this.txnMgr.rollback(this.ec);
            }
         } else {
            this.txnMgr.rollback(this.ec);
         }
      }

      TransactionEventListener[] ls = this.getListenersForEvent();

      for(TransactionEventListener tel : ls) {
         tel.transactionRolledBack();
      }

   }

   protected void internalPostCommit() {
      boolean var23 = false;

      try {
         var23 = true;
         this.active = false;
         if (this.ec.getStatistics() != null) {
            this.ec.getStatistics().transactionCommitted(System.currentTimeMillis() - this.beginTime);
            var23 = false;
         } else {
            var23 = false;
         }
      } finally {
         if (var23) {
            try {
               TransactionEventListener[] ls = this.getListenersForEvent();

               for(TransactionEventListener tel : ls) {
                  tel.transactionCommitted();
               }
            } finally {
               this.committing = false;
               this.listenersPerTransaction.clear();
               if (this.sync != null) {
                  this.sync.afterCompletion(3);
               }

            }

         }
      }

      try {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionCommitted();
         }
      } finally {
         this.committing = false;
         this.listenersPerTransaction.clear();
         if (this.sync != null) {
            this.sync.afterCompletion(3);
         }

      }

   }

   private TransactionEventListener[] getListenersForEvent() {
      TransactionEventListener[] ls = new TransactionEventListener[this.userListeners.size() + this.listenersPerTransaction.size() + 1];
      System.arraycopy(this.listenersPerTransaction.toArray(), 0, ls, 0, this.listenersPerTransaction.size());
      System.arraycopy(this.userListeners.toArray(), 0, ls, this.listenersPerTransaction.size(), this.userListeners.size());
      ls[ls.length - 1] = this.ecListener;
      return ls;
   }

   public boolean isActive() {
      return this.active;
   }

   public boolean getIsActive() {
      return this.active;
   }

   public boolean isCommitting() {
      return this.committing;
   }

   public boolean getNontransactionalRead() {
      return this.ec.getBooleanProperty("datanucleus.NontransactionalRead");
   }

   public boolean getNontransactionalWrite() {
      return this.ec.getBooleanProperty("datanucleus.NontransactionalWrite");
   }

   public boolean getNontransactionalWriteAutoCommit() {
      return this.ec.getBooleanProperty("datanucleus.nontx.atomic");
   }

   public boolean getOptimistic() {
      return this.properties != null ? this.properties.getFrequentProperties().getOptimisticTransaction() : this.ec.getBooleanProperty("datanucleus.Optimistic");
   }

   public boolean getRestoreValues() {
      return this.ec.getBooleanProperty("datanucleus.RestoreValues");
   }

   public boolean getRetainValues() {
      return this.ec.getBooleanProperty("datanucleus.RetainValues");
   }

   public boolean getRollbackOnly() {
      return this.rollbackOnly;
   }

   public Synchronization getSynchronization() {
      return this.sync;
   }

   public void setNontransactionalRead(boolean nontransactionalRead) {
      this.ec.setProperty("datanucleus.NontransactionalRead", nontransactionalRead);
   }

   public void setNontransactionalWrite(boolean nontransactionalWrite) {
      this.ec.setProperty("datanucleus.NontransactionalWrite", nontransactionalWrite);
   }

   public void setNontransactionalWriteAutoCommit(boolean autoCommit) {
      this.ec.setProperty("datanucleus.nontx.atomic", autoCommit);
   }

   public void setOptimistic(boolean optimistic) {
      this.ec.setProperty("datanucleus.Optimistic", optimistic);
   }

   public void setRestoreValues(boolean restoreValues) {
      this.ec.setProperty("datanucleus.RestoreValues", restoreValues);
   }

   public void setRetainValues(boolean retainValues) {
      this.ec.setProperty("datanucleus.RetainValues", retainValues);
      if (retainValues) {
         this.setNontransactionalRead(true);
      }

   }

   public void setRollbackOnly() {
      if (this.active) {
         this.rollbackOnly = true;
      }

   }

   public void setSavepoint(String name) {
      if (this.active) {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionSetSavepoint(name);
         }

      }
   }

   public void releaseSavepoint(String name) {
      if (this.active) {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionReleaseSavepoint(name);
         }

      }
   }

   public void rollbackToSavepoint(String name) {
      if (this.active) {
         TransactionEventListener[] ls = this.getListenersForEvent();

         for(TransactionEventListener tel : ls) {
            tel.transactionRollbackToSavepoint(name);
         }

      }
   }

   public void setSynchronization(Synchronization sync) {
      this.sync = sync;
   }

   public void addTransactionEventListener(TransactionEventListener listener) {
      this.listenersPerTransaction.add(listener);
   }

   public void removeTransactionEventListener(TransactionEventListener listener) {
      this.listenersPerTransaction.remove(listener);
      this.userListeners.remove(listener);
   }

   public void bindTransactionEventListener(TransactionEventListener listener) {
      this.userListeners.add(listener);
   }

   public Boolean getSerializeRead() {
      return this.serializeRead;
   }

   public void setSerializeRead(Boolean serializeRead) {
      this.serializeRead = serializeRead;
   }

   public Map getOptions() {
      return this.options;
   }

   public void setOption(String option, int value) {
      if (this.options == null) {
         this.options = new HashMap();
      }

      this.options.put(option, value);
   }

   public void setOption(String option, boolean value) {
      if (this.options == null) {
         this.options = new HashMap();
      }

      this.options.put(option, value);
   }

   public void setOption(String option, String value) {
      if (this.options == null) {
         this.options = new HashMap();
      }

      this.options.put(option, value);
   }

   public void setOption(String option, Object value) {
      if (this.options == null) {
         this.options = new HashMap();
      }

      this.options.put(option, value);
   }

   public void setProperties(PropertyStore properties) {
      this.properties = properties;
   }
}
