package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.events.v1.Event;
import io.fabric8.kubernetes.api.model.events.v1.EventList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.V1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1EventingAPIGroupClient extends ClientAdapter implements V1EventingAPIGroupDSL {
   public MixedOperation events() {
      return this.resources(Event.class, EventList.class);
   }

   public V1EventingAPIGroupClient newInstance() {
      return new V1EventingAPIGroupClient();
   }
}
