package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.scheduling.v1.PriorityClass;
import io.fabric8.kubernetes.api.model.scheduling.v1.PriorityClassList;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.V1SchedulingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1SchedulingAPIGroupClient extends ClientAdapter implements V1SchedulingAPIGroupDSL {
   public NonNamespaceOperation priorityClasses() {
      return this.resources(PriorityClass.class, PriorityClassList.class);
   }

   public V1SchedulingAPIGroupClient newInstance() {
      return new V1SchedulingAPIGroupClient();
   }
}
