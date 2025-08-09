package io.fabric8.kubernetes.api.model.extensions;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AllowedCSIDriverBuilder extends AllowedCSIDriverFluent implements VisitableBuilder {
   AllowedCSIDriverFluent fluent;

   public AllowedCSIDriverBuilder() {
      this(new AllowedCSIDriver());
   }

   public AllowedCSIDriverBuilder(AllowedCSIDriverFluent fluent) {
      this(fluent, new AllowedCSIDriver());
   }

   public AllowedCSIDriverBuilder(AllowedCSIDriverFluent fluent, AllowedCSIDriver instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AllowedCSIDriverBuilder(AllowedCSIDriver instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AllowedCSIDriver build() {
      AllowedCSIDriver buildable = new AllowedCSIDriver(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
