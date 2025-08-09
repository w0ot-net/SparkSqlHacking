package org.apache.datasketches.quantilescommon;

public interface PartitioningFeature {
   default GenericPartitionBoundaries getPartitionBoundariesFromNumParts(int numEquallySizedParts) {
      return this.getPartitionBoundariesFromNumParts(numEquallySizedParts, QuantileSearchCriteria.INCLUSIVE);
   }

   GenericPartitionBoundaries getPartitionBoundariesFromNumParts(int var1, QuantileSearchCriteria var2);

   default GenericPartitionBoundaries getPartitionBoundariesFromPartSize(long nominalPartSizeItems) {
      return this.getPartitionBoundariesFromPartSize(nominalPartSizeItems, QuantileSearchCriteria.INCLUSIVE);
   }

   GenericPartitionBoundaries getPartitionBoundariesFromPartSize(long var1, QuantileSearchCriteria var3);
}
