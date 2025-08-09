package org.sparkproject.guava.hash;

import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;
import org.sparkproject.guava.annotations.Beta;

@DoNotMock("Implement with a lambda")
@ElementTypesAreNonnullByDefault
@Beta
public interface Funnel extends Serializable {
   void funnel(@ParametricNullness Object from, PrimitiveSink into);
}
