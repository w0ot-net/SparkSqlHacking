package net.razorvine.pickle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnpickleStack implements Serializable {
   private static final long serialVersionUID = 5032718425413805422L;
   private final ArrayList stack = new ArrayList();
   protected final Object MARKER = new Object();

   public void add(Object o) {
      this.stack.add(o);
   }

   public void add_mark() {
      this.stack.add(this.MARKER);
   }

   public Object pop() {
      int size = this.stack.size();
      Object result = this.stack.get(size - 1);
      this.stack.remove(size - 1);
      return result;
   }

   public List pop_all_since_marker() {
      ArrayList<Object> result = new ArrayList();

      for(Object o = this.pop(); o != this.MARKER; o = this.pop()) {
         result.add(o);
      }

      result.trimToSize();
      Collections.reverse(result);
      return result;
   }

   public Object peek() {
      return this.stack.get(this.stack.size() - 1);
   }

   public void trim() {
      this.stack.trimToSize();
   }

   public int size() {
      return this.stack.size();
   }

   public void clear() {
      this.stack.clear();
      this.stack.trimToSize();
   }
}
