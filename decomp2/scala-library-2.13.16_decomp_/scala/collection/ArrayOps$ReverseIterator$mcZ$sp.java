package scala.collection;

public final class ArrayOps$ReverseIterator$mcZ$sp extends ArrayOps.ReverseIterator {
   private static final long serialVersionUID = 3L;
   public final boolean[] xs$mcZ$sp;

   public boolean next() {
      return this.next$mcZ$sp();
   }

   public boolean next$mcZ$sp() {
      if (this.scala$collection$ArrayOps$ReverseIterator$$pos < 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         Iterator$.scala$collection$Iterator$$_empty.next();
      }

      boolean r = this.xs$mcZ$sp[this.scala$collection$ArrayOps$ReverseIterator$$pos];
      --this.scala$collection$ArrayOps$ReverseIterator$$pos;
      return r;
   }

   public ArrayOps$ReverseIterator$mcZ$sp(final boolean[] xs$mcZ$sp) {
      super(xs$mcZ$sp);
      this.xs$mcZ$sp = xs$mcZ$sp;
   }
}
