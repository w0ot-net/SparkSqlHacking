package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class AWSElasticBlockStoreVolumeSourceBuilder extends AWSElasticBlockStoreVolumeSourceFluent implements VisitableBuilder {
   AWSElasticBlockStoreVolumeSourceFluent fluent;

   public AWSElasticBlockStoreVolumeSourceBuilder() {
      this(new AWSElasticBlockStoreVolumeSource());
   }

   public AWSElasticBlockStoreVolumeSourceBuilder(AWSElasticBlockStoreVolumeSourceFluent fluent) {
      this(fluent, new AWSElasticBlockStoreVolumeSource());
   }

   public AWSElasticBlockStoreVolumeSourceBuilder(AWSElasticBlockStoreVolumeSourceFluent fluent, AWSElasticBlockStoreVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public AWSElasticBlockStoreVolumeSourceBuilder(AWSElasticBlockStoreVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public AWSElasticBlockStoreVolumeSource build() {
      AWSElasticBlockStoreVolumeSource buildable = new AWSElasticBlockStoreVolumeSource(this.fluent.getFsType(), this.fluent.getPartition(), this.fluent.getReadOnly(), this.fluent.getVolumeID());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
