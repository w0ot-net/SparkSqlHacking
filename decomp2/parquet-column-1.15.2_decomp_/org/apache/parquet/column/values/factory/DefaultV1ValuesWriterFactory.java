package org.apache.parquet.column.values.factory;

import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.bytestreamsplit.ByteStreamSplitValuesWriter;
import org.apache.parquet.column.values.plain.BooleanPlainValuesWriter;
import org.apache.parquet.column.values.plain.FixedLenByteArrayPlainValuesWriter;
import org.apache.parquet.column.values.plain.PlainValuesWriter;

public class DefaultV1ValuesWriterFactory implements ValuesWriterFactory {
   private ParquetProperties parquetProperties;

   public void initialize(ParquetProperties properties) {
      this.parquetProperties = properties;
   }

   private Encoding getEncodingForDataPage() {
      return Encoding.PLAIN_DICTIONARY;
   }

   private Encoding getEncodingForDictionaryPage() {
      return Encoding.PLAIN_DICTIONARY;
   }

   public ValuesWriter newValuesWriter(ColumnDescriptor descriptor) {
      switch (descriptor.getType()) {
         case BOOLEAN:
            return this.getBooleanValuesWriter();
         case FIXED_LEN_BYTE_ARRAY:
            return this.getFixedLenByteArrayValuesWriter(descriptor);
         case BINARY:
            return this.getBinaryValuesWriter(descriptor);
         case INT32:
            return this.getInt32ValuesWriter(descriptor);
         case INT64:
            return this.getInt64ValuesWriter(descriptor);
         case INT96:
            return this.getInt96ValuesWriter(descriptor);
         case DOUBLE:
            return this.getDoubleValuesWriter(descriptor);
         case FLOAT:
            return this.getFloatValuesWriter(descriptor);
         default:
            throw new IllegalArgumentException("Unknown type " + descriptor.getType());
      }
   }

   private ValuesWriter getBooleanValuesWriter() {
      return new BooleanPlainValuesWriter();
   }

   private ValuesWriter getFixedLenByteArrayValuesWriter(ColumnDescriptor path) {
      return (ValuesWriter)(this.parquetProperties.isByteStreamSplitEnabled(path) ? new ByteStreamSplitValuesWriter.FixedLenByteArrayByteStreamSplitValuesWriter(path.getPrimitiveType().getTypeLength(), this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator()) : new FixedLenByteArrayPlainValuesWriter(path.getPrimitiveType().getTypeLength(), this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator()));
   }

   private ValuesWriter getBinaryValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter = new PlainValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }

   private ValuesWriter getInt32ValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter;
      if (this.parquetProperties.isByteStreamSplitEnabled(path)) {
         fallbackWriter = new ByteStreamSplitValuesWriter.IntegerByteStreamSplitValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      } else {
         fallbackWriter = new PlainValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      }

      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }

   private ValuesWriter getInt64ValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter;
      if (this.parquetProperties.isByteStreamSplitEnabled(path)) {
         fallbackWriter = new ByteStreamSplitValuesWriter.LongByteStreamSplitValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      } else {
         fallbackWriter = new PlainValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      }

      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }

   private ValuesWriter getInt96ValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter = new FixedLenByteArrayPlainValuesWriter(12, this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }

   private ValuesWriter getDoubleValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter;
      if (this.parquetProperties.isByteStreamSplitEnabled(path)) {
         fallbackWriter = new ByteStreamSplitValuesWriter.DoubleByteStreamSplitValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      } else {
         fallbackWriter = new PlainValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      }

      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }

   private ValuesWriter getFloatValuesWriter(ColumnDescriptor path) {
      ValuesWriter fallbackWriter;
      if (this.parquetProperties.isByteStreamSplitEnabled(path)) {
         fallbackWriter = new ByteStreamSplitValuesWriter.FloatByteStreamSplitValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      } else {
         fallbackWriter = new PlainValuesWriter(this.parquetProperties.getInitialSlabSize(), this.parquetProperties.getPageSizeThreshold(), this.parquetProperties.getAllocator());
      }

      return DefaultValuesWriterFactory.dictWriterWithFallBack(path, this.parquetProperties, this.getEncodingForDictionaryPage(), this.getEncodingForDataPage(), fallbackWriter);
   }
}
