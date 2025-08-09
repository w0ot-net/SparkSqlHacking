package org.apache.derby.impl.jdbc;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.derby.iapi.jdbc.BrokeredCallableStatement;
import org.apache.derby.iapi.jdbc.BrokeredConnection;
import org.apache.derby.iapi.jdbc.BrokeredPreparedStatement;
import org.apache.derby.iapi.jdbc.BrokeredStatement;
import org.apache.derby.iapi.jdbc.BrokeredStatementControl;

final class XAStatementControl implements BrokeredStatementControl {
   private final EmbedXAConnection xaConnection;
   private final BrokeredConnection applicationConnection;
   BrokeredStatement applicationStatement;
   private EmbedConnection realConnection;
   private Statement realStatement;
   private PreparedStatement realPreparedStatement;
   private CallableStatement realCallableStatement;

   private XAStatementControl(EmbedXAConnection var1) {
      this.xaConnection = var1;
      this.realConnection = var1.realConnection;
      this.applicationConnection = var1.currentConnectionHandle;
   }

   XAStatementControl(EmbedXAConnection var1, Statement var2) throws SQLException {
      this(var1);
      this.realStatement = var2;
      this.applicationStatement = this.applicationConnection.newBrokeredStatement(this);
      ((EmbedStatement)var2).setApplicationStatement(this.applicationStatement);
   }

   XAStatementControl(EmbedXAConnection var1, PreparedStatement var2, String var3, Object var4) throws SQLException {
      this(var1);
      this.realPreparedStatement = var2;
      this.applicationStatement = this.applicationConnection.newBrokeredStatement(this, var3, var4);
      ((EmbedStatement)var2).setApplicationStatement(this.applicationStatement);
   }

   XAStatementControl(EmbedXAConnection var1, CallableStatement var2, String var3) throws SQLException {
      this(var1);
      this.realCallableStatement = var2;
      this.applicationStatement = this.applicationConnection.newBrokeredStatement(this, var3);
      ((EmbedStatement)var2).setApplicationStatement(this.applicationStatement);
   }

   public void closeRealStatement() throws SQLException {
      this.realStatement.close();
   }

   public void closeRealCallableStatement() throws SQLException {
      this.realCallableStatement.close();
   }

   public void closeRealPreparedStatement() throws SQLException {
      this.realPreparedStatement.close();
   }

   public Statement getRealStatement() throws SQLException {
      if (this.applicationConnection == this.xaConnection.currentConnectionHandle) {
         if (this.realConnection == this.xaConnection.realConnection) {
            return this.realStatement;
         }

         if (this.xaConnection.realConnection == null) {
            this.xaConnection.getRealConnection();
         }

         Statement var1 = this.applicationStatement.createDuplicateStatement(this.xaConnection.realConnection, this.realStatement);
         ((EmbedStatement)this.realStatement).transferBatch((EmbedStatement)var1);

         try {
            this.realStatement.close();
         } catch (SQLException var3) {
         }

         this.realStatement = var1;
         this.realConnection = this.xaConnection.realConnection;
         ((EmbedStatement)this.realStatement).setApplicationStatement(this.applicationStatement);
      }

      return this.realStatement;
   }

   public PreparedStatement getRealPreparedStatement() throws SQLException {
      if (this.applicationConnection == this.xaConnection.currentConnectionHandle) {
         if (this.realConnection == this.xaConnection.realConnection) {
            return this.realPreparedStatement;
         }

         if (this.xaConnection.realConnection == null) {
            this.xaConnection.getRealConnection();
         }

         PreparedStatement var1 = ((BrokeredPreparedStatement)this.applicationStatement).createDuplicateStatement(this.xaConnection.realConnection, this.realPreparedStatement);
         ((EmbedPreparedStatement)this.realPreparedStatement).transferParameters((EmbedPreparedStatement)var1);

         try {
            this.realPreparedStatement.close();
         } catch (SQLException var3) {
         }

         this.realPreparedStatement = var1;
         this.realConnection = this.xaConnection.realConnection;
         ((EmbedStatement)this.realPreparedStatement).setApplicationStatement(this.applicationStatement);
      }

      return this.realPreparedStatement;
   }

   public CallableStatement getRealCallableStatement() throws SQLException {
      if (this.applicationConnection == this.xaConnection.currentConnectionHandle) {
         if (this.realConnection == this.xaConnection.realConnection) {
            return this.realCallableStatement;
         }

         if (this.xaConnection.realConnection == null) {
            this.xaConnection.getRealConnection();
         }

         CallableStatement var1 = ((BrokeredCallableStatement)this.applicationStatement).createDuplicateStatement(this.xaConnection.realConnection, this.realCallableStatement);
         ((EmbedStatement)this.realCallableStatement).transferBatch((EmbedStatement)var1);

         try {
            this.realCallableStatement.close();
         } catch (SQLException var3) {
         }

         this.realCallableStatement = var1;
         this.realConnection = this.xaConnection.realConnection;
         ((EmbedStatement)this.realCallableStatement).setApplicationStatement(this.applicationStatement);
      }

      return this.realCallableStatement;
   }

   public ResultSet wrapResultSet(Statement var1, ResultSet var2) {
      if (var2 != null) {
         ((EmbedResultSet)var2).setApplicationStatement(var1);
      }

      return var2;
   }

   public int checkHoldCursors(int var1) throws SQLException {
      return this.xaConnection.checkHoldCursors(var1, true);
   }
}
