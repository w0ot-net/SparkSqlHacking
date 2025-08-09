package org.apache.curator.shaded.com.google.common.io;

import java.io.IOException;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

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
