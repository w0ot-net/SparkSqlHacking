package org.sparkproject.jetty.server.handler;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpMethod;
import org.sparkproject.jetty.http.HttpStatus;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.http.pathmap.PathSpecSet;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.HttpOutput;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IncludeExclude;
import org.sparkproject.jetty.util.IteratingCallback;
import org.sparkproject.jetty.util.StringUtil;

public class BufferedResponseHandler extends HandlerWrapper {
   private static final Logger LOG = LoggerFactory.getLogger(BufferedResponseHandler.class);
   private final IncludeExclude _methods = new IncludeExclude();
   private final IncludeExclude _paths = new IncludeExclude(PathSpecSet.class);
   private final IncludeExclude _mimeTypes = new IncludeExclude();

   public BufferedResponseHandler() {
      this._methods.include(HttpMethod.GET.asString());

      for(String type : MimeTypes.getKnownMimeTypes()) {
         if (type.startsWith("image/") || type.startsWith("audio/") || type.startsWith("video/")) {
            this._mimeTypes.exclude(type);
         }
      }

      if (LOG.isDebugEnabled()) {
         LOG.debug("{} mime types {}", this, this._mimeTypes);
      }

   }

   public IncludeExclude getMethodIncludeExclude() {
      return this._methods;
   }

   public IncludeExclude getPathIncludeExclude() {
      return this._paths;
   }

   public IncludeExclude getMimeIncludeExclude() {
      return this._mimeTypes;
   }

   protected boolean isMimeTypeBufferable(String mimetype) {
      return this._mimeTypes.test(mimetype);
   }

   protected boolean isPathBufferable(String requestURI) {
      return requestURI == null ? true : this._paths.test(requestURI);
   }

   protected boolean shouldBuffer(HttpChannel channel, boolean last) {
      if (last) {
         return false;
      } else {
         Response response = channel.getResponse();
         int status = response.getStatus();
         if (!HttpStatus.hasNoBody(status) && !HttpStatus.isRedirection(status)) {
            String ct = response.getContentType();
            if (ct == null) {
               return true;
            } else {
               ct = MimeTypes.getContentTypeWithoutCharset(ct);
               return this.isMimeTypeBufferable(StringUtil.asciiToLowerCase(ct));
            }
         } else {
            return false;
         }
      }
   }

   public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      ServletContext context = baseRequest.getServletContext();
      String path = baseRequest.getPathInContext();
      if (LOG.isDebugEnabled()) {
         LOG.debug("{} handle {} in {}", new Object[]{this, baseRequest, context});
      }

      HttpOutput out = baseRequest.getResponse().getHttpOutput();

      for(HttpOutput.Interceptor interceptor = out.getInterceptor(); interceptor != null; interceptor = interceptor.getNextInterceptor()) {
         if (interceptor instanceof BufferedInterceptor) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("{} already intercepting {}", this, request);
            }

            this._handler.handle(target, baseRequest, request, response);
            return;
         }
      }

      if (!this._methods.test(baseRequest.getMethod())) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} excluded by method {}", this, request);
         }

         this._handler.handle(target, baseRequest, request, response);
      } else if (!this.isPathBufferable(path)) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("{} excluded by path {}", this, request);
         }

         this._handler.handle(target, baseRequest, request, response);
      } else {
         String mimeType = context == null ? MimeTypes.getDefaultMimeByExtension(path) : context.getMimeType(path);
         if (mimeType != null) {
            mimeType = MimeTypes.getContentTypeWithoutCharset(mimeType);
            if (!this.isMimeTypeBufferable(mimeType)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("{} excluded by path suffix mime type {}", this, request);
               }

               this._handler.handle(target, baseRequest, request, response);
               return;
            }
         }

         out.setInterceptor(this.newBufferedInterceptor(baseRequest.getHttpChannel(), out.getInterceptor()));
         if (this._handler != null) {
            this._handler.handle(target, baseRequest, request, response);
         }

      }
   }

   protected BufferedInterceptor newBufferedInterceptor(HttpChannel httpChannel, HttpOutput.Interceptor interceptor) {
      return new ArrayBufferedInterceptor(httpChannel, interceptor);
   }

   class ArrayBufferedInterceptor implements BufferedInterceptor {
      private final HttpOutput.Interceptor _next;
      private final HttpChannel _channel;
      private final Queue _buffers = new ArrayDeque();
      private Boolean _aggregating;
      private ByteBuffer _aggregate;

      public ArrayBufferedInterceptor(HttpChannel httpChannel, HttpOutput.Interceptor interceptor) {
         this._next = interceptor;
         this._channel = httpChannel;
      }

      public HttpOutput.Interceptor getNextInterceptor() {
         return this._next;
      }

      public void resetBuffer() {
         this._buffers.clear();
         this._aggregating = null;
         this._aggregate = null;
         BufferedResponseHandler.BufferedInterceptor.super.resetBuffer();
      }

      public void write(ByteBuffer content, boolean last, Callback callback) {
         if (BufferedResponseHandler.LOG.isDebugEnabled()) {
            BufferedResponseHandler.LOG.debug("{} write last={} {}", new Object[]{this, last, BufferUtil.toDetailString(content)});
         }

         if (this._aggregating == null) {
            this._aggregating = BufferedResponseHandler.this.shouldBuffer(this._channel, last);
         }

         if (!this._aggregating) {
            this.getNextInterceptor().write(content, last, callback);
         } else {
            if (last) {
               if (BufferUtil.length(content) > 0) {
                  this._buffers.offer(content);
               }

               if (BufferedResponseHandler.LOG.isDebugEnabled()) {
                  BufferedResponseHandler.LOG.debug("{} committing {}", this, this._buffers.size());
               }

               this.commit(callback);
            } else {
               if (BufferedResponseHandler.LOG.isDebugEnabled()) {
                  BufferedResponseHandler.LOG.debug("{} aggregating", this);
               }

               for(; BufferUtil.hasContent(content); BufferUtil.append(this._aggregate, content)) {
                  if (BufferUtil.space(this._aggregate) == 0) {
                     int size = Math.max(this._channel.getHttpConfiguration().getOutputBufferSize(), BufferUtil.length(content));
                     this._aggregate = BufferUtil.allocate(size);
                     this._buffers.offer(this._aggregate);
                  }
               }

               callback.succeeded();
            }

         }
      }

      private void commit(final Callback callback) {
         if (this._buffers.size() == 0) {
            this.getNextInterceptor().write(BufferUtil.EMPTY_BUFFER, true, callback);
         } else if (this._buffers.size() == 1) {
            this.getNextInterceptor().write((ByteBuffer)this._buffers.poll(), true, callback);
         } else {
            IteratingCallback icb = new IteratingCallback() {
               protected IteratingCallback.Action process() {
                  ByteBuffer buffer = (ByteBuffer)ArrayBufferedInterceptor.this._buffers.poll();
                  if (buffer == null) {
                     return IteratingCallback.Action.SUCCEEDED;
                  } else {
                     ArrayBufferedInterceptor.this.getNextInterceptor().write(buffer, ArrayBufferedInterceptor.this._buffers.isEmpty(), this);
                     return IteratingCallback.Action.SCHEDULED;
                  }
               }

               protected void onCompleteSuccess() {
                  callback.succeeded();
               }

               protected void onCompleteFailure(Throwable cause) {
                  callback.failed(cause);
               }
            };
            icb.iterate();
         }

      }
   }

   protected interface BufferedInterceptor extends HttpOutput.Interceptor {
   }
}
