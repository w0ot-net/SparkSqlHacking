package org.fusesource.leveldbjni.internal;

import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.ClassFlag;
import org.fusesource.hawtjni.runtime.FieldFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniField;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.MethodFlag;
import org.fusesource.hawtjni.runtime.PointerMath;

public class NativeRange {
   private final byte[] start;
   private final byte[] limit;

   public byte[] limit() {
      return this.limit;
   }

   public byte[] start() {
      return this.start;
   }

   public NativeRange(byte[] start, byte[] limit) {
      NativeDB.checkArgNotNull(start, "start");
      NativeDB.checkArgNotNull(limit, "limit");
      this.limit = limit;
      this.start = start;
   }

   @JniClass(
      name = "leveldb::Range",
      flags = {ClassFlag.STRUCT, ClassFlag.CPP}
   )
   public static class RangeJNI {
      @JniField(
         flags = {FieldFlag.CONSTANT},
         accessor = "sizeof(struct leveldb::Range)"
      )
      static int SIZEOF;
      @JniField
      NativeSlice start = new NativeSlice();
      @JniField(
         flags = {FieldFlag.FIELD_SKIP}
      )
      NativeBuffer start_buffer;
      @JniField
      NativeSlice limit = new NativeSlice();
      @JniField(
         flags = {FieldFlag.FIELD_SKIP}
      )
      NativeBuffer limit_buffer;

      public static final native void memmove(@JniArg(cast = "void *") long var0, @JniArg(cast = "const void *",flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) RangeJNI var2, @JniArg(cast = "size_t") long var3);

      public static final native void memmove(@JniArg(cast = "void *",flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) RangeJNI var0, @JniArg(cast = "const void *") long var1, @JniArg(cast = "size_t") long var3);

      @JniMethod(
         flags = {MethodFlag.CONSTANT_INITIALIZER}
      )
      private static final native void init();

      public RangeJNI(NativeRange range) {
         this.start_buffer = NativeBuffer.create(range.start());
         this.start.set(this.start_buffer);

         try {
            this.limit_buffer = NativeBuffer.create(range.limit());
         } catch (OutOfMemoryError e) {
            this.start_buffer.delete();
            throw e;
         }

         this.limit.set(this.limit_buffer);
      }

      public void delete() {
         this.start_buffer.delete();
         this.limit_buffer.delete();
      }

      static NativeBuffer arrayCreate(int dimension) {
         return NativeBuffer.create((long)(dimension * SIZEOF));
      }

      void arrayWrite(long buffer, int index) {
         memmove(PointerMath.add(buffer, (long)(SIZEOF * index)), this, (long)SIZEOF);
      }

      void arrayRead(long buffer, int index) {
         memmove(this, PointerMath.add(buffer, (long)(SIZEOF * index)), (long)SIZEOF);
      }

      static {
         NativeDB.LIBRARY.load();
         init();
      }
   }
}
