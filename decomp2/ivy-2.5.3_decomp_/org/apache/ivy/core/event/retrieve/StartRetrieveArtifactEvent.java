package org.apache.ivy.core.event.retrieve;

import java.io.File;
import org.apache.ivy.core.report.ArtifactDownloadReport;

public class StartRetrieveArtifactEvent extends RetrieveArtifactEvent {
   public static final String NAME = "pre-retrieve-artifact";

   public StartRetrieveArtifactEvent(ArtifactDownloadReport report, File destFile) {
      super("pre-retrieve-artifact", report, destFile);
   }
}
