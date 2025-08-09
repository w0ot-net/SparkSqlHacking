package io.vertx.core.http;

import io.vertx.codegen.annotations.CacheReturn;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.MultiMap;
import java.util.List;

@VertxGen(
   concrete = false
)
public interface HttpResponseHead {
   HttpVersion version();

   int statusCode();

   String statusMessage();

   @CacheReturn
   MultiMap headers();

   @Nullable String getHeader(String var1);

   @GenIgnore({"permitted-type"})
   String getHeader(CharSequence var1);

   @CacheReturn
   List cookies();
}
