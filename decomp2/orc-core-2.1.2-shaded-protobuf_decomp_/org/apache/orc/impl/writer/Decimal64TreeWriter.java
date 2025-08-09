package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.Decimal64ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.DecimalColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.hadoop.hive.serde2.io.HiveDecimalWritable;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.OutStream;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.RunLengthIntegerWriterV2;
import org.apache.orc.impl.StreamName;

public class Decimal64TreeWriter extends TreeWriterBase {
   private final RunLengthIntegerWriterV2 valueWriter;
   private final int scale;

   public Decimal64TreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      OutStream stream = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.valueWriter = new RunLengthIntegerWriterV2(stream, true, true);
      this.scale = schema.getScale();
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   private void writeBatch(DecimalColumnVector vector, int offset, int length) throws IOException {
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            HiveDecimalWritable value = vector.vector[0];
            long lg = value.serialize64(this.scale);
            this.indexStatistics.updateDecimal64(lg, this.scale);
            if (this.createBloomFilter) {
               this.bloomFilterUtf8.addLong(lg);
            }

            for(int i = 0; i < length; ++i) {
               this.valueWriter.write(lg);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vector.noNulls || !vector.isNull[i + offset]) {
               HiveDecimalWritable value = vector.vector[i + offset];
               long lg = value.serialize64(this.scale);
               this.valueWriter.write(lg);
               this.indexStatistics.updateDecimal64(lg, this.scale);
               if (this.createBloomFilter) {
                  this.bloomFilterUtf8.addLong(lg);
               }
            }
         }
      }

   }

   private void writeBatch(Decimal64ColumnVector vector, int offset, int length) throws IOException {
      assert this.scale == vector.scale;

      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            long lg = vector.vector[0];
            this.indexStatistics.updateDecimal64(lg, this.scale);
            if (this.createBloomFilter) {
               this.bloomFilterUtf8.addLong(lg);
            }

            for(int i = 0; i < length; ++i) {
               this.valueWriter.write(lg);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vector.noNulls || !vector.isNull[i + offset]) {
               long lg = vector.vector[i + offset];
               this.valueWriter.write(lg);
               this.indexStatistics.updateDecimal64(lg, this.scale);
               if (this.createBloomFilter) {
                  this.bloomFilterUtf8.addLong(lg);
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
      this.valueWriter.getPosition(recorder);
   }

   public long estimateMemory() {
      return super.estimateMemory() + this.valueWriter.estimateMemory();
   }

   public long getRawDataSize() {
      return this.fileStatistics.getNumberOfValues() * (long)JavaDataModel.get().primitive2();
   }

   public void flushStreams() throws IOException {
      super.flushStreams();
      this.valueWriter.flush();
   }

   public void prepareStripe(int stripeId) {
      super.prepareStripe(stripeId);
      this.valueWriter.changeIv(CryptoUtils.modifyIvForStripe((long)stripeId));
   }
}
