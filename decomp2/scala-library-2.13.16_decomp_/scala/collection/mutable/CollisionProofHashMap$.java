package scala.collection.mutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.SortedMapFactory;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class CollisionProofHashMap$ implements SortedMapFactory {
   public static final CollisionProofHashMap$ MODULE$ = new CollisionProofHashMap$();
   private static final long serialVersionUID = 3L;

   static {
      CollisionProofHashMap$ var10000 = MODULE$;
   }

   public Object apply(final scala.collection.immutable.Seq elems, final Ordering evidence$38) {
      return SortedMapFactory.apply$(this, elems, evidence$38);
   }

   public Factory sortedMapFactory(final Ordering evidence$40) {
      return SortedMapFactory.sortedMapFactory$(this, evidence$40);
   }

   public final String ordMsg() {
      return "No implicit Ordering[${K2}] found to build a CollisionProofHashMap[${K2}, ${V2}]. You may want to upcast to a Map[${K}, ${V}] first by calling `unsorted`.";
   }

   public CollisionProofHashMap from(final IterableOnce it, final Ordering evidence$1) {
      int k = it.knownSize();
      int cap = k > 0 ? (int)((double)(k + 1) / (double)0.75F) : 16;
      return (new CollisionProofHashMap(cap, (double)0.75F, evidence$1)).addAll(it);
   }

   public CollisionProofHashMap empty(final Ordering evidence$2) {
      return new CollisionProofHashMap(evidence$2);
   }

   public Builder newBuilder(final Ordering evidence$3) {
      double newBuilder_loadFactor = (double)0.75F;
      int newBuilder_initialCapacity = 16;
      return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor, evidence$3) {
         public void sizeHint(final int size) {
            ((CollisionProofHashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public Builder newBuilder(final int initialCapacity, final double loadFactor, final Ordering evidence$4) {
      return new GrowableBuilder(initialCapacity, loadFactor, evidence$4) {
         public void sizeHint(final int size) {
            ((CollisionProofHashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public final double defaultLoadFactor() {
      return (double)0.75F;
   }

   public final int defaultInitialCapacity() {
      return 16;
   }

   private int compare(final Object key, final int hash, final CollisionProofHashMap.LLNode node, final Ordering ord) {
      int i = hash - node.hash();
      return i != 0 ? i : ord.compare(key, node.key());
   }

   public int scala$collection$mutable$CollisionProofHashMap$$compare(final Object key, final int hash, final CollisionProofHashMap.RBNode node, final Ordering ord) {
      return ord.compare(key, node.key());
   }

   private final int treeifyThreshold() {
      return 8;
   }

   public CollisionProofHashMap.RBNode scala$collection$mutable$CollisionProofHashMap$$leaf(final Object key, final int hash, final Object value, final boolean red, final CollisionProofHashMap.RBNode parent) {
      return new CollisionProofHashMap.RBNode(key, hash, value, red, (CollisionProofHashMap.RBNode)null, (CollisionProofHashMap.RBNode)null, parent);
   }

   public CollisionProofHashMap.RBNode scala$collection$mutable$CollisionProofHashMap$$minNodeNonNull(final CollisionProofHashMap.RBNode node) {
      while(node.left() != null) {
         node = node.left();
      }

      return node;
   }

   public CollisionProofHashMap.RBNode scala$collection$mutable$CollisionProofHashMap$$successor(final CollisionProofHashMap.RBNode node) {
      if (node.right() != null) {
         return this.scala$collection$mutable$CollisionProofHashMap$$minNodeNonNull(node.right());
      } else {
         CollisionProofHashMap.RBNode x = node;

         CollisionProofHashMap.RBNode y;
         for(y = node.parent(); y != null && x == y.right(); y = y.parent()) {
            x = y;
         }

         return y;
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CollisionProofHashMap$.class);
   }

   private CollisionProofHashMap$() {
   }
}
