package org.apache.spark.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import scala.Function1;

public final class JsonUtils$ implements JsonUtils {
   public static final JsonUtils$ MODULE$ = new JsonUtils$();
   private static ObjectMapper mapper;

   static {
      JsonUtils.$init$(MODULE$);
   }

   public String toJsonString(final Function1 block) {
      return JsonUtils.toJsonString$(this, block);
   }

   public ObjectMapper mapper() {
      return mapper;
   }

   public void org$apache$spark$util$JsonUtils$_setter_$mapper_$eq(final ObjectMapper x$1) {
      mapper = x$1;
   }

   private JsonUtils$() {
   }
}
