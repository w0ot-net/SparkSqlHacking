package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Registry;
import io.jsonwebtoken.lang.Strings;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultRegistry extends DelegatingMap implements Registry, Function {
   private final String qualifiedKeyName;

   private static Map toMap(Collection values, Function keyFn) {
      Assert.notEmpty(values, "Collection of values may not be null or empty.");
      Assert.notNull(keyFn, "Key function cannot be null.");
      Map<K, V> m = new LinkedHashMap(values.size());

      for(Object value : values) {
         K key = (K)Assert.notNull(keyFn.apply(value), "Key function cannot return a null value.");
         m.put(key, value);
      }

      return Collections.immutable(m);
   }

   public DefaultRegistry(String name, String keyName, Collection values, Function keyFn) {
      super(toMap(values, keyFn));
      name = (String)Assert.hasText(Strings.clean(name), "name cannot be null or empty.");
      keyName = (String)Assert.hasText(Strings.clean(keyName), "keyName cannot be null or empty.");
      this.qualifiedKeyName = name + " " + keyName;
   }

   public Object apply(Object k) {
      return this.get(k);
   }

   public Object forKey(Object key) {
      V value = (V)this.get(key);
      if (value == null) {
         String msg = "Unrecognized " + this.qualifiedKeyName + ": " + key;
         throw new IllegalArgumentException(msg);
      } else {
         return value;
      }
   }

   static Object immutable() {
      throw new UnsupportedOperationException("Registries are immutable and cannot be modified.");
   }

   public Object put(Object key, Object value) {
      return immutable();
   }

   public Object remove(Object key) {
      return immutable();
   }

   public void putAll(Map m) {
      immutable();
   }

   public void clear() {
      immutable();
   }

   public int hashCode() {
      return this.DELEGATE.hashCode();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof DefaultRegistry)) {
         return false;
      } else {
         DefaultRegistry<?, ?> other = (DefaultRegistry)obj;
         return this.qualifiedKeyName.equals(other.qualifiedKeyName) && this.DELEGATE.equals(other.DELEGATE);
      }
   }

   public String toString() {
      return this.DELEGATE.toString();
   }
}
