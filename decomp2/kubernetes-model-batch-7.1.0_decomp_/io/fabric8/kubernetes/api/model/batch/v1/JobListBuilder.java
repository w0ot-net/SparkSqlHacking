package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobListBuilder extends JobListFluent implements VisitableBuilder {
   JobListFluent fluent;

   public JobListBuilder() {
      this(new JobList());
   }

   public JobListBuilder(JobListFluent fluent) {
      this(fluent, new JobList());
   }

   public JobListBuilder(JobListFluent fluent, JobList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobListBuilder(JobList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JobList build() {
      JobList buildable = new JobList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
