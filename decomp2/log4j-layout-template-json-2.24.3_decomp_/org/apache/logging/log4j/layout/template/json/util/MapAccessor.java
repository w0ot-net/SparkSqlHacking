package org.apache.logging.log4j.layout.template.json.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapAccessor {
   private final Map map;

   public MapAccessor(final Map map) {
      this.map = (Map)Objects.requireNonNull(map, "map");
   }

   public String getString(final String key) {
      String[] path = new String[]{key};
      return (String)this.getObject(path, String.class);
   }

   public String getString(final String[] path) {
      return (String)this.getObject(path, String.class);
   }

   public boolean getBoolean(final String key, final boolean defaultValue) {
      String[] path = new String[]{key};
      return this.getBoolean(path, defaultValue);
   }

   public boolean getBoolean(final String[] path, final boolean defaultValue) {
      Boolean value = (Boolean)this.getObject(path, Boolean.class);
      return value == null ? defaultValue : value;
   }

   public Boolean getBoolean(final String key) {
      String[] path = new String[]{key};
      return (Boolean)this.getObject(path, Boolean.class);
   }

   public Boolean getBoolean(final String[] path) {
      return (Boolean)this.getObject(path, Boolean.class);
   }

   public Integer getInteger(final String key) {
      String[] path = new String[]{key};
      return this.getInteger(path);
   }

   public Integer getInteger(final String[] path) {
      return (Integer)this.getObject(path, Integer.class);
   }

   public boolean exists(final String key) {
      String[] path = new String[]{key};
      return this.exists(path);
   }

   public boolean exists(final String[] path) {
      Object value = this.getObject(path);
      return value != null;
   }

   public List getList(final String key, final Class clazz) {
      String[] path = new String[]{key};
      return this.getList(path, clazz);
   }

   public List getList(final String[] path, final Class clazz) {
      Object value = this.getObject(path);
      if (value == null) {
         return null;
      } else if (!(value instanceof List)) {
         String message = String.format("was expecting a List<%s> at path %s: %s (of type %s)", clazz, Arrays.asList(path), value, value.getClass().getCanonicalName());
         throw new IllegalArgumentException(message);
      } else {
         List<Object> items = (List)value;

         for(int itemIndex = 0; itemIndex < items.size(); ++itemIndex) {
            Object item = items.get(itemIndex);
            if (!clazz.isInstance(item)) {
               String message = String.format("was expecting a List<%s> item at path %s and index %d: %s (of type %s)", clazz, Arrays.asList(path), itemIndex, item, item != null ? item.getClass().getCanonicalName() : null);
               throw new IllegalArgumentException(message);
            }
         }

         return items;
      }
   }

   public Object getObject(final String key) {
      String[] path = new String[]{key};
      return this.getObject(path, Object.class);
   }

   public Object getObject(final String key, final Class clazz) {
      String[] path = new String[]{key};
      return this.getObject(path, clazz);
   }

   public Object getObject(final String[] path) {
      return this.getObject(path, Object.class);
   }

   public Object getObject(final String[] path, final Class clazz) {
      Objects.requireNonNull(path, "path");
      Objects.requireNonNull(clazz, "clazz");
      if (path.length == 0) {
         throw new IllegalArgumentException("empty path");
      } else {
         Object parent = this.map;

         for(String key : path) {
            if (!(parent instanceof Map)) {
               return null;
            }

            Map<String, Object> parentMap = (Map)parent;
            parent = parentMap.get(key);
         }

         if (parent != null && !clazz.isInstance(parent)) {
            String message = String.format("was expecting %s at path %s: %s (of type %s)", clazz.getSimpleName(), Arrays.asList(path), parent, parent.getClass().getCanonicalName());
            throw new IllegalArgumentException(message);
         } else {
            return parent;
         }
      }
   }

   public boolean equals(final Object instance) {
      if (this == instance) {
         return true;
      } else if (!(instance instanceof MapAccessor)) {
         return false;
      } else {
         MapAccessor that = (MapAccessor)instance;
         return this.map.equals(that.map);
      }
   }

   public int hashCode() {
      return 31 + Objects.hashCode(this.map);
   }

   public String toString() {
      return this.map.toString();
   }
}
