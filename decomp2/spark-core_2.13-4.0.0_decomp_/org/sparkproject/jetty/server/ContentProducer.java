package org.sparkproject.jetty.server;

import org.sparkproject.jetty.util.thread.AutoLock;

public interface ContentProducer {
   AutoLock lock();

   void recycle();

   void reopen();

   boolean consumeAll();

   void checkMinDataRate();

   long getRawContentArrived();

   int available();

   boolean hasContent();

   boolean isError();

   HttpInput.Content nextContent();

   void reclaim(HttpInput.Content var1);

   boolean isReady();

   HttpInput.Interceptor getInterceptor();

   void setInterceptor(HttpInput.Interceptor var1);

   boolean onContentProducible();
}
