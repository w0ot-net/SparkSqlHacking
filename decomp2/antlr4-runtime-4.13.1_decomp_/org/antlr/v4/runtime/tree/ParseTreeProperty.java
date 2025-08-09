package org.antlr.v4.runtime.tree;

import java.util.IdentityHashMap;
import java.util.Map;

public class ParseTreeProperty {
   protected Map annotations = new IdentityHashMap();

   public Object get(ParseTree node) {
      return this.annotations.get(node);
   }

   public void put(ParseTree node, Object value) {
      this.annotations.put(node, value);
   }

   public Object removeFrom(ParseTree node) {
      return this.annotations.remove(node);
   }
}
