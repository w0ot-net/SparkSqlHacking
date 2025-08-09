package org.iq80.leveldb;

public class WriteOptions {
   private boolean sync;
   private boolean snapshot;

   public boolean sync() {
      return this.sync;
   }

   public WriteOptions sync(boolean sync) {
      this.sync = sync;
      return this;
   }

   public boolean snapshot() {
      return this.snapshot;
   }

   public WriteOptions snapshot(boolean snapshot) {
      this.snapshot = snapshot;
      return this;
   }
}
