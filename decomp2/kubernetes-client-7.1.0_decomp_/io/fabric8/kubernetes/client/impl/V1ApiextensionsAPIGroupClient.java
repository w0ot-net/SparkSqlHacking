package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.V1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1ApiextensionsAPIGroupClient extends ClientAdapter implements V1ApiextensionAPIGroupDSL {
   public MixedOperation customResourceDefinitions() {
      return this.resources(CustomResourceDefinition.class, CustomResourceDefinitionList.class);
   }

   public V1ApiextensionsAPIGroupClient newInstance() {
      return new V1ApiextensionsAPIGroupClient();
   }
}
