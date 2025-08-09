package scala.collection;

public final class ArrayOps$ReverseIterator$mcI$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final int[] xs$mcI$sp;

   public int next() {
      return this.next$mcI$sp();
   }

   public int next$mcI$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      int r = this.xs$mcI$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcI$sp(final int[] xs$mcI$sp) {
      super(xs$mcI$sp);
      this.xs$mcI$sp = xs$mcI$sp;
   }
}
