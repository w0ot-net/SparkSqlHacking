package scala.collection.immutable;

import scala.collection.IterableOnce;
import scala.collection.SortedIterableFactory;
import scala.math.Ordering;
import scala.runtime.ModuleSerializationProxy;

public final class SortedSet$ extends SortedIterableFactory.Delegate {
   public static final SortedSet$ MODULE$ = new SortedSet$();
   private static final long serialVersionUID = 3L;

   public SortedSet from(final IterableOnce it, final Ordering evidence$1) {
      if (it instanceof SortedSet) {
         SortedSet var3 = (SortedSet)it;
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

      return (SortedSet)super.from(it, evidence$1);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortedSet$.class);
   }

   private SortedSet$() {
      super(TreeSet$.MODULE$);
   }
}
