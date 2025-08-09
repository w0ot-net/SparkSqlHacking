package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EventSeriesBuilder extends EventSeriesFluent implements VisitableBuilder {
   EventSeriesFluent fluent;

   public EventSeriesBuilder() {
      this(new EventSeries());
   }

   public EventSeriesBuilder(EventSeriesFluent fluent) {
      this(fluent, new EventSeries());
   }

   public EventSeriesBuilder(EventSeriesFluent fluent, EventSeries instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EventSeriesBuilder(EventSeries instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EventSeries build() {
      EventSeries buildable = new EventSeries(this.fluent.getCount(), this.fluent.buildLastObservedTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
