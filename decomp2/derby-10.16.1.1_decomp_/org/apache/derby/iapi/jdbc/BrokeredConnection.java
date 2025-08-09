package org.apache.derby.iapi.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import org.apache.derby.shared.common.error.ExceptionFactory;
import org.apache.derby.shared.common.error.SQLWarningFactory;

public class BrokeredConnection implements EngineConnection {
   int stateHoldability = 1;
   final BrokeredConnectionControl control;
   protected boolean isClosed;
   private String connString;
   private int stateIsolationLevel;
   private boolean stateReadOnly;
   private boolean stateAutoCommit;

   public BrokeredConnection(BrokeredConnectionControl var1) throws SQLException {
      this.control = var1;
   }

   public final void setAutoCommit(boolean var1) throws SQLException {
      try {
         this.control.checkAutoCommit(var1);
         this.getRealConnection().setAutoCommit(var1);
         this.stateAutoCommit = var1;
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final boolean getAutoCommit() throws SQLException {
      try {
         return this.getRealConnection().getAutoCommit();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Statement createStatement() throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().createStatement());
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final PreparedStatement prepareStatement(String var1) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1), var1, (Object)null);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final CallableStatement prepareCall(String var1) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareCall(var1), var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final String nativeSQL(String var1) throws SQLException {
      try {
         return this.getRealConnection().nativeSQL(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final void commit() throws SQLException {
      try {
         this.control.checkCommit();
         this.getRealConnection().commit();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void rollback() throws SQLException {
      try {
         this.control.checkRollback();
         this.getRealConnection().rollback();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void close() throws SQLException {
      if (!this.isClosed) {
         try {
            this.control.checkClose();
            if (!this.control.closingConnection()) {
               this.isClosed = true;
            } else {
               this.isClosed = true;
               this.getRealConnection().close();
            }
         } catch (SQLException var2) {
            this.notifyException(var2);
            throw var2;
         }
      }
   }

   public final boolean isClosed() throws SQLException {
      if (this.isClosed) {
         return true;
      } else {
         try {
            boolean var1 = this.getRealConnection().isClosed();
            if (var1) {
               this.control.closingConnection();
               this.isClosed = true;
            }

            return var1;
         } catch (SQLException var2) {
            this.notifyException(var2);
            throw var2;
         }
      }
   }

   public final SQLWarning getWarnings() throws SQLException {
      try {
         return this.getRealConnection().getWarnings();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void clearWarnings() throws SQLException {
      try {
         this.getRealConnection().clearWarnings();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final DatabaseMetaData getMetaData() throws SQLException {
      try {
         return this.getRealConnection().getMetaData();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void setReadOnly(boolean var1) throws SQLException {
      try {
         this.getRealConnection().setReadOnly(var1);
         this.stateReadOnly = var1;
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final boolean isReadOnly() throws SQLException {
      try {
         return this.getRealConnection().isReadOnly();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void setCatalog(String var1) throws SQLException {
      try {
         this.getRealConnection().setCatalog(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final String getCatalog() throws SQLException {
      try {
         return this.getRealConnection().getCatalog();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void setTransactionIsolation(int var1) throws SQLException {
      try {
         this.getRealConnection().setTransactionIsolation(var1);
         this.stateIsolationLevel = var1;
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final int getTransactionIsolation() throws SQLException {
      try {
         return this.getRealConnection().getTransactionIsolation();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Statement createStatement(int var1, int var2) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().createStatement(var1, var2));
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final PreparedStatement prepareStatement(String var1, int var2, int var3) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1, var2, var3), var1, (Object)null);
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw var5;
      }
   }

   public final CallableStatement prepareCall(String var1, int var2, int var3) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareCall(var1, var2, var3), var1);
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw var5;
      }
   }

   public Map getTypeMap() throws SQLException {
      try {
         return this.getRealConnection().getTypeMap();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final void setTypeMap(Map var1) throws SQLException {
      try {
         this.getRealConnection().setTypeMap(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final Statement createStatement(int var1, int var2, int var3) throws SQLException {
      try {
         var3 = this.statementHoldabilityCheck(var3);
         return this.control.wrapStatement(this.getRealConnection().createStatement(var1, var2, var3));
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw var5;
      }
   }

   public final CallableStatement prepareCall(String var1, int var2, int var3, int var4) throws SQLException {
      try {
         var4 = this.statementHoldabilityCheck(var4);
         return this.control.wrapStatement(this.getRealConnection().prepareCall(var1, var2, var3, var4), var1);
      } catch (SQLException var6) {
         this.notifyException(var6);
         throw var6;
      }
   }

   public final Savepoint setSavepoint() throws SQLException {
      try {
         this.control.checkSavepoint();
         return this.getRealConnection().setSavepoint();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Savepoint setSavepoint(String var1) throws SQLException {
      try {
         this.control.checkSavepoint();
         return this.getRealConnection().setSavepoint(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final void rollback(Savepoint var1) throws SQLException {
      try {
         this.control.checkRollback();
         this.getRealConnection().rollback(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final void releaseSavepoint(Savepoint var1) throws SQLException {
      try {
         this.getRealConnection().releaseSavepoint(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final void setHoldability(int var1) throws SQLException {
      try {
         var1 = this.control.checkHoldCursors(var1, false);
         this.getRealConnection().setHoldability(var1);
         this.stateHoldability = var1;
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final PreparedStatement prepareStatement(String var1, int var2) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1, var2), var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final PreparedStatement prepareStatement(String var1, int[] var2) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1, var2), var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final PreparedStatement prepareStatement(String var1, String[] var2) throws SQLException {
      try {
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1, var2), var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   final SQLException noCurrentConnection() {
      return ExceptionFactory.getInstance().getSQLException("08003", (SQLException)null, (Throwable)null, (Object[])null);
   }

   final EngineConnection getRealConnection() throws SQLException {
      if (this.isClosed) {
         throw this.noCurrentConnection();
      } else {
         return this.control.getRealConnection();
      }
   }

   final void notifyException(SQLException var1) {
      if (!this.isClosed) {
         this.control.notifyException(var1);
      }

   }

   public void syncState() throws SQLException {
      EngineConnection var1 = this.getRealConnection();
      this.stateIsolationLevel = var1.getTransactionIsolation();
      this.stateReadOnly = var1.isReadOnly();
      this.stateAutoCommit = var1.getAutoCommit();
      this.stateHoldability = var1.getHoldability();
   }

   public void getIsolationUptoDate() throws SQLException {
      if (this.control.isIsolationLevelSetUsingSQLorJDBC()) {
         this.stateIsolationLevel = this.getRealConnection().getTransactionIsolation();
         this.control.resetIsolationLevelFlag();
      }

   }

   public void setState(boolean var1) throws SQLException {
      if (var1) {
         EngineConnection var2 = this.getRealConnection();
         var2.setTransactionIsolation(this.stateIsolationLevel);
         var2.setReadOnly(this.stateReadOnly);
         var2.setAutoCommit(this.stateAutoCommit);
         var2.setHoldability(this.stateHoldability);
      }

   }

   public final BrokeredStatement newBrokeredStatement(BrokeredStatementControl var1) throws SQLException {
      try {
         return new BrokeredStatement(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public BrokeredPreparedStatement newBrokeredStatement(BrokeredStatementControl var1, String var2, Object var3) throws SQLException {
      try {
         return new BrokeredPreparedStatement(var1, var2, var3);
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw var5;
      }
   }

   public BrokeredCallableStatement newBrokeredStatement(BrokeredStatementControl var1, String var2) throws SQLException {
      try {
         return new BrokeredCallableStatement(var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final void setDrdaID(String var1) {
      try {
         this.getRealConnection().setDrdaID(var1);
      } catch (SQLException var3) {
      }

   }

   public boolean isInGlobalTransaction() {
      return this.control.isInGlobalTransaction();
   }

   public final void setPrepareIsolation(int var1) throws SQLException {
      this.getRealConnection().setPrepareIsolation(var1);
   }

   public final int getPrepareIsolation() throws SQLException {
      return this.getRealConnection().getPrepareIsolation();
   }

   public final void addWarning(SQLWarning var1) throws SQLException {
      this.getRealConnection().addWarning(var1);
   }

   public String toString() {
      if (this.connString == null) {
         String var1;
         try {
            var1 = this.getRealConnection().toString();
         } catch (SQLException var3) {
            var1 = "<none>";
         }

         String var10001 = this.getClass().getName();
         this.connString = var10001 + "@" + this.hashCode() + ", Wrapped Connection = " + var1;
      }

      return this.connString;
   }

   public final PreparedStatement prepareStatement(String var1, int var2, int var3, int var4) throws SQLException {
      try {
         var4 = this.statementHoldabilityCheck(var4);
         return this.control.wrapStatement(this.getRealConnection().prepareStatement(var1, var2, var3, var4), var1, (Object)null);
      } catch (SQLException var6) {
         this.notifyException(var6);
         throw var6;
      }
   }

   public final int getHoldability() throws SQLException {
      try {
         return this.getRealConnection().getHoldability();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Array createArrayOf(String var1, Object[] var2) throws SQLException {
      try {
         return this.getRealConnection().createArrayOf(var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final Blob createBlob() throws SQLException {
      try {
         return this.getRealConnection().createBlob();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Clob createClob() throws SQLException {
      try {
         return this.getRealConnection().createClob();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final NClob createNClob() throws SQLException {
      try {
         return this.getRealConnection().createNClob();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final SQLXML createSQLXML() throws SQLException {
      try {
         return this.getRealConnection().createSQLXML();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final Struct createStruct(String var1, Object[] var2) throws SQLException {
      try {
         return this.getRealConnection().createStruct(var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   public final boolean isValid(int var1) throws SQLException {
      if (this.isClosed()) {
         return false;
      } else {
         try {
            return this.getRealConnection().isValid(var1);
         } catch (SQLException var3) {
            this.notifyException(var3);
            throw var3;
         }
      }
   }

   public final void setClientInfo(String var1, String var2) throws SQLClientInfoException {
      try {
         this.getRealConnection().setClientInfo(var1, var2);
      } catch (SQLClientInfoException var4) {
         this.notifyException(var4);
         throw var4;
      } catch (SQLException var5) {
         this.notifyException(var5);
         throw new SQLClientInfoException(var5.getMessage(), var5.getSQLState(), var5.getErrorCode(), (new FailedProperties40(FailedProperties40.makeProperties(var1, var2))).getProperties());
      }
   }

   public final void setClientInfo(Properties var1) throws SQLClientInfoException {
      try {
         this.getRealConnection().setClientInfo(var1);
      } catch (SQLClientInfoException var3) {
         this.notifyException(var3);
         throw var3;
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw new SQLClientInfoException(var4.getMessage(), var4.getSQLState(), var4.getErrorCode(), (new FailedProperties40(var1)).getProperties());
      }
   }

   public final String getClientInfo(String var1) throws SQLException {
      try {
         return this.getRealConnection().getClientInfo(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final Properties getClientInfo() throws SQLException {
      try {
         return this.getRealConnection().getClientInfo();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public final boolean isWrapperFor(Class var1) throws SQLException {
      try {
         if (this.getRealConnection().isClosed()) {
            throw this.noCurrentConnection();
         } else {
            return var1.isInstance(this);
         }
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public final Object unwrap(Class var1) throws SQLException {
      try {
         if (this.getRealConnection().isClosed()) {
            throw this.noCurrentConnection();
         } else {
            try {
               return var1.cast(this);
            } catch (ClassCastException var3) {
               throw ExceptionFactory.getInstance().getSQLException("XJ128.S", (SQLException)null, (Throwable)null, new Object[]{var1});
            }
         }
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }

   final int statementHoldabilityCheck(int var1) throws SQLException {
      int var2 = this.control.checkHoldCursors(var1, true);
      if (var2 != var1) {
         SQLWarning var3 = SQLWarningFactory.newSQLWarning("01J07", new Object[0]);
         this.addWarning(var3);
      }

      return var2;
   }

   public Object getLOBMapping(int var1) throws SQLException {
      return this.getRealConnection().getLOBMapping(var1);
   }

   public String getCurrentSchemaName() throws SQLException {
      try {
         return this.getRealConnection().getCurrentSchemaName();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public void resetFromPool() throws SQLException {
      this.getRealConnection().resetFromPool();
   }

   public String getSchema() throws SQLException {
      try {
         return this.getRealConnection().getSchema();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public void setSchema(String var1) throws SQLException {
      try {
         this.getRealConnection().setSchema(var1);
      } catch (SQLException var3) {
         this.notifyException(var3);
         throw var3;
      }
   }

   public void abort(Executor var1) throws SQLException {
      if (!this.isClosed) {
         this.getRealConnection().abort(var1);
      }

   }

   public int getNetworkTimeout() throws SQLException {
      try {
         return this.getRealConnection().getNetworkTimeout();
      } catch (SQLException var2) {
         this.notifyException(var2);
         throw var2;
      }
   }

   public void setNetworkTimeout(Executor var1, int var2) throws SQLException {
      try {
         this.getRealConnection().setNetworkTimeout(var1, var2);
      } catch (SQLException var4) {
         this.notifyException(var4);
         throw var4;
      }
   }
}
