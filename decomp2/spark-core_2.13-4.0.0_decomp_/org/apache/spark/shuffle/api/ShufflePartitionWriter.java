package org.apache.spark.shuffle.api;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import org.apache.spark.annotation.Private;

@Private
public interface ShufflePartitionWriter {
   OutputStream openStream() throws IOException;

   default Optional openChannelWrapper() throws IOException {
      return Optional.empty();
   }

   long getNumBytesWritten();
}
