package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
