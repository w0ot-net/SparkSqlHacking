package scala.collection;

import scala.runtime.Statics;

public final class ArrayOps$ArrayIterator$mcC$sp extends ArrayOps.ArrayIterator {
   private static final long serialVersionUID = 3L;
   public final char[] xs$mcC$sp;

   public char next() {
      return this.next$mcC$sp();
   }

   public char next$mcC$sp() {
      if (this.scala$collection$ArrayOps$ArrayIterator$$pos >= this.xs$mcC$sp.length) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      char r = this.xs$mcC$sp[this.scala$collection$ArrayOps$ArrayIterator$$pos];
      ++this.scala$collection$ArrayOps$ArrayIterator$$pos;
      return r;
   }

   public ArrayOps$ArrayIterator$mcC$sp(final char[] xs$mcC$sp) {
      super(xs$mcC$sp);
      this.xs$mcC$sp = xs$mcC$sp;
      Statics.releaseFence();
   }
}
