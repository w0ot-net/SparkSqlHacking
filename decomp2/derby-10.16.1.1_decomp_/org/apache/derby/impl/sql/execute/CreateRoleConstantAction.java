package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.jdbc.AuthenticationService;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.impl.jdbc.authentication.BasicAuthenticationServiceImpl;
import org.apache.derby.shared.common.error.StandardException;

class CreateRoleConstantAction extends DDLConstantAction {
   private String roleName;

   public CreateRoleConstantAction(String var1) {
      this.roleName = var1;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      DataDescriptorGenerator var5 = var3.getDataDescriptorGenerator();
      if (this.roleName.equals("PUBLIC")) {
         throw StandardException.newException("4251B", new Object[0]);
      } else {
         String var6 = var2.getCurrentUserId(var1);
         var3.startWriting(var2);
         RoleGrantDescriptor var7 = var3.getRoleDefinitionDescriptor(this.roleName);
         if (var7 != null) {
            throw StandardException.newException("X0Y68.S", new Object[]{var7.getDescriptorType(), this.roleName});
         } else if (this.knownUser(this.roleName, var6, var2, var3, var4)) {
            throw StandardException.newException("X0Y68.S", new Object[]{"User", this.roleName});
         } else {
            var7 = var5.newRoleGrantDescriptor(var3.getUUIDFactory().createUUID(), this.roleName, var6, "_SYSTEM", true, true);
            var3.addDescriptor(var7, (TupleDescriptor)null, 19, false, var4);
         }
      }
   }

   public String toString() {
      return "CREATE ROLE " + this.roleName;
   }

   private boolean knownUser(String var1, String var2, LanguageConnectionContext var3, DataDictionary var4, TransactionController var5) throws StandardException {
      AuthenticationService var6 = var3.getDatabase().getAuthenticationService();
      if (var2.equals(var1)) {
         return true;
      } else if (var6 instanceof BasicAuthenticationServiceImpl && PropertyUtil.existsBuiltinUser(var5, var1)) {
         return true;
      } else if (var4.existsGrantToAuthid(var1, var5)) {
         return true;
      } else {
         return var4.existsSchemaOwnedBy(var1, var5);
      }
   }
}
