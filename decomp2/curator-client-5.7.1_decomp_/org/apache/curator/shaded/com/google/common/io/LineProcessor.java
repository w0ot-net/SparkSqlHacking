package org.apache.curator.shaded.com.google.common.io;

import java.io.IOException;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface LineProcessor {
   @CanIgnoreReturnValue
   boolean processLine(String line) throws IOException;

   @ParametricNullness
   Object getResult();
}
