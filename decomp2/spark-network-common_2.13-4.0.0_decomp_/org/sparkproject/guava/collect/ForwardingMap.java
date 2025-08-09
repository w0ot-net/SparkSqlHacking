package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Objects;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingMap extends ForwardingObject implements Map {
   protected ForwardingMap() {
   }

   protected abstract Map delegate();

   public int size() {
      return this.delegate().size();
   }

   public boolean isEmpty() {
      return this.delegate().isEmpty();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object key) {
      return this.delegate().remove(key);
   }

   public void clear() {
      this.delegate().clear();
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.delegate().containsKey(key);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.delegate().containsValue(value);
   }

   @CheckForNull
   public Object get(@CheckForNull Object key) {
      return this.delegate().get(key);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.delegate().put(key, value);
   }

   public void putAll(Map map) {
      this.delegate().putAll(map);
   }

   public Set keySet() {
      return this.delegate().keySet();
   }

   public Collection values() {
      return this.delegate().values();
   }

   public Set entrySet() {
      return this.delegate().entrySet();
   }

   public boolean equals(@CheckForNull Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   protected void standardPutAll(Map map) {
      Maps.putAllImpl(this, map);
   }

   @CheckForNull
   protected Object standardRemove(@CheckForNull Object key) {
      Iterator<Map.Entry<K, V>> entryIterator = this.entrySet().iterator();

      while(entryIterator.hasNext()) {
         Map.Entry<K, V> entry = (Map.Entry)entryIterator.next();
         if (Objects.equal(entry.getKey(), key)) {
            V value = (V)entry.getValue();
            entryIterator.remove();
            return value;
         }
      }

      return null;
   }

   protected void standardClear() {
      Iterators.clear(this.entrySet().iterator());
   }

   protected boolean standardContainsKey(@CheckForNull Object key) {
      return Maps.containsKeyImpl(this, key);
   }

   protected boolean standardContainsValue(@CheckForNull Object value) {
      return Maps.containsValueImpl(this, value);
   }

   protected boolean standardIsEmpty() {
      return !this.entrySet().iterator().hasNext();
   }

   protected boolean standardEquals(@CheckForNull Object object) {
      return Maps.equalsImpl(this, object);
   }

   protected int standardHashCode() {
      return Sets.hashCodeImpl(this.entrySet());
   }

   protected String standardToString() {
      return Maps.toStringImpl(this);
   }

   protected class StandardKeySet extends Maps.KeySet {
      public StandardKeySet() {
         super(ForwardingMap.this);
      }
   }

   protected class StandardValues extends Maps.Values {
      public StandardValues() {
         super(ForwardingMap.this);
      }
   }

   protected abstract class StandardEntrySet extends Maps.EntrySet {
      Map map() {
         return ForwardingMap.this;
      }
   }
}
