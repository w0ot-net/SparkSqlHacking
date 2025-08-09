package io.fabric8.kubernetes.client.extended.leaderelection;

import io.fabric8.kubernetes.client.extended.leaderelection.resourcelock.Lock;
import java.time.Duration;

public class LeaderElectionConfig {
   private final Lock lock;
   private final Duration leaseDuration;
   private final Duration renewDeadline;
   private final Duration retryPeriod;
   private final LeaderCallbacks leaderCallbacks;
   private final boolean releaseOnCancel;
   private final String name;

   public LeaderElectionConfig(Lock lock, Duration leaseDuration, Duration renewDeadline, Duration retryPeriod, LeaderCallbacks leaderCallbacks, boolean releaseOnCancel, String name) {
      this.lock = lock;
      this.leaseDuration = leaseDuration;
      this.renewDeadline = renewDeadline;
      this.retryPeriod = retryPeriod;
      this.leaderCallbacks = leaderCallbacks;
      this.releaseOnCancel = releaseOnCancel;
      this.name = name;
   }

   public Lock getLock() {
      return this.lock;
   }

   public Duration getLeaseDuration() {
      return this.leaseDuration;
   }

   public Duration getRenewDeadline() {
      return this.renewDeadline;
   }

   public Duration getRetryPeriod() {
      return this.retryPeriod;
   }

   public LeaderCallbacks getLeaderCallbacks() {
      return this.leaderCallbacks;
   }

   public boolean isReleaseOnCancel() {
      return this.releaseOnCancel;
   }

   public String getName() {
      return this.name;
   }
}
