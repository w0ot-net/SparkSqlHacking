package scala.collection;

import scala.runtime.BoxedUnit;

public final class ArrayOps$ReverseIterator$mcV$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final BoxedUnit[] xs$mcV$sp;

   public void next() {
      this.next$mcV$sp();
   }

   public void next$mcV$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      BoxedUnit var1 = this.xs$mcV$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
   }

   public ArrayOps$ReverseIterator$mcV$sp(final BoxedUnit[] xs$mcV$sp) {
      super(xs$mcV$sp);
      this.xs$mcV$sp = xs$mcV$sp;
   }
}
