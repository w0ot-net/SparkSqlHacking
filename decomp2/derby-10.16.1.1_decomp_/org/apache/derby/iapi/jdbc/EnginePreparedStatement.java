package org.apache.derby.iapi.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface EnginePreparedStatement extends PreparedStatement, EngineStatement {
   long getVersionCounter() throws SQLException;

   long executeLargeUpdate() throws SQLException;
}
