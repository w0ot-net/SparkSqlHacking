package org.apache.derby.impl.jdbc;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.services.context.ContextImpl;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.shared.common.error.StandardException;

class EmbedConnectionContext extends ContextImpl implements ConnectionContext {
   private SoftReference connRef;

   EmbedConnectionContext(ContextManager var1, EmbedConnection var2) {
      super(var1, "JDBC_ConnectionContext");
      this.connRef = new SoftReference(var2);
   }

   public void cleanupOnError(Throwable var1) {
      if (this.connRef != null) {
         EmbedConnection var2 = (EmbedConnection)this.connRef.get();
         if (var1 instanceof StandardException) {
            StandardException var3 = (StandardException)var1;
            if (var3.getSeverity() < 40000) {
               if (var2 != null) {
                  var2.needCommit = false;
               }

               return;
            }
         }

         if (var2 != null) {
            var2.setInactive();
         }

         this.connRef = null;
         this.popMe();
      }
   }

   public Connection getNestedConnection(boolean var1) throws SQLException {
      EmbedConnection var2 = (EmbedConnection)this.connRef.get();
      if (var2 != null && !var2.isClosed()) {
         if (!var1) {
            StatementContext var3 = this.privilegedGetLCC(var2).getStatementContext();
            if (var3 == null || var3.getSQLAllowed() < 0) {
               throw Util.noCurrentConnection();
            }
         }

         return var2.getLocalDriver().getNewNestedConnection(var2);
      } else {
         throw Util.noCurrentConnection();
      }
   }

   public ResultSet getResultSet(org.apache.derby.iapi.sql.ResultSet var1) throws SQLException {
      EmbedConnection var2 = (EmbedConnection)this.connRef.get();
      EmbedResultSet var3 = var2.getLocalDriver().newEmbedResultSet(var2, var1, false, (EmbedStatement)null, true);
      return var3;
   }

   public boolean processInaccessibleDynamicResult(ResultSet var1) {
      EmbedConnection var2 = (EmbedConnection)this.connRef.get();
      if (var2 == null) {
         return false;
      } else {
         return EmbedStatement.processDynamicResult(var2, var1, (EmbedStatement)null) != null;
      }
   }

   private LanguageConnectionContext privilegedGetLCC(EmbedConnection var1) {
      return var1.getLanguageConnection();
   }
}
