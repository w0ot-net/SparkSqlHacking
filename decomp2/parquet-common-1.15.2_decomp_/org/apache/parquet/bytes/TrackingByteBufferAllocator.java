package org.apache.parquet.bytes;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class TrackingByteBufferAllocator implements ByteBufferAllocator, AutoCloseable {
   private static final boolean DEBUG = false;
   private final Map allocated = new HashMap();
   private final ByteBufferAllocator allocator;

   public static TrackingByteBufferAllocator wrap(ByteBufferAllocator allocator) {
      return new TrackingByteBufferAllocator(allocator);
   }

   private TrackingByteBufferAllocator(ByteBufferAllocator allocator) {
      this.allocator = allocator;
   }

   public ByteBuffer allocate(int size) {
      ByteBuffer buffer = this.allocator.allocate(size);
      this.allocated.put(new Key(buffer), TrackingByteBufferAllocator.ByteBufferAllocationStacktraceException.create());
      return buffer;
   }

   public void release(ByteBuffer b) throws ReleasingUnallocatedByteBufferException {
      Objects.requireNonNull(b);
      if (this.allocated.remove(new Key(b)) == null) {
         throw new ReleasingUnallocatedByteBufferException();
      } else {
         this.allocator.release(b);
         b.clear();
      }
   }

   public boolean isDirect() {
      return this.allocator.isDirect();
   }

   public void close() throws LeakedByteBufferException {
      if (!this.allocated.isEmpty()) {
         LeakedByteBufferException ex = new LeakedByteBufferException(this.allocated.size(), (ByteBufferAllocationStacktraceException)this.allocated.values().iterator().next());
         this.allocated.clear();
         throw ex;
      }
   }

   private static class Key {
      private final int hashCode;
      private final ByteBuffer buffer;

      Key(ByteBuffer buffer) {
         this.hashCode = System.identityHashCode(buffer);
         this.buffer = buffer;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            Key key = (Key)o;
            return this.buffer == key.buffer;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.hashCode;
      }
   }

   public static class LeakDetectorHeapByteBufferAllocatorException extends RuntimeException {
      private LeakDetectorHeapByteBufferAllocatorException(String msg) {
         super(msg);
      }

      private LeakDetectorHeapByteBufferAllocatorException(String msg, Throwable cause) {
         super(msg, cause);
      }

      private LeakDetectorHeapByteBufferAllocatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
         super(message, cause, enableSuppression, writableStackTrace);
      }
   }

   public static class ByteBufferAllocationStacktraceException extends LeakDetectorHeapByteBufferAllocatorException {
      private static final ByteBufferAllocationStacktraceException WITHOUT_STACKTRACE = new ByteBufferAllocationStacktraceException(false);

      private static ByteBufferAllocationStacktraceException create() {
         return WITHOUT_STACKTRACE;
      }

      private ByteBufferAllocationStacktraceException() {
         super("Allocation stacktrace of the first ByteBuffer:", (<undefinedtype>)null);
      }

      private ByteBufferAllocationStacktraceException(boolean unused) {
         super("Set org.apache.parquet.bytes.TrackingByteBufferAllocator.DEBUG = true for more info", (Throwable)null, false, false, null);
      }
   }

   public static class ReleasingUnallocatedByteBufferException extends LeakDetectorHeapByteBufferAllocatorException {
      private ReleasingUnallocatedByteBufferException() {
         super("Releasing a ByteBuffer instance that is not allocated by this allocator or already been released", (<undefinedtype>)null);
      }
   }

   public static class LeakedByteBufferException extends LeakDetectorHeapByteBufferAllocatorException {
      private LeakedByteBufferException(int count, ByteBufferAllocationStacktraceException e) {
         super(count + " ByteBuffer object(s) is/are remained unreleased after closing this allocator.", e, null);
      }
   }
}
