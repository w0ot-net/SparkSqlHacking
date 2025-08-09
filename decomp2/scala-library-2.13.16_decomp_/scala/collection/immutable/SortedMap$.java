package scala.collection.immutable;

import scala.collection.IterableOnce;
import scala.collection.SortedMapFactory;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class SortedMap$ extends SortedMapFactory.Delegate {
   public static final SortedMap$ MODULE$ = new SortedMap$();
   private static final long serialVersionUID = 3L;

   public SortedMap from(final IterableOnce it, final Ordering evidence$1) {
      if (it instanceof SortedMap) {
         SortedMap var3 = (SortedMap)it;
         if (scala.package$.MODULE$.Ordering() == null) {
            throw null;
         }

         Ordering var4 = var3.ordering();
         if (evidence$1 == null) {
            if (var4 == null) {
               return var3;
            }
         } else if (evidence$1.equals(var4)) {
            return var3;
         }
      }

      return (SortedMap)super.from(it, evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortedMap$.class);
   }

   private SortedMap$() {
      super(TreeMap$.MODULE$);
   }
}
