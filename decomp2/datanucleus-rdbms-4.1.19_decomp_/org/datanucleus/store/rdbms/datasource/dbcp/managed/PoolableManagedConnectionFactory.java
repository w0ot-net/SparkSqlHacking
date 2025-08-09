package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.util.Collection;
import org.datanucleus.store.rdbms.datasource.dbcp.AbandonedConfig;
import org.datanucleus.store.rdbms.datasource.dbcp.PoolableConnectionFactory;
import org.datanucleus.store.rdbms.datasource.dbcp.PoolingConnection;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPool;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.KeyedObjectPoolFactory;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPool;

public class PoolableManagedConnectionFactory extends PoolableConnectionFactory {
   private final TransactionRegistry transactionRegistry;

   public PoolableManagedConnectionFactory(XAConnectionFactory connFactory, ObjectPool pool, KeyedObjectPoolFactory stmtPoolFactory, String validationQuery, boolean defaultReadOnly, boolean defaultAutoCommit) {
      super(connFactory, pool, stmtPoolFactory, validationQuery, defaultReadOnly, defaultAutoCommit);
      this.transactionRegistry = connFactory.getTransactionRegistry();
   }

   public PoolableManagedConnectionFactory(XAConnectionFactory connFactory, ObjectPool pool, KeyedObjectPoolFactory stmtPoolFactory, String validationQuery, int validationQueryTimeout, Collection connectionInitSqls, Boolean defaultReadOnly, boolean defaultAutoCommit, int defaultTransactionIsolation, String defaultCatalog, AbandonedConfig config) {
      super(connFactory, pool, stmtPoolFactory, validationQuery, validationQueryTimeout, connectionInitSqls, defaultReadOnly, defaultAutoCommit, defaultTransactionIsolation, defaultCatalog, config);
      this.transactionRegistry = connFactory.getTransactionRegistry();
   }

   public synchronized Object makeObject() throws Exception {
      Connection conn = this._connFactory.createConnection();
      if (conn == null) {
         throw new IllegalStateException("Connection factory returned null from createConnection");
      } else {
         this.initializeConnection(conn);
         if (null != this._stmtPoolFactory) {
            KeyedObjectPool stmtpool = this._stmtPoolFactory.createPool();
            conn = new PoolingConnection(conn, stmtpool);
            stmtpool.setFactory((PoolingConnection)conn);
         }

         return new PoolableManagedConnection(this.transactionRegistry, conn, this._pool, this._config);
      }
   }
}
