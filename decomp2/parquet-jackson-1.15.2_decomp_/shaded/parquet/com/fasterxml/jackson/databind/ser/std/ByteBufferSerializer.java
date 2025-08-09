package shaded.parquet.com.fasterxml.jackson.databind.ser.std;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.databind.JavaType;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import shaded.parquet.com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import shaded.parquet.com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

public class ByteBufferSerializer extends StdScalarSerializer {
   public ByteBufferSerializer() {
      super(ByteBuffer.class);
   }

   public void serialize(ByteBuffer bbuf, JsonGenerator gen, SerializerProvider provider) throws IOException {
      if (bbuf.hasArray()) {
         int pos = bbuf.position();
         gen.writeBinary(bbuf.array(), bbuf.arrayOffset() + pos, bbuf.limit() - pos);
      } else {
         ByteBuffer copy = bbuf.asReadOnlyBuffer();
         InputStream in = new ByteBufferBackedInputStream(copy);
         gen.writeBinary(in, copy.remaining());
         in.close();
      }
   }

   public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
      JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
      if (v2 != null) {
         v2.itemsFormat(JsonFormatTypes.INTEGER);
      }

   }
}
