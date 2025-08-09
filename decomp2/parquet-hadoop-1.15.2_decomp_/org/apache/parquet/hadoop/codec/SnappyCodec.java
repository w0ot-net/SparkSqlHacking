package org.apache.parquet.hadoop.codec;

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

public class SnappyCodec implements Configurable, CompressionCodec {
   private Configuration conf;
   private final String BUFFER_SIZE_CONFIG = "io.file.buffer.size";

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }

   public Compressor createCompressor() {
      return new SnappyCompressor();
   }

   public Decompressor createDecompressor() {
      return new SnappyDecompressor();
   }

   public CompressionInputStream createInputStream(InputStream stream) throws IOException {
      return this.createInputStream(stream, this.createDecompressor());
   }

   public CompressionInputStream createInputStream(InputStream stream, Decompressor decompressor) throws IOException {
      return new NonBlockedDecompressorStream(stream, decompressor, this.conf.getInt("io.file.buffer.size", 4096));
   }

   public CompressionOutputStream createOutputStream(OutputStream stream) throws IOException {
      return this.createOutputStream(stream, this.createCompressor());
   }

   public CompressionOutputStream createOutputStream(OutputStream stream, Compressor compressor) throws IOException {
      return new NonBlockedCompressorStream(stream, compressor, this.conf.getInt("io.file.buffer.size", 4096));
   }

   public Class getCompressorType() {
      return SnappyCompressor.class;
   }

   public Class getDecompressorType() {
      return SnappyDecompressor.class;
   }

   public String getDefaultExtension() {
      return ".snappy";
   }
}
