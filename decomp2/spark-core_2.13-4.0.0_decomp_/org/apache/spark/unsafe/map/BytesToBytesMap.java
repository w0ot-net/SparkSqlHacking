package org.apache.spark.unsafe.map;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.Nullable;
import org.apache.spark.SparkEnv;
import org.apache.spark.executor.ShuffleWriteMetrics;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.SparkLogger;
import org.apache.spark.internal.SparkLoggerFactory;
import org.apache.spark.internal.LogKeys.PATH.;
import org.apache.spark.memory.MemoryConsumer;
import org.apache.spark.memory.SparkOutOfMemoryError;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.unsafe.Platform;
import org.apache.spark.unsafe.UnsafeAlignedOffset;
import org.apache.spark.unsafe.array.ByteArrayMethods;
import org.apache.spark.unsafe.array.LongArray;
import org.apache.spark.unsafe.hash.Murmur3_x86_32;
import org.apache.spark.unsafe.memory.MemoryBlock;
import org.apache.spark.util.collection.unsafe.sort.UnsafeSorterSpillReader;
import org.apache.spark.util.collection.unsafe.sort.UnsafeSorterSpillWriter;
import org.sparkproject.guava.annotations.VisibleForTesting;
import org.sparkproject.guava.io.Closeables;

public final class BytesToBytesMap extends MemoryConsumer {
   private static final SparkLogger logger = SparkLoggerFactory.getLogger(BytesToBytesMap.class);
   private static final HashMapGrowthStrategy growthStrategy;
   private final TaskMemoryManager taskMemoryManager;
   private final LinkedList dataPages;
   private MemoryBlock currentPage;
   private long pageCursor;
   public static final int MAX_CAPACITY = 536870912;
   @Nullable
   private LongArray longArray;
   private boolean canGrowArray;
   private final double loadFactor;
   private final long pageSizeBytes;
   private int numKeys;
   private int numValues;
   private int growthThreshold;
   private int mask;
   private final Location loc;
   private long numProbes;
   private long numKeyLookups;
   private long peakMemoryUsedBytes;
   private final int initialCapacity;
   private final BlockManager blockManager;
   private final SerializerManager serializerManager;
   private volatile MapIterator destructiveIterator;
   private LinkedList spillWriters;

   public BytesToBytesMap(TaskMemoryManager taskMemoryManager, BlockManager blockManager, SerializerManager serializerManager, int initialCapacity, double loadFactor, long pageSizeBytes) {
      super(taskMemoryManager, pageSizeBytes, taskMemoryManager.getTungstenMemoryMode());
      this.dataPages = new LinkedList();
      this.currentPage = null;
      this.pageCursor = 0L;
      this.canGrowArray = true;
      this.numProbes = 0L;
      this.numKeyLookups = 0L;
      this.peakMemoryUsedBytes = 0L;
      this.destructiveIterator = null;
      this.spillWriters = new LinkedList();
      this.taskMemoryManager = taskMemoryManager;
      this.blockManager = blockManager;
      this.serializerManager = serializerManager;
      this.loadFactor = loadFactor;
      this.loc = new Location();
      this.pageSizeBytes = pageSizeBytes;
      if (initialCapacity <= 0) {
         throw new IllegalArgumentException("Initial capacity must be greater than 0");
      } else if (initialCapacity > 536870912) {
         throw new IllegalArgumentException("Initial capacity " + initialCapacity + " exceeds maximum capacity of 536870912");
      } else if (pageSizeBytes > 17179869176L) {
         throw new IllegalArgumentException("Page size " + pageSizeBytes + " cannot exceed 17179869176");
      } else {
         this.initialCapacity = initialCapacity;
         this.allocate(initialCapacity);
      }
   }

   public BytesToBytesMap(TaskMemoryManager taskMemoryManager, int initialCapacity, long pageSizeBytes) {
      this(taskMemoryManager, SparkEnv.get() != null ? SparkEnv.get().blockManager() : null, SparkEnv.get() != null ? SparkEnv.get().serializerManager() : null, initialCapacity, (double)0.5F, pageSizeBytes);
   }

