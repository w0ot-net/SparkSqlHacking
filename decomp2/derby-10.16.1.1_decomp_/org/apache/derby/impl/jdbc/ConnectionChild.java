package org.apache.derby.impl.jdbc;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.apache.derby.iapi.jdbc.InternalDriver;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.util.InterruptStatus;

abstract class ConnectionChild {
   EmbedConnection localConn;
   private LanguageConnectionContext lcc;
   final InternalDriver factory;
   private Calendar cal;

   ConnectionChild(EmbedConnection var1) {
      this.localConn = var1;
      this.factory = var1.getLocalDriver();
   }

   final EmbedConnection getEmbedConnection() {
      return this.localConn;
   }

   final Object getConnectionSynchronization() {
      return this.localConn.getConnectionSynchronization();
   }

   final SQLException handleException(Throwable var1) throws SQLException {
      return this.localConn.handleException(var1);
   }

   final void needCommit() {
      this.localConn.needCommit();
   }

   final void commitIfNeeded() throws SQLException {
      this.localConn.commitIfNeeded();
   }

   final void commitIfAutoCommit() throws SQLException {
      this.localConn.commitIfAutoCommit();
   }

   final void setupContextStack() throws SQLException {
      this.localConn.setupContextStack();
   }

   final void restoreContextStack() throws SQLException {
      this.localConn.restoreContextStack();
   }

   Calendar getCal() {
      if (this.cal == null) {
         this.cal = new GregorianCalendar();
      }

      return this.cal;
   }

   static SQLException newSQLException(String var0, Object... var1) {
      return EmbedConnection.newSQLException(var0, var1);
   }

   protected static void restoreIntrFlagIfSeen(boolean var0, EmbedConnection var1) {
      if (var0) {
         InterruptStatus.restoreIntrFlagIfSeen(getLCC(var1));
      } else {
         InterruptStatus.restoreIntrFlagIfSeen();
      }

   }

   LanguageConnectionContext getLanguageConnectionContext(EmbedConnection var1) {
      if (this.lcc == null) {
         this.lcc = getLCC(var1);
      }

      return this.lcc;
   }

   static LanguageConnectionContext getLCC(EmbedConnection var0) {
      return var0.getLanguageConnection();
   }
}
