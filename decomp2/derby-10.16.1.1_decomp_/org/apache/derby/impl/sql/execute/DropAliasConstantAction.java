package org.apache.derby.impl.sql.execute;

import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class DropAliasConstantAction extends DDLConstantAction {
   private SchemaDescriptor sd;
   private final String aliasName;
   private final char nameSpace;

   DropAliasConstantAction(SchemaDescriptor var1, String var2, char var3) {
      this.sd = var1;
      this.aliasName = var2;
      this.nameSpace = var3;
   }

   public String toString() {
      return "DROP ALIAS " + this.aliasName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      var3.startWriting(var2);
      AliasDescriptor var4 = var3.getAliasDescriptor(this.sd.getUUID().toString(), this.aliasName, this.nameSpace);
      if (var4 == null) {
         throw StandardException.newException("42X94", new Object[]{AliasDescriptor.getAliasType(this.nameSpace), this.aliasName});
      } else {
         this.adjustUDTDependencies(var2, var3, var4, false);
         var4.drop(var2);
      }
   }
}
