package org.apache.ivy.plugins.lock;

public class CreateFileLockStrategy extends ArtifactLockStrategy {
   public CreateFileLockStrategy(boolean debugLocking) {
      super(new FileBasedLockStrategy.CreateFileLocker(debugLocking), debugLocking);
      this.setName("artifact-lock");
   }
}
