package org.apache.parquet.column;

import java.io.IOException;
import org.apache.parquet.bytes.BytesUtils;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.values.ValuesReader;
import org.apache.parquet.column.values.bitpacking.ByteBitPackingValuesReader;
import org.apache.parquet.column.values.bitpacking.Packer;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesReaderForDouble;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesReaderForFLBA;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesReaderForFloat;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesReaderForInteger;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesReaderForLong;
import org.apache.parquet.column.values.delta.DeltaBinaryPackingValuesReader;
import org.apache.parquet.column.values.deltalengthbytearray.DeltaLengthByteArrayValuesReader;
import org.apache.parquet.column.values.deltastrings.DeltaByteArrayReader;
import org.apache.parquet.column.values.dictionary.DictionaryValuesReader;
import org.apache.parquet.column.values.dictionary.PlainValuesDictionary;
import org.apache.parquet.column.values.plain.BinaryPlainValuesReader;
import org.apache.parquet.column.values.plain.BooleanPlainValuesReader;
import org.apache.parquet.column.values.plain.FixedLenByteArrayPlainValuesReader;
import org.apache.parquet.column.values.plain.PlainValuesReader;
import org.apache.parquet.column.values.rle.RunLengthBitPackingHybridValuesReader;
import org.apache.parquet.column.values.rle.ZeroIntegerValuesReader;
import org.apache.parquet.io.ParquetDecodingException;
import org.apache.parquet.schema.PrimitiveType;

public enum Encoding {
   PLAIN {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         switch (descriptor.getType()) {
            case BOOLEAN:
               return new BooleanPlainValuesReader();
            case BINARY:
               return new BinaryPlainValuesReader();
            case FLOAT:
               return new PlainValuesReader.FloatPlainValuesReader();
            case DOUBLE:
               return new PlainValuesReader.DoublePlainValuesReader();
            case INT32:
               return new PlainValuesReader.IntegerPlainValuesReader();
            case INT64:
               return new PlainValuesReader.LongPlainValuesReader();
            case INT96:
               return new FixedLenByteArrayPlainValuesReader(12);
            case FIXED_LEN_BYTE_ARRAY:
               return new FixedLenByteArrayPlainValuesReader(descriptor.getTypeLength());
            default:
               throw new ParquetDecodingException("no plain reader for type " + descriptor.getType());
         }
      }

