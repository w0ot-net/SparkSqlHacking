package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.client.V1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.V1beta1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.ApiextensionsAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class ApiextensionsAPIGroupClient extends ClientAdapter implements ApiextensionsAPIGroupDSL {
   public V1ApiextensionAPIGroupDSL v1() {
      return (V1ApiextensionAPIGroupDSL)this.adapt(V1ApiextensionsAPIGroupClient.class);
   }

   public V1beta1ApiextensionAPIGroupDSL v1beta1() {
      return (V1beta1ApiextensionAPIGroupDSL)this.adapt(V1beta1ApiextensionsAPIGroupClient.class);
   }

   public ApiextensionsAPIGroupClient newInstance() {
      return new ApiextensionsAPIGroupClient();
   }
}
