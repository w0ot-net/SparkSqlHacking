package org.apache.derby.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface StorageRandomAccessFile extends DataInput, DataOutput {
   void close() throws IOException;

   long getFilePointer() throws IOException;

   long length() throws IOException;

   void seek(long var1) throws IOException;

   void setLength(long var1) throws IOException;

   void sync() throws IOException;

   int read(byte[] var1, int var2, int var3) throws IOException;

   StorageRandomAccessFile clone();
}
