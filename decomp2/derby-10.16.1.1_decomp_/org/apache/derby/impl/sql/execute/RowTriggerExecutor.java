package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.shared.common.error.StandardException;

class RowTriggerExecutor extends GenericTriggerExecutor {
   RowTriggerExecutor(InternalTriggerExecutionContext var1, TriggerDescriptor var2, Activation var3, LanguageConnectionContext var4) {
      super(var1, var2, var3, var4);
   }

   void fireTrigger(TriggerEvent var1, CursorResultSet var2, CursorResultSet var3, int[] var4) throws StandardException {
      this.tec.setTrigger(this.triggerd);

      try {
         while((var2 == null || var2.getNextRow() != null) && (var3 == null || var3.getNextRow() != null)) {
            this.tec.setBeforeResultSet(var2 == null ? null : TemporaryRowHolderResultSet.getNewRSOnCurrentRow(this.triggerd, this.activation, var2, var4));
            this.tec.setAfterResultSet(var3 == null ? null : TemporaryRowHolderResultSet.getNewRSOnCurrentRow(this.triggerd, this.activation, var3, var4));
            if (var1.isAfter()) {
               this.tec.updateAICounters();
            }

            this.executeWhenClauseAndAction();
            if (var1.isBefore()) {
               this.tec.updateAICounters();
            }
         }
      } finally {
         this.clearSPS();
         this.tec.clearTrigger();
      }

   }
}
