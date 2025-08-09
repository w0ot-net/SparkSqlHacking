package org.sparkproject.guava.collect;

import com.google.errorprone.annotations.DoNotMock;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@DoNotMock("Use Interners.new*Interner")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface Interner {
   Object intern(Object sample);
}
