package scala.collection;

public final class ArrayOps$ReverseIterator$mcC$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final char[] xs$mcC$sp;

   public char next() {
      return this.next$mcC$sp();
   }

   public char next$mcC$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      char r = this.xs$mcC$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcC$sp(final char[] xs$mcC$sp) {
      super(xs$mcC$sp);
      this.xs$mcC$sp = xs$mcC$sp;
   }
}
