package org.apache.derby.impl.sql.execute;

import java.util.Iterator;
import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class GrantRoleConstantAction extends DDLConstantAction {
   private List roleNames;
   private List grantees;
   private final boolean withAdminOption = false;

   public GrantRoleConstantAction(List var1, List var2) {
      this.roleNames = var1;
      this.grantees = var2;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      DataDescriptorGenerator var5 = var3.getDataDescriptorGenerator();
      String var6 = var2.getCurrentUserId(var1);
      var3.startWriting(var2);

      for(String var8 : this.roleNames) {
         if (var8.equals("PUBLIC")) {
            throw StandardException.newException("4251B", new Object[0]);
         }

         for(String var10 : this.grantees) {
            RoleGrantDescriptor var11 = var3.getRoleDefinitionDescriptor(var8);
            if (var11 == null) {
               throw StandardException.newException("0P000", new Object[]{var8});
            }

            if (!var6.equals(var2.getDataDictionary().getAuthorizationDatabaseOwner())) {
               throw StandardException.newException("4251A", new Object[]{"GRANT role"});
            }

            RoleGrantDescriptor var12 = var3.getRoleGrantDescriptor(var8, var10, var6);
            if (var12 != null) {
            }

            if (var12 == null) {
               RoleGrantDescriptor var13 = var3.getRoleDefinitionDescriptor(var10);
               if (var13 != null) {
                  this.checkCircularity(var8, var10, var6, var4, var3);
               }

               var12 = var5.newRoleGrantDescriptor(var3.getUUIDFactory().createUUID(), var8, var10, var6, false, false);
               var3.addDescriptor(var12, (TupleDescriptor)null, 19, false, var4);
            }
         }
      }

   }

   private void checkCircularity(String var1, String var2, String var3, TransactionController var4, DataDictionary var5) throws StandardException {
      RoleClosureIterator var6 = var5.createRoleClosureIterator(var4, var2, false);

      String var7;
      while((var7 = var6.next()) != null) {
         if (var1.equals(var7)) {
            throw StandardException.newException("4251C", new Object[]{var1, var2});
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
      return "GRANT " + var10000 + " TO: " + var4.toString() + "\n";
   }
}