   public int numKeys() {
      return this.numKeys;
   }

   public int numValues() {
      return this.numValues;
   }

   public MapIterator iterator() {
      return new MapIterator(this.numValues, new Location(), false);
   }

   public MapIterator destructiveIterator() {
      this.updatePeakMemoryUsed();
      return new MapIterator(this.numValues, new Location(), true);
   }

   public MapIteratorWithKeyIndex iteratorWithKeyIndex() {
      return new MapIteratorWithKeyIndex();
   }

   public int maxNumKeysIndex() {
      return (int)(this.longArray.size() / 2L);
   }

   public Location lookup(Object keyBase, long keyOffset, int keyLength) {
      this.safeLookup(keyBase, keyOffset, keyLength, this.loc, Murmur3_x86_32.hashUnsafeWords(keyBase, keyOffset, keyLength, 42));
      return this.loc;
   }

   public Location lookup(Object keyBase, long keyOffset, int keyLength, int hash) {
      this.safeLookup(keyBase, keyOffset, keyLength, this.loc, hash);
      return this.loc;
   }

   public void safeLookup(Object keyBase, long keyOffset, int keyLength, Location loc, int hash) {
      assert this.longArray != null;

      ++this.numKeyLookups;
      int pos = hash & this.mask;
      int step = 1;

      while(true) {
         ++this.numProbes;
         if (this.longArray.get(pos * 2) == 0L) {
            loc.with(pos, hash, false);
            return;
         }

         long stored = this.longArray.get(pos * 2 + 1);
         if ((int)stored == hash) {
            loc.with(pos, hash, true);
            if (loc.getKeyLength() == keyLength) {
               boolean areEqual = ByteArrayMethods.arrayEquals(keyBase, keyOffset, loc.getKeyBase(), loc.getKeyOffset(), (long)keyLength);
               if (areEqual) {
                  return;
               }
            }
         }

         pos = pos + step & this.mask;
         ++step;
      }
   }

   private boolean acquireNewPage(long required) {
      try {
         this.currentPage = this.allocatePage(required);
      } catch (SparkOutOfMemoryError var4) {
         return false;
      }

      this.dataPages.add(this.currentPage);
      UnsafeAlignedOffset.putSize(this.currentPage.getBaseObject(), this.currentPage.getBaseOffset(), 0);
      this.pageCursor = (long)UnsafeAlignedOffset.getUaoSize();
      return true;
   }

   public long spill(long size, MemoryConsumer trigger) throws IOException {
      return trigger != this && this.destructiveIterator != null ? this.destructiveIterator.spill(size) : 0L;
   }

   private void allocate(int capacity) {
      assert capacity >= 0;

      capacity = Math.max((int)Math.min(536870912L, ByteArrayMethods.nextPowerOf2((long)capacity)), 64);

      assert capacity <= 536870912;

      this.longArray = this.allocateArray((long)capacity * 2L);
      this.longArray.zeroOut();
      this.growthThreshold = (int)((double)capacity * this.loadFactor);
      this.mask = capacity - 1;
   }

   public void free() {
      this.updatePeakMemoryUsed();
      if (this.longArray != null) {
         this.freeArray(this.longArray);
         this.longArray = null;
      }

      Iterator<MemoryBlock> dataPagesIterator = this.dataPages.iterator();

      while(dataPagesIterator.hasNext()) {
         MemoryBlock dataPage = (MemoryBlock)dataPagesIterator.next();
         dataPagesIterator.remove();
         this.freePage(dataPage);
      }

      assert this.dataPages.isEmpty();

      while(!this.spillWriters.isEmpty()) {
         File file = ((UnsafeSorterSpillWriter)this.spillWriters.removeFirst()).getFile();
         if (file != null && file.exists() && !file.delete()) {
            logger.error("Was unable to delete spill file {}", new MDC[]{MDC.of(.MODULE$, file.getAbsolutePath())});
         }
      }

   }

