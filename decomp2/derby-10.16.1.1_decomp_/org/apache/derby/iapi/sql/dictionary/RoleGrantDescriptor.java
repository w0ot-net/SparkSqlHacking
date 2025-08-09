package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class RoleGrantDescriptor extends TupleDescriptor implements Provider {
   private final UUID uuid;
   private final String roleName;
   private final String grantee;
   private final String grantor;
   private boolean withAdminOption;
   private final boolean isDef;

   public RoleGrantDescriptor(DataDictionary var1, UUID var2, String var3, String var4, String var5, boolean var6, boolean var7) {
      super(var1);
      this.uuid = var2;
      this.roleName = var3;
      this.grantee = var4;
      this.grantor = var5;
      this.withAdminOption = var6;
      this.isDef = var7;
   }

   public UUID getUUID() {
      return this.uuid;
   }

   public String getGrantee() {
      return this.grantee;
   }

   public String getGrantor() {
      return this.grantor;
   }

   public boolean isDef() {
      return this.isDef;
   }

   public String getRoleName() {
      return this.roleName;
   }

   public boolean isWithAdminOption() {
      return this.withAdminOption;
   }

   public void setWithAdminOption(boolean var1) {
      this.withAdminOption = var1;
   }

   public String toString() {
      return "";
   }

   public String getDescriptorType() {
      return "Role";
   }

   public String getDescriptorName() {
      return this.roleName + " " + this.grantor + " " + this.grantee;
   }

   public void drop(LanguageConnectionContext var1) throws StandardException {
      DataDictionary var2 = this.getDataDictionary();
      TransactionController var3 = var1.getTransactionExecute();
      var2.dropRoleGrant(this.roleName, this.grantee, this.grantor, var3);
   }

   public UUID getObjectID() {
      return this.uuid;
   }

   public boolean isPersistent() {
      return true;
   }

   public String getObjectName() {
      String var10000 = this.isDef ? "CREATE ROLE: " : "GRANT ROLE: ";
      return var10000 + this.roleName + " GRANT TO: " + this.grantee + " GRANTED BY: " + this.grantor + (this.withAdminOption ? " WITH ADMIN OPTION" : "");
   }

   public String getClassType() {
      return "RoleGrant";
   }

   public DependableFinder getDependableFinder() {
      return this.getDependableFinder(471);
   }
}
