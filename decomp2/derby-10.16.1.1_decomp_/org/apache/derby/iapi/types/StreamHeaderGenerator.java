package org.apache.derby.iapi.types;

import java.io.IOException;
import java.io.ObjectOutput;

public interface StreamHeaderGenerator {
   boolean expectsCharCount();

   int generateInto(byte[] var1, int var2, long var3);

   int generateInto(ObjectOutput var1, long var2) throws IOException;

   int writeEOF(byte[] var1, int var2, long var3);

   int writeEOF(ObjectOutput var1, long var2) throws IOException;

   int getMaxHeaderLength();
}
