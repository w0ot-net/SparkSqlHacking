package org.apache.derby.impl.sql.execute;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.ConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.KeyConstraintDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.shared.common.error.StandardException;

abstract class DDLSingleTableConstantAction extends DDLConstantAction {
   protected UUID tableId;

   DDLSingleTableConstantAction(UUID var1) {
      this.tableId = var1;
   }

   void dropConstraint(ConstraintDescriptor var1, Activation var2, LanguageConnectionContext var3, boolean var4) throws StandardException {
      this.dropConstraint(var1, (TableDescriptor)null, (List)null, var2, var3, var4);
   }

   void dropConstraint(ConstraintDescriptor var1, TableDescriptor var2, Activation var3, LanguageConnectionContext var4, boolean var5) throws StandardException {
      this.dropConstraint(var1, var2, (List)null, var3, var4, var5);
   }

   void dropConstraint(ConstraintDescriptor var1, TableDescriptor var2, List var3, Activation var4, LanguageConnectionContext var5, boolean var6) throws StandardException {
      Properties var7 = null;
      if (var1 instanceof KeyConstraintDescriptor) {
         var7 = new Properties();
         this.loadIndexProperties(var5, ((KeyConstraintDescriptor)var1).getIndexConglomerateDescriptor(var5.getDataDictionary()), var7);
      }

      ConglomerateDescriptor var8 = var1.drop(var5, var6);
      if (var8 != null) {
         if (var2 != null && var2.getUUID().equals(var1.getTableDescriptor().getUUID())) {
            if (var3 != null) {
               var3.add(this.getConglomReplacementAction(var8, var1.getTableDescriptor(), var7));
            }
         } else {
            this.executeConglomReplacement(this.getConglomReplacementAction(var8, var1.getTableDescriptor(), var7), var4);
         }

      }
   }

   void dropConglomerate(ConglomerateDescriptor var1, TableDescriptor var2, Activation var3, LanguageConnectionContext var4) throws StandardException {
      this.dropConglomerate(var1, var2, false, (List)null, var3, var4);
   }

   void dropConglomerate(ConglomerateDescriptor var1, TableDescriptor var2, boolean var3, List var4, Activation var5, LanguageConnectionContext var6) throws StandardException {
      Properties var7 = new Properties();
      this.loadIndexProperties(var6, var1, var7);
      ConglomerateDescriptor var8 = var1.drop(var6, var2);
      if (var8 != null) {
         if (var3) {
            if (var4 != null) {
               var4.add(this.getConglomReplacementAction(var8, var2, var7));
            }
         } else {
            this.executeConglomReplacement(this.getConglomReplacementAction(var8, var2, var7), var5);
         }

      }
   }

   void recreateUniqueConstraintBackingIndexAsUniqueWhenNotNull(ConglomerateDescriptor var1, TableDescriptor var2, Activation var3, LanguageConnectionContext var4) throws StandardException {
      Properties var5 = new Properties();
      this.loadIndexProperties(var4, var1, var5);
      ArrayList var6 = new ArrayList();
      this.dropConglomerate(var1, var2, false, var6, var3, var4);
      String[] var7 = var1.getColumnNames();
      if (var7 == null) {
         int[] var8 = var1.getIndexDescriptor().baseColumnPositions();
         var7 = new String[var8.length];

         for(int var9 = 0; var9 < var7.length; ++var9) {
            var7[var9] = var2.getColumnDescriptor(var8[var9]).getColumnName();
         }
      }

      CreateIndexConstantAction var10 = new CreateIndexConstantAction(false, false, true, var1.getIndexDescriptor().hasDeferrableChecking(), false, 3, var1.getIndexDescriptor().indexType(), var2.getSchemaName(), var1.getConglomerateName(), var2.getName(), var2.getUUID(), var7, var1.getIndexDescriptor().isAscending(), true, var1.getUUID(), var5);
      var10.executeConstantAction(var3);
   }

   private void loadIndexProperties(LanguageConnectionContext var1, ConglomerateDescriptor var2, Properties var3) throws StandardException {
      ConglomerateController var4 = var1.getTransactionExecute().openConglomerate(var2.getConglomerateNumber(), false, 4, 7, 5);
      var4.getInternalTablePropertySet(var3);
      var4.close();
   }

   ConstantAction getConglomReplacementAction(ConglomerateDescriptor var1, TableDescriptor var2, Properties var3) throws StandardException {
      return new CreateIndexConstantAction(var1, var2, var3);
   }

   void executeConglomReplacement(ConstantAction var1, Activation var2) throws StandardException {
      CreateIndexConstantAction var3 = (CreateIndexConstantAction)var1;
      LanguageConnectionContext var4 = var2.getLanguageConnectionContext();
      DataDictionary var5 = var4.getDataDictionary();
      var3.executeConstantAction(var2);
      ConglomerateDescriptor[] var6 = var5.getConglomerateDescriptors(var3.getReplacedConglomNumber());
      var5.updateConglomerateDescriptor(var6, var3.getCreatedConglomNumber(), var4.getTransactionExecute());
   }
}
