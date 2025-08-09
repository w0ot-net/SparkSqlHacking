package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DateColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.OutStream;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.StreamName;

public class DateTreeWriter extends TreeWriterBase {
   private final IntegerWriter writer;
   private final boolean isDirectV2;
   private final boolean useProleptic;

   public DateTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      OutStream out = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.writer = this.createIntegerWriter(out, true, this.isDirectV2, context);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

      this.useProleptic = context.getProlepticGregorian();
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      LongColumnVector vec = (LongColumnVector)vector;
      if (vector instanceof DateColumnVector) {
         ((DateColumnVector)vec).changeCalendar(this.useProleptic, true);
      } else if (this.useProleptic) {
         throw new IllegalArgumentException("Can't use LongColumnVector to write proleptic dates");
      }

      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            int value = (int)vec.vector[0];
            this.indexStatistics.updateDate(value);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong((long)value);
               }

               this.bloomFilterUtf8.addLong((long)value);
            }

            for(int i = 0; i < length; ++i) {
               this.writer.write((long)value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               int value = (int)vec.vector[i + offset];
               this.writer.write((long)value);
               this.indexStatistics.updateDate(value);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addLong((long)value);
                  }

                  this.bloomFilterUtf8.addLong((long)value);
               }
            }
         }
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      super.writeStripe(requiredIndexEntries);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   void recordPosition(PositionRecorder recorder) throws IOException {
      super.recordPosition(recorder);
      this.writer.getPosition(recorder);
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

   public long estimateMemory() {
      return super.estimateMemory() + this.writer.estimateMemory();
   }

   public long getRawDataSize() {
      return this.fileStatistics.getNumberOfValues() * (long)JavaDataModel.get().lengthOfDate();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.writer.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      this.writer.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));
   }
}
