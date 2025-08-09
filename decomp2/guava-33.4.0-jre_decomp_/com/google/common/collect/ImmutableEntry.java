package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
class ImmutableEntry extends AbstractMapEntry implements Serializable {
   @ParametricNullness
   final Object key;
   @ParametricNullness
   final Object value;
   private static final long serialVersionUID = 0L;

   ImmutableEntry(@ParametricNullness Object key, @ParametricNullness Object value) {
      this.key = key;
      this.value = value;
   }

   @ParametricNullness
   public final Object getKey() {
      return this.key;
   }

   @ParametricNullness
   public final Object getValue() {
      return this.value;
   }

   @ParametricNullness
   public final Object setValue(@ParametricNullness Object value) {
      throw new UnsupportedOperationException();
   }
}
