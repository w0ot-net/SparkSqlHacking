package org.json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JSONArray {
   private final List values;

   public JSONArray() {
      this.values = new ArrayList();
   }

   public JSONArray(Collection copyFrom) {
      this();
      if (copyFrom != null) {
         for(Object aCopyFrom : copyFrom) {
            this.put(JSONObject.wrap(aCopyFrom));
         }
      }

   }

   public JSONArray(JSONTokener readFrom) throws JSONException {
      Object object = readFrom.nextValue();
      if (object instanceof JSONArray) {
         this.values = ((JSONArray)object).values;
      } else {
         throw JSON.typeMismatch(object, "JSONArray");
      }
   }

   public JSONArray(String json) throws JSONException {
      this(new JSONTokener(json));
   }

   public JSONArray(Object array) throws JSONException {
      if (!array.getClass().isArray()) {
         throw new JSONException("Not a primitive array: " + array.getClass());
      } else {
         int length = Array.getLength(array);
         this.values = new ArrayList(length);

         for(int i = 0; i < length; ++i) {
            this.put(JSONObject.wrap(Array.get(array, i)));
         }

      }
   }

   public int length() {
      return this.values.size();
   }

   public JSONArray put(boolean value) {
      this.values.add(value);
      return this;
   }

   public JSONArray put(double value) throws JSONException {
      this.values.add(JSON.checkDouble(value));
      return this;
   }

   public JSONArray put(int value) {
      this.values.add(value);
      return this;
   }

   public JSONArray put(long value) {
      this.values.add(value);
      return this;
   }

   public JSONArray put(Collection value) {
      if (value == null) {
         return this.put((Object)null);
      } else {
         this.values.add(new JSONArray(value));
         return this;
      }
   }

   public JSONArray put(Object value) {
      this.values.add(value);
      return this;
   }

   void checkedPut(Object value) throws JSONException {
      if (value instanceof Number) {
         JSON.checkDouble(((Number)value).doubleValue());
      }

      this.put(value);
   }

   public JSONArray put(int index, boolean value) throws JSONException {
      return this.put(index, (Object)value);
   }

   public JSONArray put(int index, double value) throws JSONException {
      return this.put(index, (Object)value);
   }

   public JSONArray put(int index, int value) throws JSONException {
      return this.put(index, (Object)value);
   }

   public JSONArray put(int index, long value) throws JSONException {
      return this.put(index, (Object)value);
   }

   public JSONArray put(int index, Collection value) throws JSONException {
      return value == null ? this.put(index, (Object)null) : this.put(index, (Object)(new JSONArray(value)));
   }

   public JSONArray put(int index, Object value) throws JSONException {
      if (value instanceof Number) {
         JSON.checkDouble(((Number)value).doubleValue());
      }

      while(this.values.size() <= index) {
         this.values.add((Object)null);
      }

      this.values.set(index, value);
      return this;
   }

   public boolean isNull(int index) {
      Object value = this.opt(index);
      return value == null || value == JSONObject.NULL;
   }

   public Object get(int index) throws JSONException {
      try {
         Object value = this.values.get(index);
         if (value == null) {
            throw new JSONException("Value at " + index + " is null.");
         } else {
            return value;
         }
      } catch (IndexOutOfBoundsException var3) {
         throw new JSONException("Index " + index + " out of range [0.." + this.values.size() + ")");
      }
   }

   public Object opt(int index) {
      return index >= 0 && index < this.values.size() ? this.values.get(index) : null;
   }

   public Object remove(int index) {
      return index >= 0 && index < this.values.size() ? this.values.remove(index) : null;
   }

   public boolean getBoolean(int index) throws JSONException {
      Object object = this.get(index);
      Boolean result = JSON.toBoolean(object);
      if (result == null) {
         throw JSON.typeMismatch(index, object, "boolean");
      } else {
         return result;
      }
   }

   public boolean optBoolean(int index) {
      return this.optBoolean(index, false);
   }

   public boolean optBoolean(int index, boolean fallback) {
      Object object = this.opt(index);
      Boolean result = JSON.toBoolean(object);
      return result != null ? result : fallback;
   }

   public double getDouble(int index) throws JSONException {
      Object object = this.get(index);
      Double result = JSON.toDouble(object);
      if (result == null) {
         throw JSON.typeMismatch(index, object, "double");
      } else {
         return result;
      }
   }

   public double optDouble(int index) {
      return this.optDouble(index, Double.NaN);
   }

   public double optDouble(int index, double fallback) {
      Object object = this.opt(index);
      Double result = JSON.toDouble(object);
      return result != null ? result : fallback;
   }

   public int getInt(int index) throws JSONException {
      Object object = this.get(index);
      Integer result = JSON.toInteger(object);
      if (result == null) {
         throw JSON.typeMismatch(index, object, "int");
      } else {
         return result;
      }
   }

   public int optInt(int index) {
      return this.optInt(index, 0);
   }

   public int optInt(int index, int fallback) {
      Object object = this.opt(index);
      Integer result = JSON.toInteger(object);
      return result != null ? result : fallback;
   }

   public long getLong(int index) throws JSONException {
      Object object = this.get(index);
      Long result = JSON.toLong(object);
      if (result == null) {
         throw JSON.typeMismatch(index, object, "long");
      } else {
         return result;
      }
   }

   public long optLong(int index) {
      return this.optLong(index, 0L);
   }

   public long optLong(int index, long fallback) {
      Object object = this.opt(index);
      Long result = JSON.toLong(object);
      return result != null ? result : fallback;
   }

   public String getString(int index) throws JSONException {
      Object object = this.get(index);
      String result = JSON.toString(object);
      if (result == null) {
         throw JSON.typeMismatch(index, object, "String");
      } else {
         return result;
      }
   }

   public String optString(int index) {
      return this.optString(index, "");
   }

   public String optString(int index, String fallback) {
      Object object = this.opt(index);
      String result = JSON.toString(object);
      return result != null ? result : fallback;
   }

   public JSONArray getJSONArray(int index) throws JSONException {
      Object object = this.get(index);
      if (object instanceof JSONArray) {
         return (JSONArray)object;
      } else {
         throw JSON.typeMismatch(index, object, "JSONArray");
      }
   }

   public JSONArray optJSONArray(int index) {
      Object object = this.opt(index);
      return object instanceof JSONArray ? (JSONArray)object : null;
   }

   public JSONObject getJSONObject(int index) throws JSONException {
      Object object = this.get(index);
      if (object instanceof JSONObject) {
         return (JSONObject)object;
      } else {
         throw JSON.typeMismatch(index, object, "JSONObject");
      }
   }

   public JSONObject optJSONObject(int index) {
      Object object = this.opt(index);
      return object instanceof JSONObject ? (JSONObject)object : null;
   }

   public JSONObject toJSONObject(JSONArray names) throws JSONException {
      JSONObject result = new JSONObject();
      int length = Math.min(names.length(), this.values.size());
      if (length == 0) {
         return null;
      } else {
         for(int i = 0; i < length; ++i) {
            String name = JSON.toString(names.opt(i));
            result.put(name, this.opt(i));
         }

         return result;
      }
   }

   public String join(String separator) throws JSONException {
      JSONStringer stringer = new JSONStringer();
      stringer.open(JSONStringer.Scope.NULL, "");
      int i = 0;

      for(int size = this.values.size(); i < size; ++i) {
         if (i > 0) {
            stringer.out.append(separator);
         }

         stringer.value(this.values.get(i));
      }

      stringer.close(JSONStringer.Scope.NULL, JSONStringer.Scope.NULL, "");
      return stringer.out.toString();
   }

   public String toString() {
      try {
         JSONStringer stringer = new JSONStringer();
         this.writeTo(stringer);
         return stringer.toString();
      } catch (JSONException var2) {
         return null;
      }
   }

   public String toString(int indentSpaces) throws JSONException {
      JSONStringer stringer = new JSONStringer(indentSpaces);
      this.writeTo(stringer);
      return stringer.toString();
   }

   void writeTo(JSONStringer stringer) throws JSONException {
      stringer.array();

      for(Object value : this.values) {
         stringer.value(value);
      }

      stringer.endArray();
   }

   public boolean equals(Object o) {
      return o instanceof JSONArray && ((JSONArray)o).values.equals(this.values);
   }

   public int hashCode() {
      return this.values.hashCode();
   }
}
