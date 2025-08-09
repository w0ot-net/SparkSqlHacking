package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.shared.common.error.StandardException;

class SavepointConstantAction extends DDLConstantAction {
   private final String savepointName;
   private final int savepointStatementType;

   SavepointConstantAction(String var1, int var2) {
      this.savepointName = var1;
      this.savepointStatementType = var2;
   }

   public String toString() {
      if (this.savepointStatementType == 1) {
         return this.constructToString("SAVEPOINT ", this.savepointName + " ON ROLLBACK RETAIN CURSORS ON ROLLBACK RETAIN LOCKS");
      } else {
         return this.savepointStatementType == 2 ? this.constructToString("ROLLBACK WORK TO SAVEPOINT ", this.savepointName) : this.constructToString("RELEASE TO SAVEPOINT ", this.savepointName);
      }
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      StatementContext var3 = var2.getStatementContext();
      if (var3 != null && var3.inTrigger()) {
         throw StandardException.newException("XJ017.S", new Object[0]);
      } else {
         if (this.savepointStatementType == 1) {
            if (this.savepointName.startsWith("SYS")) {
               throw StandardException.newException("42939", new Object[]{"SYS"});
            }

            var2.languageSetSavePoint(this.savepointName, this.savepointName);
         } else if (this.savepointStatementType == 2) {
            var2.internalRollbackToSavepoint(this.savepointName, true, this.savepointName);
         } else {
            var2.releaseSavePoint(this.savepointName, this.savepointName);
         }

      }
   }
}
