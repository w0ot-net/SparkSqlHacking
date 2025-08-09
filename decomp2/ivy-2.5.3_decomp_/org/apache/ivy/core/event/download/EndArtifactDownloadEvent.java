package org.apache.ivy.core.event.download;

import java.io.File;
import org.apache.ivy.core.cache.ArtifactOrigin;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public class EndArtifactDownloadEvent extends DownloadEvent {
   public static final String NAME = "post-download-artifact";
   private DependencyResolver resolver;
   private ArtifactDownloadReport report;

   public EndArtifactDownloadEvent(DependencyResolver resolver, Artifact artifact, ArtifactDownloadReport report, File dest) {
      super("post-download-artifact", artifact);
      this.resolver = resolver;
      this.report = report;
      this.addAttribute("resolver", this.resolver.getName());
      this.addAttribute("status", this.report.getDownloadStatus().toString());
      this.addAttribute("details", this.report.getDownloadDetails());
      this.addAttribute("size", String.valueOf(this.report.getSize()));
      this.addAttribute("file", dest.getAbsolutePath());
      this.addAttribute("duration", String.valueOf(this.report.getDownloadTimeMillis()));
      ArtifactOrigin origin = report.getArtifactOrigin();
      if (!ArtifactOrigin.isUnknown(origin)) {
         this.addAttribute("origin", origin.getLocation());
         this.addAttribute("local", String.valueOf(origin.isLocal()));
      } else {
         this.addAttribute("origin", "");
         this.addAttribute("local", "");
      }

   }

   public ArtifactDownloadReport getReport() {
      return this.report;
   }

   public DependencyResolver getResolver() {
      return this.resolver;
   }
}
