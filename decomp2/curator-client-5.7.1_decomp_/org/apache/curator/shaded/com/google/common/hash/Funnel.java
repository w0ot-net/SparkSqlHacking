package org.apache.curator.shaded.com.google.common.hash;

import java.io.Serializable;
import org.apache.curator.shaded.com.google.common.annotations.Beta;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Implement with a lambda")
@ElementTypesAreNonnullByDefault
@Beta
public interface Funnel extends Serializable {
   void funnel(@ParametricNullness Object from, PrimitiveSink into);
}
