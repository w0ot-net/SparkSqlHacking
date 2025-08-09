package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ListenerStatusBuilder extends ListenerStatusFluent implements VisitableBuilder {
   ListenerStatusFluent fluent;

   public ListenerStatusBuilder() {
      this(new ListenerStatus());
   }

   public ListenerStatusBuilder(ListenerStatusFluent fluent) {
      this(fluent, new ListenerStatus());
   }

   public ListenerStatusBuilder(ListenerStatusFluent fluent, ListenerStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ListenerStatusBuilder(ListenerStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ListenerStatus build() {
      ListenerStatus buildable = new ListenerStatus(this.fluent.getAttachedRoutes(), this.fluent.getConditions(), this.fluent.getName(), this.fluent.buildSupportedKinds());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
