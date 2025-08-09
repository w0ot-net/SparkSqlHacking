package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ZipEncoding {
   boolean canEncode(String var1);

   String decode(byte[] var1) throws IOException;

   ByteBuffer encode(String var1) throws IOException;
}
