package io.airlift.compress.hadoop;

import java.io.IOException;
import java.io.OutputStream;

public abstract class HadoopOutputStream extends OutputStream {
   public abstract void finish() throws IOException;

   public abstract void write(byte[] b, int off, int len) throws IOException;

   public abstract void flush() throws IOException;

   public abstract void close() throws IOException;
}
