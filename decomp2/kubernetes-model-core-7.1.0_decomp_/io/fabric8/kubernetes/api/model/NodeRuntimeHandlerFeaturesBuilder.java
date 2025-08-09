package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class NodeRuntimeHandlerFeaturesBuilder extends NodeRuntimeHandlerFeaturesFluent implements VisitableBuilder {
   NodeRuntimeHandlerFeaturesFluent fluent;

   public NodeRuntimeHandlerFeaturesBuilder() {
      this(new NodeRuntimeHandlerFeatures());
   }

   public NodeRuntimeHandlerFeaturesBuilder(NodeRuntimeHandlerFeaturesFluent fluent) {
      this(fluent, new NodeRuntimeHandlerFeatures());
   }

   public NodeRuntimeHandlerFeaturesBuilder(NodeRuntimeHandlerFeaturesFluent fluent, NodeRuntimeHandlerFeatures instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public NodeRuntimeHandlerFeaturesBuilder(NodeRuntimeHandlerFeatures instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public NodeRuntimeHandlerFeatures build() {
      NodeRuntimeHandlerFeatures buildable = new NodeRuntimeHandlerFeatures(this.fluent.getRecursiveReadOnlyMounts(), this.fluent.getUserNamespaces());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
