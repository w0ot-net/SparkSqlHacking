package org.apache.derby.impl.sql.catalog;

import java.sql.SQLException;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.ConnectionUtil;
import org.apache.derby.iapi.sql.dictionary.AliasDescriptor;
import org.apache.derby.iapi.sql.dictionary.ColPermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.PermDescriptor;
import org.apache.derby.iapi.sql.dictionary.PermissionsDescriptor;
import org.apache.derby.iapi.sql.dictionary.PrivilegedSQLObject;
import org.apache.derby.iapi.sql.dictionary.RoutinePermsDescriptor;
import org.apache.derby.iapi.sql.dictionary.SchemaDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TablePermsDescriptor;
import org.apache.derby.shared.common.error.StandardException;

class PermissionsCacheable implements Cacheable {
   protected final DataDictionaryImpl dd;
   private PermissionsDescriptor permissions;

   PermissionsCacheable(DataDictionaryImpl var1) {
      this.dd = var1;
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      if (var1 instanceof TablePermsDescriptor var2) {
         this.permissions = this.dd.getUncachedTablePermsDescriptor(var2);
         if (this.permissions == null) {
            TableDescriptor var3 = this.dd.getTableDescriptor(var2.getTableUUID());
            SchemaDescriptor var4 = var3.getSchemaDescriptor();
            if (var4.isSystemSchema()) {
               this.permissions = new TablePermsDescriptor(this.dd, var2.getGrantee(), (String)null, var2.getTableUUID(), "Y", "N", "N", "N", "N", "N");
               ((TablePermsDescriptor)this.permissions).setUUID(var2.getTableUUID());
            } else if (var2.getGrantee().equals(var4.getAuthorizationId())) {
               this.permissions = new TablePermsDescriptor(this.dd, var2.getGrantee(), "_SYSTEM", var2.getTableUUID(), "Y", "Y", "Y", "Y", "Y", "Y");
            } else {
               this.permissions = new TablePermsDescriptor(this.dd, var2.getGrantee(), (String)null, var2.getTableUUID(), "N", "N", "N", "N", "N", "N");
            }
         }
      } else if (var1 instanceof ColPermsDescriptor var9) {
         this.permissions = this.dd.getUncachedColPermsDescriptor(var9);
         if (this.permissions == null) {
            this.permissions = new ColPermsDescriptor(this.dd, var9.getGrantee(), (String)null, var9.getTableUUID(), var9.getType(), (FormatableBitSet)null);
         }
      } else if (var1 instanceof RoutinePermsDescriptor var10) {
         this.permissions = this.dd.getUncachedRoutinePermsDescriptor(var10);
         if (this.permissions == null) {
            try {
               AliasDescriptor var12 = this.dd.getAliasDescriptor(var10.getRoutineUUID());
               SchemaDescriptor var14 = this.dd.getSchemaDescriptor(var12.getSchemaUUID(), ConnectionUtil.getCurrentLCC().getTransactionExecute());
               if (var14.isSystemSchema() && !var14.isSchemaWithGrantableRoutines()) {
                  this.permissions = new RoutinePermsDescriptor(this.dd, var10.getGrantee(), (String)null, var10.getRoutineUUID(), true);
               } else if (var10.getGrantee().equals(var14.getAuthorizationId())) {
                  this.permissions = new RoutinePermsDescriptor(this.dd, var10.getGrantee(), "_SYSTEM", var10.getRoutineUUID(), true);
               }
            } catch (SQLException var8) {
               throw StandardException.plainWrapException(var8);
            }
         }
      } else {
         if (!(var1 instanceof PermDescriptor)) {
            return null;
         }

         PermDescriptor var11 = (PermDescriptor)var1;
         this.permissions = this.dd.getUncachedGenericPermDescriptor(var11);
         if (this.permissions == null) {
            String var13 = var11.getObjectType();
            String var15 = var11.getPermission();
            UUID var5 = var11.getPermObjectId();
            PrivilegedSQLObject var6 = PermDescriptor.getProtectedObject(this.dd, var5, var13);
            SchemaDescriptor var7 = var6.getSchemaDescriptor();
            if (var11.getGrantee().equals(var7.getAuthorizationId())) {
               this.permissions = new PermDescriptor(this.dd, (UUID)null, var13, var6.getUUID(), var15, "_SYSTEM", var11.getGrantee(), true);
            }
         }
      }

      return this.permissions != null ? this : null;
   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      if (var1 == null) {
         return null;
      } else {
         this.permissions = (PermissionsDescriptor)((PermissionsDescriptor)var1).clone();
         return this;
      }
   }

   public void clearIdentity() {
      this.permissions = null;
   }

   public Object getIdentity() {
      return this.permissions;
   }

   public boolean isDirty() {
      return false;
   }

   public void clean(boolean var1) throws StandardException {
   }
}
