package org.apache.curator.framework.recipes.locks;

import java.io.Closeable;
import java.io.IOException;

public interface Lease extends Closeable {
   void close() throws IOException;

   byte[] getData() throws Exception;

   String getNodeName();
}
