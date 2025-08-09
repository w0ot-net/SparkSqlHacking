package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.jdbc.ConnectionContext;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.iapi.sql.execute.NoPutResultSet;
import org.apache.derby.shared.common.error.StandardException;

public class TriggerEventActivator {
   private LanguageConnectionContext lcc;
   private TriggerInfo triggerInfo;
   private InternalTriggerExecutionContext tec;
   private GenericTriggerExecutor[][] executors;
   private Activation activation;
   private ConnectionContext cc;
   private String statementText;
   private int dmlType;
   private UUID tableId;
   private String tableName;

   public TriggerEventActivator(LanguageConnectionContext var1, UUID var2, TriggerInfo var3, int var4, Activation var5, Vector var6) throws StandardException {
      if (var3 != null) {
         this.tableName = var3.triggerArray[0].getTableDescriptor().getQualifiedName();
         this.lcc = var1;
         this.activation = var5;
         this.tableId = var2;
         this.dmlType = var4;
         this.triggerInfo = var3;
         this.cc = (ConnectionContext)var1.getContextManager().getContext("JDBC_ConnectionContext");
         this.statementText = var1.getStatementContext().getStatementText();
         this.tec = ((GenericExecutionFactory)var1.getLanguageConnectionFactory().getExecutionFactory()).getTriggerExecutionContext(var1, this.cc, this.statementText, var4, var2, this.tableName, var6);
         this.setupExecutors(var3);
      }
   }

   void reopen() throws StandardException {
      this.tec = ((GenericExecutionFactory)this.lcc.getLanguageConnectionFactory().getExecutionFactory()).getTriggerExecutionContext(this.lcc, this.cc, this.statementText, this.dmlType, this.tableId, this.tableName, (Vector)null);
      this.setupExecutors(this.triggerInfo);
   }

   private void setupExecutors(TriggerInfo var1) throws StandardException {
      this.executors = new GenericTriggerExecutor[6][];
      ArrayList var2 = new ArrayList(6);

      for(int var3 = 0; var3 < 6; ++var3) {
         var2.add(new ArrayList());
      }

      for(int var8 = 0; var8 < var1.triggerArray.length; ++var8) {
         TriggerDescriptor var4 = var1.triggerArray[var8];
         switch (var4.getTriggerEventMask()) {
            case 1:
               if (var4.isBeforeTrigger()) {
                  ((List)var2.get(2)).add(var4);
               } else {
                  ((List)var2.get(5)).add(var4);
               }
               break;
            case 2:
               if (var4.isBeforeTrigger()) {
                  ((List)var2.get(1)).add(var4);
               } else {
                  ((List)var2.get(4)).add(var4);
               }
            case 3:
            default:
               break;
            case 4:
               if (var4.isBeforeTrigger()) {
                  ((List)var2.get(0)).add(var4);
               } else {
                  ((List)var2.get(3)).add(var4);
               }
         }
      }

      for(int var9 = 0; var9 < var2.size(); ++var9) {
         List var10 = (List)var2.get(var9);
         int var5 = var10.size();
         if (var5 > 0) {
            this.executors[var9] = new GenericTriggerExecutor[var5];

            for(int var6 = 0; var6 < var5; ++var6) {
               TriggerDescriptor var7 = (TriggerDescriptor)var10.get(var6);
               this.executors[var9][var6] = (GenericTriggerExecutor)(var7.isRowTrigger() ? new RowTriggerExecutor(this.tec, var7, this.activation, this.lcc) : new StatementTriggerExecutor(this.tec, var7, this.activation, this.lcc));
            }
         }
      }

   }

   public void notifyEvent(TriggerEvent var1, CursorResultSet var2, CursorResultSet var3, int[] var4) throws StandardException {
      if (this.executors != null) {
         int var5 = var1.getNumber();
         if (this.executors[var5] != null) {
            this.tec.setCurrentTriggerEvent(var1);

            try {
               if (var2 != null) {
                  var2.open();
               }

               if (var3 != null) {
                  var3.open();
               }

               this.lcc.pushExecutionStmtValidator(this.tec);

               for(int var6 = 0; var6 < this.executors[var5].length; ++var6) {
                  if (var6 > 0) {
                     if (var2 != null) {
                        ((NoPutResultSet)var2).reopenCore();
                     }

                     if (var3 != null) {
                        ((NoPutResultSet)var3).reopenCore();
                     }
                  }

                  this.tec.resetAICounters(true);
                  this.executors[var5][var6].fireTrigger(var1, var2, var3, var4);
               }
            } finally {
               this.lcc.popExecutionStmtValidator(this.tec);
               this.tec.clearCurrentTriggerEvent();
            }

         }
      }
   }

   public void cleanup() throws StandardException {
      if (this.tec != null) {
         this.tec.cleanup();
      }

   }
}
