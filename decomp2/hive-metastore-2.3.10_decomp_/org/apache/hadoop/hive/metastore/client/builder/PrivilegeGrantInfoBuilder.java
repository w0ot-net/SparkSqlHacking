package org.apache.hadoop.hive.metastore.client.builder;

import java.io.IOException;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.PrivilegeGrantInfo;
import org.apache.hadoop.hive.metastore.utils.SecurityUtils;

public class PrivilegeGrantInfoBuilder {
   private String privilege;
   private String grantor;
   private int createTime = (int)(System.currentTimeMillis() / 1000L);
   private PrincipalType grantorType;
   private boolean grantOption = false;

   public PrivilegeGrantInfoBuilder setPrivilege(String privilege) {
      this.privilege = privilege;
      return this;
   }

   public PrivilegeGrantInfoBuilder setGrantor(String grantor) {
      this.grantor = grantor;
      return this;
   }

   public PrivilegeGrantInfoBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public PrivilegeGrantInfoBuilder setGrantorType(PrincipalType grantorType) {
      this.grantorType = grantorType;
      return this;
   }

   public PrivilegeGrantInfoBuilder setGrantOption(boolean grantOption) {
      this.grantOption = grantOption;
      return this;
   }

   public PrivilegeGrantInfo build() throws MetaException {
      if (this.privilege == null) {
         throw new MetaException("Privilege must be provided.");
      } else {
         if (this.grantor == null) {
            try {
               this.grantor = SecurityUtils.getUser();
               this.grantorType = PrincipalType.USER;
            } catch (IOException e) {
               throw new MetaException(e.getMessage());
            }
         }

         return new PrivilegeGrantInfo(this.privilege, this.createTime, this.grantor, this.grantorType, this.grantOption);
      }
   }
}
