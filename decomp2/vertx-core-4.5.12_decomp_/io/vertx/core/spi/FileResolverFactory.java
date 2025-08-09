package io.vertx.core.spi;

import io.vertx.core.VertxOptions;
import io.vertx.core.impl.VertxBuilder;
import io.vertx.core.spi.file.FileResolver;

public interface FileResolverFactory extends VertxServiceProvider {
   default void init(VertxBuilder builder) {
      if (builder.fileResolver() == null) {
         FileResolver fileResolver = this.resolver(builder.options());
         builder.fileResolver(fileResolver);
      }

   }

   FileResolver resolver(VertxOptions var1);
}
