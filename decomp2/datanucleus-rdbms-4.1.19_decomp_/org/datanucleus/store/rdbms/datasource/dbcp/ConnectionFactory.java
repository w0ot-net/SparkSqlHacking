package org.datanucleus.store.rdbms.datasource.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
   Connection createConnection() throws SQLException;
}
