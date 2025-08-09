package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import org.apache.derby.iapi.tools.i18n.LocalizedResource;

abstract class ijResultImpl implements ijResult {
   public boolean isConnection() {
      return false;
   }

   public boolean isStatement() {
      return false;
   }

   public boolean isResultSet() throws SQLException {
      return false;
   }

   public boolean isUpdateCount() throws SQLException {
      return false;
   }

   public boolean isNextRowOfResultSet() {
      return false;
   }

   public boolean isVector() {
      return false;
   }

   public boolean isMulti() {
      return false;
   }

   public boolean isException() {
      return false;
   }

   public boolean isMultipleResultSetResult() {
      return false;
   }

   public boolean hasWarnings() throws SQLException {
      return this.getSQLWarnings() != null;
   }

   public Connection getConnection() {
      return null;
   }

   public Statement getStatement() {
      return null;
   }

   public int getUpdateCount() throws SQLException {
      return -1;
   }

   public ResultSet getResultSet() throws SQLException {
      return null;
   }

   public List getMultipleResultSets() {
      return null;
   }

   public ResultSet getNextRowOfResultSet() {
      return null;
   }

   public Vector getVector() {
      return null;
   }

   public SQLException getException() {
      return null;
   }

   public int[] getColumnDisplayList() {
      return null;
   }

   public int[] getColumnWidthList() {
      return null;
   }

   public void closeStatement() throws SQLException {
   }

   public abstract SQLWarning getSQLWarnings() throws SQLException;

   public abstract void clearSQLWarnings() throws SQLException;

   public String toString() {
      if (this.isConnection()) {
         return LocalizedResource.getMessage("IJ_Con0", this.getConnection().toString());
      } else if (this.isStatement()) {
         return LocalizedResource.getMessage("IJ_Stm0", this.getStatement().toString());
      } else if (this.isNextRowOfResultSet()) {
         return LocalizedResource.getMessage("IJ_Row0", this.getNextRowOfResultSet().toString());
      } else if (this.isVector()) {
         return LocalizedResource.getMessage("IJ_Vec0", this.getVector().toString());
      } else if (this.isMulti()) {
         return LocalizedResource.getMessage("IJ_Mul0", this.getVector().toString());
      } else if (this.isException()) {
         return LocalizedResource.getMessage("IJ_Exc0", this.getException().toString());
      } else if (this.isMultipleResultSetResult()) {
         return LocalizedResource.getMessage("IJ_MRS0", this.getMultipleResultSets().toString());
      } else {
         try {
            if (this.isResultSet()) {
               return LocalizedResource.getMessage("IJ_Rse0", this.getStatement().toString());
            }
         } catch (SQLException var2) {
         }

         return LocalizedResource.getMessage("IJ_Unkn0", this.getClass().getName());
      }
   }
}
