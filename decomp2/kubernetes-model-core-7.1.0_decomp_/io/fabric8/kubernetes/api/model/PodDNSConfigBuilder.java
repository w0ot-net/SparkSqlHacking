package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class PodDNSConfigBuilder extends PodDNSConfigFluent implements VisitableBuilder {
   PodDNSConfigFluent fluent;

   public PodDNSConfigBuilder() {
      this(new PodDNSConfig());
   }

   public PodDNSConfigBuilder(PodDNSConfigFluent fluent) {
      this(fluent, new PodDNSConfig());
   }

   public PodDNSConfigBuilder(PodDNSConfigFluent fluent, PodDNSConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public PodDNSConfigBuilder(PodDNSConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public PodDNSConfig build() {
      PodDNSConfig buildable = new PodDNSConfig(this.fluent.getNameservers(), this.fluent.buildOptions(), this.fluent.getSearches());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
