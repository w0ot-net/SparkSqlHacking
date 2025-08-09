package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class DropRoleConstantAction extends DDLConstantAction {
   private final String roleName;

   DropRoleConstantAction(String var1) {
      this.roleName = var1;
   }

   public String toString() {
      return "DROP ROLE " + this.roleName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      var3.startWriting(var2);
      RoleGrantDescriptor var5 = var3.getRoleDefinitionDescriptor(this.roleName);
      if (var5 == null) {
         throw StandardException.newException("0P000", new Object[]{this.roleName});
      } else {
         RoleClosureIterator var6 = var3.createRoleClosureIterator(var1.getTransactionController(), this.roleName, false);

         String var7;
         while((var7 = var6.next()) != null) {
            RoleGrantDescriptor var8 = var3.getRoleDefinitionDescriptor(var7);
            var3.getDependencyManager().invalidateFor(var8, 47, var2);
         }

         var5.drop(var2);
         var3.dropRoleGrantsByGrantee(this.roleName, var4);
         var3.dropRoleGrantsByName(this.roleName, var4);
         var3.dropAllPermsByGrantee(this.roleName, var4);
      }
   }
}
