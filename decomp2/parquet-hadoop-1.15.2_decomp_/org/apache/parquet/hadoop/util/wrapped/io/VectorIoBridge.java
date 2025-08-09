package org.apache.parquet.hadoop.util.wrapped.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.PositionedReadable;
import org.apache.parquet.Exceptions;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.io.ParquetFileRange;
import org.apache.parquet.util.DynMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class VectorIoBridge {
   private static final Logger LOG = LoggerFactory.getLogger(VectorIoBridge.class);
   private static final String READ_VECTORED = "readVectored";
   private static final String HAS_CAPABILITY = "hasCapability";
   static final String VECTOREDIO_CAPABILITY = "in:readvectored";
   private static final VectorIoBridge INSTANCE = new VectorIoBridge();
   private final DynMethods.UnboundMethod readVectored;
   private final DynMethods.UnboundMethod hasCapabilityMethod;
   private final AtomicLong vectorReads = new AtomicLong();
   private final AtomicLong blocksRead = new AtomicLong();
   private final AtomicLong bytesRead = new AtomicLong();

   private VectorIoBridge() {
      this.readVectored = BindingUtils.loadInvocation(PositionedReadable.class, Void.TYPE, "readVectored", List.class, IntFunction.class);
      LOG.debug("Vector IO availability: {}", this.available());
      this.hasCapabilityMethod = BindingUtils.loadInvocation(FSDataInputStream.class, Boolean.TYPE, "hasCapability", String.class);
   }

   public boolean readVectoredAvailable(FSDataInputStream stream, ByteBufferAllocator allocator) {
      return this.available() && !allocator.isDirect();
   }

   public boolean available() {
      return !this.readVectored.isNoop() && FileRangeBridge.bridgeAvailable();
   }

   private void checkAvailable() {
      if (!this.available()) {
         throw new UnsupportedOperationException("Hadoop VectorIO not found");
      }
   }

   public void readVectoredRanges(FSDataInputStream stream, List ranges, ByteBufferAllocator allocator) throws IOException {
      if (!this.readVectoredAvailable(stream, allocator)) {
         throw new UnsupportedOperationException("Vectored IO not available on stream " + stream);
      } else {
         List<ParquetFileRange> sorted = validateAndSortRanges(ranges);
         FileRangeBridge rangeBridge = FileRangeBridge.instance();
         Stream var10000 = sorted.stream();
         rangeBridge.getClass();
         List<FileRangeBridge.WrappedFileRange> fileRanges = (List)var10000.map(rangeBridge::toFileRange).collect(Collectors.toList());
         this.readWrappedRanges(stream, fileRanges, allocator::allocate);
         fileRanges.forEach((fileRange) -> {
            ParquetFileRange parquetFileRange = (ParquetFileRange)fileRange.getReference();
            parquetFileRange.setDataReadFuture(fileRange.getData());
         });
      }
   }

   private void readWrappedRanges(PositionedReadable stream, List ranges, IntFunction allocate) throws IOException {
      this.vectorReads.incrementAndGet();
      this.blocksRead.addAndGet((long)ranges.size());
      List<Object> instances = (List)ranges.stream().map((r) -> {
         this.bytesRead.addAndGet((long)r.getLength());
         return r.getFileRange();
      }).collect(Collectors.toList());
      LOG.debug("readVectored with {} ranges on stream {}", ranges.size(), stream);

      try {
         this.readVectored.invokeChecked(stream, new Object[]{instances, allocate});
      } catch (Exception e) {
         Exceptions.throwIfInstance(e, IOException.class);
         Exceptions.throwIfInstance(e, RuntimeException.class);
         throw new RuntimeException(e);
      }
   }

   public String toString() {
      return "VectorIoBridge{available=" + this.available() + ", readVectored=" + this.readVectored + ", vectorReads=" + this.vectorReads.get() + ", blocksRead=" + this.blocksRead.get() + ", bytesRead=" + this.bytesRead.get() + '}';
   }

   public boolean hasCapability(FSDataInputStream stream, String capability) {
      if (this.hasCapabilityMethod.isNoop()) {
         return false;
      } else {
         try {
            return (Boolean)this.hasCapabilityMethod.invoke(stream, new Object[]{capability});
         } catch (RuntimeException var4) {
            return false;
         }
      }
   }

   public long getVectorReads() {
      return this.vectorReads.get();
   }

   public long getBlocksRead() {
      return this.blocksRead.get();
   }

   public long getBytesRead() {
      return this.bytesRead.get();
   }

   void resetCounters() {
      this.vectorReads.set(0L);
      this.blocksRead.set(0L);
      this.bytesRead.set(0L);
   }

   private static List sortRanges(List input) {
      List<ParquetFileRange> l = new ArrayList(input);
      l.sort(Comparator.comparingLong(ParquetFileRange::getOffset));
      return l;
   }

   private static ParquetFileRange validateRangeRequest(ParquetFileRange range) {
      Objects.requireNonNull(range, "range is null");
      Preconditions.checkArgument(range.getLength() >= 0, "length is negative in %s", range);
      Preconditions.checkArgument(range.getOffset() >= 0L, "offset is negative in %s", range);
      return range;
   }

   private static List validateAndSortRanges(List input) {
      Objects.requireNonNull(input, "Null input list");
      if (input.isEmpty()) {
         LOG.debug("Empty input list");
         return input;
      } else {
         List<ParquetFileRange> sortedRanges;
         if (input.size() == 1) {
            validateRangeRequest((ParquetFileRange)input.get(0));
            sortedRanges = input;
         } else {
            sortedRanges = sortRanges(input);
            ParquetFileRange prev = null;

            for(ParquetFileRange current : sortedRanges) {
               validateRangeRequest(current);
               if (prev != null) {
                  Preconditions.checkArgument(current.getOffset() >= prev.getOffset() + (long)prev.getLength(), "Overlapping ranges %s and %s", prev, current);
               }

               prev = current;
            }
         }

         return sortedRanges;
      }
   }

   public static VectorIoBridge instance() {
      return INSTANCE;
   }

   public static boolean bridgeAvailable() {
      return instance().available();
   }

   public static VectorIoBridge availableInstance() {
      VectorIoBridge bridge = instance();
      bridge.checkAvailable();
      return bridge;
   }
}
