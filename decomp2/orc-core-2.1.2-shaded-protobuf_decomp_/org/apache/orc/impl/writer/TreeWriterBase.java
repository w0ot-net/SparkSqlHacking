package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.VectorizedRowBatch;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.OrcFile;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.BloomFilterIndex;
import org.apache.orc.OrcProto.ColumnEncoding;
import org.apache.orc.OrcProto.RowIndex;
import org.apache.orc.OrcProto.RowIndexEntry;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.BitFieldWriter;
import org.apache.orc.impl.ColumnStatisticsImpl;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.OutStream;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.RunLengthIntegerWriter;
import org.apache.orc.impl.RunLengthIntegerWriterV2;
import org.apache.orc.impl.StreamName;
import org.apache.orc.util.BloomFilter;
import org.apache.orc.util.BloomFilterIO;
import org.apache.orc.util.BloomFilterUtf8;

public abstract class TreeWriterBase implements TreeWriter {
   protected final int id;
   protected final BitFieldWriter isPresent;
   protected final TypeDescription schema;
   protected final WriterEncryptionVariant encryption;
   private final boolean isCompressed;
   protected final ColumnStatisticsImpl indexStatistics;
   protected final ColumnStatisticsImpl stripeColStatistics;
   protected final ColumnStatisticsImpl fileStatistics;
   protected final RowIndexPositionRecorder rowIndexPosition;
   private final OrcProto.RowIndex.Builder rowIndex;
   private final OrcProto.RowIndexEntry.Builder rowIndexEntry;
   protected final BloomFilter bloomFilter;
   protected final BloomFilterUtf8 bloomFilterUtf8;
   protected final boolean createBloomFilter;
   private final OrcProto.BloomFilterIndex.Builder bloomFilterIndex;
   private final OrcProto.BloomFilterIndex.Builder bloomFilterIndexUtf8;
   protected final OrcProto.BloomFilter.Builder bloomFilterEntry;
   private boolean foundNulls;
   private OutStream isPresentOutStream;
   protected final WriterContext context;

   TreeWriterBase(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      this.schema = schema;
      this.encryption = encryption;
      this.context = context;
      this.isCompressed = context.isCompressed();
      this.id = schema.getId();
      this.isPresentOutStream = context.createStream(new StreamName(this.id, Kind.PRESENT, encryption));
      this.isPresent = new BitFieldWriter(this.isPresentOutStream, 1);
      this.foundNulls = false;
      this.createBloomFilter = context.getBloomFilterColumns()[this.id];
      boolean proleptic = context.getProlepticGregorian();
      this.indexStatistics = ColumnStatisticsImpl.create(schema, proleptic);
      this.stripeColStatistics = ColumnStatisticsImpl.create(schema, proleptic);
      this.fileStatistics = ColumnStatisticsImpl.create(schema, proleptic);
      if (context.buildIndex()) {
         this.rowIndex = RowIndex.newBuilder();
         this.rowIndexEntry = RowIndexEntry.newBuilder();
         this.rowIndexPosition = new RowIndexPositionRecorder(this.rowIndexEntry);
      } else {
         this.rowIndex = null;
         this.rowIndexEntry = null;
         this.rowIndexPosition = null;
      }

      if (this.createBloomFilter) {
         this.bloomFilterEntry = org.apache.orc.OrcProto.BloomFilter.newBuilder();
         if (context.getBloomFilterVersion() == OrcFile.BloomFilterVersion.ORIGINAL) {
            this.bloomFilter = new BloomFilter((long)context.getRowIndexStride(), context.getBloomFilterFPP());
            this.bloomFilterIndex = BloomFilterIndex.newBuilder();
         } else {
            this.bloomFilter = null;
            this.bloomFilterIndex = null;
         }

         this.bloomFilterUtf8 = new BloomFilterUtf8((long)context.getRowIndexStride(), context.getBloomFilterFPP());
         this.bloomFilterIndexUtf8 = BloomFilterIndex.newBuilder();
      } else {
         this.bloomFilterEntry = null;
         this.bloomFilterIndex = null;
         this.bloomFilterIndexUtf8 = null;
         this.bloomFilter = null;
         this.bloomFilterUtf8 = null;
      }

   }

