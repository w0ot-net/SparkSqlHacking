package org.apache.spark.network.shuffle;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.network.buffer.ManagedBuffer;

public interface DownloadFileWritableChannel extends WritableByteChannel {
   ManagedBuffer closeAndRead() throws IOException;
}
