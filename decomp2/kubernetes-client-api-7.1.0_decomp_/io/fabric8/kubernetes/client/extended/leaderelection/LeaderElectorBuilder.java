package io.fabric8.kubernetes.client.extended.leaderelection;

import io.fabric8.kubernetes.client.KubernetesClient;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.Executor;

public class LeaderElectorBuilder {
   private final KubernetesClient client;
   private final Executor executor;
   private LeaderElectionConfig leaderElectionConfig;

   public LeaderElectorBuilder(KubernetesClient client, Executor executor) {
      this.client = client;
      this.executor = executor;
   }

   public LeaderElectorBuilder withConfig(LeaderElectionConfig leaderElectionConfig) {
      this.leaderElectionConfig = validate(leaderElectionConfig);
      return this;
   }

   public LeaderElector build() {
      return new LeaderElector(this.client, this.leaderElectionConfig, this.executor);
   }

   private static LeaderElectionConfig validate(LeaderElectionConfig leaderElectionConfig) {
      Objects.requireNonNull(leaderElectionConfig, "LeaderElectionConfig is required");
      Objects.requireNonNull(leaderElectionConfig.getName(), "name is required");
      Objects.requireNonNull(leaderElectionConfig.getLeaseDuration(), "leaseDuration is required");
      Objects.requireNonNull(leaderElectionConfig.getRenewDeadline(), "renewDeadLine is required");
      Objects.requireNonNull(leaderElectionConfig.getRetryPeriod(), "retryPeriod is required");
      Objects.requireNonNull(leaderElectionConfig.getLeaderCallbacks(), "leaderCallbacks are required");
      Objects.requireNonNull(leaderElectionConfig.getLock(), "lock is required");
      if (leaderElectionConfig.getLeaseDuration().compareTo(leaderElectionConfig.getRenewDeadline()) <= 0) {
         throw new IllegalArgumentException("leaseDuration must be greater than renewDeadLine");
      } else {
         Duration maxRetryPeriod = leaderElectionConfig.getRetryPeriod().plusMillis((long)Math.ceil((double)leaderElectionConfig.getRetryPeriod().toMillis() * LeaderElector.JITTER_FACTOR));
         if (leaderElectionConfig.getRenewDeadline().compareTo(maxRetryPeriod) <= 0) {
            throw new IllegalArgumentException("renewDeadline must be greater than retryPeriod + retryPeriod*JITTER_FACTOR");
         } else if (leaderElectionConfig.getLeaseDuration().toMillis() < 1L) {
            throw new IllegalArgumentException("leaseDuration must be greater than zero");
         } else if (leaderElectionConfig.getRenewDeadline().toMillis() < 1L) {
            throw new IllegalArgumentException("renewDeadline must be greater than zero");
         } else if (leaderElectionConfig.getRetryPeriod().toMillis() < 1L) {
            throw new IllegalArgumentException("retryPeriod must be greater than zero");
         } else {
            return leaderElectionConfig;
         }
      }
   }
}
