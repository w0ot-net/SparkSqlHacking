package org.apache.parquet.column.values.dictionary;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferInputStream;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.Dictionary;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.values.plain.PlainValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.io.api.Binary;

public abstract class PlainValuesDictionary extends Dictionary {
   protected PlainValuesDictionary(DictionaryPage dictionaryPage) throws IOException {
      super(dictionaryPage.getEncoding());
      if (dictionaryPage.getEncoding() != Encoding.PLAIN_DICTIONARY && dictionaryPage.getEncoding() != Encoding.PLAIN) {
         throw new ParquetDecodingException("Dictionary data encoding type not supported: " + dictionaryPage.getEncoding());
      }
   }

   public static class PlainBinaryDictionary extends PlainValuesDictionary {
      private Binary[] binaryDictionaryContent;

      public PlainBinaryDictionary(DictionaryPage dictionaryPage) throws IOException {
         this(dictionaryPage, (Integer)null);
      }

      public PlainBinaryDictionary(DictionaryPage dictionaryPage, Integer length) throws IOException {
         super(dictionaryPage);
         this.binaryDictionaryContent = null;
         ByteBuffer dictionaryBytes = dictionaryPage.getBytes().toByteBuffer();
         this.binaryDictionaryContent = new Binary[dictionaryPage.getDictionarySize()];
         int offset = dictionaryBytes.position();
         if (length == null) {
            for(int i = 0; i < this.binaryDictionaryContent.length; ++i) {
               int len = BytesUtils.readIntLittleEndian(dictionaryBytes, offset);
               offset += 4;
               this.binaryDictionaryContent[i] = Binary.fromConstantByteBuffer(dictionaryBytes, offset, len);
               offset += len;
            }
         } else {
            Preconditions.checkArgument(length > 0, "Invalid byte array length: %s", length);

            for(int i = 0; i < this.binaryDictionaryContent.length; ++i) {
               this.binaryDictionaryContent[i] = Binary.fromConstantByteBuffer(dictionaryBytes, offset, length);
               offset += length;
            }
         }

      }

      public Binary decodeToBinary(int id) {
         return this.binaryDictionaryContent[id];
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("PlainBinaryDictionary {\n");

         for(int i = 0; i < this.binaryDictionaryContent.length; ++i) {
            sb.append(i).append(" => ").append(this.binaryDictionaryContent[i]).append("\n");
         }

         return sb.append("}").toString();
      }

      public int getMaxId() {
         return this.binaryDictionaryContent.length - 1;
      }
   }

   public static class PlainLongDictionary extends PlainValuesDictionary {
      private long[] longDictionaryContent = null;

      public PlainLongDictionary(DictionaryPage dictionaryPage) throws IOException {
         super(dictionaryPage);
         ByteBufferInputStream in = dictionaryPage.getBytes().toInputStream();
         this.longDictionaryContent = new long[dictionaryPage.getDictionarySize()];
         PlainValuesReader.LongPlainValuesReader longReader = new PlainValuesReader.LongPlainValuesReader();
         longReader.initFromPage(dictionaryPage.getDictionarySize(), in);

         for(int i = 0; i < this.longDictionaryContent.length; ++i) {
            this.longDictionaryContent[i] = longReader.readLong();
         }

      }

      public long decodeToLong(int id) {
         return this.longDictionaryContent[id];
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("PlainLongDictionary {\n");

         for(int i = 0; i < this.longDictionaryContent.length; ++i) {
            sb.append(i).append(" => ").append(this.longDictionaryContent[i]).append("\n");
         }

         return sb.append("}").toString();
      }

      public int getMaxId() {
         return this.longDictionaryContent.length - 1;
      }
   }

   public static class PlainDoubleDictionary extends PlainValuesDictionary {
      private double[] doubleDictionaryContent = null;

      public PlainDoubleDictionary(DictionaryPage dictionaryPage) throws IOException {
         super(dictionaryPage);
         ByteBufferInputStream in = dictionaryPage.getBytes().toInputStream();
         this.doubleDictionaryContent = new double[dictionaryPage.getDictionarySize()];
         PlainValuesReader.DoublePlainValuesReader doubleReader = new PlainValuesReader.DoublePlainValuesReader();
         doubleReader.initFromPage(dictionaryPage.getDictionarySize(), in);

         for(int i = 0; i < this.doubleDictionaryContent.length; ++i) {
            this.doubleDictionaryContent[i] = doubleReader.readDouble();
         }

      }

      public double decodeToDouble(int id) {
         return this.doubleDictionaryContent[id];
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("PlainDoubleDictionary {\n");

         for(int i = 0; i < this.doubleDictionaryContent.length; ++i) {
            sb.append(i).append(" => ").append(this.doubleDictionaryContent[i]).append("\n");
         }

         return sb.append("}").toString();
      }

      public int getMaxId() {
         return this.doubleDictionaryContent.length - 1;
      }
   }

   public static class PlainIntegerDictionary extends PlainValuesDictionary {
      private int[] intDictionaryContent = null;

      public PlainIntegerDictionary(DictionaryPage dictionaryPage) throws IOException {
         super(dictionaryPage);
         ByteBufferInputStream in = dictionaryPage.getBytes().toInputStream();
         this.intDictionaryContent = new int[dictionaryPage.getDictionarySize()];
         PlainValuesReader.IntegerPlainValuesReader intReader = new PlainValuesReader.IntegerPlainValuesReader();
         intReader.initFromPage(dictionaryPage.getDictionarySize(), in);

         for(int i = 0; i < this.intDictionaryContent.length; ++i) {
            this.intDictionaryContent[i] = intReader.readInteger();
         }

      }

      public int decodeToInt(int id) {
         return this.intDictionaryContent[id];
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("PlainIntegerDictionary {\n");

         for(int i = 0; i < this.intDictionaryContent.length; ++i) {
            sb.append(i).append(" => ").append(this.intDictionaryContent[i]).append("\n");
         }

         return sb.append("}").toString();
      }

      public int getMaxId() {
         return this.intDictionaryContent.length - 1;
      }
   }

   public static class PlainFloatDictionary extends PlainValuesDictionary {
      private float[] floatDictionaryContent = null;

      public PlainFloatDictionary(DictionaryPage dictionaryPage) throws IOException {
         super(dictionaryPage);
         ByteBufferInputStream in = dictionaryPage.getBytes().toInputStream();
         this.floatDictionaryContent = new float[dictionaryPage.getDictionarySize()];
         PlainValuesReader.FloatPlainValuesReader floatReader = new PlainValuesReader.FloatPlainValuesReader();
         floatReader.initFromPage(dictionaryPage.getDictionarySize(), in);

         for(int i = 0; i < this.floatDictionaryContent.length; ++i) {
            this.floatDictionaryContent[i] = floatReader.readFloat();
         }

      }

      public float decodeToFloat(int id) {
         return this.floatDictionaryContent[id];
      }

      public String toString() {
         StringBuilder sb = new StringBuilder("PlainFloatDictionary {\n");

         for(int i = 0; i < this.floatDictionaryContent.length; ++i) {
            sb.append(i).append(" => ").append(this.floatDictionaryContent[i]).append("\n");
         }

         return sb.append("}").toString();
      }

      public int getMaxId() {
         return this.floatDictionaryContent.length - 1;
      }
   }
}
