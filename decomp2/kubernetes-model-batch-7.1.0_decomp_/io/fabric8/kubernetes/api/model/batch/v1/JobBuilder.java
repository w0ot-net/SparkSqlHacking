package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobBuilder extends JobFluent implements VisitableBuilder {
   JobFluent fluent;

   public JobBuilder() {
      this(new Job());
   }

   public JobBuilder(JobFluent fluent) {
      this(fluent, new Job());
   }

   public JobBuilder(JobFluent fluent, Job instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobBuilder(Job instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Job build() {
      Job buildable = new Job(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
