package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ImageVolumeSourceBuilder extends ImageVolumeSourceFluent implements VisitableBuilder {
   ImageVolumeSourceFluent fluent;

   public ImageVolumeSourceBuilder() {
      this(new ImageVolumeSource());
   }

   public ImageVolumeSourceBuilder(ImageVolumeSourceFluent fluent) {
      this(fluent, new ImageVolumeSource());
   }

   public ImageVolumeSourceBuilder(ImageVolumeSourceFluent fluent, ImageVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ImageVolumeSourceBuilder(ImageVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ImageVolumeSource build() {
      ImageVolumeSource buildable = new ImageVolumeSource(this.fluent.getPullPolicy(), this.fluent.getReference());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
