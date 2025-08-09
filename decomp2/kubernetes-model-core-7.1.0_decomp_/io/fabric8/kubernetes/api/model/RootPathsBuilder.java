package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RootPathsBuilder extends RootPathsFluent implements VisitableBuilder {
   RootPathsFluent fluent;

   public RootPathsBuilder() {
      this(new RootPaths());
   }

   public RootPathsBuilder(RootPathsFluent fluent) {
      this(fluent, new RootPaths());
   }

   public RootPathsBuilder(RootPathsFluent fluent, RootPaths instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RootPathsBuilder(RootPaths instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RootPaths build() {
      RootPaths buildable = new RootPaths(this.fluent.getPaths());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
