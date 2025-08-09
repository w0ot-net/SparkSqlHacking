package org.apache.ivy.plugins.lock;

import java.io.File;
import org.apache.ivy.core.module.descriptor.Artifact;

public abstract class ArtifactLockStrategy extends FileBasedLockStrategy {
   protected ArtifactLockStrategy(FileBasedLockStrategy.FileLocker locker, boolean debugLocking) {
      super(locker, debugLocking);
   }

   public boolean lockArtifact(Artifact artifact, File artifactFileToDownload) throws InterruptedException {
      return this.acquireLock(new File(artifactFileToDownload.getAbsolutePath() + ".lck"));
   }

   public void unlockArtifact(Artifact artifact, File artifactFileToDownload) {
      this.releaseLock(new File(artifactFileToDownload.getAbsolutePath() + ".lck"));
   }
}
