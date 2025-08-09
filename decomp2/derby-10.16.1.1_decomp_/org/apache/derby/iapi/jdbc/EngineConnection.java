package org.apache.derby.iapi.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.concurrent.Executor;

public interface EngineConnection extends Connection {
   void setDrdaID(String var1);

   boolean isInGlobalTransaction();

   void setPrepareIsolation(int var1) throws SQLException;

   int getPrepareIsolation() throws SQLException;

   void addWarning(SQLWarning var1) throws SQLException;

   Object getLOBMapping(int var1) throws SQLException;

   String getCurrentSchemaName() throws SQLException;

   void resetFromPool() throws SQLException;

   String getSchema() throws SQLException;

   void setSchema(String var1) throws SQLException;

   void abort(Executor var1) throws SQLException;

   void setNetworkTimeout(Executor var1, int var2) throws SQLException;

   int getNetworkTimeout() throws SQLException;
}
