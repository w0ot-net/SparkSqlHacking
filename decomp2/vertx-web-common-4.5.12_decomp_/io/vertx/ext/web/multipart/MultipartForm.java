package io.vertx.ext.web.multipart;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.multipart.impl.MultipartFormImpl;
import java.nio.charset.Charset;

@VertxGen
public interface MultipartForm extends Iterable {
   static MultipartForm create() {
      return new MultipartFormImpl();
   }

   @Fluent
   MultipartForm setCharset(String var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultipartForm setCharset(Charset var1);

   @GenIgnore({"permitted-type"})
   Charset getCharset();

   @Fluent
   MultipartForm attribute(String var1, String var2);

   @Fluent
   MultipartForm textFileUpload(String var1, String var2, String var3, String var4);

   @Fluent
   MultipartForm textFileUpload(String var1, String var2, Buffer var3, String var4);

   @Fluent
   MultipartForm binaryFileUpload(String var1, String var2, String var3, String var4);

   @Fluent
   MultipartForm binaryFileUpload(String var1, String var2, Buffer var3, String var4);
}
