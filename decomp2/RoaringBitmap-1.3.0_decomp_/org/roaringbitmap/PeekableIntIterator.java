package org.roaringbitmap;

public interface PeekableIntIterator extends IntIterator {
   void advanceIfNeeded(int var1);

   int peekNext();

   PeekableIntIterator clone();
}
