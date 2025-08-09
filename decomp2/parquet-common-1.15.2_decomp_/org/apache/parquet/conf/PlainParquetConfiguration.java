package org.apache.parquet.conf;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PlainParquetConfiguration implements ParquetConfiguration {
   private final Map map;

   public PlainParquetConfiguration() {
      this.map = new HashMap();
   }

   public PlainParquetConfiguration(Map properties) {
      this.map = new HashMap(properties);
   }

   public void set(String s, String s1) {
      this.map.put(s, s1);
   }

   public void setLong(String name, long value) {
      this.set(name, String.valueOf(value));
   }

   public void setInt(String name, int value) {
      this.set(name, String.valueOf(value));
   }

   public void setBoolean(String name, boolean value) {
      this.set(name, String.valueOf(value));
   }

   public void setStrings(String name, String... value) {
      if (value.length > 0) {
         StringBuilder sb = new StringBuilder(value[0]);

         for(int i = 1; i < value.length; ++i) {
            sb.append(',');
            sb.append(value[i]);
         }

         this.set(name, sb.toString());
      } else {
         this.set(name, "");
      }

   }

   public void setClass(String name, Class value, Class xface) {
      if (xface.isAssignableFrom(value)) {
         this.set(name, value.getName());
      } else {
         throw new RuntimeException(xface.getCanonicalName() + " is not assignable from " + value.getCanonicalName());
      }
   }

   public String get(String name) {
      return (String)this.map.get(name);
   }

   public String get(String name, String defaultValue) {
      String value = this.get(name);
      return value != null ? value : defaultValue;
   }

   public long getLong(String name, long defaultValue) {
      String value = this.get(name);
      return value != null ? Long.parseLong(value) : defaultValue;
   }

   public int getInt(String name, int defaultValue) {
      String value = this.get(name);
      return value != null ? Integer.parseInt(value) : defaultValue;
   }

   public boolean getBoolean(String name, boolean defaultValue) {
      String value = this.get(name);
      return value != null ? Boolean.parseBoolean(value) : defaultValue;
   }

   public String getTrimmed(String name) {
      String value = this.get(name);
      return value != null ? value.trim() : null;
   }

   public String getTrimmed(String name, String defaultValue) {
      String value = this.get(name);
      return value != null ? value.trim() : defaultValue;
   }

   public String[] getStrings(String name, String[] defaultValue) {
      String value = this.get(name);
      return value != null ? value.split(",") : defaultValue;
   }

   public Class getClass(String name, Class defaultValue) {
      String value = this.get(name);
      if (value != null) {
         try {
            return Class.forName(value);
         } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
         }
      } else {
         return defaultValue;
      }
   }

   public Class getClass(String name, Class defaultValue, Class xface) {
      Class<?> value = this.getClass(name, defaultValue);
      return value != null && value.isAssignableFrom(xface) ? value : defaultValue;
   }

   public Class getClassByName(String name) throws ClassNotFoundException {
      return Class.forName(name);
   }

   public Iterator iterator() {
      return this.map.entrySet().iterator();
   }
}
