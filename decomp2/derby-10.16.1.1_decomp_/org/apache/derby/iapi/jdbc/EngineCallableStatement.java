package org.apache.derby.iapi.jdbc;

import java.sql.CallableStatement;
import java.sql.SQLException;

public interface EngineCallableStatement extends EngineStatement, CallableStatement {
   Object getObject(int var1, Class var2) throws SQLException;

   Object getObject(String var1, Class var2) throws SQLException;
}
