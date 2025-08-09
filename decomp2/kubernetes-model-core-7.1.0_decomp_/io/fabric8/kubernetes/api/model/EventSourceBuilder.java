package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EventSourceBuilder extends EventSourceFluent implements VisitableBuilder {
   EventSourceFluent fluent;

   public EventSourceBuilder() {
      this(new EventSource());
   }

   public EventSourceBuilder(EventSourceFluent fluent) {
      this(fluent, new EventSource());
   }

   public EventSourceBuilder(EventSourceFluent fluent, EventSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EventSourceBuilder(EventSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EventSource build() {
      EventSource buildable = new EventSource(this.fluent.getComponent(), this.fluent.getHost());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
