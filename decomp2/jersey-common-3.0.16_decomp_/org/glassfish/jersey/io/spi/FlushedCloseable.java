package org.glassfish.jersey.io.spi;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;

public interface FlushedCloseable extends Flushable, Closeable {
   void close() throws IOException;
}
