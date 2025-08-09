package org.apache.parquet.hadoop;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdCompressCtx;
import com.github.luben.zstd.ZstdDecompressCtx;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.Decompressor;
import org.apache.parquet.ParquetRuntimeException;
import org.apache.parquet.Preconditions;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.bytes.ReusingByteBufferAllocator;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.apache.parquet.util.AutoCloseables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xerial.snappy.Snappy;

class DirectCodecFactory extends CodecFactory implements AutoCloseable {
   private static final Logger LOG = LoggerFactory.getLogger(DirectCodecFactory.class);
   private final ByteBufferAllocator allocator;
   private static final Class DIRECT_DECOMPRESSION_CODEC_CLASS;
   private static final Method DECOMPRESS_METHOD;
   private static final Method CREATE_DIRECT_DECOMPRESSOR_METHOD;

   DirectCodecFactory(Configuration config, ByteBufferAllocator allocator, int pageSize) {
      super(config, pageSize);
      this.allocator = (ByteBufferAllocator)Objects.requireNonNull(allocator, "allocator cannot be null");
      Preconditions.checkState(allocator.isDirect(), "A %s requires a direct buffer allocator be provided.", this.getClass().getSimpleName());
   }

   protected CodecFactory.BytesCompressor createCompressor(CompressionCodecName codecName) {
      switch (codecName) {
         case SNAPPY:
            return new SnappyCompressor();
         case ZSTD:
            return new ZstdCompressor();
         default:
            return super.createCompressor(codecName);
      }
   }

   protected CodecFactory.BytesDecompressor createDecompressor(CompressionCodecName codecName) {
      switch (codecName) {
         case SNAPPY:
            return new SnappyDecompressor();
         case ZSTD:
            return new ZstdDecompressor();
         default:
            CompressionCodec codec = this.getCodec(codecName);
            if (codec == null) {
               return NO_OP_DECOMPRESSOR;
            } else {
               DirectCodecPool.CodecPool pool = DirectCodecFactory.DirectCodecPool.INSTANCE.codec(codec);
               return (CodecFactory.BytesDecompressor)(pool.supportsDirectDecompression() ? new FullDirectDecompressor(pool.borrowDirectDecompressor()) : new IndirectDecompressor(pool.borrowDecompressor()));
            }
      }
   }

   public void close() {
      this.release();
   }

   static {
      Class<?> tempClass = null;
      Method tempCreateMethod = null;
      Method tempDecompressMethod = null;

      try {
         tempClass = Class.forName("org.apache.hadoop.io.compress.DirectDecompressionCodec");
         tempCreateMethod = tempClass.getMethod("createDirectDecompressor");
         Class<?> tempClass2 = Class.forName("org.apache.hadoop.io.compress.DirectDecompressor");
         tempDecompressMethod = tempClass2.getMethod("decompress", ByteBuffer.class, ByteBuffer.class);
      } catch (NoSuchMethodException | ClassNotFoundException var4) {
      }

      DIRECT_DECOMPRESSION_CODEC_CLASS = tempClass;
      CREATE_DIRECT_DECOMPRESSOR_METHOD = tempCreateMethod;
      DECOMPRESS_METHOD = tempDecompressMethod;
   }

   public class IndirectDecompressor extends CodecFactory.BytesDecompressor {
      private final Decompressor decompressor;

      public IndirectDecompressor(CompressionCodec codec) {
         this((Decompressor)DirectCodecFactory.DirectCodecPool.INSTANCE.codec(codec).borrowDecompressor());
      }

      private IndirectDecompressor(Decompressor decompressor) {
         this.decompressor = decompressor;
      }

