package org.apache.derby.impl.sql.execute;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.AggregateAliasInfo;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColPermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptorList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.sql.dictionary.DependencyDescriptor;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.RoleClosureIterator;
import org.apache.derby.iapi.sql.dictionary.RoleGrantDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.StatementColumnPermission;
import org.apache.derby.iapi.sql.dictionary.StatementGenericPermission;
import org.apache.derby.iapi.sql.dictionary.StatementPermission;
import org.apache.derby.iapi.sql.dictionary.StatementRolePermission;
import org.apache.derby.iapi.sql.dictionary.StatementRoutinePermission;
import org.apache.derby.iapi.sql.dictionary.StatementSchemaPermission;
import org.apache.derby.iapi.sql.dictionary.StatementTablePermission;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.execute.ConstantAction;
import org.apache.derby.iapi.store.access.ConglomerateController;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

abstract class DDLConstantAction implements ConstantAction {
   static SchemaDescriptor getAndCheckSchemaDescriptor(DataDictionary var0, UUID var1, String var2) throws StandardException {
      SchemaDescriptor var3 = var0.getSchemaDescriptor(var1, (TransactionController)null);
      return var3;
   }

   static SchemaDescriptor getSchemaDescriptorForCreate(DataDictionary var0, Activation var1, String var2) throws StandardException {
      TransactionController var3 = var1.getLanguageConnectionContext().getTransactionExecute();
      SchemaDescriptor var4 = var0.getSchemaDescriptor(var2, var3, false);
      if (var4 == null || var4.getUUID() == null) {
         CreateSchemaConstantAction var5 = new CreateSchemaConstantAction(var2, (String)null);
         if (var1.getLanguageConnectionContext().isInitialDefaultSchema(var2)) {
            executeCAPreferSubTrans(var5, var3, var1);
         } else {
            try {
               var5.executeConstantAction(var1);
            } catch (StandardException var7) {
               if (!var7.getMessageId().equals("X0Y68.S")) {
                  throw var7;
               }
            }
         }

         var4 = var0.getSchemaDescriptor(var2, var3, true);
      }

      return var4;
   }

   private static void executeCAPreferSubTrans(CreateSchemaConstantAction var0, TransactionController var1, Activation var2) throws StandardException {
      TransactionController var4 = null;

      TransactionController var3;
      try {
         var4 = var1.startNestedUserTransaction(false, true);
         var3 = var4;
      } catch (StandardException var6) {
         var3 = var1;
      }

      while(true) {
         try {
            var0.executeConstantAction(var2, var3);
            break;
         } catch (StandardException var7) {
            if (var7.isLockTimeout()) {
               if (!var7.getMessageId().equals("40XL1.T.1") && var3 == var4) {
                  var3 = var1;
                  var4.destroy();
                  continue;
               }
            } else if (var7.getMessageId().equals("X0Y68.S")) {
               break;
            }

            if (var3 == var4) {
               var4.destroy();
            }

            throw var7;
         }
      }

      if (var3 == var4) {
         var4.commit();
         var4.destroy();
      }

   }

   final void lockTableForDDL(TransactionController var1, long var2, boolean var4) throws StandardException {
      ConglomerateController var5 = var1.openConglomerate(var2, false, var4 ? 68 : 64, 7, 5);
      var5.close();
   }

   protected String constructToString(String var1, String var2) {
      return var1 + var2;
   }

