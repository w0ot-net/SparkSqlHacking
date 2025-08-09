package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ListColumnVector;
import org.apache.orc.ColumnStatistics;
import org.apache.orc.OrcProto;
import org.apache.orc.StripeStatistics;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.StreamName;

public class ListTreeWriter extends TreeWriterBase {
   private final IntegerWriter lengths;
   private final boolean isDirectV2;
   private final TreeWriter childWriter;

   ListTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.childWriter = TreeWriter.Factory.create((TypeDescription)schema.getChildren().get(0), encryption, context);
      this.lengths = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.LENGTH, encryption)), false, this.isDirectV2, context);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   OrcProto.ColumnEncoding.Builder getEncoding() {
      OrcProto.ColumnEncoding.Builder result = super.getEncoding();
      if (this.isDirectV2) {
         result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT_V2);
      } else {
         result.setKind(org.apache.orc.OrcProto.ColumnEncoding.Kind.DIRECT);
      }

      return result;
   }

   public void createRowIndexEntry() throws IOException {
      super.createRowIndexEntry();
      this.childWriter.createRowIndexEntry();
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      ListColumnVector vec = (ListColumnVector)vector;
      this.indexStatistics.updateCollectionLength((long)vec.lengths.length);
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            int childOffset = (int)vec.offsets[0];
            int childLength = (int)vec.lengths[0];

            for(int i = 0; i < length; ++i) {
               this.lengths.write((long)childLength);
               this.childWriter.writeBatch(vec.child, childOffset, childLength);
            }

            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong((long)childLength);
               }

               this.bloomFilterUtf8.addLong((long)childLength);
            }
         }
      } else {
         int currentOffset = 0;
         int currentLength = 0;

         for(int i = 0; i < length; ++i) {
            if (!vec.isNull[i + offset]) {
               int nextLength = (int)vec.lengths[offset + i];
               int nextOffset = (int)vec.offsets[offset + i];
               this.lengths.write((long)nextLength);
               if (currentLength == 0) {
                  currentOffset = nextOffset;
                  currentLength = nextLength;
               } else if (currentOffset + currentLength != nextOffset) {
                  this.childWriter.writeBatch(vec.child, currentOffset, currentLength);
                  currentOffset = nextOffset;
                  currentLength = nextLength;
               } else {
                  currentLength += nextLength;
               }

               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addLong((long)nextLength);
                  }

                  this.bloomFilterUtf8.addLong((long)nextLength);
               }
            }
         }

         if (currentLength != 0) {
            this.childWriter.writeBatch(vec.child, currentOffset, currentLength);
         }
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      super.writeStripe(requiredIndexEntries);
      this.childWriter.writeStripe(requiredIndexEntries);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   void recordPosition(PositionRecorder recorder) throws IOException {
      super.recordPosition(recorder);
      this.lengths.getPosition(recorder);
   }

   public void addStripeStatistics(StripeStatistics[] stats) throws IOException {
      super.addStripeStatistics(stats);
      this.childWriter.addStripeStatistics(stats);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.lengths.estimateMemory() + this.childWriter.estimateMemory();
   }

   public long getRawDataSize() {
      return this.childWriter.getRawDataSize();
   }

   public void writeFileStatistics() throws IOException {
      super.writeFileStatistics();
      this.childWriter.writeFileStatistics();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.lengths.flush();
      this.childWriter.flushStreams();
   }

   public void getCurrentStatistics(ColumnStatistics[] output) {
      super.getCurrentStatistics(output);
      this.childWriter.getCurrentStatistics(output);
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      this.lengths.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));
      this.childWriter.prepareStripe(stripeId);
   }
}
