package org.apache.parquet.hadoop.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.CodecFactory;

public class HadoopCodecs {
   public static CompressionCodecFactory newFactory(int sizeHint) {
      return new CodecFactory(new Configuration(), sizeHint);
   }

   public static CompressionCodecFactory newFactory(Configuration conf, int sizeHint) {
      return new CodecFactory(conf, sizeHint);
   }

   public static CompressionCodecFactory newFactory(ParquetConfiguration conf, int sizeHint) {
      return new CodecFactory(conf, sizeHint);
   }

   public static CompressionCodecFactory newDirectFactory(Configuration conf, ByteBufferAllocator allocator, int sizeHint) {
      return CodecFactory.createDirectCodecFactory(conf, allocator, sizeHint);
   }
}
