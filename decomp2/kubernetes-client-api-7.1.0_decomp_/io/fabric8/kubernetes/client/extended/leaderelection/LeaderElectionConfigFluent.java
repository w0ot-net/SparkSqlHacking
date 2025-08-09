package io.fabric8.kubernetes.client.extended.leaderelection;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.client.extended.leaderelection.resourcelock.Lock;
import java.time.Duration;
import java.util.Objects;

public class LeaderElectionConfigFluent extends BaseFluent {
   private Lock lock;
   private Duration leaseDuration;
   private Duration renewDeadline;
   private Duration retryPeriod;
   private LeaderCallbacks leaderCallbacks;
   private boolean releaseOnCancel;
   private String name;

   public LeaderElectionConfigFluent() {
   }

   public LeaderElectionConfigFluent(LeaderElectionConfig instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(LeaderElectionConfig instance) {
      if (instance != null) {
         this.withLock(instance.getLock());
         this.withLeaseDuration(instance.getLeaseDuration());
         this.withRenewDeadline(instance.getRenewDeadline());
         this.withRetryPeriod(instance.getRetryPeriod());
         this.withLeaderCallbacks(instance.getLeaderCallbacks());
         this.withReleaseOnCancel(instance.isReleaseOnCancel());
         this.withName(instance.getName());
      }

   }

   public Lock getLock() {
      return this.lock;
   }

   public LeaderElectionConfigFluent withLock(Lock lock) {
      this.lock = lock;
      return this;
   }

   public boolean hasLock() {
      return this.lock != null;
   }

   public Duration getLeaseDuration() {
      return this.leaseDuration;
   }

   public LeaderElectionConfigFluent withLeaseDuration(Duration leaseDuration) {
      this.leaseDuration = leaseDuration;
      return this;
   }

   public boolean hasLeaseDuration() {
      return this.leaseDuration != null;
   }

   public Duration getRenewDeadline() {
      return this.renewDeadline;
   }

   public LeaderElectionConfigFluent withRenewDeadline(Duration renewDeadline) {
      this.renewDeadline = renewDeadline;
      return this;
   }

   public boolean hasRenewDeadline() {
      return this.renewDeadline != null;
   }

   public Duration getRetryPeriod() {
      return this.retryPeriod;
   }

   public LeaderElectionConfigFluent withRetryPeriod(Duration retryPeriod) {
      this.retryPeriod = retryPeriod;
      return this;
   }

   public boolean hasRetryPeriod() {
      return this.retryPeriod != null;
   }

   public LeaderCallbacks getLeaderCallbacks() {
      return this.leaderCallbacks;
   }

   public LeaderElectionConfigFluent withLeaderCallbacks(LeaderCallbacks leaderCallbacks) {
      this.leaderCallbacks = leaderCallbacks;
      return this;
   }

   public boolean hasLeaderCallbacks() {
      return this.leaderCallbacks != null;
   }

   public boolean isReleaseOnCancel() {
      return this.releaseOnCancel;
   }

   public LeaderElectionConfigFluent withReleaseOnCancel(boolean releaseOnCancel) {
      this.releaseOnCancel = releaseOnCancel;
      return this;
   }

   public boolean hasReleaseOnCancel() {
      return true;
   }

   public String getName() {
      return this.name;
   }

   public LeaderElectionConfigFluent withName(String name) {
      this.name = name;
      return this;
   }

   public boolean hasName() {
      return this.name != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            LeaderElectionConfigFluent that = (LeaderElectionConfigFluent)o;
            if (!Objects.equals(this.lock, that.lock)) {
               return false;
            } else if (!Objects.equals(this.leaseDuration, that.leaseDuration)) {
               return false;
            } else if (!Objects.equals(this.renewDeadline, that.renewDeadline)) {
               return false;
            } else if (!Objects.equals(this.retryPeriod, that.retryPeriod)) {
               return false;
            } else if (!Objects.equals(this.leaderCallbacks, that.leaderCallbacks)) {
               return false;
            } else if (this.releaseOnCancel != that.releaseOnCancel) {
               return false;
            } else {
               return Objects.equals(this.name, that.name);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.lock, this.leaseDuration, this.renewDeadline, this.retryPeriod, this.leaderCallbacks, this.releaseOnCancel, this.name, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.lock != null) {
         sb.append("lock:");
         sb.append(this.lock + ",");
      }

      if (this.leaseDuration != null) {
         sb.append("leaseDuration:");
         sb.append(this.leaseDuration + ",");
      }

      if (this.renewDeadline != null) {
         sb.append("renewDeadline:");
         sb.append(this.renewDeadline + ",");
      }

      if (this.retryPeriod != null) {
         sb.append("retryPeriod:");
         sb.append(this.retryPeriod + ",");
      }

      if (this.leaderCallbacks != null) {
         sb.append("leaderCallbacks:");
         sb.append(this.leaderCallbacks + ",");
      }

      sb.append("releaseOnCancel:");
      sb.append(this.releaseOnCancel + ",");
      if (this.name != null) {
         sb.append("name:");
         sb.append(this.name);
      }

      sb.append("}");
      return sb.toString();
   }

   public LeaderElectionConfigFluent withReleaseOnCancel() {
      return this.withReleaseOnCancel(true);
   }
}
