package org.apache.parquet.column.values.factory;

import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.ParquetProperties;
import org.apache.parquet.column.values.ValuesWriter;
import org.apache.parquet.column.values.dictionary.DictionaryValuesWriter;
import org.apache.parquet.column.values.fallback.FallbackValuesWriter;

public class DefaultValuesWriterFactory implements ValuesWriterFactory {
   private ValuesWriterFactory delegateFactory;
   private static final ValuesWriterFactory DEFAULT_V1_WRITER_FACTORY = new DefaultV1ValuesWriterFactory();
   private static final ValuesWriterFactory DEFAULT_V2_WRITER_FACTORY = new DefaultV2ValuesWriterFactory();

   public void initialize(ParquetProperties properties) {
      if (properties.getWriterVersion() == ParquetProperties.WriterVersion.PARQUET_1_0) {
         this.delegateFactory = DEFAULT_V1_WRITER_FACTORY;
      } else {
         this.delegateFactory = DEFAULT_V2_WRITER_FACTORY;
      }

      this.delegateFactory.initialize(properties);
   }

   public ValuesWriter newValuesWriter(ColumnDescriptor descriptor) {
      return this.delegateFactory.newValuesWriter(descriptor);
   }

   static DictionaryValuesWriter dictionaryWriter(ColumnDescriptor path, ParquetProperties properties, Encoding dictPageEncoding, Encoding dataPageEncoding) {
      switch (path.getType()) {
         case BOOLEAN:
            throw new IllegalArgumentException("no dictionary encoding for BOOLEAN");
         case BINARY:
            return new DictionaryValuesWriter.PlainBinaryDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case INT32:
            return new DictionaryValuesWriter.PlainIntegerDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case INT64:
            return new DictionaryValuesWriter.PlainLongDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case INT96:
            return new DictionaryValuesWriter.PlainFixedLenArrayDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), 12, dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case DOUBLE:
            return new DictionaryValuesWriter.PlainDoubleDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case FLOAT:
            return new DictionaryValuesWriter.PlainFloatDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         case FIXED_LEN_BYTE_ARRAY:
            return new DictionaryValuesWriter.PlainFixedLenArrayDictionaryValuesWriter(properties.getDictionaryPageSizeThreshold(), path.getTypeLength(), dataPageEncoding, dictPageEncoding, properties.getAllocator());
         default:
            throw new IllegalArgumentException("Unknown type " + path.getType());
      }
   }

   static ValuesWriter dictWriterWithFallBack(ColumnDescriptor path, ParquetProperties parquetProperties, Encoding dictPageEncoding, Encoding dataPageEncoding, ValuesWriter writerToFallBackTo) {
      return (ValuesWriter)(parquetProperties.isDictionaryEnabled(path) ? FallbackValuesWriter.of(dictionaryWriter(path, parquetProperties, dictPageEncoding, dataPageEncoding), writerToFallBackTo) : writerToFallBackTo);
   }
}
