package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.streams.ReadStream;

@VertxGen
public interface HttpServerFileUpload extends ReadStream {
   HttpServerFileUpload exceptionHandler(Handler var1);

   HttpServerFileUpload handler(Handler var1);

   HttpServerFileUpload endHandler(Handler var1);

   HttpServerFileUpload pause();

   HttpServerFileUpload resume();

   HttpServerFileUpload fetch(long var1);

   void streamToFileSystem(String var1, Handler var2);

   Future streamToFileSystem(String var1);

   boolean cancelStreamToFileSystem() throws IllegalStateException;

   String filename();

   String name();

   String contentType();

   String contentTransferEncoding();

   String charset();

   long size();

   boolean isSizeAvailable();

   AsyncFile file();
}
