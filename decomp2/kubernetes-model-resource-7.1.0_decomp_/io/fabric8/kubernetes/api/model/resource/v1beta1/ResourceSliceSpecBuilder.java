package io.fabric8.kubernetes.api.model.resource.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ResourceSliceSpecBuilder extends ResourceSliceSpecFluent implements VisitableBuilder {
   ResourceSliceSpecFluent fluent;

   public ResourceSliceSpecBuilder() {
      this(new ResourceSliceSpec());
   }

   public ResourceSliceSpecBuilder(ResourceSliceSpecFluent fluent) {
      this(fluent, new ResourceSliceSpec());
   }

   public ResourceSliceSpecBuilder(ResourceSliceSpecFluent fluent, ResourceSliceSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ResourceSliceSpecBuilder(ResourceSliceSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ResourceSliceSpec build() {
      ResourceSliceSpec buildable = new ResourceSliceSpec(this.fluent.getAllNodes(), this.fluent.buildDevices(), this.fluent.getDriver(), this.fluent.getNodeName(), this.fluent.getNodeSelector(), this.fluent.buildPool());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
