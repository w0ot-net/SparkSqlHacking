package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LinuxContainerUserBuilder extends LinuxContainerUserFluent implements VisitableBuilder {
   LinuxContainerUserFluent fluent;

   public LinuxContainerUserBuilder() {
      this(new LinuxContainerUser());
   }

   public LinuxContainerUserBuilder(LinuxContainerUserFluent fluent) {
      this(fluent, new LinuxContainerUser());
   }

   public LinuxContainerUserBuilder(LinuxContainerUserFluent fluent, LinuxContainerUser instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LinuxContainerUserBuilder(LinuxContainerUser instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LinuxContainerUser build() {
      LinuxContainerUser buildable = new LinuxContainerUser(this.fluent.getGid(), this.fluent.getSupplementalGroups(), this.fluent.getUid());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
