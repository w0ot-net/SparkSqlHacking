package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class GroupVersionForDiscoveryBuilder extends GroupVersionForDiscoveryFluent implements VisitableBuilder {
   GroupVersionForDiscoveryFluent fluent;

   public GroupVersionForDiscoveryBuilder() {
      this(new GroupVersionForDiscovery());
   }

   public GroupVersionForDiscoveryBuilder(GroupVersionForDiscoveryFluent fluent) {
      this(fluent, new GroupVersionForDiscovery());
   }

   public GroupVersionForDiscoveryBuilder(GroupVersionForDiscoveryFluent fluent, GroupVersionForDiscovery instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public GroupVersionForDiscoveryBuilder(GroupVersionForDiscovery instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public GroupVersionForDiscovery build() {
      GroupVersionForDiscovery buildable = new GroupVersionForDiscovery(this.fluent.getGroupVersion(), this.fluent.getVersion());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
