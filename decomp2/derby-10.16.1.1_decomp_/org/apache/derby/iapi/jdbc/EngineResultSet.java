package org.apache.derby.iapi.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EngineResultSet extends ResultSet {
   boolean isForUpdate();

   boolean isNull(int var1) throws SQLException;

   int getLength(int var1) throws SQLException;
}
