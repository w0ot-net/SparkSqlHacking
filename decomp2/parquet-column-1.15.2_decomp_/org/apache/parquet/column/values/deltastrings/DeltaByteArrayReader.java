package org.apache.parquet.column.values.deltastrings;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.column.values.RequiresPreviousReader;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.delta.DeltaBinaryPackingValuesReader;
import org.apache.parquet.column.values.deltalengthbytearray.DeltaLengthByteArrayValuesReader;
import org.apache.parquet.io.api.Binary;

public class DeltaByteArrayReader extends ValuesReader implements RequiresPreviousReader {
   private ValuesReader prefixLengthReader = new DeltaBinaryPackingValuesReader();
   private ValuesReader suffixReader = new DeltaLengthByteArrayValuesReader();
   private Binary previous = Binary.fromConstantByteArray(new byte[0]);

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      this.prefixLengthReader.initFromPage(valueCount, stream);
      this.suffixReader.initFromPage(valueCount, stream);
   }

   public void skip() {
      this.readBytes();
   }

   public Binary readBytes() {
      int prefixLength = this.prefixLengthReader.readInteger();
      Binary suffix = this.suffixReader.readBytes();
      int length = prefixLength + suffix.length();
      if (prefixLength != 0) {
         byte[] out = new byte[length];
         System.arraycopy(this.previous.getBytesUnsafe(), 0, out, 0, prefixLength);
         System.arraycopy(suffix.getBytesUnsafe(), 0, out, prefixLength, suffix.length());
         this.previous = Binary.fromConstantByteArray(out);
      } else {
         this.previous = suffix;
      }

      return this.previous;
   }

   public void setPreviousReader(ValuesReader reader) {
      if (reader instanceof DeltaByteArrayReader) {
         this.previous = ((DeltaByteArrayReader)reader).previous;
      }

   }
}
