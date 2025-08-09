package org.apache.parquet.hadoop.codec;

import com.github.luben.zstd.BufferPool;
import com.github.luben.zstd.NoPool;
import com.github.luben.zstd.RecyclingBufferPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;

public class ZstandardCodec implements Configurable, CompressionCodec {
   public static final String PARQUET_COMPRESS_ZSTD_BUFFERPOOL_ENABLED = "parquet.compression.codec.zstd.bufferPool.enabled";
   public static final boolean DEFAULT_PARQUET_COMPRESS_ZSTD_BUFFERPOOL_ENABLED = true;
   public static final String PARQUET_COMPRESS_ZSTD_LEVEL = "parquet.compression.codec.zstd.level";
   public static final int DEFAULT_PARQUET_COMPRESS_ZSTD_LEVEL = 3;
   public static final String PARQUET_COMPRESS_ZSTD_WORKERS = "parquet.compression.codec.zstd.workers";
   public static final int DEFAULTPARQUET_COMPRESS_ZSTD_WORKERS = 0;
   private Configuration conf;

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }

   public Compressor createCompressor() {
      return null;
   }

   public Decompressor createDecompressor() {
      return null;
   }

   public CompressionInputStream createInputStream(InputStream stream, Decompressor decompressor) throws IOException {
      return this.createInputStream(stream);
   }

   public CompressionInputStream createInputStream(InputStream stream) throws IOException {
      BufferPool pool;
      if (this.conf.getBoolean("parquet.compression.codec.zstd.bufferPool.enabled", true)) {
         pool = RecyclingBufferPool.INSTANCE;
      } else {
         pool = NoPool.INSTANCE;
      }

      return new ZstdDecompressorStream(stream, pool);
   }

   public CompressionOutputStream createOutputStream(OutputStream stream, Compressor compressor) throws IOException {
      return this.createOutputStream(stream);
   }

   public CompressionOutputStream createOutputStream(OutputStream stream) throws IOException {
      BufferPool pool;
      if (this.conf.getBoolean("parquet.compression.codec.zstd.bufferPool.enabled", true)) {
         pool = RecyclingBufferPool.INSTANCE;
      } else {
         pool = NoPool.INSTANCE;
      }

      return new ZstdCompressorStream(stream, pool, this.conf.getInt("parquet.compression.codec.zstd.level", 3), this.conf.getInt("parquet.compression.codec.zstd.workers", 0));
   }

   public Class getCompressorType() {
      return null;
   }

   public Class getDecompressorType() {
      return null;
   }

   public String getDefaultExtension() {
      return ".zstd";
   }
}
