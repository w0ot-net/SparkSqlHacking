package io.fabric8.kubernetes.api.model.discovery.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ForZoneBuilder extends ForZoneFluent implements VisitableBuilder {
   ForZoneFluent fluent;

   public ForZoneBuilder() {
      this(new ForZone());
   }

   public ForZoneBuilder(ForZoneFluent fluent) {
      this(fluent, new ForZone());
   }

   public ForZoneBuilder(ForZoneFluent fluent, ForZone instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ForZoneBuilder(ForZone instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ForZone build() {
      ForZone buildable = new ForZone(this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
