package io.fabric8.kubernetes.api.model.events.v1beta1;

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
      Event buildable = new Event(this.fluent.getAction(), this.fluent.getApiVersion(), this.fluent.getDeprecatedCount(), this.fluent.getDeprecatedFirstTimestamp(), this.fluent.getDeprecatedLastTimestamp(), this.fluent.getDeprecatedSource(), this.fluent.getEventTime(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.getNote(), this.fluent.getReason(), this.fluent.buildRegarding(), this.fluent.buildRelated(), this.fluent.getReportingController(), this.fluent.getReportingInstance(), this.fluent.buildSeries(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
