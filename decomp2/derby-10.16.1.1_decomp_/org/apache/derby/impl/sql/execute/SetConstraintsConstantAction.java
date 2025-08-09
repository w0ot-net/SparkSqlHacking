package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.impl.sql.compile.TableName;
import org.apache.derby.shared.common.error.StandardException;

class SetConstraintsConstantAction extends DDLConstantAction {
   private final boolean deferred;
   private final List constraints;

   SetConstraintsConstantAction(List var1, boolean var2) {
      this.constraints = var1;
      this.deferred = var2;
   }

   public String toString() {
      return "SET CONSTRAINTS";
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      ArrayList var4 = new ArrayList();
      if (this.constraints != null) {
         for(TableName var6 : this.constraints) {
            SchemaDescriptor var7 = var3.getSchemaDescriptor(var6.getSchemaName(), var2.getTransactionExecute(), true);
            ConstraintDescriptor var8 = var3.getConstraintDescriptor(var6.getTableName(), var7.getUUID());
            if (var8 == null) {
               throw StandardException.newException("42X94", new Object[]{"CONSTRAINT", var6.getFullSQLName()});
            }

            String var10000 = IdUtil.normalToDelimited(var7.getSchemaName());
            String var9 = var10000 + "." + IdUtil.normalToDelimited(var8.getConstraintName());
            if (var4.contains(var9)) {
               throw StandardException.newException("42734", new Object[]{var8.getConstraintName(), var9});
            }

            var4.add(var9);
            if (this.deferred && !var8.deferrable()) {
               throw StandardException.newException("X0Y91.S", new Object[]{var8.getConstraintName()});
            }

            var2.setConstraintDeferred(var1, var8, this.deferred);
         }
      } else {
         var2.setDeferredAll(var1, this.deferred);
      }

   }
}
