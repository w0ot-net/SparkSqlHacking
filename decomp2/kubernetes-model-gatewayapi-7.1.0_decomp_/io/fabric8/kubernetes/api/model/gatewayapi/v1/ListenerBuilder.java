package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ListenerBuilder extends ListenerFluent implements VisitableBuilder {
   ListenerFluent fluent;

   public ListenerBuilder() {
      this(new Listener());
   }

   public ListenerBuilder(ListenerFluent fluent) {
      this(fluent, new Listener());
   }

   public ListenerBuilder(ListenerFluent fluent, Listener instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ListenerBuilder(Listener instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Listener build() {
      Listener buildable = new Listener(this.fluent.buildAllowedRoutes(), this.fluent.getHostname(), this.fluent.getName(), this.fluent.getPort(), this.fluent.getProtocol(), this.fluent.buildTls());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
