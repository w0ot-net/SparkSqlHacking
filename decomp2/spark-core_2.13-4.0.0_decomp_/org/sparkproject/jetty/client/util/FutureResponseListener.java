package org.sparkproject.jetty.client.util;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.sparkproject.jetty.client.HttpContentResponse;
import org.sparkproject.jetty.client.api.ContentResponse;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Result;

public class FutureResponseListener extends BufferingResponseListener implements Future {
   private final CountDownLatch latch;
   private final Request request;
   private ContentResponse response;
   private Throwable failure;
   private volatile boolean cancelled;

   public FutureResponseListener(Request request) {
      this(request, 2097152);
   }

   public FutureResponseListener(Request request, int maxLength) {
      super(maxLength);
      this.latch = new CountDownLatch(1);
      this.request = request;
   }

   public Request getRequest() {
      return this.request;
   }

   public void onComplete(Result result) {
      this.response = new HttpContentResponse(result.getResponse(), this.getContent(), this.getMediaType(), this.getEncoding());
      this.failure = result.getFailure();
      this.latch.countDown();
   }

   public boolean cancel(boolean mayInterruptIfRunning) {
      this.cancelled = true;
      return this.request.abort(new CancellationException());
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public boolean isDone() {
      return this.latch.getCount() == 0L || this.isCancelled();
   }

   public ContentResponse get() throws InterruptedException, ExecutionException {
      this.latch.await();
      return this.getResult();
   }

   public ContentResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
      boolean expired = !this.latch.await(timeout, unit);
      if (expired) {
         throw new TimeoutException();
      } else {
         return this.getResult();
      }
   }

   private ContentResponse getResult() throws ExecutionException {
      if (this.isCancelled()) {
         throw (CancellationException)(new CancellationException()).initCause(this.failure);
      } else if (this.failure != null) {
         throw new ExecutionException(this.failure);
      } else {
         return this.response;
      }
   }
}
