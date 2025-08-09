package org.apache.parquet.hadoop;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.compression.CompressionCodecFactory;
import org.apache.parquet.conf.HadoopParquetConfiguration;
import org.apache.parquet.conf.ParquetConfiguration;
import org.apache.parquet.hadoop.codec.ZstandardCodec;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.hadoop.util.ConfigurationUtil;

public class CodecFactory implements CompressionCodecFactory {
   protected static final Map CODEC_BY_NAME = Collections.synchronizedMap(new HashMap());
   private final Map compressors;
   private final Map decompressors;
   protected final ParquetConfiguration conf;
   protected final int pageSize;
   /** @deprecated */
   @Deprecated
   protected final Configuration configuration;
   static final BytesDecompressor NO_OP_DECOMPRESSOR = new BytesDecompressor() {
      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) {
         Preconditions.checkArgument(compressedSize == decompressedSize, "Non-compressed data did not have matching compressed and decompressed sizes.");
         Preconditions.checkArgument(input.remaining() >= compressedSize, "Not enough bytes available in the input buffer");
         int origLimit = input.limit();
         input.limit(input.position() + compressedSize);
         output.put(input);
         input.limit(origLimit);
      }

      public BytesInput decompress(BytesInput bytes, int decompressedSize) {
         return bytes;
      }

