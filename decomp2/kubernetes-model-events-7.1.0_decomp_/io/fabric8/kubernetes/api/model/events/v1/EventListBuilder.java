package io.fabric8.kubernetes.api.model.events.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class EventListBuilder extends EventListFluent implements VisitableBuilder {
   EventListFluent fluent;

   public EventListBuilder() {
      this(new EventList());
   }

   public EventListBuilder(EventListFluent fluent) {
      this(fluent, new EventList());
   }

   public EventListBuilder(EventListFluent fluent, EventList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public EventListBuilder(EventList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public EventList build() {
      EventList buildable = new EventList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
