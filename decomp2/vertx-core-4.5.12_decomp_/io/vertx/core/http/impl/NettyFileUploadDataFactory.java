package io.vertx.core.http.impl;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerFileUpload;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.ContextInternal;
import java.nio.charset.Charset;
import java.util.function.Supplier;

class NettyFileUploadDataFactory extends DefaultHttpDataFactory {
   private final ContextInternal context;
   private final HttpServerRequest request;
   private final Supplier lazyUploadHandler;

   NettyFileUploadDataFactory(ContextInternal context, HttpServerRequest request, Supplier lazyUploadHandler) {
      super(false);
      this.context = context;
      this.request = request;
      this.lazyUploadHandler = lazyUploadHandler;
   }

   public FileUpload createFileUpload(HttpRequest httpRequest, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
      NettyFileUpload nettyUpload = new NettyFileUpload(this.context, this.request, name, filename, contentType, contentTransferEncoding, charset, size);
      HttpServerFileUploadImpl upload = new HttpServerFileUploadImpl(this.context, nettyUpload, name, filename, contentType, contentTransferEncoding, charset, size);
      Handler<HttpServerFileUpload> uploadHandler = (Handler)this.lazyUploadHandler.get();
      if (uploadHandler != null) {
         this.context.dispatch(upload, uploadHandler);
      }

      return nettyUpload;
   }
}
