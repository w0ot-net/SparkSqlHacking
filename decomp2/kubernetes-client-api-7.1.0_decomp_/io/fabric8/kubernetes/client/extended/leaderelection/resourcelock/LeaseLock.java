package io.fabric8.kubernetes.client.extended.leaderelection.resourcelock;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.coordination.v1.Lease;
import io.fabric8.kubernetes.api.model.coordination.v1.LeaseBuilder;
import io.fabric8.kubernetes.api.model.coordination.v1.LeaseFluent;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class LeaseLock extends ResourceLock {
   public LeaseLock(String leaseNamespace, String leaseName, String identity) {
      super(leaseNamespace, leaseName, identity);
   }

   public LeaseLock(ObjectMeta meta, String identity) {
      super(meta, identity);
   }

   protected Class getKind() {
      return Lease.class;
   }

   protected Lease toResource(LeaderElectionRecord leaderElectionRecord, ObjectMetaBuilder meta) {
      return ((LeaseBuilder)((LeaseFluent.SpecNested)((LeaseFluent.SpecNested)((LeaseFluent.SpecNested)((LeaseFluent.SpecNested)((LeaseFluent.SpecNested)((LeaseBuilder)(new LeaseBuilder()).withMetadata(meta.build())).withNewSpec().withHolderIdentity(leaderElectionRecord.getHolderIdentity())).withLeaseDurationSeconds((int)leaderElectionRecord.getLeaseDuration().get(ChronoUnit.SECONDS))).withAcquireTime(leaderElectionRecord.getAcquireTime())).withRenewTime(leaderElectionRecord.getRenewTime())).withLeaseTransitions(leaderElectionRecord.getLeaderTransitions())).endSpec()).build();
   }

   protected LeaderElectionRecord toRecord(Lease resource) {
      return (LeaderElectionRecord)Optional.ofNullable(resource.getSpec()).map((spec) -> new LeaderElectionRecord(spec.getHolderIdentity(), Duration.ofSeconds((long)spec.getLeaseDurationSeconds()), spec.getAcquireTime(), spec.getRenewTime(), (Integer)Optional.ofNullable(spec.getLeaseTransitions()).orElse(0))).orElse((Object)null);
   }
}
