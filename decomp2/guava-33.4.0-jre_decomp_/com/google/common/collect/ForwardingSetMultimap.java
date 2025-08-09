package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingSetMultimap extends ForwardingMultimap implements SetMultimap {
   protected abstract SetMultimap delegate();

   public Set entries() {
      return this.delegate().entries();
   }

   public Set get(@ParametricNullness Object key) {
      return this.delegate().get(key);
   }

   @CanIgnoreReturnValue
   public Set removeAll(@CheckForNull Object key) {
      return this.delegate().removeAll(key);
   }

   @CanIgnoreReturnValue
   public Set replaceValues(@ParametricNullness Object key, Iterable values) {
      return this.delegate().replaceValues(key, values);
   }
}
