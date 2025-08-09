package org.apache.derby.iapi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.apache.derby.shared.common.error.ExceptionFactory;

public class BrokeredStatement implements EngineStatement {
   final BrokeredStatementControl control;
   final int resultSetType;
   final int resultSetConcurrency;
   final int resultSetHoldability;
   private String cursorName;
   private Boolean escapeProcessing;

   BrokeredStatement(BrokeredStatementControl var1) throws SQLException {
      this.control = var1;
      this.resultSetType = this.getResultSetType();
      this.resultSetConcurrency = this.getResultSetConcurrency();
      this.resultSetHoldability = this.getResultSetHoldability();
   }

   public final void addBatch(String var1) throws SQLException {
      this.getStatement().addBatch(var1);
   }

   public final void clearBatch() throws SQLException {
      this.getStatement().clearBatch();
   }

   public final int[] executeBatch() throws SQLException {
      return this.getStatement().executeBatch();
   }

   public final void cancel() throws SQLException {
      this.getStatement().cancel();
   }

   public final boolean execute(String var1) throws SQLException {
      return this.getStatement().execute(var1);
   }

   public final ResultSet executeQuery(String var1) throws SQLException {
      return this.wrapResultSet(this.getStatement().executeQuery(var1));
   }

   public final int executeUpdate(String var1) throws SQLException {
      return this.getStatement().executeUpdate(var1);
   }

   public void close() throws SQLException {
      this.control.closeRealStatement();
   }

   public final Connection getConnection() throws SQLException {
      return this.getStatement().getConnection();
   }

   public final int getFetchDirection() throws SQLException {
      return this.getStatement().getFetchDirection();
   }

   public final int getFetchSize() throws SQLException {
      return this.getStatement().getFetchSize();
   }

   public final int getMaxFieldSize() throws SQLException {
      return this.getStatement().getMaxFieldSize();
   }

   public final int getMaxRows() throws SQLException {
      return this.getStatement().getMaxRows();
   }

   public final int getResultSetConcurrency() throws SQLException {
      return this.getStatement().getResultSetConcurrency();
   }

   public final void setMaxFieldSize(int var1) throws SQLException {
      this.getStatement().setMaxFieldSize(var1);
   }

   public final void setMaxRows(int var1) throws SQLException {
      this.getStatement().setMaxRows(var1);
   }

   public final void setEscapeProcessing(boolean var1) throws SQLException {
      this.getStatement().setEscapeProcessing(var1);
      this.escapeProcessing = var1 ? Boolean.TRUE : Boolean.FALSE;
   }

   public final SQLWarning getWarnings() throws SQLException {
      return this.getStatement().getWarnings();
   }

   public final void clearWarnings() throws SQLException {
      this.getStatement().clearWarnings();
   }

   public final void setCursorName(String var1) throws SQLException {
      this.getStatement().setCursorName(var1);
      this.cursorName = var1;
   }

   public final ResultSet getResultSet() throws SQLException {
      return this.wrapResultSet(this.getStatement().getResultSet());
   }

   public final int getUpdateCount() throws SQLException {
      return this.getStatement().getUpdateCount();
   }

   public final boolean getMoreResults() throws SQLException {
      return this.getStatement().getMoreResults();
   }

   public final int getResultSetType() throws SQLException {
      return this.getStatement().getResultSetType();
   }

   public final void setFetchDirection(int var1) throws SQLException {
      this.getStatement().setFetchDirection(var1);
   }

   public final void setFetchSize(int var1) throws SQLException {
      this.getStatement().setFetchSize(var1);
   }

   public final int getQueryTimeout() throws SQLException {
      return this.getStatement().getQueryTimeout();
   }

   public final void setQueryTimeout(int var1) throws SQLException {
      this.getStatement().setQueryTimeout(var1);
   }

   public final boolean execute(String var1, int var2) throws SQLException {
      return this.getStatement().execute(var1, var2);
   }

   public final boolean execute(String var1, int[] var2) throws SQLException {
      return this.getStatement().execute(var1, var2);
   }

   public final boolean execute(String var1, String[] var2) throws SQLException {
      return this.getStatement().execute(var1, var2);
   }

   public final int executeUpdate(String var1, int var2) throws SQLException {
      int var3 = this.getStatement().executeUpdate(var1, var2);
      return var3;
   }

   public final int executeUpdate(String var1, int[] var2) throws SQLException {
      return this.getStatement().executeUpdate(var1, var2);
   }

