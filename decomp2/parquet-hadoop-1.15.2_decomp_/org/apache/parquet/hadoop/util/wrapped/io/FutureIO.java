package org.apache.parquet.hadoop.util.wrapped.io;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UncheckedIOException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FutureIO {
   private static final Logger LOG = LoggerFactory.getLogger(FutureIO.class);

   public static Object awaitFuture(Future future, long timeout, TimeUnit unit) throws InterruptedIOException, IOException, RuntimeException, TimeoutException {
      try {
         LOG.debug("Awaiting future");
         return future.get(timeout, unit);
      } catch (InterruptedException e) {
         throw (InterruptedIOException)(new InterruptedIOException(e.toString())).initCause(e);
      } catch (CompletionException | ExecutionException e) {
         throw unwrapInnerException(e);
      }
   }

   public static IOException unwrapInnerException(Throwable e) {
      Throwable cause = e.getCause();
      if (cause instanceof IOException) {
         return (IOException)cause;
      } else if (cause instanceof UncheckedIOException) {
         return ((UncheckedIOException)cause).getCause();
      } else if (!(cause instanceof CompletionException) && !(cause instanceof ExecutionException)) {
         if (cause instanceof RuntimeException) {
            throw (RuntimeException)cause;
         } else if (cause instanceof Error) {
            throw (Error)cause;
         } else {
            return cause != null ? new IOException(cause) : new IOException(e);
         }
      } else {
         return unwrapInnerException(cause);
      }
   }
}
