package org.apache.parquet.column.values.plain;

import java.io.IOException;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.LittleEndianDataInputStream;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class PlainValuesReader extends ValuesReader {
   private static final Logger LOG = LoggerFactory.getLogger(PlainValuesReader.class);
   protected LittleEndianDataInputStream in;

   public void initFromPage(int valueCount, ByteBufferInputStream stream) throws IOException {
      LOG.debug("init from page at offset {} for length {}", stream.position(), stream.available());
      this.in = new LittleEndianDataInputStream(stream.remainingStream());
   }

   public void skip() {
      this.skip(1);
   }

   void skipBytesFully(int n) throws IOException {
      for(int skipped = 0; skipped < n; skipped += this.in.skipBytes(n - skipped)) {
      }

   }

   public static class DoublePlainValuesReader extends PlainValuesReader {
      public void skip(int n) {
         try {
            this.skipBytesFully(n * 8);
         } catch (IOException e) {
            throw new ParquetDecodingException("could not skip " + n + " double values", e);
         }
      }

      public double readDouble() {
         try {
            return this.in.readDouble();
         } catch (IOException e) {
            throw new ParquetDecodingException("could not read double", e);
         }
      }
   }

   public static class FloatPlainValuesReader extends PlainValuesReader {
      public void skip(int n) {
         try {
            this.skipBytesFully(n * 4);
         } catch (IOException e) {
            throw new ParquetDecodingException("could not skip " + n + " floats", e);
         }
      }

      public float readFloat() {
         try {
            return this.in.readFloat();
         } catch (IOException e) {
            throw new ParquetDecodingException("could not read float", e);
         }
      }
   }

   public static class IntegerPlainValuesReader extends PlainValuesReader {
      public void skip(int n) {
         try {
            this.in.skipBytes(n * 4);
         } catch (IOException e) {
            throw new ParquetDecodingException("could not skip " + n + " ints", e);
         }
      }

      public int readInteger() {
         try {
            return this.in.readInt();
         } catch (IOException e) {
            throw new ParquetDecodingException("could not read int", e);
         }
      }
   }

   public static class LongPlainValuesReader extends PlainValuesReader {
      public void skip(int n) {
         try {
            this.in.skipBytes(n * 8);
         } catch (IOException e) {
            throw new ParquetDecodingException("could not skip " + n + " longs", e);
         }
      }

      public long readLong() {
         try {
            return this.in.readLong();
         } catch (IOException e) {
            throw new ParquetDecodingException("could not read long", e);
         }
      }
   }
}
