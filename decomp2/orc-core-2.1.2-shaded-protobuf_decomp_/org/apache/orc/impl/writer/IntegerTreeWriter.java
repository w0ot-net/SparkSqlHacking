package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
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

public class IntegerTreeWriter extends TreeWriterBase {
   private final IntegerWriter writer;
   private boolean isDirectV2 = true;
   private final boolean isLong;

   public IntegerTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      OutStream out = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.writer = this.createIntegerWriter(out, true, this.isDirectV2, context);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

      this.isLong = schema.getCategory() == TypeDescription.Category.LONG;
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

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      LongColumnVector vec = (LongColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            long value = vec.vector[0];
            this.indexStatistics.updateInteger(value, length);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong(value);
               }

               this.bloomFilterUtf8.addLong(value);
            }

            for(int i = 0; i < length; ++i) {
               this.writer.write(value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               long value = vec.vector[i + offset];
               this.writer.write(value);
               this.indexStatistics.updateInteger(value, 1);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addLong(value);
                  }

                  this.bloomFilterUtf8.addLong(value);
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

   public long estimateMemory() {
      return super.estimateMemory() + this.writer.estimateMemory();
   }

   public long getRawDataSize() {
      JavaDataModel jdm = JavaDataModel.get();
      long num = this.fileStatistics.getNumberOfValues();
      return num * (long)(this.isLong ? jdm.primitive2() : jdm.primitive1());
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
