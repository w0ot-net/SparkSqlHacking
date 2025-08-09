package org.apache.derby.iapi.store.access;

public interface SpaceInfo {
   long getNumAllocatedPages();

   long getNumFreePages();

   long getNumUnfilledPages();

   int getPageSize();
}
