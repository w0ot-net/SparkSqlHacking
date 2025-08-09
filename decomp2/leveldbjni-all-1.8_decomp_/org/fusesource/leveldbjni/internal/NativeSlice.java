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

@JniClass(
   name = "leveldb::Slice",
   flags = {ClassFlag.STRUCT, ClassFlag.CPP}
)
class NativeSlice {
   @JniField(
      cast = "const char*"
   )
   private long data_;
   @JniField(
      cast = "size_t"
   )
   private long size_;

   public NativeSlice() {
   }

   public NativeSlice(long data, long length) {
      this.data_ = data;
      this.size_ = length;
   }

   public NativeSlice(NativeBuffer buffer) {
      this(buffer.pointer(), buffer.capacity());
   }

   public static NativeSlice create(NativeBuffer buffer) {
      return buffer == null ? null : new NativeSlice(buffer);
   }

   public long data() {
      return this.data_;
   }

   public NativeSlice data(long data) {
      this.data_ = data;
      return this;
   }

   public long size() {
      return this.size_;
   }

   public NativeSlice size(long size) {
      this.size_ = size;
      return this;
   }

   public NativeSlice set(NativeSlice buffer) {
      this.size_ = buffer.size_;
      this.data_ = buffer.data_;
      return this;
   }

   public NativeSlice set(NativeBuffer buffer) {
      this.size_ = buffer.capacity();
      this.data_ = buffer.pointer();
      return this;
   }

   public byte[] toByteArray() {
      if (this.size_ > 2147483647L) {
         throw new ArrayIndexOutOfBoundsException("Native slice is larger than the maximum Java array");
      } else {
         byte[] rc = new byte[(int)this.size_];
         NativeBuffer.NativeBufferJNI.buffer_copy(this.data_, 0L, rc, 0L, (long)rc.length);
         return rc;
      }
   }

   static NativeBuffer arrayCreate(int dimension) {
      return NativeBuffer.create((long)(dimension * NativeSlice.SliceJNI.SIZEOF));
   }

   void write(long buffer, int index) {
      NativeSlice.SliceJNI.memmove(PointerMath.add(buffer, (long)(NativeSlice.SliceJNI.SIZEOF * index)), this, (long)NativeSlice.SliceJNI.SIZEOF);
   }

   void read(long buffer, int index) {
      NativeSlice.SliceJNI.memmove(this, PointerMath.add(buffer, (long)(NativeSlice.SliceJNI.SIZEOF * index)), (long)NativeSlice.SliceJNI.SIZEOF);
   }

   @JniClass(
      name = "leveldb::Slice",
      flags = {ClassFlag.CPP}
   )
   static class SliceJNI {
      @JniField(
         flags = {FieldFlag.CONSTANT},
         accessor = "sizeof(struct leveldb::Slice)"
      )
      static int SIZEOF;

      @JniMethod(
         flags = {MethodFlag.CPP_DELETE}
      )
      public static final native void delete(long var0);

      public static final native void memmove(@JniArg(cast = "void *") long var0, @JniArg(cast = "const void *",flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) NativeSlice var2, @JniArg(cast = "size_t") long var3);

      public static final native void memmove(@JniArg(cast = "void *",flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) NativeSlice var0, @JniArg(cast = "const void *") long var1, @JniArg(cast = "size_t") long var3);

      @JniMethod(
         flags = {MethodFlag.CONSTANT_INITIALIZER}
      )
      private static final native void init();

      static {
         NativeDB.LIBRARY.load();
         init();
      }
   }
}
