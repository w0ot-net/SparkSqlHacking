package scala.collection;

public final class ArrayOps$ReverseIterator$mcJ$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final long[] xs$mcJ$sp;

   public long next() {
      return this.next$mcJ$sp();
   }

   public long next$mcJ$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      long r = this.xs$mcJ$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcJ$sp(final long[] xs$mcJ$sp) {
      super(xs$mcJ$sp);
      this.xs$mcJ$sp = xs$mcJ$sp;
   }
}
