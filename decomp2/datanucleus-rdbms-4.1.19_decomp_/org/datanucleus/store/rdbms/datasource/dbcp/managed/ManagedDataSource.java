package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.datasource.dbcp.PoolingDataSource;
import org.datanucleus.store.rdbms.datasource.dbcp.pool.ObjectPool;

public class ManagedDataSource extends PoolingDataSource {
   private TransactionRegistry transactionRegistry;

   public ManagedDataSource() {
   }

   public ManagedDataSource(ObjectPool pool, TransactionRegistry transactionRegistry) {
      super(pool);
      this.transactionRegistry = transactionRegistry;
   }

   public void setTransactionRegistry(TransactionRegistry transactionRegistry) {
      if (this.transactionRegistry != null) {
         throw new IllegalStateException("TransactionRegistry already set");
      } else if (transactionRegistry == null) {
         throw new NullPointerException("TransactionRegistry is null");
      } else {
         this.transactionRegistry = transactionRegistry;
      }
   }

   public Connection getConnection() throws SQLException {
      if (this._pool == null) {
         throw new IllegalStateException("Pool has not been set");
      } else if (this.transactionRegistry == null) {
         throw new IllegalStateException("TransactionRegistry has not been set");
      } else {
         Connection connection = new ManagedConnection(this._pool, this.transactionRegistry, this.isAccessToUnderlyingConnectionAllowed());
         return connection;
      }
   }
}
