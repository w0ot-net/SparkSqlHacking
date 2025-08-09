package org.datanucleus.store.rdbms.datasource.dbcp.managed;

import java.sql.Connection;
import java.sql.SQLException;
import org.datanucleus.store.rdbms.datasource.dbcp.ConnectionFactory;

public interface XAConnectionFactory extends ConnectionFactory {
   TransactionRegistry getTransactionRegistry();

   Connection createConnection() throws SQLException;
}
