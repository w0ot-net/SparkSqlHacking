package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.networking.v1.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1.IngressClass;
import io.fabric8.kubernetes.api.model.networking.v1.IngressClassList;
import io.fabric8.kubernetes.api.model.networking.v1.IngressList;
import io.fabric8.kubernetes.api.model.networking.v1.NetworkPolicy;
import io.fabric8.kubernetes.api.model.networking.v1.NetworkPolicyList;
import io.fabric8.kubernetes.client.V1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1NetworkAPIGroupClient extends ClientAdapter implements V1NetworkAPIGroupDSL {
   public MixedOperation networkPolicies() {
      return this.resources(NetworkPolicy.class, NetworkPolicyList.class);
   }

   public MixedOperation ingresses() {
      return this.resources(Ingress.class, IngressList.class);
   }

   public MixedOperation ingressClasses() {
      return this.resources(IngressClass.class, IngressClassList.class);
   }

   public V1NetworkAPIGroupClient newInstance() {
      return new V1NetworkAPIGroupClient();
   }
}
