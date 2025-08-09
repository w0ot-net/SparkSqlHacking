package org.apache.curator.shaded.com.google.common.collect;

import java.util.Map;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use ImmutableClassToInstanceMap or MutableClassToInstanceMap")
@ElementTypesAreNonnullByDefault
@GwtCompatible
public interface ClassToInstanceMap extends Map {
   @CheckForNull
   Object getInstance(Class type);

   @CheckForNull
   @CanIgnoreReturnValue
   Object putInstance(Class type, @ParametricNullness Object value);
}
