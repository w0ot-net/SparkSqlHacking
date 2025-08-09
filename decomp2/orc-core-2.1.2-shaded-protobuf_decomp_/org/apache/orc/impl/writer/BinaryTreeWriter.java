package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.function.Consumer;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.BinaryColumnStatistics;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.StreamName;

public class BinaryTreeWriter extends TreeWriterBase {
   private final PositionedOutputStream stream;
   private final IntegerWriter length;
   private boolean isDirectV2 = true;

   public BinaryTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.stream = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.length = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.LENGTH, encryption)), false, this.isDirectV2, context);
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

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      BytesColumnVector vec = (BytesColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            for(int i = 0; i < length; ++i) {
               this.stream.write(vec.vector[0], vec.start[0], vec.length[0]);
               this.length.write((long)vec.length[0]);
            }

            this.indexStatistics.updateBinary(vec.vector[0], vec.start[0], vec.length[0], length);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addBytes(vec.vector[0], vec.start[0], vec.length[0]);
               }

               this.bloomFilterUtf8.addBytes(vec.vector[0], vec.start[0], vec.length[0]);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               this.stream.write(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]);
               this.length.write((long)vec.length[offset + i]);
               this.indexStatistics.updateBinary(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i], 1);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addBytes(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]);
                  }

                  this.bloomFilterUtf8.addBytes(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]);
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
      this.length.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.stream.getBufferSize() + this.length.estimateMemory();
   }

   public long getRawDataSize() {
      BinaryColumnStatistics bcs = (BinaryColumnStatistics)this.fileStatistics;
      return bcs.getSum();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.stream.flush();
      this.length.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      Consumer<byte[]> updater = CryptoUtils.modifyIvForStripe((long)stripeId);
      this.stream.changeIv(updater);
      this.length.changeIv(updater);
   }
}
