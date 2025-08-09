package org.apache.derby.impl.sql.execute;

import java.util.Iterator;
import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class RevokeRoleConstantAction extends DDLConstantAction {
   private List roleNames;
   private List grantees;
   private final boolean withAdminOption = false;

   public RevokeRoleConstantAction(List var1, List var2) {
      this.roleNames = var1;
      this.grantees = var2;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      String var5 = var2.getCurrentUserId(var1);
      var3.startWriting(var2);

      for(String var7 : this.roleNames) {
         if (var7.equals("PUBLIC")) {
            throw StandardException.newException("4251B", new Object[0]);
         }

         for(String var9 : this.grantees) {
            RoleGrantDescriptor var10 = var3.getRoleDefinitionDescriptor(var7);
            if (var10 == null) {
               throw StandardException.newException("0P000", new Object[]{var7});
            }

            if (!var5.equals(var2.getDataDictionary().getAuthorizationDatabaseOwner())) {
               throw StandardException.newException("4251A", new Object[]{"REVOKE role"});
            }

            RoleGrantDescriptor var11 = var3.getRoleGrantDescriptor(var7, var9, var5);
            if (var11 != null) {
            }

            if (var11 != null) {
               RoleClosureIterator var12 = var3.createRoleClosureIterator(var1.getTransactionController(), var7, false);

               String var13;
               while((var13 = var12.next()) != null) {
                  var10 = var3.getRoleDefinitionDescriptor(var13);
                  var3.getDependencyManager().invalidateFor(var10, 47, var2);
               }

               var11.drop(var2);
            } else {
               var1.addWarning(StandardException.newWarning("01007", new Object[]{var7, var9}));
            }
         }
      }

   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();

      for(Iterator var2 = this.roleNames.iterator(); var2.hasNext(); var1.append(var2.next().toString())) {
         if (var1.length() > 0) {
            var1.append(", ");
         }
      }

      StringBuffer var4 = new StringBuffer();

      for(Iterator var3 = this.grantees.iterator(); var3.hasNext(); var4.append(var3.next().toString())) {
         if (var4.length() > 0) {
            var4.append(", ");
         }
      }

      String var10000 = var1.toString();
      return "REVOKE " + var10000 + " FROM: " + var4.toString() + "\n";
   }
}
