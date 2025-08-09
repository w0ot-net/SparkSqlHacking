package io.vertx.core.json;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.shareddata.ClusterSerializable;
import io.vertx.core.shareddata.Shareable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public class JsonObject implements Iterable, ClusterSerializable, Shareable {
   private Map map;

   public JsonObject(String json) {
      if (json == null) {
         throw new NullPointerException();
      } else {
         this.fromJson(json);
         if (this.map == null) {
            throw new DecodeException("Invalid JSON object: " + json);
         }
      }
   }

   public JsonObject() {
      this.map = new LinkedHashMap();
   }

   public JsonObject(Map map) {
      if (map == null) {
         throw new NullPointerException();
      } else {
         this.map = map;
      }
   }

   public JsonObject(Buffer buf) {
      if (buf == null) {
         throw new NullPointerException();
      } else {
         this.fromBuffer(buf);
         if (this.map == null) {
            throw new DecodeException("Invalid JSON object: " + buf);
         }
      }
   }

   public static JsonObject of() {
      return new JsonObject();
   }

   public static JsonObject of(String k1, Object v1) {
      JsonObject obj = new JsonObject(new LinkedHashMap(1));
      obj.put(k1, v1);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2) {
      JsonObject obj = new JsonObject(new LinkedHashMap(2));
      obj.put(k1, v1);
      obj.put(k2, v2);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
      JsonObject obj = new JsonObject(new LinkedHashMap(3));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
      JsonObject obj = new JsonObject(new LinkedHashMap(4));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5) {
      JsonObject obj = new JsonObject(new LinkedHashMap(5));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6) {
      JsonObject obj = new JsonObject(new LinkedHashMap(6));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      obj.put(k6, v6);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7) {
      JsonObject obj = new JsonObject(new LinkedHashMap(7));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      obj.put(k6, v6);
      obj.put(k7, v7);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8) {
      JsonObject obj = new JsonObject(new LinkedHashMap(8));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      obj.put(k6, v6);
      obj.put(k7, v7);
      obj.put(k8, v8);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8, String k9, Object v9) {
      JsonObject obj = new JsonObject(new LinkedHashMap(9));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      obj.put(k6, v6);
      obj.put(k7, v7);
      obj.put(k8, v8);
      obj.put(k9, v9);
      return obj;
   }

   public static JsonObject of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8, String k9, Object v9, String k10, Object v10) {
      JsonObject obj = new JsonObject(new LinkedHashMap(10));
      obj.put(k1, v1);
      obj.put(k2, v2);
      obj.put(k3, v3);
      obj.put(k4, v4);
      obj.put(k5, v5);
      obj.put(k6, v6);
      obj.put(k7, v7);
      obj.put(k8, v8);
      obj.put(k9, v9);
      obj.put(k10, v10);
      return obj;
   }

   public static JsonObject mapFrom(Object obj) {
      return obj == null ? null : new JsonObject((Map)Json.CODEC.fromValue(obj, Map.class));
   }

   public Object mapTo(Class type) {
      return Json.CODEC.fromValue(this.map, type);
   }

   public String getString(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val == null) {
         return null;
      } else if (val instanceof Instant) {
         return DateTimeFormatter.ISO_INSTANT.format((Instant)val);
      } else if (val instanceof byte[]) {
         return JsonUtil.BASE64_ENCODER.encodeToString((byte[])val);
      } else if (val instanceof Buffer) {
         return JsonUtil.BASE64_ENCODER.encodeToString(((Buffer)val).getBytes());
      } else {
         return val instanceof Enum ? ((Enum)val).name() : val.toString();
      }
   }

   public Number getNumber(String key) {
      Objects.requireNonNull(key);
      return (Number)this.map.get(key);
   }

   public Integer getInteger(String key) {
      Objects.requireNonNull(key);
      Number number = (Number)this.map.get(key);
      if (number == null) {
         return null;
      } else {
         return number instanceof Integer ? (Integer)number : number.intValue();
      }
   }

   public Long getLong(String key) {
      Objects.requireNonNull(key);
      Number number = (Number)this.map.get(key);
      if (number == null) {
         return null;
      } else {
         return number instanceof Long ? (Long)number : number.longValue();
      }
   }

   public Double getDouble(String key) {
      Objects.requireNonNull(key);
      Number number = (Number)this.map.get(key);
      if (number == null) {
         return null;
      } else {
         return number instanceof Double ? (Double)number : number.doubleValue();
      }
   }

   public Float getFloat(String key) {
      Objects.requireNonNull(key);
      Number number = (Number)this.map.get(key);
      if (number == null) {
         return null;
      } else {
         return number instanceof Float ? (Float)number : number.floatValue();
      }
   }

   public Boolean getBoolean(String key) {
      Objects.requireNonNull(key);
      return (Boolean)this.map.get(key);
   }

   public JsonObject getJsonObject(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val instanceof Map) {
         val = new JsonObject((Map)val);
      }

      return (JsonObject)val;
   }

   public JsonArray getJsonArray(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val instanceof List) {
         val = new JsonArray((List)val);
      }

      return (JsonArray)val;
   }

   public byte[] getBinary(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val == null) {
         return null;
      } else if (val instanceof byte[]) {
         return (byte[])val;
      } else if (val instanceof Buffer) {
         return ((Buffer)val).getBytes();
      } else {
         String encoded = (String)val;
         return JsonUtil.BASE64_DECODER.decode(encoded);
      }
   }

   public Buffer getBuffer(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val == null) {
         return null;
      } else if (val instanceof Buffer) {
         return (Buffer)val;
      } else if (val instanceof byte[]) {
         return Buffer.buffer((byte[])val);
      } else {
         String encoded = (String)val;
         return Buffer.buffer(JsonUtil.BASE64_DECODER.decode(encoded));
      }
   }

   public Instant getInstant(String key) {
      Objects.requireNonNull(key);
      Object val = this.map.get(key);
      if (val == null) {
         return null;
      } else if (val instanceof Instant) {
         return (Instant)val;
      } else {
         String encoded = (String)val;
         return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(encoded));
      }
   }

   public Object getValue(String key) {
      Objects.requireNonNull(key);
      return JsonUtil.wrapJsonValue(this.map.get(key));
   }

   public String getString(String key, String def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getString(key) : def;
   }

   public Number getNumber(String key, Number def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getNumber(key) : def;
   }

   public Integer getInteger(String key, Integer def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getInteger(key) : def;
   }

   public Long getLong(String key, Long def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getLong(key) : def;
   }

   public Double getDouble(String key, Double def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getDouble(key) : def;
   }

   public Float getFloat(String key, Float def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getFloat(key) : def;
   }

   public Boolean getBoolean(String key, Boolean def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getBoolean(key) : def;
   }

   public JsonObject getJsonObject(String key, JsonObject def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getJsonObject(key) : def;
   }

   public JsonArray getJsonArray(String key, JsonArray def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getJsonArray(key) : def;
   }

   public byte[] getBinary(String key, byte[] def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getBinary(key) : def;
   }

   public Buffer getBuffer(String key, Buffer def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getBuffer(key) : def;
   }

   public Instant getInstant(String key, Instant def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getInstant(key) : def;
   }

   public Object getValue(String key, Object def) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key) ? this.getValue(key) : def;
   }

   public boolean containsKey(String key) {
      Objects.requireNonNull(key);
      return this.map.containsKey(key);
   }

   public Set fieldNames() {
      return this.map.keySet();
   }

   public JsonObject putNull(String key) {
      Objects.requireNonNull(key);
      this.map.put(key, (Object)null);
      return this;
   }

   public JsonObject put(String key, Object value) {
      Objects.requireNonNull(key);
      this.map.put(key, value);
      return this;
   }

   public Object remove(String key) {
      Objects.requireNonNull(key);
      return JsonUtil.wrapJsonValue(this.map.remove(key));
   }

   public JsonObject mergeIn(JsonObject other) {
      return this.mergeIn(other, false);
   }

   public JsonObject mergeIn(JsonObject other, boolean deep) {
      return this.mergeIn(other, deep ? Integer.MAX_VALUE : 1);
   }

   public JsonObject mergeIn(JsonObject other, int depth) {
      if (depth < 1) {
         return this;
      } else if (depth == 1) {
         this.map.putAll(other.map);
         return this;
      } else {
         for(Map.Entry e : other.map.entrySet()) {
            if (e.getValue() == null) {
               this.map.put(e.getKey(), (Object)null);
            } else {
               this.map.merge(e.getKey(), e.getValue(), (oldVal, newVal) -> {
                  if (oldVal instanceof Map) {
                     oldVal = new JsonObject((Map)oldVal);
                  }

                  if (newVal instanceof Map) {
                     newVal = new JsonObject((Map)newVal);
                  }

                  return oldVal instanceof JsonObject && newVal instanceof JsonObject ? ((JsonObject)oldVal).mergeIn((JsonObject)newVal, depth - 1) : newVal;
               });
            }
         }

         return this;
      }
   }

   public String encode() {
      return Json.CODEC.toString(this, false);
   }

   public String encodePrettily() {
      return Json.CODEC.toString(this, true);
   }

   public Buffer toBuffer() {
      return Json.CODEC.toBuffer(this, false);
   }

   public JsonObject copy() {
      return this.copy(JsonUtil.DEFAULT_CLONER);
   }

   public JsonObject copy(Function cloner) {
      Map<String, Object> copiedMap;
      if (this.map instanceof LinkedHashMap) {
         copiedMap = new LinkedHashMap(this.map.size());
      } else {
         copiedMap = new HashMap(this.map.size());
      }

      for(Map.Entry entry : this.map.entrySet()) {
         Object val = JsonUtil.deepCopy(entry.getValue(), cloner);
         copiedMap.put(entry.getKey(), val);
      }

      return new JsonObject(copiedMap);
   }

   public Map getMap() {
      return this.map;
   }

   public Stream stream() {
      return JsonUtil.asStream(this.iterator());
   }

   public Iterator iterator() {
      return new Iter(this.map.entrySet().iterator());
   }

   public int size() {
      return this.map.size();
   }

   public JsonObject clear() {
      this.map.clear();
      return this;
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   public String toString() {
      return this.encode();
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (this == o) {
         return true;
      } else if (this.getClass() != o.getClass()) {
         return false;
      } else {
         JsonObject other = (JsonObject)o;
         if (this.size() != other.size()) {
            return false;
         } else {
            for(String key : this.map.keySet()) {
               if (!other.containsKey(key)) {
                  return false;
               }

               Object thisValue = this.getValue(key);
               Object otherValue = other.getValue(key);
               if (thisValue != otherValue && !compareObjects(thisValue, otherValue)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   static boolean compareObjects(Object o1, Object o2) {
      if (o1 instanceof Number && o2 instanceof Number) {
         if (o1.getClass() == o2.getClass()) {
            return o1.equals(o2);
         } else {
            Number n1 = (Number)o1;
            Number n2 = (Number)o2;
            return compareNumbers(n1, n2);
         }
      } else {
         return o1 instanceof CharSequence && o2 instanceof CharSequence && o1.getClass() != o2.getClass() ? Objects.equals(o1.toString(), o2.toString()) : Objects.equals(o1, o2);
      }
   }

   private static boolean compareNumbers(Number n1, Number n2) {
      if (isDecimalNumber(n1) && isDecimalNumber(n2)) {
         return n1.doubleValue() == n2.doubleValue();
      } else if (isWholeNumber(n1) && isWholeNumber(n2)) {
         return n1.longValue() == n2.longValue();
      } else if ((!isWholeNumber(n1) || !isDecimalNumber(n2)) && (!isDecimalNumber(n1) || !isWholeNumber(n2))) {
         if (isWholeNumber(n1)) {
            return n1.longValue() == n2.longValue();
         } else {
            return n1.doubleValue() == n2.doubleValue();
         }
      } else {
         return n1.doubleValue() == n2.doubleValue();
      }
   }

   private static boolean isWholeNumber(Number thisValue) {
      return thisValue instanceof Integer || thisValue instanceof Long;
   }

   private static boolean isDecimalNumber(Number thisValue) {
      return thisValue instanceof Float || thisValue instanceof Double;
   }

   public int hashCode() {
      return this.map.hashCode();
   }

   public void writeToBuffer(Buffer buffer) {
      Buffer buf = this.toBuffer();
      buffer.appendInt(buf.length());
      buffer.appendBuffer(buf);
   }

   public int readFromBuffer(int pos, Buffer buffer) {
      int length = buffer.getInt(pos);
      int start = pos + 4;
      Buffer buf = buffer.getBuffer(start, start + length);
      this.fromBuffer(buf);
      return pos + length + 4;
   }

   private void fromJson(String json) {
      this.map = (Map)Json.CODEC.fromString(json, Map.class);
   }

   private void fromBuffer(Buffer buf) {
      this.map = (Map)Json.CODEC.fromBuffer(buf, Map.class);
   }

   private static class Iter implements Iterator {
      final Iterator mapIter;

      Iter(Iterator mapIter) {
         this.mapIter = mapIter;
      }

      public boolean hasNext() {
         return this.mapIter.hasNext();
      }

      public Map.Entry next() {
         Map.Entry<String, Object> entry = (Map.Entry)this.mapIter.next();
         Object val = entry.getValue();
         Object wrapped = JsonUtil.wrapJsonValue(val);
         return (Map.Entry)(val != wrapped ? new Entry((String)entry.getKey(), wrapped) : entry);
      }

      public void remove() {
         this.mapIter.remove();
      }
   }

   private static final class Entry implements Map.Entry {
      final String key;
      final Object value;

      public Entry(String key, Object value) {
         this.key = key;
         this.value = value;
      }

      public String getKey() {
         return this.key;
      }

      public Object getValue() {
         return this.value;
      }

      public Object setValue(Object value) {
         throw new UnsupportedOperationException();
      }
   }
}
