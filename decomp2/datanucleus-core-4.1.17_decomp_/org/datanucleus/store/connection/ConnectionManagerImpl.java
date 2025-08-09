package org.datanucleus.store.connection;

import java.util.HashMap;
import java.util.Map;
import javax.transaction.xa.XAResource;
import org.datanucleus.ExecutionContext;
import org.datanucleus.PersistenceNucleusContext;
import org.datanucleus.Transaction;
import org.datanucleus.TransactionEventListener;
import org.datanucleus.exceptions.NucleusUserException;
import org.datanucleus.store.federation.FederatedStoreManager;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class ConnectionManagerImpl implements ConnectionManager {
   PersistenceNucleusContext nucleusContext;
   Map factories = new HashMap();
   ManagedConnectionPool connectionPool = new ManagedConnectionPool();
   boolean connectionPoolEnabled = true;

   public ConnectionManagerImpl(PersistenceNucleusContext context) {
      this.nucleusContext = context;
   }

   public void closeAllConnections(ConnectionFactory factory, ExecutionContext ec) {
      if (ec != null && this.connectionPoolEnabled) {
         ManagedConnection mconnFromPool = this.connectionPool.getManagedConnection(factory, ec);
         if (mconnFromPool != null) {
            if (NucleusLogger.CONNECTION.isDebugEnabled()) {
               NucleusLogger.CONNECTION.debug(Localiser.msg("009005", mconnFromPool, ec, factory));
            }

            mconnFromPool.close();
         }
      }

   }

   public ManagedConnection allocateConnection(final ConnectionFactory factory, final ExecutionContext ec, Transaction transaction, Map options) {
      if (ec != null && this.connectionPoolEnabled) {
         ManagedConnection mconnFromPool = this.connectionPool.getManagedConnection(factory, ec);
         if (mconnFromPool != null) {
            if (NucleusLogger.CONNECTION.isDebugEnabled()) {
               NucleusLogger.CONNECTION.debug(Localiser.msg("009004", mconnFromPool, ec, factory));
            }

            if (!mconnFromPool.closeAfterTransactionEnd()) {
               if (transaction.isActive()) {
                  if (mconnFromPool.commitOnRelease()) {
                     mconnFromPool.setCommitOnRelease(false);
                  }

                  if (mconnFromPool.closeOnRelease()) {
                     mconnFromPool.setCloseOnRelease(false);
                  }

                  XAResource res = mconnFromPool.getXAResource();
                  org.datanucleus.transaction.Transaction tx = this.nucleusContext.getTransactionManager().getTransaction(ec);
                  if (res != null && tx != null && !tx.isEnlisted(res)) {
                     String cfResourceType = factory.getResourceType();
                     if (!ConnectionResourceType.JTA.toString().equalsIgnoreCase(cfResourceType)) {
                        tx.enlistResource(res);
                     }
                  }
               } else {
                  if (!mconnFromPool.commitOnRelease()) {
                     mconnFromPool.setCommitOnRelease(true);
                  }

                  if (mconnFromPool.closeOnRelease()) {
                     mconnFromPool.setCloseOnRelease(false);
                  }
               }
            }

            return mconnFromPool;
         }
      }

      final ManagedConnection mconn = factory.createManagedConnection(ec, this.mergeOptions(transaction, options));
      if (ec != null) {
         if (transaction.isActive()) {
            this.configureTransactionEventListener(transaction, mconn);
            org.datanucleus.transaction.Transaction tx = this.nucleusContext.getTransactionManager().getTransaction(ec);
            mconn.setCommitOnRelease(false);
            mconn.setCloseOnRelease(false);
            XAResource res = mconn.getXAResource();
            if (res != null && tx != null && !tx.isEnlisted(res)) {
               String cfResourceType = factory.getResourceType();
               if (!ConnectionResourceType.JTA.toString().equalsIgnoreCase(cfResourceType)) {
                  tx.enlistResource(res);
               }
            }
         }

         if (this.connectionPoolEnabled) {
            mconn.addListener(new ManagedConnectionResourceListener() {
               public void transactionFlushed() {
               }

               public void transactionPreClose() {
               }

               public void managedConnectionPreClose() {
               }

               public void managedConnectionPostClose() {
                  if (NucleusLogger.CONNECTION.isDebugEnabled()) {
                     NucleusLogger.CONNECTION.debug(Localiser.msg("009006", mconn, ec, factory));
                  }

                  ConnectionManagerImpl.this.connectionPool.removeManagedConnection(factory, ec);
                  mconn.removeListener(this);
               }

               public void resourcePostClose() {
               }
            });
            if (NucleusLogger.CONNECTION.isDebugEnabled()) {
               NucleusLogger.CONNECTION.debug(Localiser.msg("009007", mconn, ec, factory));
            }

            this.connectionPool.putManagedConnection(factory, ec, mconn);
         }
      }

      return mconn;
   }

   private Map mergeOptions(Transaction transaction, Map overridingOptions) {
      Map m = new HashMap();
      if (transaction != null && transaction.getOptions() != null && !transaction.getOptions().isEmpty()) {
         m.putAll(transaction.getOptions());
      }

      if (overridingOptions != null && !overridingOptions.isEmpty()) {
         m.putAll(overridingOptions);
      }

      return m;
   }

   private void configureTransactionEventListener(final Transaction transaction, final ManagedConnection mconn) {
      if (mconn.closeAfterTransactionEnd()) {
         transaction.addTransactionEventListener(new TransactionEventListener() {
            public void transactionStarted() {
            }

            public void transactionRolledBack() {
               try {
                  mconn.close();
               } finally {
                  transaction.removeTransactionEventListener(this);
               }

            }

            public void transactionCommitted() {
               try {
                  mconn.close();
               } finally {
                  transaction.removeTransactionEventListener(this);
               }

            }

            public void transactionEnded() {
               try {
                  mconn.close();
               } finally {
                  transaction.removeTransactionEventListener(this);
               }

            }

            public void transactionPreCommit() {
               if (mconn.isLocked()) {
                  throw new NucleusUserException(Localiser.msg("009000"));
               } else {
                  mconn.transactionPreClose();
               }
            }

            public void transactionPreRollBack() {
               if (mconn.isLocked()) {
                  throw new NucleusUserException(Localiser.msg("009000"));
               } else {
                  mconn.transactionPreClose();
               }
            }

            public void transactionPreFlush() {
            }

            public void transactionFlushed() {
               mconn.transactionFlushed();
            }

            public void transactionSetSavepoint(String name) {
               mconn.setSavepoint(name);
            }

            public void transactionReleaseSavepoint(String name) {
               mconn.releaseSavepoint(name);
            }

            public void transactionRollbackToSavepoint(String name) {
               mconn.rollbackToSavepoint(name);
            }
         });
      } else {
         transaction.bindTransactionEventListener(new TransactionEventListener() {
            public void transactionStarted() {
            }

            public void transactionPreFlush() {
            }

            public void transactionFlushed() {
               mconn.transactionFlushed();
            }

            public void transactionPreCommit() {
               if (mconn.isLocked()) {
                  throw new NucleusUserException(Localiser.msg("009000"));
               } else {
                  mconn.transactionPreClose();
               }
            }

            public void transactionCommitted() {
            }

            public void transactionPreRollBack() {
               if (mconn.isLocked()) {
                  throw new NucleusUserException(Localiser.msg("009000"));
               } else {
                  mconn.transactionPreClose();
               }
            }

            public void transactionRolledBack() {
            }

            public void transactionEnded() {
            }

            public void transactionSetSavepoint(String name) {
               mconn.setSavepoint(name);
            }

            public void transactionReleaseSavepoint(String name) {
               mconn.releaseSavepoint(name);
            }

            public void transactionRollbackToSavepoint(String name) {
               mconn.rollbackToSavepoint(name);
            }
         });
      }

   }

   public ConnectionFactory lookupConnectionFactory(String name) {
      return (ConnectionFactory)this.factories.get(name);
   }

   public void registerConnectionFactory(String name, ConnectionFactory factory) {
      this.factories.put(name, factory);
   }

   public void disableConnectionPool() {
      this.connectionPoolEnabled = false;
   }

   class ManagedConnectionPool {
      Map connectionsPool = new HashMap();

      public void removeManagedConnection(ConnectionFactory factory, ExecutionContext ec) {
         synchronized(this.connectionsPool) {
            Object poolKey = this.getPoolKey(factory, ec);
            Map connectionsForPool = (Map)this.connectionsPool.get(poolKey);
            if (connectionsForPool != null) {
               if (connectionsForPool.remove(factory) != null && ConnectionManagerImpl.this.nucleusContext.getStatistics() != null) {
                  ConnectionManagerImpl.this.nucleusContext.getStatistics().decrementActiveConnections();
               }

               if (connectionsForPool.size() == 0) {
                  this.connectionsPool.remove(poolKey);
               }
            }

         }
      }

      public ManagedConnection getManagedConnection(ConnectionFactory factory, ExecutionContext ec) {
         synchronized(this.connectionsPool) {
            Object poolKey = this.getPoolKey(factory, ec);
            Map<ConnectionFactory, ManagedConnection> connectionsForEC = (Map)this.connectionsPool.get(poolKey);
            if (connectionsForEC == null) {
               return null;
            } else {
               ManagedConnection mconn = (ManagedConnection)connectionsForEC.get(factory);
               if (mconn != null) {
                  if (mconn.isLocked()) {
                     throw new NucleusUserException(Localiser.msg("009000"));
                  } else {
                     return mconn;
                  }
               } else {
                  return null;
               }
            }
         }
      }

      public void putManagedConnection(ConnectionFactory factory, ExecutionContext ec, ManagedConnection mconn) {
         synchronized(this.connectionsPool) {
            Object poolKey = this.getPoolKey(factory, ec);
            Map connectionsForOM = (Map)this.connectionsPool.get(poolKey);
            if (connectionsForOM == null) {
               connectionsForOM = new HashMap();
               this.connectionsPool.put(poolKey, connectionsForOM);
            }

            if (connectionsForOM.put(factory, mconn) == null && ConnectionManagerImpl.this.nucleusContext.getStatistics() != null) {
               ConnectionManagerImpl.this.nucleusContext.getStatistics().incrementActiveConnections();
            }

         }
      }

      Object getPoolKey(ConnectionFactory factory, ExecutionContext ec) {
         return ec.getStoreManager() instanceof FederatedStoreManager ? ConnectionManagerImpl.this.new PoolKey(factory, ec) : ec;
      }
   }

   class PoolKey {
      ConnectionFactory factory;
      ExecutionContext ec;

      public PoolKey(ConnectionFactory factory, ExecutionContext ec) {
         this.factory = factory;
         this.ec = ec;
      }

      public boolean equals(Object obj) {
         if (obj != null && obj instanceof PoolKey) {
            PoolKey other = (PoolKey)obj;
            return this.factory == other.factory && this.ec == other.ec;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.factory.hashCode() ^ this.ec.hashCode();
      }
   }
}
