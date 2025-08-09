package org.fusesource.leveldbjni.internal;

import org.iq80.leveldb.Snapshot;

public class JniSnapshot implements Snapshot {
   private final NativeDB db;
   private final NativeSnapshot snapshot;

   JniSnapshot(NativeDB db, NativeSnapshot snapshot) {
      this.db = db;
      this.snapshot = snapshot;
   }

   public void close() {
      this.db.releaseSnapshot(this.snapshot);
   }

   NativeSnapshot snapshot() {
      return this.snapshot;
   }
}
