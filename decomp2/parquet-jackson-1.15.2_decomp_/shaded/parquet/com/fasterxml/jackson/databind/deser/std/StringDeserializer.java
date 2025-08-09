package shaded.parquet.com.fasterxml.jackson.databind.deser.std;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonParser;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.DeserializationContext;
import shaded.parquet.com.fasterxml.jackson.databind.JsonMappingException;
import shaded.parquet.com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import shaded.parquet.com.fasterxml.jackson.databind.type.LogicalType;

@JacksonStdImpl
public class StringDeserializer extends StdScalarDeserializer {
   private static final long serialVersionUID = 1L;
   public static final StringDeserializer instance = new StringDeserializer();

   public StringDeserializer() {
      super(String.class);
   }

   public LogicalType logicalType() {
      return LogicalType.Textual;
   }

   public boolean isCachable() {
      return true;
   }

   public Object getEmptyValue(DeserializationContext ctxt) throws JsonMappingException {
      return "";
   }

   public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      if (p.hasToken(JsonToken.VALUE_STRING)) {
         return p.getText();
      } else {
         return p.hasToken(JsonToken.START_ARRAY) ? (String)this._deserializeFromArray(p, ctxt) : this._parseString(p, ctxt, this);
      }
   }

   public String deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
      return this.deserialize(p, ctxt);
   }
}
