package io.fabric8.kubernetes.api.model.policy.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class SELinuxStrategyOptionsBuilder extends SELinuxStrategyOptionsFluent implements VisitableBuilder {
   SELinuxStrategyOptionsFluent fluent;

   public SELinuxStrategyOptionsBuilder() {
      this(new SELinuxStrategyOptions());
   }

   public SELinuxStrategyOptionsBuilder(SELinuxStrategyOptionsFluent fluent) {
      this(fluent, new SELinuxStrategyOptions());
   }

   public SELinuxStrategyOptionsBuilder(SELinuxStrategyOptionsFluent fluent, SELinuxStrategyOptions instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public SELinuxStrategyOptionsBuilder(SELinuxStrategyOptions instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public SELinuxStrategyOptions build() {
      SELinuxStrategyOptions buildable = new SELinuxStrategyOptions(this.fluent.getRule(), this.fluent.getSeLinuxOptions());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
