package org.fusesource.leveldbjni.internal;

import org.iq80.leveldb.WriteBatch;

public class JniWriteBatch implements WriteBatch {
   private final NativeWriteBatch writeBatch;

   JniWriteBatch(NativeWriteBatch writeBatch) {
      this.writeBatch = writeBatch;
   }

   public void close() {
      this.writeBatch.delete();
   }

   public WriteBatch put(byte[] key, byte[] value) {
      this.writeBatch.put(key, value);
      return this;
   }

   public WriteBatch delete(byte[] key) {
      this.writeBatch.delete(key);
      return this;
   }

   public NativeWriteBatch writeBatch() {
      return this.writeBatch;
   }
}