   protected OrcProto.RowIndex.Builder getRowIndex() {
      return this.rowIndex;
   }

   protected ColumnStatisticsImpl getStripeStatistics() {
      return this.stripeColStatistics;
   }

   protected OrcProto.RowIndexEntry.Builder getRowIndexEntry() {
      return this.rowIndexEntry;
   }

   IntegerWriter createIntegerWriter(PositionedOutputStream output, boolean signed, boolean isDirectV2, WriterContext writer) {
      if (isDirectV2) {
         boolean alignedBitpacking = writer.getEncodingStrategy().equals(OrcFile.EncodingStrategy.SPEED);
         return new RunLengthIntegerWriterV2(output, signed, alignedBitpacking);
      } else {
         return new RunLengthIntegerWriter(output, signed);
      }
   }

   boolean isNewWriteFormat(WriterContext writer) {
      return writer.getVersion() != OrcFile.Version.V_0_11;
   }

   public void writeRootBatch(VectorizedRowBatch batch, int offset, int length) throws IOException {
      this.writeBatch(batch.cols[0], offset, length);
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      if (vector.noNulls) {
         this.indexStatistics.increment(length);
         if (this.isPresent != null) {
            for(int i = 0; i < length; ++i) {
               this.isPresent.write(1);
            }
         }
      } else if (vector.isRepeating) {
         boolean isNull = vector.isNull[0];
         if (this.isPresent != null) {
            for(int i = 0; i < length; ++i) {
               this.isPresent.write(isNull ? 0 : 1);
            }
         }

         if (isNull) {
            this.foundNulls = true;
            this.indexStatistics.setNull();
         } else {
            this.indexStatistics.increment(length);
         }
      } else {
         int nonNullCount = 0;

         for(int i = 0; i < length; ++i) {
            boolean isNull = vector.isNull[i + offset];
            if (!isNull) {
               ++nonNullCount;
            }

            if (this.isPresent != null) {
               this.isPresent.write(isNull ? 0 : 1);
            }
         }

         this.indexStatistics.increment(nonNullCount);
         if (nonNullCount != length) {
            this.foundNulls = true;
            this.indexStatistics.setNull();
         }
      }

   }

   private void removeIsPresentPositions() {
      for(int i = 0; i < this.rowIndex.getEntryCount(); ++i) {
         OrcProto.RowIndexEntry.Builder entry = this.rowIndex.getEntryBuilder(i);
         List<Long> positions = entry.getPositionsList();
         positions = positions.subList(this.isCompressed ? 4 : 3, positions.size());
         entry.clearPositions();
         entry.addAllPositions(positions);
      }

   }

