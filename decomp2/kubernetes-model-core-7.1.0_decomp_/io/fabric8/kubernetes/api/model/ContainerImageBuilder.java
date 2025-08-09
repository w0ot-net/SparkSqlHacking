package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ContainerImageBuilder extends ContainerImageFluent implements VisitableBuilder {
   ContainerImageFluent fluent;

   public ContainerImageBuilder() {
      this(new ContainerImage());
   }

   public ContainerImageBuilder(ContainerImageFluent fluent) {
      this(fluent, new ContainerImage());
   }

   public ContainerImageBuilder(ContainerImageFluent fluent, ContainerImage instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ContainerImageBuilder(ContainerImage instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ContainerImage build() {
      ContainerImage buildable = new ContainerImage(this.fluent.getNames(), this.fluent.getSizeBytes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
