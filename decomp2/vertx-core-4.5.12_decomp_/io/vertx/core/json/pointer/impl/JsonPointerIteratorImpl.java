package io.vertx.core.json.pointer.impl;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.pointer.JsonPointerIterator;
import java.util.List;
import java.util.Map;

public class JsonPointerIteratorImpl implements JsonPointerIterator {
   public boolean isObject(Object value) {
      return value instanceof JsonObject;
   }

   public boolean isArray(Object value) {
      return value instanceof JsonArray;
   }

   public boolean isNull(Object value) {
      return value == null;
   }

   public boolean objectContainsKey(Object value, String key) {
      return this.isObject(value) && ((JsonObject)value).containsKey(key);
   }

   public Object getObjectParameter(Object value, String key, boolean createOnMissing) {
      if (this.isObject(value)) {
         if (!this.objectContainsKey(value, key)) {
            if (!createOnMissing) {
               return null;
            }

            this.writeObjectParameter(value, key, new JsonObject());
         }

         return this.jsonifyValue(((JsonObject)value).getValue(key));
      } else {
         return null;
      }
   }

   public Object getArrayElement(Object value, int i) {
      if (this.isArray(value)) {
         try {
            return this.jsonifyValue(((JsonArray)value).getValue(i));
         } catch (IndexOutOfBoundsException var4) {
         }
      }

      return null;
   }

   public boolean writeObjectParameter(Object value, String key, Object el) {
      if (this.isObject(value)) {
         ((JsonObject)value).put(key, el);
         return true;
      } else {
         return false;
      }
   }

   public boolean writeArrayElement(Object value, int i, Object el) {
      if (this.isArray(value)) {
         try {
            ((JsonArray)value).getList().add(i, el);
            return true;
         } catch (IndexOutOfBoundsException var5) {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean appendArrayElement(Object value, Object el) {
      if (this.isArray(value)) {
         ((JsonArray)value).add(el);
         return true;
      } else {
         return false;
      }
   }

   private Object jsonifyValue(Object v) {
      if (v instanceof Map) {
         return new JsonObject((Map)v);
      } else {
         return v instanceof List ? new JsonArray((List)v) : v;
      }
   }
}
