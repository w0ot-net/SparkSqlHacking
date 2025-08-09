package org.apache.commons.math3.geometry.partitioning;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NodesSet implements Iterable {
   private List list = new ArrayList();

   public void add(BSPTree node) {
      for(BSPTree existing : this.list) {
         if (node == existing) {
            return;
         }
      }

      this.list.add(node);
   }

   public void addAll(Iterable iterator) {
      for(BSPTree node : iterator) {
         this.add(node);
      }

   }

   public Iterator iterator() {
      return this.list.iterator();
   }
}
