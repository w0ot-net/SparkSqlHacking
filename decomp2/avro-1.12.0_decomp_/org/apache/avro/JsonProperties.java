package org.apache.avro;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import org.apache.avro.util.MapEntry;
import org.apache.avro.util.internal.Accessor;
import org.apache.avro.util.internal.JacksonUtils;

public abstract class JsonProperties {
   public static final Null NULL_VALUE;
   private ConcurrentMap props = new ConcurrentHashMap() {
      private static final long serialVersionUID = 1L;
      private Queue propOrder = new ConcurrentLinkedQueue();

      public JsonNode putIfAbsent(String key, JsonNode value) {
         JsonNode r = (JsonNode)super.putIfAbsent(key, value);
         if (r == null) {
            this.propOrder.add(new MapEntry(key, value));
         }

         return r;
      }

      public JsonNode put(String key, JsonNode value) {
         return this.putIfAbsent(key, value);
      }

      public Set entrySet() {
         return new AbstractSet() {
            public Iterator iterator() {
               return new Iterator() {
                  Iterator it;

                  {
                     this.it = propOrder.iterator();
                  }

                  public boolean hasNext() {
                     return this.it.hasNext();
                  }

                  public Map.Entry next() {
                     return (Map.Entry)this.it.next();
                  }
               };
            }

            public int size() {
               return propOrder.size();
            }
         };
      }
   };
   private Set reserved;

   JsonProperties(Set reserved) {
      this.reserved = reserved;
   }

   JsonProperties(Set reserved, Map propMap) {
      this.reserved = reserved;

      for(Map.Entry a : propMap.entrySet()) {
         Object v = a.getValue();
         JsonNode json = null;
         Object var7;
         if (v instanceof String) {
            var7 = TextNode.valueOf((String)v);
         } else if (v instanceof JsonNode) {
            var7 = (JsonNode)v;
         } else {
            var7 = JacksonUtils.toJsonNode(v);
         }

         this.props.put((String)a.getKey(), var7);
      }

   }

   public String getProp(String name) {
      JsonNode value = this.getJsonProp(name);
      return value != null && value.isTextual() ? value.textValue() : null;
   }

   private JsonNode getJsonProp(String name) {
      return (JsonNode)this.props.get(name);
   }

   public Object getObjectProp(String name) {
      return JacksonUtils.toObject((JsonNode)this.props.get(name));
   }

   public Object getObjectProp(String name, Object defaultValue) {
      JsonNode json = (JsonNode)this.props.get(name);
      return json != null ? JacksonUtils.toObject(json) : defaultValue;
   }

   public void addProp(String name, String value) {
      this.addProp(name, (JsonNode)TextNode.valueOf(value));
   }

   public void addProp(String name, Object value) {
      if (value instanceof JsonNode) {
         this.addProp(name, (JsonNode)value);
      } else {
         this.addProp(name, JacksonUtils.toJsonNode(value));
      }

   }

   public void putAll(JsonProperties np) {
      for(Map.Entry e : np.props.entrySet()) {
         this.addProp((String)e.getKey(), (JsonNode)e.getValue());
      }

   }

   private void addProp(String name, JsonNode value) {
      if (this.reserved.contains(name)) {
         throw new AvroRuntimeException("Can't set reserved property: " + name);
      } else if (value == null) {
         throw new AvroRuntimeException("Can't set a property to null: " + name);
      } else {
         JsonNode old = (JsonNode)this.props.putIfAbsent(name, value);
         if (old != null && !old.equals(value)) {
            throw new AvroRuntimeException("Can't overwrite property: " + name);
         }
      }
   }

   public void addAllProps(JsonProperties properties) {
      for(Map.Entry entry : properties.props.entrySet()) {
         this.addProp((String)entry.getKey(), (JsonNode)entry.getValue());
      }

   }

   public Map getObjectProps() {
      Map<String, Object> result = new LinkedHashMap();

      for(Map.Entry e : this.props.entrySet()) {
         result.put((String)e.getKey(), JacksonUtils.toObject((JsonNode)e.getValue()));
      }

      return Collections.unmodifiableMap(result);
   }

   public boolean propsContainsKey(String key) {
      return this.props.containsKey(key);
   }

   public void forEachProperty(BiConsumer consumer) {
      for(Map.Entry entry : this.props.entrySet()) {
         Object value = JacksonUtils.toObject((JsonNode)entry.getValue());
         consumer.accept((String)entry.getKey(), value);
      }

   }

   void writeProps(JsonGenerator gen) throws IOException {
      for(Map.Entry e : this.props.entrySet()) {
         gen.writeObjectField((String)e.getKey(), e.getValue());
      }

   }

   int propsHashCode() {
      return this.props.hashCode();
   }

   boolean propsEqual(JsonProperties np) {
      return Objects.equals(this.props, np.props);
   }

   public boolean hasProps() {
      return !this.props.isEmpty();
   }

   static {
      Accessor.setAccessor(new Accessor.JsonPropertiesAccessor() {
         protected void addProp(JsonProperties props, String name, JsonNode value) {
            props.addProp(name, value);
         }
      });
      NULL_VALUE = new Null();
   }

   public static class Null {
      private Null() {
      }
   }
}
