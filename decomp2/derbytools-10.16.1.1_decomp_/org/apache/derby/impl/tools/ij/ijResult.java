package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

interface ijResult {
   boolean isConnection();

   boolean isStatement();

   boolean isResultSet() throws SQLException;

   boolean isUpdateCount() throws SQLException;

   boolean isNextRowOfResultSet();

   boolean isVector();

   boolean isMulti();

   boolean isException();

   boolean isMultipleResultSetResult();

   boolean hasWarnings() throws SQLException;

   Connection getConnection();

   Statement getStatement();

   int getUpdateCount() throws SQLException;

   ResultSet getResultSet() throws SQLException;

   List getMultipleResultSets();

   ResultSet getNextRowOfResultSet();

   Vector getVector();

   SQLException getException();

   int[] getColumnDisplayList();

   int[] getColumnWidthList();

   void closeStatement() throws SQLException;

   SQLWarning getSQLWarnings() throws SQLException;

   void clearSQLWarnings() throws SQLException;
}
