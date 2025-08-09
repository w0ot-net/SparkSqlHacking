package org.apache.orc.impl.writer;

import java.io.IOException;
import java.util.function.Consumer;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.Decimal64ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.OrcProto;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.IntegerWriter;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.SerializationUtils;
import org.apache.orc.impl.StreamName;

public class DecimalTreeWriter extends TreeWriterBase {
   private final PositionedOutputStream valueStream;
   private final SerializationUtils utils = new SerializationUtils();
   private final long[] scratchLongs;
   private final byte[] scratchBuffer;
   private final IntegerWriter scaleStream;
   private final boolean isDirectV2;

   public DecimalTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.isDirectV2 = this.isNewWriteFormat(context);
      this.valueStream = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.scratchLongs = new long[6];
      this.scratchBuffer = new byte[79];
      this.scaleStream = this.createIntegerWriter(context.createStream(new StreamName(this.id, Kind.SECONDARY, encryption)), true, this.isDirectV2, context);
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

   private void writeBatch(DecimalColumnVector vector, int offset, int length) throws IOException {
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            HiveDecimalWritable value = vector.vector[0];
            this.indexStatistics.updateDecimal(value);
            if (this.createBloomFilter) {
               String str = value.toString(this.scratchBuffer);
               if (this.bloomFilter != null) {
                  this.bloomFilter.addString(str);
               }

               this.bloomFilterUtf8.addString(str);
            }

            for(int i = 0; i < length; ++i) {
               value.serializationUtilsWrite(this.valueStream, this.scratchLongs);
               this.scaleStream.write((long)value.scale());
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vector.noNulls || !vector.isNull[i + offset]) {
               HiveDecimalWritable value = vector.vector[i + offset];
               value.serializationUtilsWrite(this.valueStream, this.scratchLongs);
               this.scaleStream.write((long)value.scale());
               this.indexStatistics.updateDecimal(value);
               if (this.createBloomFilter) {
                  String str = value.toString(this.scratchBuffer);
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addString(str);
                  }

                  this.bloomFilterUtf8.addString(str);
               }
            }
         }
      }

   }

   private void writeBatch(Decimal64ColumnVector vector, int offset, int length) throws IOException {
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            this.indexStatistics.updateDecimal64(vector.vector[0], vector.scale);
            if (this.createBloomFilter) {
               HiveDecimalWritable value = vector.getScratchWritable();
               value.setFromLongAndScale(vector.vector[0], vector.scale);
               String str = value.toString(this.scratchBuffer);
               if (this.bloomFilter != null) {
                  this.bloomFilter.addString(str);
               }

               this.bloomFilterUtf8.addString(str);
            }

            for(int i = 0; i < length; ++i) {
               this.utils.writeVslong(this.valueStream, vector.vector[0]);
               this.scaleStream.write((long)vector.scale);
            }
         }
      } else {
         HiveDecimalWritable value = vector.getScratchWritable();

         for(int i = 0; i < length; ++i) {
            if (vector.noNulls || !vector.isNull[i + offset]) {
               long num = vector.vector[i + offset];
               this.utils.writeVslong(this.valueStream, num);
               this.scaleStream.write((long)vector.scale);
               this.indexStatistics.updateDecimal64(num, vector.scale);
               if (this.createBloomFilter) {
                  value.setFromLongAndScale(num, vector.scale);
                  String str = value.toString(this.scratchBuffer);
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addString(str);
                  }

                  this.bloomFilterUtf8.addString(str);
               }
            }
         }
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      if (vector instanceof Decimal64ColumnVector) {
         this.writeBatch((Decimal64ColumnVector)vector, offset, length);
      } else {
         this.writeBatch((DecimalColumnVector)vector, offset, length);
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
      this.valueStream.getPosition(recorder);
      this.scaleStream.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.valueStream.getBufferSize() + this.scaleStream.estimateMemory();
   }

   public long getRawDataSize() {
      return this.fileStatistics.getNumberOfValues() * (long)JavaDataModel.get().lengthOfDecimal();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.valueStream.flush();
      this.scaleStream.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      Consumer<byte[]> updater = CryptoUtils.modifyIvForStripe((long)stripeId);
      this.valueStream.changeIv(updater);
      this.scaleStream.changeIv(updater);
   }
}
