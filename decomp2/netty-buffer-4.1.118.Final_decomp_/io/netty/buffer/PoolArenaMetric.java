package io.netty.buffer;

import java.util.List;

public interface PoolArenaMetric extends SizeClassesMetric {
   int numThreadCaches();

   /** @deprecated */
   @Deprecated
   int numTinySubpages();

   int numSmallSubpages();

   int numChunkLists();

   /** @deprecated */
   @Deprecated
   List tinySubpages();

   List smallSubpages();

   List chunkLists();

   long numAllocations();

   /** @deprecated */
   @Deprecated
   long numTinyAllocations();

   long numSmallAllocations();

   long numNormalAllocations();

   long numHugeAllocations();

   long numDeallocations();

   /** @deprecated */
   @Deprecated
   long numTinyDeallocations();

   long numSmallDeallocations();

   long numNormalDeallocations();

   long numHugeDeallocations();

   long numActiveAllocations();

   /** @deprecated */
   @Deprecated
   long numActiveTinyAllocations();

   long numActiveSmallAllocations();

   long numActiveNormalAllocations();

   long numActiveHugeAllocations();

   long numActiveBytes();
}
