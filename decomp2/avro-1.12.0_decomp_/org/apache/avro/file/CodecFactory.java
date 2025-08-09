package org.apache.avro.file;

import java.util.HashMap;
import java.util.Map;
import org.apache.avro.AvroRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CodecFactory {
   private static final Logger LOG = LoggerFactory.getLogger(CodecFactory.class);
   private static final Map REGISTERED = new HashMap();
   public static final int DEFAULT_DEFLATE_LEVEL = -1;
   public static final int DEFAULT_XZ_LEVEL = 6;
   public static final int DEFAULT_ZSTANDARD_LEVEL = 3;
   public static final boolean DEFAULT_ZSTANDARD_BUFFERPOOL = false;

   public static CodecFactory nullCodec() {
      return NullCodec.OPTION;
   }

   public static CodecFactory deflateCodec(int compressionLevel) {
      return new DeflateCodec.Option(compressionLevel);
   }

   public static CodecFactory xzCodec(int compressionLevel) {
      return new XZCodec.Option(compressionLevel);
   }

   public static CodecFactory snappyCodec() {
      try {
         return new SnappyCodec.Option();
      } catch (Throwable t) {
         LOG.debug("Snappy was not available", t);
         return null;
      }
   }

   public static CodecFactory bzip2Codec() {
      return new BZip2Codec.Option();
   }

   public static CodecFactory zstandardCodec(int level) {
      return new ZstandardCodec.Option(level, false, false);
   }

   public static CodecFactory zstandardCodec(int level, boolean useChecksum) {
      return new ZstandardCodec.Option(level, useChecksum, false);
   }

   public static CodecFactory zstandardCodec(int level, boolean useChecksum, boolean useBufferPool) {
      return new ZstandardCodec.Option(level, useChecksum, useBufferPool);
   }

   protected abstract Codec createInstance();

   public static CodecFactory fromString(String s) {
      CodecFactory o = (CodecFactory)REGISTERED.get(s);
      if (o == null) {
         throw new AvroRuntimeException("Unrecognized codec: " + s);
      } else {
         return o;
      }
   }

   public static CodecFactory addCodec(String name, CodecFactory c) {
      return c != null ? (CodecFactory)REGISTERED.put(name, c) : null;
   }

   public String toString() {
      Codec instance = this.createInstance();
      return instance.toString();
   }

   static {
      addCodec("null", nullCodec());
      addCodec("deflate", deflateCodec(-1));
      addCodec("bzip2", bzip2Codec());
      addCodec("xz", xzCodec(6));
      addCodec("zstandard", zstandardCodec(3, false));
      addCodec("snappy", snappyCodec());
   }
}
