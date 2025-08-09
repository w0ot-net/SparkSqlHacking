package io.fabric8.kubernetes.api.model.networking.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class IPAddressBuilder extends IPAddressFluent implements VisitableBuilder {
   IPAddressFluent fluent;

   public IPAddressBuilder() {
      this(new IPAddress());
   }

   public IPAddressBuilder(IPAddressFluent fluent) {
      this(fluent, new IPAddress());
   }

   public IPAddressBuilder(IPAddressFluent fluent, IPAddress instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public IPAddressBuilder(IPAddress instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public IPAddress build() {
      IPAddress buildable = new IPAddress(this.fluent.getApiVersion(), this.fluent.getKind(), this.fluent.buildMetadata(), this.fluent.buildSpec());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
