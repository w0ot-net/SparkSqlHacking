package org.apache.derby.impl.sql.execute;

import java.util.Iterator;
import java.util.List;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.PermDescriptor;
import org.apache.derby.iapi.sql.dictionary.PrivilegedSQLObject;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class GenericPrivilegeInfo extends PrivilegeInfo {
   private PrivilegedSQLObject _tupleDescriptor;
   private String _privilege;
   private boolean _restrict;

   public GenericPrivilegeInfo(PrivilegedSQLObject var1, String var2, boolean var3) {
      this._tupleDescriptor = var1;
      this._privilege = var2;
      this._restrict = var3;
   }

   public void executeGrantRevoke(Activation var1, boolean var2, List var3) throws StandardException {
      LanguageConnectionContext var4 = var1.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      String var6 = var4.getCurrentUserId(var1);
      TransactionController var7 = var4.getTransactionExecute();
      SchemaDescriptor var8 = this._tupleDescriptor.getSchemaDescriptor();
      UUID var9 = this._tupleDescriptor.getUUID();
      String var10 = this._tupleDescriptor.getObjectTypeName();
      this.checkOwnership(var6, this._tupleDescriptor, var8, var5);
      DataDescriptorGenerator var11 = var5.getDataDescriptorGenerator();
      PermDescriptor var12 = var11.newPermDescriptor((UUID)null, var10, var9, this._privilege, var6, (String)null, false);
      var5.startWriting(var4);

      boolean var14;
      String var15;
      for(Iterator var13 = var3.iterator(); var13.hasNext(); this.addWarningIfPrivilegeNotRevoked(var1, var2, var14, var15)) {
         var14 = false;
         var15 = (String)var13.next();
         if (var5.addRemovePermissionsDescriptor(var2, var12, var15, var7)) {
            var14 = true;
            int var16 = this._restrict ? 45 : 44;
            var5.getDependencyManager().invalidateFor(var12, var16, var4);
            var5.getDependencyManager().invalidateFor(this._tupleDescriptor, var16, var4);
         }
      }

   }
}
