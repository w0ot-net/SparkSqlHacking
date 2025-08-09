package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDNSConfigOptionBuilder extends PodDNSConfigOptionFluent implements VisitableBuilder {
   PodDNSConfigOptionFluent fluent;

   public PodDNSConfigOptionBuilder() {
      this(new PodDNSConfigOption());
   }

   public PodDNSConfigOptionBuilder(PodDNSConfigOptionFluent fluent) {
      this(fluent, new PodDNSConfigOption());
   }

   public PodDNSConfigOptionBuilder(PodDNSConfigOptionFluent fluent, PodDNSConfigOption instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDNSConfigOptionBuilder(PodDNSConfigOption instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDNSConfigOption build() {
      PodDNSConfigOption buildable = new PodDNSConfigOption(this.fluent.getName(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
