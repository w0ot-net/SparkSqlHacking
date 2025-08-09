package org.sparkproject.jetty.proxy;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.sparkproject.jetty.client.api.Request;
import org.sparkproject.jetty.client.api.Response;
import org.sparkproject.jetty.client.api.Result;
import org.sparkproject.jetty.client.util.AsyncRequestContent;
import org.sparkproject.jetty.client.util.InputStreamRequestContent;
import org.sparkproject.jetty.util.Callback;

public class ProxyServlet extends AbstractProxyServlet {
   private static final String CONTINUE_ACTION_ATTRIBUTE = ProxyServlet.class.getName() + ".continueAction";

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int requestId = this.getRequestId(request);
      String rewrittenTarget = this.rewriteTarget(request);
      if (this._log.isDebugEnabled()) {
         StringBuffer uri = request.getRequestURL();
         if (request.getQueryString() != null) {
            uri.append("?").append(request.getQueryString());
         }

         if (this._log.isDebugEnabled()) {
            this._log.debug("{} rewriting: {} -> {}", new Object[]{requestId, uri, rewrittenTarget});
         }
      }

      if (rewrittenTarget == null) {
         this.onProxyRewriteFailed(request, response);
      } else {
         Request proxyRequest = this.newProxyRequest(request, rewrittenTarget);
         this.copyRequestHeaders(request, proxyRequest);
         this.addProxyHeaders(request, proxyRequest);
         AsyncContext asyncContext = request.startAsync();
         asyncContext.setTimeout(0L);
         proxyRequest.timeout(this.getTimeout(), TimeUnit.MILLISECONDS);
         if (this.hasContent(request)) {
            if (this.expects100Continue(request)) {
               AsyncRequestContent delegate = new AsyncRequestContent(new ByteBuffer[0]);
               proxyRequest.body(delegate);
               proxyRequest.attribute(CONTINUE_ACTION_ATTRIBUTE, (Runnable)() -> {
                  try {
                     Request.Content content = this.proxyRequestContent(request, response, proxyRequest);
                     new DelegatingRequestContent(request, proxyRequest, response, content, delegate);
                  } catch (Throwable failure) {
                     this.onClientRequestFailure(request, proxyRequest, response, failure);
                  }

               });
            } else {
               proxyRequest.body(this.proxyRequestContent(request, response, proxyRequest));
            }
         }

         this.sendProxyRequest(request, response, proxyRequest);
      }
   }

   protected Request.Content proxyRequestContent(HttpServletRequest request, HttpServletResponse response, Request proxyRequest) throws IOException {
      return new ProxyInputStreamRequestContent(request, response, proxyRequest, request.getInputStream());
   }

   protected Response.Listener newProxyResponseListener(HttpServletRequest request, HttpServletResponse response) {
      return new ProxyResponseListener(request, response);
   }

   protected void onResponseContent(HttpServletRequest request, HttpServletResponse response, Response proxyResponse, byte[] buffer, int offset, int length, Callback callback) {
      try {
         if (this._log.isDebugEnabled()) {
            this._log.debug("{} proxying content to downstream: {} bytes", this.getRequestId(request), length);
         }

         response.getOutputStream().write(buffer, offset, length);
         callback.succeeded();
      } catch (Throwable x) {
         callback.failed(x);
      }

   }

   protected void onContinue(HttpServletRequest clientRequest, Request proxyRequest) {
      super.onContinue(clientRequest, proxyRequest);
      Runnable action = (Runnable)proxyRequest.getAttributes().get(CONTINUE_ACTION_ATTRIBUTE);
      Executor executor = this.getHttpClient().getExecutor();
      executor.execute(action);
   }

   public static class Transparent extends ProxyServlet {
      private final AbstractProxyServlet.TransparentDelegate delegate = new AbstractProxyServlet.TransparentDelegate(this);

      public void init(ServletConfig config) throws ServletException {
         super.init(config);
         this.delegate.init(config);
      }

      protected String rewriteTarget(HttpServletRequest request) {
         return this.delegate.rewriteTarget(request);
      }
   }

   protected class ProxyResponseListener extends Response.Listener.Adapter {
      private final HttpServletRequest request;
      private final HttpServletResponse response;

      protected ProxyResponseListener(HttpServletRequest request, HttpServletResponse response) {
         this.request = request;
         this.response = response;
      }

      public void onBegin(Response proxyResponse) {
         this.response.setStatus(proxyResponse.getStatus());
      }

      public void onHeaders(Response proxyResponse) {
         ProxyServlet.this.onServerResponseHeaders(this.request, this.response, proxyResponse);
      }

      public void onContent(final Response proxyResponse, ByteBuffer content, Callback callback) {
         int length = content.remaining();
         byte[] buffer;
         int offset;
         if (content.hasArray()) {
            buffer = content.array();
            offset = content.arrayOffset();
         } else {
            buffer = new byte[length];
            content.get(buffer);
            offset = 0;
         }

         ProxyServlet.this.onResponseContent(this.request, this.response, proxyResponse, buffer, offset, length, new Callback.Nested(callback) {
            public void failed(Throwable x) {
               super.failed(x);
               proxyResponse.abort(x);
            }
         });
      }

      public void onComplete(Result result) {
         if (result.isSucceeded()) {
            ProxyServlet.this.onProxyResponseSuccess(this.request, this.response, result.getResponse());
         } else {
            ProxyServlet.this.onProxyResponseFailure(this.request, this.response, result.getResponse(), result.getFailure());
         }

         if (ProxyServlet.this._log.isDebugEnabled()) {
            ProxyServlet.this._log.debug("{} proxying complete", ProxyServlet.this.getRequestId(this.request));
         }

      }
   }

   protected class ProxyInputStreamRequestContent extends InputStreamRequestContent {
      private final HttpServletResponse response;
      private final Request proxyRequest;
      private final HttpServletRequest request;

      protected ProxyInputStreamRequestContent(HttpServletRequest request, HttpServletResponse response, Request proxyRequest, InputStream input) {
         super(input);
         this.request = request;
         this.response = response;
         this.proxyRequest = proxyRequest;
      }

      public long getLength() {
         return (long)this.request.getContentLength();
      }

      protected ByteBuffer onRead(byte[] buffer, int offset, int length) {
         if (ProxyServlet.this._log.isDebugEnabled()) {
            ProxyServlet.this._log.debug("{} proxying content to upstream: {} bytes", ProxyServlet.this.getRequestId(this.request), length);
         }

         return super.onRead(buffer, offset, length);
      }

      protected void onReadFailure(Throwable failure) {
         ProxyServlet.this.onClientRequestFailure(this.request, this.proxyRequest, this.response, failure);
      }
   }

   private class DelegatingRequestContent implements Request.Content.Consumer {
      private final HttpServletRequest clientRequest;
      private final Request proxyRequest;
      private final HttpServletResponse proxyResponse;
      private final AsyncRequestContent delegate;
      private final Request.Content.Subscription subscription;

      private DelegatingRequestContent(HttpServletRequest clientRequest, Request proxyRequest, HttpServletResponse proxyResponse, Request.Content content, AsyncRequestContent delegate) {
         this.clientRequest = clientRequest;
         this.proxyRequest = proxyRequest;
         this.proxyResponse = proxyResponse;
         this.delegate = delegate;
         this.subscription = content.subscribe(this, true);
         this.subscription.demand();
      }

      public void onContent(ByteBuffer buffer, boolean last, Callback callback) {
         Callback wrapped = Callback.from((Runnable)(() -> this.succeeded(callback, last)), (Consumer)((failure) -> this.failed(callback, failure)));
         if (buffer.hasRemaining()) {
            this.delegate.offer(buffer, wrapped);
         } else {
            wrapped.succeeded();
         }

         if (last) {
            this.delegate.close();
         }

      }

      private void succeeded(Callback callback, boolean last) {
         callback.succeeded();
         if (!last) {
            this.subscription.demand();
         }

      }

      private void failed(Callback callback, Throwable failure) {
         callback.failed(failure);
         this.onFailure(failure);
      }

      public void onFailure(Throwable failure) {
         ProxyServlet.this.onClientRequestFailure(this.clientRequest, this.proxyRequest, this.proxyResponse, failure);
      }
   }
}
