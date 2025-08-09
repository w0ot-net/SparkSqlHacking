package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.extensions.DaemonSet;
import io.fabric8.kubernetes.api.model.extensions.DaemonSetList;
import io.fabric8.kubernetes.api.model.extensions.Ingress;
import io.fabric8.kubernetes.api.model.extensions.IngressList;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicy;
import io.fabric8.kubernetes.api.model.extensions.NetworkPolicyList;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicy;
import io.fabric8.kubernetes.api.model.policy.v1beta1.PodSecurityPolicyList;
import io.fabric8.kubernetes.client.dsl.ExtensionsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.internal.batch.v1.JobOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1.DeploymentOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.extensions.v1beta1.ReplicaSetOperationsImpl;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class ExtensionsAPIGroupClient extends ClientAdapter implements ExtensionsAPIGroupDSL {
   /** @deprecated */
   @Deprecated
   public MixedOperation daemonSets() {
      return this.resources(DaemonSet.class, DaemonSetList.class);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation deployments() {
      return new DeploymentOperationsImpl(this);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation ingress() {
      return this.ingresses();
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation ingresses() {
      return this.resources(Ingress.class, IngressList.class);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation jobs() {
      return new JobOperationsImpl(this);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation networkPolicies() {
      return this.resources(NetworkPolicy.class, NetworkPolicyList.class);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation podSecurityPolicies() {
      return this.resources(PodSecurityPolicy.class, PodSecurityPolicyList.class);
   }

   /** @deprecated */
   @Deprecated
   public MixedOperation replicaSets() {
      return new ReplicaSetOperationsImpl(this);
   }

   public ExtensionsAPIGroupClient newInstance() {
      return new ExtensionsAPIGroupClient();
   }
}
