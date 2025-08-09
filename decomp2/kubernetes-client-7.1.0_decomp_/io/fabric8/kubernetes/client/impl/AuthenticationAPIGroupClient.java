package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.V1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.V1Beta1AuthenticationAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class AuthenticationAPIGroupClient extends ClientAdapter implements AuthenticationAPIGroupDSL {
   public V1AuthenticationAPIGroupDSL v1() {
      return (V1AuthenticationAPIGroupDSL)this.adapt(V1AuthenticationAPIGroupClient.class);
   }

   public V1Beta1AuthenticationAPIGroupDSL v1beta1() {
      return (V1Beta1AuthenticationAPIGroupDSL)this.adapt(V1Beta1AuthenticationAPIGroupClient.class);
   }

   public AuthenticationAPIGroupClient newInstance() {
      return new AuthenticationAPIGroupClient();
   }
}