   public void prepareStripe(int stripeId) {
      if (this.isPresent != null) {
         this.isPresent.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));
      }

   }

   public void flushStreams() throws IOException {
      if (this.isPresent != null) {
         this.isPresent.flush();
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      if (this.isPresent != null && !this.foundNulls) {
         this.isPresentOutStream.suppress();
         if (this.rowIndex != null) {
            this.removeIsPresentPositions();
         }
      }

      long byteCount = this.context.getPhysicalWriter().getFileBytes(this.id, this.encryption);
      this.stripeColStatistics.updateByteCount(byteCount);
      this.fileStatistics.merge(this.stripeColStatistics);
      this.context.writeStatistics(new StreamName(this.id, Kind.STRIPE_STATISTICS, this.encryption), this.stripeColStatistics.serialize());
      this.stripeColStatistics.reset();
      this.foundNulls = false;
      this.context.setEncoding(this.id, this.encryption, this.getEncoding().build());
      if (this.rowIndex != null) {
         if (this.rowIndex.getEntryCount() != requiredIndexEntries) {
            int var10002 = this.rowIndex.getEntryCount();
            throw new IllegalArgumentException("Column has wrong number of index entries found: " + var10002 + " expected: " + requiredIndexEntries);
         }

         this.context.writeIndex(new StreamName(this.id, Kind.ROW_INDEX, this.encryption), this.rowIndex);
         this.rowIndex.clear();
         this.rowIndexEntry.clear();
      }

      if (this.bloomFilterIndex != null) {
         this.context.writeBloomFilter(new StreamName(this.id, Kind.BLOOM_FILTER), this.bloomFilterIndex);
         this.bloomFilterIndex.clear();
      }

      if (this.bloomFilterIndexUtf8 != null) {
         this.context.writeBloomFilter(new StreamName(this.id, Kind.BLOOM_FILTER_UTF8), this.bloomFilterIndexUtf8);
         this.bloomFilterIndexUtf8.clear();
      }

   }

   OrcProto.ColumnEncoding.Builder getEncoding() {
      OrcProto.ColumnEncoding.Builder builder = ColumnEncoding.newBuilder().setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT);
      if (this.createBloomFilter) {
         builder.setBloomEncoding(BloomFilterIO.Encoding.CURRENT.getId());
      }

      return builder;
   }

   public void createRowIndexEntry() throws IOException {
      this.stripeColStatistics.merge(this.indexStatistics);
      this.rowIndexEntry.setStatistics(this.indexStatistics.serialize());
      this.indexStatistics.reset();
      this.rowIndex.addEntry(this.rowIndexEntry);
      this.rowIndexEntry.clear();
      this.addBloomFilterEntry();
      this.recordPosition(this.rowIndexPosition);
   }

   void addBloomFilterEntry() {
      if (this.createBloomFilter) {
         if (this.bloomFilter != null) {
            BloomFilterIO.serialize(this.bloomFilterEntry, this.bloomFilter);
            this.bloomFilterIndex.addBloomFilter(this.bloomFilterEntry.build());
            this.bloomFilter.reset();
         }

         if (this.bloomFilterUtf8 != null) {
            BloomFilterIO.serialize(this.bloomFilterEntry, this.bloomFilterUtf8);
            this.bloomFilterIndexUtf8.addBloomFilter(this.bloomFilterEntry.build());
            this.bloomFilterUtf8.reset();
         }
      }

   }

   public void addStripeStatistics(StripeStatistics[] stats) throws IOException {
      int variantId;
      int relativeColumn;
      if (this.encryption == null) {
         variantId = stats.length - 1;
         relativeColumn = this.id;
      } else {
         variantId = this.encryption.getVariantId();
         relativeColumn = this.id - this.encryption.getRoot().getId();
      }

      OrcProto.ColumnStatistics colStats = stats[variantId].getColumn(relativeColumn);
      this.fileStatistics.merge(ColumnStatisticsImpl.deserialize(this.schema, colStats));
      this.context.writeStatistics(new StreamName(this.id, Kind.STRIPE_STATISTICS, this.encryption), colStats.toBuilder());
   }

   void recordPosition(PositionRecorder recorder) throws IOException {
      if (this.isPresent != null) {
         this.isPresent.getPosition(recorder);
      }

   }

   public long estimateMemory() {
      long result = 0L;
      if (this.isPresent != null) {
         result = this.isPresentOutStream.getBufferSize();
      }

      return result;
   }

   public void writeFileStatistics() throws IOException {
      this.context.writeStatistics(new StreamName(this.id, Kind.FILE_STATISTICS, this.encryption), this.fileStatistics.serialize());
   }

   public void getCurrentStatistics(ColumnStatistics[] output) {
      output[this.id] = this.fileStatistics;
   }

   static class RowIndexPositionRecorder implements PositionRecorder {
      private final OrcProto.RowIndexEntry.Builder builder;

      RowIndexPositionRecorder(OrcProto.RowIndexEntry.Builder builder) {
         this.builder = builder;
      }

      public void addPosition(long position) {
         this.builder.addPositions(position);
      }
   }
}
