package shaded.parquet.com.fasterxml.jackson.databind.node;

import java.io.IOException;
import shaded.parquet.com.fasterxml.jackson.core.JsonGenerator;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.SerializerProvider;
import shaded.parquet.com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public final class MissingNode extends ValueNode {
   private static final long serialVersionUID = 1L;
   private static final MissingNode instance = new MissingNode();

   protected MissingNode() {
   }

   protected Object readResolve() {
      return instance;
   }

   public boolean isMissingNode() {
      return true;
   }

   public JsonNode deepCopy() {
      return this;
   }

   public static MissingNode getInstance() {
      return instance;
   }

   public JsonNodeType getNodeType() {
      return JsonNodeType.MISSING;
   }

   public JsonToken asToken() {
      return JsonToken.NOT_AVAILABLE;
   }

   public String asText() {
      return "";
   }

   public String asText(String defaultValue) {
      return defaultValue;
   }

   public final void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
      g.writeNull();
   }

   public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      g.writeNull();
   }

   public JsonNode require() {
      return (JsonNode)this._reportRequiredViolation("require() called on `MissingNode`", new Object[0]);
   }

   public JsonNode requireNonNull() {
      return (JsonNode)this._reportRequiredViolation("requireNonNull() called on `MissingNode`", new Object[0]);
   }

   public int hashCode() {
      return JsonNodeType.MISSING.ordinal();
   }

   public String toString() {
      return "";
   }

   public String toPrettyString() {
      return "";
   }

   public boolean equals(Object o) {
      return o == this;
   }
}