      public void release() {
      }
   };
   static final BytesCompressor NO_OP_COMPRESSOR = new BytesCompressor() {
      public BytesInput compress(BytesInput bytes) {
         return bytes;
      }

      public CompressionCodecName getCodecName() {
         return CompressionCodecName.UNCOMPRESSED;
      }

      public void release() {
      }
   };

   public CodecFactory(Configuration configuration, int pageSize) {
      this((ParquetConfiguration)(new HadoopParquetConfiguration(configuration)), pageSize);
   }

   public CodecFactory(ParquetConfiguration configuration, int pageSize) {
      this.compressors = new HashMap();
      this.decompressors = new HashMap();
      if (configuration instanceof HadoopParquetConfiguration) {
         this.configuration = ((HadoopParquetConfiguration)configuration).getConfiguration();
      } else {
         this.configuration = null;
      }

      this.conf = configuration;
      this.pageSize = pageSize;
   }

   public static CodecFactory createDirectCodecFactory(Configuration config, ByteBufferAllocator allocator, int pageSize) {
      return new DirectCodecFactory(config, allocator, pageSize);
   }

   public BytesCompressor getCompressor(CompressionCodecName codecName) {
      BytesCompressor comp = (BytesCompressor)this.compressors.get(codecName);
      if (comp == null) {
         comp = this.createCompressor(codecName);
         this.compressors.put(codecName, comp);
      }

      return comp;
   }

   public BytesDecompressor getDecompressor(CompressionCodecName codecName) {
      BytesDecompressor decomp = (BytesDecompressor)this.decompressors.get(codecName);
      if (decomp == null) {
         decomp = this.createDecompressor(codecName);
         this.decompressors.put(codecName, decomp);
      }

      return decomp;
   }

   protected BytesCompressor createCompressor(CompressionCodecName codecName) {
      CompressionCodec codec = this.getCodec(codecName);
      return (BytesCompressor)(codec == null ? NO_OP_COMPRESSOR : new HeapBytesCompressor(codecName, codec));
   }

   protected BytesDecompressor createDecompressor(CompressionCodecName codecName) {
      CompressionCodec codec = this.getCodec(codecName);
      return (BytesDecompressor)(codec == null ? NO_OP_DECOMPRESSOR : new HeapBytesDecompressor(codec));
   }

   protected CompressionCodec getCodec(CompressionCodecName codecName) {
      String codecClassName = codecName.getHadoopCompressionCodecClassName();
      if (codecClassName == null) {
         return null;
      } else {
         String codecCacheKey = this.cacheKey(codecName);
         CompressionCodec codec = (CompressionCodec)CODEC_BY_NAME.get(codecCacheKey);
         if (codec != null) {
            return codec;
         } else {
            try {
               Class<?> codecClass;
               try {
                  codecClass = Class.forName(codecClassName);
               } catch (ClassNotFoundException var7) {
                  codecClass = (new Configuration(false)).getClassLoader().loadClass(codecClassName);
               }

               codec = (CompressionCodec)ReflectionUtils.newInstance(codecClass, ConfigurationUtil.createHadoopConfiguration(this.conf));
               CODEC_BY_NAME.put(codecCacheKey, codec);
               return codec;
            } catch (ClassNotFoundException e) {
               throw new BadConfigurationException("Class " + codecClassName + " was not found", e);
            }
         }
      }
   }

   private String cacheKey(CompressionCodecName codecName) {
      String level = null;
      switch (codecName) {
         case GZIP:
            level = this.conf.get("zlib.compress.level");
            break;
         case BROTLI:
            level = this.conf.get("compression.brotli.quality");
            break;
         case ZSTD:
            level = this.conf.get("parquet.compression.codec.zstd.level");
      }

      String codecClass = codecName.getHadoopCompressionCodecClassName();
      return level == null ? codecClass : codecClass + ":" + level;
   }

   public void release() {
      for(BytesCompressor compressor : this.compressors.values()) {
         compressor.release();
      }

      this.compressors.clear();

      for(BytesDecompressor decompressor : this.decompressors.values()) {
         decompressor.release();
      }

      this.decompressors.clear();
   }

   class HeapBytesDecompressor extends BytesDecompressor {
      private final CompressionCodec codec;
      private final Decompressor decompressor;

      HeapBytesDecompressor(CompressionCodec codec) {
         this.codec = (CompressionCodec)Objects.requireNonNull(codec);
         this.decompressor = CodecPool.getDecompressor(codec);
      }

      public BytesInput decompress(BytesInput bytes, int decompressedSize) throws IOException {
         if (this.decompressor != null) {
            this.decompressor.reset();
         }

         InputStream is = this.codec.createInputStream(bytes.toInputStream(), this.decompressor);
         BytesInput decompressed;
         if (this.codec instanceof ZstandardCodec) {
            decompressed = BytesInput.copy(BytesInput.from(is, decompressedSize));
            is.close();
         } else {
            decompressed = BytesInput.from(is, decompressedSize);
         }

         return decompressed;
      }

      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) throws IOException {
         Preconditions.checkArgument(input.remaining() >= compressedSize, "Not enough bytes available in the input buffer");
         int origLimit = input.limit();
         int origPosition = input.position();
         input.limit(origPosition + compressedSize);
         ByteBuffer decompressed = this.decompress(BytesInput.from(new ByteBuffer[]{input}), decompressedSize).toByteBuffer();
         output.put(decompressed);
         input.limit(origLimit);
         input.position(origPosition + compressedSize);
      }

      public void release() {
         if (this.decompressor != null) {
            CodecPool.returnDecompressor(this.decompressor);
         }

      }
   }

   class HeapBytesCompressor extends BytesCompressor {
      private final CompressionCodec codec;
      private final Compressor compressor;
      private final ByteArrayOutputStream compressedOutBuffer;
      private final CompressionCodecName codecName;

      HeapBytesCompressor(CompressionCodecName codecName, CompressionCodec codec) {
         this.codecName = codecName;
         this.codec = (CompressionCodec)Objects.requireNonNull(codec);
         this.compressor = CodecPool.getCompressor(codec);
         this.compressedOutBuffer = new ByteArrayOutputStream(CodecFactory.this.pageSize);
      }

      public BytesInput compress(BytesInput bytes) throws IOException {
         this.compressedOutBuffer.reset();
         if (this.compressor != null) {
            this.compressor.reset();
         }

         CompressionOutputStream cos = this.codec.createOutputStream(this.compressedOutBuffer, this.compressor);
         Throwable var3 = null;

         try {
            bytes.writeAllTo(cos);
            cos.finish();
         } catch (Throwable var12) {
            var3 = var12;
            throw var12;
         } finally {
            if (cos != null) {
               if (var3 != null) {
                  try {
                     cos.close();
                  } catch (Throwable var11) {
                     var3.addSuppressed(var11);
                  }
               } else {
                  cos.close();
               }
            }

         }

         return BytesInput.from(this.compressedOutBuffer);
      }

      public void release() {
         if (this.compressor != null) {
            CodecPool.returnCompressor(this.compressor);
         }

      }

      public CompressionCodecName getCodecName() {
         return this.codecName;
      }
   }

   /** @deprecated */
   @Deprecated
   public abstract static class BytesCompressor implements CompressionCodecFactory.BytesInputCompressor {
      public abstract BytesInput compress(BytesInput var1) throws IOException;

      public abstract CompressionCodecName getCodecName();

      public abstract void release();
   }

   /** @deprecated */
   @Deprecated
   public abstract static class BytesDecompressor implements CompressionCodecFactory.BytesInputDecompressor {
      public abstract BytesInput decompress(BytesInput var1, int var2) throws IOException;

      public abstract void decompress(ByteBuffer var1, int var2, ByteBuffer var3, int var4) throws IOException;

      public abstract void release();
   }
}
