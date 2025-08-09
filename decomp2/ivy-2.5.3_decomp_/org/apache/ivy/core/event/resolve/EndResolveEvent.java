package org.apache.ivy.core.event.resolve;

import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.report.ResolveReport;

public class EndResolveEvent extends ResolveEvent {
   public static final String NAME = "post-resolve";
   private ResolveReport report;

   public EndResolveEvent(ModuleDescriptor md, String[] confs, ResolveReport report) {
      super("post-resolve", md, confs);
      this.report = report;
      this.addAttribute("resolve-id", String.valueOf(report.getResolveId()));
      this.addAttribute("nb-dependencies", String.valueOf(report.getDependencies().size()));
      this.addAttribute("nb-artifacts", String.valueOf(report.getArtifacts().size()));
      this.addAttribute("resolve-duration", String.valueOf(report.getResolveTime()));
      this.addAttribute("download-duration", String.valueOf(report.getDownloadTime()));
      this.addAttribute("download-size", String.valueOf(report.getDownloadSize()));
   }

   public ResolveReport getReport() {
      return this.report;
   }
}
