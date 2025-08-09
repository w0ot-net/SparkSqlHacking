package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CronJobListBuilder extends CronJobListFluent implements VisitableBuilder {
   CronJobListFluent fluent;

   public CronJobListBuilder() {
      this(new CronJobList());
   }

   public CronJobListBuilder(CronJobListFluent fluent) {
      this(fluent, new CronJobList());
   }

   public CronJobListBuilder(CronJobListFluent fluent, CronJobList instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CronJobListBuilder(CronJobList instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CronJobList build() {
      CronJobList buildable = new CronJobList(this.fluent.getApiVersion(), this.fluent.buildItems(), this.fluent.getKind(), this.fluent.getMetadata());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
