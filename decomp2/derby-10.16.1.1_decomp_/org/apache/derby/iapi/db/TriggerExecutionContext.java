package org.apache.derby.iapi.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.catalog.UUID;

public interface TriggerExecutionContext {
   int UPDATE_EVENT = 1;
   int DELETE_EVENT = 2;
   int INSERT_EVENT = 3;

   String getTargetTableName();

   UUID getTargetTableId();

   int getEventType();

   String getEventStatementText();

   ResultSet getOldRowSet() throws SQLException;

   ResultSet getNewRowSet() throws SQLException;

   ResultSet getOldRow() throws SQLException;

   ResultSet getNewRow() throws SQLException;

   Long getAutoincrementValue(String var1);
}