   public TaskMemoryManager getTaskMemoryManager() {
      return this.taskMemoryManager;
   }

   public long getPageSizeBytes() {
      return this.pageSizeBytes;
   }

   public long getTotalMemoryConsumption() {
      long totalDataPagesSize = 0L;

      for(MemoryBlock dataPage : this.dataPages) {
         totalDataPagesSize += dataPage.size();
      }

      return totalDataPagesSize + (this.longArray != null ? this.longArray.memoryBlock().size() : 0L);
   }

   private void updatePeakMemoryUsed() {
      long mem = this.getTotalMemoryConsumption();
      if (mem > this.peakMemoryUsedBytes) {
         this.peakMemoryUsedBytes = mem;
      }

   }

   public long getPeakMemoryUsedBytes() {
      this.updatePeakMemoryUsed();
      return this.peakMemoryUsedBytes;
   }

   public double getAvgHashProbesPerKey() {
      return (double)1.0F * (double)this.numProbes / (double)this.numKeyLookups;
   }

   @VisibleForTesting
   public int getNumDataPages() {
      return this.dataPages.size();
   }

   public LongArray getArray() {
      assert this.longArray != null;

      return this.longArray;
   }

   public void reset() {
      this.updatePeakMemoryUsed();
      this.numKeys = 0;
      this.numValues = 0;
      this.freeArray(this.longArray);
      this.longArray = null;

      while(this.dataPages.size() > 0) {
         MemoryBlock dataPage = (MemoryBlock)this.dataPages.removeLast();
         this.freePage(dataPage);
      }

      this.allocate(this.initialCapacity);
      this.canGrowArray = true;
      this.currentPage = null;
      this.pageCursor = 0L;
   }

   @VisibleForTesting
   void growAndRehash() {
      assert this.longArray != null;

      LongArray oldLongArray = this.longArray;
      int oldCapacity = (int)oldLongArray.size() / 2;
      this.allocate(Math.min(growthStrategy.nextCapacity(oldCapacity), 536870912));

      for(int i = 0; (long)i < oldLongArray.size(); i += 2) {
         long keyPointer = oldLongArray.get(i);
         if (keyPointer != 0L) {
            int hashcode = (int)oldLongArray.get(i + 1);
            int newPos = hashcode & this.mask;

            for(int step = 1; this.longArray.get(newPos * 2) != 0L; ++step) {
               newPos = newPos + step & this.mask;
            }

            this.longArray.set(newPos * 2, keyPointer);
            this.longArray.set(newPos * 2 + 1, (long)hashcode);
         }
      }

      this.freeArray(oldLongArray);
   }

   static {
      growthStrategy = HashMapGrowthStrategy.DOUBLING;
   }

   public final class MapIterator implements Iterator {
      private int numRecords;
      private final Location loc;
      private MemoryBlock currentPage = null;
      private int recordsInPage = 0;
      private Object pageBaseObject;
      private long offsetInPage;
      private boolean destructive = false;
      private UnsafeSorterSpillReader reader = null;

      private MapIterator(int numRecords, Location loc, boolean destructive) {
         this.numRecords = numRecords;
         this.loc = loc;
         this.destructive = destructive;
         if (destructive) {
            BytesToBytesMap.this.destructiveIterator = this;
            if (BytesToBytesMap.this.longArray != null) {
               BytesToBytesMap.this.freeArray(BytesToBytesMap.this.longArray);
               BytesToBytesMap.this.longArray = null;
            }
         }

      }

