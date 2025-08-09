package org.glassfish.jersey.server;

public interface BroadcasterListener {
   void onException(ChunkedOutput var1, Exception var2);

   void onClose(ChunkedOutput var1);
}
