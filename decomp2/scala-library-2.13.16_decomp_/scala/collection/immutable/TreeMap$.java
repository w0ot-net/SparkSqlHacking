package scala.collection.immutable;

import scala.MatchError;
import scala.Tuple2;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.SortedMapFactory;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class TreeMap$ implements SortedMapFactory {
   public static final TreeMap$ MODULE$ = new TreeMap$();
   private static final long serialVersionUID = 3L;

   static {
      TreeMap$ var10000 = MODULE$;
   }

   public Object apply(final Seq elems, final Ordering evidence$38) {
      return SortedMapFactory.apply$(this, elems, evidence$38);
   }

   public Factory sortedMapFactory(final Ordering evidence$40) {
      return SortedMapFactory.sortedMapFactory$(this, evidence$40);
   }

   public TreeMap empty(final Ordering evidence$1) {
      return new TreeMap(evidence$1);
   }

   public TreeMap from(final IterableOnce it, final Ordering ordering) {
      if (it instanceof TreeMap) {
         TreeMap var3 = (TreeMap)it;
         Ordering var4 = var3.ordering();
         if (ordering == null) {
            if (var4 == null) {
               return var3;
            }
         } else if (ordering.equals(var4)) {
            return var3;
         }
      }

      if (it instanceof scala.collection.SortedMap) {
         scala.collection.SortedMap var5 = (scala.collection.SortedMap)it;
         Ordering var6 = var5.ordering();
         if (ordering == null) {
            if (var6 == null) {
               return new TreeMap(RedBlackTree$.MODULE$.fromOrderedEntries(var5.iterator(), var5.size()), ordering);
            }
         } else if (ordering.equals(var6)) {
            return new TreeMap(RedBlackTree$.MODULE$.fromOrderedEntries(var5.iterator(), var5.size()), ordering);
         }
      }

      RedBlackTree.Tree t = null;

      Object k;
      Object v;
      for(Iterator i = it.iterator(); i.hasNext(); t = RedBlackTree$.MODULE$.update(t, k, v, true, ordering)) {
         Tuple2 var9 = (Tuple2)i.next();
         if (var9 == null) {
            throw new MatchError((Object)null);
         }

         k = var9._1();
         v = var9._2();
      }

      return new TreeMap(t, ordering);
   }

   public ReusableBuilder newBuilder(final Ordering ordering) {
      return new TreeMap.TreeMapBuilder(ordering);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeMap$.class);
   }

   private TreeMap$() {
   }
}
