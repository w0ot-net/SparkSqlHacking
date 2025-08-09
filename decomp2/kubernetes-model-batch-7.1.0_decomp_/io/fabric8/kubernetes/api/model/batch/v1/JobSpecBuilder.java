package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobSpecBuilder extends JobSpecFluent implements VisitableBuilder {
   JobSpecFluent fluent;

   public JobSpecBuilder() {
      this(new JobSpec());
   }

   public JobSpecBuilder(JobSpecFluent fluent) {
      this(fluent, new JobSpec());
   }

   public JobSpecBuilder(JobSpecFluent fluent, JobSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobSpecBuilder(JobSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JobSpec build() {
      JobSpec buildable = new JobSpec(this.fluent.getActiveDeadlineSeconds(), this.fluent.getBackoffLimit(), this.fluent.getBackoffLimitPerIndex(), this.fluent.getCompletionMode(), this.fluent.getCompletions(), this.fluent.getManagedBy(), this.fluent.getManualSelector(), this.fluent.getMaxFailedIndexes(), this.fluent.getParallelism(), this.fluent.buildPodFailurePolicy(), this.fluent.getPodReplacementPolicy(), this.fluent.buildSelector(), this.fluent.buildSuccessPolicy(), this.fluent.getSuspend(), this.fluent.buildTemplate(), this.fluent.getTtlSecondsAfterFinished());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
