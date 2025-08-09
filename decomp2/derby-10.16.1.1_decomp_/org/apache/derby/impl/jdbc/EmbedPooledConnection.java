package org.apache.derby.impl.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEvent;
import javax.sql.StatementEventListener;
import org.apache.derby.iapi.jdbc.BrokeredConnection;
import org.apache.derby.iapi.jdbc.BrokeredConnectionControl;
import org.apache.derby.iapi.jdbc.EmbeddedDataSourceInterface;
import org.apache.derby.iapi.jdbc.EngineConnection;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;

public class EmbedPooledConnection implements PooledConnection, BrokeredConnectionControl {
   private String connString;
   private ArrayList eventListener;
   private final CopyOnWriteArrayList statementEventListeners = new CopyOnWriteArrayList();
   private int eventIterators;
   EmbedConnection realConnection;
   int defaultIsolationLevel;
   private boolean defaultReadOnly;
   BrokeredConnection currentConnectionHandle;
   final EmbeddedDataSourceInterface dataSource;
   private final String username;
   private final String password;
   private final boolean requestPassword;
   protected boolean isActive;

   public boolean isActive() {
      return this.isActive;
   }

   public EmbedPooledConnection(EmbeddedDataSourceInterface var1, String var2, String var3, boolean var4) throws SQLException {
      this.dataSource = var1;
      this.username = var2;
      this.password = var3;
      this.requestPassword = var4;
      this.isActive = true;
      this.openRealConnection();
   }

   String getUsername() {
      return this.username != null && !this.username.equals("") ? this.username : "APP";
   }

   String getPassword() {
      return this.password == null ? "" : this.password;
   }

   public synchronized Connection getConnection() throws SQLException {
      this.checkActive();
      if (this.realConnection == null) {
         this.openRealConnection();
      } else {
         this.resetRealConnection();
      }

      this.closeCurrentConnectionHandle();
      Connection var1 = this.getNewCurrentConnectionHandle();
      return var1;
   }

   final void openRealConnection() throws SQLException {
      Connection var1 = this.dataSource.getConnection(this.username, this.password, this.requestPassword);
      this.realConnection = (EmbedConnection)var1;
      this.defaultIsolationLevel = var1.getTransactionIsolation();
      this.defaultReadOnly = var1.isReadOnly();
      if (this.currentConnectionHandle != null) {
         this.realConnection.setApplicationConnection(this.currentConnectionHandle);
      }

   }

   final Connection getNewCurrentConnectionHandle() throws SQLException {
      BrokeredConnection var1 = this.currentConnectionHandle = this.realConnection.getLocalDriver().newBrokeredConnection(this);
      this.realConnection.setApplicationConnection(var1);
      return var1;
   }

   private void closeCurrentConnectionHandle() throws SQLException {
      if (this.currentConnectionHandle != null) {
         ArrayList var1 = this.eventListener;
         this.eventListener = null;

         try {
            this.currentConnectionHandle.close();
         } finally {
            this.eventListener = var1;
         }

         this.currentConnectionHandle = null;
      }

   }

   void resetRealConnection() throws SQLException {
      this.realConnection.rollback();
      this.realConnection.clearWarnings();
      if (this.realConnection.getTransactionIsolation() != this.defaultIsolationLevel) {
         this.realConnection.setTransactionIsolation(this.defaultIsolationLevel);
      }

      if (!this.realConnection.getAutoCommit()) {
         this.realConnection.setAutoCommit(true);
      }

      if (this.realConnection.isReadOnly() != this.defaultReadOnly) {
         this.realConnection.setReadOnly(this.defaultReadOnly);
      }

      if (this.realConnection.getHoldability() != 1) {
         this.realConnection.setHoldability(1);
      }

      this.realConnection.resetFromPool();
   }

   public synchronized void close() throws SQLException {
      if (this.isActive) {
         this.closeCurrentConnectionHandle();

         try {
            if (this.realConnection != null && !this.realConnection.isClosed()) {
               this.realConnection.close();
            }
         } finally {
            this.realConnection = null;
            this.isActive = false;
            this.eventListener = null;
         }

      }
   }

   public final synchronized void addConnectionEventListener(ConnectionEventListener var1) {
      if (this.isActive) {
         if (var1 != null) {
            if (this.eventListener == null) {
               this.eventListener = new ArrayList();
            } else if (this.eventIterators > 0) {
               this.eventListener = new ArrayList(this.eventListener);
            }

            this.eventListener.add(var1);
         }
      }
   }

