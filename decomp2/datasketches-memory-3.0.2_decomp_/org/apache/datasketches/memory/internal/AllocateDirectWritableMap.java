package org.apache.datasketches.memory.internal;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;
import sun.nio.ch.FileChannelImpl;

final class AllocateDirectWritableMap {
   static final Logger LOG = Logger.getLogger(AllocateDirectWritableMap.class.getCanonicalName());
   private static final int MAP_RO = 0;
   private static final int MAP_RW = 1;
   private static final Method FILE_CHANNEL_IMPL_MAP0_METHOD;
   static final Method FILE_CHANNEL_IMPL_UNMAP0_METHOD;
   private static final Method MAPPED_BYTE_BUFFER_LOAD0_METHOD;
   private static final Method MAPPED_BYTE_BUFFER_ISLOADED0_METHOD;
   static final Method MAPPED_BYTE_BUFFER_FORCE0_METHOD;
   private static int pageSize;
   private final Deallocator deallocator;
   private final MemoryCleaner cleaner;
   private final File file;
   final long capacityBytes;
   final RandomAccessFile raf;
   final long nativeBaseOffset;
   final boolean resourceReadOnly;

   AllocateDirectWritableMap(File file, long fileOffsetBytes, long capacityBytes, boolean localReadOnly) {
      this.file = file;
      this.capacityBytes = capacityBytes;
      this.resourceReadOnly = isFileReadOnly(file);
      long fileLength = file.length();
      if ((localReadOnly || this.resourceReadOnly) && fileOffsetBytes + capacityBytes > fileLength) {
         throw new IllegalArgumentException("Read-only mode and requested map length is greater than current file length: Requested Length = " + (fileOffsetBytes + capacityBytes) + ", Current File Length = " + fileLength);
      } else {
         this.raf = mapper(file, fileOffsetBytes, capacityBytes, this.resourceReadOnly);
         this.nativeBaseOffset = map(this.raf.getChannel(), this.resourceReadOnly, fileOffsetBytes, capacityBytes);
         this.deallocator = new Deallocator(this.nativeBaseOffset, capacityBytes, this.raf);
         this.cleaner = new MemoryCleaner(this, this.deallocator);
      }
   }

   public void close() {
      try {
         if (this.deallocator.deallocate(false)) {
            this.cleaner.clean();
         }
      } catch (Exception e) {
         throw new IllegalStateException("Attempted close of Memory-Mapped File: " + this.file.getName() + " " + e);
      } finally {
         ResourceImpl.reachabilityFence(this);
      }

   }

   public void force() {
      try {
         MAPPED_BYTE_BUFFER_FORCE0_METHOD.invoke(AccessByteBuffer.ZERO_READ_ONLY_DIRECT_BYTE_BUFFER, this.raf.getFD(), this.nativeBaseOffset, this.capacityBytes);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
         throw new RuntimeException(String.format("Encountered %s exception in force. " + ((Exception)e).toString()));
      }
   }

   public StepBoolean getValid() {
      return this.deallocator.getValid();
   }

   public static boolean isFileReadOnly(File file) {
      return !file.canWrite();
   }

