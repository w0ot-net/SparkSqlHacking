package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.TriggerDescriptor;
import org.apache.derby.iapi.sql.execute.CursorResultSet;
import org.apache.derby.shared.common.error.StandardException;

class StatementTriggerExecutor extends GenericTriggerExecutor {
   StatementTriggerExecutor(InternalTriggerExecutionContext var1, TriggerDescriptor var2, Activation var3, LanguageConnectionContext var4) {
      super(var1, var2, var3, var4);
   }

   void fireTrigger(TriggerEvent var1, CursorResultSet var2, CursorResultSet var3, int[] var4) throws StandardException {
      this.tec.setTrigger(this.triggerd);
      this.tec.setBeforeResultSet(var2);
      this.tec.setAfterResultSet(var3);

      try {
         this.executeWhenClauseAndAction();
      } finally {
         this.clearSPS();
         this.tec.clearTrigger();
      }

   }
}
