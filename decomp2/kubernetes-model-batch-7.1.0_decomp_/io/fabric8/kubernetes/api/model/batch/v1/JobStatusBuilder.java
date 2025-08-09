package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobStatusBuilder extends JobStatusFluent implements VisitableBuilder {
   JobStatusFluent fluent;

   public JobStatusBuilder() {
      this(new JobStatus());
   }

   public JobStatusBuilder(JobStatusFluent fluent) {
      this(fluent, new JobStatus());
   }

   public JobStatusBuilder(JobStatusFluent fluent, JobStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobStatusBuilder(JobStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JobStatus build() {
      JobStatus buildable = new JobStatus(this.fluent.getActive(), this.fluent.getCompletedIndexes(), this.fluent.getCompletionTime(), this.fluent.buildConditions(), this.fluent.getFailed(), this.fluent.getFailedIndexes(), this.fluent.getReady(), this.fluent.getStartTime(), this.fluent.getSucceeded(), this.fluent.getTerminating(), this.fluent.buildUncountedTerminatedPods());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
