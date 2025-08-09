package org.apache.curator.shaded.com.google.common.collect;

import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
