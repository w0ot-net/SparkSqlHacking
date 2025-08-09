package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.List;

public abstract class ValueNode extends BaseJsonNode {
   private static final long serialVersionUID = 1L;

   protected ValueNode() {
   }

   protected JsonNode _at(JsonPointer ptr) {
      return null;
   }

   public JsonNode deepCopy() {
      return this;
   }

   public abstract JsonToken asToken();

   public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(this, this.asToken()));
      this.serialize(g, provider);
      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   public boolean isEmpty() {
      return true;
   }

   public final JsonNode get(int index) {
      return null;
   }

   public final JsonNode path(int index) {
      return MissingNode.getInstance();
   }

   public final boolean has(int index) {
      return false;
   }

   public final boolean hasNonNull(int index) {
      return false;
   }

   public final JsonNode get(String fieldName) {
      return null;
   }

   public final JsonNode path(String fieldName) {
      return MissingNode.getInstance();
   }

   public final boolean has(String fieldName) {
      return false;
   }

   public final boolean hasNonNull(String fieldName) {
      return false;
   }

   public final JsonNode findValue(String fieldName) {
      return null;
   }

   public final ObjectNode findParent(String fieldName) {
      return null;
   }

   public final List findValues(String fieldName, List foundSoFar) {
      return foundSoFar;
   }

   public final List findValuesAsText(String fieldName, List foundSoFar) {
      return foundSoFar;
   }

   public final List findParents(String fieldName, List foundSoFar) {
      return foundSoFar;
   }
}