   protected void storeConstraintDependenciesOnPrivileges(Activation var1, Dependent var2, UUID var3, ProviderInfo[] var4) throws StandardException {
      LanguageConnectionContext var5 = var1.getLanguageConnectionContext();
      DataDictionary var6 = var5.getDataDictionary();
      DependencyManager var7 = var6.getDependencyManager();
      String var8 = var5.getCurrentUserId(var1);
      SettableBoolean var9 = new SettableBoolean();
      if (!var8.equals(var6.getAuthorizationDatabaseOwner())) {
         List var11 = var1.getPreparedStatement().getRequiredPermissionsList();
         if (var11 != null && !var11.isEmpty()) {
            for(StatementPermission var13 : var11) {
               if (var13 instanceof StatementTablePermission) {
                  StatementTablePermission var14 = (StatementTablePermission)var13;
                  if (var14.getPrivType() != 2 || !var14.getTableUUID().equals(var3)) {
                     continue;
                  }
               } else {
                  if (var13 instanceof StatementSchemaPermission || var13 instanceof StatementRolePermission || var13 instanceof StatementGenericPermission) {
                     continue;
                  }

                  StatementRoutinePermission var17 = (StatementRoutinePermission)var13;
                  if (!this.inProviderSet(var4, var17.getRoutineUUID())) {
                     continue;
                  }
               }

               PermissionsDescriptor var10 = var13.getPermissionDescriptor(var8, var6);
               if (var10 == null) {
                  var10 = var13.getPermissionDescriptor("PUBLIC", var6);
                  boolean var18 = false;
                  if (var10 == null || var10 instanceof ColPermsDescriptor && !((StatementColumnPermission)var13).allColumnsCoveredByUserOrPUBLIC(var8, var6)) {
                     var18 = true;
                     var10 = findRoleUsage(var1, var13);
                  }

                  if (!var10.checkOwner(var8)) {
                     var7.addDependency(var2, var10, var5.getContextManager());
                     if (var18) {
                        trackRoleDependency(var1, var2, var9);
                     }
                  }
               } else if (!var10.checkOwner(var8)) {
                  var7.addDependency(var2, var10, var5.getContextManager());
                  if (var10 instanceof ColPermsDescriptor) {
                     StatementColumnPermission var19 = (StatementColumnPermission)var13;
                     var10 = var19.getPUBLIClevelColPermsDescriptor(var8, var6);
                     if (var10 != null && var10.getObjectID() != null) {
                        var7.addDependency(var2, var10, var5.getContextManager());
                     }

                     if (!var19.allColumnsCoveredByUserOrPUBLIC(var8, var6)) {
                        trackRoleDependency(var1, var2, var9);
                     }
                  }
               }

               if (!(var13 instanceof StatementRoutinePermission)) {
                  break;
               }
            }
         }
      }

   }

   private static PermissionsDescriptor findRoleUsage(Activation var0, StatementPermission var1) throws StandardException {
      LanguageConnectionContext var2 = var0.getLanguageConnectionContext();
      DataDictionary var3 = var2.getDataDictionary();
      String var4 = var2.getCurrentRoleId(var0);
      PermissionsDescriptor var5 = null;

      String var7;
      for(RoleClosureIterator var6 = var3.createRoleClosureIterator(var0.getTransactionController(), var4, true); var5 == null && (var7 = var6.next()) != null; var5 = var1.getPermissionDescriptor(var7, var3)) {
      }

      return var5;
   }

   private static void trackRoleDependency(Activation var0, Dependent var1, SettableBoolean var2) throws StandardException {
      if (!var2.get()) {
         LanguageConnectionContext var3 = var0.getLanguageConnectionContext();
         DataDictionary var4 = var3.getDataDictionary();
         DependencyManager var5 = var4.getDependencyManager();
         String var6 = var3.getCurrentRoleId(var0);
         RoleGrantDescriptor var7 = var4.getRoleDefinitionDescriptor(var6);
         var5.addDependency(var1, var7, var3.getContextManager());
         var2.set(true);
      }

   }

