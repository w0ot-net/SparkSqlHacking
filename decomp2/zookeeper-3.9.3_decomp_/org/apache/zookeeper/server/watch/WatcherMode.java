package org.apache.zookeeper.server.watch;

public enum WatcherMode {
   STANDARD(false, false),
   PERSISTENT(true, false),
   PERSISTENT_RECURSIVE(true, true);

   public static final WatcherMode DEFAULT_WATCHER_MODE = STANDARD;
   private final boolean isPersistent;
   private final boolean isRecursive;

   public static WatcherMode fromZooDef(int mode) {
      switch (mode) {
         case 0:
            return PERSISTENT;
         case 1:
            return PERSISTENT_RECURSIVE;
         default:
            throw new IllegalArgumentException("Unsupported mode: " + mode);
      }
   }

   private WatcherMode(boolean isPersistent, boolean isRecursive) {
      this.isPersistent = isPersistent;
      this.isRecursive = isRecursive;
   }

   public boolean isPersistent() {
      return this.isPersistent;
   }

   public boolean isRecursive() {
      return this.isRecursive;
   }
}
