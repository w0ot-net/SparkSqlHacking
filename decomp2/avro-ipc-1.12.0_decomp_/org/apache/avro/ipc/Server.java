package org.apache.avro.ipc;

public interface Server {
   int getPort();

   void start();

   void close();

   void join() throws InterruptedException;
}
