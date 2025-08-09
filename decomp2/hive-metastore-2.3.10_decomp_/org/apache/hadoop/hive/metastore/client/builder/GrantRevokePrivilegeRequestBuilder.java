package org.apache.hadoop.hive.metastore.client.builder;

import org.apache.hadoop.hive.metastore.api.GrantRevokePrivilegeRequest;
import org.apache.hadoop.hive.metastore.api.GrantRevokeType;
import org.apache.hadoop.hive.metastore.api.HiveObjectPrivilege;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrivilegeBag;

public class GrantRevokePrivilegeRequestBuilder {
   private GrantRevokeType requestType;
   private PrivilegeBag privileges = new PrivilegeBag();
   private boolean revokeGrantOption = false;

   public GrantRevokePrivilegeRequestBuilder setRequestType(GrantRevokeType requestType) {
      this.requestType = requestType;
      return this;
   }

   public GrantRevokePrivilegeRequestBuilder setRevokeGrantOption(boolean revokeGrantOption) {
      this.revokeGrantOption = revokeGrantOption;
      return this;
   }

   public GrantRevokePrivilegeRequestBuilder addPrivilege(HiveObjectPrivilege privilege) {
      this.privileges.addToPrivileges(privilege);
      return this;
   }

   public GrantRevokePrivilegeRequest build() throws MetaException {
      if (this.requestType != null && this.privileges.getPrivilegesSize() != 0) {
         GrantRevokePrivilegeRequest rqst = new GrantRevokePrivilegeRequest(this.requestType, this.privileges);
         if (this.revokeGrantOption) {
            rqst.setRevokeGrantOption(this.revokeGrantOption);
         }

         return rqst;
      } else {
         throw new MetaException("The request type and at least one privilege must be provided.");
      }
   }
}
