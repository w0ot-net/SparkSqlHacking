package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

@DoNotMock("Use ImmutableTable, HashBasedTable, or another implementation")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface Table {
   boolean contains(@CheckForNull @CompatibleWith("R") Object rowKey, @CheckForNull @CompatibleWith("C") Object columnKey);

   boolean containsRow(@CheckForNull @CompatibleWith("R") Object rowKey);

   boolean containsColumn(@CheckForNull @CompatibleWith("C") Object columnKey);

   boolean containsValue(@CheckForNull @CompatibleWith("V") Object value);

   @CheckForNull
   Object get(@CheckForNull @CompatibleWith("R") Object rowKey, @CheckForNull @CompatibleWith("C") Object columnKey);

   boolean isEmpty();

   int size();

   boolean equals(@CheckForNull Object obj);

   int hashCode();

   void clear();

   @CheckForNull
   @CanIgnoreReturnValue
   Object put(@ParametricNullness Object rowKey, @ParametricNullness Object columnKey, @ParametricNullness Object value);

   void putAll(Table table);

   @CheckForNull
   @CanIgnoreReturnValue
   Object remove(@CheckForNull @CompatibleWith("R") Object rowKey, @CheckForNull @CompatibleWith("C") Object columnKey);

   Map row(@ParametricNullness Object rowKey);

   Map column(@ParametricNullness Object columnKey);

   Set cellSet();

   Set rowKeySet();

   Set columnKeySet();

   Collection values();

   Map rowMap();

   Map columnMap();

   public interface Cell {
      @ParametricNullness
      Object getRowKey();

      @ParametricNullness
      Object getColumnKey();

      @ParametricNullness
      Object getValue();

      boolean equals(@CheckForNull Object obj);

      int hashCode();
   }
}
