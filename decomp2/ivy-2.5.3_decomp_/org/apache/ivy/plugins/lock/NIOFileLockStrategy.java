package org.apache.ivy.plugins.lock;

public class NIOFileLockStrategy extends ArtifactLockStrategy {
   public NIOFileLockStrategy(boolean debugLocking) {
      super(new FileBasedLockStrategy.NIOFileLocker(debugLocking), debugLocking);
      this.setName("artifact-lock-nio");
   }
}
