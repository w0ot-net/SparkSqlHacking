package org.apache.commons.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
   Connection createConnection() throws SQLException;
}
