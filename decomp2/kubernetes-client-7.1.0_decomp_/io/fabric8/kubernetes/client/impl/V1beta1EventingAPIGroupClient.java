package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.events.v1beta1.Event;
import io.fabric8.kubernetes.api.model.events.v1beta1.EventList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1beta1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1EventingAPIGroupClient extends ClientAdapter implements V1beta1EventingAPIGroupDSL {
   public MixedOperation events() {
      return this.resources(Event.class, EventList.class);
   }

   public V1beta1EventingAPIGroupClient newInstance() {
      return new V1beta1EventingAPIGroupClient();
   }
}
