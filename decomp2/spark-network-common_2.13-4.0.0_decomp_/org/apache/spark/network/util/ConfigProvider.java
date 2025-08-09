package org.apache.spark.network.util;

import java.util.NoSuchElementException;

public abstract class ConfigProvider {
   public abstract String get(String var1);

   public abstract Iterable getAll();

   public String get(String name, String defaultValue) {
      try {
         return this.get(name);
      } catch (NoSuchElementException var4) {
         return defaultValue;
      }
   }

   public int getInt(String name, int defaultValue) {
      return Integer.parseInt(this.get(name, Integer.toString(defaultValue)));
   }

   public long getLong(String name, long defaultValue) {
      return Long.parseLong(this.get(name, Long.toString(defaultValue)));
   }

   public double getDouble(String name, double defaultValue) {
      return Double.parseDouble(this.get(name, Double.toString(defaultValue)));
   }

   public boolean getBoolean(String name, boolean defaultValue) {
      return Boolean.parseBoolean(this.get(name, Boolean.toString(defaultValue)));
   }
}
