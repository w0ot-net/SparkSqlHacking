package org.apache.derby.iapi.sql.dictionary;

import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.shared.common.error.StandardException;

public abstract class PermissionsDescriptor extends TupleDescriptor implements Cloneable, Provider {
   protected UUID oid;
   private String grantee;
   private final String grantor;

   PermissionsDescriptor(DataDictionary var1, String var2, String var3) {
      super(var1);
      this.grantee = var2;
      this.grantor = var3;
   }

   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         return null;
      }
   }

   public abstract int getCatalogNumber();

   protected boolean keyEquals(PermissionsDescriptor var1) {
      return this.grantee.equals(var1.grantee);
   }

   protected int keyHashCode() {
      return this.grantee.hashCode();
   }

   public void setGrantee(String var1) {
      this.grantee = var1;
   }

   public final String getGrantee() {
      return this.grantee;
   }

   public final String getGrantor() {
      return this.grantor;
   }

   public UUID getUUID() {
      return this.oid;
   }

   public void setUUID(UUID var1) {
      this.oid = var1;
   }

   public abstract boolean checkOwner(String var1) throws StandardException;

   public UUID getObjectID() {
      return this.oid;
   }

   public boolean isPersistent() {
      return true;
   }
}
