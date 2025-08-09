package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface LineProcessor {
   @CanIgnoreReturnValue
   boolean processLine(String line) throws IOException;

   @ParametricNullness
   Object getResult();
}
