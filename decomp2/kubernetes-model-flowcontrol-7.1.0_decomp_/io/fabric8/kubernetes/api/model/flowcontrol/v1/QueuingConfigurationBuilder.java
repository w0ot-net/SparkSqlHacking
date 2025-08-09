package io.fabric8.kubernetes.api.model.flowcontrol.v1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class QueuingConfigurationBuilder extends QueuingConfigurationFluent implements VisitableBuilder {
   QueuingConfigurationFluent fluent;

   public QueuingConfigurationBuilder() {
      this(new QueuingConfiguration());
   }

   public QueuingConfigurationBuilder(QueuingConfigurationFluent fluent) {
      this(fluent, new QueuingConfiguration());
   }

   public QueuingConfigurationBuilder(QueuingConfigurationFluent fluent, QueuingConfiguration instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public QueuingConfigurationBuilder(QueuingConfiguration instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public QueuingConfiguration build() {
      QueuingConfiguration buildable = new QueuingConfiguration(this.fluent.getHandSize(), this.fluent.getQueueLengthLimit(), this.fluent.getQueues());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
