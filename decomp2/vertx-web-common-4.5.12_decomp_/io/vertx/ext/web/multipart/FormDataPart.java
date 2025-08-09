package io.vertx.ext.web.multipart;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.buffer.Buffer;

@VertxGen
public interface FormDataPart {
   @CacheReturn
   String name();

   @CacheReturn
   boolean isAttribute();

   @CacheReturn
   boolean isFileUpload();

   @CacheReturn
   String value();

   @CacheReturn
   String filename();

   @CacheReturn
   String pathname();

   @CacheReturn
   Buffer content();

   @CacheReturn
   String mediaType();

   @CacheReturn
   Boolean isText();
}
