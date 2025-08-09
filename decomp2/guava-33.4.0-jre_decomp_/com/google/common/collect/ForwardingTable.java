package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingTable extends ForwardingObject implements Table {
   protected ForwardingTable() {
   }

   protected abstract Table delegate();

   public Set cellSet() {
      return this.delegate().cellSet();
   }

   public void clear() {
      this.delegate().clear();
   }

   public Map column(@ParametricNullness Object columnKey) {
      return this.delegate().column(columnKey);
   }

   public Set columnKeySet() {
      return this.delegate().columnKeySet();
   }

   public Map columnMap() {
      return this.delegate().columnMap();
   }

   public boolean contains(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return this.delegate().contains(rowKey, columnKey);
   }

   public boolean containsColumn(@CheckForNull Object columnKey) {
      return this.delegate().containsColumn(columnKey);
   }

   public boolean containsRow(@CheckForNull Object rowKey) {
      return this.delegate().containsRow(rowKey);
   }

   public boolean containsValue(@CheckForNull Object value) {
      return this.delegate().containsValue(value);
   }

   @CheckForNull
   public Object get(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return this.delegate().get(rowKey, columnKey);
   }

   public boolean isEmpty() {
      return this.delegate().isEmpty();
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value) {
      return this.delegate().put(rowKey, columnKey, value);
   }

   public void putAll(Table table) {
      this.delegate().putAll(table);
   }

   @CheckForNull
   @CanIgnoreReturnValue
   public Object remove(@CheckForNull Object rowKey, @CheckForNull Object columnKey) {
      return this.delegate().remove(rowKey, columnKey);
   }

   public Map row(@ParametricNullness Object rowKey) {
      return this.delegate().row(rowKey);
   }

   public Set rowKeySet() {
      return this.delegate().rowKeySet();
   }

   public Map rowMap() {
      return this.delegate().rowMap();
   }

   public int size() {
      return this.delegate().size();
   }

   public Collection values() {
      return this.delegate().values();
   }

   public boolean equals(@CheckForNull Object obj) {
      return obj == this || this.delegate().equals(obj);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }
}
