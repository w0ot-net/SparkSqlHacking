package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.V1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class AuthorizationAPIGroupClient extends ClientAdapter implements AuthorizationAPIGroupDSL {
   public V1AuthorizationAPIGroupDSL v1() {
      return (V1AuthorizationAPIGroupDSL)this.adapt(V1AuthorizationAPIGroupClient.class);
   }

   public V1beta1AuthorizationAPIGroupDSL v1beta1() {
      return (V1beta1AuthorizationAPIGroupDSL)this.adapt(V1beta1AuthorizationAPIGroupClient.class);
   }

   public AuthorizationAPIGroupClient newInstance() {
      return new AuthorizationAPIGroupClient();
   }
}
