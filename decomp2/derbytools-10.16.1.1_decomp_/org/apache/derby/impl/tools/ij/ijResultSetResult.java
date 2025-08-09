package org.apache.derby.impl.tools.ij;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.apache.derby.iapi.tools.ToolUtils;

public class ijResultSetResult extends ijResultImpl {
   private ResultSet resultSet;
   private Statement statement;
   private int[] displayColumns = null;
   private int[] columnWidths = null;

   public ijResultSetResult(ResultSet var1) throws SQLException {
      this.resultSet = var1;
      this.statement = this.resultSet.getStatement();
   }

   public ijResultSetResult(ResultSet var1, int[] var2, int[] var3) throws SQLException {
      this.resultSet = var1;
      this.statement = this.resultSet.getStatement();
      this.displayColumns = ToolUtils.copy(var2);
      this.columnWidths = ToolUtils.copy(var3);
   }

   public boolean isResultSet() throws SQLException {
      return this.statement == null || this.statement.getUpdateCount() == -1;
   }

   public ResultSet getResultSet() throws SQLException {
      return this.resultSet;
   }

   public void closeStatement() throws SQLException {
      if (this.statement != null) {
         this.statement.close();
      } else {
         this.resultSet.close();
      }

   }

   public int[] getColumnDisplayList() {
      return ToolUtils.copy(this.displayColumns);
   }

   public int[] getColumnWidthList() {
      return ToolUtils.copy(this.columnWidths);
   }

   public SQLWarning getSQLWarnings() throws SQLException {
      return this.resultSet.getWarnings();
   }

   public void clearSQLWarnings() throws SQLException {
      this.resultSet.clearWarnings();
   }
}
