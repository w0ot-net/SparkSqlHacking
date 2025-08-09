package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;

@JniClass(
   name = "leveldb::WriteOptions",
   flags = {ClassFlag.STRUCT, ClassFlag.CPP}
)
public class NativeWriteOptions {
   @JniField
   boolean sync;

   public boolean sync() {
      return this.sync;
   }

   public NativeWriteOptions sync(boolean sync) {
      this.sync = sync;
      return this;
   }
}
