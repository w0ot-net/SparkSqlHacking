package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.rbac.ClusterRole;
import io.fabric8.kubernetes.api.model.rbac.ClusterRoleBinding;
import io.fabric8.kubernetes.api.model.rbac.ClusterRoleBindingList;
import io.fabric8.kubernetes.api.model.rbac.ClusterRoleList;
import io.fabric8.kubernetes.api.model.rbac.Role;
import io.fabric8.kubernetes.api.model.rbac.RoleBinding;
import io.fabric8.kubernetes.api.model.rbac.RoleBindingList;
import io.fabric8.kubernetes.api.model.rbac.RoleList;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.RbacAPIGroupDSL;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class RbacAPIGroupClient extends ClientAdapter implements RbacAPIGroupDSL {
   public MixedOperation roles() {
      return this.resources(Role.class, RoleList.class);
   }

   public MixedOperation roleBindings() {
      return this.resources(RoleBinding.class, RoleBindingList.class);
   }

   public NonNamespaceOperation clusterRoles() {
      return this.resources(ClusterRole.class, ClusterRoleList.class);
   }

   public NonNamespaceOperation clusterRoleBindings() {
      return this.resources(ClusterRoleBinding.class, ClusterRoleBindingList.class);
   }

   public RbacAPIGroupClient newInstance() {
      return new RbacAPIGroupClient();
   }
}
