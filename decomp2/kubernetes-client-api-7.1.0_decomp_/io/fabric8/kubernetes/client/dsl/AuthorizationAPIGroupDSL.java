package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1AuthorizationAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1AuthorizationAPIGroupDSL;

public interface AuthorizationAPIGroupDSL extends Client {
   V1AuthorizationAPIGroupDSL v1();

   V1beta1AuthorizationAPIGroupDSL v1beta1();
}
