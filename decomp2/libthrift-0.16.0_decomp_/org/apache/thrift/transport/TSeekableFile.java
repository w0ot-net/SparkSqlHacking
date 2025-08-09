package org.apache.thrift.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface TSeekableFile {
   InputStream getInputStream() throws IOException;

   OutputStream getOutputStream() throws IOException;

   void close() throws IOException;

   long length() throws IOException;

   void seek(long var1) throws IOException;
}
