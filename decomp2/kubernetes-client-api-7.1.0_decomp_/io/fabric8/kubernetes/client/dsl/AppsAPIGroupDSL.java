package io.fabric8.kubernetes.client.dsl;

import io.fabric8.kubernetes.client.Client;

public interface AppsAPIGroupDSL extends Client {
   MixedOperation daemonSets();

   MixedOperation deployments();

   MixedOperation replicaSets();

   MixedOperation statefulSets();

   MixedOperation controllerRevisions();
}
