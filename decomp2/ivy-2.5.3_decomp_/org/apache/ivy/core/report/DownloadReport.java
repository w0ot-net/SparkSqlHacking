package org.apache.ivy.core.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.Artifact;

public class DownloadReport {
   private final Map artifacts = new HashMap();

   public void addArtifactReport(ArtifactDownloadReport adr) {
      this.artifacts.put(adr.getArtifact(), adr);
   }

   public ArtifactDownloadReport[] getArtifactsReports() {
      return (ArtifactDownloadReport[])this.artifacts.values().toArray(new ArtifactDownloadReport[this.artifacts.size()]);
   }

   public ArtifactDownloadReport[] getArtifactsReports(DownloadStatus status) {
      List<ArtifactDownloadReport> ret = new ArrayList(this.artifacts.size());

      for(ArtifactDownloadReport adr : this.artifacts.values()) {
         if (adr.getDownloadStatus() == status) {
            ret.add(adr);
         }
      }

      return (ArtifactDownloadReport[])ret.toArray(new ArtifactDownloadReport[ret.size()]);
   }

   public ArtifactDownloadReport getArtifactReport(Artifact artifact) {
      return (ArtifactDownloadReport)this.artifacts.get(artifact);
   }
}
