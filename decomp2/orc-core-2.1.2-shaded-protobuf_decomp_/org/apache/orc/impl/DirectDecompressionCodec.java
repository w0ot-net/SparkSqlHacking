package org.apache.orc.impl;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.orc.CompressionCodec;

public interface DirectDecompressionCodec extends CompressionCodec {
   boolean isAvailable();

   void directDecompress(ByteBuffer var1, ByteBuffer var2) throws IOException;
}
