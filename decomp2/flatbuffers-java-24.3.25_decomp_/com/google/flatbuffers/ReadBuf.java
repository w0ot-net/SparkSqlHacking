package com.google.flatbuffers;

public interface ReadBuf {
   boolean getBoolean(int var1);

   byte get(int var1);

   short getShort(int var1);

   int getInt(int var1);

   long getLong(int var1);

   float getFloat(int var1);

   double getDouble(int var1);

   String getString(int var1, int var2);

   byte[] data();

   int limit();
}
