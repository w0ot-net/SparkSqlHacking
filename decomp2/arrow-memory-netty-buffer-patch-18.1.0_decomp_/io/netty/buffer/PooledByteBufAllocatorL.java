package io.netty.buffer;

import io.netty.util.internal.OutOfDirectMemoryError;
import io.netty.util.internal.StringUtil;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.util.AssertionUtil;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PooledByteBufAllocatorL {
   private static final Logger memoryLogger = LoggerFactory.getLogger("arrow.allocator");
   private static final int MEMORY_LOGGER_FREQUENCY_SECONDS = 60;
   public final UnsafeDirectLittleEndian empty;
   private final AtomicLong hugeBufferSize = new AtomicLong(0L);
   private final AtomicLong hugeBufferCount = new AtomicLong(0L);
   private final AtomicLong normalBufferSize = new AtomicLong(0L);
   private final AtomicLong normalBufferCount = new AtomicLong(0L);
   private final InnerAllocator allocator = new InnerAllocator();

   public PooledByteBufAllocatorL() {
      this.empty = new UnsafeDirectLittleEndian(new DuplicatedByteBuf(Unpooled.EMPTY_BUFFER));
   }

   public UnsafeDirectLittleEndian allocate(long size) {
      try {
         return this.allocator.directBuffer(LargeMemoryUtil.checkedCastToInt(size), Integer.MAX_VALUE);
      } catch (OutOfMemoryError var4) {
         if (!(var4 instanceof OutOfDirectMemoryError) && !"Direct buffer memory".equals(var4.getMessage())) {
            throw var4;
         } else {
            throw new OutOfMemoryException("Failure allocating buffer.", var4);
         }
      }
   }

   public int getChunkSize() {
      return this.allocator.chunkSize();
   }

   public long getHugeBufferSize() {
      return this.hugeBufferSize.get();
   }

   public long getHugeBufferCount() {
      return this.hugeBufferCount.get();
   }

   public long getNormalBufferSize() {
      return this.normalBufferSize.get();
   }

   public long getNormalBufferCount() {
      return this.normalBufferSize.get();
   }

   private static class AccountedUnsafeDirectLittleEndian extends UnsafeDirectLittleEndian {
      private final long initialCapacity;
      private final AtomicLong count;
      private final AtomicLong size;

      private AccountedUnsafeDirectLittleEndian(LargeBuffer buf, AtomicLong count, AtomicLong size) {
         super(buf);
         this.initialCapacity = (long)buf.capacity();
         this.count = count;
         this.size = size;
      }

      private AccountedUnsafeDirectLittleEndian(PooledUnsafeDirectByteBuf buf, AtomicLong count, AtomicLong size) {
         super(buf);
         this.initialCapacity = (long)buf.capacity();
         this.count = count;
         this.size = size;
      }

      public ByteBuf copy() {
         throw new UnsupportedOperationException("copy method is not supported");
      }

      public ByteBuf copy(int index, int length) {
         throw new UnsupportedOperationException("copy method is not supported");
      }

      public boolean release(int decrement) {
         boolean released = super.release(decrement);
         if (released) {
            this.count.decrementAndGet();
            this.size.addAndGet(-this.initialCapacity);
         }

         return released;
      }
   }

   private class InnerAllocator extends PooledByteBufAllocator {
      private final PoolArena[] directArenas;
      private final MemoryStatusThread statusThread;

      public InnerAllocator() {
         super(true);

         try {
            Field f = PooledByteBufAllocator.class.getDeclaredField("directArenas");
            f.setAccessible(true);
            this.directArenas = (PoolArena[])f.get(this);
         } catch (Exception e) {
            throw new RuntimeException("Failure while initializing allocator.  Unable to retrieve direct arenas field.", e);
         }

         if (PooledByteBufAllocatorL.memoryLogger.isTraceEnabled()) {
            this.statusThread = new MemoryStatusThread(this);
            this.statusThread.start();
         } else {
            this.statusThread = null;
         }

      }

      private UnsafeDirectLittleEndian newDirectBufferL(int initialCapacity, int maxCapacity) {
         PoolThreadCache cache = this.threadCache();
         PoolArena<ByteBuffer> directArena = cache.directArena;
         if (directArena != null) {
            if (initialCapacity > this.chunkSize()) {
               ByteBuf buf = UnpooledByteBufAllocator.DEFAULT.directBuffer(initialCapacity, maxCapacity);
               PooledByteBufAllocatorL.this.hugeBufferSize.addAndGet((long)buf.capacity());
               PooledByteBufAllocatorL.this.hugeBufferCount.incrementAndGet();
               return new AccountedUnsafeDirectLittleEndian(new LargeBuffer(buf), PooledByteBufAllocatorL.this.hugeBufferCount, PooledByteBufAllocatorL.this.hugeBufferSize);
            } else {
               ByteBuf buf = directArena.allocate(cache, initialCapacity, maxCapacity);
               if (!(buf instanceof PooledUnsafeDirectByteBuf)) {
                  this.fail();
               }

               if (!AssertionUtil.ASSERT_ENABLED) {
                  return new UnsafeDirectLittleEndian((PooledUnsafeDirectByteBuf)buf);
               } else {
                  PooledByteBufAllocatorL.this.normalBufferSize.addAndGet((long)buf.capacity());
                  PooledByteBufAllocatorL.this.normalBufferCount.incrementAndGet();
                  return new AccountedUnsafeDirectLittleEndian((PooledUnsafeDirectByteBuf)buf, PooledByteBufAllocatorL.this.normalBufferCount, PooledByteBufAllocatorL.this.normalBufferSize);
               }
            }
         } else {
            throw this.fail();
         }
      }

      private UnsupportedOperationException fail() {
         return new UnsupportedOperationException("Arrow requires that the JVM used supports access sun.misc.Unsafe.  This platform didn't provide that functionality.");
      }

      public UnsafeDirectLittleEndian directBuffer(int initialCapacity, int maxCapacity) {
         if (initialCapacity == 0 && maxCapacity == 0) {
            this.newDirectBuffer(initialCapacity, maxCapacity);
         }

         this.validate(initialCapacity, maxCapacity);
         return this.newDirectBufferL(initialCapacity, maxCapacity);
      }

      public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
         throw new UnsupportedOperationException("Arrow doesn't support using heap buffers.");
      }

      private void validate(int initialCapacity, int maxCapacity) {
         if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity: " + initialCapacity + " (expected: 0+)");
         } else if (initialCapacity > maxCapacity) {
            throw new IllegalArgumentException(String.format("initialCapacity: %d (expected: not greater than maxCapacity(%d)", initialCapacity, maxCapacity));
         }
      }

      public String toString() {
         StringBuilder buf = new StringBuilder();
         buf.append(this.directArenas.length);
         buf.append(" direct arena(s):");
         buf.append(StringUtil.NEWLINE);

         for(PoolArena a : this.directArenas) {
            buf.append(a);
         }

         buf.append("Large buffers outstanding: ");
         buf.append(PooledByteBufAllocatorL.this.hugeBufferCount.get());
         buf.append(" totaling ");
         buf.append(PooledByteBufAllocatorL.this.hugeBufferSize.get());
         buf.append(" bytes.");
         buf.append('\n');
         buf.append("Normal buffers outstanding: ");
         buf.append(PooledByteBufAllocatorL.this.normalBufferCount.get());
         buf.append(" totaling ");
         buf.append(PooledByteBufAllocatorL.this.normalBufferSize.get());
         buf.append(" bytes.");
         return buf.toString();
      }

      private class MemoryStatusThread extends Thread {
         private final InnerAllocator allocator;

         public MemoryStatusThread(InnerAllocator allocator) {
            super("allocation.logger");
            this.setDaemon(true);
            this.allocator = allocator;
         }

         public void run() {
            while(true) {
               PooledByteBufAllocatorL.memoryLogger.trace("Memory Usage: \n{}", this.allocator);

               try {
                  Thread.sleep(60000L);
               } catch (InterruptedException var2) {
                  return;
               }
            }
         }
      }
   }
}
