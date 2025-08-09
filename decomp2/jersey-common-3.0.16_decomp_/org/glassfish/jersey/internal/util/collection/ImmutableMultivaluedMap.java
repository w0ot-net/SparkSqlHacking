package org.glassfish.jersey.internal.util.collection;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableMultivaluedMap implements MultivaluedMap {
   private final MultivaluedMap delegate;

   public static ImmutableMultivaluedMap empty() {
      return new ImmutableMultivaluedMap(new MultivaluedHashMap());
   }

   public ImmutableMultivaluedMap(MultivaluedMap delegate) {
      if (delegate == null) {
         throw new NullPointerException("ImmutableMultivaluedMap delegate must not be 'null'.");
      } else {
         this.delegate = delegate;
      }
   }

   public boolean equalsIgnoreValueOrder(MultivaluedMap otherMap) {
      return this.delegate.equalsIgnoreValueOrder(otherMap);
   }

   public void putSingle(Object key, Object value) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public void add(Object key, Object value) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public Object getFirst(Object key) {
      return this.delegate.getFirst(key);
   }

   public void addAll(Object key, Object... newValues) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public void addAll(Object key, List valueList) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public void addFirst(Object key, Object value) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public int size() {
      return this.delegate.size();
   }

   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   public boolean containsKey(Object key) {
      return this.delegate.containsKey(key);
   }

   public boolean containsValue(Object value) {
      return this.delegate.containsValue(value);
   }

   public List get(Object key) {
      return (List)this.delegate.get(key);
   }

   public List put(Object key, List value) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public List remove(Object key) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public void putAll(Map m) {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public void clear() {
      throw new UnsupportedOperationException("This MultivaluedMap implementation is immutable.");
   }

   public Set keySet() {
      return Collections.unmodifiableSet(this.delegate.keySet());
   }

   public Collection values() {
      return Collections.unmodifiableCollection(this.delegate.values());
   }

   public Set entrySet() {
      return Collections.unmodifiableSet(this.delegate.entrySet());
   }

   public String toString() {
      return this.delegate.toString();
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof ImmutableMultivaluedMap)) {
         return false;
      } else {
         ImmutableMultivaluedMap that = (ImmutableMultivaluedMap)o;
         return this.delegate.equals(that.delegate);
      }
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }
}
