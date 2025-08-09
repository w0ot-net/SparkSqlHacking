package org.sparkproject.guava.graph;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.AbstractIterator;
import org.sparkproject.guava.collect.UnmodifiableIterator;

@ElementTypesAreNonnullByDefault
abstract class MultiEdgesConnecting extends AbstractSet {
   private final Map outEdgeToNode;
   private final Object targetNode;

   MultiEdgesConnecting(Map outEdgeToNode, Object targetNode) {
      this.outEdgeToNode = (Map)Preconditions.checkNotNull(outEdgeToNode);
      this.targetNode = Preconditions.checkNotNull(targetNode);
   }

   public UnmodifiableIterator iterator() {
      final Iterator<? extends Map.Entry<E, ?>> entries = this.outEdgeToNode.entrySet().iterator();
      return new AbstractIterator() {
         @CheckForNull
         protected Object computeNext() {
            while(true) {
               if (entries.hasNext()) {
                  Map.Entry<E, ?> entry = (Map.Entry)entries.next();
                  if (!MultiEdgesConnecting.this.targetNode.equals(entry.getValue())) {
                     continue;
                  }

                  return entry.getKey();
               }

               return this.endOfData();
            }
         }
      };
   }

   public boolean contains(@CheckForNull Object edge) {
      return this.targetNode.equals(this.outEdgeToNode.get(edge));
   }
}
