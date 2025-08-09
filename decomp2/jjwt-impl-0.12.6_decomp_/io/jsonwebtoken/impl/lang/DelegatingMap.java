package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.Assert;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class DelegatingMap implements Map {
   protected Map DELEGATE;

   protected DelegatingMap(Map delegate) {
      this.setDelegate(delegate);
   }

   protected void setDelegate(Map delegate) {
      this.DELEGATE = (Map)Assert.notNull(delegate, "Delegate cannot be null.");
   }

   public int size() {
      return this.DELEGATE.size();
   }

   public Collection values() {
      return this.DELEGATE.values();
   }

   public Object get(Object id) {
      return this.DELEGATE.get(id);
   }

   public boolean isEmpty() {
      return this.DELEGATE.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.DELEGATE.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.DELEGATE.containsValue(value);
   }

   public Object put(Object key, Object value) {
      return this.DELEGATE.put(key, value);
   }

   public Object remove(Object key) {
      return this.DELEGATE.remove(key);
   }

   public void putAll(Map m) {
      this.DELEGATE.putAll(m);
   }

   public void clear() {
      this.DELEGATE.clear();
   }

   public Set keySet() {
      return this.DELEGATE.keySet();
   }

   public Set entrySet() {
      return this.DELEGATE.entrySet();
   }
}
