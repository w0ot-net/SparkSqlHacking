package io.vertx.core;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.http.impl.headers.HeadersMultiMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

@VertxGen
public interface MultiMap extends Iterable {
   static MultiMap caseInsensitiveMultiMap() {
      return HeadersMultiMap.headers();
   }

   @GenIgnore({"permitted-type"})
   String get(CharSequence var1);

   @Nullable String get(String var1);

   List getAll(String var1);

   @GenIgnore({"permitted-type"})
   List getAll(CharSequence var1);

   @GenIgnore({"permitted-type"})
   default void forEach(final BiConsumer action) {
      this.forEach(new Consumer() {
         public void accept(Map.Entry entry) {
            action.accept(entry.getKey(), entry.getValue());
         }
      });
   }

   @GenIgnore({"permitted-type"})
   default List entries() {
      List<Map.Entry<String, String>> entries = new ArrayList();
      this.forEach(entries::add);
      return entries;
   }

   boolean contains(String var1);

   @GenIgnore({"permitted-type"})
   boolean contains(CharSequence var1);

   default boolean contains(String name, String value, boolean caseInsensitive) {
      return this.getAll(name).stream().anyMatch((val) -> caseInsensitive ? val.equalsIgnoreCase(value) : val.equals(value));
   }

   @GenIgnore({"permitted-type"})
   default boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
      Predicate<String> predicate;
      if (caseInsensitive) {
         String valueAsString = value.toString();
         predicate = (val) -> val.equalsIgnoreCase(valueAsString);
      } else {
         predicate = (val) -> val.contentEquals(value);
      }

      return this.getAll(name).stream().anyMatch(predicate);
   }

   boolean isEmpty();

   Set names();

   @Fluent
   MultiMap add(String var1, String var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap add(CharSequence var1, CharSequence var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap add(String var1, Iterable var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap add(CharSequence var1, Iterable var2);

   @Fluent
   MultiMap addAll(MultiMap var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap addAll(Map var1);

   @Fluent
   MultiMap set(String var1, String var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap set(CharSequence var1, CharSequence var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap set(String var1, Iterable var2);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap set(CharSequence var1, Iterable var2);

   @Fluent
   MultiMap setAll(MultiMap var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap setAll(Map var1);

   @Fluent
   MultiMap remove(String var1);

   @GenIgnore({"permitted-type"})
   @Fluent
   MultiMap remove(CharSequence var1);

   @Fluent
   MultiMap clear();

   int size();
}
