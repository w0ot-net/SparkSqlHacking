package org.apache.curator.shaded.com.google.common.reflect;

import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use ImmutableTypeToInstanceMap or MutableTypeToInstanceMap")
@ElementTypesAreNonnullByDefault
public interface TypeToInstanceMap extends Map {
   @CheckForNull
   Object getInstance(Class type);

   @CheckForNull
   Object getInstance(TypeToken type);

   @CheckForNull
   @CanIgnoreReturnValue
   Object putInstance(Class type, @ParametricNullness Object value);

   @CheckForNull
   @CanIgnoreReturnValue
   Object putInstance(TypeToken type, @ParametricNullness Object value);
}
