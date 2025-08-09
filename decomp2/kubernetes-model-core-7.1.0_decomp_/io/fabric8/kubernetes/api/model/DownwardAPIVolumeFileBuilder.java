package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class DownwardAPIVolumeFileBuilder extends DownwardAPIVolumeFileFluent implements VisitableBuilder {
   DownwardAPIVolumeFileFluent fluent;

   public DownwardAPIVolumeFileBuilder() {
      this(new DownwardAPIVolumeFile());
   }

   public DownwardAPIVolumeFileBuilder(DownwardAPIVolumeFileFluent fluent) {
      this(fluent, new DownwardAPIVolumeFile());
   }

   public DownwardAPIVolumeFileBuilder(DownwardAPIVolumeFileFluent fluent, DownwardAPIVolumeFile instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public DownwardAPIVolumeFileBuilder(DownwardAPIVolumeFile instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public DownwardAPIVolumeFile build() {
      DownwardAPIVolumeFile buildable = new DownwardAPIVolumeFile(this.fluent.buildFieldRef(), this.fluent.getMode(), this.fluent.getPath(), this.fluent.buildResourceFieldRef());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
