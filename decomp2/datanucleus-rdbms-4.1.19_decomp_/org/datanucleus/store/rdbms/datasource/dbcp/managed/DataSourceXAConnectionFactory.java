package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;
import javax.transaction.xa.XAResource;

public class DataSourceXAConnectionFactory implements XAConnectionFactory {
   protected TransactionRegistry transactionRegistry;
   protected XADataSource xaDataSource;
   protected String username;
   protected String password;

   public DataSourceXAConnectionFactory(TransactionManager transactionManager, XADataSource xaDataSource) {
      this(transactionManager, xaDataSource, (String)null, (String)null);
   }

   public DataSourceXAConnectionFactory(TransactionManager transactionManager, XADataSource xaDataSource, String username, String password) {
      if (transactionManager == null) {
         throw new NullPointerException("transactionManager is null");
      } else if (xaDataSource == null) {
         throw new NullPointerException("xaDataSource is null");
      } else {
         this.transactionRegistry = new TransactionRegistry(transactionManager);
         this.xaDataSource = xaDataSource;
         this.username = username;
         this.password = password;
      }
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public TransactionRegistry getTransactionRegistry() {
      return this.transactionRegistry;
   }

   public Connection createConnection() throws SQLException {
      XAConnection xaConnection;
      if (this.username == null) {
         xaConnection = this.xaDataSource.getXAConnection();
      } else {
         xaConnection = this.xaDataSource.getXAConnection(this.username, this.password);
      }

      Connection connection = xaConnection.getConnection();
      XAResource xaResource = xaConnection.getXAResource();
      this.transactionRegistry.registerConnection(connection, xaResource);
      return connection;
   }
}
