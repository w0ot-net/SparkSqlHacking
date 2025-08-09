package io.fabric8.kubernetes.api.model.storage.v1beta1;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class CSIDriverSpecBuilder extends CSIDriverSpecFluent implements VisitableBuilder {
   CSIDriverSpecFluent fluent;

   public CSIDriverSpecBuilder() {
      this(new CSIDriverSpec());
   }

   public CSIDriverSpecBuilder(CSIDriverSpecFluent fluent) {
      this(fluent, new CSIDriverSpec());
   }

   public CSIDriverSpecBuilder(CSIDriverSpecFluent fluent, CSIDriverSpec instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public CSIDriverSpecBuilder(CSIDriverSpec instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public CSIDriverSpec build() {
      CSIDriverSpec buildable = new CSIDriverSpec(this.fluent.getAttachRequired(), this.fluent.getFsGroupPolicy(), this.fluent.getPodInfoOnMount(), this.fluent.getRequiresRepublish(), this.fluent.getStorageCapacity(), this.fluent.buildTokenRequests(), this.fluent.getVolumeLifecycleModes());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
