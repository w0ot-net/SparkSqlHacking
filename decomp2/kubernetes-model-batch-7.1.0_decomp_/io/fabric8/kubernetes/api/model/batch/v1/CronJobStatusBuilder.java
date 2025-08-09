package io.fabric8.kubernetes.api.model.batch.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CronJobStatusBuilder extends CronJobStatusFluent implements VisitableBuilder {
   CronJobStatusFluent fluent;

   public CronJobStatusBuilder() {
      this(new CronJobStatus());
   }

   public CronJobStatusBuilder(CronJobStatusFluent fluent) {
      this(fluent, new CronJobStatus());
   }

   public CronJobStatusBuilder(CronJobStatusFluent fluent, CronJobStatus instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CronJobStatusBuilder(CronJobStatus instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CronJobStatus build() {
      CronJobStatus buildable = new CronJobStatus(this.fluent.buildActive(), this.fluent.getLastScheduleTime(), this.fluent.getLastSuccessfulTime());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
