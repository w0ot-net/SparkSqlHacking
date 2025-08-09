package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllowedHostPathBuilder extends AllowedHostPathFluent implements VisitableBuilder {
   AllowedHostPathFluent fluent;

   public AllowedHostPathBuilder() {
      this(new AllowedHostPath());
   }

   public AllowedHostPathBuilder(AllowedHostPathFluent fluent) {
      this(fluent, new AllowedHostPath());
   }

   public AllowedHostPathBuilder(AllowedHostPathFluent fluent, AllowedHostPath instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllowedHostPathBuilder(AllowedHostPath instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllowedHostPath build() {
      AllowedHostPath buildable = new AllowedHostPath(this.fluent.getPathPrefix(), this.fluent.getReadOnly());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
