package io.fabric8.kubernetes.client.impl;

import io.fabric8.kubernetes.api.model.apps.ControllerRevision;
import io.fabric8.kubernetes.api.model.apps.ControllerRevisionList;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;
import io.fabric8.kubernetes.api.model.apps.DaemonSetList;
import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.DeploymentOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.ReplicaSetOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.apps.v1.StatefulSetOperationsImpl;
import io.fabric8.kubernetes.client.extension.ClientAdapter;

public class AppsAPIGroupClient extends ClientAdapter implements AppsAPIGroupDSL {
   public MixedOperation daemonSets() {
      return this.resources(DaemonSet.class, DaemonSetList.class);
   }

   public MixedOperation deployments() {
      return new DeploymentOperationsImpl(this.getClient());
   }

   public MixedOperation replicaSets() {
      return new ReplicaSetOperationsImpl(this.getClient());
   }

   public MixedOperation statefulSets() {
      return new StatefulSetOperationsImpl(this.getClient());
   }

   public MixedOperation controllerRevisions() {
      return this.resources(ControllerRevision.class, ControllerRevisionList.class);
   }

   public AppsAPIGroupClient newInstance() {
      return new AppsAPIGroupClient();
   }
}
