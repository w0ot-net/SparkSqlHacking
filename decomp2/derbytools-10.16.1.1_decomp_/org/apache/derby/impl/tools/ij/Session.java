package org.apache.derby.impl.tools.ij;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import org.apache.derby.iapi.tools.i18n.LocalizedOutput;

class Session {
   static final String DEFAULT_NAME = "CONNECTION";
   boolean singleSession = true;
   Connection conn = null;
   String tag;
   String name;
   Hashtable prepStmts = new Hashtable();
   Hashtable cursorStmts = new Hashtable();
   Hashtable cursors = new Hashtable();
   Hashtable asyncStmts = new Hashtable();
   boolean isJCC = false;
   boolean isDNC = false;
   boolean isEmbeddedDerby = false;

   Session(Connection var1, String var2, String var3) {
      this.conn = var1;
      this.tag = var2;
      this.name = var3;

      try {
         this.isJCC = this.conn.getMetaData().getDriverName().startsWith("IBM DB2 JDBC Universal Driver");
         this.isDNC = this.conn.getMetaData().getDriverName().startsWith("Apache Derby Network Client");
         this.isEmbeddedDerby = this.conn.getMetaData().getDriverName().startsWith("Apache Derby Embedded JDBC Driver");
      } catch (SQLException var5) {
      }

   }

   Connection getConnection() {
      return this.conn;
   }

   boolean getIsJCC() {
      return this.isJCC;
   }

   boolean getIsDNC() {
      return this.isDNC;
   }

   boolean getIsEmbeddedDerby() {
      return this.isEmbeddedDerby;
   }

   String getName() {
      return this.name;
   }

   PreparedStatement addPreparedStatement(String var1, PreparedStatement var2) {
      return (PreparedStatement)this.prepStmts.put(var1, var2);
   }

   Statement addCursorStatement(String var1, Statement var2) {
      return (Statement)this.cursorStmts.put(var1, var2);
   }

   ResultSet addCursor(String var1, ResultSet var2) {
      return (ResultSet)this.cursors.put(var1, var2);
   }

   AsyncStatement addAsyncStatement(String var1, AsyncStatement var2) {
      return (AsyncStatement)this.asyncStmts.put(var1, var2);
   }

   PreparedStatement getPreparedStatement(String var1) {
      return (PreparedStatement)this.prepStmts.get(var1);
   }

   Statement getCursorStatement(String var1) {
      return (Statement)this.cursorStmts.get(var1);
   }

   ResultSet getCursor(String var1) {
      return (ResultSet)this.cursors.get(var1);
   }

   AsyncStatement getAsyncStatement(String var1) {
      return (AsyncStatement)this.asyncStmts.get(var1);
   }

   boolean removePreparedStatement(String var1) {
      return this.prepStmts.remove(var1) != null;
   }

   boolean removeCursorStatement(String var1) {
      return this.cursorStmts.remove(var1) != null;
   }

   boolean removeCursor(String var1) {
      return this.cursors.remove(var1) != null;
   }

   void doPrompt(boolean var1, LocalizedOutput var2, boolean var3) {
      if (var3 && this.singleSession) {
         this.singleSession = false;
         if (this.tag == null) {
            this.tag = "(" + this.name + ")";
         } else {
            String var10001 = this.tag.substring(0, this.tag.length() - 1);
            this.tag = var10001 + ":" + this.name + ")";
         }
      }

      if (!var3 && !this.singleSession) {
         this.singleSession = true;
         if (this.tag != null) {
            if (this.tag.length() == this.name.length() + 2) {
               this.tag = null;
            } else {
               String var4 = this.tag.substring(0, this.tag.length() - 2 - this.name.length());
               this.tag = var4 + ")";
            }
         }
      }

      utilMain.doPrompt(var1, var2, this.tag);
   }

   void close() throws SQLException {
      if (!this.conn.isClosed()) {
         if (!this.conn.getAutoCommit() && this.name != null && !this.name.startsWith("XA")) {
            this.conn.rollback();
         }

         this.conn.close();
      }

      this.prepStmts.clear();
      this.cursorStmts.clear();
      this.cursors.clear();
      this.asyncStmts.clear();
      this.conn = null;
   }
}
