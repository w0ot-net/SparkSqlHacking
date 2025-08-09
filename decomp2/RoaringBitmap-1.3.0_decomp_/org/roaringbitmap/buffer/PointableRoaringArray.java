package org.roaringbitmap.buffer;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface PointableRoaringArray extends Cloneable {
   int advanceUntil(char var1, int var2);

   PointableRoaringArray clone();

   boolean containsForContainerAtIndex(int var1, char var2);

   int getCardinality(int var1);

   int getContainerIndex(char var1);

   MappeableContainer getContainerAtIndex(int var1);

   MappeableContainerPointer getContainerPointer();

   MappeableContainerPointer getContainerPointer(int var1);

   int getIndex(char var1);

   char getKeyAtIndex(int var1);

   boolean hasRunCompression();

   void serialize(DataOutput var1) throws IOException;

   void serialize(ByteBuffer var1);

   int serializedSizeInBytes();

   int size();

   int first();

   int last();

   int firstSigned();

   int lastSigned();
}
