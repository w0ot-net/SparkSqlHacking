package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;

public class AtomicIntegerDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;

   public AtomicIntegerDeserializer() {
      super(AtomicInteger.class);
   }

   public AtomicInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.isExpectedNumberIntToken()) {
         return new AtomicInteger(p.getIntValue());
      } else {
         Integer I = this._parseInteger(p, ctxt, AtomicInteger.class);
         return I == null ? null : new AtomicInteger(I);
      }
   }

   public LogicalType logicalType() {
      return LogicalType.Integer;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return new AtomicInteger();
   }
}
