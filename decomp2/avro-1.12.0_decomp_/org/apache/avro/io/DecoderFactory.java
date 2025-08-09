package org.apache.avro.io;

import java.io.IOException;
import java.io.InputStream;
import org.apache.avro.Schema;

public class DecoderFactory {
   private static final DecoderFactory DEFAULT_FACTORY = new DefaultDecoderFactory();
   static final int DEFAULT_BUFFER_SIZE = 8192;
   int binaryDecoderBufferSize = 8192;

   /** @deprecated */
   @Deprecated
   public static DecoderFactory defaultFactory() {
      return get();
   }

   public static DecoderFactory get() {
      return DEFAULT_FACTORY;
   }

   public DecoderFactory configureDecoderBufferSize(int size) {
      if (size < 32) {
         size = 32;
      }

      if (size > 16777216) {
         size = 16777216;
      }

      this.binaryDecoderBufferSize = size;
      return this;
   }

   public int getConfiguredBufferSize() {
      return this.binaryDecoderBufferSize;
   }

   /** @deprecated */
   @Deprecated
   public BinaryDecoder createBinaryDecoder(InputStream in, BinaryDecoder reuse) {
      return this.binaryDecoder(in, reuse);
   }

   public BinaryDecoder binaryDecoder(InputStream in, BinaryDecoder reuse) {
      return null != reuse && reuse.getClass().equals(BinaryDecoder.class) ? reuse.configure(in, this.binaryDecoderBufferSize) : new BinaryDecoder(in, this.binaryDecoderBufferSize);
   }

   public BinaryDecoder directBinaryDecoder(InputStream in, BinaryDecoder reuse) {
      return null != reuse && reuse.getClass().equals(DirectBinaryDecoder.class) ? ((DirectBinaryDecoder)reuse).configure(in) : new DirectBinaryDecoder(in);
   }

   /** @deprecated */
   @Deprecated
   public BinaryDecoder createBinaryDecoder(byte[] bytes, int offset, int length, BinaryDecoder reuse) {
      return null != reuse && reuse.getClass().equals(BinaryDecoder.class) ? reuse.configure(bytes, offset, length) : new BinaryDecoder(bytes, offset, length);
   }

   public BinaryDecoder binaryDecoder(byte[] bytes, int offset, int length, BinaryDecoder reuse) {
      return null != reuse && reuse.getClass().equals(BinaryDecoder.class) ? reuse.configure(bytes, offset, length) : new BinaryDecoder(bytes, offset, length);
   }

   /** @deprecated */
   @Deprecated
   public BinaryDecoder createBinaryDecoder(byte[] bytes, BinaryDecoder reuse) {
      return this.binaryDecoder(bytes, 0, bytes.length, reuse);
   }

   public BinaryDecoder binaryDecoder(byte[] bytes, BinaryDecoder reuse) {
      return this.binaryDecoder(bytes, 0, bytes.length, reuse);
   }

   public JsonDecoder jsonDecoder(Schema schema, InputStream input) throws IOException {
      return new JsonDecoder(schema, input);
   }

   public JsonDecoder jsonDecoder(Schema schema, String input) throws IOException {
      return new JsonDecoder(schema, input);
   }

   public ValidatingDecoder validatingDecoder(Schema schema, Decoder wrapped) throws IOException {
      return new ValidatingDecoder(schema, wrapped);
   }

   public ResolvingDecoder resolvingDecoder(Schema writer, Schema reader, Decoder wrapped) throws IOException {
      return new ResolvingDecoder(writer, reader, wrapped);
   }

   private static class DefaultDecoderFactory extends DecoderFactory {
      public DecoderFactory configureDecoderBufferSize(int bufferSize) {
         throw new IllegalArgumentException("This Factory instance is Immutable");
      }
   }
}
