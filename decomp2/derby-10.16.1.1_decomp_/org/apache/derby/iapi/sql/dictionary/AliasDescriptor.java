package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.AliasInfo;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.catalog.types.AggregateAliasInfo;
import org.apache.derby.catalog.types.RoutineAliasInfo;
import org.apache.derby.catalog.types.UDTAliasInfo;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.util.IdUtil;
import org.apache.derby.shared.common.error.StandardException;

public final class AliasDescriptor extends PrivilegedSQLObject implements Provider, Dependent {
   private final UUID aliasID;
   private final String aliasName;
   private final UUID schemaID;
   private final String javaClassName;
   private final char aliasType;
   private final char nameSpace;
   private final boolean systemAlias;
   private final AliasInfo aliasInfo;
   private final String specificName;
   private final SchemaDescriptor schemaDescriptor;

   public AliasDescriptor(DataDictionary var1, UUID var2, String var3, UUID var4, String var5, char var6, char var7, boolean var8, AliasInfo var9, String var10) throws StandardException {
      super(var1);
      this.aliasID = var2;
      this.aliasName = var3;
      this.schemaID = var4;
      this.schemaDescriptor = var1.getSchemaDescriptor(var4, (TransactionController)null);
      this.javaClassName = var5;
      this.aliasType = var6;
      this.nameSpace = var7;
      this.systemAlias = var8;
      this.aliasInfo = var9;
      if (var10 == null) {
         var10 = var1.getSystemSQLName();
      }

      this.specificName = var10;
   }

   public UUID getUUID() {
      return this.aliasID;
   }

   public String getObjectTypeName() {
      if (this.aliasInfo instanceof UDTAliasInfo) {
         return "TYPE";
      } else {
         return this.aliasInfo instanceof AggregateAliasInfo ? "DERBY AGGREGATE" : null;
      }
   }

   public UUID getSchemaUUID() {
      return this.schemaID;
   }

   public final SchemaDescriptor getSchemaDescriptor() {
      return this.schemaDescriptor;
   }

   public final String getName() {
      return this.aliasName;
   }

   public String getSchemaName() {
      return this.schemaDescriptor.getSchemaName();
   }

   public String getQualifiedName() {
      return IdUtil.mkQualifiedName(this.getSchemaName(), this.aliasName);
   }

   public String getJavaClassName() {
      return this.javaClassName;
   }

   public char getAliasType() {
      return this.aliasType;
   }

   public char getNameSpace() {
      return this.nameSpace;
   }

   public boolean getSystemAlias() {
      return this.systemAlias;
   }

   public AliasInfo getAliasInfo() {
      return this.aliasInfo;
   }

   public String toString() {
      return "";
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof AliasDescriptor var2)) {
         return false;
      } else {
         return this.aliasID.equals(var2.getUUID());
      }
   }

   public int hashCode() {
      return this.aliasID.hashCode();
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(136);
   }

   public String getObjectName() {
      return this.aliasName;
   }

   public UUID getObjectID() {
      return this.aliasID;
   }

   public String getClassType() {
      return "Alias";
   }

   public String getDescriptorType() {
      return getAliasType(this.aliasType);
   }

   public static final String getAliasType(char var0) {
      switch (var0) {
         case 'A' -> {
            return "TYPE";
         }
         case 'F' -> {
            return "FUNCTION";
         }
         case 'G' -> {
            return "DERBY AGGREGATE";
         }
         case 'P' -> {
            return "PROCEDURE";
         }
         case 'S' -> {
            return "SYNONYM";
         }
         default -> {
            return null;
         }
      }
   }

   public String getDescriptorName() {
      return this.aliasName;
   }

   public String getSpecificName() {
      return this.specificName;
   }

   public boolean isPersistent() {
      return !this.getSchemaUUID().toString().equals("c013800d-00fb-2642-07ec-000000134f30");
   }

   public boolean isTableFunction() {
      if (this.getAliasType() != 'F') {
         return false;
      } else {
         RoutineAliasInfo var1 = (RoutineAliasInfo)this.getAliasInfo();
         return var1.getReturnType().isRowMultiSet();
      }
   }

   public void drop(LanguageConnectionContext var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      TransactionController var3 = var1.getTransactionExecute();
      DependencyManager var4 = var2.getDependencyManager();
      byte var5 = 0;
      switch (this.getAliasType()) {
         case 'A':
            var5 = 50;
            break;
         case 'F':
         case 'P':
            var5 = 6;
            break;
         case 'G':
            var5 = 51;
            break;
         case 'S':
            var5 = 43;
      }

      var4.invalidateFor(this, var5, var1);
      if (this.getAliasType() == 'S') {
         SchemaDescriptor var6 = var2.getSchemaDescriptor(this.schemaID, var3);
         DataDescriptorGenerator var7 = var2.getDataDescriptorGenerator();
         TableDescriptor var8 = var7.newTableDescriptor(this.aliasName, var6, 4, 'R');
         var2.dropTableDescriptor(var8, var6, var3);
      } else {
         var2.dropAllRoutinePermDescriptors(this.getUUID(), var3);
      }

      var2.dropAliasDescriptor(this, var3);
   }

   public synchronized boolean isValid() {
      return true;
   }

   public void prepareToInvalidate(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      DependencyManager var4 = this.getDataDictionary().getDependencyManager();
      switch (var2) {
         default -> throw StandardException.newException("X0Y30.S", new Object[]{var4.getActionString(var2), var1.getObjectName(), this.getQualifiedName()});
      }
   }

   public void makeInvalid(int var1, LanguageConnectionContext var2) throws StandardException {
   }
}
