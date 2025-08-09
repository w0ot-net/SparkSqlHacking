package org.sparkproject.guava.io;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;
import org.sparkproject.guava.annotations.GwtIncompatible;
import org.sparkproject.guava.annotations.J2ktIncompatible;

@DoNotMock("Implement it normally")
@ElementTypesAreNonnullByDefault
@J2ktIncompatible
@GwtIncompatible
public interface ByteProcessor {
   @CanIgnoreReturnValue
   boolean processBytes(byte[] buf, int off, int len) throws IOException;

   @ParametricNullness
   Object getResult();
}
