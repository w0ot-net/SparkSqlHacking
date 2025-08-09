package org.apache.datasketches.quantilescommon;

public interface QuantilesAPI {
   String EMPTY_MSG = "The sketch must not be empty for this operation. ";
   String UNSUPPORTED_MSG = "Unsupported operation for this Sketch Type. ";
   String NOT_SINGLE_ITEM_MSG = "Sketch does not have just one item. ";
   String MEM_REQ_SVR_NULL_MSG = "MemoryRequestServer must not be null. ";
   String TGT_IS_READ_ONLY_MSG = "Target sketch is Read Only, cannot write. ";
   String SELF_MERGE_MSG = "A sketch cannot merge with itself. ";

   int getK();

   long getN();

   double getNormalizedRankError(boolean var1);

   int getNumRetained();

   double getRankLowerBound(double var1);

   double getRankUpperBound(double var1);

   boolean hasMemory();

   boolean isDirect();

   boolean isEmpty();

   boolean isEstimationMode();

   boolean isReadOnly();

   void reset();

   String toString();
}
