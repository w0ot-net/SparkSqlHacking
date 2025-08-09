package org.apache.derby.iapi.db;

import java.sql.SQLException;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;

public class Factory {
   public static org.apache.derby.database.Database getDatabaseOfConnection() throws SQLException {
      LanguageConnectionContext var0 = ConnectionUtil.getCurrentLCC();
      return var0.getDatabase();
   }

   public static TriggerExecutionContext getTriggerExecutionContext() throws SQLException {
      LanguageConnectionContext var0 = ConnectionUtil.getCurrentLCC();
      return var0.getTriggerExecutionContext();
   }
}
