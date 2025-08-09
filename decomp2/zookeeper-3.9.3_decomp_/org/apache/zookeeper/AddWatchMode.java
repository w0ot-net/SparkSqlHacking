package org.apache.zookeeper;

public enum AddWatchMode {
   PERSISTENT(0),
   PERSISTENT_RECURSIVE(1);

   private final int mode;

   public int getMode() {
      return this.mode;
   }

   private AddWatchMode(int mode) {
      this.mode = mode;
   }
}
