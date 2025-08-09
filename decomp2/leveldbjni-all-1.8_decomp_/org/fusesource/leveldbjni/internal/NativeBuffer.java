package org.fusesource.leveldbjni.internal;

import java.util.concurrent.atomic.AtomicInteger;
import org.fusesource.hawtjni.runtime.ArgFlag;
import org.fusesource.hawtjni.runtime.JniArg;
import org.fusesource.hawtjni.runtime.JniClass;
import org.fusesource.hawtjni.runtime.JniMethod;
import org.fusesource.hawtjni.runtime.PointerMath;

public class NativeBuffer extends NativeObject {
   private final Allocation allocation;
   private final long capacity;
   private static final ThreadLocal CURRENT_POOL = new ThreadLocal();

   public static NativeBuffer create(long capacity) {
      Pool pool = (Pool)CURRENT_POOL.get();
      if (pool == null) {
         Allocation allocation = new Allocation(capacity);
         return new NativeBuffer(allocation, allocation.self, capacity);
      } else {
         return pool.create(capacity);
      }
   }

   public static void pushMemoryPool(int size) {
      Pool original = (Pool)CURRENT_POOL.get();
      Pool next = new Pool(size, original);
      CURRENT_POOL.set(next);
   }

   public static void popMemoryPool() {
      Pool next = (Pool)CURRENT_POOL.get();
      next.delete();
      if (next.prev == null) {
         CURRENT_POOL.remove();
      } else {
         CURRENT_POOL.set(next.prev);
      }

   }

   public static NativeBuffer create(byte[] data) {
      return data == null ? null : create(data, 0, data.length);
   }

   public static NativeBuffer create(String data) {
      return create(cbytes(data));
   }

   public static NativeBuffer create(byte[] data, int offset, int length) {
      NativeBuffer rc = create((long)length);
      rc.write(0L, data, offset, length);
      return rc;
   }

   private NativeBuffer(Allocation allocation, long self, long capacity) {
      super(self);
      this.capacity = capacity;
      this.allocation = allocation;
      this.allocation.retain();
   }

   public NativeBuffer slice(long offset, long length) {
      this.assertAllocated();
      if (length < 0L) {
         throw new IllegalArgumentException("length cannot be negative");
      } else if (offset < 0L) {
         throw new IllegalArgumentException("offset cannot be negative");
      } else if (offset + length >= this.capacity) {
         throw new ArrayIndexOutOfBoundsException("offset + length exceed the length of this buffer");
      } else {
         return new NativeBuffer(this.allocation, PointerMath.add(this.self, offset), length);
      }
   }

   static byte[] cbytes(String strvalue) {
      byte[] value = strvalue.getBytes();
      byte[] rc = new byte[value.length + 1];
      System.arraycopy(value, 0, rc, 0, value.length);
      return rc;
   }

   public NativeBuffer head(long length) {
      return this.slice(0L, length);
   }

   public NativeBuffer tail(long length) {
      if (this.capacity - length < 0L) {
         throw new ArrayIndexOutOfBoundsException("capacity-length cannot be less than zero");
      } else {
         return this.slice(this.capacity - length, length);
      }
   }

   public void delete() {
      this.allocation.release();
   }

   public long capacity() {
      return this.capacity;
   }

   public void write(long at, byte[] source, int offset, int length) {
      this.assertAllocated();
      if (length < 0) {
         throw new IllegalArgumentException("length cannot be negative");
      } else if (offset < 0) {
         throw new IllegalArgumentException("offset cannot be negative");
      } else if (at < 0L) {
         throw new IllegalArgumentException("at cannot be negative");
      } else if (at + (long)length > this.capacity) {
         throw new ArrayIndexOutOfBoundsException("at + length exceeds the capacity of this object");
      } else if (offset + length > source.length) {
         throw new ArrayIndexOutOfBoundsException("offset + length exceed the length of the source buffer");
      } else {
         NativeBuffer.NativeBufferJNI.buffer_copy(source, (long)offset, this.self, at, (long)length);
      }
   }

