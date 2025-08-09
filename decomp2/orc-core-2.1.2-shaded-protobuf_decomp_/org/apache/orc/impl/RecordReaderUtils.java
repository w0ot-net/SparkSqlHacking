package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileRange;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.common.io.DiskRangeList;
import org.apache.hadoop.util.VersionInfo;
import org.apache.orc.CompressionCodec;
import org.apache.orc.DataReader;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeInformation;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.StripeFooter;
import org.apache.orc.OrcProto.Stream.Kind;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecordReaderUtils {
   private static final HadoopShims SHIMS = HadoopShimsFactory.get();
   private static final boolean supportVectoredIO;
   private static final Logger LOG;
   private static final int BYTE_STREAM_POSITIONS = 1;
   private static final int RUN_LENGTH_BYTE_POSITIONS = 2;
   private static final int BITFIELD_POSITIONS = 3;
   private static final int RUN_LENGTH_INT_POSITIONS = 2;
   static final int WORST_UNCOMPRESSED_SLOP = 4098;
   static final int MAX_VALUES_LENGTH = 512;
   static final int MAX_BYTE_WIDTH;

   public static DataReader createDefaultDataReader(DataReaderProperties properties) {
      return new DefaultDataReader(properties);
   }

   static boolean overlap(long leftA, long rightA, long leftB, long rightB) {
      if (leftA <= leftB) {
         return rightA >= leftB;
      } else {
         return rightB >= leftA;
      }
   }

   public static long estimateRgEndOffset(boolean isCompressed, int bufferSize, boolean isLast, long nextGroupOffset, long streamLength) {
      long slop = 4098L;
      if (isCompressed) {
         int stretchFactor = 2 + (512 * MAX_BYTE_WIDTH - 1) / bufferSize;
         slop = (long)(stretchFactor * (3 + bufferSize));
      }

      return isLast ? streamLength : Math.min(streamLength, nextGroupOffset + slop);
   }

   public static int getIndexPosition(OrcProto.ColumnEncoding.Kind columnEncoding, TypeDescription.Category columnType, OrcProto.Stream.Kind streamType, boolean isCompressed, boolean hasNulls) {
      if (streamType == Kind.PRESENT) {
         return 0;
      } else {
         int compressionValue = isCompressed ? 1 : 0;
         int base = hasNulls ? 3 + compressionValue : 0;
         switch (columnType) {
            case BOOLEAN:
            case BYTE:
            case SHORT:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case DATE:
            case STRUCT:
            case MAP:
            case LIST:
            case UNION:
               return base;
            case CHAR:
            case VARCHAR:
            case STRING:
               if (columnEncoding != org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY && columnEncoding != org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY_V2) {
                  if (streamType == Kind.DATA) {
                     return base;
                  }

                  return base + 1 + compressionValue;
               }

               return base;
            case BINARY:
            case DECIMAL:
               if (streamType == Kind.DATA) {
                  return base;
               }

               return base + 1 + compressionValue;
            case TIMESTAMP:
            case TIMESTAMP_INSTANT:
               if (streamType == Kind.DATA) {
                  return base;
               }

               return base + 2 + compressionValue;
            default:
               throw new IllegalArgumentException("Unknown type " + String.valueOf(columnType));
         }
      }
   }

   public static boolean isDictionary(OrcProto.Stream.Kind kind, OrcProto.ColumnEncoding encoding) {
      assert kind != Kind.DICTIONARY_COUNT;

      OrcProto.ColumnEncoding.Kind encodingKind = encoding.getKind();
      return kind == Kind.DICTIONARY_DATA || kind == Kind.LENGTH && (encodingKind == org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY || encodingKind == org.apache.orc.OrcProto.ColumnEncoding.Kind.DICTIONARY_V2);
   }

   public static String stringifyDiskRanges(DiskRangeList range) {
      StringBuilder buffer = new StringBuilder();
      buffer.append("[");

      for(boolean isFirst = true; range != null; range = range.next) {
         if (!isFirst) {
            buffer.append(", {");
         } else {
            buffer.append("{");
         }

         isFirst = false;
         buffer.append(range);
         buffer.append("}");
      }

      buffer.append("]");
      return buffer.toString();
   }

   static long computeEnd(BufferChunk first, BufferChunk last) {
      long end = 0L;

      for(BufferChunk ptr = first; ptr != last.next; ptr = (BufferChunk)ptr.next) {
         end = Math.max(ptr.getEnd(), end);
      }

      return end;
   }

   static void zeroCopyReadRanges(FSDataInputStream file, HadoopShims.ZeroCopyReaderShim zcr, BufferChunk first, BufferChunk last, boolean allocateDirect) throws IOException {
      long offset = first.getOffset();
      int length = (int)(computeEnd(first, last) - offset);
      file.seek(offset);

      List<ByteBuffer> bytes;
      ByteBuffer read;
      for(bytes = new ArrayList(); length > 0; length -= read.remaining()) {
         read = zcr.readBuffer(length, false);
         bytes.add(read);
      }

      long currentOffset = offset;
      BufferChunk current = first;
      Iterator<ByteBuffer> buffers = bytes.iterator();

      for(ByteBuffer currentBuffer = (ByteBuffer)buffers.next(); current != last.next; current = (BufferChunk)current.next) {
         if (current.getOffset() < offset) {
            buffers = bytes.iterator();
            currentBuffer = (ByteBuffer)buffers.next();
            currentOffset = offset;
         }

         while(currentOffset + (long)currentBuffer.remaining() <= current.getOffset()) {
            currentOffset += (long)currentBuffer.remaining();
            currentBuffer = (ByteBuffer)buffers.next();
         }

         if (currentOffset + (long)currentBuffer.remaining() >= current.getEnd()) {
            ByteBuffer copy = currentBuffer.slice();
            copy.position((int)(current.getOffset() - currentOffset));
            copy.limit(copy.position() + current.getLength());
            current.setChunk(copy);
         } else {
            ByteBuffer result = allocateDirect ? ByteBuffer.allocateDirect(current.getLength()) : ByteBuffer.allocate(current.getLength());
            ByteBuffer copy = currentBuffer.slice();
            copy.position((int)(current.getOffset() - currentOffset));
            result.put(copy);
            currentOffset += (long)currentBuffer.remaining();
            currentBuffer = (ByteBuffer)buffers.next();

            while(result.hasRemaining()) {
               if (result.remaining() > currentBuffer.remaining()) {
                  result.put(currentBuffer.slice());
                  currentOffset += (long)currentBuffer.remaining();
                  currentBuffer = (ByteBuffer)buffers.next();
               } else {
                  copy = currentBuffer.slice();
                  copy.limit(result.remaining());
                  result.put(copy);
               }
            }

            result.flip();
            current.setChunk(result);
         }
      }

   }

   static void readRanges(FSDataInputStream file, BufferChunk first, BufferChunk last, boolean allocateDirect) throws IOException {
      long offset = first.getOffset();
      int readSize = (int)(computeEnd(first, last) - offset);
      byte[] buffer = new byte[readSize];

      try {
         file.readFully(offset, buffer, 0, buffer.length);
      } catch (IOException e) {
         throw new IOException(String.format("Failed while reading %s %d:%d", file, offset, buffer.length), e);
      }

      ByteBuffer bytes;
      if (allocateDirect) {
         bytes = ByteBuffer.allocateDirect(readSize);
         bytes.put(buffer);
         bytes.flip();
      } else {
         bytes = ByteBuffer.wrap(buffer);
      }

      for(BufferChunk current = first; current != last.next; current = (BufferChunk)current.next) {
         ByteBuffer currentBytes = current == last ? bytes : bytes.duplicate();
         currentBytes.position((int)(current.getOffset() - offset));
         currentBytes.limit((int)(current.getEnd() - offset));
         current.setChunk(currentBytes);
      }

   }

   static BufferChunk findSingleRead(BufferChunk first) {
      return findSingleRead(first, 0L);
   }

   private static BufferChunk findSingleRead(BufferChunk first, long minSeekSize) {
      BufferChunk last = first;

      for(long currentEnd = first.getEnd(); last.next != null && !last.next.hasData() && last.next.getOffset() <= currentEnd + minSeekSize && last.next.getEnd() - first.getOffset() < 2147483647L; currentEnd = Math.max(currentEnd, last.getEnd())) {
         last = (BufferChunk)last.next;
      }

      return last;
   }

   static void readDiskRanges(FSDataInputStream file, HadoopShims.ZeroCopyReaderShim zcr, BufferChunkList list, boolean doForceDirect) throws IOException {
      readDiskRanges(file, zcr, list, doForceDirect, 0, (double)0.0F);
   }

   private static void readDiskRanges(FSDataInputStream file, HadoopShims.ZeroCopyReaderShim zcr, BufferChunkList list, boolean doForceDirect, int minSeekSize, double minSeekSizeTolerance) throws IOException {
      BufferChunk current = list == null ? null : list.get();

      while(current != null) {
         while(current.hasData()) {
            current = (BufferChunk)current.next;
         }

         if (zcr != null) {
            BufferChunk last = findSingleRead(current);
            zeroCopyReadRanges(file, zcr, current, last, doForceDirect);
            current = (BufferChunk)last.next;
         } else {
            ChunkReader chunkReader = RecordReaderUtils.ChunkReader.create(current, minSeekSize);
            chunkReader.readRanges(file, doForceDirect, minSeekSizeTolerance);
            current = (BufferChunk)chunkReader.to.next;
         }
      }

   }

   private static void readDiskRangesVectored(FSDataInputStream fileInputStream, BufferChunkList range, boolean doForceDirect) throws IOException {
      if (range != null) {
         IntFunction<ByteBuffer> allocate = doForceDirect ? ByteBuffer::allocateDirect : ByteBuffer::allocate;
         ArrayList<FileRange> fileRanges = new ArrayList();
         HashMap<FileRange, BufferChunk> map = new HashMap();

         for(BufferChunk cur = range.get(); cur != null; cur = (BufferChunk)cur.next) {
            if (!cur.hasData()) {
               FileRange fileRange = FileRange.createFileRange(cur.getOffset(), cur.getLength());
               fileRanges.add(fileRange);
               map.put(fileRange, cur);
            }
         }

         fileInputStream.readVectored(fileRanges, allocate);

         for(FileRange r : fileRanges) {
            BufferChunk var11 = (BufferChunk)map.get(r);

            try {
               var11.setChunk((ByteBuffer)r.getData().get());
            } catch (ExecutionException | InterruptedException e) {
               throw new RuntimeException(e);
            }
         }

      }
   }

   static HadoopShims.ZeroCopyReaderShim createZeroCopyShim(FSDataInputStream file, CompressionCodec codec, ByteBufferAllocatorPool pool) throws IOException {
      return codec != null && (!(codec instanceof DirectDecompressionCodec) || !((DirectDecompressionCodec)codec).isAvailable()) ? null : SHIMS.getZeroCopyReader(file, pool);
   }

   static {
      supportVectoredIO = SHIMS.supportVectoredIO(VersionInfo.getVersion());
      LOG = LoggerFactory.getLogger(RecordReaderUtils.class);
      MAX_BYTE_WIDTH = SerializationUtils.decodeBitWidth(SerializationUtils.FixedBitSizes.SIXTYFOUR.ordinal()) / 8;
   }

   private static class DefaultDataReader implements DataReader {
      private FSDataInputStream file;
      private ByteBufferAllocatorPool pool;
      private HadoopShims.ZeroCopyReaderShim zcr = null;
      private final Supplier fileSystemSupplier;
      private final Path path;
      private final boolean useZeroCopy;
      private final int minSeekSize;
      private final double minSeekSizeTolerance;
      private InStream.StreamOptions options;
      private boolean isOpen = false;

      private DefaultDataReader(DataReaderProperties properties) {
         this.fileSystemSupplier = properties.getFileSystemSupplier();
         this.path = properties.getPath();
         this.file = properties.getFile();
         this.useZeroCopy = properties.getZeroCopy();
         this.options = properties.getCompression();
         this.minSeekSize = properties.getMinSeekSize();
         this.minSeekSizeTolerance = properties.getMinSeekSizeTolerance();
      }

      public void open() throws IOException {
         if (this.file == null) {
            this.file = ((FileSystem)this.fileSystemSupplier.get()).open(this.path);
         }

         if (this.useZeroCopy) {
            this.pool = new ByteBufferAllocatorPool();
            this.zcr = RecordReaderUtils.createZeroCopyShim(this.file, this.options.getCodec(), this.pool);
         } else {
            this.zcr = null;
         }

         this.isOpen = true;
      }

      public OrcProto.StripeFooter readStripeFooter(StripeInformation stripe) throws IOException {
         if (!this.isOpen) {
            this.open();
         }

         long offset = stripe.getOffset() + stripe.getIndexLength() + stripe.getDataLength();
         int tailLength = (int)stripe.getFooterLength();
         ByteBuffer tailBuf = ByteBuffer.allocate(tailLength);
         this.file.readFully(offset, tailBuf.array(), tailBuf.arrayOffset(), tailLength);
         return StripeFooter.parseFrom(InStream.createCodedInputStream(InStream.create("footer", new BufferChunk(tailBuf, 0L), 0L, (long)tailLength, this.options)));
      }

      public BufferChunkList readFileData(BufferChunkList range, boolean doForceDirect) throws IOException {
         if (RecordReaderUtils.supportVectoredIO && this.zcr == null) {
            RecordReaderUtils.readDiskRangesVectored(this.file, range, doForceDirect);
         } else {
            RecordReaderUtils.readDiskRanges(this.file, this.zcr, range, doForceDirect, this.minSeekSize, this.minSeekSizeTolerance);
         }

         return range;
      }

      public void close() throws IOException {
         if (this.options.getCodec() != null) {
            OrcCodecPool.returnCodec(this.options.getCodec().getKind(), this.options.getCodec());
            this.options.withCodec((CompressionCodec)null);
         }

         if (this.pool != null) {
            this.pool.clear();
         }

         HadoopShims.ZeroCopyReaderShim myZcr = this.zcr;

         try {
            if (this.file != null) {
               this.file.close();
               this.file = null;
            }
         } catch (Throwable var5) {
            if (myZcr != null) {
               try {
                  myZcr.close();
               } catch (Throwable var4) {
                  var5.addSuppressed(var4);
               }
            }

            throw var5;
         }

         if (myZcr != null) {
            myZcr.close();
         }

      }

      public boolean isTrackingDiskRanges() {
         return this.zcr != null;
      }

      /** @deprecated */
      @Deprecated
      public void releaseBuffer(ByteBuffer buffer) {
         this.zcr.releaseBuffer(buffer);
      }

      public void releaseAllBuffers() {
         this.zcr.releaseAllBuffers();
      }

      public DataReader clone() {
         if (this.file != null) {
            RecordReaderUtils.LOG.warn("Cloning an opened DataReader; the stream will be reused and closed twice");
         }

         try {
            DefaultDataReader clone = (DefaultDataReader)super.clone();
            if (this.options.getCodec() != null) {
               clone.options = this.options.clone();
            }

            return clone;
         } catch (CloneNotSupportedException e) {
            throw new UnsupportedOperationException("uncloneable", e);
         }
      }

      public InStream.StreamOptions getCompressionOptions() {
         return this.options;
      }
   }

   public static final class ByteBufferAllocatorPool implements HadoopShims.ByteBufferPoolShim {
      private final TreeMap buffers = new TreeMap();
      private final TreeMap directBuffers = new TreeMap();
      private long currentGeneration = 0L;

      private TreeMap getBufferTree(boolean direct) {
         return direct ? this.directBuffers : this.buffers;
      }

      public void clear() {
         this.buffers.clear();
         this.directBuffers.clear();
      }

      public ByteBuffer getBuffer(boolean direct, int length) {
         TreeMap<Key, ByteBuffer> tree = this.getBufferTree(direct);
         Map.Entry<Key, ByteBuffer> entry = tree.ceilingEntry(new Key(length, 0L));
         if (entry == null) {
            return direct ? ByteBuffer.allocateDirect(length) : ByteBuffer.allocate(length);
         } else {
            tree.remove(entry.getKey());
            return (ByteBuffer)entry.getValue();
         }
      }

      public void putBuffer(ByteBuffer buffer) {
         TreeMap<Key, ByteBuffer> tree = this.getBufferTree(buffer.isDirect());

         Key key;
         do {
            key = new Key(buffer.capacity(), (long)(this.currentGeneration++));
         } while(tree.putIfAbsent(key, buffer) != null);

      }

      private static final class Key implements Comparable {
         private final int capacity;
         private final long insertionGeneration;

         Key(int capacity, long insertionGeneration) {
            this.capacity = capacity;
            this.insertionGeneration = insertionGeneration;
         }

         public int compareTo(Key other) {
            int c = Integer.compare(this.capacity, other.capacity);
            return c != 0 ? c : Long.compare(this.insertionGeneration, other.insertionGeneration);
         }

         public boolean equals(Object rhs) {
            if (rhs instanceof Key o) {
               return 0 == this.compareTo(o);
            } else {
               return false;
            }
         }

         public int hashCode() {
            return (629 + this.capacity) * 37 + (int)(this.insertionGeneration ^ this.insertionGeneration >> 32);
         }
      }
   }

   static class ChunkReader {
      private final BufferChunk from;
      private final BufferChunk to;
      private final int readBytes;
      private final int reqBytes;

      private ChunkReader(BufferChunk from, BufferChunk to, int readSize, int reqBytes) {
         this.from = from;
         this.to = to;
         this.readBytes = readSize;
         this.reqBytes = reqBytes;
      }

      double getExtraBytesFraction() {
         return (double)(this.readBytes - this.reqBytes) / (double)this.reqBytes;
      }

      public int getReadBytes() {
         return this.readBytes;
      }

      public int getReqBytes() {
         return this.reqBytes;
      }

      public BufferChunk getFrom() {
         return this.from;
      }

      public BufferChunk getTo() {
         return this.to;
      }

      void populateChunks(ByteBuffer bytes, boolean allocateDirect, double extraByteTolerance) {
         if (this.getExtraBytesFraction() > extraByteTolerance) {
            RecordReaderUtils.LOG.debug("ExtraBytesFraction = {}, ExtraByteTolerance = {}, reducing memory size", this.getExtraBytesFraction(), extraByteTolerance);
            this.populateChunksReduceSize(bytes, allocateDirect);
         } else {
            RecordReaderUtils.LOG.debug("ExtraBytesFraction = {}, ExtraByteTolerance = {}, populating as is", this.getExtraBytesFraction(), extraByteTolerance);
            this.populateChunksAsIs(bytes);
         }

      }

      void populateChunksAsIs(ByteBuffer bytes) {
         BufferChunk current = this.from;

         for(long offset = this.from.getOffset(); current != this.to.next; current = (BufferChunk)current.next) {
            ByteBuffer currentBytes = current == this.to ? bytes : bytes.duplicate();
            currentBytes.position((int)(current.getOffset() - offset));
            currentBytes.limit((int)(current.getEnd() - offset));
            current.setChunk(currentBytes);
         }

      }

      void populateChunksReduceSize(ByteBuffer bytes, boolean allocateDirect) {
         ByteBuffer newBuffer;
         if (allocateDirect) {
            newBuffer = ByteBuffer.allocateDirect(this.reqBytes);
            newBuffer.position(this.reqBytes);
            newBuffer.flip();
         } else {
            byte[] newBytes = new byte[this.reqBytes];
            newBuffer = ByteBuffer.wrap(newBytes);
         }

         long offset = this.from.getOffset();
         int copyStart = 0;
         int skippedBytes = 0;

         for(BufferChunk current = this.from; current != this.to.next; current = (BufferChunk)current.next) {
            int srcPosition = (int)(current.getOffset() - offset);
            skippedBytes += Math.max(0, srcPosition - copyStart);
            copyStart = Math.max(copyStart, srcPosition);
            int copyEnd = (int)(current.getEnd() - offset);
            int copyLength = copyStart < copyEnd ? copyEnd - copyStart : 0;
            newBuffer.put(bytes.array(), copyStart, copyLength);
            copyStart += copyLength;
            ByteBuffer currentBytes = current == this.to ? newBuffer : newBuffer.duplicate();
            currentBytes.position(srcPosition - skippedBytes);
            currentBytes.limit(currentBytes.position() + current.getLength());
            current.setChunk(currentBytes);
         }

      }

      void readRanges(FSDataInputStream file, boolean allocateDirect, double extraByteTolerance) throws IOException {
         long offset = this.from.getOffset();
         int readSize = (int)(RecordReaderUtils.computeEnd(this.from, this.to) - offset);
         byte[] buffer = new byte[readSize];

         try {
            file.readFully(offset, buffer, 0, buffer.length);
         } catch (IOException e) {
            throw new IOException(String.format("Failed while reading %s %d:%d", file, offset, buffer.length), e);
         }

         ByteBuffer bytes;
         if (allocateDirect) {
            bytes = ByteBuffer.allocateDirect(readSize);
            bytes.put(buffer);
            bytes.flip();
         } else {
            bytes = ByteBuffer.wrap(buffer);
         }

         this.populateChunks(bytes, allocateDirect, extraByteTolerance);
      }

      static ChunkReader create(BufferChunk from, BufferChunk to) {
         long start = Long.MAX_VALUE;
         long end = Long.MIN_VALUE;
         long currentStart = Long.MAX_VALUE;
         long currentEnd = Long.MIN_VALUE;
         long reqBytes = 0L;

         for(BufferChunk current = from; current != to.next; current = (BufferChunk)current.next) {
            start = Math.min(start, current.getOffset());
            end = Math.max(end, current.getEnd());
            if (currentEnd != Long.MIN_VALUE && current.getOffset() > currentEnd) {
               reqBytes += currentEnd - currentStart;
               currentStart = current.getOffset();
               currentEnd = current.getEnd();
            } else {
               currentStart = Math.min(currentStart, current.getOffset());
               currentEnd = Math.max(currentEnd, current.getEnd());
            }
         }

         reqBytes += currentEnd - currentStart;
         if (reqBytes > 2147483639L) {
            throw new IllegalArgumentException("invalid reqBytes value " + reqBytes + ",out of bounds 2147483639");
         } else {
            long readBytes = end - start;
            if (readBytes > 2147483639L) {
               throw new IllegalArgumentException("invalid readBytes value " + readBytes + ",out of bounds 2147483639");
            } else {
               return new ChunkReader(from, to, (int)readBytes, (int)reqBytes);
            }
         }
      }

      static ChunkReader create(BufferChunk from, int minSeekSize) {
         BufferChunk to = RecordReaderUtils.findSingleRead(from, (long)minSeekSize);
         return create(from, to);
      }
   }
}
