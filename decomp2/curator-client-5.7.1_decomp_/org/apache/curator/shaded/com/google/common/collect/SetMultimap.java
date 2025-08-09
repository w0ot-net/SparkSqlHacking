package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface SetMultimap extends Multimap {
   Set get(@ParametricNullness Object key);

   @CanIgnoreReturnValue
   Set removeAll(@CheckForNull Object key);

   @CanIgnoreReturnValue
   Set replaceValues(@ParametricNullness Object key, Iterable values);

   Set entries();

   Map asMap();

   boolean equals(@CheckForNull Object obj);
}
