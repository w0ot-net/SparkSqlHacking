package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.scheduling.v1beta1.PriorityClass;
import io.fabric8.kubernetes.api.model.scheduling.v1beta1.PriorityClassList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1SchedulingAPIGroupClient extends ClientAdapter implements V1beta1SchedulingAPIGroupDSL {
   public NonNamespaceOperation priorityClasses() {
      return this.resources(PriorityClass.class, PriorityClassList.class);
   }

   public V1beta1SchedulingAPIGroupClient newInstance() {
      return new V1beta1SchedulingAPIGroupClient();
   }
}
