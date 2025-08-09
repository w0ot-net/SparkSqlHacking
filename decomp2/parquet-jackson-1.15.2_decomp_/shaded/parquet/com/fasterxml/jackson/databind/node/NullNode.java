package shaded.parquet.com.fasterxml.jackson.databind.node;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;

public class NullNode extends ValueNode {
   private static final long serialVersionUID = 1L;
   public static final NullNode instance = new NullNode();

   protected NullNode() {
   }

   protected Object readResolve() {
      return instance;
   }

   public static NullNode getInstance() {
      return instance;
   }

   public JsonNodeType getNodeType() {
      return JsonNodeType.NULL;
   }

   public JsonToken asToken() {
      return JsonToken.VALUE_NULL;
   }

   public String asText(String defaultValue) {
      return defaultValue;
   }

   public String asText() {
      return "null";
   }

   public JsonNode requireNonNull() {
      return (JsonNode)this._reportRequiredViolation("requireNonNull() called on `NullNode`", new Object[0]);
   }

   public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
      provider.defaultSerializeNull(g);
   }

   public boolean equals(Object o) {
      return o == this || o instanceof NullNode;
   }

   public int hashCode() {
      return JsonNodeType.NULL.ordinal();
   }
}
