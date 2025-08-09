package org.apache.ivy.core.event.retrieve;

import java.io.File;
import org.apache.ivy.core.report.ArtifactDownloadReport;

public class EndRetrieveArtifactEvent extends RetrieveArtifactEvent {
   public static final String NAME = "post-retrieve-artifact";

   public EndRetrieveArtifactEvent(ArtifactDownloadReport report, File destFile) {
      super("post-retrieve-artifact", report, destFile);
   }
}
