package org.glassfish.jersey.servlet.async;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.servlet.init.internal.LocalizationMessages;
import org.glassfish.jersey.servlet.spi.AsyncContextDelegate;
import org.glassfish.jersey.servlet.spi.AsyncContextDelegateProvider;

public class AsyncContextDelegateProviderImpl implements AsyncContextDelegateProvider {
   private static final Logger LOGGER = Logger.getLogger(AsyncContextDelegateProviderImpl.class.getName());

   public final AsyncContextDelegate createDelegate(HttpServletRequest request, HttpServletResponse response) {
      return new ExtensionImpl(request, response);
   }

   private static final class ExtensionImpl implements AsyncContextDelegate {
      private static final int NEVER_TIMEOUT_VALUE = -1;
      private final HttpServletRequest request;
      private final HttpServletResponse response;
      private final AtomicReference asyncContextRef;
      private final AtomicBoolean completed;

      private ExtensionImpl(HttpServletRequest request, HttpServletResponse response) {
         this.request = request;
         this.response = response;
         this.asyncContextRef = new AtomicReference();
         this.completed = new AtomicBoolean(false);
      }

      public void suspend() throws IllegalStateException {
         if (!this.completed.get() && this.asyncContextRef.get() == null) {
            this.asyncContextRef.set(this.getAsyncContext());
         }

      }

      private AsyncContext getAsyncContext() {
         AsyncContext asyncContext;
         if (this.request.isAsyncStarted()) {
            asyncContext = this.request.getAsyncContext();

            try {
               asyncContext.setTimeout(-1L);
            } catch (IllegalStateException ex) {
               AsyncContextDelegateProviderImpl.LOGGER.log(Level.FINE, LocalizationMessages.SERVLET_ASYNC_CONTEXT_ALREADY_STARTED(), ex);
            }
         } else {
            asyncContext = this.request.startAsync(this.request, this.response);
            asyncContext.setTimeout(-1L);
         }

         return asyncContext;
      }

      public void complete() {
         this.completed.set(true);
         AsyncContext asyncContext = (AsyncContext)this.asyncContextRef.getAndSet((Object)null);
         if (asyncContext != null) {
            asyncContext.complete();
         }

      }
   }
}
