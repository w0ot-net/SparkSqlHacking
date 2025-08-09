package io.fabric8.kubernetes.api.model.autoscaling.v2beta2;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class HPAScalingPolicyBuilder extends HPAScalingPolicyFluent implements VisitableBuilder {
   HPAScalingPolicyFluent fluent;

   public HPAScalingPolicyBuilder() {
      this(new HPAScalingPolicy());
   }

   public HPAScalingPolicyBuilder(HPAScalingPolicyFluent fluent) {
      this(fluent, new HPAScalingPolicy());
   }

   public HPAScalingPolicyBuilder(HPAScalingPolicyFluent fluent, HPAScalingPolicy instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public HPAScalingPolicyBuilder(HPAScalingPolicy instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public HPAScalingPolicy build() {
      HPAScalingPolicy buildable = new HPAScalingPolicy(this.fluent.getPeriodSeconds(), this.fluent.getType(), this.fluent.getValue());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
