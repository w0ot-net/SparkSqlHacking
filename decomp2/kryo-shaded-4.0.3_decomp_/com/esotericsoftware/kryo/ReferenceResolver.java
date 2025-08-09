package com.esotericsoftware.kryo;

public interface ReferenceResolver {
   void setKryo(Kryo var1);

   int getWrittenId(Object var1);

   int addWrittenObject(Object var1);

   int nextReadId(Class var1);

   void setReadObject(int var1, Object var2);

   Object getReadObject(Class var1, int var2);

   void reset();

   boolean useReferences(Class var1);
}
