package org.roaringbitmap;

public interface PeekableCharRankIterator extends PeekableCharIterator {
   int peekNextRank();

   PeekableCharRankIterator clone();
}
