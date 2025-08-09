package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class RouteNamespacesBuilder extends RouteNamespacesFluent implements VisitableBuilder {
   RouteNamespacesFluent fluent;

   public RouteNamespacesBuilder() {
      this(new RouteNamespaces());
   }

   public RouteNamespacesBuilder(RouteNamespacesFluent fluent) {
      this(fluent, new RouteNamespaces());
   }

   public RouteNamespacesBuilder(RouteNamespacesFluent fluent, RouteNamespaces instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public RouteNamespacesBuilder(RouteNamespaces instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public RouteNamespaces build() {
      RouteNamespaces buildable = new RouteNamespaces(this.fluent.getFrom(), this.fluent.buildSelector());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
