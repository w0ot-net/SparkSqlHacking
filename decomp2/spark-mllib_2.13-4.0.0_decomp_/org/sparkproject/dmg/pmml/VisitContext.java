package org.sparkproject.dmg.pmml;

import java.util.Deque;
import java.util.Iterator;

interface VisitContext {
   Deque getParents();

   default boolean hasParent() {
      Deque<PMMLObject> parents = this.getParents();
      return !parents.isEmpty();
   }

   default PMMLObject getParent() {
      Deque<PMMLObject> parents = this.getParents();
      return (PMMLObject)parents.getFirst();
   }

   default PMMLObject getParent(int index) {
      Deque<PMMLObject> parents = this.getParents();
      if (index < 0) {
         throw new IllegalArgumentException();
      } else {
         Iterator<PMMLObject> it = parents.iterator();

         for(int i = 0; i < index; ++i) {
            it.next();
         }

         return (PMMLObject)it.next();
      }
   }

   default void pushParent(PMMLObject parent) {
      Deque<PMMLObject> parents = this.getParents();
      parents.addFirst(parent);
   }

   default PMMLObject popParent() {
      Deque<PMMLObject> parents = this.getParents();
      return (PMMLObject)parents.removeFirst();
   }
}
