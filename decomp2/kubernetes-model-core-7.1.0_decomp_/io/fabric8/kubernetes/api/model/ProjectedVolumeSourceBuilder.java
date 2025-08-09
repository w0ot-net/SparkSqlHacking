package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.VisitableBuilder;

public class ProjectedVolumeSourceBuilder extends ProjectedVolumeSourceFluent implements VisitableBuilder {
   ProjectedVolumeSourceFluent fluent;

   public ProjectedVolumeSourceBuilder() {
      this(new ProjectedVolumeSource());
   }

   public ProjectedVolumeSourceBuilder(ProjectedVolumeSourceFluent fluent) {
      this(fluent, new ProjectedVolumeSource());
   }

   public ProjectedVolumeSourceBuilder(ProjectedVolumeSourceFluent fluent, ProjectedVolumeSource instance) {
      this.fluent = fluent;
      fluent.copyInstance(instance);
   }

   public ProjectedVolumeSourceBuilder(ProjectedVolumeSource instance) {
      this.fluent = this;
      this.copyInstance(instance);
   }

   public ProjectedVolumeSource build() {
      ProjectedVolumeSource buildable = new ProjectedVolumeSource(this.fluent.getDefaultMode(), this.fluent.buildSources());
      buildable.setAdditionalProperties(this.fluent.getAdditionalProperties());
      return buildable;
   }
}
