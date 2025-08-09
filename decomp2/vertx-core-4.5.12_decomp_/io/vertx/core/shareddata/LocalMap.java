package io.vertx.core.shareddata;

import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.VertxGen;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@VertxGen
public interface LocalMap extends Map {
   Object get(Object var1);

   Object put(Object var1, Object var2);

   Object remove(Object var1);

   void clear();

   int size();

   boolean isEmpty();

   Object putIfAbsent(Object var1, Object var2);

   boolean removeIfPresent(Object var1, Object var2);

   boolean replaceIfPresent(Object var1, Object var2, Object var3);

   Object replace(Object var1, Object var2);

   void close();

   @GenIgnore
   Set keySet();

   @GenIgnore
   Collection values();

   @GenIgnore
   Object compute(Object var1, BiFunction var2);

   @GenIgnore
   Object computeIfAbsent(Object var1, Function var2);

   @GenIgnore
   Object computeIfPresent(Object var1, BiFunction var2);

   boolean containsKey(Object var1);

   boolean containsValue(Object var1);

   @GenIgnore
   Set entrySet();

   @GenIgnore
   void forEach(BiConsumer var1);

   Object getOrDefault(Object var1, Object var2);

   @GenIgnore
   Object merge(Object var1, Object var2, BiFunction var3);

   @GenIgnore
   void putAll(Map var1);

   @GenIgnore
   boolean remove(Object var1, Object var2);

   @GenIgnore
   boolean replace(Object var1, Object var2, Object var3);

   @GenIgnore
   void replaceAll(BiFunction var1);
}
