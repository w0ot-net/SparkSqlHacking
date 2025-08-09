package io.fabric8.kubernetes.client.extended.leaderelection;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class LeaderElectionConfigBuilder extends LeaderElectionConfigFluent implements VisitableBuilder {
   LeaderElectionConfigFluent fluent;

   public LeaderElectionConfigBuilder() {
      this.fluent = this;
   }

   public LeaderElectionConfigBuilder(LeaderElectionConfigFluent fluent) {
      this.fluent = fluent;
   }

   public LeaderElectionConfigBuilder(LeaderElectionConfigFluent fluent, LeaderElectionConfig instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public LeaderElectionConfigBuilder(LeaderElectionConfig instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public LeaderElectionConfig build() {
      LeaderElectionConfig buildable = new LeaderElectionConfig(this.fluent.getLock(), this.fluent.getLeaseDuration(), this.fluent.getRenewDeadline(), this.fluent.getRetryPeriod(), this.fluent.getLeaderCallbacks(), this.fluent.isReleaseOnCancel(), this.fluent.getName());
      return buildable;
   }
}
