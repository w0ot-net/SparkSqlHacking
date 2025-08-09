package org.apache.ivy.plugins.lock;

import java.io.File;
import org.apache.ivy.core.module.descriptor.Artifact;

public interface LockStrategy {
   String getName();

   boolean lockArtifact(Artifact var1, File var2) throws InterruptedException;

   void unlockArtifact(Artifact var1, File var2);
}
