package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.CronJobList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1BatchAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.internal.batch.v1.JobOperationsImpl;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1BatchAPIGroupClient extends ClientAdapter implements V1BatchAPIGroupDSL {
   public MixedOperation jobs() {
      return new JobOperationsImpl(this);
   }

   public MixedOperation cronjobs() {
      return this.resources(CronJob.class, CronJobList.class);
   }

   public V1BatchAPIGroupClient newInstance() {
      return new V1BatchAPIGroupClient();
   }
}
