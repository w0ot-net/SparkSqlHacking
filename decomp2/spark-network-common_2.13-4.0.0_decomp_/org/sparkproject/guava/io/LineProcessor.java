package org.sparkproject.guava.io;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface LineProcessor {
   @CanIgnoreReturnValue
   boolean processLine(String line) throws IOException;

   @ParametricNullness
   Object getResult();
}
