package org.apache.arrow.vector.compression;

import java.util.EnumMap;
import java.util.Map;
import java.util.ServiceLoader;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;

public interface CompressionCodec {
   ArrowBuf compress(BufferAllocator var1, ArrowBuf var2);

   ArrowBuf decompress(BufferAllocator var1, ArrowBuf var2);

   CompressionUtil.CodecType getCodecType();

   public interface Factory {
      Factory INSTANCE = bestEffort();

      CompressionCodec createCodec(CompressionUtil.CodecType var1);

      CompressionCodec createCodec(CompressionUtil.CodecType var1, int var2);

      private static Factory bestEffort() {
         ServiceLoader<Factory> serviceLoader = ServiceLoader.load(Factory.class);
         final Map<CompressionUtil.CodecType, Factory> factories = new EnumMap(CompressionUtil.CodecType.class);

         for(Factory factory : serviceLoader) {
            for(CompressionUtil.CodecType codecType : CompressionUtil.CodecType.values()) {
               try {
                  factory.createCodec(codecType);
                  factories.putIfAbsent(codecType, factory);
               } catch (Throwable var9) {
               }
            }
         }

         final Factory fallback = NoCompressionCodec.Factory.INSTANCE;
         return new Factory() {
            public CompressionCodec createCodec(CompressionUtil.CodecType codecType) {
               return ((Factory)factories.getOrDefault(codecType, fallback)).createCodec(codecType);
            }

            public CompressionCodec createCodec(CompressionUtil.CodecType codecType, int compressionLevel) {
               return ((Factory)factories.getOrDefault(codecType, fallback)).createCodec(codecType, compressionLevel);
            }
         };
      }
   }
}
