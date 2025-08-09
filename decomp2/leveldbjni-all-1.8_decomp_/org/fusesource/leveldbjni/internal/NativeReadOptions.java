package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;

@JniClass(
   name = "leveldb::ReadOptions",
   flags = {ClassFlag.STRUCT, ClassFlag.CPP}
)
public class NativeReadOptions {
   @JniField
   private boolean verify_checksums = false;
   @JniField
   private boolean fill_cache = true;
   @JniField(
      cast = "const leveldb::Snapshot*"
   )
   private long snapshot = 0L;

   public boolean fillCache() {
      return this.fill_cache;
   }

   public NativeReadOptions fillCache(boolean fill_cache) {
      this.fill_cache = fill_cache;
      return this;
   }

   public NativeSnapshot snapshot() {
      return this.snapshot == 0L ? null : new NativeSnapshot(this.snapshot);
   }

   public NativeReadOptions snapshot(NativeSnapshot snapshot) {
      if (snapshot == null) {
         this.snapshot = 0L;
      } else {
         this.snapshot = snapshot.pointer();
      }

      return this;
   }

   public boolean verifyChecksums() {
      return this.verify_checksums;
   }

   public NativeReadOptions verifyChecksums(boolean verify_checksums) {
      this.verify_checksums = verify_checksums;
      return this;
   }
}
