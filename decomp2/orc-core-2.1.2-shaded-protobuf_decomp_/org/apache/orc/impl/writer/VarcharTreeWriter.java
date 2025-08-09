package org.apache.orc.impl.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.TypeDescription;
import org.apache.orc.impl.Utf8Utils;

public class VarcharTreeWriter extends StringBaseTreeWriter {
   private final int maxLength;

   VarcharTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.maxLength = schema.getMaxLength();
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      BytesColumnVector vec = (BytesColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            this.writeTruncated(vec, 0, length);
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               this.writeTruncated(vec, i + offset, 1);
            }
         }
      }

   }

   private void writeTruncated(BytesColumnVector vec, int row, int repeats) throws IOException {
      int itemLength = Utf8Utils.truncateBytesTo(this.maxLength, vec.vector[row], vec.start[row], vec.length[row]);
      if (this.useDictionaryEncoding) {
         int id = this.dictionary.add(vec.vector[row], vec.start[row], itemLength);

         for(int i = 0; i < repeats; ++i) {
            this.rows.add(id);
         }
      } else {
         for(int i = 0; i < repeats; ++i) {
            this.directStreamOutput.write(vec.vector[row], vec.start[row], itemLength);
            this.lengthOutput.write((long)itemLength);
         }
      }

      this.indexStatistics.updateString(vec.vector[row], vec.start[row], itemLength, repeats);
      if (this.createBloomFilter) {
         if (this.bloomFilter != null) {
            this.bloomFilter.addString(new String(vec.vector[row], vec.start[row], itemLength, StandardCharsets.UTF_8));
         }

         this.bloomFilterUtf8.addBytes(vec.vector[row], vec.start[row], itemLength);
      }

   }
}
