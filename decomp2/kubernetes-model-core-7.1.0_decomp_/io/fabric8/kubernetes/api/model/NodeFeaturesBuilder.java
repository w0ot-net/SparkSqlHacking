package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeFeaturesBuilder extends NodeFeaturesFluent implements VisitableBuilder {
   NodeFeaturesFluent fluent;

   public NodeFeaturesBuilder() {
      this(new NodeFeatures());
   }

   public NodeFeaturesBuilder(NodeFeaturesFluent fluent) {
      this(fluent, new NodeFeatures());
   }

   public NodeFeaturesBuilder(NodeFeaturesFluent fluent, NodeFeatures instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeFeaturesBuilder(NodeFeatures instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeFeatures build() {
      NodeFeatures buildable = new NodeFeatures(this.fluent.getSupplementalGroupsPolicy());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
