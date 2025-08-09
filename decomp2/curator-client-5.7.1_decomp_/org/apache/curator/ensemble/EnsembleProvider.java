package org.apache.curator.ensemble;

import java.io.Closeable;
import java.io.IOException;

public interface EnsembleProvider extends Closeable {
   void start() throws Exception;

   String getConnectionString();

   void close() throws IOException;

   void setConnectionString(String var1);

   boolean updateServerListEnabled();
}
