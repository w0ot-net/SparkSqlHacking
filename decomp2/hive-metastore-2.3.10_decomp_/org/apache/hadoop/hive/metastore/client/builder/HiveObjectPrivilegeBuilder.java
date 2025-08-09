package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.HiveObjectPrivilege;
import org.apache.hadoop.hive.metastore.api.HiveObjectRef;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;

public class HiveObjectPrivilegeBuilder {
   private HiveObjectRef hiveObjectRef;
   private String principleName;
   private PrincipalType principalType;
   private PrivilegeGrantInfo grantInfo;

   public HiveObjectPrivilegeBuilder setHiveObjectRef(HiveObjectRef hiveObjectRef) {
      this.hiveObjectRef = hiveObjectRef;
      return this;
   }

   public HiveObjectPrivilegeBuilder setPrincipleName(String principleName) {
      this.principleName = principleName;
      return this;
   }

   public HiveObjectPrivilegeBuilder setPrincipalType(PrincipalType principalType) {
      this.principalType = principalType;
      return this;
   }

   public HiveObjectPrivilegeBuilder setGrantInfo(PrivilegeGrantInfo grantInfo) {
      this.grantInfo = grantInfo;
      return this;
   }

   public HiveObjectPrivilege build() throws MetaException {
      if (this.hiveObjectRef != null && this.principleName != null && this.principalType != null && this.grantInfo != null) {
         return new HiveObjectPrivilege(this.hiveObjectRef, this.principleName, this.principalType, this.grantInfo);
      } else {
         throw new MetaException("hive object reference, principle name and type, and grant info must all be provided");
      }
   }
}
