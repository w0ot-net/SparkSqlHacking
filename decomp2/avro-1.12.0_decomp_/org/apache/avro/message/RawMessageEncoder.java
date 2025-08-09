package org.apache.avro.message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.internal.ThreadLocalWithInitial;

public class RawMessageEncoder implements MessageEncoder {
   private static final ThreadLocal TEMP = ThreadLocalWithInitial.of(BufferOutputStream::new);
   private static final ThreadLocal ENCODER = new ThreadLocal();
   private final boolean copyOutputBytes;
   private final DatumWriter writer;

   public RawMessageEncoder(GenericData model, Schema schema) {
      this(model, schema, true);
   }

   public RawMessageEncoder(GenericData model, Schema schema, boolean shouldCopy) {
      this.copyOutputBytes = shouldCopy;
      this.writer = model.createDatumWriter(schema);
   }

   public ByteBuffer encode(Object datum) throws IOException {
      BufferOutputStream temp = (BufferOutputStream)TEMP.get();
      temp.reset();
      this.encode(datum, temp);
      return this.copyOutputBytes ? temp.toBufferWithCopy() : temp.toBufferWithoutCopy();
   }

   public void encode(Object datum, OutputStream stream) throws IOException {
      BinaryEncoder encoder = EncoderFactory.get().directBinaryEncoder(stream, (BinaryEncoder)ENCODER.get());
      ENCODER.set(encoder);
      this.writer.write(datum, encoder);
      encoder.flush();
   }

   private static class BufferOutputStream extends ByteArrayOutputStream {
      BufferOutputStream() {
      }

      ByteBuffer toBufferWithoutCopy() {
         return ByteBuffer.wrap(this.buf, 0, this.count);
      }

      ByteBuffer toBufferWithCopy() {
         return ByteBuffer.wrap(this.toByteArray());
      }
   }
}
