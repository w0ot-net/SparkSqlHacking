package org.apache.derby.iapi.sql.dictionary;

import java.sql.SQLException;

public interface OptionalTool {
   void loadTool(String... var1) throws SQLException;

   void unloadTool(String... var1) throws SQLException;
}
