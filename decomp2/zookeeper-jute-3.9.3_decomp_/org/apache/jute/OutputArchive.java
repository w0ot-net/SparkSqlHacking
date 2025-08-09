package org.apache.jute;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public interface OutputArchive {
   void writeByte(byte var1, String var2) throws IOException;

   void writeBool(boolean var1, String var2) throws IOException;

   void writeInt(int var1, String var2) throws IOException;

   void writeLong(long var1, String var3) throws IOException;

   void writeFloat(float var1, String var2) throws IOException;

   void writeDouble(double var1, String var3) throws IOException;

   void writeString(String var1, String var2) throws IOException;

   void writeBuffer(byte[] var1, String var2) throws IOException;

   void writeRecord(Record var1, String var2) throws IOException;

   void startRecord(Record var1, String var2) throws IOException;

   void endRecord(Record var1, String var2) throws IOException;

   void startVector(List var1, String var2) throws IOException;

   void endVector(List var1, String var2) throws IOException;

   void startMap(TreeMap var1, String var2) throws IOException;

   void endMap(TreeMap var1, String var2) throws IOException;

   long getDataSize();
}
