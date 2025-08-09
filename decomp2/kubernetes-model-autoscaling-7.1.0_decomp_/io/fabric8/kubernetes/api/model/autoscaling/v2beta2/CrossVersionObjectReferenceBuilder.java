package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CrossVersionObjectReferenceBuilder extends CrossVersionObjectReferenceFluent implements VisitableBuilder {
   CrossVersionObjectReferenceFluent fluent;

   public CrossVersionObjectReferenceBuilder() {
      this(new CrossVersionObjectReference());
   }

   public CrossVersionObjectReferenceBuilder(CrossVersionObjectReferenceFluent fluent) {
      this(fluent, new CrossVersionObjectReference());
   }

   public CrossVersionObjectReferenceBuilder(CrossVersionObjectReferenceFluent fluent, CrossVersionObjectReference instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CrossVersionObjectReferenceBuilder(CrossVersionObjectReference instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CrossVersionObjectReference build() {
      CrossVersionObjectReference buildable = new CrossVersionObjectReference(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.getName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
