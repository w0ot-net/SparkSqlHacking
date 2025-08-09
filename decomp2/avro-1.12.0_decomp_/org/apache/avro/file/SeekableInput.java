package org.apache.avro.file;

import java.io.Closeable;
import java.io.IOException;

public interface SeekableInput extends Closeable {
   void seek(long p) throws IOException;

   long tell() throws IOException;

   long length() throws IOException;

   int read(byte[] b, int off, int len) throws IOException;
}
