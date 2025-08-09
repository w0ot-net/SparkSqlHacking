package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1AuthenticationAPIGroupDSL;

public interface AuthenticationAPIGroupDSL extends Client {
   V1AuthenticationAPIGroupDSL v1();

   V1Beta1AuthenticationAPIGroupDSL v1beta1();
}
