package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CronJobBuilder extends CronJobFluent implements VisitableBuilder {
   CronJobFluent fluent;

   public CronJobBuilder() {
      this(new CronJob());
   }

   public CronJobBuilder(CronJobFluent fluent) {
      this(fluent, new CronJob());
   }

   public CronJobBuilder(CronJobFluent fluent, CronJob instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CronJobBuilder(CronJob instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CronJob build() {
      CronJob buildable = new CronJob(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec(), this.fluent.buildStatus());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
