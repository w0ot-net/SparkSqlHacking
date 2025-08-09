package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.networking.v1beta1.IPAddress;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IPAddressList;
import io.fabric8.kubernetes.api.model.networking.v1beta1.Ingress;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressClass;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressClassList;
import io.fabric8.kubernetes.api.model.networking.v1beta1.IngressList;
import io.fabric8.kubernetes.client.V1beta1NetworkAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1NetworkAPIGroupClient extends ClientAdapter implements V1beta1NetworkAPIGroupDSL {
   public NonNamespaceOperation ipAddresses() {
      return this.resources(IPAddress.class, IPAddressList.class);
   }

   public MixedOperation ingresses() {
      return this.resources(Ingress.class, IngressList.class);
   }

   public MixedOperation ingressClasses() {
      return this.resources(IngressClass.class, IngressClassList.class);
   }

   public V1beta1NetworkAPIGroupClient newInstance() {
      return new V1beta1NetworkAPIGroupClient();
   }
}
