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
import org.apache.hadoop.io.compress.DirectDecompressionCodec;
import org.apache.hadoop.io.compress.DirectDecompressor;

public class Lz4RawCodec implements Configurable, CompressionCodec, DirectDecompressionCodec {
   private Configuration conf;
   public static final String BUFFER_SIZE_CONFIG = "io.file.buffer.size";
   private static final int DEFAULT_BUFFER_SIZE_CONFIG = 4096;

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public Configuration getConf() {
      return this.conf;
   }

   public Compressor createCompressor() {
      return new Lz4RawCompressor();
   }

   public Decompressor createDecompressor() {
      return new Lz4RawDecompressor();
   }

   public DirectDecompressor createDirectDecompressor() {
      return new Lz4RawDecompressor();
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
      return Lz4RawCompressor.class;
   }

   public Class getDecompressorType() {
      return Lz4RawDecompressor.class;
   }

   public String getDefaultExtension() {
      return ".lz4";
   }
}
