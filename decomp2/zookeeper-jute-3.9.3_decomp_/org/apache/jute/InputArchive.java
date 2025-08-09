package org.apache.jute;

import java.io.IOException;

public interface InputArchive {
   byte readByte(String var1) throws IOException;

   boolean readBool(String var1) throws IOException;

   int readInt(String var1) throws IOException;

   long readLong(String var1) throws IOException;

   float readFloat(String var1) throws IOException;

   double readDouble(String var1) throws IOException;

   String readString(String var1) throws IOException;

   byte[] readBuffer(String var1) throws IOException;

   void readRecord(Record var1, String var2) throws IOException;

   void startRecord(String var1) throws IOException;

   void endRecord(String var1) throws IOException;

   Index startVector(String var1) throws IOException;

   void endVector(String var1) throws IOException;

   Index startMap(String var1) throws IOException;

   void endMap(String var1) throws IOException;
}
