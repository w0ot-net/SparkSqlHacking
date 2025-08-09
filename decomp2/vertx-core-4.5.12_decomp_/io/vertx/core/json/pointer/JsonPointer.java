package io.vertx.core.json.pointer;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.json.pointer.impl.JsonPointerImpl;
import java.net.URI;
import java.util.List;

@VertxGen
public interface JsonPointer {
   boolean isRootPointer();

   boolean isLocalPointer();

   boolean isParent(JsonPointer var1);

   String toString();

   @GenIgnore({"permitted-type"})
   URI toURI();

   @GenIgnore({"permitted-type"})
   URI getURIWithoutFragment();

   @Fluent
   JsonPointer append(String var1);

   @Fluent
   JsonPointer append(int var1);

   @Fluent
   JsonPointer append(List var1);

   @Fluent
   JsonPointer append(JsonPointer var1);

   @Fluent
   JsonPointer parent();

   default @Nullable Object query(Object objectToQuery, JsonPointerIterator iterator) {
      return this.queryOrDefault(objectToQuery, iterator, (Object)null);
   }

   Object queryOrDefault(Object var1, JsonPointerIterator var2, Object var3);

   default @Nullable Object queryJson(Object jsonElement) {
      return this.query(jsonElement, JsonPointerIterator.JSON_ITERATOR);
   }

   default @Nullable Object queryJsonOrDefault(Object jsonElement, Object defaultValue) {
      return this.queryOrDefault(jsonElement, JsonPointerIterator.JSON_ITERATOR, defaultValue);
   }

   List tracedQuery(Object var1, JsonPointerIterator var2);

   Object write(Object var1, JsonPointerIterator var2, Object var3, boolean var4);

   default Object writeJson(Object jsonElement, Object newElement) {
      return this.writeJson(jsonElement, newElement, false);
   }

   default Object writeJson(Object jsonElement, Object newElement, boolean createOnMissing) {
      return this.write(jsonElement, JsonPointerIterator.JSON_ITERATOR, newElement, createOnMissing);
   }

   JsonPointer copy();

   static JsonPointer create() {
      return new JsonPointerImpl();
   }

   static JsonPointer from(String pointer) {
      return new JsonPointerImpl(pointer);
   }

   @GenIgnore({"permitted-type"})
   static JsonPointer fromURI(URI uri) {
      return new JsonPointerImpl(uri);
   }
}
