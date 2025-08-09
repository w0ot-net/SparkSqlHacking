package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface V1FlowControlAPIGroupDSL extends Client {
   NonNamespaceOperation flowSchema();

   NonNamespaceOperation priorityLevelConfigurations();
}
