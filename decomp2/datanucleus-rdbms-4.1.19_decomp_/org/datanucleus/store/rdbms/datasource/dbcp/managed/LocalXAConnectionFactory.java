package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import org.datanucleus.store.rdbms.datasource.dbcp.ConnectionFactory;

public class LocalXAConnectionFactory implements XAConnectionFactory {
   protected TransactionRegistry transactionRegistry;
   protected ConnectionFactory connectionFactory;

   public LocalXAConnectionFactory(TransactionManager transactionManager, ConnectionFactory connectionFactory) {
      if (transactionManager == null) {
         throw new NullPointerException("transactionManager is null");
      } else if (connectionFactory == null) {
         throw new NullPointerException("connectionFactory is null");
      } else {
         this.transactionRegistry = new TransactionRegistry(transactionManager);
         this.connectionFactory = connectionFactory;
      }
   }

   public TransactionRegistry getTransactionRegistry() {
      return this.transactionRegistry;
   }

   public Connection createConnection() throws SQLException {
      Connection connection = this.connectionFactory.createConnection();
      XAResource xaResource = new LocalXAResource(connection);
      this.transactionRegistry.registerConnection(connection, xaResource);
      return connection;
   }

   protected static class LocalXAResource implements XAResource {
      private final Connection connection;
      private Xid currentXid;
      private boolean originalAutoCommit;

      public LocalXAResource(Connection localTransaction) {
         this.connection = localTransaction;
      }

      public synchronized Xid getXid() {
         return this.currentXid;
      }

      public synchronized void start(Xid xid, int flag) throws XAException {
         if (flag == 0) {
            if (this.currentXid != null) {
               throw new XAException("Already enlisted in another transaction with xid " + xid);
            }

            try {
               this.originalAutoCommit = this.connection.getAutoCommit();
            } catch (SQLException var5) {
               this.originalAutoCommit = true;
            }

            try {
               this.connection.setAutoCommit(false);
            } catch (SQLException e) {
               throw (XAException)(new XAException("Count not turn off auto commit for a XA transaction")).initCause(e);
            }

            this.currentXid = xid;
         } else {
            if (flag != 134217728) {
               throw new XAException("Unknown start flag " + flag);
            }

            if (xid != this.currentXid) {
               throw new XAException("Attempting to resume in different transaction: expected " + this.currentXid + ", but was " + xid);
            }
         }

      }

      public synchronized void end(Xid xid, int flag) throws XAException {
         if (xid == null) {
            throw new NullPointerException("xid is null");
         } else if (!this.currentXid.equals(xid)) {
            throw new XAException("Invalid Xid: expected " + this.currentXid + ", but was " + xid);
         }
      }

      public synchronized int prepare(Xid xid) {
         try {
            if (this.connection.isReadOnly()) {
               this.connection.setAutoCommit(this.originalAutoCommit);
               return 3;
            }
         } catch (SQLException var3) {
         }

         return 0;
      }

      public synchronized void commit(Xid xid, boolean flag) throws XAException {
         if (xid == null) {
            throw new NullPointerException("xid is null");
         } else if (!this.currentXid.equals(xid)) {
            throw new XAException("Invalid Xid: expected " + this.currentXid + ", but was " + xid);
         } else {
            try {
               if (this.connection.isClosed()) {
                  throw new XAException("Conection is closed");
               }

               if (!this.connection.isReadOnly()) {
                  this.connection.commit();
               }
            } catch (SQLException e) {
               throw (XAException)(new XAException()).initCause(e);
            } finally {
               try {
                  this.connection.setAutoCommit(this.originalAutoCommit);
               } catch (SQLException var10) {
               }

               this.currentXid = null;
            }

         }
      }

      public synchronized void rollback(Xid xid) throws XAException {
         if (xid == null) {
            throw new NullPointerException("xid is null");
         } else if (!this.currentXid.equals(xid)) {
            throw new XAException("Invalid Xid: expected " + this.currentXid + ", but was " + xid);
         } else {
            try {
               this.connection.rollback();
            } catch (SQLException e) {
               throw (XAException)(new XAException()).initCause(e);
            } finally {
               try {
                  this.connection.setAutoCommit(this.originalAutoCommit);
               } catch (SQLException var9) {
               }

               this.currentXid = null;
            }

         }
      }

      public boolean isSameRM(XAResource xaResource) {
         return this == xaResource;
      }

      public synchronized void forget(Xid xid) {
         if (xid != null && this.currentXid.equals(xid)) {
            this.currentXid = null;
         }

      }

      public Xid[] recover(int flag) {
         return new Xid[0];
      }

      public int getTransactionTimeout() {
         return 0;
      }

      public boolean setTransactionTimeout(int transactionTimeout) {
         return false;
      }
   }
}
