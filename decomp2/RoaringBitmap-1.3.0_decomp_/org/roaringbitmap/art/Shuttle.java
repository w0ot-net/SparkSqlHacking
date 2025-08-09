package org.roaringbitmap.art;

public interface Shuttle {
   void initShuttle();

   void initShuttleFrom(long var1);

   boolean moveToNextLeaf();

   LeafNode getCurrentLeafNode();

   void remove();
}
