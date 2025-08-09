package org.apache.derby.impl.sql.compile;

import java.util.HashMap;
import java.util.List;
import org.apache.derby.catalog.TypeDescriptor;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.sql.compile.Visitor;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.PrivilegedSQLObject;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.impl.sql.execute.GenericPrivilegeInfo;
import org.apache.derby.impl.sql.execute.PrivilegeInfo;
import org.apache.derby.shared.common.error.StandardException;

class PrivilegeNode extends QueryTreeNode {
   public static final int TABLE_PRIVILEGES = 0;
   public static final int ROUTINE_PRIVILEGES = 1;
   public static final int SEQUENCE_PRIVILEGES = 2;
   public static final int UDT_PRIVILEGES = 3;
   public static final int AGGREGATE_PRIVILEGES = 4;
   private int objectType;
   private TableName objectName;
   private TablePrivilegesNode specificPrivileges;
   private RoutineDesignator routineDesignator;
   private String privilege;
   private boolean restrict;
   private Provider dependencyProvider;

   PrivilegeNode(int var1, Object var2, TablePrivilegesNode var3, ContextManager var4) throws StandardException {
      super(var4);
      this.objectType = var1;
      switch (this.objectType) {
         case 0:
            this.objectName = (TableName)var2;
            this.specificPrivileges = var3;
            break;
         case 1:
            this.routineDesignator = (RoutineDesignator)var2;
            this.objectName = this.routineDesignator.name;
            break;
         default:
            throw this.unimplementedFeature();
      }

   }

   PrivilegeNode(int var1, TableName var2, String var3, boolean var4, ContextManager var5) {
      super(var5);
      this.objectType = var1;
      this.objectName = var2;
      this.privilege = var3;
      this.restrict = var4;
   }

   public QueryTreeNode bind(HashMap var1, List var2, boolean var3) throws StandardException {
      SchemaDescriptor var4 = this.getSchemaDescriptor(this.objectName.getSchemaName(), true);
      this.objectName.setSchemaName(var4.getSchemaName());
      if (var2.contains(var4.getAuthorizationId())) {
         throw StandardException.newException("42509", new Object[]{this.objectName.getFullTableName()});
      } else {
         switch (this.objectType) {
            case 0:
               if (var4.isSystemSchema()) {
                  throw StandardException.newException("42509", new Object[]{this.objectName.getFullTableName()});
               }

               TableDescriptor var5 = this.getTableDescriptor(this.objectName.getTableName(), var4);
               if (var5 == null) {
                  throw StandardException.newException("42X05", new Object[]{this.objectName});
               }

               if (isSessionSchema(var4.getSchemaName())) {
                  throw StandardException.newException("XCL51.S", new Object[0]);
               }

               if (var5.getTableType() != 0 && var5.getTableType() != 2) {
                  throw StandardException.newException("42509", new Object[]{this.objectName.getFullTableName()});
               }

               this.specificPrivileges.bind(var5, var3);
               this.dependencyProvider = var5;
               break;
            case 1:
               if (!var4.isSchemaWithGrantableRoutines()) {
                  throw StandardException.newException("42509", new Object[]{this.objectName.getFullTableName()});
               }

               AliasDescriptor var6 = null;
               List var7 = this.getDataDictionary().getRoutineList(var4.getUUID().toString(), this.objectName.getTableName(), (char)(this.routineDesignator.isFunction ? 'F' : 'P'));
               if (this.routineDesignator.paramTypeList == null) {
                  if (var7.size() > 1) {
                     throw StandardException.newException(this.routineDesignator.isFunction ? "42X46" : "42X47", new Object[]{this.objectName.getFullTableName()});
                  }

                  if (var7.size() != 1) {
                     if (this.routineDesignator.isFunction) {
                        throw StandardException.newException("42Y03.S.2", new Object[]{this.objectName.getFullTableName()});
                     }

                     throw StandardException.newException("42Y03.S.1", new Object[]{this.objectName.getFullTableName()});
                  }

                  var6 = (AliasDescriptor)var7.get(0);
               } else {
                  boolean var8 = false;

                  for(int var9 = var7.size() - 1; !var8 && var9 >= 0; --var9) {
                     var6 = (AliasDescriptor)var7.get(var9);
                     RoutineAliasInfo var10 = (RoutineAliasInfo)var6.getAliasInfo();
                     int var11 = var10.getParameterCount();
                     if (var11 == this.routineDesignator.paramTypeList.size()) {
                        TypeDescriptor[] var12 = var10.getParameterTypes();
                        var8 = true;

                        for(int var13 = 0; var13 < var11; ++var13) {
                           if (!var12[var13].equals(this.routineDesignator.paramTypeList.get(var13))) {
                              var8 = false;
                              break;
                           }
                        }
                     }
                  }

                  if (!var8) {
                     StringBuilder var14 = new StringBuilder(this.objectName.getFullTableName());
                     var14.append("(");

                     for(int var15 = 0; var15 < this.routineDesignator.paramTypeList.size(); ++var15) {
                        if (var15 > 0) {
                           var14.append(",");
                        }

                        var14.append(((TypeDescriptor)this.routineDesignator.paramTypeList.get(var15)).toString());
                     }

                     throw StandardException.newException("42Y03.S.0", new Object[]{var14.toString()});
                  }
               }

               this.routineDesignator.setAliasDescriptor(var6);
               this.dependencyProvider = var6;
               break;
            case 2:
               this.dependencyProvider = this.getDataDictionary().getSequenceDescriptor(var4, this.objectName.getTableName());
               if (this.dependencyProvider == null) {
                  throw StandardException.newException("42X94", new Object[]{"SEQUENCE", this.objectName.getFullTableName()});
               }
               break;
            case 3:
               this.dependencyProvider = this.getDataDictionary().getAliasDescriptor(var4.getUUID().toString(), this.objectName.getTableName(), 'A');
               if (this.dependencyProvider == null) {
                  throw StandardException.newException("42X94", new Object[]{"TYPE", this.objectName.getFullTableName()});
               }
               break;
            case 4:
               this.dependencyProvider = this.getDataDictionary().getAliasDescriptor(var4.getUUID().toString(), this.objectName.getTableName(), 'G');
               if (this.dependencyProvider == null) {
                  throw StandardException.newException("42X94", new Object[]{"DERBY AGGREGATE", this.objectName.getFullTableName()});
               }
               break;
            default:
               throw this.unimplementedFeature();
         }

         if (this.dependencyProvider != null && var1.get(this.dependencyProvider) == null) {
            this.getCompilerContext().createDependency(this.dependencyProvider);
            var1.put(this.dependencyProvider, this.dependencyProvider);
         }

         return this;
      }
   }

   PrivilegeInfo makePrivilegeInfo() throws StandardException {
      switch (this.objectType) {
         case 0:
            return this.specificPrivileges.makePrivilegeInfo();
         case 1:
            return this.routineDesignator.makePrivilegeInfo();
         case 2:
         case 3:
         case 4:
            return new GenericPrivilegeInfo((PrivilegedSQLObject)this.dependencyProvider, this.privilege, this.restrict);
         default:
            throw this.unimplementedFeature();
      }
   }

   private StandardException unimplementedFeature() {
      return StandardException.newException("XSCB3.S", new Object[0]);
   }

   void acceptChildren(Visitor var1) throws StandardException {
      super.acceptChildren(var1);
      if (this.objectName != null) {
         this.objectName = (TableName)this.objectName.accept(var1);
      }

   }
}
