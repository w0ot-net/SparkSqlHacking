package org.apache.derby.iapi.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public interface BrokeredConnectionControl {
   EngineConnection getRealConnection() throws SQLException;

   void notifyException(SQLException var1);

   void checkAutoCommit(boolean var1) throws SQLException;

   void checkSavepoint() throws SQLException;

   void checkRollback() throws SQLException;

   void checkCommit() throws SQLException;

   void checkClose() throws SQLException;

   int checkHoldCursors(int var1, boolean var2) throws SQLException;

   boolean isIsolationLevelSetUsingSQLorJDBC() throws SQLException;

   void resetIsolationLevelFlag() throws SQLException;

   boolean isInGlobalTransaction();

   boolean closingConnection() throws SQLException;

   Statement wrapStatement(Statement var1) throws SQLException;

   PreparedStatement wrapStatement(PreparedStatement var1, String var2, Object var3) throws SQLException;

   CallableStatement wrapStatement(CallableStatement var1, String var2) throws SQLException;

   void onStatementClose(PreparedStatement var1);

   void onStatementErrorOccurred(PreparedStatement var1, SQLException var2);
}
