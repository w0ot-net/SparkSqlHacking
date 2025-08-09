package org.apache.derby.impl.sql.execute;

import java.util.Iterator;
import java.util.List;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.RoutinePermsDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RoutinePrivilegeInfo extends PrivilegeInfo {
   private AliasDescriptor aliasDescriptor;

   public RoutinePrivilegeInfo(AliasDescriptor var1) {
      this.aliasDescriptor = var1;
   }

   public void executeGrantRevoke(Activation var1, boolean var2, List var3) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      String var6 = var4.getCurrentUserId(var1);
      TransactionController var7 = var4.getTransactionExecute();
      this.checkOwnership(var6, this.aliasDescriptor, var5.getSchemaDescriptor(this.aliasDescriptor.getSchemaUUID(), var7), var5);
      DataDescriptorGenerator var8 = var5.getDataDescriptorGenerator();
      RoutinePermsDescriptor var9 = var8.newRoutinePermsDescriptor(this.aliasDescriptor, var6);
      var5.startWriting(var4);

      boolean var11;
      String var12;
      for(Iterator var10 = var3.iterator(); var10.hasNext(); this.addWarningIfPrivilegeNotRevoked(var1, var2, var11, var12)) {
         var11 = false;
         var12 = (String)var10.next();
         if (var5.addRemovePermissionsDescriptor(var2, var9, var12, var7)) {
            var11 = true;
            var5.getDependencyManager().invalidateFor(var9, 45, var4);
            var5.getDependencyManager().invalidateFor(this.aliasDescriptor, 23, var4);
         }
      }

   }
}
