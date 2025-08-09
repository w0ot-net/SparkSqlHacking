package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
class EmptyImmutableSetMultimap extends ImmutableSetMultimap {
   static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
   private static final long serialVersionUID = 0L;

   private EmptyImmutableSetMultimap() {
      super(ImmutableMap.of(), 0, (Comparator)null);
   }

   public ImmutableMap asMap() {
      return super.asMap();
   }

   private Object readResolve() {
      return INSTANCE;
   }
}
