package io.airlift.compress.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Seekable;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.io.compress.DoNotPool;

public class CodecAdapter implements Configurable, CompressionCodec {
   private final Function streamsFactory;
   private HadoopStreams hadoopStreams;
   private Configuration conf;

   public CodecAdapter(Function streamsFactory) {
      this.streamsFactory = (Function)Objects.requireNonNull(streamsFactory, "streamsFactory is null");
      this.hadoopStreams = (HadoopStreams)streamsFactory.apply(Optional.empty());
   }

   public final Configuration getConf() {
      return this.conf;
   }

   public final void setConf(Configuration conf) {
      this.conf = conf;
      this.hadoopStreams = (HadoopStreams)this.streamsFactory.apply(Optional.of(conf));
   }

   public final CompressionOutputStream createOutputStream(OutputStream out) throws IOException {
      return new CompressionOutputStreamAdapter(this.hadoopStreams.createOutputStream(out));
   }

   public final CompressionOutputStream createOutputStream(OutputStream out, Compressor compressor) throws IOException {
      if (!(compressor instanceof CompressorAdapter)) {
         throw new IllegalArgumentException("Compressor is not the compressor adapter");
      } else {
         return new CompressionOutputStreamAdapter(this.hadoopStreams.createOutputStream(out));
      }
   }

   public final Class getCompressorType() {
      return CompressorAdapter.class;
   }

   public Compressor createCompressor() {
      return new CompressorAdapter();
   }

   public final CompressionInputStream createInputStream(InputStream in) throws IOException {
      return new CompressionInputStreamAdapter(this.hadoopStreams.createInputStream(in), getPositionSupplier(in));
   }

   public final CompressionInputStream createInputStream(InputStream in, Decompressor decompressor) throws IOException {
      if (!(decompressor instanceof DecompressorAdapter)) {
         throw new IllegalArgumentException("Decompressor is not the decompressor adapter");
      } else {
         return new CompressionInputStreamAdapter(this.hadoopStreams.createInputStream(in), getPositionSupplier(in));
      }
   }

   private static CompressionInputStreamAdapter.PositionSupplier getPositionSupplier(InputStream inputStream) {
      if (inputStream instanceof Seekable) {
         Seekable var10000 = (Seekable)inputStream;
         ((Seekable)inputStream).getClass();
         return var10000::getPos;
      } else {
         return () -> 0L;
      }
   }

   public final Class getDecompressorType() {
      return DecompressorAdapter.class;
   }

   public final Decompressor createDecompressor() {
      return new DecompressorAdapter();
   }

   public final String getDefaultExtension() {
      return this.hadoopStreams.getDefaultFileExtension();
   }

   @DoNotPool
   private static class CompressorAdapter implements Compressor {
      private CompressorAdapter() {
      }

      public void setInput(byte[] b, int off, int len) {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public boolean needsInput() {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public void setDictionary(byte[] b, int off, int len) {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public long getBytesRead() {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public long getBytesWritten() {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public void finish() {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public boolean finished() {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public int compress(byte[] b, int off, int len) throws IOException {
         throw new UnsupportedOperationException("Block compressor is not supported");
      }

      public void reset() {
      }

      public void end() {
      }

      public void reinit(Configuration conf) {
      }
   }

   @DoNotPool
   private static class DecompressorAdapter implements Decompressor {
      private DecompressorAdapter() {
      }

      public void setInput(byte[] b, int off, int len) {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public boolean needsInput() {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public void setDictionary(byte[] b, int off, int len) {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public boolean needsDictionary() {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public boolean finished() {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public int decompress(byte[] b, int off, int len) throws IOException {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public int getRemaining() {
         throw new UnsupportedOperationException("Block decompressor is not supported");
      }

      public void reset() {
      }

      public void end() {
      }
   }
}
