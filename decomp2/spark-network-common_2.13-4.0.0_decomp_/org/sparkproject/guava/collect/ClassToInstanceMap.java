package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;

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