   protected void storeViewTriggerDependenciesOnPrivileges(Activation var1, Dependent var2) throws StandardException {
      LanguageConnectionContext var3 = var1.getLanguageConnectionContext();
      DataDictionary var4 = var3.getDataDictionary();
      DependencyManager var5 = var4.getDependencyManager();
      String var6 = var4.getAuthorizationDatabaseOwner();
      String var7 = var3.getCurrentUserId(var1);
      SettableBoolean var8 = new SettableBoolean();
      if (!var7.equals(var6)) {
         List var10 = var1.getPreparedStatement().getRequiredPermissionsList();
         if (var10 != null && !var10.isEmpty()) {
            for(StatementPermission var12 : var10) {
               if (!(var12 instanceof StatementSchemaPermission) && !(var12 instanceof StatementRolePermission)) {
                  PermissionsDescriptor var9 = var12.getPermissionDescriptor(var7, var4);
                  if (var9 == null) {
                     var9 = var12.getPermissionDescriptor("PUBLIC", var4);
                     boolean var13 = false;
                     if (var9 == null || var9 instanceof ColPermsDescriptor && !((StatementColumnPermission)var12).allColumnsCoveredByUserOrPUBLIC(var7, var4)) {
                        var13 = true;
                        var9 = findRoleUsage(var1, var12);
                     }

                     if (!var9.checkOwner(var7)) {
                        var5.addDependency(var2, var9, var3.getContextManager());
                        if (var13) {
                           trackRoleDependency(var1, var2, var8);
                        }
                     }
                  } else if (!var9.checkOwner(var7)) {
                     var5.addDependency(var2, var9, var3.getContextManager());
                     if (var9 instanceof ColPermsDescriptor) {
                        StatementColumnPermission var16 = (StatementColumnPermission)var12;
                        var9 = var16.getPUBLIClevelColPermsDescriptor(var7, var4);
                        if (var9 != null && var9.getObjectID() != null) {
                           var5.addDependency(var2, var9, var3.getContextManager());
                        }

                        if (!var16.allColumnsCoveredByUserOrPUBLIC(var7, var4)) {
                           trackRoleDependency(var1, var2, var8);
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private boolean inProviderSet(ProviderInfo[] var1, UUID var2) {
      if (var1 == null) {
         return false;
      } else {
         for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3].getObjectId().equals(var2)) {
               return true;
            }
         }

         return false;
      }
   }

   protected void addColumnDependencies(LanguageConnectionContext var1, DataDictionary var2, TableDescriptor var3, ColumnInfo var4) throws StandardException {
      ProviderInfo[] var5 = var4.providers;
      if (var5 != null) {
         DependencyManager var6 = var2.getDependencyManager();
         ContextManager var7 = var1.getContextManager();
         int var8 = var5.length;
         ColumnDescriptor var9 = var3.getColumnDescriptor(var4.name);
         DefaultDescriptor var10 = var9.getDefaultDescriptor(var2);

         for(int var11 = 0; var11 < var8; ++var11) {
            ProviderInfo var12 = var5[var11];
            DependableFinder var13 = var12.getDependableFinder();
            UUID var14 = var12.getObjectId();
            Provider var15 = (Provider)var13.getDependable(var2, var14);
            var6.addDependency(var10, var15, var7);
         }
      }

   }

   protected void adjustUDTDependencies(LanguageConnectionContext var1, DataDictionary var2, TableDescriptor var3, ColumnInfo[] var4, boolean var5) throws StandardException {
      if (var5 || var4 != null) {
         TransactionController var6 = var1.getTransactionExecute();
         int var7 = var4 == null ? 0 : var4.length;
         HashMap var8 = new HashMap();
         HashMap var9 = new HashMap();
         HashSet var10 = new HashSet();
         HashSet var11 = new HashSet();

         for(int var12 = 0; var12 < var7; ++var12) {
            ColumnInfo var13 = var4[var12];
            AliasDescriptor var14 = var2.getAliasDescriptorForUDT(var6, var4[var12].dataType);
            if (var14 != null) {
               String var15 = var14.getObjectID().toString();
               if (var13.action == 0) {
                  var10.add(var13.name);
                  if (var8.get(var15) == null) {
                     var8.put(var15, var14);
                  }
               } else if (var13.action == 1) {
                  var11.add(var13.name);
                  var9.put(var15, var14);
               }
            }
         }

         if (var5 || !var8.isEmpty() || !var9.isEmpty()) {
            ColumnDescriptorList var18 = var3.getColumnDescriptorList();
            int var19 = var18.size();

            for(int var20 = 0; var20 < var19; ++var20) {
               ColumnDescriptor var21 = var18.elementAt(var20);
               if (!var10.contains(var21.getColumnName()) && !var11.contains(var21.getColumnName())) {
                  AliasDescriptor var16 = var2.getAliasDescriptorForUDT(var6, var21.getType());
                  if (var16 != null) {
                     String var17 = var16.getObjectID().toString();
                     if (var5) {
                        var9.put(var17, var16);
                     } else {
                        if (var8.get(var17) != null) {
                           var8.remove(var17);
                        }

                        if (var9.get(var17) != null) {
                           var9.remove(var17);
                        }
                     }
                  }
               }
            }

            this.adjustUDTDependencies(var1, var2, var3, var8, var9);
         }
      }
   }

   private void adjustUDTDependencies(LanguageConnectionContext var1, DataDictionary var2, Dependent var3, HashMap var4, HashMap var5) throws StandardException {
      if (!var4.isEmpty() || !var5.isEmpty()) {
         TransactionController var6 = var1.getTransactionExecute();
         DependencyManager var7 = var2.getDependencyManager();
         ContextManager var8 = var1.getContextManager();

         for(AliasDescriptor var10 : var4.values()) {
            var7.addDependency(var3, var10, var8);
         }

         for(AliasDescriptor var13 : var5.values()) {
            DependencyDescriptor var11 = new DependencyDescriptor(var3, var13);
            var2.dropStoredDependency(var11, var6);
         }

      }
   }

   protected void adjustUDTDependencies(LanguageConnectionContext var1, DataDictionary var2, AliasDescriptor var3, boolean var4) throws StandardException {
      RoutineAliasInfo var5 = null;
      AggregateAliasInfo var6 = null;
      switch (var3.getAliasType()) {
         case 'F':
         case 'P':
            var5 = (RoutineAliasInfo)var3.getAliasInfo();
            break;
         case 'G':
            var6 = (AggregateAliasInfo)var3.getAliasInfo();
            break;
         default:
            return;
      }

      TransactionController var7 = var1.getTransactionExecute();
      HashMap var8 = new HashMap();
      HashMap var9 = new HashMap();
      HashMap var10 = var4 ? var8 : var9;
      TypeDescriptor var11 = var6 != null ? var6.getReturnType() : var5.getReturnType();
      if (var11 != null) {
         AliasDescriptor var12 = var2.getAliasDescriptorForUDT(var7, DataTypeDescriptor.getType(var11));
         if (var12 != null) {
            var10.put(var12.getObjectID().toString(), var12);
         }
      }

      if (var11 != null && var11.isRowMultiSet()) {
         TypeDescriptor[] var16 = var11.getRowTypes();
         int var13 = var16.length;

         for(int var14 = 0; var14 < var13; ++var14) {
            AliasDescriptor var15 = var2.getAliasDescriptorForUDT(var7, DataTypeDescriptor.getType(var16[var14]));
            if (var15 != null) {
               var10.put(var15.getObjectID().toString(), var15);
            }
         }
      }

      TypeDescriptor[] var17 = var6 != null ? new TypeDescriptor[]{var6.getForType()} : var5.getParameterTypes();
      if (var17 != null) {
         int var18 = var17.length;

         for(int var19 = 0; var19 < var18; ++var19) {
            AliasDescriptor var20 = var2.getAliasDescriptorForUDT(var7, DataTypeDescriptor.getType(var17[var19]));
            if (var20 != null) {
               var10.put(var20.getObjectID().toString(), var20);
            }
         }
      }

      this.adjustUDTDependencies(var1, var2, var3, var8, var9);
   }

   private static class SettableBoolean {
      boolean value = false;

      SettableBoolean() {
      }

      void set(boolean var1) {
         this.value = var1;
      }

      boolean get() {
         return this.value;
      }
   }
}
