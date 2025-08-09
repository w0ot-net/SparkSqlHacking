package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.lang.MapMutator;
import java.util.Map;

public class DelegatingMapMutator extends DelegatingMap implements MapMutator {
   protected DelegatingMapMutator(Map delegate) {
      super(delegate);
   }

   protected final MapMutator self() {
      return this;
   }

   public MapMutator empty() {
      this.clear();
      return this.self();
   }

   public MapMutator add(Object key, Object value) {
      this.put(key, value);
      return this.self();
   }

   public MapMutator add(Map m) {
      this.putAll(m);
      return this.self();
   }

   public MapMutator delete(Object key) {
      this.remove(key);
      return this.self();
   }
}