      public BytesInput decompress(BytesInput bytes, int decompressedSize) throws IOException {
         this.decompressor.reset();
         byte[] inputBytes = bytes.toByteArray();
         this.decompressor.setInput(inputBytes, 0, inputBytes.length);
         byte[] output = new byte[decompressedSize];
         this.decompressor.decompress(output, 0, decompressedSize);
         return BytesInput.from(output);
      }

      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) throws IOException {
         this.decompressor.reset();
         byte[] inputBytes = new byte[compressedSize];
         input.get(inputBytes);
         this.decompressor.setInput(inputBytes, 0, inputBytes.length);
         byte[] outputBytes = new byte[decompressedSize];
         this.decompressor.decompress(outputBytes, 0, decompressedSize);
         output.put(outputBytes);
      }

      public void release() {
         DirectCodecFactory.DirectCodecPool.INSTANCE.returnDecompressor(this.decompressor);
      }
   }

   private abstract class BaseDecompressor extends CodecFactory.BytesDecompressor {
      private final ReusingByteBufferAllocator inputAllocator;
      private final ReusingByteBufferAllocator outputAllocator;

      BaseDecompressor() {
         this.inputAllocator = ReusingByteBufferAllocator.strict(DirectCodecFactory.this.allocator);
         this.outputAllocator = ReusingByteBufferAllocator.unsafe(DirectCodecFactory.this.allocator);
      }

      public BytesInput decompress(BytesInput bytes, int decompressedSize) throws IOException {
         ByteBufferReleaser releaser = this.inputAllocator.getReleaser();
         Throwable var4 = null;

         BytesInput var8;
         try {
            ByteBuffer input = bytes.toByteBuffer(releaser);
            ByteBuffer output = this.outputAllocator.allocate(decompressedSize);
            int size = this.decompress(input.slice(), output.slice());
            if (size != decompressedSize) {
               throw new DirectCodecPool.ParquetCompressionCodecException("Unexpected decompressed size: " + size + " != " + decompressedSize);
            }

            output.limit(size);
            var8 = BytesInput.from(new ByteBuffer[]{output});
         } catch (Throwable var17) {
            var4 = var17;
            throw var17;
         } finally {
            if (releaser != null) {
               if (var4 != null) {
                  try {
                     releaser.close();
                  } catch (Throwable var16) {
                     var4.addSuppressed(var16);
                  }
               } else {
                  releaser.close();
               }
            }

         }

         return var8;
      }

      abstract int decompress(ByteBuffer var1, ByteBuffer var2) throws IOException;

      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) throws IOException {
         int origInputLimit = input.limit();
         input.limit(input.position() + compressedSize);
         int origOutputLimit = output.limit();
         output.limit(output.position() + decompressedSize);
         int size = this.decompress(input.slice(), output.slice());
         if (size != decompressedSize) {
            throw new DirectCodecPool.ParquetCompressionCodecException("Unexpected decompressed size: " + size + " != " + decompressedSize);
         } else {
            input.position(input.limit());
            input.limit(origInputLimit);
            output.position(output.limit());
            output.limit(origOutputLimit);
         }
      }

      public void release() {
         AutoCloseables.uncheckedClose(new AutoCloseable[]{this.outputAllocator, this.inputAllocator, this::closeDecompressor});
      }

      abstract void closeDecompressor();
   }

   private abstract class BaseCompressor extends CodecFactory.BytesCompressor {
      private final ReusingByteBufferAllocator inputAllocator;
      private final ReusingByteBufferAllocator outputAllocator;

      BaseCompressor() {
         this.inputAllocator = ReusingByteBufferAllocator.strict(DirectCodecFactory.this.allocator);
         this.outputAllocator = ReusingByteBufferAllocator.unsafe(DirectCodecFactory.this.allocator);
      }

      public BytesInput compress(BytesInput bytes) throws IOException {
         ByteBufferReleaser releaser = this.inputAllocator.getReleaser();
         Throwable var3 = null;

         BytesInput var7;
         try {
            ByteBuffer input = bytes.toByteBuffer(releaser);
            ByteBuffer output = this.outputAllocator.allocate(this.maxCompressedSize(Math.toIntExact(bytes.size())));
            int size = this.compress(input.slice(), output.slice());
            output.limit(size);
            var7 = BytesInput.from(new ByteBuffer[]{output});
         } catch (Throwable var16) {
            var3 = var16;
            throw var16;
         } finally {
            if (releaser != null) {
               if (var3 != null) {
                  try {
                     releaser.close();
                  } catch (Throwable var15) {
                     var3.addSuppressed(var15);
                  }
               } else {
                  releaser.close();
               }
            }

         }

         return var7;
      }

      abstract int maxCompressedSize(int var1);

      abstract int compress(ByteBuffer var1, ByteBuffer var2) throws IOException;

      public void release() {
         AutoCloseables.uncheckedClose(new AutoCloseable[]{this.outputAllocator, this.inputAllocator, this::closeCompressor});
      }

      abstract void closeCompressor();
   }

   public class FullDirectDecompressor extends BaseDecompressor {
      private final Object decompressor;

      public FullDirectDecompressor(CompressionCodecName codecName) {
         this((Object)DirectCodecFactory.DirectCodecPool.INSTANCE.codec((CompressionCodec)Objects.requireNonNull(DirectCodecFactory.this.getCodec(codecName))).borrowDirectDecompressor());
      }

      private FullDirectDecompressor(Object decompressor) {
         this.decompressor = decompressor;
      }

      public BytesInput decompress(BytesInput compressedBytes, int decompressedSize) throws IOException {
         if (this.decompressor instanceof Decompressor) {
            ((Decompressor)this.decompressor).reset();
         }

         return super.decompress(compressedBytes, decompressedSize);
      }

      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) throws IOException {
         if (this.decompressor instanceof Decompressor) {
            ((Decompressor)this.decompressor).reset();
         }

         super.decompress(input, compressedSize, output, decompressedSize);
      }

      int decompress(ByteBuffer input, ByteBuffer output) {
         int startPos = output.position();

         try {
            DirectCodecFactory.DECOMPRESS_METHOD.invoke(this.decompressor, input, output);
         } catch (InvocationTargetException | IllegalAccessException e) {
            throw new DirectCodecPool.ParquetCompressionCodecException(e);
         }

         int size = output.position() - startPos;
         return size == 0 ? output.limit() : size;
      }

      void closeDecompressor() {
         DirectCodecFactory.DirectCodecPool.INSTANCE.returnDirectDecompressor(this.decompressor);
      }
   }

   /** @deprecated */
   @Deprecated
   public class NoopDecompressor extends CodecFactory.BytesDecompressor {
      public void decompress(ByteBuffer input, int compressedSize, ByteBuffer output, int decompressedSize) throws IOException {
         CodecFactory.NO_OP_DECOMPRESSOR.decompress(input, compressedSize, output, decompressedSize);
      }

      public BytesInput decompress(BytesInput bytes, int decompressedSize) throws IOException {
         return CodecFactory.NO_OP_DECOMPRESSOR.decompress(bytes, decompressedSize);
      }

      public void release() {
         CodecFactory.NO_OP_DECOMPRESSOR.release();
      }
   }

   public class SnappyDecompressor extends BaseDecompressor {
      int decompress(ByteBuffer input, ByteBuffer output) throws IOException {
         return Snappy.uncompress(input, output);
      }

      void closeDecompressor() {
      }
   }

   public class SnappyCompressor extends BaseCompressor {
      int compress(ByteBuffer input, ByteBuffer output) throws IOException {
         return Snappy.compress(input, output);
      }

      int maxCompressedSize(int size) {
         return Snappy.maxCompressedLength(size);
      }

      public CompressionCodecName getCodecName() {
         return CompressionCodecName.SNAPPY;
      }

      void closeCompressor() {
      }
   }

   private class ZstdDecompressor extends BaseDecompressor {
      private final ZstdDecompressCtx context = new ZstdDecompressCtx();

      ZstdDecompressor() {
      }

      int decompress(ByteBuffer input, ByteBuffer output) {
         return this.context.decompress(output, input);
      }

      void closeDecompressor() {
         this.context.close();
      }
   }

   private class ZstdCompressor extends BaseCompressor {
      private final ZstdCompressCtx context = new ZstdCompressCtx();

      ZstdCompressor() {
         this.context.setLevel(DirectCodecFactory.this.conf.getInt("parquet.compression.codec.zstd.level", 3));
         this.context.setWorkers(DirectCodecFactory.this.conf.getInt("parquet.compression.codec.zstd.workers", 0));
      }

      public CompressionCodecName getCodecName() {
         return CompressionCodecName.ZSTD;
      }

      int maxCompressedSize(int size) {
         return Math.toIntExact(Zstd.compressBound((long)size));
      }

      int compress(ByteBuffer input, ByteBuffer output) {
         return this.context.compress(output, input);
      }

      void closeCompressor() {
         this.context.close();
      }
   }

   /** @deprecated */
   @Deprecated
   public static class NoopCompressor extends CodecFactory.BytesCompressor {
      public BytesInput compress(BytesInput bytes) throws IOException {
         return CodecFactory.NO_OP_COMPRESSOR.compress(bytes);
      }

      public CompressionCodecName getCodecName() {
         return CodecFactory.NO_OP_COMPRESSOR.getCodecName();
      }

      public void release() {
         CodecFactory.NO_OP_COMPRESSOR.release();
      }
   }

   static class DirectCodecPool {
      public static final DirectCodecPool INSTANCE = new DirectCodecPool();
      private final Map codecs = Collections.synchronizedMap(new HashMap());
      private final Map directDePools = Collections.synchronizedMap(new HashMap());
      private final Map dePools = Collections.synchronizedMap(new HashMap());
      private final Map cPools = Collections.synchronizedMap(new HashMap());

      private DirectCodecPool() {
      }

      public CodecPool codec(CompressionCodec codec) {
         CodecPool pools = (CodecPool)this.codecs.get(codec);
         if (pools == null) {
            synchronized(this) {
               pools = (CodecPool)this.codecs.get(codec);
               if (pools == null) {
                  pools = new CodecPool(codec);
                  this.codecs.put(codec, pools);
               }
            }
         }

         return pools;
      }

      private void returnToPool(Object obj, Map pools) {
         try {
            GenericObjectPool pool = (GenericObjectPool)pools.get(obj.getClass());
            if (pool == null) {
               throw new IllegalStateException("Received unexpected compressor or decompressor, cannot be returned to any available pool: " + obj.getClass().getSimpleName());
            } else {
               pool.returnObject(obj);
            }
         } catch (Exception e) {
            throw new ParquetCompressionCodecException(e);
         }
      }

      public Object borrow(GenericObjectPool pool) {
         try {
            return pool.borrowObject();
         } catch (Exception e) {
            throw new ParquetCompressionCodecException(e);
         }
      }

      public void returnCompressor(Compressor compressor) {
         this.returnToPool(compressor, this.cPools);
      }

      public void returnDecompressor(Decompressor decompressor) {
         this.returnToPool(decompressor, this.dePools);
      }

      public void returnDirectDecompressor(Object decompressor) {
         this.returnToPool(decompressor, this.directDePools);
      }

      public class CodecPool {
         private final GenericObjectPool compressorPool;
         private final GenericObjectPool decompressorPool;
         private final GenericObjectPool directDecompressorPool;
         private final boolean supportDirectDecompressor;
         private static final String BYTE_BUF_IMPL_NOT_FOUND_MSG = "Unable to find ByteBuffer based %s for codec %s, will use a byte array based implementation instead.";

         private CodecPool(final CompressionCodec codec) {
            try {
               boolean supportDirectDecompressor = DirectCodecFactory.DIRECT_DECOMPRESSION_CODEC_CLASS != null && DirectCodecFactory.DIRECT_DECOMPRESSION_CODEC_CLASS.isAssignableFrom(codec.getClass());
               this.compressorPool = new GenericObjectPool(new BasePoolableObjectFactory() {
                  public Object makeObject() throws Exception {
                     return codec.createCompressor();
                  }
               }, Integer.MAX_VALUE);
               Object com = this.compressorPool.borrowObject();
               if (com != null) {
                  DirectCodecPool.this.cPools.put(com.getClass(), this.compressorPool);
                  this.compressorPool.returnObject(com);
               } else if (DirectCodecFactory.LOG.isDebugEnabled()) {
                  DirectCodecFactory.LOG.debug(String.format("Unable to find ByteBuffer based %s for codec %s, will use a byte array based implementation instead.", "compressor", codec.getClass().getName()));
               }

               this.decompressorPool = new GenericObjectPool(new BasePoolableObjectFactory() {
                  public Object makeObject() throws Exception {
                     return codec.createDecompressor();
                  }
               }, Integer.MAX_VALUE);
               Object decom = this.decompressorPool.borrowObject();
               if (decom != null) {
                  DirectCodecPool.this.dePools.put(decom.getClass(), this.decompressorPool);
                  this.decompressorPool.returnObject(decom);
               } else if (DirectCodecFactory.LOG.isDebugEnabled()) {
                  DirectCodecFactory.LOG.debug(String.format("Unable to find ByteBuffer based %s for codec %s, will use a byte array based implementation instead.", "decompressor", codec.getClass().getName()));
               }

               if (supportDirectDecompressor) {
                  this.directDecompressorPool = new GenericObjectPool(new BasePoolableObjectFactory() {
                     public Object makeObject() throws Exception {
                        return DirectCodecFactory.CREATE_DIRECT_DECOMPRESSOR_METHOD.invoke(codec);
                     }
                  }, Integer.MAX_VALUE);
                  Object ddecom = this.directDecompressorPool.borrowObject();
                  if (ddecom != null) {
                     DirectCodecPool.this.directDePools.put(ddecom.getClass(), this.directDecompressorPool);
                     this.directDecompressorPool.returnObject(ddecom);
                  } else {
                     supportDirectDecompressor = false;
                     if (DirectCodecFactory.LOG.isDebugEnabled()) {
                        DirectCodecFactory.LOG.debug(String.format("Unable to find ByteBuffer based %s for codec %s, will use a byte array based implementation instead.", "compressor", codec.getClass().getName()));
                     }
                  }
               } else {
                  this.directDecompressorPool = null;
               }

               this.supportDirectDecompressor = supportDirectDecompressor;
            } catch (Exception e) {
               throw new ParquetCompressionCodecException("Error creating compression codec pool.", e);
            }
         }

         public Object borrowDirectDecompressor() {
            Preconditions.checkArgument(this.supportDirectDecompressor, "Tried to get a direct Decompressor from a non-direct codec.");

            try {
               return this.directDecompressorPool.borrowObject();
            } catch (Exception e) {
               throw new ParquetCompressionCodecException(e);
            }
         }

         public boolean supportsDirectDecompression() {
            return this.supportDirectDecompressor;
         }

         public Decompressor borrowDecompressor() {
            return (Decompressor)DirectCodecPool.this.borrow(this.decompressorPool);
         }

         public Compressor borrowCompressor() {
            return (Compressor)DirectCodecPool.this.borrow(this.compressorPool);
         }
      }

      public static class ParquetCompressionCodecException extends ParquetRuntimeException {
         public ParquetCompressionCodecException() {
         }

         public ParquetCompressionCodecException(String message, Throwable cause) {
            super(message, cause);
         }

         public ParquetCompressionCodecException(String message) {
            super(message);
         }

         public ParquetCompressionCodecException(Throwable cause) {
            super(cause);
         }
      }
   }
}
