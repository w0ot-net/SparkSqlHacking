package org.apache.parquet.column.values.dictionary;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.Dictionary;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridDecoder;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(DictionaryValuesReader.class);
   private ByteBufferInputStream in;
   private Dictionary dictionary;
   private RunLengthBitPackingHybridDecoder decoder;

   public DictionaryValuesReader(Dictionary dictionary) {
      this.dictionary = dictionary;
   }

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      this.in = stream.remainingStream();
      if (this.in.available() > 0) {
         LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
         int bitWidth = BytesUtils.readIntLittleEndianOnOneByte(this.in);
         LOG.debug("bit width {}", bitWidth);
         this.decoder = new RunLengthBitPackingHybridDecoder(bitWidth, this.in);
      } else {
         this.decoder = new RunLengthBitPackingHybridDecoder(1, this.in) {
            public int readInt() throws IOException {
               throw new IOException("Attempt to read from empty page");
            }
         };
      }

   }

   public int readValueDictionaryId() {
      try {
         return this.decoder.readInt();
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public Binary readBytes() {
      try {
         return this.dictionary.decodeToBinary(this.decoder.readInt());
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public float readFloat() {
      try {
         return this.dictionary.decodeToFloat(this.decoder.readInt());
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public double readDouble() {
      try {
         return this.dictionary.decodeToDouble(this.decoder.readInt());
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public int readInteger() {
      try {
         return this.dictionary.decodeToInt(this.decoder.readInt());
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public long readLong() {
      try {
         return this.dictionary.decodeToLong(this.decoder.readInt());
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }

   public void skip() {
      try {
         this.decoder.readInt();
      } catch (IOException e) {
         throw new ParquetDecodingException(e);
      }
   }
}
