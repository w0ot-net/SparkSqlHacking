package io.vertx.core.json.pointer;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.pointer.impl.JsonPointerIteratorImpl;

@VertxGen
public interface JsonPointerIterator {
   JsonPointerIterator JSON_ITERATOR = new JsonPointerIteratorImpl();

   boolean isObject(@Nullable Object var1);

   boolean isArray(@Nullable Object var1);

   boolean isNull(@Nullable Object var1);

   boolean objectContainsKey(@Nullable Object var1, String var2);

   Object getObjectParameter(@Nullable Object var1, String var2, boolean var3);

   Object getArrayElement(@Nullable Object var1, int var2);

   boolean writeObjectParameter(@Nullable Object var1, String var2, @Nullable Object var3);

   boolean writeArrayElement(@Nullable Object var1, int var2, @Nullable Object var3);

   boolean appendArrayElement(@Nullable Object var1, @Nullable Object var2);
}
