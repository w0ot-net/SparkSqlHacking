package io.fabric8.kubernetes.client.http;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ByteArrayBodyHandler implements AsyncBody.Consumer {
   private final List buffers = Collections.synchronizedList(new LinkedList());
   private final CompletableFuture result = new CompletableFuture();

   public void consume(List value, AsyncBody asyncBody) throws Exception {
      this.buffers.addAll(value);
      asyncBody.consume();
   }

   protected void onResponse(HttpResponse response) {
      AsyncBody asyncBody = (AsyncBody)response.body();
      asyncBody.done().whenComplete(this::onBodyDone);
      asyncBody.consume();
   }

   private void onBodyDone(Void v, Throwable t) {
      if (t != null) {
         this.result.completeExceptionally(t);
      } else {
         byte[] bytes = null;
         synchronized(this.buffers) {
            bytes = BufferUtil.toArray((Collection)this.buffers);
         }

         this.result.complete(bytes);
      }

      this.buffers.clear();
   }

   public CompletableFuture getResult() {
      return this.result;
   }
}
