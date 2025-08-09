package scala.collection.immutable;

import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.MapFactory;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.runtime.ModuleSerializationProxy;

public final class TreeSeqMap$ implements MapFactory {
   public static final TreeSeqMap$ MODULE$ = new TreeSeqMap$();
   private static final TreeSeqMap EmptyByInsertion;
   private static final TreeSeqMap EmptyByModification;
   private static final TreeSeqMap Empty;
   private static final Map$ Mapping;

   static {
      TreeSeqMap$ var10000 = MODULE$;
      TreeSeqMap.Ordering$ var10002 = TreeSeqMap.Ordering$.MODULE$;
      EmptyByInsertion = new TreeSeqMap(TreeSeqMap$Ordering$Zero$.MODULE$, HashMap$.MODULE$.empty(), 0, TreeSeqMap$OrderBy$Insertion$.MODULE$);
      var10002 = TreeSeqMap.Ordering$.MODULE$;
      EmptyByModification = new TreeSeqMap(TreeSeqMap$Ordering$Zero$.MODULE$, HashMap$.MODULE$.empty(), 0, TreeSeqMap$OrderBy$Modification$.MODULE$);
      Empty = MODULE$.EmptyByInsertion();
      Mapping = Map$.MODULE$;
   }

   public Object apply(final Seq elems) {
      return MapFactory.apply$(this, elems);
   }

   public Factory mapFactory() {
      return MapFactory.mapFactory$(this);
   }

   private TreeSeqMap EmptyByInsertion() {
      return EmptyByInsertion;
   }

   private TreeSeqMap EmptyByModification() {
      return EmptyByModification;
   }

   public TreeSeqMap Empty() {
      return Empty;
   }

   public TreeSeqMap empty() {
      return this.empty(TreeSeqMap$OrderBy$Insertion$.MODULE$);
   }

   public TreeSeqMap empty(final TreeSeqMap.OrderBy orderBy) {
      TreeSeqMap$OrderBy$Modification$ var2 = TreeSeqMap$OrderBy$Modification$.MODULE$;
      if (orderBy != null) {
         if (orderBy.equals(var2)) {
            return this.EmptyByModification();
         }
      }

      return this.EmptyByInsertion();
   }

   public TreeSeqMap from(final IterableOnce it) {
      if (it instanceof TreeSeqMap) {
         return (TreeSeqMap)it;
      } else {
         TreeSeqMap.OrderBy newBuilder_newBuilder_orderedBy = TreeSeqMap$OrderBy$Insertion$.MODULE$;
         TreeSeqMap.Builder var10000 = new TreeSeqMap.Builder(newBuilder_newBuilder_orderedBy);
         newBuilder_newBuilder_orderedBy = null;
         return (TreeSeqMap)((Builder)Growable.addAll$(var10000, it)).result();
      }
   }

   public int scala$collection$immutable$TreeSeqMap$$increment(final int ord) {
      return ord == Integer.MAX_VALUE ? Integer.MIN_VALUE : ord + 1;
   }

   public Builder newBuilder() {
      TreeSeqMap.OrderBy newBuilder_orderedBy = TreeSeqMap$OrderBy$Insertion$.MODULE$;
      return new TreeSeqMap.Builder(newBuilder_orderedBy);
   }

   public Builder newBuilder(final TreeSeqMap.OrderBy orderedBy) {
      return new TreeSeqMap.Builder(orderedBy);
   }

   private Map$ Mapping() {
      return Mapping;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(TreeSeqMap$.class);
   }

   private TreeSeqMap$() {
   }
}
