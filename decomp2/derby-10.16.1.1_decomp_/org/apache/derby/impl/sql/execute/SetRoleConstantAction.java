package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.ParameterValueSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataValueDescriptor;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

class SetRoleConstantAction implements ConstantAction {
   private final String roleName;
   private final int type;

   SetRoleConstantAction(String var1, int var2) {
      this.roleName = var1;
      this.type = var2;
   }

   public String toString() {
      String var10000 = this.type == 1 && this.roleName == null ? "?" : this.roleName;
      return "SET ROLE " + var10000;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      String var4 = this.roleName;
      var2.getCurrentUserId(var1);
      String var6 = var2.getDataDictionary().getAuthorizationDatabaseOwner();
      TransactionController var7 = var2.getTransactionExecute();
      if (!var7.isIdle()) {
         throw StandardException.newException("25001.S.1", new Object[0]);
      } else {
         if (this.type == 1) {
            ParameterValueSet var8 = var1.getParameterValueSet();
            DataValueDescriptor var9 = var8.getParameter(0);
            String var10 = var9.getString();
            if (var10 == null) {
               throw StandardException.newException("XCXA0.S", new Object[0]);
            }

            var4 = IdUtil.parseRoleId(var10);
         }

         RoleGrantDescriptor var14 = null;

         try {
            String var15 = var2.getCurrentRoleId(var1);
            if (var15 != null && !var15.equals(var4)) {
               var14 = var3.getRoleDefinitionDescriptor(var15);
               if (var14 != null) {
                  var3.getDependencyManager().invalidateFor(var14, 48, var2);
               }
            }

            if (var4 != null) {
               var14 = var3.getRoleDefinitionDescriptor(var4);
               if (var14 == null) {
                  throw StandardException.newException("0P000", new Object[]{var4});
               }

               if (!var2.roleIsSettable(var1, var4)) {
                  throw StandardException.newException("0P000.S.1", new Object[]{var4});
               }
            }
         } finally {
            var2.userCommit();
         }

         var2.setCurrentRole(var1, var14 != null ? var4 : null);
      }
   }
}
