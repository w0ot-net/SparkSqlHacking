package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.StatementUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.execute.ExecRow;
import org.apache.derby.iapi.store.access.ScanController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class ForeignKeyRIChecker extends GenericRIChecker {
   ForeignKeyRIChecker(LanguageConnectionContext var1, TransactionController var2, FKInfo var3) throws StandardException {
      super(var1, var2, var3);
   }

   void doCheck(Activation var1, ExecRow var2, boolean var3, int var4) throws StandardException {
      if (!var3) {
         if (!this.isAnyFieldNull(var2)) {
            ScanController var5 = this.getScanController(this.fkInfo.refConglomNumber, this.refScoci, this.refDcoci, var2);
            if (!var5.next()) {
               UUID var6 = this.fkInfo.fkIds[0];
               this.close();
               if (!this.fkInfo.deferrable[0] || !this.lcc.isEffectivelyDeferred(this.lcc.getCurrentSQLSessionContext(var1), var6)) {
                  StandardException var7 = StandardException.newException("23503", new Object[]{this.fkInfo.fkConstraintNames[0], this.fkInfo.tableName, StatementUtil.typeName(this.fkInfo.stmtType), RowUtil.toString(var2, this.fkInfo.colArray)});
                  throw var7;
               }

               this.deferredRowsHashTable = DeferredConstraintsMemory.rememberFKViolation(this.lcc, this.deferredRowsHashTable, this.fkInfo.fkIds[0], this.indexQualifierRow.getRowArray(), this.fkInfo.schemaName, this.fkInfo.tableName);
            }

         }
      }
   }

   int getRICheckIsolationLevel() {
      return 2;
   }
}
