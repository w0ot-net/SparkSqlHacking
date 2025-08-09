package org.apache.orc.impl;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Random;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.orc.EncryptionAlgorithm;

public interface HadoopShims {
   DirectDecompressor getDirectDecompressor(DirectCompressionType var1);

   ZeroCopyReaderShim getZeroCopyReader(FSDataInputStream var1, ByteBufferPoolShim var2) throws IOException;

   boolean endVariableLengthBlock(OutputStream var1) throws IOException;

   default boolean supportVectoredIO(String version) {
      String[] versionParts = version.split("[.-]");
      int major = Integer.parseInt(versionParts[0]);
      int minor = Integer.parseInt(versionParts[1]);
      int patch = Integer.parseInt(versionParts[2]);
      return major == 3 && (minor > 3 || minor == 3 && patch > 4);
   }

   KeyProvider getHadoopKeyProvider(Configuration var1, Random var2) throws IOException;

   public static enum DirectCompressionType {
      NONE,
      ZLIB_NOHEADER,
      ZLIB,
      SNAPPY;

      // $FF: synthetic method
      private static DirectCompressionType[] $values() {
         return new DirectCompressionType[]{NONE, ZLIB_NOHEADER, ZLIB, SNAPPY};
      }
   }

   public static enum KeyProviderKind {
      UNKNOWN(0),
      HADOOP(1),
      AWS(2),
      GCP(3),
      AZURE(4);

      private final int value;

      private KeyProviderKind(int value) {
         this.value = value;
      }

      public int getValue() {
         return this.value;
      }

      // $FF: synthetic method
      private static KeyProviderKind[] $values() {
         return new KeyProviderKind[]{UNKNOWN, HADOOP, AWS, GCP, AZURE};
      }
   }

   public static class KeyMetadata {
      private final String keyName;
      private final int version;
      private final EncryptionAlgorithm algorithm;

      public KeyMetadata(String key, int version, EncryptionAlgorithm algorithm) {
         this.keyName = key;
         this.version = version;
         this.algorithm = algorithm;
      }

      public String getKeyName() {
         return this.keyName;
      }

      public EncryptionAlgorithm getAlgorithm() {
         return this.algorithm;
      }

      public int getVersion() {
         return this.version;
      }

      public String toString() {
         String var10000 = this.keyName;
         return var10000 + "@" + this.version + " " + String.valueOf(this.algorithm);
      }
   }

   public interface ByteBufferPoolShim {
      ByteBuffer getBuffer(boolean var1, int var2);

      void putBuffer(ByteBuffer var1);
   }

   public interface DirectDecompressor {
      void decompress(ByteBuffer var1, ByteBuffer var2) throws IOException;

      void reset();

      void end();
   }

   public interface ZeroCopyReaderShim extends Closeable {
      ByteBuffer readBuffer(int var1, boolean var2) throws IOException;

      /** @deprecated */
      @Deprecated
      void releaseBuffer(ByteBuffer var1);

      void releaseAllBuffers();

      void close() throws IOException;
   }
}
