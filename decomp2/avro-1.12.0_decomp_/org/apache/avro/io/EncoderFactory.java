package org.apache.avro.io;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.EnumSet;
import org.apache.avro.AvroRuntimeException;
import org.apache.avro.Schema;

public class EncoderFactory {
   private static final int DEFAULT_BUFFER_SIZE = 2048;
   private static final int DEFAULT_BLOCK_BUFFER_SIZE = 65536;
   private static final int MIN_BLOCK_BUFFER_SIZE = 64;
   private static final int MAX_BLOCK_BUFFER_SIZE = 1073741824;
   private static final EncoderFactory DEFAULT_FACTORY = new DefaultEncoderFactory();
   protected int binaryBufferSize = 2048;
   protected int binaryBlockSize = 65536;

   public static EncoderFactory get() {
      return DEFAULT_FACTORY;
   }

   public EncoderFactory configureBufferSize(int size) {
      if (size < 32) {
         size = 32;
      }

      if (size > 16777216) {
         size = 16777216;
      }

      this.binaryBufferSize = size;
      return this;
   }

   public int getBufferSize() {
      return this.binaryBufferSize;
   }

   public EncoderFactory configureBlockSize(int size) {
      if (size < 64) {
         size = 64;
      }

      if (size > 1073741824) {
         size = 1073741824;
      }

      this.binaryBlockSize = size;
      return this;
   }

   public int getBlockSize() {
      return this.binaryBlockSize;
   }

   public BinaryEncoder binaryEncoder(OutputStream out, BinaryEncoder reuse) {
      return null != reuse && reuse.getClass().equals(BufferedBinaryEncoder.class) ? ((BufferedBinaryEncoder)reuse).configure(out, this.binaryBufferSize) : new BufferedBinaryEncoder(out, this.binaryBufferSize);
   }

   public BinaryEncoder directBinaryEncoder(OutputStream out, BinaryEncoder reuse) {
      return null != reuse && reuse.getClass().equals(DirectBinaryEncoder.class) ? ((DirectBinaryEncoder)reuse).configure(out) : new DirectBinaryEncoder(out);
   }

   public BinaryEncoder blockingDirectBinaryEncoder(OutputStream out, BinaryEncoder reuse) {
      return (BinaryEncoder)(null != reuse && reuse.getClass().equals(BlockingDirectBinaryEncoder.class) ? ((DirectBinaryEncoder)reuse).configure(out) : new BlockingDirectBinaryEncoder(out));
   }

   public BinaryEncoder blockingBinaryEncoder(OutputStream out, BinaryEncoder reuse) {
      int blockSize = this.binaryBlockSize;
      int bufferSize = blockSize * 2 >= this.binaryBufferSize ? 32 : this.binaryBufferSize;
      return null != reuse && reuse.getClass().equals(BlockingBinaryEncoder.class) ? ((BlockingBinaryEncoder)reuse).configure(out, blockSize, bufferSize) : new BlockingBinaryEncoder(out, blockSize, bufferSize);
   }

   public JsonEncoder jsonEncoder(Schema schema, OutputStream out) throws IOException {
      return new JsonEncoder(schema, out);
   }

   public JsonEncoder jsonEncoder(Schema schema, OutputStream out, boolean pretty) throws IOException {
      return new JsonEncoder(schema, out, pretty);
   }

   public JsonEncoder jsonEncoder(Schema schema, OutputStream out, boolean pretty, boolean autoflush) throws IOException {
      EnumSet<JsonEncoder.JsonOptions> options = EnumSet.noneOf(JsonEncoder.JsonOptions.class);
      if (pretty) {
         options.add(JsonEncoder.JsonOptions.Pretty);
      }

      if (!autoflush) {
         options.add(JsonEncoder.JsonOptions.NoFlushStream);
      }

      return new JsonEncoder(schema, out, options);
   }

   JsonEncoder jsonEncoder(Schema schema, JsonGenerator gen) throws IOException {
      return new JsonEncoder(schema, gen);
   }

   public ValidatingEncoder validatingEncoder(Schema schema, Encoder encoder) throws IOException {
      return new ValidatingEncoder(schema, encoder);
   }

   private static class DefaultEncoderFactory extends EncoderFactory {
      public EncoderFactory configureBlockSize(int size) {
         throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
      }

      public EncoderFactory configureBufferSize(int size) {
         throw new AvroRuntimeException("Default EncoderFactory cannot be configured");
      }
   }
}
