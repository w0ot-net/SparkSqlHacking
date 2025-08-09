package org.apache.orc.impl.writer;

import java.io.IOException;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.LongColumnVector;
import org.apache.hadoop.hive.ql.util.JavaDataModel;
import org.apache.orc.TypeDescription;
import org.apache.orc.OrcProto.Stream.Kind;
import org.apache.orc.impl.BitFieldWriter;
import org.apache.orc.impl.CryptoUtils;
import org.apache.orc.impl.PositionRecorder;
import org.apache.orc.impl.PositionedOutputStream;
import org.apache.orc.impl.StreamName;

public class BooleanTreeWriter extends TreeWriterBase {
   private final BitFieldWriter writer;

   public BooleanTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      PositionedOutputStream out = context.createStream(new StreamName(this.id, Kind.DATA, encryption));
      this.writer = new BitFieldWriter(out, 1);
      if (this.rowIndexPosition != null) {
         this.recordPosition(this.rowIndexPosition);
      }

   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      LongColumnVector vec = (LongColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            int value = vec.vector[0] == 0L ? 0 : 1;
            this.indexStatistics.updateBoolean(value != 0, length);

            for(int i = 0; i < length; ++i) {
               this.writer.write(value);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               int value = vec.vector[i + offset] == 0L ? 0 : 1;
               this.writer.write(value);
               this.indexStatistics.updateBoolean(value != 0, 1);
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
