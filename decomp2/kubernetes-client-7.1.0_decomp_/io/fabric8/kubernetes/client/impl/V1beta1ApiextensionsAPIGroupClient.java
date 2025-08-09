package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinition;
import io.fabric8.kubernetes.api.model.apiextensions.v1beta1.CustomResourceDefinitionList;
import io.fabric8.kubernetes.client.V1beta1ApiextensionAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class V1beta1ApiextensionsAPIGroupClient extends ClientAdapter implements V1beta1ApiextensionAPIGroupDSL {
   public MixedOperation customResourceDefinitions() {
      return this.resources(CustomResourceDefinition.class, CustomResourceDefinitionList.class);
   }

   public V1beta1ApiextensionsAPIGroupClient newInstance() {
      return new V1beta1ApiextensionsAPIGroupClient();
   }
}
