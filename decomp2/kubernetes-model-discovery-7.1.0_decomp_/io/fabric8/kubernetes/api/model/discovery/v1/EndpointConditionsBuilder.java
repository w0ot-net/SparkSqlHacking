package io.fabric8.kubernetes.api.model.discovery.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EndpointConditionsBuilder extends EndpointConditionsFluent implements VisitableBuilder {
   EndpointConditionsFluent fluent;

   public EndpointConditionsBuilder() {
      this(new EndpointConditions());
   }

   public EndpointConditionsBuilder(EndpointConditionsFluent fluent) {
      this(fluent, new EndpointConditions());
   }

   public EndpointConditionsBuilder(EndpointConditionsFluent fluent, EndpointConditions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EndpointConditionsBuilder(EndpointConditions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EndpointConditions build() {
      EndpointConditions buildable = new EndpointConditions(this.fluent.getReady(), this.fluent.getServing(), this.fluent.getTerminating());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
