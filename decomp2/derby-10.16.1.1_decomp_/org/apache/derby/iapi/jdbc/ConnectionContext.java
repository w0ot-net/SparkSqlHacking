package org.apache.derby.iapi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ConnectionContext {
   String CONTEXT_ID = "JDBC_ConnectionContext";

   Connection getNestedConnection(boolean var1) throws SQLException;

   ResultSet getResultSet(org.apache.derby.iapi.sql.ResultSet var1) throws SQLException;

   boolean processInaccessibleDynamicResult(ResultSet var1);
}
