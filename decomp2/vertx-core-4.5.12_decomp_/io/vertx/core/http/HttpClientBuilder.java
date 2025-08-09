package io.vertx.core.http;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Handler;
import java.util.function.Function;

@VertxGen
public interface HttpClientBuilder {
   @Fluent
   HttpClientBuilder with(HttpClientOptions var1);

   @Fluent
   HttpClientBuilder with(PoolOptions var1);

   @Fluent
   HttpClientBuilder withConnectHandler(Handler var1);

   @Fluent
   HttpClientBuilder withRedirectHandler(Function var1);

   HttpClient build();
}
