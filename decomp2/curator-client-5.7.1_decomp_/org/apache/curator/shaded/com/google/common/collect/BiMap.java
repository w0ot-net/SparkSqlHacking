package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

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
