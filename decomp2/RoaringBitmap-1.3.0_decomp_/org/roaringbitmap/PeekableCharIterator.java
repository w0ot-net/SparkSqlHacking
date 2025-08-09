package org.roaringbitmap;

public interface PeekableCharIterator extends CharIterator {
   void advanceIfNeeded(char var1);

   char peekNext();

   PeekableCharIterator clone();
}
