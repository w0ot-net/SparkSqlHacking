package org.apache.zookeeper.server;

public interface RequestProcessor {
   void processRequest(Request var1) throws RequestProcessorException;

   void shutdown();

   public static class RequestProcessorException extends Exception {
      public RequestProcessorException(String msg, Throwable t) {
         super(msg, t);
      }
   }
}
