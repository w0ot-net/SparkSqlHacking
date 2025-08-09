package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class JobTemplateSpecBuilder extends JobTemplateSpecFluent implements VisitableBuilder {
   JobTemplateSpecFluent fluent;

   public JobTemplateSpecBuilder() {
      this(new JobTemplateSpec());
   }

   public JobTemplateSpecBuilder(JobTemplateSpecFluent fluent) {
      this(fluent, new JobTemplateSpec());
   }

   public JobTemplateSpecBuilder(JobTemplateSpecFluent fluent, JobTemplateSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public JobTemplateSpecBuilder(JobTemplateSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public JobTemplateSpec build() {
      JobTemplateSpec buildable = new JobTemplateSpec(this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
