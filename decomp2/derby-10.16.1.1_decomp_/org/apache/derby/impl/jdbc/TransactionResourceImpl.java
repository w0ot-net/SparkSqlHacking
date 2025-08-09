package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.util.Properties;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.iapi.util.InterruptStatus;
import org.apache.derby.shared.common.error.StandardException;

public final class TransactionResourceImpl {
   protected ContextManager cm;
   protected ContextService csf;
   protected String username;
   private String dbname;
   private InternalDriver driver;
   private String url;
   private String drdaID;
   protected Database database;
   protected LanguageConnectionContext lcc;

   TransactionResourceImpl(InternalDriver var1, String var2, Properties var3) throws SQLException {
      this.driver = var1;
      this.csf = var1.getContextServiceFactory();
      this.dbname = InternalDriver.getDatabaseName(var2, var3);
      this.url = var2;
      this.username = IdUtil.getUserNameFromURLProps(var3);
      this.drdaID = var3.getProperty("drdaID", (String)null);
      this.cm = this.csf.newContextManager();
   }

   void setDatabase(Database var1) {
      this.database = var1;
   }

   void startTransaction() throws StandardException, SQLException {
      this.lcc = this.database.setupConnection(this.cm, this.username, this.drdaID, this.dbname);
   }

   InternalDriver getDriver() {
      return this.driver;
   }

   ContextService getCsf() {
      return this.csf;
   }

   ContextManager getContextManager() {
      return this.cm;
   }

   LanguageConnectionContext getLcc() {
      return this.lcc;
   }

   String getDBName() {
      return this.dbname;
   }

   String getUrl() {
      return this.url;
   }

   Database getDatabase() {
      return this.database;
   }

   StandardException shutdownDatabaseException() {
      StandardException var1 = StandardException.newException("08006.D", new Object[]{this.getDBName()});
      var1.setReport(1);
      return var1;
   }

   void commit() throws StandardException {
      this.lcc.userCommit();
   }

   void rollback() throws StandardException {
      if (this.lcc != null) {
         this.lcc.userRollback();
      }

   }

   void clearContextInError() {
      this.csf.resetCurrentContextManager(this.cm);
      this.cm = null;
   }

   void clearLcc() {
      this.lcc = null;
   }

   final void setupContextStack() {
      this.csf.setCurrentContextManager(this.cm);
   }

   final void restoreContextStack() {
      if (this.csf != null && this.cm != null) {
         this.csf.resetCurrentContextManager(this.cm);
      }
   }

   final SQLException handleException(Throwable var1, boolean var2, boolean var3) throws SQLException {
      try {
         if (var1 instanceof SQLException) {
            InterruptStatus.restoreIntrFlagIfSeen();
            return (SQLException)var1;
         } else {
            boolean var4 = false;
            if (var1 instanceof StandardException) {
               StandardException var5 = (StandardException)var1;
               int var6 = var5.getSeverity();
               if (var6 <= 20000) {
                  if (var2 && var3) {
                     var5.setSeverity(30000);
                  }
               } else if ("08000".equals(var5.getMessageId())) {
                  var4 = true;
               }
            }

            if (this.cm != null) {
               boolean var8 = this.database != null && this.database.isActive() && !this.isLoginException((Throwable)var1);
               boolean var9 = this.cleanupOnError((Throwable)var1, var8);
               if (var4 && var9) {
                  var1 = this.shutdownDatabaseException();
               }
            }

            InterruptStatus.restoreIntrFlagIfSeen();
            return wrapInSQLException((Throwable)var1);
         }
      } catch (Throwable var7) {
         if (this.cm != null) {
            this.cm.cleanupOnError(var7, this.database != null ? this.isActive() : false);
         }

         InterruptStatus.restoreIntrFlagIfSeen();
         throw wrapInSQLException(var7);
      }
   }

   private boolean isLoginException(Throwable var1) {
      return var1 instanceof StandardException && ((StandardException)var1).getSQLState().equals("08004");
   }

   public static SQLException wrapInSQLException(Throwable var0) {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof SQLException) {
         return (SQLException)var0;
      } else if (var0 instanceof StandardException) {
         StandardException var1 = (StandardException)var0;
         if ("08000".equals(var1.getSQLState())) {
            Thread.currentThread().interrupt();
         }

         return var1.getCause() == null ? Util.generateCsSQLException(var1) : Util.seeNextException(var1.getMessageId(), wrapInSQLException(var1.getCause()), var1.getCause(), var1.getArguments());
      } else {
         return Util.javaException(var0);
      }
   }

   String getUserName() {
      return this.username;
   }

   boolean cleanupOnError(Throwable var1, boolean var2) {
      return this.cm.cleanupOnError(var1, var2);
   }

   boolean isIdle() {
      return this.lcc == null || this.lcc.getTransactionExecute().isIdle();
   }

   boolean isActive() {
      return this.driver.isActive() && (this.database == null || this.database.isActive());
   }
}
