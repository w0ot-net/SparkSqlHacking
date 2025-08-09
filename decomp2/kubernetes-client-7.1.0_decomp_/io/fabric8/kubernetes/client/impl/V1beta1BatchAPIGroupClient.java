package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1beta1.CronJobList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1BatchAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1BatchAPIGroupClient extends ClientAdapter implements V1beta1BatchAPIGroupDSL {
   public MixedOperation cronjobs() {
      return this.resources(CronJob.class, CronJobList.class);
   }

   public V1beta1BatchAPIGroupClient newInstance() {
      return new V1beta1BatchAPIGroupClient();
   }
}
