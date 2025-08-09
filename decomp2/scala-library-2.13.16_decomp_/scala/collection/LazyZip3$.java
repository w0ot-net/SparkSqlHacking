package scala.collection;

import scala.Tuple3;

public final class LazyZip3$ {
   public static final LazyZip3$ MODULE$ = new LazyZip3$();

   public View lazyZip3ToIterable(final LazyZip3 zipped3) {
      if (zipped3 == null) {
         throw null;
      } else {
         return new AbstractView() {
            // $FF: synthetic field
            private final LazyZip3 $outer;

            public AbstractIterator iterator() {
               return new AbstractIterator() {
                  private final Iterator elems1;
                  private final Iterator elems2;
                  private final Iterator elems3;

                  public boolean hasNext() {
                     return this.elems1.hasNext() && this.elems2.hasNext() && this.elems3.hasNext();
                  }

                  public Tuple3 next() {
                     return new Tuple3(this.elems1.next(), this.elems2.next(), this.elems3.next());
                  }

                  public {
                     this.elems1 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll1.iterator();
                     this.elems2 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll2.iterator();
                     this.elems3 = scala$collection$LazyZip3$$anon$$$outer().scala$collection$LazyZip3$$coll3.iterator();
                  }
               };
            }

            public int knownSize() {
               return this.$outer.scala$collection$LazyZip3$$zipKnownSize();
            }

            public boolean isEmpty() {
               return this.$outer.scala$collection$LazyZip3$$coll1.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll2.isEmpty() || this.$outer.scala$collection$LazyZip3$$coll3.isEmpty();
            }

            // $FF: synthetic method
            public LazyZip3 scala$collection$LazyZip3$$anon$$$outer() {
               return this.$outer;
            }

            public {
               if (LazyZip3$.this == null) {
                  throw null;
               } else {
                  this.$outer = LazyZip3$.this;
               }
            }
         };
      }
   }

   private LazyZip3$() {
   }
}
