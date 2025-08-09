package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.DoNotMock;
import java.io.Serializable;

@DoNotMock("Implement with a lambda")
@ElementTypesAreNonnullByDefault
@Beta
public interface Funnel extends Serializable {
   void funnel(@ParametricNullness Object from, PrimitiveSink into);
}
