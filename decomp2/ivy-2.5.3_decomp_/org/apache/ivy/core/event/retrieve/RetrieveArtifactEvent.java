package org.apache.ivy.core.event.retrieve;

import java.io.File;
import org.apache.ivy.core.event.IvyEvent;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;

public class RetrieveArtifactEvent extends IvyEvent {
   private ArtifactDownloadReport report;
   private File destFile;

   public RetrieveArtifactEvent(String name, ArtifactDownloadReport report, File destFile) {
      super(name);
      this.addArtifactAttributes(report.getArtifact());
      this.report = report;
      this.destFile = destFile;
      this.addAttribute("from", report.getLocalFile().getAbsolutePath());
      this.addAttribute("to", destFile.getAbsolutePath());
      this.addAttribute("size", String.valueOf(destFile.length()));
   }

   protected void addArtifactAttributes(Artifact artifact) {
      this.addMridAttributes(artifact.getModuleRevisionId());
      this.addAttributes(artifact.getAttributes());
      this.addAttribute("metadata", String.valueOf(artifact.isMetadata()));
   }

   public File getDestFile() {
      return this.destFile;
   }

   public ArtifactDownloadReport getReport() {
      return this.report;
   }
}