   public boolean isLoaded() {
      try {
         return (Boolean)MAPPED_BYTE_BUFFER_ISLOADED0_METHOD.invoke(AccessByteBuffer.ZERO_READ_ONLY_DIRECT_BYTE_BUFFER, this.nativeBaseOffset, this.capacityBytes, pageCount(this.capacityBytes));
      } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
         throw new RuntimeException(String.format("Encountered %s exception while loading", e.getClass()));
      }
   }

   public void load() {
      this.madvise();
      int count = pageCount(this.capacityBytes);
      long offset = this.nativeBaseOffset;

      for(int i = 0; i < count; ++i) {
         UnsafeUtil.unsafe.getByte(offset);
         offset += (long)pageSize;
      }

   }

   private void madvise() {
      try {
         MAPPED_BYTE_BUFFER_LOAD0_METHOD.invoke(AccessByteBuffer.ZERO_READ_ONLY_DIRECT_BYTE_BUFFER, this.nativeBaseOffset, this.capacityBytes);
      } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
         throw new RuntimeException(String.format("Encountered %s exception while loading", e.getClass()));
      }
   }

   private static int pageCount(long bytes) {
      return (int)(bytes + (long)pageSize - 1L) / pageSize;
   }

   private static RandomAccessFile mapper(File file, long fileOffset, long capacityBytes, boolean resourceReadOnly) {
      String mode = resourceReadOnly ? "r" : "rw";

      try {
         RandomAccessFile raf = new RandomAccessFile(file, mode);
         if (fileOffset + capacityBytes > raf.length()) {
            raf.setLength(fileOffset + capacityBytes);
         }

         return raf;
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   private static long map(FileChannel fileChannel, boolean resourceReadOnly, long position, long lengthBytes) {
      int pagePosition = (int)(position % (long)UnsafeUtil.unsafe.pageSize());
      long mapPosition = position - (long)pagePosition;
      long mapSize = lengthBytes + (long)pagePosition;
      int mapMode = resourceReadOnly ? 0 : 1;

      try {
         long nativeBaseOffset = (Long)FILE_CHANNEL_IMPL_MAP0_METHOD.invoke(fileChannel, mapMode, mapPosition, mapSize);
         return nativeBaseOffset;
      } catch (InvocationTargetException e) {
         throw new RuntimeException("Exception while mapping", e.getTargetException());
      } catch (IllegalAccessException e) {
         throw new RuntimeException("Exception while mapping", e);
      }
   }

   static {
      pageSize = UnsafeUtil.unsafe.pageSize();

      try {
         FILE_CHANNEL_IMPL_MAP0_METHOD = FileChannelImpl.class.getDeclaredMethod("map0", Integer.TYPE, Long.TYPE, Long.TYPE);
         FILE_CHANNEL_IMPL_MAP0_METHOD.setAccessible(true);
         FILE_CHANNEL_IMPL_UNMAP0_METHOD = FileChannelImpl.class.getDeclaredMethod("unmap0", Long.TYPE, Long.TYPE);
         FILE_CHANNEL_IMPL_UNMAP0_METHOD.setAccessible(true);
         MAPPED_BYTE_BUFFER_LOAD0_METHOD = MappedByteBuffer.class.getDeclaredMethod("load0", Long.TYPE, Long.TYPE);
         MAPPED_BYTE_BUFFER_LOAD0_METHOD.setAccessible(true);
         MAPPED_BYTE_BUFFER_ISLOADED0_METHOD = MappedByteBuffer.class.getDeclaredMethod("isLoaded0", Long.TYPE, Long.TYPE, Integer.TYPE);
         MAPPED_BYTE_BUFFER_ISLOADED0_METHOD.setAccessible(true);
         MAPPED_BYTE_BUFFER_FORCE0_METHOD = MappedByteBuffer.class.getDeclaredMethod("force0", FileDescriptor.class, Long.TYPE, Long.TYPE);
         MAPPED_BYTE_BUFFER_FORCE0_METHOD.setAccessible(true);
      } catch (NoSuchMethodException | SecurityException e) {
         throw new RuntimeException("Could not reflect static methods: " + e);
      }
   }

   private static final class Deallocator implements Runnable {
      private final RandomAccessFile myRaf;
      private final FileChannel myFc;
      private final long actualNativeBaseOffset;
      private final long myCapacity;
      private final StepBoolean valid = new StepBoolean(true);

      Deallocator(long nativeBaseOffset, long capacityBytes, RandomAccessFile raf) {
         this.myRaf = raf;

         assert this.myRaf != null;

         this.myFc = this.myRaf.getChannel();
         this.actualNativeBaseOffset = nativeBaseOffset;

         assert this.actualNativeBaseOffset != 0L;

         this.myCapacity = capacityBytes;

         assert this.myCapacity != 0L;

      }

      StepBoolean getValid() {
         return this.valid;
      }

      public void run() throws IllegalStateException {
         this.deallocate(true);
      }

      boolean deallocate(boolean calledFromCleaner) throws IllegalStateException {
         if (this.valid.change()) {
            if (calledFromCleaner) {
               AllocateDirectWritableMap.LOG.warning("A direct mapped resource was not closed explicitly");
            }

            this.unmap();
            return true;
         } else {
            return false;
         }
      }

      private void unmap() throws IllegalStateException {
         try {
            AllocateDirectWritableMap.FILE_CHANNEL_IMPL_UNMAP0_METHOD.invoke(this.myFc, this.actualNativeBaseOffset, this.myCapacity);
            this.myRaf.close();
         } catch (IllegalArgumentException | InvocationTargetException | IOException | IllegalAccessException e) {
            throw new IllegalStateException(String.format("Encountered %s exception while freeing memory", e.getClass()));
         }
      }
   }
}
