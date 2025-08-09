package org.apache.parquet.conf;

public interface ParquetConfiguration extends Iterable {
   void set(String var1, String var2);

   void setLong(String var1, long var2);

   void setInt(String var1, int var2);

   void setBoolean(String var1, boolean var2);

   void setStrings(String var1, String... var2);

   void setClass(String var1, Class var2, Class var3);

   String get(String var1);

   String get(String var1, String var2);

   long getLong(String var1, long var2);

   int getInt(String var1, int var2);

   boolean getBoolean(String var1, boolean var2);

   String getTrimmed(String var1);

   String getTrimmed(String var1, String var2);

   String[] getStrings(String var1, String[] var2);

   Class getClass(String var1, Class var2);

   Class getClass(String var1, Class var2, Class var3);

   Class getClassByName(String var1) throws ClassNotFoundException;
}
