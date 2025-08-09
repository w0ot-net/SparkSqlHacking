package org.apache.derby.catalog;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.derby.iapi.db.Factory;
import org.apache.derby.iapi.db.TriggerExecutionContext;
import org.apache.derby.vti.UpdatableVTITemplate;

public final class TriggerNewTransitionRows extends UpdatableVTITemplate {
   private ResultSet resultSet;

   public TriggerNewTransitionRows() throws SQLException {
      this.initializeResultSet();
   }

   private ResultSet initializeResultSet() throws SQLException {
      if (this.resultSet != null) {
         this.resultSet.close();
      }

      TriggerExecutionContext var1 = Factory.getTriggerExecutionContext();
      if (var1 == null) {
         throw new SQLException("There are no active triggers", "38000");
      } else {
         this.resultSet = var1.getNewRowSet();
         if (this.resultSet == null) {
            throw new SQLException("There is no new transition rows result set for this trigger", "38000");
         } else {
            return this.resultSet;
         }
      }
   }

   public ResultSetMetaData getMetaData() throws SQLException {
      return this.resultSet.getMetaData();
   }

   public ResultSet executeQuery() throws SQLException {
      return this.initializeResultSet();
   }

   public int getResultSetConcurrency() {
      return 1007;
   }

   public void close() throws SQLException {
      this.resultSet.close();
   }
}
