package org.apache.derby.impl.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import org.apache.derby.iapi.jdbc.EmbeddedDataSourceInterface;
import org.apache.derby.iapi.jdbc.EngineConnection;
import org.apache.derby.iapi.jdbc.ResourceAdapter;

public class EmbedXAConnection extends EmbedPooledConnection implements XAConnection {
   private EmbedXAResource xaRes;

   public EmbedXAConnection(EmbeddedDataSourceInterface var1, ResourceAdapter var2, String var3, String var4, boolean var5) throws SQLException {
      super(var1, var3, var4, var5);
      this.xaRes = new EmbedXAResource(this, var2);
   }

   public boolean isInGlobalTransaction() {
      return this.isGlobal();
   }

   private boolean isGlobal() {
      return this.xaRes.getCurrentXid() != null;
   }

   public final synchronized XAResource getXAResource() throws SQLException {
      this.checkActive();
      return this.xaRes;
   }

   public void checkAutoCommit(boolean var1) throws SQLException {
      if (var1 && this.isGlobal()) {
         throw Util.generateCsSQLException("XJ056.S");
      } else {
         super.checkAutoCommit(var1);
      }
   }

   public int checkHoldCursors(int var1, boolean var2) throws SQLException {
      if (var1 == 1 && this.isGlobal()) {
         if (!var2) {
            throw Util.generateCsSQLException("XJ05C.S");
         }

         var1 = 2;
      }

      return super.checkHoldCursors(var1, var2);
   }

   public void checkSavepoint() throws SQLException {
      if (this.isGlobal()) {
         throw Util.generateCsSQLException("XJ058.S");
      } else {
         super.checkSavepoint();
      }
   }

   public void checkRollback() throws SQLException {
      if (this.isGlobal()) {
         throw Util.generateCsSQLException("XJ058.S");
      } else {
         super.checkRollback();
      }
   }

   public void checkCommit() throws SQLException {
      if (this.isGlobal()) {
         throw Util.generateCsSQLException("XJ057.S");
      } else {
         super.checkCommit();
      }
   }

   public void checkClose() throws SQLException {
      if (!this.isGlobal()) {
         super.checkClose();
      }

   }

   public Connection getConnection() throws SQLException {
      Connection var1;
      if (!this.isGlobal()) {
         var1 = super.getConnection();
      } else {
         if (this.currentConnectionHandle != null) {
            throw Util.generateCsSQLException("XJ059.S");
         }

         var1 = this.getNewCurrentConnectionHandle();
      }

      this.currentConnectionHandle.syncState();
      return var1;
   }

   public Statement wrapStatement(Statement var1) throws SQLException {
      XAStatementControl var2 = new XAStatementControl(this, var1);
      return var2.applicationStatement;
   }

   public PreparedStatement wrapStatement(PreparedStatement var1, String var2, Object var3) throws SQLException {
      var1 = super.wrapStatement(var1, var2, var3);
      XAStatementControl var4 = new XAStatementControl(this, var1, var2, var3);
      return (PreparedStatement)var4.applicationStatement;
   }

   public CallableStatement wrapStatement(CallableStatement var1, String var2) throws SQLException {
      var1 = super.wrapStatement(var1, var2);
      XAStatementControl var3 = new XAStatementControl(this, var1, var2);
      return (CallableStatement)var3.applicationStatement;
   }

   public EngineConnection getRealConnection() throws SQLException {
      EngineConnection var1 = super.getRealConnection();
      if (var1 != null) {
         return var1;
      } else {
         this.openRealConnection();
         this.currentConnectionHandle.setState(true);
         return this.realConnection;
      }
   }
}
