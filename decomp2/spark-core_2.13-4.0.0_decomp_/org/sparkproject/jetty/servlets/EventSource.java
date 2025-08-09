package org.sparkproject.jetty.servlets;

import java.io.IOException;

public interface EventSource {
   void onOpen(Emitter var1) throws IOException;

   void onClose();

   public interface Emitter {
      void event(String var1, String var2) throws IOException;

      void data(String var1) throws IOException;

      void comment(String var1) throws IOException;

      void close();
   }
}
