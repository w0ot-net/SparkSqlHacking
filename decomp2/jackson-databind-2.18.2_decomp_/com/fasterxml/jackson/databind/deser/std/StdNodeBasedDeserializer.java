package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public abstract class StdNodeBasedDeserializer extends StdDeserializer implements ResolvableDeserializer {
   private static final long serialVersionUID = 1L;
   protected JsonDeserializer _treeDeserializer;

   protected StdNodeBasedDeserializer(JavaType targetType) {
      super(targetType);
   }

   protected StdNodeBasedDeserializer(Class targetType) {
      super(targetType);
   }

   protected StdNodeBasedDeserializer(StdNodeBasedDeserializer src) {
      super((StdDeserializer)src);
      this._treeDeserializer = src._treeDeserializer;
   }

   public void resolve(DeserializationContext ctxt) throws JsonMappingException {
      this._treeDeserializer = ctxt.findRootValueDeserializer(ctxt.constructType(JsonNode.class));
   }

   public abstract Object convert(JsonNode var1, DeserializationContext var2) throws IOException;

   public Object convert(JsonNode root, DeserializationContext ctxt, Object newValue) throws IOException {
      ctxt.handleBadMerge(this);
      return this.convert(root, ctxt);
   }

   public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode n = (JsonNode)this._treeDeserializer.deserialize(jp, ctxt);
      return this.convert(n, ctxt);
   }

   public Object deserialize(JsonParser jp, DeserializationContext ctxt, Object newValue) throws IOException {
      JsonNode n = (JsonNode)this._treeDeserializer.deserialize(jp, ctxt);
      return this.convert(n, ctxt, newValue);
   }

   public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer td) throws IOException {
      JsonNode n = (JsonNode)this._treeDeserializer.deserializeWithType(jp, ctxt, td);
      return this.convert(n, ctxt);
   }
}
