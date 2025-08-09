package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HostAliasBuilder extends HostAliasFluent implements VisitableBuilder {
   HostAliasFluent fluent;

   public HostAliasBuilder() {
      this(new HostAlias());
   }

   public HostAliasBuilder(HostAliasFluent fluent) {
      this(fluent, new HostAlias());
   }

   public HostAliasBuilder(HostAliasFluent fluent, HostAlias instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HostAliasBuilder(HostAlias instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HostAlias build() {
      HostAlias buildable = new HostAlias(this.fluent.getHostnames(), this.fluent.getIp());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
