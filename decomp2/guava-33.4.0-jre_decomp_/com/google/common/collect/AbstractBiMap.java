package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
abstract class AbstractBiMap extends ForwardingMap implements BiMap, Serializable {
   private transient Map delegate;
   @RetainedWith
   transient AbstractBiMap inverse;
   @LazyInit
   @CheckForNull
   private transient Set keySet;
   @LazyInit
   @CheckForNull
   private transient Set valueSet;
   @LazyInit
   @CheckForNull
   private transient Set entrySet;
   @GwtIncompatible
   @J2ktIncompatible
   private static final long serialVersionUID = 0L;

   AbstractBiMap(Map forward, Map backward) {
      this.setDelegates(forward, backward);
   }

   private AbstractBiMap(Map backward, AbstractBiMap forward) {
      this.delegate = backward;
      this.inverse = forward;
   }

   protected Map delegate() {
      return this.delegate;
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   Object checkKey(@ParametricNullness Object key) {
      return key;
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   Object checkValue(@ParametricNullness Object value) {
      return value;
   }

   void setDelegates(Map forward, Map backward) {
      Preconditions.checkState(this.delegate == null);
      Preconditions.checkState(this.inverse == null);
      Preconditions.checkArgument(forward.isEmpty());
      Preconditions.checkArgument(backward.isEmpty());
      Preconditions.checkArgument(forward != backward);
      this.delegate = forward;
      this.inverse = this.makeInverse(backward);
   }

   AbstractBiMap makeInverse(Map backward) {
      return new Inverse(backward, this);
   }

   void setInverse(AbstractBiMap inverse) {
      this.inverse = inverse;
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.inverse.containsKey(value);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.putInBothMaps(key, value, false);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.putInBothMaps(key, value, true);
   }

   @CheckForNull
   private Object putInBothMaps(@ParametricNullness Object key, @ParametricNullness Object value, boolean force) {
      this.checkKey(key);
      this.checkValue(value);
      boolean containedKey = this.containsKey(key);
      if (containedKey && Objects.equal(value, this.get(key))) {
         return value;
      } else {
         if (force) {
            this.inverse().remove(value);
         } else {
            Preconditions.checkArgument(!this.containsValue(value), "value already present: %s", value);
         }

         V oldValue = (V)this.delegate.put(key, value);
         this.updateInverseMap(key, containedKey, oldValue, value);
         return oldValue;
      }
   }

   private void updateInverseMap(@ParametricNullness Object key, boolean containedKey, @CheckForNull Object oldValue, @ParametricNullness Object newValue) {
      if (containedKey) {
         this.removeFromInverseMap(NullnessCasts.uncheckedCastNullableTToT(oldValue));
      }

      this.inverse.delegate.put(newValue, key);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object key) {
      return this.containsKey(key) ? this.removeFromBothMaps(key) : null;
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   private Object removeFromBothMaps(@CheckForNull Object key) {
      V oldValue = (V)NullnessCasts.uncheckedCastNullableTToT(this.delegate.remove(key));
      this.removeFromInverseMap(oldValue);
      return oldValue;
   }

   private void removeFromInverseMap(@ParametricNullness Object oldValue) {
      this.inverse.delegate.remove(oldValue);
   }

   public void putAll(Map map) {
      for(Map.Entry entry : map.entrySet()) {
         this.put(entry.getKey(), entry.getValue());
      }

   }

   public void replaceAll(BiFunction function) {
      this.delegate.replaceAll(function);
      this.inverse.delegate.clear();
      Map.Entry<K, V> broken = null;
      Iterator<Map.Entry<K, V>> itr = this.delegate.entrySet().iterator();

      while(itr.hasNext()) {
         Map.Entry<K, V> entry = (Map.Entry)itr.next();
         K k = (K)entry.getKey();
         V v = (V)entry.getValue();
         K conflict = (K)this.inverse.delegate.putIfAbsent(v, k);
         if (conflict != null) {
            broken = entry;
            itr.remove();
         }
      }

      if (broken != null) {
         throw new IllegalArgumentException("value already present: " + broken.getValue());
      }
   }

   public void clear() {
      this.delegate.clear();
      this.inverse.delegate.clear();
   }

   public BiMap inverse() {
      return this.inverse;
   }

   public Set keySet() {
      Set<K> result = this.keySet;
      return result == null ? (this.keySet = new KeySet()) : result;
   }

   public Set values() {
      Set<V> result = this.valueSet;
      return result == null ? (this.valueSet = new ValueSet()) : result;
   }

   public Set entrySet() {
      Set<Map.Entry<K, V>> result = this.entrySet;
      return result == null ? (this.entrySet = new EntrySet()) : result;
   }

   Iterator entrySetIterator() {
      final Iterator<Map.Entry<K, V>> iterator = this.delegate.entrySet().iterator();
      return new Iterator() {
         @CheckForNull
         Map.Entry entry;

         public boolean hasNext() {
            return iterator.hasNext();
         }

         public Map.Entry next() {
            this.entry = (Map.Entry)iterator.next();
            return AbstractBiMap.this.new BiMapEntry(this.entry);
         }

         public void remove() {
            if (this.entry == null) {
               throw new IllegalStateException("no calls to next() since the last call to remove()");
            } else {
               V value = (V)this.entry.getValue();
               iterator.remove();
               AbstractBiMap.this.removeFromInverseMap(value);
               this.entry = null;
            }
         }
      };
   }

   private class KeySet extends ForwardingSet {
      private KeySet() {
      }

      protected Set delegate() {
         return AbstractBiMap.this.delegate.keySet();
      }

      public void clear() {
         AbstractBiMap.this.clear();
      }

      public boolean remove(@CheckForNull Object key) {
         if (!this.contains(key)) {
            return false;
         } else {
            AbstractBiMap.this.removeFromBothMaps(key);
            return true;
         }
      }

      public boolean removeAll(Collection keysToRemove) {
         return this.standardRemoveAll(keysToRemove);
      }

      public boolean retainAll(Collection keysToRetain) {
         return this.standardRetainAll(keysToRetain);
      }

      public Iterator iterator() {
         return Maps.keyIterator(AbstractBiMap.this.entrySet().iterator());
      }
   }

   private class ValueSet extends ForwardingSet {
      final Set valuesDelegate;

      private ValueSet() {
         this.valuesDelegate = AbstractBiMap.this.inverse.keySet();
      }

      protected Set delegate() {
         return this.valuesDelegate;
      }

      public Iterator iterator() {
         return Maps.valueIterator(AbstractBiMap.this.entrySet().iterator());
      }

      public @Nullable Object[] toArray() {
         return this.standardToArray();
      }

      public Object[] toArray(Object[] array) {
         return this.standardToArray(array);
      }

      public String toString() {
         return this.standardToString();
      }
   }

   class BiMapEntry extends ForwardingMapEntry {
      private final Map.Entry delegate;

      BiMapEntry(Map.Entry delegate) {
         this.delegate = delegate;
      }

      protected Map.Entry delegate() {
         return this.delegate;
      }

      public Object setValue(Object value) {
         AbstractBiMap.this.checkValue(value);
         Preconditions.checkState(AbstractBiMap.this.entrySet().contains(this), "entry no longer in map");
         if (Objects.equal(value, this.getValue())) {
            return value;
         } else {
            Preconditions.checkArgument(!AbstractBiMap.this.containsValue(value), "value already present: %s", value);
            V oldValue = (V)this.delegate.setValue(value);
            Preconditions.checkState(Objects.equal(value, AbstractBiMap.this.get(this.getKey())), "entry no longer in map");
            AbstractBiMap.this.updateInverseMap(this.getKey(), true, oldValue, value);
            return oldValue;
         }
      }
   }

   private class EntrySet extends ForwardingSet {
      final Set esDelegate;

      private EntrySet() {
         this.esDelegate = AbstractBiMap.this.delegate.entrySet();
      }

      protected Set delegate() {
         return this.esDelegate;
      }

      public void clear() {
         AbstractBiMap.this.clear();
      }

      public boolean remove(@CheckForNull Object object) {
         if (this.esDelegate.contains(object) && object instanceof Map.Entry) {
            Map.Entry<?, ?> entry = (Map.Entry)object;
            AbstractBiMap.this.inverse.delegate.remove(entry.getValue());
            this.esDelegate.remove(entry);
            return true;
         } else {
            return false;
         }
      }

      public Iterator iterator() {
         return AbstractBiMap.this.entrySetIterator();
      }

      public @Nullable Object[] toArray() {
         return this.standardToArray();
      }

      public Object[] toArray(Object[] array) {
         return this.standardToArray(array);
      }

      public boolean contains(@CheckForNull Object o) {
         return Maps.containsEntryImpl(this.delegate(), o);
      }

      public boolean containsAll(Collection c) {
         return this.standardContainsAll(c);
      }

      public boolean removeAll(Collection c) {
         return this.standardRemoveAll(c);
      }

      public boolean retainAll(Collection c) {
         return this.standardRetainAll(c);
      }
   }

   static class Inverse extends AbstractBiMap {
      @GwtIncompatible
      @J2ktIncompatible
      private static final long serialVersionUID = 0L;

      Inverse(Map backward, AbstractBiMap forward) {
         super(backward, forward, null);
      }

      @ParametricNullness
      Object checkKey(@ParametricNullness Object key) {
         return this.inverse.checkValue(key);
      }

      @ParametricNullness
      Object checkValue(@ParametricNullness Object value) {
         return this.inverse.checkKey(value);
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void writeObject(ObjectOutputStream stream) throws IOException {
         stream.defaultWriteObject();
         stream.writeObject(this.inverse());
      }

      @GwtIncompatible
      @J2ktIncompatible
      private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
         stream.defaultReadObject();
         this.setInverse((AbstractBiMap)java.util.Objects.requireNonNull(stream.readObject()));
      }

      @GwtIncompatible
      @J2ktIncompatible
      Object readResolve() {
         return this.inverse().inverse();
      }
   }
}
