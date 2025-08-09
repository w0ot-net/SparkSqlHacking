package org.apache.datasketches.memory;

public interface Positional extends Resource {
   Positional incrementPosition(long var1);

   long getEnd();

   long getPosition();

   long getStart();

   long getRemaining();

   boolean hasRemaining();

   Positional resetPosition();

   Positional setPosition(long var1);

   Positional setStartPositionEnd(long var1, long var3, long var5);
}
