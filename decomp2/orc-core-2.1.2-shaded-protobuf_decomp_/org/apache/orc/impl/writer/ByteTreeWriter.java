package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.RunLengthByteWriter;
import org.apache.orc.impl.StreamName;

public class ByteTreeWriter extends TreeWriterBase {
   private final RunLengthByteWriter writer;

   public ByteTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.writer = new RunLengthByteWriter(context.createStream(new StreamName(this.id, Kind.DATA, encryption)));
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      LongColumnVector vec = (LongColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            byte value = (byte)((int)vec.vector[0]);
            this.indexStatistics.updateInteger((long)value, length);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addLong((long)value);
               }

               this.bloomFilterUtf8.addLong((long)value);
            }

            for(int i = 0; i < length; ++i) {
               this.writer.write(value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               byte value = (byte)((int)vec.vector[i + offset]);
               this.writer.write(value);
               this.indexStatistics.updateInteger((long)value, 1);
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

   public long estimateMemory() {
      return super.estimateMemory() + this.writer.estimateMemory();
   }

   public long getRawDataSize() {
      long num = this.fileStatistics.getNumberOfValues();
      return num * (long)JavaDataModel.get().primitive1();
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
