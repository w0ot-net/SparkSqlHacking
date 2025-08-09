package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingMultimap extends ForwardingObject implements Multimap {
   protected ForwardingMultimap() {
   }

   protected abstract Multimap delegate();

   public Map asMap() {
      return this.delegate().asMap();
   }

   public void clear() {
      this.delegate().clear();
   }

   public boolean containsEntry(@CheckForNull Object key, @CheckForNull Object value) {
      return this.delegate().containsEntry(key, value);
   }

   public boolean containsKey(@CheckForNull Object key) {
      return this.delegate().containsKey(key);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.delegate().containsValue(value);
   }

   public Collection entries() {
      return this.delegate().entries();
   }

   public Collection get(@ParametricNullness Object key) {
      return this.delegate().get(key);
   }

   public boolean isEmpty() {
      return this.delegate().isEmpty();
   }

   public Multiset keys() {
      return this.delegate().keys();
   }

   public Set keySet() {
      return this.delegate().keySet();
   }

   @CanIgnoreReturnValue
   public boolean put(@ParametricNullness Object key, @ParametricNullness Object value) {
      return this.delegate().put(key, value);
   }

   @CanIgnoreReturnValue
   public boolean putAll(@ParametricNullness Object key, Iterable values) {
      return this.delegate().putAll(key, values);
   }

   @CanIgnoreReturnValue
   public boolean putAll(Multimap multimap) {
      return this.delegate().putAll(multimap);
   }

   @CanIgnoreReturnValue
   public boolean remove(@CheckForNull Object key, @CheckForNull Object value) {
      return this.delegate().remove(key, value);
   }

   @CanIgnoreReturnValue
   public Collection removeAll(@CheckForNull Object key) {
      return this.delegate().removeAll(key);
   }

   @CanIgnoreReturnValue
   public Collection replaceValues(@ParametricNullness Object key, Iterable values) {
      return this.delegate().replaceValues(key, values);
   }

   public int size() {
      return this.delegate().size();
   }

   public Collection values() {
      return this.delegate().values();
   }

   public boolean equals(@CheckForNull Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }
}
