package org.apache.spark.shuffle.api;

import java.io.Closeable;
import java.nio.channels.WritableByteChannel;
import org.apache.spark.annotation.Private;

@Private
public interface WritableByteChannelWrapper extends Closeable {
   WritableByteChannel channel();
}
