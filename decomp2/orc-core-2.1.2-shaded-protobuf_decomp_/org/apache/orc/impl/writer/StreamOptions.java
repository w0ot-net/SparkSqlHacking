package org.apache.orc.impl.writer;

import java.security.Key;
import java.util.Arrays;
import java.util.function.Consumer;
import org.apache.orc.CompressionCodec;
import org.apache.orc.EncryptionAlgorithm;

public class StreamOptions {
   private CompressionCodec codec;
   private CompressionCodec.Options options;
   private int bufferSize;
   private EncryptionAlgorithm algorithm;
   private Key key;
   private byte[] iv;

   public StreamOptions(StreamOptions other) {
      this.codec = other.codec;
      if (other.options != null) {
         this.options = other.options.copy();
      }

      this.bufferSize = other.bufferSize;
      this.algorithm = other.algorithm;
      this.key = other.key;
      if (other.iv != null) {
         this.iv = Arrays.copyOf(other.iv, other.iv.length);
      }

   }

   public StreamOptions(int bufferSize) {
      this.bufferSize = bufferSize;
   }

   public StreamOptions bufferSize(int bufferSize) {
      this.bufferSize = bufferSize;
      return this;
   }

   public StreamOptions withCodec(CompressionCodec codec, CompressionCodec.Options options) {
      this.codec = codec;
      this.options = options;
      return this;
   }

   public StreamOptions withEncryption(EncryptionAlgorithm algorithm, Key key) {
      this.algorithm = algorithm;
      this.key = key;
      return this;
   }

   public StreamOptions modifyIv(Consumer modifier) {
      modifier.accept(this.getIv());
      return this;
   }

   public CompressionCodec getCodec() {
      return this.codec;
   }

   public CompressionCodec.Options getCodecOptions() {
      return this.options;
   }

   public byte[] getIv() {
      if (this.iv == null) {
         this.iv = new byte[this.algorithm.getIvLength()];
      }

      return this.iv;
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public boolean isEncrypted() {
      return this.key != null;
   }

   public Key getKey() {
      return this.key;
   }

   public EncryptionAlgorithm getAlgorithm() {
      return this.algorithm;
   }

   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Compress: ");
      if (this.codec == null) {
         builder.append("none");
      } else {
         builder.append(this.codec.getKind());
      }

      builder.append(" buffer: ");
      builder.append(this.bufferSize);
      if (this.isEncrypted()) {
         builder.append(" encryption: ");
         builder.append(this.algorithm.getAlgorithm());
         builder.append("/");
         builder.append(this.algorithm.keyLength());
      }

      return builder.toString();
   }
}
