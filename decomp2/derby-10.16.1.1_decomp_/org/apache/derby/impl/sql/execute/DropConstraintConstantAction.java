package org.apache.derby.impl.sql.execute;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.ForeignKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.ReferencedKeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class DropConstraintConstantAction extends ConstraintConstantAction {
   private boolean cascade;
   private String constraintSchemaName;
   private int verifyType;

   DropConstraintConstantAction(String var1, String var2, String var3, UUID var4, String var5, IndexConstantAction var6, int var7, int var8) {
      super(var1, 5, var3, var4, var5, var6);
      this.cascade = var7 == 0;
      this.constraintSchemaName = var2;
      this.verifyType = var8;
   }

   public String toString() {
      if (this.constraintName == null) {
         return "DROP PRIMARY KEY";
      } else {
         String var1 = this.constraintSchemaName == null ? this.schemaName : this.constraintSchemaName;
         return "DROP CONSTRAINT " + var1 + "." + this.constraintName;
      }
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      Object var2 = null;
      Object var4 = null;
      LanguageConnectionContext var6 = var1.getLanguageConnectionContext();
      DataDictionary var7 = var6.getDataDictionary();
      DependencyManager var8 = var7.getDependencyManager();
      TransactionController var9 = var6.getTransactionExecute();
      var7.startWriting(var6);
      TableDescriptor var3 = var7.getTableDescriptor(this.tableId);
      if (var3 == null) {
         throw StandardException.newException("X0X05.S", new Object[]{this.tableName});
      } else {
         SchemaDescriptor var10 = var3.getSchemaDescriptor();
         SchemaDescriptor var11 = this.constraintSchemaName == null ? var10 : var7.getSchemaDescriptor(this.constraintSchemaName, var9, true);
         if (this.constraintName == null) {
            var2 = var7.getConstraintDescriptors(var3).getPrimaryKey();
         } else {
            var2 = var7.getConstraintDescriptorByName(var3, var11, this.constraintName, true);
         }

         if (var2 == null) {
            String var19 = this.constraintName == null ? "PRIMARY KEY" : var11.getSchemaName() + "." + this.constraintName;
            throw StandardException.newException("42X86", new Object[]{var19, var3.getQualifiedName()});
         } else {
            switch (this.verifyType) {
               case 3:
                  if (((ConstraintDescriptor)var2).getConstraintType() != this.verifyType) {
                     throw StandardException.newException("42Z9E", new Object[]{this.constraintName, "UNIQUE"});
                  }
                  break;
               case 4:
                  if (((ConstraintDescriptor)var2).getConstraintType() != this.verifyType) {
                     throw StandardException.newException("42Z9E", new Object[]{this.constraintName, "CHECK"});
                  }
               case 5:
               default:
                  break;
               case 6:
                  if (((ConstraintDescriptor)var2).getConstraintType() != this.verifyType) {
                     throw StandardException.newException("42Z9E", new Object[]{this.constraintName, "FOREIGN KEY"});
                  }
            }

            boolean var12 = this.cascade && var2 instanceof ReferencedKeyConstraintDescriptor;
            if (!var12) {
               var8.invalidateFor((Provider)var2, 19, var6);
            }

            this.dropConstraint((ConstraintDescriptor)var2, var1, var6, !var12);
            if (var12) {
               ReferencedKeyConstraintDescriptor var14 = (ReferencedKeyConstraintDescriptor)var2;
               ConstraintDescriptorList var15 = var14.getForeignKeyConstraints(3);
               int var16 = var15.size();

               for(int var17 = 0; var17 < var16; ++var17) {
                  ForeignKeyConstraintDescriptor var13 = (ForeignKeyConstraintDescriptor)var15.elementAt(var17);
                  var8.invalidateFor(var13, 19, var6);
                  this.dropConstraint(var13, var1, var6, true);
               }

               var8.invalidateFor((Provider)var2, 19, var6);
               var8.clearDependencies(var6, (Dependent)var2);
            }

         }
      }
   }
}