      private void advanceToNextPage() {
         MemoryBlock pageToFree = null;

         try {
            synchronized(this) {
               int nextIdx = BytesToBytesMap.this.dataPages.indexOf(this.currentPage) + 1;
               if (this.destructive && this.currentPage != null) {
                  BytesToBytesMap.this.dataPages.remove(this.currentPage);
                  pageToFree = this.currentPage;
                  --nextIdx;
               }

               if (BytesToBytesMap.this.dataPages.size() > nextIdx) {
                  this.currentPage = (MemoryBlock)BytesToBytesMap.this.dataPages.get(nextIdx);
                  this.pageBaseObject = this.currentPage.getBaseObject();
                  this.offsetInPage = this.currentPage.getBaseOffset();
                  this.recordsInPage = UnsafeAlignedOffset.getSize(this.pageBaseObject, this.offsetInPage);
                  this.offsetInPage += (long)UnsafeAlignedOffset.getUaoSize();
               } else {
                  this.currentPage = null;
                  if (this.reader != null) {
                     this.handleFailedDelete();
                  }

                  try {
                     Closeables.close(this.reader, false);
                     this.reader = ((UnsafeSorterSpillWriter)BytesToBytesMap.this.spillWriters.getFirst()).getReader(BytesToBytesMap.this.serializerManager);
                     this.recordsInPage = -1;
                  } catch (IOException e) {
                     Platform.throwException(e);
                  }
               }
            }
         } finally {
            if (pageToFree != null) {
               BytesToBytesMap.this.freePage(pageToFree);
            }

         }

      }

      public boolean hasNext() {
         if (this.numRecords == 0 && this.reader != null) {
            this.handleFailedDelete();
         }

         return this.numRecords > 0;
      }

      public Location next() {
         if (this.recordsInPage == 0) {
            this.advanceToNextPage();
         }

         --this.numRecords;
         if (this.currentPage != null) {
            int totalLength = UnsafeAlignedOffset.getSize(this.pageBaseObject, this.offsetInPage);
            this.loc.with(this.currentPage, this.offsetInPage);
            this.offsetInPage += (long)(UnsafeAlignedOffset.getUaoSize() + totalLength + 8);
            --this.recordsInPage;
            return this.loc;
         } else {
            assert this.reader != null;

            if (!this.reader.hasNext()) {
               this.advanceToNextPage();
            }

            try {
               this.reader.loadNext();
            } catch (IOException e) {
               try {
                  this.reader.close();
               } catch (IOException e2) {
                  BytesToBytesMap.logger.error("Error while closing spill reader", e2);
               }

               Platform.throwException(e);
            }

            this.loc.with(this.reader.getBaseObject(), this.reader.getBaseOffset(), this.reader.getRecordLength());
            return this.loc;
         }
      }

      public synchronized long spill(long numBytes) throws IOException {
         if (this.destructive && BytesToBytesMap.this.dataPages.size() != 1) {
            BytesToBytesMap.this.updatePeakMemoryUsed();
            ShuffleWriteMetrics writeMetrics = new ShuffleWriteMetrics();
            long released = 0L;

            while(BytesToBytesMap.this.dataPages.size() > 0) {
               MemoryBlock block = (MemoryBlock)BytesToBytesMap.this.dataPages.getLast();
               if (block == this.currentPage) {
                  break;
               }

               Object base = block.getBaseObject();
               long offset = block.getBaseOffset();
               int numRecords = UnsafeAlignedOffset.getSize(base, offset);
               int uaoSize = UnsafeAlignedOffset.getUaoSize();
               offset += (long)uaoSize;

               UnsafeSorterSpillWriter writer;
               for(writer = new UnsafeSorterSpillWriter(BytesToBytesMap.this.blockManager, 32768, writeMetrics, numRecords); numRecords > 0; --numRecords) {
                  int length = UnsafeAlignedOffset.getSize(base, offset);
                  writer.write(base, offset + (long)uaoSize, length, 0L);
                  offset += (long)(uaoSize + length + 8);
               }

               writer.close();
               BytesToBytesMap.this.spillWriters.add(writer);
               BytesToBytesMap.this.dataPages.removeLast();
               released += block.size();
               BytesToBytesMap.this.freePage(block);
               if (released >= numBytes) {
                  break;
               }
            }

            return released;
         } else {
            return 0L;
         }
      }

