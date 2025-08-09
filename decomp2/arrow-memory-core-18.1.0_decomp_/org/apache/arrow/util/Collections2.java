package org.apache.arrow.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class Collections2 {
   private Collections2() {
   }

   public static List toList(Iterator iterator) {
      List<T> target = new ArrayList();
      Objects.requireNonNull(target);
      iterator.forEachRemaining(target::add);
      return target;
   }

   public static List toList(Iterable iterable) {
      return (List)(iterable instanceof Collection ? new ArrayList((Collection)iterable) : toList(iterable.iterator()));
   }

   public static List toImmutableList(Iterable iterable) {
      return Collections.unmodifiableList(toList(iterable));
   }

   public static Map immutableMapCopy(Map map) {
      return Collections.unmodifiableMap(new HashMap(map));
   }

   public static List immutableListCopy(List list) {
      return Collections.unmodifiableList(new ArrayList(list));
   }

   public static List asImmutableList(Object... values) {
      return Collections.unmodifiableList(Arrays.asList(values));
   }

   public static String toString(Iterator iterator) {
      return (String)StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false).map(String::valueOf).collect(Collectors.joining(", ", "[", "]"));
   }
}
