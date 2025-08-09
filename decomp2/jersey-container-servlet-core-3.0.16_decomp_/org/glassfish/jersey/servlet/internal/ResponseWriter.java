package org.glassfish.jersey.servlet.internal;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.server.ContainerException;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.internal.JerseyRequestTimeoutHandler;
import org.glassfish.jersey.server.spi.ContainerResponseWriter;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.spi.AsyncContextDelegate;

public class ResponseWriter implements ContainerResponseWriter {
   private static final Logger LOGGER = Logger.getLogger(ResponseWriter.class.getName());
   private final HttpServletResponse response;
   private final boolean useSetStatusOn404;
   private final boolean configSetStatusOverSendError;
   private final CompletableFuture responseContext;
   private final AsyncContextDelegate asyncExt;
   private final JerseyRequestTimeoutHandler requestTimeoutHandler;

   public ResponseWriter(boolean useSetStatusOn404, boolean configSetStatusOverSendError, HttpServletResponse response, AsyncContextDelegate asyncExt, ScheduledExecutorService timeoutTaskExecutor) {
      this.useSetStatusOn404 = useSetStatusOn404;
      this.configSetStatusOverSendError = configSetStatusOverSendError;
      this.response = response;
      this.asyncExt = asyncExt;
      this.responseContext = new CompletableFuture();
      this.requestTimeoutHandler = new JerseyRequestTimeoutHandler(this, timeoutTaskExecutor);
   }

   public boolean suspend(long timeOut, TimeUnit timeUnit, ContainerResponseWriter.TimeoutHandler timeoutHandler) {
      try {
         this.asyncExt.suspend();
      } catch (IllegalStateException ex) {
         LOGGER.log(Level.WARNING, LocalizationMessages.SERVLET_REQUEST_SUSPEND_FAILED(), ex);
         return false;
      }

      return this.requestTimeoutHandler.suspend(timeOut, timeUnit, timeoutHandler);
   }

   public void setSuspendTimeout(long timeOut, TimeUnit timeUnit) throws IllegalStateException {
      this.requestTimeoutHandler.setSuspendTimeout(timeOut, timeUnit);
   }

   public OutputStream writeResponseStatusAndHeaders(long contentLength, ContainerResponse responseContext) throws ContainerException {
      this.responseContext.complete(responseContext);
      if (responseContext.hasEntity() && contentLength != -1L && contentLength < 2147483647L) {
         this.response.setContentLength((int)contentLength);
      }

      MultivaluedMap<String, String> headers = responseContext.getStringHeaders();

      for(Map.Entry e : headers.entrySet()) {
         Iterator<String> it = ((List)e.getValue()).iterator();
         if (it.hasNext()) {
            String header = (String)e.getKey();
            if (this.response.containsHeader(header)) {
               this.response.setHeader(header, (String)it.next());
            }

            while(it.hasNext()) {
               this.response.addHeader(header, (String)it.next());
            }
         }
      }

      String reasonPhrase = responseContext.getStatusInfo().getReasonPhrase();
      if (reasonPhrase != null) {
         ServletContainer.setStatus(this.response, responseContext.getStatus(), reasonPhrase);
      } else {
         this.response.setStatus(responseContext.getStatus());
      }

      if (!responseContext.hasEntity()) {
         return null;
      } else {
         try {
            OutputStream outputStream = this.response.getOutputStream();
            return new NonCloseableOutputStreamWrapper(outputStream);
         } catch (IOException e) {
            throw new ContainerException(e);
         }
      }
   }

   public void commit() {
      try {
         this.callSendError();
      } finally {
         this.requestTimeoutHandler.close();
         this.asyncExt.complete();
      }

   }

   private void callSendError() {
      if (!this.configSetStatusOverSendError && !this.response.isCommitted()) {
         ContainerResponse responseContext = this.getResponseContext();
         boolean hasEntity = responseContext.hasEntity();
         Response.StatusType status = responseContext.getStatusInfo();
         if (!hasEntity && status != null && status.getStatusCode() >= 400 && (!this.useSetStatusOn404 || status != Status.NOT_FOUND)) {
            String reason = status.getReasonPhrase();

            try {
               if (reason != null && !reason.isEmpty()) {
                  this.response.sendError(status.getStatusCode(), reason);
               } else {
                  this.response.sendError(status.getStatusCode());
               }
            } catch (IOException ex) {
               throw new ContainerException(LocalizationMessages.EXCEPTION_SENDING_ERROR_RESPONSE(status, reason != null ? reason : "--"), ex);
            }
         }
      }

   }

   public void failure(Throwable error) {
      try {
         if (!this.response.isCommitted()) {
            try {
               int statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
               if (this.configSetStatusOverSendError) {
                  this.response.reset();
                  ServletContainer.setStatus(this.response, statusCode, "Request failed.");
               } else {
                  this.response.sendError(statusCode, "Request failed.");
               }
            } catch (IllegalStateException ex) {
               LOGGER.log(Level.FINER, "Unable to reset failed response.", ex);
            } catch (IOException ex) {
               throw new ContainerException(LocalizationMessages.EXCEPTION_SENDING_ERROR_RESPONSE(Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Request failed."), ex);
            } finally {
               this.asyncExt.complete();
            }
         }
      } finally {
         this.requestTimeoutHandler.close();
         this.responseContext.completeExceptionally(error);
         this.rethrow(error);
      }

   }

   public boolean enableResponseBuffering() {
      return true;
   }

   private void rethrow(Throwable error) {
      if (error instanceof RuntimeException) {
         throw (RuntimeException)error;
      } else {
         throw new ContainerException(error);
      }
   }

   public int getResponseStatus() {
      return this.getResponseContext().getStatus();
   }

   public boolean responseContextResolved() {
      return this.responseContext.isDone();
   }

   public ContainerResponse getResponseContext() {
      try {
         return (ContainerResponse)this.responseContext.get();
      } catch (ExecutionException | InterruptedException ex) {
         throw new ContainerException(ex);
      }
   }

   private static class NonCloseableOutputStreamWrapper extends OutputStream {
      private final OutputStream delegate;

      public NonCloseableOutputStreamWrapper(OutputStream delegate) {
         this.delegate = delegate;
      }

      public void write(int b) throws IOException {
         this.delegate.write(b);
      }

      public void write(byte[] b) throws IOException {
         this.delegate.write(b);
      }

      public void write(byte[] b, int off, int len) throws IOException {
         this.delegate.write(b, off, len);
      }

      public void flush() throws IOException {
         this.delegate.flush();
      }

      public void close() throws IOException {
      }
   }
}
