package org.apache.derby.iapi.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

public interface EngineStatement extends Statement {
   void closeOnCompletion() throws SQLException;

   boolean isCloseOnCompletion() throws SQLException;

   long[] executeLargeBatch() throws SQLException;

   long executeLargeUpdate(String var1) throws SQLException;

   long executeLargeUpdate(String var1, int var2) throws SQLException;

   long executeLargeUpdate(String var1, int[] var2) throws SQLException;

   long executeLargeUpdate(String var1, String[] var2) throws SQLException;

   long getLargeMaxRows() throws SQLException;

   long getLargeUpdateCount() throws SQLException;

   void setLargeMaxRows(long var1) throws SQLException;
}
