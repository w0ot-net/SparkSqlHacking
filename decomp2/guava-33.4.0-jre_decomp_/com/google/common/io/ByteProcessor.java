package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import java.io.IOException;

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
