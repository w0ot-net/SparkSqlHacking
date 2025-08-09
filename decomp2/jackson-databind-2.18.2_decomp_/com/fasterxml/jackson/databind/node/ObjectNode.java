package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class ObjectNode extends ContainerNode implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final Map _children;

   public ObjectNode(JsonNodeFactory nc) {
      super(nc);
      this._children = new LinkedHashMap();
   }

   public ObjectNode(JsonNodeFactory nc, Map children) {
      super(nc);
      this._children = (Map)Objects.requireNonNull(children, "Must not pass `null` for 'children' argument");
   }

   protected JsonNode _at(JsonPointer ptr) {
      return this.get(ptr.getMatchingProperty());
   }

   public ObjectNode deepCopy() {
      ObjectNode ret = this.objectNode();

      for(Map.Entry entry : this._children.entrySet()) {
         ret._children.put(entry.getKey(), ((JsonNode)entry.getValue()).deepCopy());
      }

      return ret;
   }

   /** @deprecated */
   @Deprecated
   public ObjectNode with(String exprOrProperty) {
      JsonPointer ptr = this._jsonPointerIfValid(exprOrProperty);
      if (ptr != null) {
         return this.withObject(ptr);
      } else {
         JsonNode n = (JsonNode)this._children.get(exprOrProperty);
         if (n != null) {
            if (n instanceof ObjectNode) {
               return (ObjectNode)n;
            } else {
               throw new UnsupportedOperationException("Property '" + exprOrProperty + "' has value that is not of type `ObjectNode` (but `" + n.getClass().getName() + "`)");
            }
         } else {
            ObjectNode result = this.objectNode();
            this._children.put(exprOrProperty, result);
            return result;
         }
      }
   }

   public ObjectNode withObject(String exprOrProperty) {
      JsonPointer ptr = this._jsonPointerIfValid(exprOrProperty);
      return ptr != null ? this.withObject(ptr) : this.withObjectProperty(exprOrProperty);
   }

   public ObjectNode withObjectProperty(String propName) {
      JsonNode child = (JsonNode)this._children.get(propName);
      if (child != null && !child.isNull()) {
         return child.isObject() ? (ObjectNode)child : (ObjectNode)this._reportWrongNodeType("Cannot replace `JsonNode` of type `%s` with `ObjectNode` for property \"%s\" (default mode `OverwriteMode.%s`)", new Object[]{child.getClass().getName(), propName, JsonNode.OverwriteMode.NULLS});
      } else {
         return this.putObject(propName);
      }
   }

   public ArrayNode withArray(String exprOrProperty) {
      JsonPointer ptr = this._jsonPointerIfValid(exprOrProperty);
      if (ptr != null) {
         return this.withArray((JsonPointer)ptr);
      } else {
         JsonNode n = (JsonNode)this._children.get(exprOrProperty);
         if (n != null) {
            if (n instanceof ArrayNode) {
               return (ArrayNode)n;
            } else {
               throw new UnsupportedOperationException("Property '" + exprOrProperty + "' has value that is not of type `ArrayNode` (but `" + n.getClass().getName() + "`)");
            }
         } else {
            ArrayNode result = this.arrayNode();
            this._children.put(exprOrProperty, result);
            return result;
         }
      }
   }

   public ArrayNode withArrayProperty(String propName) {
      JsonNode child = (JsonNode)this._children.get(propName);
      if (child != null && !child.isNull()) {
         return child.isArray() ? (ArrayNode)child : (ArrayNode)this._reportWrongNodeType("Cannot replace `JsonNode` of type `%s` with `ArrayNode` for property \"%s\" with (default mode `OverwriteMode.%s`)", new Object[]{child.getClass().getName(), propName, JsonNode.OverwriteMode.NULLS});
      } else {
         return this.putArray(propName);
      }
   }

   protected ObjectNode _withObject(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
      if (currentPtr.matches()) {
         return this;
      } else {
         JsonNode n = this._at(currentPtr);
         if (n != null && n instanceof BaseJsonNode) {
            ObjectNode found = ((BaseJsonNode)n)._withObject(origPtr, currentPtr.tail(), overwriteMode, preferIndex);
            if (found != null) {
               return found;
            }

            this._withXxxVerifyReplace(origPtr, currentPtr, overwriteMode, preferIndex, n);
         }

         return this._withObjectAddTailProperty(currentPtr, preferIndex);
      }
   }

   protected ArrayNode _withArray(JsonPointer origPtr, JsonPointer currentPtr, JsonNode.OverwriteMode overwriteMode, boolean preferIndex) {
      if (currentPtr.matches()) {
         return null;
      } else {
         JsonNode n = this._at(currentPtr);
         if (n != null && n instanceof BaseJsonNode) {
            ArrayNode found = ((BaseJsonNode)n)._withArray(origPtr, currentPtr.tail(), overwriteMode, preferIndex);
            if (found != null) {
               return found;
            }

            this._withXxxVerifyReplace(origPtr, currentPtr, overwriteMode, preferIndex, n);
         }

         return this._withArrayAddTailProperty(currentPtr, preferIndex);
      }
   }

   protected ObjectNode _withObjectAddTailProperty(JsonPointer tail, boolean preferIndex) {
      String propName = tail.getMatchingProperty();
      tail = tail.tail();
      if (tail.matches()) {
         return this.putObject(propName);
      } else {
         return preferIndex && tail.mayMatchElement() ? this.putArray(propName)._withObjectAddTailElement(tail, preferIndex) : this.putObject(propName)._withObjectAddTailProperty(tail, preferIndex);
      }
   }

   protected ArrayNode _withArrayAddTailProperty(JsonPointer tail, boolean preferIndex) {
      String propName = tail.getMatchingProperty();
      tail = tail.tail();
      if (tail.matches()) {
         return this.putArray(propName);
      } else {
         return preferIndex && tail.mayMatchElement() ? this.putArray(propName)._withArrayAddTailElement(tail, preferIndex) : this.putObject(propName)._withArrayAddTailProperty(tail, preferIndex);
      }
   }

   public boolean isEmpty(SerializerProvider serializers) {
      return this._children.isEmpty();
   }

   public JsonNodeType getNodeType() {
      return JsonNodeType.OBJECT;
   }

   public final boolean isObject() {
      return true;
   }

   public JsonToken asToken() {
      return JsonToken.START_OBJECT;
   }

   public int size() {
      return this._children.size();
   }

   public boolean isEmpty() {
      return this._children.isEmpty();
   }

   public Iterator elements() {
      return this._children.values().iterator();
   }

   public JsonNode get(int index) {
      return null;
   }

   public JsonNode get(String propertyName) {
      return (JsonNode)this._children.get(propertyName);
   }

   public Iterator fieldNames() {
      return this._children.keySet().iterator();
   }

   public JsonNode path(int index) {
      return MissingNode.getInstance();
   }

   public JsonNode path(String propertyName) {
      JsonNode n = (JsonNode)this._children.get(propertyName);
      return (JsonNode)(n != null ? n : MissingNode.getInstance());
   }

   public JsonNode required(String propertyName) {
      JsonNode n = (JsonNode)this._children.get(propertyName);
      return n != null ? n : (JsonNode)this._reportRequiredViolation("No value for property '%s' of `ObjectNode`", new Object[]{propertyName});
   }

   public Iterator fields() {
      return this._children.entrySet().iterator();
   }

   public Set properties() {
      return this._children.entrySet();
   }

   public boolean equals(Comparator comparator, JsonNode o) {
      if (!(o instanceof ObjectNode)) {
         return false;
      } else {
         ObjectNode other = (ObjectNode)o;
         Map<String, JsonNode> m1 = this._children;
         Map<String, JsonNode> m2 = other._children;
         int len = m1.size();
         if (m2.size() != len) {
            return false;
         } else {
            for(Map.Entry entry : m1.entrySet()) {
               JsonNode v2 = (JsonNode)m2.get(entry.getKey());
               if (v2 == null || !((JsonNode)entry.getValue()).equals(comparator, v2)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public JsonNode findValue(String propertyName) {
      JsonNode jsonNode = (JsonNode)this._children.get(propertyName);
      if (jsonNode != null) {
         return jsonNode;
      } else {
         for(JsonNode child : this._children.values()) {
            JsonNode value = child.findValue(propertyName);
            if (value != null) {
               return value;
            }
         }

         return null;
      }
   }

   public List findValues(String propertyName, List foundSoFar) {
      for(Map.Entry entry : this._children.entrySet()) {
         if (propertyName.equals(entry.getKey())) {
            if (foundSoFar == null) {
               foundSoFar = new ArrayList();
            }

            foundSoFar.add(entry.getValue());
         } else {
            foundSoFar = ((JsonNode)entry.getValue()).findValues(propertyName, foundSoFar);
         }
      }

      return foundSoFar;
   }

   public List findValuesAsText(String propertyName, List foundSoFar) {
      for(Map.Entry entry : this._children.entrySet()) {
         if (propertyName.equals(entry.getKey())) {
            if (foundSoFar == null) {
               foundSoFar = new ArrayList();
            }

            foundSoFar.add(((JsonNode)entry.getValue()).asText());
         } else {
            foundSoFar = ((JsonNode)entry.getValue()).findValuesAsText(propertyName, foundSoFar);
         }
      }

      return foundSoFar;
   }

   public ObjectNode findParent(String propertyName) {
      JsonNode jsonNode = (JsonNode)this._children.get(propertyName);
      if (jsonNode != null) {
         return this;
      } else {
         for(JsonNode child : this._children.values()) {
            JsonNode value = child.findParent(propertyName);
            if (value != null) {
               return (ObjectNode)value;
            }
         }

         return null;
      }
   }

   public List findParents(String propertyName, List foundSoFar) {
      for(Map.Entry entry : this._children.entrySet()) {
         if (propertyName.equals(entry.getKey())) {
            if (foundSoFar == null) {
               foundSoFar = new ArrayList();
            }

            foundSoFar.add(this);
         } else {
            foundSoFar = ((JsonNode)entry.getValue()).findParents(propertyName, foundSoFar);
         }
      }

      return foundSoFar;
   }

   public void serialize(JsonGenerator g, SerializerProvider provider) throws IOException {
      if (provider != null) {
         boolean trimEmptyArray = !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
         boolean skipNulls = !provider.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_NULL_PROPERTIES);
         if (trimEmptyArray || skipNulls) {
            g.writeStartObject(this);
            this.serializeFilteredContents(g, provider, trimEmptyArray, skipNulls);
            g.writeEndObject();
            return;
         }
      }

      g.writeStartObject(this);

      for(Map.Entry en : this._contentsToSerialize(provider).entrySet()) {
         JsonNode value = (JsonNode)en.getValue();
         g.writeFieldName((String)en.getKey());
         value.serialize(g, provider);
      }

      g.writeEndObject();
   }

   public void serializeWithType(JsonGenerator g, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
      boolean trimEmptyArray = false;
      boolean skipNulls = false;
      if (provider != null) {
         trimEmptyArray = !provider.isEnabled(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
         skipNulls = !provider.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_NULL_PROPERTIES);
      }

      WritableTypeId typeIdDef = typeSer.writeTypePrefix(g, typeSer.typeId(this, JsonToken.START_OBJECT));
      if (!trimEmptyArray && !skipNulls) {
         for(Map.Entry en : this._contentsToSerialize(provider).entrySet()) {
            JsonNode value = (JsonNode)en.getValue();
            g.writeFieldName((String)en.getKey());
            value.serialize(g, provider);
         }
      } else {
         this.serializeFilteredContents(g, provider, trimEmptyArray, skipNulls);
      }

      typeSer.writeTypeSuffix(g, typeIdDef);
   }

   protected void serializeFilteredContents(JsonGenerator g, SerializerProvider ctxt, boolean trimEmptyArray, boolean skipNulls) throws IOException {
      for(Map.Entry en : this._contentsToSerialize(ctxt).entrySet()) {
         JsonNode value = (JsonNode)en.getValue();
         if ((!trimEmptyArray || !value.isArray() || !value.isEmpty(ctxt)) && (!skipNulls || !value.isNull())) {
            g.writeFieldName((String)en.getKey());
            value.serialize(g, ctxt);
         }
      }

   }

   protected Map _contentsToSerialize(SerializerProvider ctxt) {
      return (Map)(ctxt.isEnabled((DatatypeFeature)JsonNodeFeature.WRITE_PROPERTIES_SORTED) && !this._children.isEmpty() && !(this._children instanceof TreeMap) ? new TreeMap(this._children) : this._children);
   }

   public JsonNode set(String propertyName, JsonNode value) {
      if (value == null) {
         value = this.nullNode();
      }

      this._children.put(propertyName, value);
      return this;
   }

   public JsonNode setAll(Map properties) {
      for(Map.Entry en : properties.entrySet()) {
         JsonNode n = (JsonNode)en.getValue();
         if (n == null) {
            n = this.nullNode();
         }

         this._children.put(en.getKey(), n);
      }

      return this;
   }

   public JsonNode setAll(ObjectNode other) {
      this._children.putAll(other._children);
      return this;
   }

   public JsonNode replace(String propertyName, JsonNode value) {
      if (value == null) {
         value = this.nullNode();
      }

      return (JsonNode)this._children.put(propertyName, value);
   }

   public JsonNode without(String propertyName) {
      this._children.remove(propertyName);
      return this;
   }

   public JsonNode without(Collection propertyNames) {
      this._children.keySet().removeAll(propertyNames);
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JsonNode put(String propertyName, JsonNode value) {
      if (value == null) {
         value = this.nullNode();
      }

      return (JsonNode)this._children.put(propertyName, value);
   }

   public JsonNode putIfAbsent(String propertyName, JsonNode value) {
      if (value == null) {
         value = this.nullNode();
      }

      return (JsonNode)this._children.putIfAbsent(propertyName, value);
   }

   public JsonNode remove(String propertyName) {
      return (JsonNode)this._children.remove(propertyName);
   }

   public ObjectNode remove(Collection propertyNames) {
      this._children.keySet().removeAll(propertyNames);
      return this;
   }

   public ObjectNode removeAll() {
      this._children.clear();
      return this;
   }

   /** @deprecated */
   @Deprecated
   public JsonNode putAll(Map properties) {
      return this.setAll(properties);
   }

   /** @deprecated */
   @Deprecated
   public JsonNode putAll(ObjectNode other) {
      return this.setAll(other);
   }

   public ObjectNode retain(Collection propertyNames) {
      this._children.keySet().retainAll(propertyNames);
      return this;
   }

   public ObjectNode retain(String... propertyNames) {
      return this.retain((Collection)Arrays.asList(propertyNames));
   }

   public ArrayNode putArray(String propertyName) {
      ArrayNode n = this.arrayNode();
      this._put(propertyName, n);
      return n;
   }

   public ObjectNode putObject(String propertyName) {
      ObjectNode n = this.objectNode();
      this._put(propertyName, n);
      return n;
   }

   public ObjectNode putPOJO(String propertyName, Object pojo) {
      return this._put(propertyName, this.pojoNode(pojo));
   }

   public ObjectNode putRawValue(String propertyName, RawValue raw) {
      return this._put(propertyName, this.rawValueNode(raw));
   }

   public ObjectNode putNull(String propertyName) {
      this._children.put(propertyName, this.nullNode());
      return this;
   }

   public ObjectNode put(String propertyName, short v) {
      return this._put(propertyName, this.numberNode(v));
   }

   public ObjectNode put(String fieldName, Short v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, int v) {
      return this._put(fieldName, this.numberNode(v));
   }

   public ObjectNode put(String fieldName, Integer v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, long v) {
      return this._put(fieldName, this.numberNode(v));
   }

   public ObjectNode put(String fieldName, Long v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, float v) {
      return this._put(fieldName, this.numberNode(v));
   }

   public ObjectNode put(String fieldName, Float v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, double v) {
      return this._put(fieldName, this.numberNode(v));
   }

   public ObjectNode put(String fieldName, Double v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, BigDecimal v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, BigInteger v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.numberNode(v)));
   }

   public ObjectNode put(String fieldName, String v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.textNode(v)));
   }

   public ObjectNode put(String fieldName, boolean v) {
      return this._put(fieldName, this.booleanNode(v));
   }

   public ObjectNode put(String fieldName, Boolean v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.booleanNode(v)));
   }

   public ObjectNode put(String fieldName, byte[] v) {
      return this._put(fieldName, (JsonNode)(v == null ? this.nullNode() : this.binaryNode(v)));
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (o == null) {
         return false;
      } else {
         return o instanceof ObjectNode ? this._childrenEqual((ObjectNode)o) : false;
      }
   }

   protected boolean _childrenEqual(ObjectNode other) {
      return this._children.equals(other._children);
   }

   public int hashCode() {
      return this._children.hashCode();
   }

   protected ObjectNode _put(String fieldName, JsonNode value) {
      this._children.put(fieldName, value);
      return this;
   }
}
