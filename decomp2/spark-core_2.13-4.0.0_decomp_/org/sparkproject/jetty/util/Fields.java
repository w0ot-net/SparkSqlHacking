package org.sparkproject.jetty.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Fields implements Iterable {
   private final boolean caseSensitive;
   private final Map fields;

   public Fields() {
      this(false);
   }

   public Fields(boolean caseSensitive) {
      this.caseSensitive = caseSensitive;
      this.fields = new LinkedHashMap();
   }

   public Fields(Fields original, boolean immutable) {
      this.caseSensitive = original.caseSensitive;
      Map<String, Field> copy = new LinkedHashMap();
      copy.putAll(original.fields);
      this.fields = immutable ? Collections.unmodifiableMap(copy) : copy;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         Fields that = (Fields)obj;
         if (this.getSize() != that.getSize()) {
            return false;
         } else if (this.caseSensitive != that.caseSensitive) {
            return false;
         } else {
            for(Map.Entry entry : this.fields.entrySet()) {
               String name = (String)entry.getKey();
               Field value = (Field)entry.getValue();
               if (!value.equals(that.get(name), this.caseSensitive)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.fields.hashCode();
   }

   public Set getNames() {
      Set<String> result = new LinkedHashSet();

      for(Field field : this.fields.values()) {
         result.add(field.getName());
      }

      return result;
   }

   private String normalizeName(String name) {
      return this.caseSensitive ? name : name.toLowerCase(Locale.ENGLISH);
   }

   public Field get(String name) {
      return (Field)this.fields.get(this.normalizeName(name));
   }

   public void put(String name, String value) {
      Field field = new Field(name, value);
      this.fields.put(this.normalizeName(name), field);
   }

   public void put(Field field) {
      if (field != null) {
         this.fields.put(this.normalizeName(field.getName()), field);
      }

   }

   public void add(String name, String value) {
      String key = this.normalizeName(name);
      Field field = (Field)this.fields.get(key);
      if (field == null) {
         field = new Field(name, value);
         this.fields.put(key, field);
      } else {
         field = new Field(field.getName(), field.getValues(), new String[]{value});
         this.fields.put(key, field);
      }

   }

   public Field remove(String name) {
      return (Field)this.fields.remove(this.normalizeName(name));
   }

   public void clear() {
      this.fields.clear();
   }

   public boolean isEmpty() {
      return this.fields.isEmpty();
   }

   public int getSize() {
      return this.fields.size();
   }

   public Iterator iterator() {
      return this.fields.values().iterator();
   }

   public String toString() {
      return this.fields.toString();
   }

   public static class Field {
      private final String name;
      private final List values;

      public Field(String name, String value) {
         this(name, Collections.singletonList(value));
      }

      private Field(String name, List values, String... moreValues) {
         this.name = name;
         List<String> list = new ArrayList(values.size() + moreValues.length);
         list.addAll(values);
         list.addAll(Arrays.asList(moreValues));
         this.values = Collections.unmodifiableList(list);
      }

      public boolean equals(Field that, boolean caseSensitive) {
         if (this == that) {
            return true;
         } else if (that == null) {
            return false;
         } else if (caseSensitive) {
            return this.equals(that);
         } else {
            return this.name.equalsIgnoreCase(that.name) && this.values.equals(that.values);
         }
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (obj != null && this.getClass() == obj.getClass()) {
            Field that = (Field)obj;
            return this.name.equals(that.name) && this.values.equals(that.values);
         } else {
            return false;
         }
      }

      public int hashCode() {
         int result = this.name.hashCode();
         result = 31 * result + this.values.hashCode();
         return result;
      }

      public String getName() {
         return this.name;
      }

      public String getValue() {
         return (String)this.values.get(0);
      }

      public Integer getValueAsInt() {
         String value = this.getValue();
         return value == null ? null : Integer.parseInt(value);
      }

      public List getValues() {
         return this.values;
      }

      public boolean hasMultipleValues() {
         return this.values.size() > 1;
      }

      public String toString() {
         return String.format("%s=%s", this.name, this.values);
      }
   }
}
