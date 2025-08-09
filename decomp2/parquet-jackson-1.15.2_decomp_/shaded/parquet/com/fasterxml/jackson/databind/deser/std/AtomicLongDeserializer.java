package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;

public class AtomicLongDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;

   public AtomicLongDeserializer() {
      super(AtomicLong.class);
   }

   public AtomicLong deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken()) {
         return new AtomicLong(p.getLongValue());
      } else {
         Long L = this._parseLong(p, ctxt, AtomicLong.class);
         return L == null ? null : new AtomicLong((long)L.intValue());
      }
   }

   public LogicalType logicalType() {
      return LogicalType.Integer;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return new AtomicLong();
   }
}
