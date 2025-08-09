package io.vertx.ext.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.ext.auth.impl.HashingStrategyImpl;
import java.util.Map;
import java.util.ServiceLoader;

/** @deprecated */
@Deprecated
@VertxGen
public interface HashingStrategy {
   static HashingStrategy load() {
      HashingStrategyImpl strategy = new HashingStrategyImpl();

      for(HashingAlgorithm algorithm : ServiceLoader.load(HashingAlgorithm.class)) {
         strategy.add(algorithm);
      }

      return strategy;
   }

   String hash(String var1, Map var2, String var3, String var4);

   boolean verify(String var1, String var2);

   HashingAlgorithm get(String var1);

   @Fluent
   HashingStrategy put(String var1, HashingAlgorithm var2);
}
