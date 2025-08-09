package io.fabric8.kubernetes.client.extended.leaderelection.resourcelock;

import io.fabric8.kubernetes.client.KubernetesClient;

public interface Lock {
   String LEADER_ELECTION_RECORD_ANNOTATION_KEY = "control-plane.alpha.kubernetes.io/leader";

   LeaderElectionRecord get(KubernetesClient var1);

   void create(KubernetesClient var1, LeaderElectionRecord var2);

   void update(KubernetesClient var1, LeaderElectionRecord var2);

   String identity();

   String describe();
}