   public final synchronized void removeConnectionEventListener(ConnectionEventListener var1) {
      if (var1 != null && this.eventListener != null) {
         if (this.eventIterators > 0) {
            this.eventListener = new ArrayList(this.eventListener);
         }

         this.eventListener.remove(var1);
      }
   }

   public synchronized EngineConnection getRealConnection() throws SQLException {
      this.checkActive();
      return this.realConnection;
   }

   public synchronized LanguageConnectionContext getLanguageConnection() throws SQLException {
      this.checkActive();
      return this.realConnection.getLanguageConnection();
   }

   public synchronized void notifyError(SQLException var1) {
      if (var1.getErrorCode() >= 40000) {
         this.fireConnectionEventListeners(var1);
      }
   }

   private void fireConnectionEventListeners(SQLException var1) {
      if (this.eventListener != null && !this.eventListener.isEmpty()) {
         ConnectionEvent var2 = new ConnectionEvent(this, var1);
         ++this.eventIterators;

         try {
            for(ConnectionEventListener var4 : this.eventListener) {
               if (var1 == null) {
                  var4.connectionClosed(var2);
               } else {
                  var4.connectionErrorOccurred(var2);
               }
            }
         } finally {
            --this.eventIterators;
         }
      }

   }

   final void checkActive() throws SQLException {
      if (!this.isActive) {
         throw Util.noCurrentConnection();
      }
   }

   public boolean isIsolationLevelSetUsingSQLorJDBC() throws SQLException {
      return this.realConnection != null ? getLanguageConnectionContext(this.realConnection).isIsolationLevelSetUsingSQLorJDBC() : false;
   }

   public void resetIsolationLevelFlag() throws SQLException {
      getLanguageConnectionContext(this.realConnection).resetIsolationLevelFlagUsedForSQLandJDBC();
   }

   public boolean isInGlobalTransaction() {
      return false;
   }

   public void notifyException(SQLException var1) {
      this.notifyError(var1);
   }

   public void checkAutoCommit(boolean var1) throws SQLException {
   }

   public int checkHoldCursors(int var1, boolean var2) throws SQLException {
      return var1;
   }

   public void checkSavepoint() throws SQLException {
   }

   public void checkRollback() throws SQLException {
   }

   public void checkCommit() throws SQLException {
   }

   public void checkClose() throws SQLException {
      if (this.realConnection != null) {
         this.realConnection.checkForTransactionInProgress();
      }

   }

   public synchronized boolean closingConnection() throws SQLException {
      this.currentConnectionHandle = null;
      this.fireConnectionEventListeners((SQLException)null);
      return false;
   }

   public Statement wrapStatement(Statement var1) throws SQLException {
      return var1;
   }

   public PreparedStatement wrapStatement(PreparedStatement var1, String var2, Object var3) throws SQLException {
      EmbedPreparedStatement var4 = (EmbedPreparedStatement)var1;
      var4.setBrokeredConnectionControl(this);
      return var4;
   }

   public CallableStatement wrapStatement(CallableStatement var1, String var2) throws SQLException {
      EmbedCallableStatement var3 = (EmbedCallableStatement)var1;
      var3.setBrokeredConnectionControl(this);
      return var3;
   }

   public String toString() {
      if (this.connString == null) {
         String var1 = this.isActive ? this.realConnection.toString() : "<none>";
         String var10001 = this.getClass().getName();
         this.connString = var10001 + "@" + this.hashCode() + " Physical Connection = " + var1;
      }

      return this.connString;
   }

   public void onStatementClose(PreparedStatement var1) {
      if (!this.statementEventListeners.isEmpty()) {
         StatementEvent var2 = new StatementEvent(this, var1);

         for(StatementEventListener var4 : this.statementEventListeners) {
            var4.statementClosed(var2);
         }
      }

   }

   public void onStatementErrorOccurred(PreparedStatement var1, SQLException var2) {
      if (!this.statementEventListeners.isEmpty()) {
         StatementEvent var3 = new StatementEvent(this, var1, var2);

         for(StatementEventListener var5 : this.statementEventListeners) {
            var5.statementErrorOccurred(var3);
         }
      }

   }

   public void removeStatementEventListener(StatementEventListener var1) {
      if (var1 != null) {
         this.statementEventListeners.remove(var1);
      }

   }

   public void addStatementEventListener(StatementEventListener var1) {
      if (this.isActive && var1 != null) {
         this.statementEventListeners.add(var1);
      }

   }

   private static LanguageConnectionContext getLanguageConnectionContext(EmbedConnection var0) {
      return var0.getLanguageConnection();
   }
}
