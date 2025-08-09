package org.apache.ivy.core.event.download;

import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.module.descriptor.Artifact;

public abstract class DownloadEvent extends IvyEvent {
   private Artifact artifact;

   public DownloadEvent(String name, Artifact artifact) {
      super(name);
      this.artifact = artifact;
      this.addArtifactAttributes(this.artifact);
   }

   protected void addArtifactAttributes(Artifact artifact) {
      this.addMridAttributes(artifact.getModuleRevisionId());
      this.addAttributes(artifact.getAttributes());
      this.addAttribute("metadata", String.valueOf(artifact.isMetadata()));
   }

   public Artifact getArtifact() {
      return this.artifact;
   }
}
