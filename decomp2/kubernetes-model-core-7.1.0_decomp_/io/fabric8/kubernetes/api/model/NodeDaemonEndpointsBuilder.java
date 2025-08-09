package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeDaemonEndpointsBuilder extends NodeDaemonEndpointsFluent implements VisitableBuilder {
   NodeDaemonEndpointsFluent fluent;

   public NodeDaemonEndpointsBuilder() {
      this(new NodeDaemonEndpoints());
   }

   public NodeDaemonEndpointsBuilder(NodeDaemonEndpointsFluent fluent) {
      this(fluent, new NodeDaemonEndpoints());
   }

   public NodeDaemonEndpointsBuilder(NodeDaemonEndpointsFluent fluent, NodeDaemonEndpoints instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeDaemonEndpointsBuilder(NodeDaemonEndpoints instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeDaemonEndpoints build() {
      NodeDaemonEndpoints buildable = new NodeDaemonEndpoints(this.fluent.buildKubeletEndpoint());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
