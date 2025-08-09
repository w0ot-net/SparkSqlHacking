package io.fabric8.kubernetes.client;

import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;

public interface V1ApiextensionAPIGroupDSL extends Client {
   NonNamespaceOperation customResourceDefinitions();
}
