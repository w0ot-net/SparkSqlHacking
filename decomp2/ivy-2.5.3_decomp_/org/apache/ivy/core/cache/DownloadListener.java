package org.apache.ivy.core.cache;

import java.io.File;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.plugins.resolver.util.ResolvedResource;

public interface DownloadListener {
   void needArtifact(RepositoryCacheManager var1, Artifact var2);

   void startArtifactDownload(RepositoryCacheManager var1, ResolvedResource var2, Artifact var3, ArtifactOrigin var4);

   void endArtifactDownload(RepositoryCacheManager var1, Artifact var2, ArtifactDownloadReport var3, File var4);
}
