package org.apache.commons.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.DelegatingConnection;
import org.apache.commons.pool.ObjectPool;

public class ManagedConnection extends DelegatingConnection {
   private final ObjectPool pool;
   private final TransactionRegistry transactionRegistry;
   private final boolean accessToUnderlyingConnectionAllowed;
   private TransactionContext transactionContext;
   private boolean isSharedConnection;

   public ManagedConnection(ObjectPool pool, TransactionRegistry transactionRegistry, boolean accessToUnderlyingConnectionAllowed) throws SQLException {
      super((Connection)null);
      this.pool = pool;
      this.transactionRegistry = transactionRegistry;
      this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
      this.updateTransactionStatus();
   }

   protected void checkOpen() throws SQLException {
      super.checkOpen();
      this.updateTransactionStatus();
   }

   private void updateTransactionStatus() throws SQLException {
      if (this.transactionContext != null) {
         if (this.transactionContext.isActive()) {
            if (this.transactionContext != this.transactionRegistry.getActiveTransactionContext()) {
               throw new SQLException("Connection can not be used while enlisted in another transaction");
            }

            return;
         }

         this.transactionComplete();
      }

      this.transactionContext = this.transactionRegistry.getActiveTransactionContext();
      if (this.transactionContext != null && this.transactionContext.getSharedConnection() != null) {
         Connection connection = this.getDelegateInternal();
         this.setDelegate((Connection)null);
         if (connection != null) {
            try {
               this.pool.returnObject(connection);
            } catch (Exception var7) {
               try {
                  this.pool.invalidateObject(connection);
               } catch (Exception var6) {
               }
            }
         }

         this.transactionContext.addTransactionContextListener(new CompletionListener());
         this.setDelegate(this.transactionContext.getSharedConnection());
         this.isSharedConnection = true;
      } else {
         if (this.getDelegateInternal() == null) {
            try {
               Connection connection = (Connection)this.pool.borrowObject();
               this.setDelegate(connection);
            } catch (Exception e) {
               throw (SQLException)(new SQLException("Unable to acquire a new connection from the pool")).initCause(e);
            }
         }

         if (this.transactionContext != null) {
            this.transactionContext.addTransactionContextListener(new CompletionListener());

            try {
               this.transactionContext.setSharedConnection(this.getDelegateInternal());
            } catch (SQLException e) {
               this.transactionContext = null;
               throw e;
            }
         }
      }

   }

   public void close() throws SQLException {
      if (!this._closed) {
         try {
            if (this.transactionContext == null) {
               this.getDelegateInternal().close();
            }
         } finally {
            this._closed = true;
         }
      }

   }

   protected void transactionComplete() {
      this.transactionContext = null;
      if (this.isSharedConnection) {
         this.setDelegate((Connection)null);
         this.isSharedConnection = false;
      }

      Connection delegate = this.getDelegateInternal();
      if (this._closed && delegate != null) {
         try {
            this.setDelegate((Connection)null);
            if (!delegate.isClosed()) {
               delegate.close();
            }
         } catch (SQLException var6) {
         } finally {
            this._closed = true;
         }
      }

   }

   public void setAutoCommit(boolean autoCommit) throws SQLException {
      if (this.transactionContext != null) {
         throw new SQLException("Auto-commit can not be set while enrolled in a transaction");
      } else {
         super.setAutoCommit(autoCommit);
      }
   }

   public void commit() throws SQLException {
      if (this.transactionContext != null) {
         throw new SQLException("Commit can not be set while enrolled in a transaction");
      } else {
         super.commit();
      }
   }

   public void rollback() throws SQLException {
      if (this.transactionContext != null) {
         throw new SQLException("Commit can not be set while enrolled in a transaction");
      } else {
         super.rollback();
      }
   }

   public void setReadOnly(boolean readOnly) throws SQLException {
      if (this.transactionContext != null) {
         throw new SQLException("Read-only can not be set while enrolled in a transaction");
      } else {
         super.setReadOnly(readOnly);
      }
   }

   public boolean isAccessToUnderlyingConnectionAllowed() {
      return this.accessToUnderlyingConnectionAllowed;
   }

   public Connection getDelegate() {
      return this.isAccessToUnderlyingConnectionAllowed() ? this.getDelegateInternal() : null;
   }

   public Connection getInnermostDelegate() {
      return this.isAccessToUnderlyingConnectionAllowed() ? super.getInnermostDelegateInternal() : null;
   }

   protected class CompletionListener implements TransactionContextListener {
      public void afterCompletion(TransactionContext completedContext, boolean commited) {
         if (completedContext == ManagedConnection.this.transactionContext) {
            ManagedConnection.this.transactionComplete();
         }

      }
   }
}
