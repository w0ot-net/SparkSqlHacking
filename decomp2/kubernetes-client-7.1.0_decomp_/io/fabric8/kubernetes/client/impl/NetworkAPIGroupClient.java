package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.networking.v1.NetworkPolicy;
import io.fabric8.kubernetes.api.model.networking.v1.NetworkPolicyList;
import io.fabric8.kubernetes.api.model.networking.v1beta1.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressList;
import io.fabric8.kubernetes.client.V1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class NetworkAPIGroupClient extends ClientAdapter implements NetworkAPIGroupDSL {
   public V1NetworkAPIGroupDSL v1() {
      return (V1NetworkAPIGroupDSL)this.adapt(V1NetworkAPIGroupClient.class);
   }

   public V1beta1NetworkAPIGroupDSL v1beta1() {
      return (V1beta1NetworkAPIGroupDSL)this.adapt(V1beta1NetworkAPIGroupClient.class);
   }

   public MixedOperation networkPolicies() {
      return this.resources(NetworkPolicy.class, NetworkPolicyList.class);
   }

   public MixedOperation ingress() {
      return this.ingresses();
   }

   public MixedOperation ingresses() {
      return this.resources(Ingress.class, IngressList.class);
   }

   public NetworkAPIGroupClient newInstance() {
      return new NetworkAPIGroupClient();
   }
}
