package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.scheduling.v1beta1.PriorityClass;
import io.fabric8.kubernetes.api.model.scheduling.v1beta1.PriorityClassList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class SchedulingAPIGroupClient extends ClientAdapter implements SchedulingAPIGroupDSL {
   public MixedOperation priorityClass() {
      return this.resources(PriorityClass.class, PriorityClassList.class);
   }

   public V1SchedulingAPIGroupDSL v1() {
      return (V1SchedulingAPIGroupDSL)this.adapt(V1SchedulingAPIGroupClient.class);
   }

   public V1beta1SchedulingAPIGroupDSL v1beta1() {
      return (V1beta1SchedulingAPIGroupDSL)this.adapt(V1beta1SchedulingAPIGroupClient.class);
   }

   public SchedulingAPIGroupClient newInstance() {
      return new SchedulingAPIGroupClient();
   }
}
