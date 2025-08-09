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

public class FloatTreeWriter extends TreeWriterBase {
   private final PositionedOutputStream stream;
   private final SerializationUtils utils;

   public FloatTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
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
            float value = (float)vec.vector[0];
            this.indexStatistics.updateDouble((double)value);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addDouble((double)value);
               }

               this.bloomFilterUtf8.addDouble((double)value);
            }

            for(int i = 0; i < length; ++i) {
               this.utils.writeFloat(this.stream, value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               float value = (float)vec.vector[i + offset];
               this.utils.writeFloat(this.stream, value);
               this.indexStatistics.updateDouble((double)value);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addDouble((double)value);
                  }

                  this.bloomFilterUtf8.addDouble((double)value);
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
      this.stream.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.stream.getBufferSize();
   }

   public long getRawDataSize() {
      long num = this.fileStatistics.getNumberOfValues();
      return num * (long)JavaDataModel.get().primitive1();
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