   public void read(long at, byte[] target, int offset, int length) {
      this.assertAllocated();
      if (length < 0) {
         throw new IllegalArgumentException("length cannot be negative");
      } else if (offset < 0) {
         throw new IllegalArgumentException("offset cannot be negative");
      } else if (at < 0L) {
         throw new IllegalArgumentException("at cannot be negative");
      } else if (at + (long)length > this.capacity) {
         throw new ArrayIndexOutOfBoundsException("at + length exceeds the capacity of this object");
      } else if (offset + length > target.length) {
         throw new ArrayIndexOutOfBoundsException("offset + length exceed the length of the target buffer");
      } else {
         NativeBuffer.NativeBufferJNI.buffer_copy(this.self, at, target, (long)offset, (long)length);
      }
   }

   public byte[] toByteArray() {
      if (this.capacity > 2147483647L) {
         throw new OutOfMemoryError("Native buffer larger than the largest allowed Java byte[]");
      } else {
         byte[] rc = new byte[(int)this.capacity];
         this.read(0L, rc, 0, rc.length);
         return rc;
      }
   }

   @JniClass
   static class NativeBufferJNI {
      @JniMethod(
         cast = "void *"
      )
      public static final native long malloc(@JniArg(cast = "size_t") long var0);

      public static final native void free(@JniArg(cast = "void *") long var0);

      public static final native void buffer_copy(@JniArg(cast = "const void *",flags = {ArgFlag.NO_OUT, ArgFlag.CRITICAL}) byte[] var0, @JniArg(cast = "size_t") long var1, @JniArg(cast = "void *") long var3, @JniArg(cast = "size_t") long var5, @JniArg(cast = "size_t") long var7);

      public static final native void buffer_copy(@JniArg(cast = "const void *") long var0, @JniArg(cast = "size_t") long var2, @JniArg(cast = "void *",flags = {ArgFlag.NO_IN, ArgFlag.CRITICAL}) byte[] var4, @JniArg(cast = "size_t") long var5, @JniArg(cast = "size_t") long var7);

      static {
         NativeDB.LIBRARY.load();
      }
   }

   private static class Allocation extends NativeObject {
      private final AtomicInteger retained;

      private Allocation(long size) {
         super(NativeBuffer.NativeBufferJNI.malloc(size));
         this.retained = new AtomicInteger(0);
      }

      void retain() {
         this.assertAllocated();
         this.retained.incrementAndGet();
      }

      void release() {
         this.assertAllocated();
         int r = this.retained.decrementAndGet();
         if (r < 0) {
            throw new Error("The object has already been deleted.");
         } else {
            if (r == 0) {
               NativeBuffer.NativeBufferJNI.free(this.self);
               this.self = 0L;
            }

         }
      }
   }

   private static class Pool {
      private final Pool prev;
      Allocation allocation;
      long pos;
      long remaining;
      int chunk;

      public Pool(int chunk, Pool prev) {
         this.chunk = chunk;
         this.prev = prev;
      }

      NativeBuffer create(long size) {
         if (size >= (long)this.chunk) {
            Allocation allocation = new Allocation(size);
            return new NativeBuffer(allocation, allocation.self, size);
         } else {
            if (this.remaining < size) {
               this.delete();
            }

            if (this.allocation == null) {
               this.allocate();
            }

            NativeBuffer rc = new NativeBuffer(this.allocation, this.pos, size);
            this.pos = PointerMath.add(this.pos, size);
            this.remaining -= size;
            return rc;
         }
      }

      private void allocate() {
         this.allocation = new Allocation((long)this.chunk);
         this.allocation.retain();
         this.remaining = (long)this.chunk;
         this.pos = this.allocation.self;
      }

      public void delete() {
         if (this.allocation != null) {
            this.allocation.release();
            this.allocation = null;
         }

      }
   }
}
