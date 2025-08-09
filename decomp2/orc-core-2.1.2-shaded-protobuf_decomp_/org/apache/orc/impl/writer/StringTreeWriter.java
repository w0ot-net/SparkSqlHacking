package org.apache.orc.impl.writer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.hadoop.hive.ql.exec.vector.BytesColumnVector;
import org.apache.hadoop.hive.ql.exec.vector.ColumnVector;
import org.apache.orc.TypeDescription;

public class StringTreeWriter extends StringBaseTreeWriter {
   StringTreeWriter(TypeDescription schema, WriterEncryptionVariant encryption, WriterContext context) throws IOException {
      super(schema, encryption, context);
   }

   public void writeBatch(ColumnVector vector, int offset, int length) throws IOException {
      super.writeBatch(vector, offset, length);
      BytesColumnVector vec = (BytesColumnVector)vector;
      if (vector.isRepeating) {
         if (vector.noNulls || !vector.isNull[0]) {
            if (this.useDictionaryEncoding) {
               int id = this.dictionary.add(vec.vector[0], vec.start[0], vec.length[0]);

               for(int i = 0; i < length; ++i) {
                  this.rows.add(id);
               }
            } else {
               for(int i = 0; i < length; ++i) {
                  this.directStreamOutput.write(vec.vector[0], vec.start[0], vec.length[0]);
                  this.lengthOutput.write((long)vec.length[0]);
               }
            }

            this.indexStatistics.updateString(vec.vector[0], vec.start[0], vec.length[0], length);
            if (this.createBloomFilter) {
               if (this.bloomFilter != null) {
                  this.bloomFilter.addString(new String(vec.vector[0], vec.start[0], vec.length[0], StandardCharsets.UTF_8));
               }

               this.bloomFilterUtf8.addBytes(vec.vector[0], vec.start[0], vec.length[0]);
            }
         }
      } else {
         for(int i = 0; i < length; ++i) {
            if (vec.noNulls || !vec.isNull[i + offset]) {
               if (this.useDictionaryEncoding) {
                  this.rows.add(this.dictionary.add(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]));
               } else {
                  this.directStreamOutput.write(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]);
                  this.lengthOutput.write((long)vec.length[offset + i]);
               }

               this.indexStatistics.updateString(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i], 1);
               if (this.createBloomFilter) {
                  if (this.bloomFilter != null) {
                     this.bloomFilter.addString(new String(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i], StandardCharsets.UTF_8));
                  }

                  this.bloomFilterUtf8.addBytes(vec.vector[offset + i], vec.start[offset + i], vec.length[offset + i]);
               }
            }
         }
      }

   }
}
