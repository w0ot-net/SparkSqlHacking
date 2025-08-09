package org.apache.curator.framework.recipes.cache;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.apache.curator.shaded.com.google.common.collect.Iterators;

class TreeCacheIterator implements Iterator {
   private final LinkedList stack = new LinkedList();
   private Current current;

   TreeCacheIterator(TreeCache.TreeNode root) {
      this.current = new Current(Iterators.forArray(new TreeCache.TreeNode[]{root}));
      this.stack.push(this.current);
   }

   public boolean hasNext() {
      return this.current != null && TreeCache.isLive(this.current.node.childData);
   }

   public ChildData next() {
      if (this.current == null) {
         throw new NoSuchElementException();
      } else {
         ChildData result = this.current.node.childData;

         do {
            this.setNext();
         } while(this.current != null && !TreeCache.isLive(this.current.node.childData));

         return result;
      }
   }

   private void setNext() {
      if (this.current.node.children != null) {
         this.stack.push(this.current);
         this.current = new Current(this.current.node.children.values().iterator());
      } else {
         while(true) {
            if (this.current.iterator.hasNext()) {
               this.current.node = (TreeCache.TreeNode)this.current.iterator.next();
               break;
            }

            if (this.stack.size() <= 0) {
               this.current = null;
               break;
            }

            this.current = (Current)this.stack.pop();
         }
      }

   }

   private static class Current {
      final Iterator iterator;
      TreeCache.TreeNode node;

      Current(Iterator iterator) {
         this.iterator = iterator;
         this.node = (TreeCache.TreeNode)iterator.next();
      }
   }
}
