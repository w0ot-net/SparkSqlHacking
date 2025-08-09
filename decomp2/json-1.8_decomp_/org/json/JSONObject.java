package org.json;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class JSONObject {
   private static final Double NEGATIVE_ZERO = (double)-0.0F;
   public static final Object NULL = new Object() {
      public boolean equals(Object o) {
         return o == this || o == null;
      }

      public int hashCode() {
         return 0;
      }

      public String toString() {
         return "null";
      }
   };
   private final LinkedHashMap nameValuePairs;

   public JSONObject() {
      this.nameValuePairs = new LinkedHashMap();
   }

   public JSONObject(Map copyFrom) {
      this();

      for(Map.Entry entry : copyFrom.entrySet()) {
         String key = (String)entry.getKey();
         if (key == null) {
            throw new NullPointerException("key == null");
         }

         this.nameValuePairs.put(key, wrap(entry.getValue()));
      }

   }

   public JSONObject(JSONTokener readFrom) throws JSONException {
      Object object = readFrom.nextValue();
      if (object instanceof JSONObject) {
         this.nameValuePairs = ((JSONObject)object).nameValuePairs;
      } else {
         throw JSON.typeMismatch(object, "JSONObject");
      }
   }

   public JSONObject(String json) throws JSONException {
      this(new JSONTokener(json));
   }

   public JSONObject(JSONObject copyFrom, String[] names) throws JSONException {
      this();

      for(String name : names) {
         Object value = copyFrom.opt(name);
         if (value != null) {
            this.nameValuePairs.put(name, value);
         }
      }

   }

   public JSONObject(Object bean) throws JSONException {
      this(propertiesAsMap(bean));
   }

   private static Map propertiesAsMap(Object bean) throws JSONException {
      Map<String, Object> props = new TreeMap();

      try {
         PropertyDescriptor[] properties = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();

         for(PropertyDescriptor prop : properties) {
            Object v = prop.getReadMethod().invoke(bean);
            props.put(prop.getDisplayName(), wrap(v));
         }

         return props;
      } catch (IllegalAccessException e) {
         throw new JSONException(e);
      } catch (IntrospectionException e) {
         throw new JSONException(e);
      } catch (InvocationTargetException e) {
         throw new JSONException(e);
      }
   }

   public static String[] getNames(JSONObject x) {
      Set<String> names = x.keySet();
      String[] r = new String[names.size()];
      int i = 0;

      for(String name : names) {
         r[i++] = name;
      }

      return r;
   }

   public int length() {
      return this.nameValuePairs.size();
   }

   public JSONObject put(String name, boolean value) throws JSONException {
      this.nameValuePairs.put(this.checkName(name), value);
      return this;
   }

   public JSONObject put(String name, double value) throws JSONException {
      this.nameValuePairs.put(this.checkName(name), JSON.checkDouble(value));
      return this;
   }

   public JSONObject put(String name, int value) throws JSONException {
      this.nameValuePairs.put(this.checkName(name), value);
      return this;
   }

   public JSONObject put(String name, long value) throws JSONException {
      this.nameValuePairs.put(this.checkName(name), value);
      return this;
   }

   public JSONObject put(String name, Object value) throws JSONException {
      if (value == null) {
         this.nameValuePairs.remove(name);
         return this;
      } else {
         if (value instanceof Number) {
            JSON.checkDouble(((Number)value).doubleValue());
         }

         this.nameValuePairs.put(this.checkName(name), value);
         return this;
      }
   }

   public JSONObject putOpt(String name, Object value) throws JSONException {
      return name != null && value != null ? this.put(name, value) : this;
   }

   public JSONObject accumulate(String name, Object value) throws JSONException {
      Object current = this.nameValuePairs.get(this.checkName(name));
      if (current == null) {
         return this.put(name, value);
      } else {
         if (current instanceof JSONArray) {
            JSONArray array = (JSONArray)current;
            array.checkedPut(value);
         } else {
            JSONArray array = new JSONArray();
            array.checkedPut(current);
            array.checkedPut(value);
            this.nameValuePairs.put(name, array);
         }

         return this;
      }
   }

   public JSONObject append(String name, Object value) throws JSONException {
      Object current = this.nameValuePairs.get(this.checkName(name));
      JSONArray array;
      if (current instanceof JSONArray) {
         array = (JSONArray)current;
      } else {
         if (current != null) {
            throw new JSONException("Key " + name + " is not a JSONArray");
         }

         JSONArray newArray = new JSONArray();
         this.nameValuePairs.put(name, newArray);
         array = newArray;
      }

      array.checkedPut(value);
      return this;
   }

   String checkName(String name) throws JSONException {
      if (name == null) {
         throw new JSONException("Names must be non-null");
      } else {
         return name;
      }
   }

   public Object remove(String name) {
      return this.nameValuePairs.remove(name);
   }

   public boolean isNull(String name) {
      Object value = this.nameValuePairs.get(name);
      return value == null || value == NULL;
   }

   public boolean has(String name) {
      return this.nameValuePairs.containsKey(name);
   }

   public Object get(String name) throws JSONException {
      Object result = this.nameValuePairs.get(name);
      if (result == null) {
         throw new JSONException("No value for " + name);
      } else {
         return result;
      }
   }

   public Object opt(String name) {
      return this.nameValuePairs.get(name);
   }

   public boolean getBoolean(String name) throws JSONException {
      Object object = this.get(name);
      Boolean result = JSON.toBoolean(object);
      if (result == null) {
         throw JSON.typeMismatch(name, object, "boolean");
      } else {
         return result;
      }
   }

   public boolean optBoolean(String name) {
      return this.optBoolean(name, false);
   }

   public boolean optBoolean(String name, boolean fallback) {
      Object object = this.opt(name);
      Boolean result = JSON.toBoolean(object);
      return result != null ? result : fallback;
   }

   public double getDouble(String name) throws JSONException {
      Object object = this.get(name);
      Double result = JSON.toDouble(object);
      if (result == null) {
         throw JSON.typeMismatch(name, object, "double");
      } else {
         return result;
      }
   }

   public double optDouble(String name) {
      return this.optDouble(name, Double.NaN);
   }

   public double optDouble(String name, double fallback) {
      Object object = this.opt(name);
      Double result = JSON.toDouble(object);
      return result != null ? result : fallback;
   }

   public int getInt(String name) throws JSONException {
      Object object = this.get(name);
      Integer result = JSON.toInteger(object);
      if (result == null) {
         throw JSON.typeMismatch(name, object, "int");
      } else {
         return result;
      }
   }

   public int optInt(String name) {
      return this.optInt(name, 0);
   }

   public int optInt(String name, int fallback) {
      Object object = this.opt(name);
      Integer result = JSON.toInteger(object);
      return result != null ? result : fallback;
   }

   public long getLong(String name) throws JSONException {
      Object object = this.get(name);
      Long result = JSON.toLong(object);
      if (result == null) {
         throw JSON.typeMismatch(name, object, "long");
      } else {
         return result;
      }
   }

   public long optLong(String name) {
      return this.optLong(name, 0L);
   }

   public long optLong(String name, long fallback) {
      Object object = this.opt(name);
      Long result = JSON.toLong(object);
      return result != null ? result : fallback;
   }

   public String getString(String name) throws JSONException {
      Object object = this.get(name);
      String result = JSON.toString(object);
      if (result == null) {
         throw JSON.typeMismatch(name, object, "String");
      } else {
         return result;
      }
   }

   public String optString(String name) {
      return this.optString(name, "");
   }

   public String optString(String name, String fallback) {
      Object object = this.opt(name);
      String result = JSON.toString(object);
      return result != null ? result : fallback;
   }

   public JSONArray getJSONArray(String name) throws JSONException {
      Object object = this.get(name);
      if (object instanceof JSONArray) {
         return (JSONArray)object;
      } else {
         throw JSON.typeMismatch(name, object, "JSONArray");
      }
   }

   public JSONArray optJSONArray(String name) {
      Object object = this.opt(name);
      return object instanceof JSONArray ? (JSONArray)object : null;
   }

   public JSONObject getJSONObject(String name) throws JSONException {
      Object object = this.get(name);
      if (object instanceof JSONObject) {
         return (JSONObject)object;
      } else {
         throw JSON.typeMismatch(name, object, "JSONObject");
      }
   }

   public JSONObject optJSONObject(String name) {
      Object object = this.opt(name);
      return object instanceof JSONObject ? (JSONObject)object : null;
   }

   public JSONArray toJSONArray(JSONArray names) throws JSONException {
      JSONArray result = new JSONArray();
      if (names == null) {
         return null;
      } else {
         int length = names.length();
         if (length == 0) {
            return null;
         } else {
            for(int i = 0; i < length; ++i) {
               String name = JSON.toString(names.opt(i));
               result.put(this.opt(name));
            }

            return result;
         }
      }
   }

   public Iterator keys() {
      return this.nameValuePairs.keySet().iterator();
   }

   public Set keySet() {
      return this.nameValuePairs.keySet();
   }

   public JSONArray names() {
      return this.nameValuePairs.isEmpty() ? null : new JSONArray(new ArrayList(this.nameValuePairs.keySet()));
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
      stringer.object();

      for(Map.Entry entry : this.nameValuePairs.entrySet()) {
         stringer.key((String)entry.getKey()).value(entry.getValue());
      }

      stringer.endObject();
   }

   public static String numberToString(Number number) throws JSONException {
      if (number == null) {
         throw new JSONException("Number must be non-null");
      } else {
         double doubleValue = number.doubleValue();
         JSON.checkDouble(doubleValue);
         if (number.equals(NEGATIVE_ZERO)) {
            return "-0";
         } else {
            long longValue = number.longValue();
            return doubleValue == (double)longValue ? Long.toString(longValue) : number.toString();
         }
      }
   }

   public static String quote(String data) {
      if (data == null) {
         return "\"\"";
      } else {
         try {
            JSONStringer stringer = new JSONStringer();
            stringer.open(JSONStringer.Scope.NULL, "");
            stringer.value(data);
            stringer.close(JSONStringer.Scope.NULL, JSONStringer.Scope.NULL, "");
            return stringer.toString();
         } catch (JSONException var2) {
            throw new AssertionError();
         }
      }
   }

   public static Object wrap(Object o) {
      if (o == null) {
         return NULL;
      } else if (!(o instanceof JSONArray) && !(o instanceof JSONObject)) {
         if (o.equals(NULL)) {
            return o;
         } else {
            try {
               if (o instanceof Collection) {
                  return new JSONArray((Collection)o);
               } else if (o.getClass().isArray()) {
                  return new JSONArray(o);
               } else if (o instanceof Map) {
                  return new JSONObject((Map)o);
               } else if (!(o instanceof Boolean) && !(o instanceof Byte) && !(o instanceof Character) && !(o instanceof Double) && !(o instanceof Float) && !(o instanceof Integer) && !(o instanceof Long) && !(o instanceof Short) && !(o instanceof String)) {
                  return !o.getClass().getPackage().getName().startsWith("java.") && !(o instanceof Enum) ? new JSONObject(o) : o.toString();
               } else {
                  return o;
               }
            } catch (Exception var2) {
               return null;
            }
         }
      } else {
         return o;
      }
   }
}
