package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.Activation;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public final class SchemaDescriptor extends UniqueTupleDescriptor implements Provider {
   public static final String STD_SYSTEM_SCHEMA_NAME = "SYS";
   public static final String IBM_SYSTEM_SCHEMA_NAME = "SYSIBM";
   public static final String IBM_SYSTEM_CAT_SCHEMA_NAME = "SYSCAT";
   public static final String IBM_SYSTEM_FUN_SCHEMA_NAME = "SYSFUN";
   public static final String IBM_SYSTEM_PROC_SCHEMA_NAME = "SYSPROC";
   public static final String IBM_SYSTEM_STAT_SCHEMA_NAME = "SYSSTAT";
   public static final String IBM_SYSTEM_NULLID_SCHEMA_NAME = "NULLID";
   public static final String STD_SQLJ_SCHEMA_NAME = "SQLJ";
   public static final String STD_SYSTEM_DIAG_SCHEMA_NAME = "SYSCS_DIAG";
   public static final String STD_SYSTEM_UTIL_SCHEMA_NAME = "SYSCS_UTIL";
   public static final String STD_DEFAULT_SCHEMA_NAME = "APP";
   public static final String SYSCAT_SCHEMA_UUID = "c013800d-00fb-2641-07ec-000000134f30";
   public static final String SYSFUN_SCHEMA_UUID = "c013800d-00fb-2642-07ec-000000134f30";
   public static final String SYSPROC_SCHEMA_UUID = "c013800d-00fb-2643-07ec-000000134f30";
   public static final String SYSSTAT_SCHEMA_UUID = "c013800d-00fb-2644-07ec-000000134f30";
   public static final String SYSCS_DIAG_SCHEMA_UUID = "c013800d-00fb-2646-07ec-000000134f30";
   public static final String SYSCS_UTIL_SCHEMA_UUID = "c013800d-00fb-2649-07ec-000000134f30";
   public static final String NULLID_SCHEMA_UUID = "c013800d-00fb-2647-07ec-000000134f30";
   public static final String SQLJ_SCHEMA_UUID = "c013800d-00fb-2648-07ec-000000134f30";
   public static final String SYSTEM_SCHEMA_UUID = "8000000d-00d0-fd77-3ed8-000a0a0b1900";
   public static final String SYSIBM_SCHEMA_UUID = "c013800d-00f8-5b53-28a9-00000019ed88";
   public static final String DEFAULT_SCHEMA_UUID = "80000000-00d2-b38f-4cda-000a0a412c00";
   public static final String STD_DECLARED_GLOBAL_TEMPORARY_TABLES_SCHEMA_NAME = "SESSION";
   public static final String DEFAULT_USER_NAME = "APP";
   public static final String SA_USER_NAME = "DBA";
   private final String name;
   private UUID oid;
   private String aid;
   private final boolean isSystem;
   private final boolean isSYSIBM;
   private int collationType;

   public SchemaDescriptor(DataDictionary var1, String var2, String var3, UUID var4, boolean var5) {
      super(var1);
      this.name = var2;
      this.aid = var3;
      this.oid = var4;
      this.isSystem = var5;
      this.isSYSIBM = var5 && "SYSIBM".equals(var2);
      if (var5) {
         this.collationType = var1.getCollationTypeOfSystemSchemas();
      } else {
         this.collationType = var1.getCollationTypeOfUserSchemas();
      }

   }

   public String getSchemaName() {
      return this.name;
   }

   public String getAuthorizationId() {
      return this.aid;
   }

   public void setAuthorizationId(String var1) {
      this.aid = var1;
   }

   public UUID getUUID() {
      return this.oid;
   }

   public void setUUID(UUID var1) {
      this.oid = var1;
   }

   public int getCollationType() {
      return this.collationType;
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(371);
   }

   public String getObjectName() {
      return this.name;
   }

   public UUID getObjectID() {
      return this.oid;
   }

   public String getClassType() {
      return "Schema";
   }

   public String toString() {
      return this.name;
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof SchemaDescriptor var2)) {
         return false;
      } else {
         return this.oid != null && var2.oid != null ? this.oid.equals(var2.oid) : this.name.equals(var2.name);
      }
   }

   public boolean isSystemSchema() {
      return this.isSystem;
   }

   public boolean isSchemaWithGrantableRoutines() {
      if (!this.isSystem) {
         return true;
      } else {
         return this.name.equals("SQLJ") || this.name.equals("SYSCS_UTIL");
      }
   }

   public boolean isSYSIBM() {
      return this.isSYSIBM;
   }

   public int hashCode() {
      return this.oid.hashCode();
   }

   public String getDescriptorName() {
      return this.name;
   }

   public String getDescriptorType() {
      return "Schema";
   }

   public void drop(LanguageConnectionContext var1, Activation var2) throws StandardException {
      DataDictionary var3 = this.getDataDictionary();
      DependencyManager var4 = var3.getDependencyManager();
      TransactionController var5 = var1.getTransactionExecute();
      if (this.getSchemaName().equals("SESSION") && this.getUUID() == null) {
         throw StandardException.newException("42Y07", new Object[]{this.getSchemaName()});
      } else if (!var3.isSchemaEmpty(this)) {
         throw StandardException.newException("X0Y54.S", new Object[]{this.getSchemaName()});
      } else {
         var4.invalidateFor(this, 32, var1);
         var3.dropSchemaDescriptor(this.getSchemaName(), var5);
         var1.resetSchemaUsages(var2, this.getSchemaName());
      }
   }
}
