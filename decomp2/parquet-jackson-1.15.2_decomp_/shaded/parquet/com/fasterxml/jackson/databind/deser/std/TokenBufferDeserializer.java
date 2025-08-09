package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;
import shaded.parquet.com.fasterxml.jackson.databind.util.TokenBuffer;

@JacksonStdImpl
public class TokenBufferDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;

   public TokenBufferDeserializer() {
      super(TokenBuffer.class);
   }

   public LogicalType logicalType() {
      return LogicalType.Untyped;
   }

   public TokenBuffer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      return ctxt.bufferForInputBuffering(p).deserialize(p, ctxt);
   }
}
