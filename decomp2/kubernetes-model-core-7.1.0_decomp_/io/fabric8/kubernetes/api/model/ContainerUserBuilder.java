package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerUserBuilder extends ContainerUserFluent implements VisitableBuilder {
   ContainerUserFluent fluent;

   public ContainerUserBuilder() {
      this(new ContainerUser());
   }

   public ContainerUserBuilder(ContainerUserFluent fluent) {
      this(fluent, new ContainerUser());
   }

   public ContainerUserBuilder(ContainerUserFluent fluent, ContainerUser instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerUserBuilder(ContainerUser instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerUser build() {
      ContainerUser buildable = new ContainerUser(this.fluent.buildLinux());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
