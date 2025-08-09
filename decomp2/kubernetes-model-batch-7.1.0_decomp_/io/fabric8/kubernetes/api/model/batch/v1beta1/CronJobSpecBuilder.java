package io.fabric8.kubernetes.api.model.batch.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CronJobSpecBuilder extends CronJobSpecFluent implements VisitableBuilder {
   CronJobSpecFluent fluent;

   public CronJobSpecBuilder() {
      this(new CronJobSpec());
   }

   public CronJobSpecBuilder(CronJobSpecFluent fluent) {
      this(fluent, new CronJobSpec());
   }

   public CronJobSpecBuilder(CronJobSpecFluent fluent, CronJobSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CronJobSpecBuilder(CronJobSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CronJobSpec build() {
      CronJobSpec buildable = new CronJobSpec(this.fluent.getConcurrencyPolicy(), this.fluent.getFailedJobsHistoryLimit(), this.fluent.buildJobTemplate(), this.fluent.getSchedule(), this.fluent.getStartingDeadlineSeconds(), this.fluent.getSuccessfulJobsHistoryLimit(), this.fluent.getSuspend());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
