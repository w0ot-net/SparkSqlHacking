package org.apache.parquet.bytes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.OutputStreamCloseException;
import org.apache.parquet.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapacityByteArrayOutputStream extends OutputStream {
   private static final Logger LOG = LoggerFactory.getLogger(CapacityByteArrayOutputStream.class);
   private static final ByteBuffer EMPTY_SLAB = ByteBuffer.wrap(new byte[0]);
   private int initialSlabSize;
   private final int maxCapacityHint;
   private final List slabs;
   private ByteBuffer currentSlab;
   private int bytesAllocated;
   private int bytesUsed;
   private ByteBufferAllocator allocator;

   public static int initialSlabSizeHeuristic(int minSlabSize, int targetCapacity, int targetNumSlabs) {
      return Math.max(minSlabSize, (int)((double)targetCapacity / Math.pow((double)2.0F, (double)targetNumSlabs)));
   }

   public static CapacityByteArrayOutputStream withTargetNumSlabs(int minSlabSize, int maxCapacityHint, int targetNumSlabs) {
      return withTargetNumSlabs(minSlabSize, maxCapacityHint, targetNumSlabs, new HeapByteBufferAllocator());
   }

   public static CapacityByteArrayOutputStream withTargetNumSlabs(int minSlabSize, int maxCapacityHint, int targetNumSlabs, ByteBufferAllocator allocator) {
      return new CapacityByteArrayOutputStream(initialSlabSizeHeuristic(minSlabSize, maxCapacityHint, targetNumSlabs), maxCapacityHint, allocator);
   }

   /** @deprecated */
   @Deprecated
   public CapacityByteArrayOutputStream(int initialSlabSize) {
      this(initialSlabSize, 1048576, new HeapByteBufferAllocator());
   }

   /** @deprecated */
   @Deprecated
   public CapacityByteArrayOutputStream(int initialSlabSize, ByteBufferAllocator allocator) {
      this(initialSlabSize, 1048576, allocator);
   }

   /** @deprecated */
   @Deprecated
   public CapacityByteArrayOutputStream(int initialSlabSize, int maxCapacityHint) {
      this(initialSlabSize, maxCapacityHint, new HeapByteBufferAllocator());
   }

   public CapacityByteArrayOutputStream(int initialSlabSize, int maxCapacityHint, ByteBufferAllocator allocator) {
      this.slabs = new ArrayList();
      this.bytesAllocated = 0;
      this.bytesUsed = 0;
      Preconditions.checkArgument(initialSlabSize > 0, "initialSlabSize must be > 0");
      Preconditions.checkArgument(maxCapacityHint > 0, "maxCapacityHint must be > 0");
      Preconditions.checkArgument(maxCapacityHint >= initialSlabSize, "maxCapacityHint can't be less than initialSlabSize %s %s", initialSlabSize, maxCapacityHint);
      this.initialSlabSize = initialSlabSize;
      this.maxCapacityHint = maxCapacityHint;
      this.allocator = allocator;
      this.reset();
   }

   private void addSlab(int minimumSize) {
      try {
         Math.addExact(this.bytesUsed, minimumSize);
      } catch (ArithmeticException e) {
         throw new OutOfMemoryError("Size of data exceeded Integer.MAX_VALUE (" + e.getMessage() + ")");
      }

      int nextSlabSize;
      if (this.bytesUsed == 0) {
         nextSlabSize = this.initialSlabSize;
      } else if (this.bytesUsed > this.maxCapacityHint / 5) {
         nextSlabSize = this.maxCapacityHint / 5;
      } else {
         nextSlabSize = this.bytesUsed;
      }

      if (nextSlabSize < minimumSize) {
         LOG.debug("slab size {} too small for value of size {}. Bumping up slab size", nextSlabSize, minimumSize);
         nextSlabSize = minimumSize;
      }

      LOG.debug("used {} slabs, adding new slab of size {}", this.slabs.size(), nextSlabSize);
      this.currentSlab = this.allocator.allocate(nextSlabSize);
      this.slabs.add(this.currentSlab);
      this.bytesAllocated = Math.addExact(this.bytesAllocated, nextSlabSize);
   }

   public void write(int b) {
      if (!this.currentSlab.hasRemaining()) {
         this.addSlab(1);
      }

      this.currentSlab.put((byte)b);
      this.bytesUsed = Math.addExact(this.bytesUsed, 1);
   }

   public void write(byte[] b, int off, int len) {
      if (off >= 0 && off <= b.length && len >= 0 && off + len - b.length <= 0) {
         if (len > this.currentSlab.remaining()) {
            int length1 = this.currentSlab.remaining();
            this.currentSlab.put(b, off, length1);
            int length2 = len - length1;
            this.addSlab(length2);
            this.currentSlab.put(b, off + length1, length2);
         } else {
            this.currentSlab.put(b, off, len);
         }

         this.bytesUsed = Math.addExact(this.bytesUsed, len);
      } else {
         throw new IndexOutOfBoundsException(String.format("Given byte array of size %d, with requested length(%d) and offset(%d)", b.length, len, off));
      }
   }

   private void writeToOutput(OutputStream out, ByteBuffer buf, int len) throws IOException {
      if (buf.hasArray()) {
         out.write(buf.array(), buf.arrayOffset(), len);
      } else {
         byte[] copy = new byte[len];
         buf.flip();
         buf.get(copy);
         out.write(copy);
      }

   }

   public void writeTo(OutputStream out) throws IOException {
      for(ByteBuffer slab : this.slabs) {
         this.writeToOutput(out, slab, slab.position());
      }

   }

   void writeInto(ByteBuffer buffer) {
      for(ByteBuffer slab : this.slabs) {
         slab.flip();
         buffer.put(slab);
      }

   }

   public long size() {
      return (long)this.bytesUsed;
   }

   public int getCapacity() {
      return this.bytesAllocated;
   }

   public void reset() {
      this.initialSlabSize = Math.max(this.bytesUsed / 7, this.initialSlabSize);
      LOG.debug("initial slab of size {}", this.initialSlabSize);

      for(ByteBuffer slab : this.slabs) {
         this.allocator.release(slab);
      }

      this.slabs.clear();
      this.bytesAllocated = 0;
      this.bytesUsed = 0;
      this.currentSlab = EMPTY_SLAB;
   }

   public long getCurrentIndex() {
      Preconditions.checkArgument(this.bytesUsed > 0, "This is an empty stream");
      return (long)(this.bytesUsed - 1);
   }

   public void setByte(long index, byte value) {
      Preconditions.checkArgument(index < (long)this.bytesUsed, "Index: %d is >= the current size of: %d", index, this.bytesUsed);
      long seen = 0L;

      for(int i = 0; i < this.slabs.size(); ++i) {
         ByteBuffer slab = (ByteBuffer)this.slabs.get(i);
         if (index < seen + (long)slab.limit()) {
            slab.put((int)(index - seen), value);
            break;
         }

         seen += (long)slab.limit();
      }

   }

   public String memUsageString(String prefix) {
      return String.format("%s %s %d slabs, %,d bytes", prefix, this.getClass().getSimpleName(), this.slabs.size(), this.getCapacity());
   }

   int getSlabCount() {
      return this.slabs.size();
   }

   ByteBuffer getInternalByteBuffer() {
      if (this.slabs.size() == 1) {
         ByteBuffer buf = ((ByteBuffer)this.slabs.get(0)).duplicate();
         buf.flip();
         return buf.slice();
      } else {
         return null;
      }
   }

   public void close() {
      for(ByteBuffer slab : this.slabs) {
         this.allocator.release(slab);
      }

      this.slabs.clear();

      try {
         super.close();
      } catch (IOException e) {
         throw new OutputStreamCloseException(e);
      }
   }
}
