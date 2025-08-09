package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EventBuilder extends EventFluent implements VisitableBuilder {
   EventFluent fluent;

   public EventBuilder() {
      this(new Event());
   }

   public EventBuilder(EventFluent fluent) {
      this(fluent, new Event());
   }

   public EventBuilder(EventFluent fluent, Event instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EventBuilder(Event instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Event build() {
      Event buildable = new Event(this.fluent.getAction(), this.fluent.getApiVersion(), this.fluent.getCount(), this.fluent.buildEventTime(), this.fluent.getFirstTimestamp(), this.fluent.buildInvolvedObject(), this.fluent.getKind(), this.fluent.getLastTimestamp(), this.fluent.getMessage(), this.fluent.buildMetadata(), this.fluent.getReason(), this.fluent.buildRelated(), this.fluent.getReportingComponent(), this.fluent.getReportingInstance(), this.fluent.buildSeries(), this.fluent.buildSource(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
