package org.apache.spark.shuffle.sort.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.spark.shuffle.IndexShuffleBlockResolver;
import org.apache.spark.shuffle.api.SingleSpillShuffleMapOutputWriter;
import org.apache.spark.util.Utils;

public class LocalDiskSingleSpillMapOutputWriter implements SingleSpillShuffleMapOutputWriter {
   private final int shuffleId;
   private final long mapId;
   private final IndexShuffleBlockResolver blockResolver;

   public LocalDiskSingleSpillMapOutputWriter(int shuffleId, long mapId, IndexShuffleBlockResolver blockResolver) {
      this.shuffleId = shuffleId;
      this.mapId = mapId;
      this.blockResolver = blockResolver;
   }

   public void transferMapSpillFile(File mapSpillFile, long[] partitionLengths, long[] checksums) throws IOException {
      File outputFile = this.blockResolver.getDataFile(this.shuffleId, this.mapId);
      File tempFile = Utils.tempFileWith(outputFile);
      Files.move(mapSpillFile.toPath(), tempFile.toPath());
      this.blockResolver.writeMetadataFileAndCommit(this.shuffleId, this.mapId, partitionLengths, checksums, tempFile);
   }
}
