package org.roaringbitmap;

public interface PeekableIntRankIterator extends PeekableIntIterator {
   int peekNextRank();

   PeekableIntRankIterator clone();
}
