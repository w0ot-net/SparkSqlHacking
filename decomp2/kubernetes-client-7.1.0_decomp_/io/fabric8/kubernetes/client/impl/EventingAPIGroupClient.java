package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.dsl.EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1beta1EventingAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class EventingAPIGroupClient extends ClientAdapter implements EventingAPIGroupDSL {
   public V1EventingAPIGroupDSL v1() {
      return (V1EventingAPIGroupDSL)this.adapt(V1EventingAPIGroupClient.class);
   }

   public V1beta1EventingAPIGroupDSL v1beta1() {
      return (V1beta1EventingAPIGroupDSL)this.adapt(V1beta1EventingAPIGroupClient.class);
   }

   public EventingAPIGroupClient newInstance() {
      return new EventingAPIGroupClient();
   }
}