      private void handleFailedDelete() {
         if (BytesToBytesMap.this.spillWriters.size() > 0) {
            File file = ((UnsafeSorterSpillWriter)BytesToBytesMap.this.spillWriters.removeFirst()).getFile();
            if (file != null && file.exists() && !file.delete()) {
               BytesToBytesMap.logger.error("Was unable to delete spill file {}", new MDC[]{MDC.of(.MODULE$, file.getAbsolutePath())});
            }
         }

      }
   }

   public final class MapIteratorWithKeyIndex implements Iterator {
      private int keyIndex = 0;
      private int numRecords;
      private final Location loc;

      private MapIteratorWithKeyIndex() {
         this.numRecords = BytesToBytesMap.this.numValues;
         this.loc = BytesToBytesMap.this.new Location();
      }

      public boolean hasNext() {
         return this.numRecords > 0;
      }

      public Location next() {
         if (!this.loc.isDefined() || !this.loc.nextValue()) {
            while(BytesToBytesMap.this.longArray.get(this.keyIndex * 2) == 0L) {
               ++this.keyIndex;
            }

            this.loc.with(this.keyIndex, 0, true);
            ++this.keyIndex;
         }

         --this.numRecords;
         return this.loc;
      }
   }

   public final class Location {
      private int pos;
      private boolean isDefined;
      private int keyHashcode;
      private Object baseObject;
      private long keyOffset;
      private int keyLength;
      private long valueOffset;
      private int valueLength;
      @Nullable
      private MemoryBlock memoryPage;

      private void updateAddressesAndSizes(long fullKeyAddress) {
         this.updateAddressesAndSizes(BytesToBytesMap.this.taskMemoryManager.getPage(fullKeyAddress), BytesToBytesMap.this.taskMemoryManager.getOffsetInPage(fullKeyAddress));
      }

      private void updateAddressesAndSizes(Object base, long offset) {
         this.baseObject = base;
         int totalLength = UnsafeAlignedOffset.getSize(base, offset);
         int uaoSize = UnsafeAlignedOffset.getUaoSize();
         offset += (long)uaoSize;
         this.keyLength = UnsafeAlignedOffset.getSize(base, offset);
         offset += (long)uaoSize;
         this.keyOffset = offset;
         this.valueOffset = offset + (long)this.keyLength;
         this.valueLength = totalLength - this.keyLength - uaoSize;
      }

      private Location with(int pos, int keyHashcode, boolean isDefined) {
         assert BytesToBytesMap.this.longArray != null;

         this.pos = pos;
         this.isDefined = isDefined;
         this.keyHashcode = keyHashcode;
         if (isDefined) {
            long fullKeyAddress = BytesToBytesMap.this.longArray.get(pos * 2);
            this.updateAddressesAndSizes(fullKeyAddress);
         }

         return this;
      }

      private Location with(MemoryBlock page, long offsetInPage) {
         this.isDefined = true;
         this.memoryPage = page;
         this.updateAddressesAndSizes(page.getBaseObject(), offsetInPage);
         return this;
      }

      private Location with(Object base, long offset, int length) {
         this.isDefined = true;
         this.memoryPage = null;
         this.baseObject = base;
         int uaoSize = UnsafeAlignedOffset.getUaoSize();
         this.keyOffset = offset + (long)uaoSize;
         this.keyLength = UnsafeAlignedOffset.getSize(base, offset);
         this.valueOffset = offset + (long)uaoSize + (long)this.keyLength;
         this.valueLength = length - uaoSize - this.keyLength;
         return this;
      }

      public boolean nextValue() {
         assert this.isDefined;

         long nextAddr = Platform.getLong(this.baseObject, this.valueOffset + (long)this.valueLength);
         if (nextAddr == 0L) {
            return false;
         } else {
            this.updateAddressesAndSizes(nextAddr);
            return true;
         }
      }

      public MemoryBlock getMemoryPage() {
         return this.memoryPage;
      }

