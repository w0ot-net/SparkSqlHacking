package io.vertx.ext.auth;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collections;
import java.util.Set;

/** @deprecated */
@Deprecated
@VertxGen
public interface HashingAlgorithm {
   String id();

   default Set params() {
      return Collections.emptySet();
   }

   @GenIgnore
   String hash(HashString var1, String var2);

   default boolean needsSeparator() {
      return true;
   }
}
