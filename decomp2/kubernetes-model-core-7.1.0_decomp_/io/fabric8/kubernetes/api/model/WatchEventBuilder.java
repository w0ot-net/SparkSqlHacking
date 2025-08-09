package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class WatchEventBuilder extends WatchEventFluent implements VisitableBuilder {
   WatchEventFluent fluent;

   public WatchEventBuilder() {
      this(new WatchEvent());
   }

   public WatchEventBuilder(WatchEventFluent fluent) {
      this(fluent, new WatchEvent());
   }

   public WatchEventBuilder(WatchEventFluent fluent, WatchEvent instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public WatchEventBuilder(WatchEvent instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public WatchEvent build() {
      WatchEvent buildable = new WatchEvent(this.fluent.getObject(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
