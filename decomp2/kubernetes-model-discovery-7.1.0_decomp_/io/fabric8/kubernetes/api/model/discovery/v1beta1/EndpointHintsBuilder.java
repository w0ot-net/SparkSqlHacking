package io.fabric8.kubernetes.api.model.discovery.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointHintsBuilder extends EndpointHintsFluent implements VisitableBuilder {
   EndpointHintsFluent fluent;

   public EndpointHintsBuilder() {
      this(new EndpointHints());
   }

   public EndpointHintsBuilder(EndpointHintsFluent fluent) {
      this(fluent, new EndpointHints());
   }

   public EndpointHintsBuilder(EndpointHintsFluent fluent, EndpointHints instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointHintsBuilder(EndpointHints instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointHints build() {
      EndpointHints buildable = new EndpointHints(this.fluent.buildForZones());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
