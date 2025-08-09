package io.vertx.core.json;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.impl.JsonUtil;
import io.vertx.core.shareddata.ClusterSerializable;
import io.vertx.core.shareddata.Shareable;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public class JsonArray implements Iterable, ClusterSerializable, Shareable {
   private List list;

   public JsonArray(String json) {
      if (json == null) {
         throw new NullPointerException();
      } else {
         this.fromJson(json);
         if (this.list == null) {
            throw new DecodeException("Invalid JSON array: " + json);
         }
      }
   }

   public JsonArray() {
      this.list = new ArrayList();
   }

   public JsonArray(List list) {
      if (list == null) {
         throw new NullPointerException();
      } else {
         this.list = list;
      }
   }

   public JsonArray(Buffer buf) {
      if (buf == null) {
         throw new NullPointerException();
      } else {
         this.fromBuffer(buf);
         if (this.list == null) {
            throw new DecodeException("Invalid JSON array: " + buf);
         }
      }
   }

   public static JsonArray of(Object... values) {
      if (values.length == 0) {
         return new JsonArray();
      } else {
         JsonArray arr = new JsonArray(new ArrayList(values.length));

         for(int i = 0; i < values.length; ++i) {
            arr.add(values[i]);
         }

         return arr;
      }
   }

   public String getString(int pos) {
      Object val = this.list.get(pos);
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

   public Number getNumber(int pos) {
      return (Number)this.list.get(pos);
   }

   public Integer getInteger(int pos) {
      Number number = (Number)this.list.get(pos);
      if (number == null) {
         return null;
      } else {
         return number instanceof Integer ? (Integer)number : number.intValue();
      }
   }

   public Long getLong(int pos) {
      Number number = (Number)this.list.get(pos);
      if (number == null) {
         return null;
      } else {
         return number instanceof Long ? (Long)number : number.longValue();
      }
   }

   public Double getDouble(int pos) {
      Number number = (Number)this.list.get(pos);
      if (number == null) {
         return null;
      } else {
         return number instanceof Double ? (Double)number : number.doubleValue();
      }
   }

   public Float getFloat(int pos) {
      Number number = (Number)this.list.get(pos);
      if (number == null) {
         return null;
      } else {
         return number instanceof Float ? (Float)number : number.floatValue();
      }
   }

   public Boolean getBoolean(int pos) {
      return (Boolean)this.list.get(pos);
   }

   public JsonObject getJsonObject(int pos) {
      Object val = this.list.get(pos);
      if (val instanceof Map) {
         val = new JsonObject((Map)val);
      }

      return (JsonObject)val;
   }

   public JsonArray getJsonArray(int pos) {
      Object val = this.list.get(pos);
      if (val instanceof List) {
         val = new JsonArray((List)val);
      }

      return (JsonArray)val;
   }

   public byte[] getBinary(int pos) {
      Object val = this.list.get(pos);
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

   public Buffer getBuffer(int pos) {
      Object val = this.list.get(pos);
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

   public Instant getInstant(int pos) {
      Object val = this.list.get(pos);
      if (val == null) {
         return null;
      } else if (val instanceof Instant) {
         return (Instant)val;
      } else {
         String encoded = (String)val;
         return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(encoded));
      }
   }

   public Object getValue(int pos) {
      return JsonUtil.wrapJsonValue(this.list.get(pos));
   }

   public boolean hasNull(int pos) {
      return this.list.get(pos) == null;
   }

   public JsonArray addNull() {
      this.list.add((Object)null);
      return this;
   }

   public JsonArray add(Object value) {
      this.list.add(value);
      return this;
   }

   public JsonArray add(int pos, Object value) {
      this.list.add(pos, value);
      return this;
   }

   public JsonArray addAll(JsonArray array) {
      this.list.addAll(array.list);
      return this;
   }

   public JsonArray setNull(int pos) {
      this.list.set(pos, (Object)null);
      return this;
   }

   public JsonArray set(int pos, Object value) {
      this.list.set(pos, value);
      return this;
   }

   public boolean contains(Object value) {
      return this.indexOf(value) >= 0;
   }

   public int indexOf(Object value) {
      if (value instanceof JsonObject) {
         return this.indexOfFirst(value, ((JsonObject)value).getMap());
      } else {
         return value instanceof JsonArray ? this.indexOfFirst(value, ((JsonArray)value).getList()) : this.list.indexOf(value);
      }
   }

   private int indexOfFirst(Object value, Object value2) {
      for(int i = 0; i < this.list.size(); ++i) {
         Object entry = this.list.get(i);
         if (value.equals(entry) || value2.equals(entry)) {
            return i;
         }
      }

      return -1;
   }

   public boolean remove(Object value) {
      Object wrappedValue = JsonUtil.wrapJsonValue(value);

      for(int i = 0; i < this.list.size(); ++i) {
         Object otherWrapperValue = this.getValue(i);
         if (wrappedValue == null) {
            if (otherWrapperValue == null) {
               this.list.remove(i);
               return true;
            }
         } else if (wrappedValue.equals(otherWrapperValue)) {
            this.list.remove(i);
            return true;
         }
      }

      return false;
   }

   public Object remove(int pos) {
      return JsonUtil.wrapJsonValue(this.list.remove(pos));
   }

   public int size() {
      return this.list.size();
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public List getList() {
      return this.list;
   }

   public JsonArray clear() {
      this.list.clear();
      return this;
   }

   public Iterator iterator() {
      return new Iter(this.list.iterator());
   }

   public String encode() {
      return Json.CODEC.toString(this, false);
   }

   public Buffer toBuffer() {
      return Json.CODEC.toBuffer(this, false);
   }

   public String encodePrettily() {
      return Json.CODEC.toString(this, true);
   }

   public JsonArray copy() {
      return this.copy(JsonUtil.DEFAULT_CLONER);
   }

   public JsonArray copy(Function cloner) {
      List<Object> copiedList = new ArrayList(this.list.size());

      for(Object val : this.list) {
         copiedList.add(JsonUtil.deepCopy(val, cloner));
      }

      return new JsonArray(copiedList);
   }

   public Stream stream() {
      return JsonUtil.asStream(this.iterator());
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
         JsonArray other = (JsonArray)o;
         if (this.size() != other.size()) {
            return false;
         } else {
            for(int i = 0; i < this.size(); ++i) {
               Object thisValue = this.getValue(i);
               Object otherValue = other.getValue(i);
               if (thisValue != otherValue && !JsonObject.compareObjects(thisValue, otherValue)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public int hashCode() {
      return this.list.hashCode();
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
      this.list = (List)Json.CODEC.fromString(json, List.class);
   }

   private void fromBuffer(Buffer buf) {
      this.list = (List)Json.CODEC.fromBuffer(buf, List.class);
   }

   private static class Iter implements Iterator {
      final Iterator listIter;

      Iter(Iterator listIter) {
         this.listIter = listIter;
      }

      public boolean hasNext() {
         return this.listIter.hasNext();
      }

      public Object next() {
         return JsonUtil.wrapJsonValue(this.listIter.next());
      }

      public void remove() {
         this.listIter.remove();
      }
   }
}
