package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.shared.common.error.StandardException;

public class PermDescriptor extends PermissionsDescriptor implements Provider {
   public static final String SEQUENCE_TYPE = "SEQUENCE";
   public static final String UDT_TYPE = "TYPE";
   public static final String AGGREGATE_TYPE = "DERBY AGGREGATE";
   public static final String USAGE_PRIV = "USAGE";
   private String objectType;
   private UUID permObjectId;
   private String permission;
   private boolean grantable;

   public PermDescriptor(DataDictionary var1, UUID var2, String var3, UUID var4, String var5, String var6, String var7, boolean var8) {
      super(var1, var7, var6);
      this.setUUID(var2);
      this.objectType = var3;
      this.permObjectId = var4;
      this.permission = var5;
      this.grantable = var8;
   }

   public PermDescriptor(DataDictionary var1, UUID var2) throws StandardException {
      this(var1, var2, (String)null, (UUID)null, (String)null, (String)null, (String)null, false);
   }

   public String getObjectType() {
      return this.objectType;
   }

   public UUID getPermObjectId() {
      return this.permObjectId;
   }

   public String getPermission() {
      return this.permission;
   }

   public boolean isGrantable() {
      return this.grantable;
   }

   public int getCatalogNumber() {
      return 21;
   }

   public String toString() {
      return "";
   }

   public boolean equals(Object var1) {
      if (!(var1 instanceof PermDescriptor var2)) {
         return false;
      } else {
         return super.keyEquals(var2) && this.permObjectId.equals(var2.permObjectId);
      }
   }

   public int hashCode() {
      return super.keyHashCode() + this.permObjectId.hashCode();
   }

   public boolean checkOwner(String var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      PrivilegedSQLObject var3 = getProtectedObject(var2, this.permObjectId, this.objectType);
      return var3.getSchemaDescriptor().getAuthorizationId().equals(var1);
   }

   public static PrivilegedSQLObject getProtectedObject(DataDictionary var0, UUID var1, String var2) throws StandardException {
      if ("SEQUENCE".equals(var2)) {
         return var0.getSequenceDescriptor(var1);
      } else if ("DERBY AGGREGATE".equals(var2)) {
         return var0.getAliasDescriptor(var1);
      } else if ("TYPE".equals(var2)) {
         return var0.getAliasDescriptor(var1);
      } else {
         throw StandardException.newException("XSCB3.S", new Object[0]);
      }
   }

   public String getObjectName() {
      try {
         DataDictionary var1 = this.getDataDictionary();
         PrivilegedSQLObject var2 = getProtectedObject(var1, this.permObjectId, this.objectType);
         return var2.getName();
      } catch (StandardException var3) {
         return this.objectType;
      }
   }

   public String getClassType() {
      return "Perm";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(473);
   }
}
