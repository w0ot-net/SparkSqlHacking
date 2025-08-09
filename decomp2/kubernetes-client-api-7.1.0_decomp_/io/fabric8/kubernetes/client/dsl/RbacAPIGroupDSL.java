package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface RbacAPIGroupDSL extends Client {
   MixedOperation roles();

   MixedOperation roleBindings();

   NonNamespaceOperation clusterRoles();

   NonNamespaceOperation clusterRoleBindings();
}
