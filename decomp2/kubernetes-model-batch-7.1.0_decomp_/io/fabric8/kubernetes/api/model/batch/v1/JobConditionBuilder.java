package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobConditionBuilder extends JobConditionFluent implements VisitableBuilder {
   JobConditionFluent fluent;

   public JobConditionBuilder() {
      this(new JobCondition());
   }

   public JobConditionBuilder(JobConditionFluent fluent) {
      this(fluent, new JobCondition());
   }

   public JobConditionBuilder(JobConditionFluent fluent, JobCondition instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobConditionBuilder(JobCondition instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JobCondition build() {
      JobCondition buildable = new JobCondition(this.fluent.getLastProbeTime(), this.fluent.getLastTransitionTime(), this.fluent.getMessage(), this.fluent.getReason(), this.fluent.getStatus(), this.fluent.getType());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
