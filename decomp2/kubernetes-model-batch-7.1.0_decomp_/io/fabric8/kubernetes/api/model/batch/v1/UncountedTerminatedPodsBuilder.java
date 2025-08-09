package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class UncountedTerminatedPodsBuilder extends UncountedTerminatedPodsFluent implements VisitableBuilder {
   UncountedTerminatedPodsFluent fluent;

   public UncountedTerminatedPodsBuilder() {
      this(new UncountedTerminatedPods());
   }

   public UncountedTerminatedPodsBuilder(UncountedTerminatedPodsFluent fluent) {
      this(fluent, new UncountedTerminatedPods());
   }

   public UncountedTerminatedPodsBuilder(UncountedTerminatedPodsFluent fluent, UncountedTerminatedPods instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public UncountedTerminatedPodsBuilder(UncountedTerminatedPods instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public UncountedTerminatedPods build() {
      UncountedTerminatedPods buildable = new UncountedTerminatedPods(this.fluent.getFailed(), this.fluent.getSucceeded());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