      public boolean isDefined() {
         return this.isDefined;
      }

      public int getKeyIndex() {
         assert this.isDefined;

         return this.pos;
      }

      public Object getKeyBase() {
         assert this.isDefined;

         return this.baseObject;
      }

      public long getKeyOffset() {
         assert this.isDefined;

         return this.keyOffset;
      }

      public Object getValueBase() {
         assert this.isDefined;

         return this.baseObject;
      }

      public long getValueOffset() {
         assert this.isDefined;

         return this.valueOffset;
      }

      public int getKeyLength() {
         assert this.isDefined;

         return this.keyLength;
      }

      public int getValueLength() {
         assert this.isDefined;

         return this.valueLength;
      }

      public boolean append(Object kbase, long koff, int klen, Object vbase, long voff, int vlen) {
         assert klen % 8 == 0;

         assert vlen % 8 == 0;

         assert BytesToBytesMap.this.longArray != null;

         if (BytesToBytesMap.this.numKeys != 536870911 && (BytesToBytesMap.this.canGrowArray || BytesToBytesMap.this.numKeys < BytesToBytesMap.this.growthThreshold)) {
            int uaoSize = UnsafeAlignedOffset.getUaoSize();
            long recordLength = 2L * (long)uaoSize + (long)klen + (long)vlen + 8L;
            if ((BytesToBytesMap.this.currentPage == null || BytesToBytesMap.this.currentPage.size() - BytesToBytesMap.this.pageCursor < recordLength) && !BytesToBytesMap.this.acquireNewPage(recordLength + (long)uaoSize)) {
               return false;
            } else {
               Object base = BytesToBytesMap.this.currentPage.getBaseObject();
               long offset = BytesToBytesMap.this.currentPage.getBaseOffset() + BytesToBytesMap.this.pageCursor;
               UnsafeAlignedOffset.putSize(base, offset, klen + vlen + uaoSize);
               UnsafeAlignedOffset.putSize(base, offset + (long)uaoSize, klen);
               long var21 = offset + 2L * (long)uaoSize;
               Platform.copyMemory(kbase, koff, base, var21, (long)klen);
               long var22 = var21 + (long)klen;
               Platform.copyMemory(vbase, voff, base, var22, (long)vlen);
               long var23 = var22 + (long)vlen;
               Platform.putLong(base, var23, this.isDefined ? BytesToBytesMap.this.longArray.get(this.pos * 2) : 0L);
               long var24 = BytesToBytesMap.this.currentPage.getBaseOffset();
               UnsafeAlignedOffset.putSize(base, var24, UnsafeAlignedOffset.getSize(base, var24) + 1);
               BytesToBytesMap var10000 = BytesToBytesMap.this;
               var10000.pageCursor += recordLength;
               long storedKeyAddress = BytesToBytesMap.this.taskMemoryManager.encodePageNumberAndOffset(BytesToBytesMap.this.currentPage, offset);
               BytesToBytesMap.this.longArray.set(this.pos * 2, storedKeyAddress);
               this.updateAddressesAndSizes(storedKeyAddress);
               ++BytesToBytesMap.this.numValues;
               if (!this.isDefined) {
                  ++BytesToBytesMap.this.numKeys;
                  BytesToBytesMap.this.longArray.set(this.pos * 2 + 1, (long)this.keyHashcode);
                  this.isDefined = true;
                  if (BytesToBytesMap.this.numKeys >= BytesToBytesMap.this.growthThreshold) {
                     if (BytesToBytesMap.this.longArray.size() / 2L < 536870912L) {
                        try {
                           BytesToBytesMap.this.growAndRehash();
                        } catch (SparkOutOfMemoryError var20) {
                           BytesToBytesMap.this.canGrowArray = false;
                        }
                     } else {
                        BytesToBytesMap.this.canGrowArray = false;
                     }
                  }
               }

               return true;
            }
         } else {
            return false;
         }
      }
   }
}
