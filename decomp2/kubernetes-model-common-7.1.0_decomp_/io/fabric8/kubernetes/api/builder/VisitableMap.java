package io.fabric8.kubernetes.api.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class VisitableMap extends HashMap implements Iterable {
   public List get(Object key) {
      if (!this.containsKey(key)) {
         this.put(String.valueOf(key), new ArrayList());
      }

      return (List)super.get(key);
   }

   public List aggregate() {
      return (List)this.values().stream().flatMap((l) -> l.stream()).collect(Collectors.toList());
   }

   public Iterator iterator() {
      return this.aggregate().iterator();
   }

   public void forEach(Consumer action) {
      this.aggregate().forEach(action);
   }

   public Spliterator spliterator() {
      return this.aggregate().spliterator();
   }
}
