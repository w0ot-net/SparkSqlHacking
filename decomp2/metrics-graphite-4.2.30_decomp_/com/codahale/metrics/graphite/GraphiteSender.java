package com.codahale.metrics.graphite;

import java.io.Closeable;
import java.io.IOException;

public interface GraphiteSender extends Closeable {
   void connect() throws IllegalStateException, IOException;

   void send(String name, String value, long timestamp) throws IOException;

   void flush() throws IOException;

   boolean isConnected();

   int getFailures();
}
