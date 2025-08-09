package io.fabric8.kubernetes.api.model.authentication.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UserInfoBuilder extends UserInfoFluent implements VisitableBuilder {
   UserInfoFluent fluent;

   public UserInfoBuilder() {
      this(new UserInfo());
   }

   public UserInfoBuilder(UserInfoFluent fluent) {
      this(fluent, new UserInfo());
   }

   public UserInfoBuilder(UserInfoFluent fluent, UserInfo instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UserInfoBuilder(UserInfo instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UserInfo build() {
      UserInfo buildable = new UserInfo(this.fluent.getExtra(), this.fluent.getGroups(), this.fluent.getUid(), this.fluent.getUsername());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
