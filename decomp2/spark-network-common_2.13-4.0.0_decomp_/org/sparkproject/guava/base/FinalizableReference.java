package org.sparkproject.guava.base;

import com.google.errorprone.annotations.DoNotMock;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@DoNotMock("Use an instance of one of the Finalizable*Reference classes")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface FinalizableReference {
   void finalizeReferent();
}
