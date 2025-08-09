package org.apache.derby.impl.store.raw.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ByteHolder {
   void write(int var1) throws IOException;

   void write(byte[] var1, int var2, int var3) throws IOException;

   long write(InputStream var1, long var2) throws IOException;

   void clear() throws IOException;

   void startReading() throws IOException;

   int read() throws IOException;

   int read(byte[] var1, int var2, int var3) throws IOException;

   int read(OutputStream var1, int var2) throws IOException;

   int shiftToFront() throws IOException;

   int available() throws IOException;

   int numBytesSaved() throws IOException;

   long skip(long var1) throws IOException;

   boolean writingMode();

   ByteHolder cloneEmpty();
}
