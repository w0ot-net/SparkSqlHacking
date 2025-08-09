package io.fabric8.kubernetes.api.model.flowcontrol.v1beta3;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UserSubjectBuilder extends UserSubjectFluent implements VisitableBuilder {
   UserSubjectFluent fluent;

   public UserSubjectBuilder() {
      this(new UserSubject());
   }

   public UserSubjectBuilder(UserSubjectFluent fluent) {
      this(fluent, new UserSubject());
   }

   public UserSubjectBuilder(UserSubjectFluent fluent, UserSubject instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UserSubjectBuilder(UserSubject instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UserSubject build() {
      UserSubject buildable = new UserSubject(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
