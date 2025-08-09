package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingListMultimap extends ForwardingMultimap implements ListMultimap {
   protected ForwardingListMultimap() {
   }

   protected abstract ListMultimap delegate();

   public List get(@ParametricNullness Object key) {
      return this.delegate().get(key);
   }

   @CanIgnoreReturnValue
   public List removeAll(@CheckForNull Object key) {
      return this.delegate().removeAll(key);
   }

   @CanIgnoreReturnValue
   public List replaceValues(@ParametricNullness Object key, Iterable values) {
      return this.delegate().replaceValues(key, values);
   }
}
