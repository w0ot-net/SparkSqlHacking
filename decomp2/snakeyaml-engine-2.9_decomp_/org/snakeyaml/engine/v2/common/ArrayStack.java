package org.snakeyaml.engine.v2.common;

import java.util.ArrayList;

public class ArrayStack {
   private final ArrayList stack;

   public ArrayStack(int initSize) {
      this.stack = new ArrayList(initSize);
   }

   public void push(Object obj) {
      this.stack.add(obj);
   }

   public Object pop() {
      return this.stack.remove(this.stack.size() - 1);
   }

   public boolean isEmpty() {
      return this.stack.isEmpty();
   }
}
