package org.iq80.leveldb;

public class ReadOptions {
   private boolean verifyChecksums = false;
   private boolean fillCache = true;
   private Snapshot snapshot;

   public Snapshot snapshot() {
      return this.snapshot;
   }

   public ReadOptions snapshot(Snapshot snapshot) {
      this.snapshot = snapshot;
      return this;
   }

   public boolean fillCache() {
      return this.fillCache;
   }

   public ReadOptions fillCache(boolean fillCache) {
      this.fillCache = fillCache;
      return this;
   }

   public boolean verifyChecksums() {
      return this.verifyChecksums;
   }

   public ReadOptions verifyChecksums(boolean verifyChecksums) {
      this.verifyChecksums = verifyChecksums;
      return this;
   }
}
