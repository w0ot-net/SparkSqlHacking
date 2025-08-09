package breeze.util;

import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;

public final class Implicits$ implements DoubleImplicits, IteratorImplicits {
   public static final Implicits$ MODULE$ = new Implicits$();

   static {
      DoubleImplicits.$init$(MODULE$);
      IteratorImplicits.$init$(MODULE$);
   }

   public IteratorImplicits.RichIterator scEnrichIterator(final Iterator iter) {
      return IteratorImplicits.scEnrichIterator$(this, iter);
   }

   public DoubleImplicits.RichDouble RichDouble(final double x) {
      return DoubleImplicits.RichDouble$(this, x);
   }

   public DoubleImplicits.RichFloat RichFloat(final float x) {
      return DoubleImplicits.RichFloat$(this, x);
   }

   public Iterable scEnrichColl(final Iterable __this) {
      return __this;
   }

   public Tuple2[] scEnrichArray(final Tuple2[] __this) {
      return __this;
   }

   private Implicits$() {
   }
}
