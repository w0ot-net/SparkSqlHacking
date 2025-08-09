package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IPAddressSpecBuilder extends IPAddressSpecFluent implements VisitableBuilder {
   IPAddressSpecFluent fluent;

   public IPAddressSpecBuilder() {
      this(new IPAddressSpec());
   }

   public IPAddressSpecBuilder(IPAddressSpecFluent fluent) {
      this(fluent, new IPAddressSpec());
   }

   public IPAddressSpecBuilder(IPAddressSpecFluent fluent, IPAddressSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IPAddressSpecBuilder(IPAddressSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IPAddressSpec build() {
      IPAddressSpec buildable = new IPAddressSpec(this.fluent.buildParentRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
