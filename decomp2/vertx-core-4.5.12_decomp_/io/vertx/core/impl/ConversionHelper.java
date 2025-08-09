package io.vertx.core.impl;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.impl.JsonUtil;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConversionHelper {
   public static Object toObject(Object obj) {
      if (obj instanceof Map) {
         return toJsonObject((Map)obj);
      } else if (obj instanceof List) {
         return toJsonArray((List)obj);
      } else {
         return obj instanceof CharSequence ? obj.toString() : obj;
      }
   }

   private static Object toJsonElement(Object obj) {
      if (obj instanceof Map) {
         return toJsonObject((Map)obj);
      } else if (obj instanceof List) {
         return toJsonArray((List)obj);
      } else if (obj instanceof CharSequence) {
         return obj.toString();
      } else {
         return obj instanceof Buffer ? JsonUtil.BASE64_ENCODER.encodeToString(((Buffer)obj).getBytes()) : obj;
      }
   }

   public static JsonObject toJsonObject(Map map) {
      if (map == null) {
         return null;
      } else {
         Map var1 = new LinkedHashMap(map);
         var1.entrySet().forEach((e) -> e.setValue(toJsonElement(e.getValue())));
         return new JsonObject(var1);
      }
   }

   public static JsonArray toJsonArray(List list) {
      if (list == null) {
         return null;
      } else {
         list = new ArrayList(list);

         for(int i = 0; i < list.size(); ++i) {
            list.set(i, toJsonElement(list.get(i)));
         }

         return new JsonArray(list);
      }
   }

   public static Object fromObject(Object obj) {
      if (obj instanceof JsonObject) {
         return fromJsonObject((JsonObject)obj);
      } else if (obj instanceof JsonArray) {
         return fromJsonArray((JsonArray)obj);
      } else if (obj instanceof Instant) {
         return DateTimeFormatter.ISO_INSTANT.format((Instant)obj);
      } else if (obj instanceof byte[]) {
         return JsonUtil.BASE64_ENCODER.encodeToString((byte[])obj);
      } else {
         return obj instanceof Enum ? ((Enum)obj).name() : obj;
      }
   }

   public static Map fromJsonObject(JsonObject json) {
      if (json == null) {
         return null;
      } else {
         Map<String, Object> map = new LinkedHashMap(json.getMap());
         map.entrySet().forEach((entry) -> entry.setValue(fromObject(entry.getValue())));
         return map;
      }
   }

   public static List fromJsonArray(JsonArray json) {
      if (json == null) {
         return null;
      } else {
         List<Object> list = new ArrayList(json.getList());

         for(int i = 0; i < list.size(); ++i) {
            list.set(i, fromObject(list.get(i)));
         }

         return list;
      }
   }
}
