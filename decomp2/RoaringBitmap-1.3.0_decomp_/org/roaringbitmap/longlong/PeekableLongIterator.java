package org.roaringbitmap.longlong;

public interface PeekableLongIterator extends LongIterator {
   void advanceIfNeeded(long var1);

   long peekNext();

   PeekableLongIterator clone();
}
