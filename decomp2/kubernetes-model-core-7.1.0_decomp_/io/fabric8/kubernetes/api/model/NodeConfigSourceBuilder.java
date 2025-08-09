package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeConfigSourceBuilder extends NodeConfigSourceFluent implements VisitableBuilder {
   NodeConfigSourceFluent fluent;

   public NodeConfigSourceBuilder() {
      this(new NodeConfigSource());
   }

   public NodeConfigSourceBuilder(NodeConfigSourceFluent fluent) {
      this(fluent, new NodeConfigSource());
   }

   public NodeConfigSourceBuilder(NodeConfigSourceFluent fluent, NodeConfigSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeConfigSourceBuilder(NodeConfigSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeConfigSource build() {
      NodeConfigSource buildable = new NodeConfigSource(this.fluent.buildConfigMap());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
