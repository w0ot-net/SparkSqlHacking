package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

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
