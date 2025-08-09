package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.EnumMap;
import java.util.Map;

enum NodeType {
   ARRAY("array"),
   BOOLEAN("boolean"),
   INTEGER("integer"),
   NULL("null"),
   NUMBER("number"),
   OBJECT("object"),
   STRING("string");

   private final String name;
   private static final Map TOKEN_MAP = new EnumMap(JsonToken.class);

   private NodeType(String name) {
      this.name = name;
   }

   public String toString() {
      return this.name;
   }

   public static NodeType getNodeType(JsonNode node) {
      JsonToken token = node.asToken();
      NodeType ret = (NodeType)TOKEN_MAP.get(token);
      if (ret == null) {
         throw new NullPointerException("unhandled token type " + token);
      } else {
         return ret;
      }
   }

   static {
      TOKEN_MAP.put(JsonToken.START_ARRAY, ARRAY);
      TOKEN_MAP.put(JsonToken.VALUE_TRUE, BOOLEAN);
      TOKEN_MAP.put(JsonToken.VALUE_FALSE, BOOLEAN);
      TOKEN_MAP.put(JsonToken.VALUE_NUMBER_INT, INTEGER);
      TOKEN_MAP.put(JsonToken.VALUE_NUMBER_FLOAT, NUMBER);
      TOKEN_MAP.put(JsonToken.VALUE_NULL, NULL);
      TOKEN_MAP.put(JsonToken.START_OBJECT, OBJECT);
      TOKEN_MAP.put(JsonToken.VALUE_STRING, STRING);
   }
}
