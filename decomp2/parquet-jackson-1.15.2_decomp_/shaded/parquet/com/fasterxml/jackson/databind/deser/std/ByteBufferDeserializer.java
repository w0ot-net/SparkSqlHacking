package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.ByteBufferBackedOutputStream;

public class ByteBufferDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;

   protected ByteBufferDeserializer() {
      super(ByteBuffer.class);
   }

   public LogicalType logicalType() {
      return LogicalType.Binary;
   }

   public ByteBuffer deserialize(JsonParser parser, DeserializationContext cx) throws IOException {
      byte[] b = parser.getBinaryValue();
      return ByteBuffer.wrap(b);
   }

   public ByteBuffer deserialize(JsonParser jp, DeserializationContext ctxt, ByteBuffer intoValue) throws IOException {
      OutputStream out = new ByteBufferBackedOutputStream(intoValue);
      jp.readBinaryValue(ctxt.getBase64Variant(), out);
      out.close();
      return intoValue;
   }
}
