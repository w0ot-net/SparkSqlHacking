package org.apache.hadoop.hive.shims;

import java.io.IOException;

public interface JettyShims {
   Server startServer(String var1, int var2) throws IOException;

   public interface Server {
      void addWar(String var1, String var2);

      void start() throws Exception;

      void join() throws InterruptedException;

      void stop() throws Exception;
   }
}
