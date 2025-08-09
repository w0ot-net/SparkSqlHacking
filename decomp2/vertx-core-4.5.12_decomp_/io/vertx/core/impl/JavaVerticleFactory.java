package io.vertx.core.impl;

import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.impl.verticle.CompilingClassLoader;
import io.vertx.core.spi.VerticleFactory;
import java.util.concurrent.Callable;

public class JavaVerticleFactory implements VerticleFactory {
   public String prefix() {
      return "java";
   }

   public void createVerticle(String verticleName, ClassLoader classLoader, Promise promise) {
      verticleName = VerticleFactory.removePrefix(verticleName);

      Class<Verticle> clazz;
      try {
         if (verticleName.endsWith(".java")) {
            CompilingClassLoader compilingLoader = new CompilingClassLoader(classLoader, verticleName);
            String className = compilingLoader.resolveMainClassName();
            clazz = compilingLoader.loadClass(className);
         } else {
            clazz = classLoader.loadClass(verticleName);
         }
      } catch (ClassNotFoundException e) {
         promise.fail((Throwable)e);
         return;
      }

      promise.complete(clazz::newInstance);
   }
}