      public Dictionary initDictionary(ColumnDescriptor descriptor, DictionaryPage dictionaryPage) throws IOException {
         switch (descriptor.getType()) {
            case BINARY:
               return new PlainValuesDictionary.PlainBinaryDictionary(dictionaryPage);
            case FLOAT:
               return new PlainValuesDictionary.PlainFloatDictionary(dictionaryPage);
            case DOUBLE:
               return new PlainValuesDictionary.PlainDoubleDictionary(dictionaryPage);
            case INT32:
               return new PlainValuesDictionary.PlainIntegerDictionary(dictionaryPage);
            case INT64:
               return new PlainValuesDictionary.PlainLongDictionary(dictionaryPage);
            case INT96:
               return new PlainValuesDictionary.PlainBinaryDictionary(dictionaryPage, 12);
            case FIXED_LEN_BYTE_ARRAY:
               return new PlainValuesDictionary.PlainBinaryDictionary(dictionaryPage, descriptor.getTypeLength());
            default:
               throw new ParquetDecodingException("Dictionary encoding not supported for type: " + descriptor.getType());
         }
      }
   },
   RLE {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         int bitWidth = BytesUtils.getWidthFromMaxInt(this.getMaxLevel(descriptor, valuesType));
         return (ValuesReader)(bitWidth == 0 ? new ZeroIntegerValuesReader() : new RunLengthBitPackingHybridValuesReader(bitWidth));
      }
   },
   BYTE_STREAM_SPLIT {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         switch (descriptor.getType()) {
            case FLOAT:
               return new ByteStreamSplitValuesReaderForFloat();
            case DOUBLE:
               return new ByteStreamSplitValuesReaderForDouble();
            case INT32:
               return new ByteStreamSplitValuesReaderForInteger();
            case INT64:
               return new ByteStreamSplitValuesReaderForLong();
            case INT96:
            default:
               throw new ParquetDecodingException("no byte stream split reader for type " + descriptor.getType());
            case FIXED_LEN_BYTE_ARRAY:
               return new ByteStreamSplitValuesReaderForFLBA(descriptor.getTypeLength());
         }
      }
   },
   /** @deprecated */
   @Deprecated
   BIT_PACKED {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         return new ByteBitPackingValuesReader(this.getMaxLevel(descriptor, valuesType), Packer.BIG_ENDIAN);
      }
   },
   /** @deprecated */
   @Deprecated
   PLAIN_DICTIONARY {
      public ValuesReader getDictionaryBasedValuesReader(ColumnDescriptor descriptor, ValuesType valuesType, Dictionary dictionary) {
         return RLE_DICTIONARY.getDictionaryBasedValuesReader(descriptor, valuesType, dictionary);
      }

      public Dictionary initDictionary(ColumnDescriptor descriptor, DictionaryPage dictionaryPage) throws IOException {
         return PLAIN.initDictionary(descriptor, dictionaryPage);
      }

      public boolean usesDictionary() {
         return true;
      }
   },
   DELTA_BINARY_PACKED {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         if (descriptor.getType() != PrimitiveType.PrimitiveTypeName.INT32 && descriptor.getType() != PrimitiveType.PrimitiveTypeName.INT64) {
            throw new ParquetDecodingException("Encoding DELTA_BINARY_PACKED is only supported for type INT32 and INT64");
         } else {
            return new DeltaBinaryPackingValuesReader();
         }
      }
   },
   DELTA_LENGTH_BYTE_ARRAY {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         if (descriptor.getType() != PrimitiveType.PrimitiveTypeName.BINARY) {
            throw new ParquetDecodingException("Encoding DELTA_LENGTH_BYTE_ARRAY is only supported for type BINARY");
         } else {
            return new DeltaLengthByteArrayValuesReader();
         }
      }
   },
   DELTA_BYTE_ARRAY {
      public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
         if (descriptor.getType() != PrimitiveType.PrimitiveTypeName.BINARY && descriptor.getType() != PrimitiveType.PrimitiveTypeName.FIXED_LEN_BYTE_ARRAY) {
            throw new ParquetDecodingException("Encoding DELTA_BYTE_ARRAY is only supported for type BINARY and FIXED_LEN_BYTE_ARRAY");
         } else {
            return new DeltaByteArrayReader();
         }
      }
   },
   RLE_DICTIONARY {
      public ValuesReader getDictionaryBasedValuesReader(ColumnDescriptor descriptor, ValuesType valuesType, Dictionary dictionary) {
         switch (descriptor.getType()) {
            case BINARY:
            case FLOAT:
            case DOUBLE:
            case INT32:
            case INT64:
            case INT96:
            case FIXED_LEN_BYTE_ARRAY:
               return new DictionaryValuesReader(dictionary);
            default:
               throw new ParquetDecodingException("Dictionary encoding not supported for type: " + descriptor.getType());
         }
      }

      public boolean usesDictionary() {
         return true;
      }
   };

   private Encoding() {
   }

   int getMaxLevel(ColumnDescriptor descriptor, ValuesType valuesType) {
      int maxLevel;
      switch (valuesType) {
         case REPETITION_LEVEL:
            maxLevel = descriptor.getMaxRepetitionLevel();
            break;
         case DEFINITION_LEVEL:
            maxLevel = descriptor.getMaxDefinitionLevel();
            break;
         case VALUES:
            if (descriptor.getType() == PrimitiveType.PrimitiveTypeName.BOOLEAN) {
               maxLevel = 1;
               break;
            }

            throw new ParquetDecodingException("Unsupported encoding for values: " + this);
         default:
            throw new ParquetDecodingException("Unsupported encoding for values: " + this);
      }

      return maxLevel;
   }

   public boolean usesDictionary() {
      return false;
   }

   public Dictionary initDictionary(ColumnDescriptor descriptor, DictionaryPage dictionaryPage) throws IOException {
      throw new UnsupportedOperationException(this.name() + " does not support dictionary");
   }

   public ValuesReader getValuesReader(ColumnDescriptor descriptor, ValuesType valuesType) {
      throw new UnsupportedOperationException("Error decoding " + descriptor + ". " + this.name() + " is dictionary based");
   }

   public ValuesReader getDictionaryBasedValuesReader(ColumnDescriptor descriptor, ValuesType valuesType, Dictionary dictionary) {
      throw new UnsupportedOperationException(this.name() + " is not dictionary based");
   }
}
