package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NamedClusterBuilder extends NamedClusterFluent implements VisitableBuilder {
   NamedClusterFluent fluent;

   public NamedClusterBuilder() {
      this(new NamedCluster());
   }

   public NamedClusterBuilder(NamedClusterFluent fluent) {
      this(fluent, new NamedCluster());
   }

   public NamedClusterBuilder(NamedClusterFluent fluent, NamedCluster instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NamedClusterBuilder(NamedCluster instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NamedCluster build() {
      NamedCluster buildable = new NamedCluster(this.fluent.buildCluster(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
