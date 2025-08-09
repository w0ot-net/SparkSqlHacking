package org.apache.orc.impl.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.TypeDescription;
import org.apache.orc.impl.Utf8Utils;

public class CharTreeWriter extends StringBaseTreeWriter {
   private final int maxLength;
   private final byte[] padding;

   CharTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
      this.maxLength = schema.getMaxLength();
      this.padding = new byte[6 * this.maxLength];
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      BytesColumnVector vec = (BytesColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            this.writePadded(vec, 0, length);
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               this.writePadded(vec, i + offset, 1);
            }
         }
      }

   }

   private void writePadded(BytesColumnVector vec, int row, int repeats) throws IOException {
      int charLength = Utf8Utils.charLength(vec.vector[row], vec.start[row], vec.length[row]);
      byte[] ptr;
      int ptrOffset;
      int ptrLength;
      if (charLength >= this.maxLength) {
         ptr = vec.vector[row];
         ptrOffset = vec.start[row];
         ptrLength = Utf8Utils.truncateBytesTo(this.maxLength, vec.vector[row], vec.start[row], vec.length[row]);
      } else {
         ptr = this.padding;
         ptrLength = vec.length[row] + (this.maxLength - charLength);
         ptrOffset = 0;
         System.arraycopy(vec.vector[row], vec.start[row], ptr, 0, vec.length[row]);
         Arrays.fill(ptr, vec.length[row], ptrLength, (byte)32);
      }

      if (this.useDictionaryEncoding) {
         int id = this.dictionary.add(ptr, ptrOffset, ptrLength);

         for(int i = 0; i < repeats; ++i) {
            this.rows.add(id);
         }
      } else {
         for(int i = 0; i < repeats; ++i) {
            this.directStreamOutput.write(ptr, ptrOffset, ptrLength);
            this.lengthOutput.write((long)ptrLength);
         }
      }

      this.indexStatistics.updateString(ptr, ptrOffset, ptrLength, repeats);
      if (this.createBloomFilter) {
         if (this.bloomFilter != null) {
            this.bloomFilter.addString(new String(ptr, ptrOffset, ptrLength, StandardCharsets.UTF_8));
         }

         this.bloomFilterUtf8.addBytes(ptr, ptrOffset, ptrLength);
      }

   }
}
