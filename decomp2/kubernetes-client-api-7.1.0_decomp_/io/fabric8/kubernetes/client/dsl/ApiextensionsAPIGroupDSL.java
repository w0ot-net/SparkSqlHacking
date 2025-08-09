package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.V1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1ApiextensionAPIGroupDSL;

public interface ApiextensionsAPIGroupDSL extends Client {
   V1ApiextensionAPIGroupDSL v1();

   V1beta1ApiextensionAPIGroupDSL v1beta1();
}
