package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface BiMap extends Map {
   @CheckForNull
   @CanIgnoreReturnValue
   Object put(@ParametricNullness Object key, @ParametricNullness Object value);

   @CheckForNull
   @CanIgnoreReturnValue
   Object forcePut(@ParametricNullness Object key, @ParametricNullness Object value);

   void putAll(Map map);

   Set values();

   BiMap inverse();
}
