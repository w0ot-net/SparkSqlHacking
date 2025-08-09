package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ResultSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.dictionary.SPSDescriptor;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.ExecPreparedStatement;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class GenericTriggerExecutor {
   final InternalTriggerExecutionContext tec;
   final TriggerDescriptor triggerd;
   final Activation activation;
   private final LanguageConnectionContext lcc;
   private boolean whenClauseRetrieved;
   private boolean actionRetrieved;
   private SPSDescriptor whenClause;
   private SPSDescriptor action;
   private ExecPreparedStatement whenPS;
   private Activation spsWhenActivation;
   private ExecPreparedStatement actionPS;
   private Activation spsActionActivation;

   GenericTriggerExecutor(InternalTriggerExecutionContext var1, TriggerDescriptor var2, Activation var3, LanguageConnectionContext var4) {
      this.tec = var1;
      this.triggerd = var2;
      this.activation = var3;
      this.lcc = var4;
   }

   abstract void fireTrigger(TriggerEvent var1, CursorResultSet var2, CursorResultSet var3, int[] var4) throws StandardException;

   private SPSDescriptor getWhenClause() throws StandardException {
      if (!this.whenClauseRetrieved) {
         this.whenClauseRetrieved = true;
         this.whenClause = this.triggerd.getWhenClauseSPS(this.lcc);
      }

      return this.whenClause;
   }

   private SPSDescriptor getAction() throws StandardException {
      if (!this.actionRetrieved) {
         this.actionRetrieved = true;
         this.action = this.triggerd.getActionSPS(this.lcc);
      }

      return this.action;
   }

   private boolean executeSPS(SPSDescriptor var1, boolean var2) throws StandardException {
      boolean var3 = false;
      boolean var4 = false;
      ExecPreparedStatement var5 = var2 ? this.whenPS : this.actionPS;
      Activation var6 = var2 ? this.spsWhenActivation : this.spsActionActivation;

      while(true) {
         if (var5 == null || var3) {
            this.lcc.getStatementContext().setActivation(this.activation);
            ExecPreparedStatement var12 = var1.getPreparedStatement();
            var5 = var12.getClone();
            var5.setValid();
            var6 = var5.getActivation(this.lcc, false);
            var5.setSource(var1.getText());
            var5.setSPSAction();
            if (var2) {
               this.whenPS = var5;
               this.spsWhenActivation = var6;
            } else {
               this.actionPS = var5;
               this.spsActionActivation = var6;
            }
         }

         StatementContext var7 = this.lcc.getStatementContext();

         try {
            ResultSet var8 = var5.executeSubStatement(this.activation, var6, false, 0L);
            if (var2) {
               ExecRow var13 = var8.getNextRow();
               DataValueDescriptor var10 = var13.getColumn(1);
               var4 = !var10.isNull() && var10.getBoolean();
            } else if (var8.returnsRows()) {
               while(var8.getNextRow() != null) {
               }
            }

            var8.close();
            return var4;
         } catch (StandardException var11) {
            StatementContext var9 = this.lcc.getStatementContext();
            if (var9 != null && var7 != var9) {
               var9.cleanupOnError(var11);
            }

            if (!var11.getMessageId().equals("XCL32.S")) {
               var6.close();
               throw var11;
            }

            var3 = true;
            var1.revalidate(this.lcc);
         }
      }
   }

   protected void clearSPS() throws StandardException {
      if (this.spsActionActivation != null) {
         this.spsActionActivation.close();
      }

      this.actionPS = null;
      this.spsActionActivation = null;
      if (this.spsWhenActivation != null) {
         this.spsWhenActivation.close();
      }

      this.whenPS = null;
      this.spsWhenActivation = null;
   }

   final void executeWhenClauseAndAction() throws StandardException {
      SPSDescriptor var1 = this.getWhenClause();
      if (var1 == null || this.executeSPS(var1, true)) {
         this.executeSPS(this.getAction(), false);
      }

   }
}
