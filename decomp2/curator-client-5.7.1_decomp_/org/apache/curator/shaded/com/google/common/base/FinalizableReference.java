package org.apache.curator.shaded.com.google.common.base;

import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use an instance of one of the Finalizable*Reference classes")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface FinalizableReference {
   void finalizeReferent();
}
