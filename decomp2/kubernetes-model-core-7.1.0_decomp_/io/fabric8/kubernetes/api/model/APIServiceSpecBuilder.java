package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class APIServiceSpecBuilder extends APIServiceSpecFluent implements VisitableBuilder {
   APIServiceSpecFluent fluent;

   public APIServiceSpecBuilder() {
      this(new APIServiceSpec());
   }

   public APIServiceSpecBuilder(APIServiceSpecFluent fluent) {
      this(fluent, new APIServiceSpec());
   }

   public APIServiceSpecBuilder(APIServiceSpecFluent fluent, APIServiceSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public APIServiceSpecBuilder(APIServiceSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public APIServiceSpec build() {
      APIServiceSpec buildable = new APIServiceSpec(this.fluent.getCaBundle(), this.fluent.getGroup(), this.fluent.getGroupPriorityMinimum(), this.fluent.getInsecureSkipTLSVerify(), this.fluent.buildService(), this.fluent.getVersion(), this.fluent.getVersionPriority());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
