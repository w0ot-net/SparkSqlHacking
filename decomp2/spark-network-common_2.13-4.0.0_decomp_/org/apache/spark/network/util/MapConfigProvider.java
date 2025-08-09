package org.apache.spark.network.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class MapConfigProvider extends ConfigProvider {
   public static final MapConfigProvider EMPTY = new MapConfigProvider(Collections.emptyMap());
   private final Map config;

   public MapConfigProvider(Map config) {
      this.config = new HashMap(config);
   }

   public String get(String name) {
      String value = (String)this.config.get(name);
      if (value == null) {
         throw new NoSuchElementException(name);
      } else {
         return value;
      }
   }

   public String get(String name, String defaultValue) {
      String value = (String)this.config.get(name);
      return value == null ? defaultValue : value;
   }

   public Iterable getAll() {
      return this.config.entrySet();
   }
}
