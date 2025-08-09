package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.Role;

public class RoleBuilder {
   private String roleName;
   private String ownerName;
   private int createTime = (int)(System.currentTimeMillis() / 1000L);

   public RoleBuilder setRoleName(String roleName) {
      this.roleName = roleName;
      return this;
   }

   public RoleBuilder setOwnerName(String ownerName) {
      this.ownerName = ownerName;
      return this;
   }

   public RoleBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public Role build() throws MetaException {
      if (this.roleName != null && this.ownerName != null) {
         return new Role(this.roleName, this.createTime, this.ownerName);
      } else {
         throw new MetaException("role name and owner name must be provided.");
      }
   }
}
