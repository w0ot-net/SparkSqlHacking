package org.apache.derby.impl.sql.execute;

import java.util.List;
import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.catalog.types.SynonymAliasInfo;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDescriptorGenerator;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

class CreateAliasConstantAction extends DDLConstantAction {
   private final String aliasName;
   private final String schemaName;
   private final String javaClassName;
   private final char aliasType;
   private final char nameSpace;
   private final AliasInfo aliasInfo;

   CreateAliasConstantAction(String var1, String var2, String var3, AliasInfo var4, char var5) {
      this.aliasName = var1;
      this.schemaName = var2;
      this.javaClassName = var3;
      this.aliasInfo = var4;
      this.aliasType = var5;
      switch (var5) {
         case 'A' -> this.nameSpace = 'A';
         case 'F' -> this.nameSpace = 'F';
         case 'G' -> this.nameSpace = 'G';
         case 'P' -> this.nameSpace = 'P';
         case 'S' -> this.nameSpace = 'S';
         default -> this.nameSpace = 0;
      }

   }

   public String toString() {
      String var1 = null;
      switch (this.aliasType) {
         case 'A' -> var1 = "CREATE TYPE ";
         case 'F' -> var1 = "CREATE FUNCTION ";
         case 'G' -> var1 = "CREATE DERBY AGGREGATE ";
         case 'P' -> var1 = "CREATE PROCEDURE ";
         case 'S' -> var1 = "CREATE SYNONYM ";
      }

      return var1 + this.aliasName;
   }

   public void executeConstantAction(Activation var1) throws StandardException {
      LanguageConnectionContext var2 = var1.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      TransactionController var4 = var2.getTransactionExecute();
      var3.startWriting(var2);
      SchemaDescriptor var5 = DDLConstantAction.getSchemaDescriptorForCreate(var3, var1, this.schemaName);
      UUID var6 = var3.getUUIDFactory().createUUID();
      AliasDescriptor var7 = new AliasDescriptor(var3, var6, this.aliasName, var5.getUUID(), this.javaClassName, this.aliasType, this.nameSpace, false, this.aliasInfo, (String)null);
      switch (this.aliasType) {
         case 'A':
            AliasDescriptor var18 = var3.getAliasDescriptor(var5.getUUID().toString(), this.aliasName, this.nameSpace);
            if (var18 != null) {
               throw StandardException.newException("X0Y68.S", new Object[]{var7.getDescriptorType(), this.aliasName});
            }
            break;
         case 'F':
            this.vetRoutine(var3, var5, var7);
            int var19 = ((RoutineAliasInfo)this.aliasInfo).getParameterCount();
            if (var19 == 1) {
               AliasDescriptor var21 = var3.getAliasDescriptor(var5.getUUID().toString(), this.aliasName, 'G');
               if (var21 != null) {
                  throw StandardException.newException("X0Y87.S", new Object[]{this.schemaName, this.aliasName});
               }
            }
            break;
         case 'G':
            AliasDescriptor var8 = var3.getAliasDescriptor(var5.getUUID().toString(), this.aliasName, this.nameSpace);
            if (var8 != null) {
               throw StandardException.newException("X0Y68.S", new Object[]{var7.getDescriptorType(), this.aliasName});
            }

            List var9 = var3.getRoutineList(var5.getUUID().toString(), this.aliasName, 'F');

            for(int var10 = 0; var10 < var9.size(); ++var10) {
               AliasDescriptor var11 = (AliasDescriptor)var9.get(var10);
               RoutineAliasInfo var20 = (RoutineAliasInfo)var11.getAliasInfo();
               if (var20.getParameterCount() == 1) {
                  throw StandardException.newException("X0Y87.S", new Object[]{this.schemaName, this.aliasName});
               }
            }
            break;
         case 'P':
            this.vetRoutine(var3, var5, var7);
            break;
         case 'S':
            TableDescriptor var12 = var3.getTableDescriptor(this.aliasName, var5, var4);
            if (var12 != null) {
               throw StandardException.newException("X0Y68.S", new Object[]{var12.getDescriptorType(), var12.getDescriptorName()});
            }

            String var13 = ((SynonymAliasInfo)this.aliasInfo).getSynonymTable();
            String var14 = ((SynonymAliasInfo)this.aliasInfo).getSynonymSchema();

            SchemaDescriptor var15;
            while(true) {
               var15 = var3.getSchemaDescriptor(var14, var4, false);
               if (var15 == null) {
                  break;
               }

               AliasDescriptor var16 = var3.getAliasDescriptor(var15.getUUID().toString(), var13, this.nameSpace);
               if (var16 == null) {
                  break;
               }

               SynonymAliasInfo var17 = (SynonymAliasInfo)var16.getAliasInfo();
               var13 = var17.getSynonymTable();
               var14 = var17.getSynonymSchema();
               if (this.aliasName.equals(var13) && this.schemaName.equals(var14)) {
                  throw StandardException.newException("42916", new Object[]{this.aliasName, ((SynonymAliasInfo)this.aliasInfo).getSynonymTable()});
               }
            }

            if (var15 != null) {
               var12 = var3.getTableDescriptor(var13, var15, var4);
            }

            if (var15 == null || var12 == null) {
               var1.addWarning(StandardException.newWarning("01522", new Object[]{this.aliasName, var14 + "." + var13}));
            }

            DataDescriptorGenerator var23 = var3.getDataDescriptorGenerator();
            TableDescriptor var22 = var23.newTableDescriptor(this.aliasName, var5, 4, 'R');
            var3.addDescriptor(var22, var5, 1, false, var4);
      }

      var3.addDescriptor(var7, (TupleDescriptor)null, 7, false, var4);
      this.adjustUDTDependencies(var2, var3, var7, true);
   }

   private void vetRoutine(DataDictionary var1, SchemaDescriptor var2, AliasDescriptor var3) throws StandardException {
      List var4 = var1.getRoutineList(var2.getUUID().toString(), this.aliasName, this.aliasType);

      for(int var5 = var4.size() - 1; var5 >= 0; --var5) {
         AliasDescriptor var6 = (AliasDescriptor)var4.get(var5);
         RoutineAliasInfo var7 = (RoutineAliasInfo)var6.getAliasInfo();
         int var8 = var7.getParameterCount();
         if (var8 == ((RoutineAliasInfo)this.aliasInfo).getParameterCount()) {
            throw StandardException.newException("X0Y68.S", new Object[]{var3.getDescriptorType(), this.aliasName});
         }
      }

   }
}
