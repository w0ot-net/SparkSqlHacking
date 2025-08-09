package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
