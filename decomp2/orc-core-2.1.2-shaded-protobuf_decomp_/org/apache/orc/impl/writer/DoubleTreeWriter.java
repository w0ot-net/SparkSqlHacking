package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DoubleColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.SerializationUtils;
import org.apache.orc.impl.StreamName;

public class DoubleTreeWriter extends TreeWriterBase {
   private final PositionedOutputStream stream;
   private final SerializationUtils utils;

   public DoubleTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.stream = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.utils = new SerializationUtils();
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      DoubleColumnVector vec = (DoubleColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            double value = vec.vector[0];
            this.indexStatistics.updateDouble(value);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addDouble(value);
               }

               this.bloomFilterUtf8.addDouble(value);
            }

            for(int i = 0; i < length; ++i) {
               this.utils.writeDouble(this.stream, value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               double value = vec.vector[i + offset];
               this.utils.writeDouble(this.stream, value);
               this.indexStatistics.updateDouble(value);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addDouble(value);
                  }

                  this.bloomFilterUtf8.addDouble(value);
               }
            }
         }
      }

   }

   public void writeStripe(int requiredIndexEntries) throws IOException {
      super.writeStripe(requiredIndexEntries);
      this.stream.flush();
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   void recordPosition(PositionRecorder recorder) throws IOException {
      super.recordPosition(recorder);
      this.stream.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.stream.getBufferSize();
   }

   public long getRawDataSize() {
      long num = this.fileStatistics.getNumberOfValues();
      return num * (long)JavaDataModel.get().primitive2();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.stream.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      this.stream.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));
   }
}
