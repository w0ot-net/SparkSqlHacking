package io.airlift.compress.hadoop;

import java.io.IOException;
import java.io.InputStream;

public abstract class HadoopInputStream extends InputStream {
   public abstract void resetState();

   public abstract int read(byte[] b, int off, int len) throws IOException;

   public abstract void close() throws IOException;
}
