package io.jsonwebtoken.impl;

import io.jsonwebtoken.impl.lang.Nameable;
import io.jsonwebtoken.impl.lang.Parameter;
import io.jsonwebtoken.impl.lang.ParameterReadable;
import io.jsonwebtoken.impl.lang.Parameters;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ParameterMap implements Map, ParameterReadable, Nameable {
   protected final Registry PARAMS;
   protected final Map values;
   protected final Map idiomaticValues;
   private final boolean initialized;
   private final boolean mutable;

   public ParameterMap(Set params) {
      this(Parameters.registry((Collection)params));
   }

   public ParameterMap(Registry registry) {
      this(registry, (Map)null, true);
   }

   public ParameterMap(Registry registry, Map values) {
      this(registry, (Map)Assert.notNull(values, "Map argument cannot be null."), false);
   }

   public ParameterMap(Registry registry, Map values, boolean mutable) {
      Assert.notNull(registry, "Parameter registry cannot be null.");
      Assert.notEmpty(registry.values(), "Parameter registry cannot be empty.");
      this.PARAMS = registry;
      this.values = new LinkedHashMap();
      this.idiomaticValues = new LinkedHashMap();
      if (!Collections.isEmpty(values)) {
         this.putAll(values);
      }

      this.mutable = mutable;
      this.initialized = true;
   }

   private void assertMutable() {
      if (this.initialized && !this.mutable) {
         String msg = this.getName() + " instance is immutable and may not be modified.";
         throw new UnsupportedOperationException(msg);
      }
   }

   protected ParameterMap replace(Parameter param) {
      Registry<String, ? extends Parameter<?>> registry = Parameters.replace(this.PARAMS, param);
      return new ParameterMap(registry, this, this.mutable);
   }

   public String getName() {
      return "Map";
   }

   public Object get(Parameter param) {
      Assert.notNull(param, "Parameter cannot be null.");
      String id = (String)Assert.hasText(param.getId(), "Parameter id cannot be null or empty.");
      Object value = this.idiomaticValues.get(id);
      return param.cast(value);
   }

   public int size() {
      return this.values.size();
   }

   public boolean isEmpty() {
      return this.values.isEmpty();
   }

   public boolean containsKey(Object o) {
      return this.values.containsKey(o);
   }

   public boolean containsValue(Object o) {
      return this.values.containsValue(o);
   }

   public Object get(Object o) {
      return this.values.get(o);
   }

   protected final Object put(Parameter param, Object value) {
      this.assertMutable();
      Assert.notNull(param, "Parameter cannot be null.");
      Assert.hasText(param.getId(), "Parameter id cannot be null or empty.");
      return this.apply(param, value);
   }

   public final Object put(String name, Object value) {
      this.assertMutable();
      name = (String)Assert.notNull(Strings.clean(name), "Member name cannot be null or empty.");
      Parameter<?> param = (Parameter)this.PARAMS.get(name);
      return param != null ? this.put(param, value) : this.nullSafePut(name, value);
   }

   private Object nullSafePut(String name, Object value) {
      if (value == null) {
         return this.remove(name);
      } else {
         this.idiomaticValues.put(name, value);
         return this.values.put(name, value);
      }
   }

   private Object apply(Parameter param, Object rawValue) {
      String id = param.getId();
      if (Objects.isEmpty(rawValue)) {
         return this.remove(id);
      } else {
         T idiomaticValue;
         Object canonicalValue;
         try {
            idiomaticValue = (T)param.applyFrom(rawValue);
            Assert.notNull(idiomaticValue, "Parameter's resulting idiomaticValue cannot be null.");
            canonicalValue = param.applyTo(idiomaticValue);
            Assert.notNull(canonicalValue, "Parameter's resulting canonicalValue cannot be null.");
         } catch (Exception e) {
            StringBuilder sb = new StringBuilder(100);
            sb.append("Invalid ").append(this.getName()).append(" ").append(param).append(" value");
            if (param.isSecret()) {
               sb.append(": ").append("<redacted>");
            } else if (!(rawValue instanceof byte[])) {
               sb.append(": ").append(Objects.nullSafeToString(rawValue));
            }

            sb.append(". ").append(e.getMessage());
            String msg = sb.toString();
            throw new IllegalArgumentException(msg, e);
         }

         this.idiomaticValues.put(id, idiomaticValue);
         return this.values.put(id, canonicalValue);
      }
   }

   public Object remove(Object key) {
      this.assertMutable();
      this.idiomaticValues.remove(key);
      return this.values.remove(key);
   }

   public void putAll(Map m) {
      if (m != null) {
         for(Map.Entry entry : m.entrySet()) {
            String s = (String)entry.getKey();
            this.put(s, entry.getValue());
         }

      }
   }

   public void clear() {
      this.assertMutable();
      this.values.clear();
      this.idiomaticValues.clear();
   }

   public Set keySet() {
      return new KeySet();
   }

   public Collection values() {
      return new ValueSet();
   }

   public Set entrySet() {
      return new EntrySet();
   }

   public String toString() {
      return this.values.toString();
   }

   public int hashCode() {
      return this.values.hashCode();
   }

   public boolean equals(Object obj) {
      return this.values.equals(obj);
   }

   private abstract class ParameterMapSet extends AbstractSet {
      private ParameterMapSet() {
      }

      public int size() {
         return ParameterMap.this.size();
      }
   }

   private class KeySet extends ParameterMapSet {
      private KeySet() {
      }

      public Iterator iterator() {
         return ParameterMap.this.new KeyIterator();
      }
   }

   private class ValueSet extends ParameterMapSet {
      private ValueSet() {
      }

      public Iterator iterator() {
         return ParameterMap.this.new ValueIterator();
      }
   }

   private class EntrySet extends ParameterMapSet {
      private EntrySet() {
      }

      public Iterator iterator() {
         return ParameterMap.this.new EntryIterator();
      }
   }

   private abstract class ParameterMapIterator implements Iterator {
      final Iterator i;
      transient Map.Entry current;

      ParameterMapIterator() {
         this.i = ParameterMap.this.values.entrySet().iterator();
         this.current = null;
      }

      public boolean hasNext() {
         return this.i.hasNext();
      }

      protected Map.Entry nextEntry() {
         this.current = (Map.Entry)this.i.next();
         return this.current;
      }

      public void remove() {
         if (this.current == null) {
            throw new IllegalStateException();
         } else {
            String key = (String)this.current.getKey();
            ParameterMap.this.remove(key);
         }
      }
   }

   private class ValueIterator extends ParameterMapIterator {
      private ValueIterator() {
      }

      public Object next() {
         return this.nextEntry().getValue();
      }
   }

   private class KeyIterator extends ParameterMapIterator {
      private KeyIterator() {
      }

      public String next() {
         return (String)this.nextEntry().getKey();
      }
   }

   private class EntryIterator extends ParameterMapIterator {
      private EntryIterator() {
      }

      public Map.Entry next() {
         return this.nextEntry();
      }
   }
}