   public final int executeUpdate(String var1, String[] var2) throws SQLException {
      return this.getStatement().executeUpdate(var1, var2);
   }

   public final boolean getMoreResults(int var1) throws SQLException {
      return this.getStatement().getMoreResults(var1);
   }

   public final ResultSet getGeneratedKeys() throws SQLException {
      return this.wrapResultSet(this.getStatement().getGeneratedKeys());
   }

   public final int getResultSetHoldability() throws SQLException {
      int var1 = this.getStatement().getResultSetHoldability();
      return this.controlCheck().checkHoldCursors(var1);
   }

   public Statement createDuplicateStatement(Connection var1, Statement var2) throws SQLException {
      Statement var3 = var1.createStatement(this.resultSetType, this.resultSetConcurrency, this.resultSetHoldability);
      this.setStatementState(var2, var3);
      return var3;
   }

   void setStatementState(Statement var1, Statement var2) throws SQLException {
      if (this.cursorName != null) {
         var2.setCursorName(this.cursorName);
      }

      if (this.escapeProcessing != null) {
         var2.setEscapeProcessing(this.escapeProcessing);
      }

      var2.setFetchDirection(var1.getFetchDirection());
      var2.setFetchSize(var1.getFetchSize());
      var2.setMaxFieldSize(var1.getMaxFieldSize());
      var2.setMaxRows(var1.getMaxRows());
      var2.setQueryTimeout(var1.getQueryTimeout());
   }

   public Statement getStatement() throws SQLException {
      return this.control.getRealStatement();
   }

   final ResultSet wrapResultSet(ResultSet var1) {
      return this.control.wrapResultSet(this, var1);
   }

   final BrokeredStatementControl controlCheck() throws SQLException {
      this.getStatement().getConnection();
      return this.control;
   }

   public boolean isWrapperFor(Class var1) throws SQLException {
      this.checkIfClosed();
      return var1.isInstance(this);
   }

   public Object unwrap(Class var1) throws SQLException {
      this.checkIfClosed();

      try {
         return var1.cast(this);
      } catch (ClassCastException var3) {
         throw this.unableToUnwrap(var1);
      }
   }

   public final boolean isClosed() throws SQLException {
      return this.getStatement().isClosed();
   }

   protected final void checkIfClosed() throws SQLException {
      if (this.isClosed()) {
         throw ExceptionFactory.getInstance().getSQLException("XJ012.S", (SQLException)null, (Throwable)null, new Object[]{"Statement"});
      }
   }

   final SQLException unableToUnwrap(Class var1) {
      return ExceptionFactory.getInstance().getSQLException("XJ128.S", (SQLException)null, (Throwable)null, new Object[]{var1});
   }

   public final boolean isPoolable() throws SQLException {
      return this.getStatement().isPoolable();
   }

   public final void setPoolable(boolean var1) throws SQLException {
      this.getStatement().setPoolable(var1);
   }

   public void closeOnCompletion() throws SQLException {
      ((EngineStatement)this.getStatement()).closeOnCompletion();
   }

   public boolean isCloseOnCompletion() throws SQLException {
      return ((EngineStatement)this.getStatement()).isCloseOnCompletion();
   }

   public long[] executeLargeBatch() throws SQLException {
      return ((EngineStatement)this.getStatement()).executeLargeBatch();
   }

   public long executeLargeUpdate(String var1) throws SQLException {
      return ((EngineStatement)this.getStatement()).executeLargeUpdate(var1);
   }

   public long executeLargeUpdate(String var1, int var2) throws SQLException {
      return ((EngineStatement)this.getStatement()).executeLargeUpdate(var1, var2);
   }

   public long executeLargeUpdate(String var1, int[] var2) throws SQLException {
      return ((EngineStatement)this.getStatement()).executeLargeUpdate(var1, var2);
   }

   public long executeLargeUpdate(String var1, String[] var2) throws SQLException {
      return ((EngineStatement)this.getStatement()).executeLargeUpdate(var1, var2);
   }

   public long getLargeMaxRows() throws SQLException {
      return ((EngineStatement)this.getStatement()).getLargeMaxRows();
   }

   public long getLargeUpdateCount() throws SQLException {
      return ((EngineStatement)this.getStatement()).getLargeUpdateCount();
   }

   public void setLargeMaxRows(long var1) throws SQLException {
      ((EngineStatement)this.getStatement()).setLargeMaxRows(var1);
   }
}
