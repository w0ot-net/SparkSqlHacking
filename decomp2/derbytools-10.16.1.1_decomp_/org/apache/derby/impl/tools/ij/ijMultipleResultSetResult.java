package org.apache.derby.impl.tools.ij;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import org.apache.derby.iapi.tools.ToolUtils;

public class ijMultipleResultSetResult extends ijResultImpl {
   private ArrayList resultSets = null;
   private int[] displayColumns = null;
   private int[] columnWidths = null;

   public ijMultipleResultSetResult(List var1, int[] var2, int[] var3) throws SQLException {
      this.resultSets = new ArrayList();
      this.resultSets.addAll(var1);
      this.displayColumns = ToolUtils.copy(var2);
      this.columnWidths = ToolUtils.copy(var3);
   }

   public void addResultSet(ResultSet var1) {
      this.resultSets.add(var1);
   }

   public boolean isMultipleResultSetResult() {
      return true;
   }

   public List getMultipleResultSets() {
      return new ArrayList(this.resultSets);
   }

   public void closeStatement() throws SQLException {
      if (this.resultSets != null) {
         Object var1 = null;

         for(int var2 = 0; var2 < this.resultSets.size(); ++var2) {
            ResultSet var3 = (ResultSet)this.resultSets.get(var2);
            if (var3.getStatement() != null) {
               var3.getStatement().close();
            } else {
               var3.close();
            }
         }
      }

   }

   public int[] getColumnDisplayList() {
      return ToolUtils.copy(this.displayColumns);
   }

   public int[] getColumnWidthList() {
      return ToolUtils.copy(this.columnWidths);
   }

   public SQLWarning getSQLWarnings() throws SQLException {
      SQLWarning var1 = null;
      Object var2 = null;

      for(int var3 = 0; var3 < this.resultSets.size(); ++var3) {
         ResultSet var4 = (ResultSet)this.resultSets.get(var3);
         if (var4.getWarnings() != null) {
            if (var1 == null) {
               var1 = var4.getWarnings();
            } else {
               var1.setNextWarning(var4.getWarnings());
            }
         }
      }

      return var1;
   }

   public void clearSQLWarnings() throws SQLException {
      for(int var1 = 0; var1 < this.resultSets.size(); ++var1) {
         ((ResultSet)this.resultSets.get(var1)).clearWarnings();
      }

   }
}
