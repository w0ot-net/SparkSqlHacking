package org.apache.spark.shuffle.api.metadata;

import java.util.Optional;
import org.apache.spark.annotation.Private;

@Private
public final class MapOutputCommitMessage {
   private final long[] partitionLengths;
   private final Optional mapOutputMetadata;

   private MapOutputCommitMessage(long[] partitionLengths, Optional mapOutputMetadata) {
      this.partitionLengths = partitionLengths;
      this.mapOutputMetadata = mapOutputMetadata;
   }

   public static MapOutputCommitMessage of(long[] partitionLengths) {
      return new MapOutputCommitMessage(partitionLengths, Optional.empty());
   }

   public static MapOutputCommitMessage of(long[] partitionLengths, MapOutputMetadata mapOutputMetadata) {
      return new MapOutputCommitMessage(partitionLengths, Optional.of(mapOutputMetadata));
   }

   public long[] getPartitionLengths() {
      return this.partitionLengths;
   }

   public Optional getMapOutputMetadata() {
      return this.mapOutputMetadata;
   }
}
