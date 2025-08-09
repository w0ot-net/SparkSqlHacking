package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.datasource.dbcp.AbandonedConfig;
import org.datanucleus.store.rdbms.datasource.dbcp.PoolableConnection;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPool;

public class PoolableManagedConnection extends PoolableConnection {
   private final TransactionRegistry transactionRegistry;

   public PoolableManagedConnection(TransactionRegistry transactionRegistry, Connection conn, ObjectPool pool, AbandonedConfig config) {
      super(conn, pool, config);
      this.transactionRegistry = transactionRegistry;
   }

   public PoolableManagedConnection(TransactionRegistry transactionRegistry, Connection conn, ObjectPool pool) {
      super(conn, pool);
      this.transactionRegistry = transactionRegistry;
   }

   public void reallyClose() throws SQLException {
      try {
         super.reallyClose();
      } finally {
         this.transactionRegistry.unregisterConnection(this);
      }

   }
}
