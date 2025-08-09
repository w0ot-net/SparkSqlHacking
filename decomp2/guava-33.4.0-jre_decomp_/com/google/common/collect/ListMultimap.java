package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface ListMultimap extends Multimap {
   List get(@ParametricNullness Object key);

   @CanIgnoreReturnValue
   List removeAll(@CheckForNull Object key);

   @CanIgnoreReturnValue
   List replaceValues(@ParametricNullness Object key, Iterable values);

   Map asMap();

   boolean equals(@CheckForNull Object obj);
}
