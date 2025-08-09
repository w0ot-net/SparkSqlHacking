package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;

public class NativeWriteBatch extends NativeObject {
   public NativeWriteBatch() {
      super(NativeWriteBatch.WriteBatchJNI.create());
   }

   public void delete() {
      this.assertAllocated();
      NativeWriteBatch.WriteBatchJNI.delete(this.self);
      this.self = 0L;
   }

   public void put(byte[] key, byte[] value) {
      NativeDB.checkArgNotNull(key, "key");
      NativeDB.checkArgNotNull(value, "value");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      try {
         NativeBuffer valueBuffer = NativeBuffer.create(value);

         try {
            this.put(keyBuffer, valueBuffer);
         } finally {
            valueBuffer.delete();
         }
      } finally {
         keyBuffer.delete();
      }

   }

   private void put(NativeBuffer keyBuffer, NativeBuffer valueBuffer) {
      this.put(new NativeSlice(keyBuffer), new NativeSlice(valueBuffer));
   }

   private void put(NativeSlice keySlice, NativeSlice valueSlice) {
      this.assertAllocated();
      NativeWriteBatch.WriteBatchJNI.Put(this.self, keySlice, valueSlice);
   }

   public void delete(byte[] key) {
      NativeDB.checkArgNotNull(key, "key");
      NativeBuffer keyBuffer = NativeBuffer.create(key);

      try {
         this.delete(keyBuffer);
      } finally {
         keyBuffer.delete();
      }

   }

   private void delete(NativeBuffer keyBuffer) {
      this.delete(new NativeSlice(keyBuffer));
   }

   private void delete(NativeSlice keySlice) {
      this.assertAllocated();
      NativeWriteBatch.WriteBatchJNI.Delete(this.self, keySlice);
   }

   public void clear() {
      this.assertAllocated();
      NativeWriteBatch.WriteBatchJNI.Clear(this.self);
   }

   @JniClass(
      name = "leveldb::WriteBatch",
      flags = {ClassFlag.CPP}
   )
   private static class WriteBatchJNI {
      @JniMethod(
         flags = {MethodFlag.CPP_NEW}
      )
      public static final native long create();

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Put(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var2, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var3);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Delete(long var0, @JniArg(flags = {ArgFlag.BY_VALUE, ArgFlag.NO_OUT}) NativeSlice var2);

      @JniMethod(
         flags = {MethodFlag.CPP_METHOD}
      )
      static final native void Clear(long var0);

      static {
         NativeDB.LIBRARY.load();
      }
   }
}
