package io.fabric8.kubernetes.api.model.clusterapi.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class BootstrapBuilder extends BootstrapFluent implements VisitableBuilder {
   BootstrapFluent fluent;

   public BootstrapBuilder() {
      this(new Bootstrap());
   }

   public BootstrapBuilder(BootstrapFluent fluent) {
      this(fluent, new Bootstrap());
   }

   public BootstrapBuilder(BootstrapFluent fluent, Bootstrap instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public BootstrapBuilder(Bootstrap instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public Bootstrap build() {
      Bootstrap buildable = new Bootstrap(this.fluent.buildConfigRef(), this.fluent.getDataSecretName());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
