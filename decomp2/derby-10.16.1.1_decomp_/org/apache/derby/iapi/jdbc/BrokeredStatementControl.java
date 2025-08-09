package org.apache.derby.iapi.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BrokeredStatementControl {
   int checkHoldCursors(int var1) throws SQLException;

   void closeRealStatement() throws SQLException;

   void closeRealCallableStatement() throws SQLException;

   void closeRealPreparedStatement() throws SQLException;

   Statement getRealStatement() throws SQLException;

   PreparedStatement getRealPreparedStatement() throws SQLException;

   CallableStatement getRealCallableStatement() throws SQLException;

   ResultSet wrapResultSet(Statement var1, ResultSet var2);
}
