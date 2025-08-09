package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class AlterConstraintConstantAction extends ConstraintConstantAction {
   private String constraintSchemaName;
   boolean[] characteristics;

   AlterConstraintConstantAction(String var1, String var2, boolean[] var3, String var4, UUID var5, String var6, IndexConstantAction var7) {
      super(var1, 5, var4, var5, var6, var7);
      this.constraintSchemaName = var2;
      this.characteristics = (boolean[])(([Z)var3).clone();
   }

   public String toString() {
      String var1 = this.constraintSchemaName == null ? this.schemaName : this.constraintSchemaName;
      return "ALTER CONSTRAINT " + var1 + "." + this.constraintName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      DependencyManager var4 = var3.getDependencyManager();
      TransactionController var5 = var2.getTransactionExecute();
      var3.startWriting(var2);
      TableDescriptor var6 = var3.getTableDescriptor(this.tableId);
      if (var6 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
      } else {
         SchemaDescriptor var7 = var6.getSchemaDescriptor();
         SchemaDescriptor var8 = this.constraintSchemaName == null ? var7 : var3.getSchemaDescriptor(this.constraintSchemaName, var5, true);
         ConstraintDescriptor var9 = var3.getConstraintDescriptorByName(var6, var8, this.constraintName, true);
         if (var9 == null) {
            Object[] var10001 = new Object[2];
            String var10004 = var8.getSchemaName();
            var10001[0] = var10004 + "." + this.constraintName;
            var10001[1] = var6.getQualifiedName();
            throw StandardException.newException("42X86", var10001);
         } else {
            if (!this.characteristics[2]) {
               var3.checkVersion(230, "DEFERRED CONSTRAINTS");
               if ((this.constraintType == 6 || this.constraintType == 1 || !this.characteristics[2]) && !PropertyUtil.getSystemProperty("derby.constraintsTesting", "false").equals("true")) {
                  throw StandardException.newException("0A000.S", new Object[]{"non-default enforcement"});
               }
            }

            var9.setEnforced(this.characteristics[2]);
            int[] var10 = new int[]{6};
            var3.updateConstraintDescriptor(var9, var9.getUUID(), var10, var5);
         }
      }
   }
}
